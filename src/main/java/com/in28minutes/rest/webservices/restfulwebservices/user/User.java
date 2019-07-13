package com.in28minutes.rest.webservices.restfulwebservices.user;

import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {


    private Integer id;

    @Size(min=2, message = "Name should have at least two characters")
    private String name;

    @Past
    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
