package com.in28minutes.rest.webservices.restfulwebservices;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDTOService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestEmptyUserDTOService {
    private UserDTOService userDTOService;

    @Before
    public void createUserService() {
        userDTOService = new UserDTOService();

    }

    @Test
    public void testDefaultUserServiceHasThreeUsers() {
        assertEquals(new ArrayList<User>(), userDTOService.findAll());
    }

    @Test
    public void testSavingNewUserAddsUserToList() {
        User newUser = userDTOService.save(new User(4, "David", new Date()));

        assertEquals(4, userDTOService.findAll().size());
    }

    @After
    public void resetUserService() {
        userDTOService = new UserDTOService();
    }
}
