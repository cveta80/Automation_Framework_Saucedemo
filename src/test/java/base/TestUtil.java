package com.selenium.course.tests.base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    private String applicationUrl, browser;
    private int implicitWait;

    @BeforeMethod
    public void setUp(){
        setupBrowserDriver();
        loadInitialPage();
    }

    private void loadInitialPage() {
        driver.get(applicationUrl);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

    private void setupBrowserDriver(){
        try{
            FileInputStream configFile = new
                    FileInputStream("src/test/resources/config.properties");
            Properties config = new Properties();
            config.load(configFile);
            applicationUrl = config.getProperty("url");
            browser = config.getProperty("targetBrowser");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            System.out.println("webhook test");
        }catch (IOException e){
            System.out.println("IOException e");
        }

        switch (browser){
            case "chrome":
                driver = DriverFactory.getChromeDriver();
                break;
            case "firefox":
                driver = DriverFactory.getFireFoxDriver();
                break;

            default:
                throw new IllegalStateException("Unsupported browser type ");
        }
    }
}
