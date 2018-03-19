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

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.utils.SeleniumUtils;

/**
 * Instance of PO_HomeView.java
 * 
 * @author
 * @version
 */
public class PO_HomeView extends PO_NavView {

    static public void checkWelcome(WebDriver driver, int language) {
	// Esperamos a que se cargue el saludo de bienvenida en Español
	SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language),
		getTimeout());
    }

    static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1, int locale2 ) { 
	//Esperamos a que se cargue el saludo de bienvenida en Español 
	PO_HomeView.checkWelcome(driver, locale1); 
	//Cambiamos a segundo idioma 
	PO_HomeView.changeIdiom(driver, textIdiom2); 
	//COmprobamos que el texto de bienvenida haya cambiado a segundo idioma 
	PO_HomeView.checkWelcome(driver, locale2);
	//Volvemos a Español. 
	PO_HomeView.changeIdiom(driver, textIdiom1); 
	//Esperamos a que se cargue el saludo de bienvenida en Español 
	PO_HomeView.checkWelcome(driver, locale1);
    }
}
