/*
 * This source file is part of the NotaneitorTests open source project.
 *
 * Copyright (c) 2018 willy and the NotaneitorTests project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

/**
 * Instance of NotaneitorTests.java
 * 
 * @author
 * @version
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SocialAppTests {

    static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    static WebDriver driver = getDriver(PathFirefox);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox) {
	// Firefox (Versión 46.0) sin geckodriver para Selenium 2.x.
	System.setProperty("webdriver.firefox.bin", PathFirefox);
	WebDriver driver = new FirefoxDriver();
	return driver;
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	driver.navigate().to(URL);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
	driver.manage().deleteAllCookies();
    }

    @BeforeClass
    public static void begin() {
    }

    @AfterClass
    public static void end() {
	driver.quit();
    }

    /**
     * Registro de usuarios con datos válidos.
     */
    @Test
    public void PR01_1() {
	// Vamos a la página de registro (signup)
	PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

	// Rellenamos el formulario con datos válidos.
	PO_RegisterView.fillForm(driver, "random@mail.com", "RandomName", "123456", "123456");

	// Comprobamos que se ha registrado el usuario.
	PO_View.checkElement(driver, "text", "Id");
    }

    /**
     * Registro de usuarios con datos inválidos (repetición de contraseña inválida).
     */
    @Test
    public void PR01_2() {
	// Vamos a la página de registro (signup)
	PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

	// Rellenamos el formulario con datos NO válidos.
	PO_RegisterView.fillForm(driver, "random2@mail.com", "RandomName", "123456", "654321");

	// Comprobamos que se ha generado el aviso necesario.
	PO_View.checkElement(driver, "text", "Las contrasenas no coinciden");
    }

    /**
     * Inicio de sesión con datos válidos.
     */
    @Test
    public void PR02_1() {
	// Vamos a la página de autenticación (login)
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

	// Rellenamos el formulario de autenticación con datos válidos.
	PO_LoginView.fillForm(driver, "p1@hotmail.com", "123123");

	// Comprobamos que el login fue correcto.
	PO_View.checkElement(driver, "text", "Id");
    }

    /**
     * Inicio de sesión con datos inválidos (usuario no existe en la aplicación).
     */
    @Test
    public void PR02_2() {
	// Vamos a la página de autenticación (login)
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

	// Rellenamos el formulario de autenticación con datos NO válidos.
	PO_LoginView.fillForm(driver, "clara@oswin.oswald", "tardis");

	// Comprobamos que el login no fue correcto.
	PO_View.checkElement(driver, "text", "Identifícate");
    }

    /**
     * Acceso al listado de usuarios desde el ususario en sesión.
     */
    @Test
    public void PR03_1() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p1@hotmail.com", "123123");

	// Clicamos en listado de todos los usuarios.
	PO_HomeView.clickOption(driver, "user/list", "class", "id");

	// Comprobamos que estamos viendo el listado de usuarios.
	PO_View.checkElement(driver, "id", "tableUsers");
    }

    /**
     * Intento de acceso con URL desde un usuario no identificado al listado de
     * usuarios de un usuario en sesión.
     */
    @Test
    public void PR03_2() {

	// Intentamos acceder a la página de listado sin estar autenticados.
	driver.navigate().to("http://localhost:8090/user/list");

	// Comprobamos que nos redirige a la página de logeo.
	PO_View.checkElement(driver, "text", "Identifícate");
    }

    /**
     * Realizar una búsqueda válida en el listado de usuarios desde un usuario en
     * sesion.
     */
    @Test
    public void PR04_1() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p1@hotmail.com", "123123");

	// Clicamos en listado de todos los usuarios.
	PO_HomeView.clickOption(driver, "user/list", "class", "id");

	// En el campo de búsqueda introducimos el criterio a buscar
	WebElement searchField = driver.findElement(By.name("searchText"));
	searchField.click();
	searchField.clear();
	searchField.sendKeys("p6@hotmail.com");

	// Clicamos el botón de enviar query
	By boton = By.className("btn");
	driver.findElement(boton).click();
    }

    /**
     * Intento de acceso con url a la busqueda de usuarios desde un usuario no
     * identificado.
     */
    @Test
    public void PR04_2() {
	// Intentamos acceder a la página de búsqueda sin estar autenticados.
	driver.navigate().to("http://localhost:8090/user/list?searchText=p6%40hotmail.com");

	// Comprobamos que nos redirige a la página de logeo.
	PO_View.checkElement(driver, "text", "Identifícate");
    }

    /**
     * Enviar una petición de amistad a un usario de forma valida.
     */
    @Test
    public void PR05_1() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p1@hotmail.com", "123123");

	// Clicamos en listado de todos los usuarios.
	PO_HomeView.clickOption(driver, "user/list", "class", "id");

	// Vamos al usuario con email p6@hotmail.com y le enviamos una petición de
	// amistad.
	List<WebElement> elementos = PO_View.checkElement(driver, "free",
		"//*[@id=\"addRequest4\"]");
	elementos.get(0).click();
    }

    /**
     * Enviar una petición de amistad a un usuario al que le habiamos enviado la
     * invitación previamente. No debería dejarnos enviar la invitación.
     */
    @Test
    public void PR05_2() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p1@hotmail.com", "123123");

	// Clicamos en listado de todos los usuarios.
	PO_HomeView.clickOption(driver, "user/list", "class", "id");

	// Vamos al usuario con email p6@hotmail.com y le enviamos una petición de
	// amistad. A este usuario le acabamos de mandar una petición.
	List<WebElement> elementos = PO_View.checkElement(driver, "free",
		"//*[@id=\"tableUsers\"]/tbody/tr[4]/td[3]/div/div/div/span");
	elementos.get(0).click();

	// Esperar error.
    }

    /**
     * Listar las invitaciones recividas por un usuario, realizar la comprobación
     * con una lista que al menos tenga un usaurio.
     */
    @Test
    public void PR06_1() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p1@hotmail.com", "123123");

	// Clicamos en listado de todos sus peticiones.
	PO_HomeView.clickOption(driver, "user/peticiones", "class", "id");

	// Checkear que existen usuarios en la lista
	PO_View.checkElement(driver, "free", "//*[@id=\"tablePeticiones\"]/tbody/tr/td[2]");
    }

    /**
     * Acceptar una invitación recivida.
     */
    @Test
    public void PR07_1() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p4@hotmail.com", "123123");

	// Clicamos en listado de todos sus peticiones.
	PO_HomeView.clickOption(driver, "user/peticiones", "class", "id");

	// Vamos al usuario con email p6@hotmail.com y le enviamos una petición de
	// amistad. A este usuario le acabamos de mandar una petición.
	List<WebElement> elementos = PO_View.checkElement(driver, "free",
		"//td[contains(text(), 'p1@hotmail.com')]/following-sibling::*/a[contains(@href, 'user/acceptFriendRequest')]");
	elementos.get(0).click();
    }

    /**
     * Listar los amigos de un usario. Realizar la comprobación con al menos un
     * amigo.
     */
    @Test
    public void PR08_1() {
	// Accedemos como un usuario en sesión a la aplicación
	PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "p4@hotmail.com", "123123");

	// Clicamos en listado de todos sus peticiones.
	PO_HomeView.clickOption(driver, "user/amigos", "class", "id");

	// Checkear que existen usuarios en la lista
	PO_View.checkElement(driver, "free", "//*[@id=\"tableAmigos\"]/tbody/tr/td[2]");
    }
}
