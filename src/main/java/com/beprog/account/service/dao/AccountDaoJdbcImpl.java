package com.beprog.account.service.dao;

import com.beprog.account.service.model.Account;
import com.beprog.account.service.utils.DbUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.QueryHint;
import jakarta.persistence.Table;
import jakarta.persistence.Tuple;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AccountDaoJdbcImpl implements AccountDao {

    @Override
    public void createAccountTable() {
        DbUtil.createSessionFactory();
    }

    @Override
    public void dropAccountTable() {
        DbUtil.closeSessionFactory();
    }

    @Override
    public UUID saveAccount(Account account) {
        Session session = DbUtil.getMyConnection();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(account);
            System.out.printf("Account с именем %s добавлен в базу данных\n", account.getName());
            transaction.commit();
            return account.getId();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        Session session = DbUtil.getMyConnection();
        try {
            Transaction transaction = session.beginTransaction();
            session.merge(account);
            System.out.printf("Обновлён аккаунт с id %s\n", account.getId());
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Account findAccountById(UUID id) {
        try (Session session = DbUtil.getMyConnection()) {
            return session.get(Account.class, id);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        try (Session session = DbUtil.getMyConnection()) {
            return session.createQuery("FROM Account", Account.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteAccountById(UUID id) {
        Session session = DbUtil.getMyConnection();
        try {
            Transaction transaction = session.beginTransaction();
            Account account = session.get(Account.class, id);
            session.remove(account);
            System.out.printf("deleted account with id %s\n", id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}
