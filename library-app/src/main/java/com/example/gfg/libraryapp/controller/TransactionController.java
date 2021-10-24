package com.example.gfg.libraryapp.controller;

import com.example.gfg.libraryapp.requests.BookIssueRequest;
import com.example.gfg.libraryapp.requests.BookReturnRequest;
import com.example.gfg.libraryapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("/issue")
    public String issueBook(@RequestBody BookIssueRequest bookIssueRequest) throws Exception {
       return transactionService.issueBook(bookIssueRequest);
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody BookReturnRequest bookReturnRequest) throws Exception {
        return transactionService.returnBook(bookReturnRequest);
    }


}
