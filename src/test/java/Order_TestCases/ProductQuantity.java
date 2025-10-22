/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Order_TestCases;

import Authentication_TestCases.AllureUtil;

import automationExercise_Pages.CartPage;

import automationExercise_Pages.HomePage;
import automationExercise_Pages.ProductsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author y_all
 */
public class ProductQuantity {

    private HomePage h;

    private ProductsPage p;
    private CartPage ct;

    private WebDriver Browser;


    @BeforeSuite
    public void setUpEnvironment() throws IOException {
        Properties props = new Properties();
        props.setProperty("OS", System.getProperty("os.name"));
        props.setProperty("Java Version", System.getProperty("java.version"));
        props.setProperty("Browser", "Chrome 116");
        props.setProperty("BaseURL", "https://automationexercise.com/");
        props.setProperty("Environment", "QA");

        try (FileOutputStream fos = new FileOutputStream(
                "C:\\Users\\y_all\\OneDrive - Faculty of Engineering Ain Shams University\\Documents\\NetBeansProjects\\SoftwareTestingDiploma_FinalProject\\target\\allure-results\\environment.properties")) {
            props.store(fos, "Environment Details");
        }
    }

    @BeforeClass
    @DataProvider(name = "combinedData")
    public Object[][] setUpClass() throws FileNotFoundException {
        SignUpUser[] users = OrderHelperClass.
                ReadUsers("SignUpCredentials.json");
        FormUser[] forms = OrderHelperClass.
                ReadFormData("FormDetails.json");

        // If you want to map them one-to-one (user[i] with form[i]):
        int size = Math.min(users.length, forms.length);
        Object[][] data = new Object[size][2];
        for (int i = 0; i < size; i++) {
            data[i][0] = users[i];
            data[i][1] = forms[i];
        }

        return data;
    }

    @BeforeMethod
    @Description(
            "This is a method that runs before the class to initialize the browser and assert that the required pages opens")
    @Severity(SeverityLevel.MINOR)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    public void setUpMethod() throws Exception {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-popup-blocking");   // blocks popup windows
//        options.addArguments("--disable-notifications");
        Browser = new EdgeDriver();
        h = new HomePage(Browser);

        p = new ProductsPage(Browser);
        ct = new CartPage(Browser);

        String CurrentURL = h.initializeBrowser();
        String ExpectedURL = "https://automationexercise.com/";
        Assert.assertTrue(CurrentURL.contains(ExpectedURL),
                CurrentURL + " should contain " + ExpectedURL);
    }

    @AfterMethod
    @Owner("Yahia Allam")
    public void tearDownMethod() throws Exception {
        Browser.quit();
    }

    @Test
    @Description(
            "This test case verifies the product quantity after adding it to cart")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CART-002")
    public void VerifyProductQuantity() {
        openProductsPage();
        verifyAllProductsText();
        openFirstProductDetails();
        verifyProductName();
        verifyProductCategory();
        verifyProductPrice();
        verifyProductAvailability();
        verifyProductCondition();
        verifyProductBrand();
        increaseProductQuantity("4");
        verifyCartQuantities(4);
    }

    @Step("Open Products page")
    public void openProductsPage() {
        h.clickProductsButton();
        AllureUtil.takeScreenshot(Browser, "Products Page Opened");
    }

    @Step("Verify 'ALL PRODUCTS' text is displayed")
    public void verifyAllProductsText() {
        String actualText = p.verifyProductPage();
        Assert.assertTrue(actualText.contains("ALL"),
                actualText + " should contain ALL");
        AllureUtil.takeScreenshot(Browser, "'ALL PRODUCTS' Text Verified");
    }

    @Step("Open first product details page")
    public void openFirstProductDetails() {
        p.clickFirstProductViewProductButton();
        AllureUtil.takeScreenshot(Browser, "First Product Details Page");
    }

    @Step("Verify product name contains 'Blue'")
    public void verifyProductName() {
        String actualText = p.verifyProductNameText();
        Assert.assertTrue(actualText.contains("Blue"),
                actualText + " should contain Blue");
        AllureUtil.takeScreenshot(Browser, "Product Name Verified");
    }

    @Step("Verify product category is 'Category: Women > Tops'")
    public void verifyProductCategory() {
        String actualText = p.verifyCategoryText();
        Assert.assertTrue(actualText.contains("Category: Women > Tops"),
                actualText + " should contain Category: Women > Tops");
        AllureUtil.takeScreenshot(Browser, "Product Category Verified");
    }

    @Step("Verify product price is 'Rs. 500'")
    public void verifyProductPrice() {
        String actualText = p.verifyPriceText();
        Assert.assertTrue(actualText.contains("Rs. 500"),
                actualText + " should contain Rs. 500");
        AllureUtil.takeScreenshot(Browser, "Product Price Verified");
    }

    @Step("Verify product availability contains 'Availability:'")
    public void verifyProductAvailability() {
        String actualText = p.verifyAvailabilityText();
        Assert.assertTrue(actualText.contains("Availability:"),
                actualText + " should contain Availability:");
        AllureUtil.takeScreenshot(Browser, "Product Availability Verified");
    }

    @Step("Verify product condition contains 'Condition:'")
    public void verifyProductCondition() {
        String actualText = p.verifyConditionText();
        Assert.assertTrue(actualText.contains("Condition:"),
                actualText + " should contain Condition:");
        AllureUtil.takeScreenshot(Browser, "Product Condition Verified");
    }

    @Step("Verify product brand contains 'Brand:'")
    public void verifyProductBrand() {
        String actualText = p.verifyBrandText();
        Assert.assertTrue(actualText.contains("Brand:"),
                actualText + " should contain Brand:");
        AllureUtil.takeScreenshot(Browser, "Product Brand Verified");
    }

    @Step("Increase product quantity to {quantity}")
    public void increaseProductQuantity(String quantity) {
        p.increaseQuantity(quantity);
        AllureUtil.takeScreenshot(Browser,
                "Product Quantity Increased to " + quantity);
    }

    @Step("Verify cart quantities are equal to {expectedQuantity}")
    public void verifyCartQuantities(int expectedQuantity) {
        List<String> quantities = ct.getProductQuantities();
        List<String> prices = ct.getProductPrices();

        for (int i = 0; i < prices.size(); i++) {
            String quantity = quantities.get(i);
            Assert.assertTrue(Integer.parseInt(quantity) > 0,
                    "Quantity should be > 0 but was: " + quantity);
            Assert.assertEquals(Integer.parseInt(quantity), expectedQuantity,
                    "Quantity should be " + expectedQuantity + " but was: " + quantity);
        }
        AllureUtil.takeScreenshot(Browser, "Cart Quantities Verified");
    }

}
