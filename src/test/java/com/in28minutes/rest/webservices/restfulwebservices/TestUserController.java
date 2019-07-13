package com.in28minutes.rest.webservices.restfulwebservices;

import com.in28minutes.rest.webservices.restfulwebservices.controllers.UserController;
import com.in28minutes.rest.webservices.restfulwebservices.services.UserService;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestUserController {

    @Test
    public void testRetrieveAllUsers() {

        UserService userServiceMock = new Mockito().mock(UserService.class);

        List<User> users = new ArrayList<>();
        users.add(new User(1, "Adam", new Date()));

        Mockito.when(userServiceMock.findAll()).thenReturn(users);

        UserController userController = new UserController(userServiceMock);

        List<User> result = userController.retrieveAllUsers();

        assertEquals(users, result);

    }
}
