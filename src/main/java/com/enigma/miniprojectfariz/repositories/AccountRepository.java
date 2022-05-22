package com.enigma.miniprojectfariz.repositories;

import com.enigma.miniprojectfariz.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
