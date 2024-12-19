package com.BankingApp.AccountsMs.Repositories;

import com.BankingApp.AccountsMs.Models.AccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<AccountsModel, Long> {
    List<AccountsModel> findByUserId(Long userId);

    Optional<AccountsModel> findByAccountId(Long accountId);

    List<AccountsModel> findByUserIdAndAccountType(Long userId, String accountType);

}
