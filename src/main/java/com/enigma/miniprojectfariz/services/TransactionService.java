package com.enigma.miniprojectfariz.services;

import com.enigma.miniprojectfariz.entities.Transaction;

public interface TransactionService {
    Transaction transfer(Transaction transaction);
}
