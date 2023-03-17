package com.innowise.accounting.util;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;

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

    private static BlockingQueue<Connection> pool;

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
