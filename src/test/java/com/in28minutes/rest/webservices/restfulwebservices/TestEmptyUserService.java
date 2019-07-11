package com.in28minutes.rest.webservices.restfulwebservices;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class TestEmptyUserService {
    private UserService userService;

    @Before
    public void createUserService() {
        userService = new UserService();

    }

    @Test
    public void testDefaultUserServiceHasThreeUsers() {
        assertEquals(new ArrayList<User>(), userService.findAll());
    }

    @Test
    public void testSavingNewUserAddsUserToList() {
        User newUser = userService.save(new User(4, "David", new Date()));

        assertEquals(4, userService.findAll().size());
    }

    @After
    public void resetUserService() {
        userService = new UserService();
    }
}
