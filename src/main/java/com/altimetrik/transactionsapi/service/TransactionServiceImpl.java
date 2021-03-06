package com.altimetrik.transactionsapi.service;

import com.altimetrik.transactionsapi.dto.ApiResponse;
import com.altimetrik.transactionsapi.exception.TransactionApiException;
import com.altimetrik.transactionsapi.modal.Statistics;
import com.altimetrik.transactionsapi.modal.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    @Override
    public ApiResponse saveTransaction(Transaction transaction) {
        ApiResponse apiResponse = new ApiResponse();
        Duration duration = Duration.between(transaction.getTime(), LocalDateTime.now());
        if (duration.getSeconds() >= 60 || transaction.getTime().isAfter(LocalDateTime.now())) {
            log.error("The transaction time is out of one minute for: {}", transaction);
            throw new TransactionApiException(204, "The transaction has not been updated successfully", HttpStatus.NO_CONTENT);
        }
        transactions.add(transaction);
        apiResponse.setStatus("Success");
        apiResponse.setMessage("The transaction has been updated successfully");
        log.info("The transaction is inserted successfully, current no of transactions: {}", transactions.size());
        return apiResponse;
    }

    @Override
    public Statistics getTransactionsInformation() {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        long count = 0;
        double sum = 0;
        double avg = 0;
        for (Transaction transaction : transactions) {
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
        log.info("Fetching the transactions information, current no of transactions: {}", transactions.size());
        return new Statistics(min, max, avg, sum, count);
    }

    @Override
    public ApiResponse deleteTransactions() {
        transactions.clear();
        ApiResponse apiResponse = new ApiResponse();
        if (CollectionUtils.isEmpty(transactions)) {
            log.info("The transactions were deleted successfully,current transactions: {}", transactions.size());
        }
        return apiResponse;
    }
}
