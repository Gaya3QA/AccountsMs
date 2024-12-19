package com.BankingApp.AccountsMs.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//The UserModel will contain only the necessary fields from the User entity that
// the Account Microservice needs. This way, you don’t need to load the entire User entity
// with all its properties.
//For example, we might only need the userId, username, and email for the account creation.
public class UserModel{

    private Long userId;
    private String username;
    private String email;
}
