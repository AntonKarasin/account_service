package com.beprog.account.service.dao;

import com.beprog.account.service.model.Account;

import java.util.List;
import java.util.UUID;

public class AccountDaoJdbcImpl implements AccountDao {

    @Override
    public void createAccountTable() {

    }

    @Override
    public void dropAccountTable() {

    }

    @Override
    public UUID saveAccount(Account account) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public Account findAccountById(UUID id) {
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        return null;
    }

    @Override
    public void deleteAccountById(UUID id) {

    }
}
