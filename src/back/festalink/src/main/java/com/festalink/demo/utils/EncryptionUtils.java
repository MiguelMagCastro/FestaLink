package com.festalink.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean matchPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}

