package com.enigma.miniprojectfariz.services.impl;

import com.enigma.miniprojectfariz.dto.AccountDTO;
import com.enigma.miniprojectfariz.entities.Account;
import com.enigma.miniprojectfariz.entities.Bank;
import com.enigma.miniprojectfariz.repositories.AccountRepository;
import com.enigma.miniprojectfariz.services.AccountService;
import com.enigma.miniprojectfariz.services.BankService;
import com.enigma.miniprojectfariz.specifications.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BankService bankService;

    @Override
    public Account addAccount(Account account) {
        Bank bank = bankService.getBankByName(account.getBank().getName());
        account.setBank(bank);
        return accountRepository.save(account);
    }

    @Override
    public Page<Account> getAccount(Pageable pageable, AccountDTO accountDTO) {
        Specification<Account> accountSpecification = AccountSpecification.getSpecification(accountDTO);
        return accountRepository.findAll(accountSpecification, pageable);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return accountRepository.findAccountByNumber(number).get();
    }

    @Override
    public Account getAccountByNumberAndPassword(String number, String password) {
        return accountRepository.findAccountByNumberAndPassword(number, password).get();
    }
}
