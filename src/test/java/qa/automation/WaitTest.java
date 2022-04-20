package com.selenium.course.tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.selenium.course.tests.base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class WaitTest extends TestUtil {

    @DataProvider(name = "userListCsv1")
    public static Object[][] readUsersFromCsv() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/userList.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }

    @Test (dataProvider = "userListCsv1")
    public void WaitTest(String userName, String password) throws InterruptedException {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys(userName);


        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys(password);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        fluentWait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();



        WebElement productsMainLabel = driver.findElement(By.xpath("//span[text()='Products']"));
        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        Assert.assertTrue(productsMainLabel.isDisplayed());
        Assert.assertTrue(shoppingCartLink.isDisplayed());;


    }



}