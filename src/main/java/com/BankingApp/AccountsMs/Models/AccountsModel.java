package com.BankingApp.AccountsMs.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "user_id", nullable = false)
    private Long userId;  // Store user_id as a foreign key

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "balance", columnDefinition = "DECIMAL(15,2) default 0.0")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "currency", columnDefinition = "VARCHAR(10) default 'USD'")
    private String currency = "USD";

    @Column(name = "created_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

}
