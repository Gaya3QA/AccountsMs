package com.BankingApp.AccountsMs.Serices;

import com.BankingApp.AccountsMs.Models.AccountsModel;
import com.BankingApp.AccountsMs.Repositories.AccountsRepository;

import com.BankingApp.AccountsMs.Models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsImpl implements AccountsInterface{
    private static final Logger logger = LogManager.getLogger(AccountsImpl.class);

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AccountsModel createAccount(Long userId, AccountsModel account) {
        // Get the user details using RestTemplate to communicate with the Users Microservice
        // Fetch user details from the Users microservice using RestTemplate
        String userServiceUrl = "http://host.docker.internal:8080/user/" +userId;
        UserModel user = restTemplate.getForObject(userServiceUrl, UserModel.class);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Set userId in the account
        account.setUserId(user.getUserId());
        logger.info("Create a new account with user id {}" + user.getUserId());
        // Save account
        return accountsRepository.save(account);


    }


    @Override
    public AccountsModel updateAccount(AccountsModel account) {
        logger.info("Update the account");
        return accountsRepository.save(account);
    }

    @Override
    public Optional<AccountsModel> getAccountById(Long accountId) {
        logger.info("Fetching account by account ID: {}", accountId);
        return accountsRepository.findByAccountId(accountId);
    }

    @Override
    public List<AccountsModel> getAllAccounts() {
        logger.info("Fetching all accounts");
        return accountsRepository.findAll();
    }

    @Override
    public List<AccountsModel> getAccountsByUserId(Long userId) {
        logger.info("Fetching accounts for user ID: {}", userId);
        return accountsRepository.findByUserId(userId);
    }



    @Override
    public List<AccountsModel> getAccountsByUserIdAndAccountType(Long userId, String accountType) {
        logger.info("Fetching accounts for user ID: {} and account type: {}", userId, accountType);
        return accountsRepository.findByUserIdAndAccountType(userId, accountType);
    }

    @Override
    public void deleteAccount(Long accountId) {
        logger.info("Deleting account with account ID: {}", accountId);
        accountsRepository.deleteById(accountId);

    }

    @Override
    public BigDecimal getBalanceByUserId(Long userId) {
        logger.info("Fetching balance for user ID: {}", userId);
        return accountsRepository.findByUserId(userId).stream()
                .map(AccountsModel::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public BigDecimal getBalanceByAccountId(Long accountId) {
        logger.info("Fetching balance for account ID: {}", accountId);
        return accountsRepository.findByAccountId(accountId)
                .map(AccountsModel::getBalance)
                .orElse(BigDecimal.ZERO);
    }
}
