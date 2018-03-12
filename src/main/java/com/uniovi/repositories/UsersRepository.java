/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.UserInterface;
//import com.uniovi.entities.impl.User;

/**
 * Instance of UsersRepository.java
 * 
 * @author 
 * @version 
 */
public interface UsersRepository extends CrudRepository<UserInterface, Long> {
	
	/**
	 * Finds a given user by its email.
	 * 
	 * @param email of the user you want to find.
	 * @return the user if found. Null otherwise.
	 */
	UserInterface findByEmail(String email);

}
