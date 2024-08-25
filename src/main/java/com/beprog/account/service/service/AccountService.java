package com.beprog.account.service.service;

import com.beprog.account.service.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    void createAccountTable();

    void dropAccountTable();

    UUID saveAccount(Account account);

    void updateAccount(Account account);

    Account findAccountById(UUID id);

    List<Account> findAllAccounts();

    void deleteAccountById(UUID id);
}
