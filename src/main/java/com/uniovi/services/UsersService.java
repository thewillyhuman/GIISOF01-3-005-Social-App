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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.UserInterface;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Iterable<UserInterface> getUsers() {
	return repository.findAll();
    }

    public UserInterface getUser(Long id) {
	return repository.findOne(id);
    }

    public UserInterface getUserByEmail(String email) {
	return repository.findByEmail(email);
    }

    public void addUser(UserInterface user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	repository.save(user);
    }

    public void removeUser(UserInterface user) {
	repository.delete(user);
    }
}
