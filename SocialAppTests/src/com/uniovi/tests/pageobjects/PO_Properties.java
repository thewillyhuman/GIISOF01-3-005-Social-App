package com.uniovi.tests.pageobjects;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class PO_Properties {
    public static int getSPANISH() {
	return SPANISH;
    }

    public static int getENGLISH() {
	return ENGLISH;
    }

    static private String Path;
    static int SPANISH = 0;
    static int ENGLISH = 1;
    public static Locale[] idioms = new Locale[] { new Locale("ES"), new Locale("EN") };
    static Properties p = new Properties();

    public PO_Properties(String Path) // throws FileNotFoundException, IOException
    {
	PO_Properties.Path = Path;
    }

    //
    // locale is de index in idioms array.
    //
    public String getString(String prop, int locale) {
	ResourceBundle bundle = ResourceBundle.getBundle(Path, idioms[locale]);
	// ResourceBundle bundle = ResourceBundle.getBundle(Path + "messages_" +
	// idioms[locale] + ".properties");
	return bundle.getString(prop);
    }

}
