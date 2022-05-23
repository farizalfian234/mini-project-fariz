package com.enigma.miniprojectfariz.services.impl;

import com.enigma.miniprojectfariz.constants.ResponseMessage;
import com.enigma.miniprojectfariz.entities.Bank;
import com.enigma.miniprojectfariz.exceptions.DataAlreadyUsed;
import com.enigma.miniprojectfariz.exceptions.DataNotFound;
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
        if (bankRepository.findBankByName(bank.getName()).isPresent()) {
            throw new DataAlreadyUsed(String.format(ResponseMessage.DATA_IS_USED));
        }
        return bankRepository.save(bank);
    }

    @Override
    public Page<Bank> getBank(Pageable pageable) {
        return bankRepository.findAll(pageable);
    }

    @Override
    public Bank getBankByName(String name) {
        if (!(bankRepository.findBankByName(name).isPresent())) {
            throw new DataNotFound(String.format(ResponseMessage.DATA_NOT_FOUND));
        }
        return bankRepository.findBankByName(name).get();
    }
}
