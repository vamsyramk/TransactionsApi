package com.altimetrik.transactionsapi.service;

import com.altimetrik.transactionsapi.dto.ApiResponse;
import com.altimetrik.transactionsapi.modal.Statistics;
import com.altimetrik.transactionsapi.modal.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class TransactionServiceImplTest {

    @TestConfiguration
    static class TransactionServiceImplTestContextConfiguration {

        @Bean
        public TransactionService employeeService() {
            return new TransactionServiceImpl();
        }
    }

    @Autowired
    private TransactionService transactionService;

    @Before
    public void setUp() {

    }

    @Test
    public void whenValidTransaction_thenTransactionSouldBeInserted() {
        Transaction transaction = new Transaction();
        transaction.setAmount(3000);
        transaction.setTime(LocalDateTime.now());
        ApiResponse apiResponse = transactionService.saveTransaction(transaction);
        assertEquals(apiResponse.getStatus(), "Success");
    }

    @Test
    public void whenValidTransaction_thenTransactionSouldBeReturned() {
        Transaction transaction = new Transaction();
        transaction.setAmount(3000);
        transaction.setTime(LocalDateTime.now());
        for(int i = 0; i < 4; i++) {
            transactionService.saveTransaction(transaction);
        }
        Statistics statistics = transactionService.getTransactionsInformation();
        System.out.println(statistics);
        assertEquals(statistics.getSum(), 12000, 0.0);
    }

}
