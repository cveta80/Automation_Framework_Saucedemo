package com.selenium.course.tests;
import Pages.*;
import com.selenium.course.tests.base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends TestUtil {

    @Test
    public void addItemToTheCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user", "secret_sauce");
        productPage.addToCartByProductName("bike-light");

        productPage.addToCartByProductName("fleece-jacket");

        WebElement shoppingCart = driver.findElement(By.cssSelector("[class='shopping_cart_link']"));

        Assert.assertTrue(shoppingCart.isDisplayed());
        shoppingCart.click();

        WebElement buttonBikeRemoveFromCart = driver.findElement(By.cssSelector("[name='remove-sauce-labs-bike-light']"));
        Assert.assertTrue(buttonBikeRemoveFromCart.isDisplayed());

        WebElement buttonFleeceRemoveFromCart = driver.findElement(By.cssSelector("[name = 'remove-sauce-labs-fleece-jacket']"));
        Assert.assertTrue(buttonFleeceRemoveFromCart.isDisplayed());

        WebElement buttonCheckout = driver.findElement(By.id("checkout"));
        Assert.assertTrue(buttonCheckout.isDisplayed());
        buttonCheckout.click();

        WebElement buttonContinue = driver.findElement(By.cssSelector("[name='continue']"));
        Assert.assertTrue(buttonContinue.isDisplayed());

        WebElement buttonCancel = driver.findElement(By.id("cancel"));
        Assert.assertTrue(buttonCancel.isDisplayed());



    }
}