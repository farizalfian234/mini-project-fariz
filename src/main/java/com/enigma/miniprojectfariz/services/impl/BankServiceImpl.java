package com.enigma.miniprojectfariz.services.impl;

import com.enigma.miniprojectfariz.entities.Bank;
import com.enigma.miniprojectfariz.repositories.BankRepository;
import com.enigma.miniprojectfariz.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Page<Bank> getAllBank(Pageable pageable) {
        return bankRepository.findAll(pageable);
    }

    @Override
    public Bank getBankByName(String name) {
        return bankRepository.findBankByName(name).get();
    }
}
