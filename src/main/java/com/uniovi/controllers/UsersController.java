/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uniovi.services.UsersService;

/**
 * Instance of UsersController.java
 * 
 * @author 
 * @version 
 */
@Controller
public class UsersController {
	
	@Autowired
	UsersService usersService;

}
