package com.solvd.service;

import com.solvd.model.User;
import com.solvd.utils.ConfigReader;

public class UserService {

    public User createDefaultUser() {
        return User.builder()
                .username(ConfigReader.getProperty("username"))
                .password(ConfigReader.getProperty("password"))
                .name("Test User")
                .country("Test Country")
                .city("Test City")
                .creditCardNumber("1234567890123456")
                .month("12")
                .year("2024")
                .build();
    }
}
