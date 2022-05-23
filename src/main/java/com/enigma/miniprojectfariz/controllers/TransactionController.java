package com.enigma.miniprojectfariz.controllers;

import com.enigma.miniprojectfariz.constants.URL;
import com.enigma.miniprojectfariz.entities.Transaction;
import com.enigma.miniprojectfariz.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URL.TRANSACTION)
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public Transaction transfer(@RequestBody Transaction transaction) {
        return transactionService.transfer(transaction);
    }
}
