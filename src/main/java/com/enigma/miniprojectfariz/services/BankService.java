package com.enigma.miniprojectfariz.services;

import com.enigma.miniprojectfariz.entities.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService {
    Bank addBank(Bank bank);
    Page<Bank> getBank(Pageable pageable);
    Bank getBankByName(String name);
}
