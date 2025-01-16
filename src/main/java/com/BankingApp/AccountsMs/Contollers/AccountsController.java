package com.BankingApp.AccountsMs.Contollers;

import com.BankingApp.AccountsMs.Models.AccountsModel;
import com.BankingApp.AccountsMs.Serices.AccountsInterface;
import jakarta.websocket.server.PathParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountsController {
    private static final Logger logger = LogManager.getLogger(AccountsController.class);

    @Autowired
    private AccountsInterface accountsInterface;

    @PostMapping("/{id}")
    public AccountsModel createAccount(@PathVariable("id") Long userId, @RequestBody AccountsModel account) {
        logger.info("Received request to create account: {}", account);
        return accountsInterface.createAccount(userId,account);
    }

    @PutMapping("/update")
    public AccountsModel updateAccount(@RequestBody AccountsModel account) {
        logger.info("Received request to update account: {}", account);
        return accountsInterface.updateAccount(account);
    }


    @GetMapping
    public List<AccountsModel> getAllAccounts() {
        logger.info("Fetching all accounts");
        return accountsInterface.getAllAccounts();
    }

    @GetMapping("/{id}/account")
    public List<AccountsModel> getAccountsByUserId(@PathVariable("id") Long userId) {
        logger.info("Fetching accounts for user ID: {}", userId);
        return accountsInterface.getAccountsByUserId(userId);
    }

    @GetMapping("/{acctid}")
    public Optional<AccountsModel> getAccountById(@PathVariable("acctid") Long accountId) {
        logger.info("Fetching account by account ID: {}", accountId);
        return accountsInterface.getAccountById(accountId);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        logger.info("Deleting account with account ID: {}", accountId);
        accountsInterface.deleteAccount(accountId);
    }

    @GetMapping("/balance")
    public BigDecimal getBalance(@RequestParam Long userId, @RequestParam Long accountId) {
        if (userId != null) {
            logger.info("Fetching balance for user ID: {}", userId);
            return accountsInterface.getBalanceByUserId(userId);
        } else if (accountId != null) {
            logger.info("Fetching balance for account ID: {}", accountId);
            return accountsInterface.getBalanceByAccountId(accountId);
        }
      return BigDecimal.ZERO;
    }

    @GetMapping("/balance/{userId}")
    public BigDecimal getBalanceForUser(@PathVariable Long userId) {
        logger.info("Fetching balance for user ID: {}", userId);
        return accountsInterface.getBalanceByUserId(userId);
    }

}
