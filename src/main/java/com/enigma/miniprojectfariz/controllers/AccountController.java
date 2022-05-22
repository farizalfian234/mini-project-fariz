package com.enigma.miniprojectfariz.controllers;

import com.enigma.miniprojectfariz.URL;
import com.enigma.miniprojectfariz.dto.AccountDTO;
import com.enigma.miniprojectfariz.entities.Account;
import com.enigma.miniprojectfariz.services.AccountService;
import com.enigma.miniprojectfariz.utils.PageResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URL.ACCOUNT)
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping
    public Account addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping
    public PageResponseWrapper<Account> getAccount(@RequestBody AccountDTO accountDTO,
                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                   @RequestParam(name = "sort", defaultValue = "name") String sort,
                                                   @RequestParam(name = "direction", defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, sizePerPage, Sort.by(direction, sort));
        Page<Account> accountPage = accountService.getAccount(pageable, accountDTO);
        return new PageResponseWrapper<>(accountPage);
    }
}
