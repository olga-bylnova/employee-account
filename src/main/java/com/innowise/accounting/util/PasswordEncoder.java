package com.innowise.accounting.util;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordEncoder {
    private static final PasswordEncoder INSTANCE = new PasswordEncoder();
    private static final int ITERATION_COUNT = 2;
    private static final int HASH_LENGTH = 64;
    private static final int SALT_LENGTH = 32;
    private static final int PARALLELISM = 1;
    private static final int MEMORY = 15 * 1024;
    private final Argon2PasswordEncoder encoder;

    private PasswordEncoder() {
        encoder = new Argon2PasswordEncoder(SALT_LENGTH, HASH_LENGTH, PARALLELISM, MEMORY, ITERATION_COUNT);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    public String encode(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String hash, String password) {
        return encoder.matches(password, hash);
    }
}
