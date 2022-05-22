package com.enigma.miniprojectfariz.controllers;

import com.enigma.miniprojectfariz.URL;
import com.enigma.miniprojectfariz.entities.Bank;
import com.enigma.miniprojectfariz.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URL.BANK)
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    public Bank addBank(@RequestBody Bank bank) {
        return bankService.addBank(bank);
    }

    @GetMapping
    public Page<Bank> getBankPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
                                     @RequestParam(name = "sort", defaultValue = "name") String sort,
                                     @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, sizePerPage, Sort.by(direction, sort));
        return bankService.getBank(pageable);
    }
}