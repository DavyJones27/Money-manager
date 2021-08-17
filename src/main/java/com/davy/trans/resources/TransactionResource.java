package com.davy.trans.resources;

import com.davy.trans.domain.Transaction;
import com.davy.trans.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody Transaction transaction
    ) {
        Integer userId = getUserId(request);
        transaction.setUserId(userId);
        transaction.setCategoryId(categoryId);

        Transaction newTransaction = transactionService.addTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }


    private Integer getUserId(HttpServletRequest request) {

        return (Integer) request.getAttribute("userId");
    }

}
