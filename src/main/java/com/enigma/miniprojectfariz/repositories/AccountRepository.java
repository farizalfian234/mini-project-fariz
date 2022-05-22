package com.enigma.miniprojectfariz.repositories;

import com.enigma.miniprojectfariz.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor {
    Optional<Account> findAccountByNumber(String number);
    Optional<Account> findAccountByNumberAndPassword(String number, String password);
}
