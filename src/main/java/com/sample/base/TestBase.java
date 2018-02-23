package com.sample.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import com.sample.utils.TestUtil;
import com.sun.istack.internal.logging.Logger;

public class TestBase {
	
	public static WebDriver dr;
	public static Properties prop;

	public TestBase() {	
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/sample/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void init() {
		String browserName = prop.getProperty("browser");

		switch (browserName) 
		{
		case "chrome":		
			System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
			dr = new ChromeDriver();
			break;
		case "firefox":	
			System.setProperty("webdriver.gecko.driver", "WebDriver/geckodriver.exe");
			dr = new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", "WebDriver/IEDriverServer.exe");
			dr = new InternetExplorerDriver();
			break;
		default:
			System.out.println("INVALID BROWSER NAME");
		}
		

		
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		dr.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		dr.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		dr.get(prop.getProperty("url"));
		
	}
}
