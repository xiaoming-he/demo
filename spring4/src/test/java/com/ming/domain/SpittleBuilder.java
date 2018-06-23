package com.ming.domain;

import com.ming.web.domain.Spittle;

public class SpittleBuilder {

    public static final String DEFAULT_FIRSTNAME = "he";
    public static final String DEFAULT_LASTNAME = "ming";
    public static final String DEFAULT_USERNAME = "heming";
    public static final String DEFAULT_PASSWORD = "12345";
    public static final Long DEFAULT_ID = 20L;

    private String firstName = DEFAULT_FIRSTNAME;
    private String lastName = DEFAULT_LASTNAME;
    private String userName = DEFAULT_USERNAME;
    private String password = DEFAULT_PASSWORD;

    public static SpittleBuilder aSpittle() {
        return new SpittleBuilder();
    }

    public SpittleBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SpittleBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public SpittleBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public SpittleBuilder password(String password) {
        this.password = password;
        return this;
    }

    public Spittle build() {
        Spittle spittle = new Spittle();
        spittle.setFirstName(this.firstName);
        spittle.setLastName(this.lastName);
        spittle.setUsername(this.userName);
        spittle.setPassword(this.password);
        return spittle;
    }

}
