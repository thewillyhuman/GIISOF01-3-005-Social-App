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

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.uniovi.categories.UnitTest;

/**
 * Instance of UserTest.java
 * 
 * @author 
 * @version 
 */
@Category(UnitTest.class)
public class UserTest {
    
    private User u1, u2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	u1 = new User("María", "maria@email.com");
	u1.setPassword("123456");
	u2 = new User("Carlos","calos@email.com");
	u2.setPassword("123456");
    }
    
    public void getNameTest() {
	assertEquals("María", u1.getName());
	assertEquals("Carlos", u2.getName());
    }
    
    public void setNameTest() {
	u1.setName("Pepe");
	assertEquals("Pepe", u1.getName());
	
	u2.setName("Laura");
	assertEquals("Laura", u2.getName());
    }
    
    public void getEmailTest() {
	assertEquals("maria@email.com", u1.getEmail());
	assertEquals("calos@email.com", u2.getEmail());
    }
    
    public void setEmailTest() {
	u1.setEmail("pepe@email.com");
	assertEquals("pepe@email.com", u1.getEmail());
	
	u2.setEmail("laura@email.com");
	assertEquals("laura@email.com", u2.getEmail());
    }
    
    public void getPasswordTest() {
	assertEquals("123456", u1.getPassword());
	assertEquals("123456", u2.getPassword());
    }
    
    public void setPasswordTest() {
	u1.setPassword("654321");
	assertEquals("654321", u1.getPassword());
	
	u2.setPassword("654321");
	assertEquals("654321", u2.getPassword());
    }

    @Test
    public void friendsRequestAcceptTest() {
	FriendRequest fr1 = new FriendRequest(u2);
	u1.getFriendRequests().add(new FriendRequest(u2));
	fr1.accept(u1);
	
	assertEquals(1, u1.getFriends().size());
    }

}
