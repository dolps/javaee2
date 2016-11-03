package com.woact.dolplads.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lastName on 24/10/2016.
 */
@Getter
@Setter
public class User {
    private final String firstName;
    private final String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
