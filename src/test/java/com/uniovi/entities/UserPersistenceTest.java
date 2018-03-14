/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.SocialAppApplication;
import com.uniovi.categories.IntegrationTest;
import com.uniovi.services.UsersService;

/**
 * Instance of UserPersistenceTest.java
 * 
 * @author
 * @version
 */
@SpringBootTest(classes = { SocialAppApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class UserPersistenceTest {

    @Autowired
    UsersService usersService;

    // @Autowired
    // FriendRequestService requestService;

    private User u1, u2, u3;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	u1 = new User();
	u1.setName("Pepe");
	u1.setEmail("pepe@email.com");
	u1.setPassword("123456");
	u1.setPasswordConfirm("123456");

	u2 = new User();
	u2.setName("María");
	u2.setEmail("maria@email.com");
	u2.setPassword("123456");
	u2.setPasswordConfirm("123456");

	u3 = new User();
	u3.setName("Laura");
	u3.setEmail("laura@email.com");
	u3.setPassword("123456");
	u3.setPasswordConfirm("123456");

	u1.getFriends().add(u2);

	//u2.getRequests().add(u3);

	usersService.saveUser(u1);
	usersService.saveUser(u2);
	usersService.saveUser(u3);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
	for(User u : usersService.getUsers())
	    usersService.removeUser(u);
    }
    
    @Test
    public void addFriendTest() {
	// Get the user Pepe
	User result = usersService.getUserByEmail("pepe@email.com");
	
	// Ads Maria as a friend of Pepe 
	result.getFriends().add(u3);
	
	// Saving paco
	usersService.saveUser(result);
	
	// Geting paco from db
	result = usersService.getUserByEmail("pepe@email.com");
	
	// Checking maria is in friends of paco
	assertEquals(2, result.getFriends().size());
    }

    @Test
    public void getUserNameTest() {
	User result = usersService.getUserByEmail("pepe@email.com");
	assertEquals("Pepe", result.getName());
    }

    @Test
    public void getUserPasswordTest() {
	User result = usersService.getUserByEmail("pepe@email.com");
	assertEquals("123456", result.getPassword());
    }

    @Test
    public void getFriendsTest() {
	User result = usersService.getUserByEmail("pepe@email.com");
	assertEquals("María", result.getFriends().iterator().next().getName());
    }

    @Test
    public void getFriendRequestsTest() {
	User maria = usersService.getUserByEmail("maria@email.com");
	assertEquals(0, maria.getRequests().size());
	maria.getRequests().add(usersService.getUserByEmail("laura@email.com"));
	usersService.saveUser(maria);
	assertEquals(1, maria.getRequests().size());
	maria = usersService.getUserByEmail("maria@email.com");
	assertEquals(1, maria.getRequests().size());
    }

    @Test
    public void acceptFriendRequestsTest() {

	// First we load Maria in the object graph. Who has a friend request from Laura.
	User maria = usersService.getUserByEmail("maria@email.com");
	User laura = usersService.getUserByEmail("laura@email.com");
	
	// Adding the request from laura
	maria.getRequests().add(laura);
	
	// We check that the friend request exists.
	assertEquals(1, maria.getRequests().size());

	// We print the frind requests. Just to debug.
	for (User u : maria.getRequests())
	    System.out.println("Request from user: " + u.getEmail());

	// We check that Maria has no friends.
	assertEquals(0, maria.getFriends().size());
	
	System.out.println(u3.getEmail());

	// We accept the request from Laura
	maria.acceptRequestFrom(laura);

	// Check that the request was successfully accepted.
	assertEquals(1, maria.getFriends().size());

	// We save the status of Maria.
	usersService.saveUser(maria);
	//usersService.saveUser(laura);

	// Check again that the request was successfully accepted and no changes in
	// local graph after persistence operation.
	assertEquals(1, maria.getFriends().size());

	// We update the Maria and laura graph from the service.
	maria = usersService.getUserByEmail("maria@email.com");
	laura = usersService.getUserByEmail("laura@email.com");

	// Check that now Maria and laura has both 1 friend.
	assertEquals(1, maria.getFriends().size());
	assertEquals(1, laura.getFriends().size());
    }

    @Test
    public void modifyUserNameTest() {
	User result = usersService.getUserByEmail("maria@email.com");
	result.setName("Eustaquia");
	usersService.saveUser(result);
	result = usersService.getUserByEmail("maria@email.com");
	assertEquals("Eustaquia", result.getName());
    }

    @Test
    public void modifyEmailTest() {
	User result = usersService.getUserByEmail("maria@email.com");
	result.setEmail("eustaquia@mail.com");
	usersService.saveUser(result);
	result = usersService.getUserByEmail("eustaquia@mail.com");
	assertEquals("eustaquia@mail.com", result.getEmail());
    }

}
