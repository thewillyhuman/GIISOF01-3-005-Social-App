/*
 * This source file is part of the NotaneitorTests open source project.
 *
 * Copyright (c) 2018 willy and the NotaneitorTests project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Instance of PO_LoginView.java
 * 
 * @author 
 * @version 
 */
public class PO_LoginView extends PO_NavView {

    /**
     * @param driver
     * @param user
     * @param password
     */
    public static void fillForm(WebDriver driver, String user, String password) {
	// Enter the user name field.
	WebElement userField = driver.findElement(By.name("username"));
	userField.click();
	userField.clear();
	userField.sendKeys(user);
	
	// Enter the password field.
	WebElement passField = driver.findElement(By.name("password"));
	passField.click();
	passField.clear();
	passField.sendKeys(password);
	
	// Clicking the login button.
	By boton = By.className("btn");
	driver.findElement(boton).click();
	
    }

}
