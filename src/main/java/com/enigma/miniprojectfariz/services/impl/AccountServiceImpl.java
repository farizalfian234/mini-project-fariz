package com.enigma.miniprojectfariz.services.impl;

import com.enigma.miniprojectfariz.constants.ResponseMessage;
import com.enigma.miniprojectfariz.dto.AccountDTO;
import com.enigma.miniprojectfariz.entities.Account;
import com.enigma.miniprojectfariz.entities.Bank;
import com.enigma.miniprojectfariz.exceptions.DataAlreadyUsed;
import com.enigma.miniprojectfariz.exceptions.DataNotFound;
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
        if (accountRepository.findAccountByNumber(account.getNumber()).isPresent()) {
            throw new DataAlreadyUsed(String.format(ResponseMessage.DATA_IS_USED));
        }
        Bank bank = bankService.getBankByName(account.getBank().getName());
        account.setBank(bank);
        return accountRepository.save(account);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateBalance(Account account, Double amount, String type) {
        Account account1 = accountRepository.
                findAccountByNumberAndPassword(account.getNumber(), account.getPassword()).get();
        if (type.equals("withdraw")) {
            if (account1.getBalance()>=amount) {
                account1.setBalance(account1.getBalance()-amount);
                return accountRepository.save(account1);
            } else {
                throw new DataAlreadyUsed(String.format(ResponseMessage.INVALID_AMOUNT));
            }
        } else if (type.equals("deposit")) {
            account1.setBalance(account1.getBalance()+amount);
            return accountRepository.save(account1);
        }
        return null;
    }

    @Override
    public void deleteAccount(Account account) {
        if (!(accountRepository.findAccountByNumberAndPassword(account.getNumber(), account.getPassword()).isPresent())) {
            throw new DataNotFound(String.format(ResponseMessage.DATA_NOT_FOUND));
        }
        Account account1 = accountRepository.findAccountByNumberAndPassword(account.getNumber(), account.getPassword()).get();
        account1.setIsDeleted(true);
        accountRepository.save(account1);
    }

    @Override
    public Page<Account> getAccount(Pageable pageable, AccountDTO accountDTO) {
        Specification<Account> accountSpecification = AccountSpecification.getSpecification(accountDTO);
        return accountRepository.findAll(accountSpecification, pageable);
    }

    @Override
    public Account getAccountByNumber(String number) {
        if (!(accountRepository.findAccountByNumber(number).isPresent()) ||
                accountRepository.findAccountByNumber(number).get().getIsDeleted()) {
            throw new DataNotFound(String.format(ResponseMessage.DATA_NOT_FOUND));
        }
        return accountRepository.findAccountByNumber(number).get();
    }

    @Override
    public Account getAccountByNumberAndPassword(String number, String password) {
        if (!(accountRepository.findAccountByNumberAndPassword(number, password).isPresent()) ||
                accountRepository.findAccountByNumberAndPassword(number, password).get().getIsDeleted()) {
            throw new DataNotFound(String.format(ResponseMessage.DATA_NOT_FOUND));
        }
        return accountRepository.findAccountByNumberAndPassword(number, password).get();
    }
}
