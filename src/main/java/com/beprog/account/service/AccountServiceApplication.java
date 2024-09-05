package com.beprog.account.service;

import com.beprog.account.service.model.Account;
import com.beprog.account.service.dao.AccountDaoJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AccountServiceApplication {

    public static void main(String[] args) {
        AccountDaoJdbcImpl service = new AccountDaoJdbcImpl();
        service.createAccountTable();
        System.out.println(UUID.randomUUID());
        Scanner myObj = new Scanner(System.in);
        String name;
        String lastName;
        for (int i=0; i<4; i++) {
            System.out.print("Имя пользователя: ");
            name = myObj.nextLine();
            System.out.print("Фамилия пользователя: ");
            lastName = myObj.nextLine();
            Account account = new Account(UUID.randomUUID(), name, lastName);
            service.saveAccount(account);
        }
        List<Account> accounts = service.findAllAccounts();
        System.out.println("Пользователи:");
        for (Account account: accounts) {
            System.out.println(account);
        }
        String uuidString;
        for (int i=0; i<2; i++) {
            System.out.println("Выберите id пользователя, данные о котором Вы хотели бы изменить: ");
            uuidString = myObj.nextLine();
            System.out.print("Имя пользователя: ");
            name = myObj.nextLine();
            System.out.print("Фамилия пользователя: ");
            lastName = myObj.nextLine();
            Account account = new Account(UUID.fromString(uuidString), name, lastName);
            service.updateAccount(account);
        }
        accounts = service.findAllAccounts();
        System.out.println("Пользователи:");
        for (Account account: accounts) {
            System.out.println(account);
        }
        System.out.println("Выберите id пользователя, которого Вы хотели бы удалить: ");
        uuidString = myObj.nextLine();
        service.deleteAccountById(UUID.fromString(uuidString));
        accounts = service.findAllAccounts();
        System.out.println("Пользователи:");
        for (Account account: accounts) {
            System.out.println(account);
        }
        service.dropAccountTable();
    }
}
