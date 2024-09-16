package com.beprog.account.service.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class DbUtil {
    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    // здесь нужно реализовать логику соединения с БД
    public static Session getMyConnection() {
        return sessionFactory.openSession();
    }

    public static void createSessionFactory() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();;
    }
    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
