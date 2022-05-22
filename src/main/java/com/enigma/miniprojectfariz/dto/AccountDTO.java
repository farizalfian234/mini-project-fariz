package com.enigma.miniprojectfariz.dto;

import com.enigma.miniprojectfariz.entities.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private Bank bank;
    private String number;
    private String name;
    private Boolean isDeleted;
}
