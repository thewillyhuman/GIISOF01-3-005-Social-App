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
import org.junit.Ignore;
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
    
    //@Autowired
    //FriendRequestService requestService;
    
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
	
	u2.getRequests().add(u3);
	
	usersService.saveUser(u1);
	usersService.saveUser(u2);
	usersService.saveUser(u3);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
	usersService.removeUser(u1);
	usersService.removeUser(u2);
	usersService.removeUser(u3);
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
	User result = usersService.getUserByEmail("maria@email.com");
	assertEquals(1, result.getRequests().size());
    }
    
    @Test @Ignore
    public void acceptFriendRequestsTest() {
	User result = usersService.getUserByEmail("maria@email.com");
	//result.getFriendRequests().forEach((request)->request.accept(result));
	assertEquals(1, result.getRequests().size());
	result.acceptRequestFrom(u3);
	
	usersService.saveUser(result);
	
	result = usersService.getUserByEmail("maria@email.com");
	assertEquals(1, result.getFriends().size());
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
