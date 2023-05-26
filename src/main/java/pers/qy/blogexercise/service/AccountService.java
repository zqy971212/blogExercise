package pers.qy.blogexercise.service;

import pers.qy.blogexercise.model.entity.Account;

import java.util.List;

public interface AccountService {
    Boolean createAccount(String userName, String email, String password);

    List<Account> getAllAccounts();

    Account getByEmail(String email);

    Account login(String userEmail, String password);
}
