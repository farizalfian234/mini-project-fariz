package com.enigma.miniprojectfariz.repositories;

import com.enigma.miniprojectfariz.entities.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {
}
