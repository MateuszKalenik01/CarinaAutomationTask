package com.solvd.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username;
    private String password;
    private String name;
    private String country;
    private String city;
    private String creditCardNumber;
    private String month;
    private String year;
}
