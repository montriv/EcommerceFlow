package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTest extends BaseTest {

    @Test
    public void fullFlowTest() {

        test = extent.createTest("SauceDemo Full Flow Test");

        // Step 1: Open site
        driver.get("https://www.saucedemo.com/");
        test.log(Status.INFO, "Opened SauceDemo website");
        
        String screenshotPath = takeScreenshot("LoginPage");
        test.addScreenCaptureFromPath(screenshotPath);

        // Step 2: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        test.log(Status.PASS, "Logged in successfully");
        
        String screenshotPath1 = takeScreenshot("HomeCartPage");
        test.addScreenCaptureFromPath(screenshotPath1);

        // Step 3: Navigate to About page
        HomePage homePage = new HomePage(driver);
        homePage.goToAboutPage();
        test.log(Status.PASS, "Navigated to About page");
        
        String screenshotPath2 = takeScreenshot("AboutPage");
        test.addScreenCaptureFromPath(screenshotPath2);

        // Step 4: Go back to Products page
        driver.get("https://www.saucedemo.com/inventory.html");
        test.log(Status.INFO, "Navigated back to Products page");
        
        String screenshotPath3 = takeScreenshot("InventoryPage");
        test.addScreenCaptureFromPath(screenshotPath3);

        // Step 5: Add product to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        test.log(Status.PASS, "Added Sauce Labs Backpack to cart");

        // Step 6: Open cart
        productPage.openCart();
        test.log(Status.PASS, "Opened cart page");

        // Step 7: Take screenshot
        String screenshotPath4 = takeScreenshot("FinalCartPage");
        test.addScreenCaptureFromPath(screenshotPath4);
        
        // Step 8: Logout
        productPage.logOut();
        test.log(Status.PASS, "Log out");
    }
}
