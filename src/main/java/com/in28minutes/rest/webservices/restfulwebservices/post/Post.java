package com.in28minutes.rest.webservices.restfulwebservices.post;

import lombok.Data;

import java.util.Date;

@Data
public class Post {

    private Integer id;
    private String title;
    private String message;
    private Date datePosted;

    public Post(Integer id, String title, String message, Date datePosted) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.datePosted = datePosted;
    }
}
