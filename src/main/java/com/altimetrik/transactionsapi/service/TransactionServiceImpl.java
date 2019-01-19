package com.altimetrik.transactionsapi.service;

import com.altimetrik.transactionsapi.dto.ApiResponse;
import com.altimetrik.transactionsapi.exception.TransactionApiException;
import com.altimetrik.transactionsapi.modal.Statistics;
import com.altimetrik.transactionsapi.modal.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private List<Transaction> transactions = new ArrayList<>();

    private synchronized void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private synchronized List<Transaction> getTransactions() {
        return transactions;
    }

    private synchronized void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }


    @Override
    public ApiResponse saveTransaction(Transaction transaction) {
        ApiResponse apiResponse = new ApiResponse();
        Duration duration = Duration.between(transaction.getTime(), LocalDateTime.now());
        if (duration.getSeconds() > 60) {
            throw new TransactionApiException(204, "The transaction has not been updated successfully", HttpStatus.NO_CONTENT);
        }
        addTransaction(transaction);
        apiResponse.setStatus("Success");
        apiResponse.setMessage("The transaction has been updated successfully");
        return apiResponse;
    }

    @Override
    public Statistics getTransactionsInformation() {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        long count = 0;
        double sum = 0;
        double avg = 0;
        for (Transaction transaction : getTransactions()) {
            Duration duration = Duration.between(transaction.getTime(), LocalDateTime.now());
            if (duration.getSeconds() < 60) {
                sum += transaction.getAmount();
                count++;
                if (transaction.getAmount() > max) {
                    max = transaction.getAmount();
                }
                if (transaction.getAmount() < min) {
                    min = transaction.getAmount();
                }
                avg = sum / count;
            }
        }
        return new Statistics(min, max, avg, sum, count);
    }

    @Override
    public ApiResponse deleteTransactions() {
        setTransactions(new ArrayList<>());
        ApiResponse apiResponse = new ApiResponse();
        if (CollectionUtils.isEmpty(getTransactions())) {
            apiResponse.setStatus("Success");
            apiResponse.setMessage("The transactions have been deleted successfully");
            return apiResponse;
        } else {
            throw new TransactionApiException(204, "No transactions to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
