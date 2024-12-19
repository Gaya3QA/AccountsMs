package com.BankingApp.AccountsMs.Serices;

import com.BankingApp.AccountsMs.Models.AccountsModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountsInterface {

    AccountsModel createAccount(Long userId, AccountsModel account);

    AccountsModel updateAccount(AccountsModel account);

    Optional<AccountsModel> getAccountById(Long accountId);

    List<AccountsModel> getAllAccounts();

    List<AccountsModel> getAccountsByUserId(Long userId);

    List<AccountsModel> getAccountsByUserIdAndAccountType(Long userId, String accountType);

    void deleteAccount(Long accountId);

    BigDecimal getBalanceByUserId(Long userId);

    BigDecimal getBalanceByAccountId(Long accountId);
}
