package com.innowise.accounting.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_SOURCE = "application.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_SOURCE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
