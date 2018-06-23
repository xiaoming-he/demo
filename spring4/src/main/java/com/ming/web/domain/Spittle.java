package com.ming.web.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Spittle {

    private  Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 5, max = 16)
    private String username;

    @NotNull
    @Size(min = 5, max = 25)
    private String password;

    private  String message;

    private  Date time;

    private Double latitude;

    private Double longitude;

    public Spittle() {

    }

    public Spittle(Long id, String message, Date time) {
        this(id, message, time, null, null);
    }

    public Spittle(Long id, String message, Date time, Double latitude, Double longitude) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Spittle(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spittle spittle = (Spittle) o;
        return Objects.equals(id, spittle.id) &&
                Objects.equals(firstName, spittle.firstName) &&
                Objects.equals(lastName, spittle.lastName) &&
                Objects.equals(password, spittle.password) &&
                Objects.equals(message, spittle.message) &&
                Objects.equals(time, spittle.time) &&
                Objects.equals(latitude, spittle.latitude) &&
                Objects.equals(longitude, spittle.longitude);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, password, message, time, latitude, longitude);
    }
}
