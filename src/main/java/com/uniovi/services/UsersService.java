/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * Instance of UsersService.java
 * 
 * @author
 * @version
 */
@Service
public class UsersService {

    @Autowired
    UsersRepository repository;

    public Iterable<User> getUsers() {
	return repository.findAll();
    }

    public User getUser(Long id) {
	return repository.findOne(id);
    }

    public User getUserByEmail(String email) {
	return repository.findByEmail(email);
    }

    public void saveUser(User user) {
	repository.save(user);
    }

    public void removeUser(User user) {
	repository.delete(user);
    }
}
