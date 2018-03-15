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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
	 * @param email
	 *            of the user you want to find.
	 * @return the user if found. Null otherwise.
	 */
	User findByEmail(String email);

	/**
	 * Find all users in pageable format
	 * 
	 * @param pageable
	 * @return
	 */
	Page<User> findAll(Pageable pageable);

	// WILLY COMEEENTALO
	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.email) LIKE LOWER(?1))")
	Page<User> searchUsersByEmailAndName(String seachtext, Pageable pageable);

}
