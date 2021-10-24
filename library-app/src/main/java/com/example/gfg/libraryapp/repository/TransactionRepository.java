package com.example.gfg.libraryapp.repository;

import com.example.gfg.libraryapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    // This function should return single latest tranction row made by this studentId and this book
    @Query(value = "select * from transaction t  where t.book_id = ?1 and t.student_id = ?2 order by t.id desc limit 1",nativeQuery = true)
    Transaction getTransaction(int bookId,int studentId);




}
