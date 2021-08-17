package com.davy.trans.repositories;

import com.davy.trans.domain.Transaction;
import com.davy.trans.exceptions.EtBadRequestException;
import com.davy.trans.exceptions.EtResourcesNotFoundException;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException;

    Integer create(Transaction transaction) throws EtBadRequestException;

    void update(Transaction transaction) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourcesNotFoundException;
}
