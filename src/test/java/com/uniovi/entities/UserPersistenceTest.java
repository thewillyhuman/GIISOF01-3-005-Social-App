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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.SocialAppApplication;
import com.uniovi.services.UsersService;

/**
 * Instance of UserPersistenceTest.java
 * 
 * @author 
 * @version 
 */
@SpringBootTest(classes = { SocialAppApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserPersistenceTest {
    
    @Autowired
    UsersService service;
    
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
	
	service.saveUser(u1);
	service.saveUser(u2);
	service.saveUser(u3);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
	service.removeUser(u1);
	service.removeUser(u2);
	service.removeUser(u3);
    }

    @Test
    public void getUserNameTest() {
	User result = service.getUserByEmail("pepe@email.com");
	assertEquals("Pepe", result.getName());
    }
    
    @Test
    public void getUserPasswordTest() {
	User result = service.getUserByEmail("pepe@email.com");
	assertEquals("123456", result.getPassword());
    }
    
    @Test
    public void getFriendsTest() {
	User result = service.getUserByEmail("pepe@email.com");
	assertEquals("María", result.getFriends().iterator().next().getName());
    }
    
    @Test
    public void modifyUserNameTest() {
	User result = service.getUserByEmail("maria@email.com");
	result.setName("Eustaquia");
	service.saveUser(result);
	result = service.getUserByEmail("maria@email.com");
	assertEquals("Eustaquia", result.getName());
    }
    
    @Test
    public void modifyEmailTest() {
	User result = service.getUserByEmail("maria@email.com");
	result.setEmail("eustaquia@mail.com");
	service.saveUser(result);
	result = service.getUserByEmail("eustaquia@mail.com");
	assertEquals("eustaquia@mail.com", result.getEmail());
    }

}
