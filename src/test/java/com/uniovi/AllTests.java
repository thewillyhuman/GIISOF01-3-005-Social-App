/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.uniovi.entities.UserPersistenceTest;
import com.uniovi.entities.UserTest;

/**
 * Instance of AllTests.java
 * 
 * @author 
 * @version 
 */
@RunWith(Suite.class)
@SuiteClasses({UserTest.class, UserPersistenceTest.class})
public class AllTests {

}
