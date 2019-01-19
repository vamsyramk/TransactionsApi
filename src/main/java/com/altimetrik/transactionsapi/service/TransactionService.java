package com.altimetrik.transactionsapi.service;

import com.altimetrik.transactionsapi.dto.ApiResponse;
import com.altimetrik.transactionsapi.modal.Statistics;
import com.altimetrik.transactionsapi.modal.Transaction;

public interface TransactionService {

    ApiResponse saveTransaction(Transaction transaction);

    Statistics getTransactionsInformation();

    ApiResponse deleteTransactions();
}
