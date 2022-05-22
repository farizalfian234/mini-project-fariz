package com.enigma.miniprojectfariz.services.impl;

import com.enigma.miniprojectfariz.entities.Account;
import com.enigma.miniprojectfariz.entities.Transaction;
import com.enigma.miniprojectfariz.entities.TransactionDetail;
import com.enigma.miniprojectfariz.repositories.TransactionRepository;
import com.enigma.miniprojectfariz.services.AccountService;
import com.enigma.miniprojectfariz.services.TransactionDetailService;
import com.enigma.miniprojectfariz.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionDetailService transactionDetailService;

    @Autowired
    AccountService accountService;

    @Override
    @Transient
    @Transactional
    public Transaction transfer(Transaction transaction) {
        Account account = accountService.getAccountByNumberAndPassword(transaction.getAccount().getNumber(),
                transaction.getAccount().getPassword());
        transaction.setAccount(account);

        Transaction transaction1 = transactionRepository.save(transaction);

        for(TransactionDetail transactionDetail: transaction1.getTransactionDetailList()) {
            transactionDetail.setTransaction(transaction1);

            Account targetAccount = accountService.getAccountByNumber(transactionDetail.getTarget().getNumber());
            account.setBalance(account.getBalance()-transactionDetail.getAmount());
            targetAccount.setBalance(targetAccount.getBalance()+transactionDetail.getAmount());

            if (!account.getBank().getName().equals(targetAccount.getBank().getName())) {
                account.setBalance(account.getBalance()-2500.0);
            }

            accountService.saveAccount(account);
            accountService.saveAccount(targetAccount);

            transactionDetail.setTarget(targetAccount);
            transactionDetail.setDate(LocalDateTime.now());
            transactionDetailService.saveTransactionDetail(transactionDetail);
        }
        return transactionRepository.save(transaction1);
    }
}
