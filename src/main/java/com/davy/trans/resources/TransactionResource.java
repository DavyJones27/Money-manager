package com.davy.trans.resources;

import com.davy.trans.domain.Transaction;
import com.davy.trans.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @PathVariable("transactionId") Integer transactionId
    ) {
        Integer userId = getUserId(request);

        Transaction newTransaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId
    ) {
        Integer userId = getUserId(request);

        List<Transaction> newTransaction = transactionService.fetchAllTransactions(userId, categoryId);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(
            HttpServletRequest request,
            @PathVariable("categoryId") Integer categoryId,
            @PathVariable("transactionId") Integer transactionId,
            @RequestBody Transaction transaction
    ) {
        Integer userId = getUserId(request);
        transaction.setUserId(userId);
        transaction.setCategoryId(categoryId);
        transaction.setTransactionId(transactionId);

        transactionService.UpdateTransaction(transaction);
        Map<String, Boolean> map = new HashMap<>();

        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    private Integer getUserId(HttpServletRequest request) {

        return (Integer) request.getAttribute("userId");
    }

}
