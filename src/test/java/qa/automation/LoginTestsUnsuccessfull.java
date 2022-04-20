package com.selenium.course.tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.selenium.course.tests.base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTestsUnsuccessfull extends TestUtil {

    @DataProvider(name = "wrongListCsv")
    public static Object[][] readUsersFromCsv() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongList.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }

    @Test (dataProvider = "wrongListCsv")
    public void unsuccessfulLogin(String userName, String password) throws InterruptedException {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys(userName);


        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();


        WebElement wrongUserBtn = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserBtn.isDisplayed());
    }



}