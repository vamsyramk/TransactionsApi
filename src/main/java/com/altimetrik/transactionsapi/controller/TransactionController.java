package com.altimetrik.transactionsapi.controller;


import com.altimetrik.transactionsapi.dto.ApiResponse;
import com.altimetrik.transactionsapi.modal.Statistics;
import com.altimetrik.transactionsapi.modal.Transaction;
import com.altimetrik.transactionsapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/altimetrik/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/stats")
    public ResponseEntity<Statistics> getStatistics() {
        return new ResponseEntity<>(transactionService.getTransactionsInformation(), HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<ApiResponse> saveTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<ApiResponse> deleteTransactions() {
        return new ResponseEntity<>(transactionService.deleteTransactions(), HttpStatus.NO_CONTENT);
    }

}
