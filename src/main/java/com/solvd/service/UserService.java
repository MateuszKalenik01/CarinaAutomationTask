package com.solvd.service;

import com.solvd.model.User;
import com.zebrunner.carina.utils.R;

public class UserService {

    public User createDefaultUser() {
        return User.builder()
                .username(R.TESTDATA.get("user.username"))
                .password(R.TESTDATA.get("user.password"))
                .name(R.TESTDATA.get("user.name"))
                .country(R.TESTDATA.get("user.country"))
                .city(R.TESTDATA.get("user.city"))
                .creditCardNumber(R.TESTDATA.get("user.creditCardNumber"))
                .month(R.TESTDATA.get("user.month"))
                .year(R.TESTDATA.get("user.year"))
                .build();
    }
}