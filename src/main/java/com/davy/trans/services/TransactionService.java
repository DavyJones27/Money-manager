package com.davy.trans.services;


import com.davy.trans.domain.Transaction;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException;

    Transaction addTransaction(Transaction transaction) throws EtBadRequestException;

    void UpdateTransaction(Transaction transaction) throws EtBadRequestException;

    void removeTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException;


}
