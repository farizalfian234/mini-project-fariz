package com.enigma.miniprojectfariz.services;

import com.enigma.miniprojectfariz.dto.AccountDTO;
import com.enigma.miniprojectfariz.entities.Account;
import com.enigma.miniprojectfariz.entities.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Account addAccount(Account account);
    Page<Account> getAccount(Pageable pageable, AccountDTO accountDTO);
    Account getAccountByNumber(String number);
    Account getAccountByNumberAndPassword(String number, String password);
}
