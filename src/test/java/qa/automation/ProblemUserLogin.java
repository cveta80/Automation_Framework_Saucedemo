package com.selenium.course.tests;

import com.selenium.course.tests.base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProblemUserLogin extends TestUtil {
    @Test
    public void ProblemUserLogin() throws InterruptedException {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("problem_user");


        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        //validate that wrong product images are seen


        WebElement productsImage = driver.findElement(By.cssSelector("[src ='/static/media/sl-404.168b1cce.jpg']"));


        Assert.assertTrue(productsImage.isDisplayed());
    }



}



