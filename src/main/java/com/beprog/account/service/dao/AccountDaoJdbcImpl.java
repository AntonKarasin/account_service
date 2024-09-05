package com.beprog.account.service.dao;

import com.beprog.account.service.model.Account;
import com.beprog.account.service.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountDaoJdbcImpl implements AccountDao {

    @Override
    public void createAccountTable() {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS accounts (id UUID PRIMARY KEY, name varchar(80), lastName varchar(80))");
            System.out.println("table created");
            st.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropAccountTable() {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            st.execute("DROP TABLE IF EXISTS accounts");
            System.out.println("table erased");
            st.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public UUID saveAccount(Account account) {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            st.execute(String.format("INSERT INTO accounts VALUES ('%s', '%s', '%s')", account.getId(), account.getName(), account.getLastName()));
            System.out.printf("Account с именем %s добавлен в базу данных\n", account.getName());
            st.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            st.execute(String.format("UPDATE accounts SET name = '%s', lastName = '%s' WHERE id = '%s'", account.getName(), account.getLastName(), account.getId()));
            System.out.printf("Обновлён аккаунт с id %s\n", account.getId());
            st.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Account findAccountById(UUID id) {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * EXCEPT id FROM accounts WHERE id = '%s'", id));
            st.close();
            con.close();
            String name = rs.getString("name");
            String lastName = rs.getString("lastName");
            Account result = new Account(id, name, lastName);
            rs.close();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM accounts");
            List<Account> result = new ArrayList<>();
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Account acc = new Account(id, name, lastName);
                result.add(acc);
            }
            rs.close();
            st.close();
            con.close();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAccountById(UUID id) {
        try {
            Connection con = DbUtil.getMyConnection();
            Statement st = con.createStatement();
            st.execute(String.format("DELETE FROM accounts WHERE id = '%s'", id));
            System.out.printf("deleted account with id %s\n", id);
            st.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
}
