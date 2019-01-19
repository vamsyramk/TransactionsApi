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

import static org.junit.Assert.assertEquals;

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
        transactionService.deleteTransactions();
        Transaction transaction = new Transaction();
        transaction.setAmount(3000);
        transaction.setTime(LocalDateTime.now());
        for(int i = 0; i < 4; i++) {
            transactionService.saveTransaction(transaction);
        }
    }

    @Test
    public void whenValidTransaction_thenTransactionShouldBeInserted() {
        Transaction transaction = new Transaction();
        transaction.setAmount(3000);
        transaction.setTime(LocalDateTime.now());
        ApiResponse apiResponse = transactionService.saveTransaction(transaction);
        assertEquals("Success", apiResponse.getStatus());
    }

    @Test
    public void whenValidTransaction_thenTransactionShouldBeReturned() {
        Statistics statistics = transactionService.getTransactionsInformation();
        System.out.println(statistics);
        assertEquals( 12000, statistics.getSum(),0.0);
    }

    @Test
    public void whenDeleteTransaction_thenTransactionShouldBeDeleted() {
        assertEquals(12000, transactionService.getTransactionsInformation().getSum(),0.0);
        transactionService.deleteTransactions();
        assertEquals(0.0, transactionService.getTransactionsInformation().getSum(), 0.0);
    }

}
