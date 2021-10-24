package com.example.gfg.libraryapp.services;

import com.example.gfg.libraryapp.models.*;
import com.example.gfg.libraryapp.repository.TransactionRepository;
import com.example.gfg.libraryapp.requests.BookIssueRequest;
import com.example.gfg.libraryapp.requests.BookReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Value("${student.max-alloted-books}")             // Variable imported from application properties
    int max_books;

    @Value("${books.alloted-time}")
    int daysAlloted;

    @Value("${books.fine_per_day}")
    int finePerDay;


    public String issueBook(BookIssueRequest bookIssueRequest) throws Exception {
        int bookId = bookIssueRequest.getBookId();
        int studentId = bookIssueRequest.getStudentId();

        Book book = bookService.getBook(bookId);
        Student student = studentService.getStudent(studentId);

        if(book==null){
            throw new Exception("There is no book present with bookId" + bookId);
        }

        if(book.getStudent()!=null){
            throw new Exception("The book is already issued by someone");
        }

        if(student==null){
            throw new Exception("Student is not present in database or maybe deleted");
        }

        if(student.getBooks().size() == max_books){
            throw new Exception("Student has reached the threshold of issued books");
        }

        // 1. Update the book table - studentId
        // 2. Create a entry in the transaction table

        Transaction transaction = Transaction.builder()
                                    .transactionStatus(TransactionStatus.PENDING)
                                    .book(book)
                                    .transactionType(TransactionType.ISSUE)
                                    .student(student)
                                    .transactionId(UUID.randomUUID().toString())
                                    .build();
        try {
            transaction = transactionRepository.save(transaction);   // dont do only transactionRepository.save(transaction);  it will create seperate entry on save() again

            book.setStudent(student);
            bookService.createBook(book);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);

            transactionRepository.save(transaction);  // upadte the transaction entry
            return transaction.getTransactionId();

        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setRemarks(e.getMessage());
            transactionRepository.save(transaction);
            throw new Exception("Transaction couldn't be completed" + e.getMessage());
        }




    }

    public String returnBook(BookReturnRequest bookReturnRequest) throws Exception {

        int bookId = bookReturnRequest.getBookId();
        int studentId = bookReturnRequest.getStudentId();

        Book book = bookService.getBook(bookId);
        Student student = studentService.getStudent(studentId);

        if(book==null){
            throw new Exception("There is no book present with bookId" + bookId);
        }

        if(book.getStudent()==null){
            throw new Exception("The book which is available cannot be returned");
        }

        if(student==null){
            throw new Exception("Student is not present in database or maybe deleted");
        }

        Transaction issuetransaction = transactionRepository.getTransaction(bookId,studentId);

        if(!TransactionType.ISSUE.equals(issuetransaction.getTransactionType())){
            throw new Exception("The book is already returned");
        }

        //Get issue date for fine calculation
        Date issueTransactionDate = issuetransaction.getTransactionTime();
        long currentTimeMillis = System.currentTimeMillis();

        long issueTransactionTimeMillis = issueTransactionDate.getTime();

        long time_diff = currentTimeMillis - issueTransactionTimeMillis;

        long days_passed = TimeUnit.DAYS.convert(time_diff,TimeUnit.MICROSECONDS);

        int fine = 0;

        if(days_passed>daysAlloted){
            fine += finePerDay*(days_passed-daysAlloted);
        }

        Transaction transaction = Transaction.builder()
                                    .transactionType(TransactionType.RETURN)
                                    .fine(fine)
                                    .book(book)
                                    .student(student)
                                    .transactionStatus(TransactionStatus.PENDING)
                                    .remarks(bookReturnRequest.getRemarks())
                                    .transactionId(UUID.randomUUID().toString())
                                    .build();

        transactionRepository.save(transaction);

        book.setStudent(null);
        bookService.createBook(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        return transaction.getTransactionId();

    }

}
