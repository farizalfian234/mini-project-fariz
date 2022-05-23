package com.enigma.miniprojectfariz.repositories;

import com.enigma.miniprojectfariz.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor {
    @Query(value = "SELECT * FROM account WHERE number=?", nativeQuery = true)
    Optional<Account> findAccountByNumber(String number);
    @Query(value = "SELECT * FROM account WHERE number=? AND password=?", nativeQuery = true)
    Optional<Account> findAccountByNumberAndPassword(String number, String password);
}
