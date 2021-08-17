package com.davy.trans.services;

import com.davy.trans.domain.Transaction;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;
import com.davy.trans.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {

        return transactionRepository.findAll(userId, categoryId);
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException {

        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws EtBadRequestException {

        int transactionId = transactionRepository.create(transaction);

        return transactionRepository.findById(transaction.getUserId(), transaction.getCategoryId(), transactionId);
    }

    @Override
    public void UpdateTransaction(Transaction transaction) throws EtBadRequestException {

        transactionRepository.update(transaction);

    }

    @Override
    public void removeTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException {

    }
}
