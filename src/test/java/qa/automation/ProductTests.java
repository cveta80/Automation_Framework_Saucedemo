package com.selenium.course.tests;
import Pages.*;
import com.selenium.course.tests.base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTests extends TestUtil {

    @Test
    public void addItemToTheCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user", "secret_sauce");
        productPage.addToCartByProductName("onesie");

        productPage.addToCartByProductName("backpack");


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productPage.getNumbersInTheCart(),2,
                "Because we have added two item in the cart.");

        softAssert.assertAll();

    }
}