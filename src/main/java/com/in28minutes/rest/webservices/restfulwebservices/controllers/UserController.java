package com.in28minutes.rest.webservices.restfulwebservices.controllers;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        // HATEOAS
        // "all-users", SERVER_PATH + "/users"
        EntityModel<User> model = new EntityModel<User>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null)
            throw new UserNotFoundException("id-" + id);
    }

}
