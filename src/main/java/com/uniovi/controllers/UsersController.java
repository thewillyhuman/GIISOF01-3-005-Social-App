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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
	return "login";
    }

}
