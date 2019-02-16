package com.squirrel.courses.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Class for encrypting password
 *
 * @author  Bogdan Popovich
 */
public class EncryptPassword {

    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
