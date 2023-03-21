package com.innowise.accounting.util;

import com.innowise.accounting.connection.WrapperConnection;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public final class ConnectionManager {
    private static final String DB_DRIVER_KEY = "db.driver";
    private static final String DB_PASSWORD_KEY = "db.password";
    private static final String DB_USER_NAME_KEY = "db.username";
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_POOL_SIZE_KEY = "db.pool.size";

    private static final String DB_PASSWORD = PropertiesUtil.getProperty(DB_PASSWORD_KEY);
    private static final String DB_USER_NAME = PropertiesUtil.getProperty(DB_USER_NAME_KEY);
    private static final String DB_URL = PropertiesUtil.getProperty(DB_URL_KEY);
    private static final String DB_POOL_SIZE = PropertiesUtil.getProperty(DB_POOL_SIZE_KEY);
    private static final String DB_DRIVER = PropertiesUtil.getProperty(DB_DRIVER_KEY);
    private static final Integer DEFAULT_POOL_SIZE = 10;

    private static BlockingQueue<WrapperConnection> pool;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void initConnectionPool() {
        int poolSize = DB_POOL_SIZE == null ? DEFAULT_POOL_SIZE : Integer.parseInt(DB_POOL_SIZE);
        pool = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            pool.add(open());
        }

    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static WrapperConnection open() {
        try {
            return new WrapperConnection(DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void releaseConnection(WrapperConnection connection) {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
