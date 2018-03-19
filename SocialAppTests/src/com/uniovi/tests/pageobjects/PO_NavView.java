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

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.*;

import com.uniovi.tests.utils.SeleniumUtils;

/**
 * Instance of PO_NavView.java
 * 
 * @author
 * @version
 */
public class PO_NavView extends PO_View {

    public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) { 
	
	//CLickamos en la opción de registro y esperamos a que se cargue el enlace de Registro. 
	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout()); 
	
	//Tiene que haber un sólo elemento. 
	assertTrue(elementos.size()==1); 
	
	//Ahora lo clickamos 
	elementos.get(0).click(); 
	
	//Esperamos a que sea visible un elemento concreto 
	elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
	
	//Tiene que haber un sólo elemento. 
	assertTrue(elementos.size()==1);
    }
    
    public static void changeIdiom(WebDriver driver, String textLanguage) { 
	//clickamos la opción Idioma.
	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
	elementos.get(0).click();
	//Esperamos a que aparezca el menú de opciones.
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
	//SeleniumUtils.esperarSegundos(driver, 2);
	//CLickamos la opción Inglés partiendo de la opción Español
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
	elementos.get(0).click();
    }
    
}
