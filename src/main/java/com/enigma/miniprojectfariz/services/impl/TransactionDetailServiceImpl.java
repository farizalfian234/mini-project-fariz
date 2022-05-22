package com.enigma.miniprojectfariz.services.impl;

import com.enigma.miniprojectfariz.entities.TransactionDetail;
import com.enigma.miniprojectfariz.repositories.TransactionDetailRepository;
import com.enigma.miniprojectfariz.services.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {
    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Override
    public TransactionDetail saveTransactionDetail(TransactionDetail transactionDetail) {
        return transactionDetailRepository.save(transactionDetail);
    }
}
