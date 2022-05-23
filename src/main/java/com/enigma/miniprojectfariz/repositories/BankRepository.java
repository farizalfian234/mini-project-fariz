package com.enigma.miniprojectfariz.repositories;

import com.enigma.miniprojectfariz.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {
    @Query(value = "SELECT * FROM mst_bank WHERE name=?", nativeQuery = true)
    Optional<Bank> findBankByName(String name);
}
