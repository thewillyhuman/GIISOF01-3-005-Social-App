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

import com.uniovi.entities.User;

/**
 * Instance of UsersRepository.java
 * 
 * @author 
 * @version 
 */
public interface UsersRepository extends CrudRepository<User, Long> {
	
	/**
	 * Finds a given user by its email.
	 * 
	 * @param email of the user you want to find.
	 * @return the user if found. Null otherwise.
	 */
	User findByEmail(String email);

}
