/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Product_TestCases;

import Authentication_TestCases.AllureUtil;

import automationExercise_Pages.CheckoutPage;

import automationExercise_Pages.HomePage;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author y_all
 */
public class AddToCartRecommendedItems {

    private HomePage h;

    private CheckoutPage ch;

    private WebDriver Browser;
    static Product[] Products;

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
        SignUpUser[] users = ProductHelperClass.
                ReadUsers("SignUpCredentials.json");
        FormUser[] forms = ProductHelperClass.
                ReadFormData("FormDetails.json");
        Products = ProductHelperClass.ReadProductData("Products.json");
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
        Browser = new ChromeDriver();
        h = new HomePage(Browser);

        ch = new CheckoutPage(Browser);

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

    @DataProvider(name = "productData")
    public Product[] productDataProvider() {
        return Products;
    }
//    @Test
//    @Description(
//            "This test case examines adding products to cart from recommended items")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("RECOM-001")
//    public void AddToCartFromRecommendedItems() {
//        Assert.assertTrue(h.scrollToRecommendedItems().toLowerCase().contains(
//                "recommended"));
//        h.addToCartRecommendedProduct();
//        List<WebElement> ProductsAdded = ch.verifyBothProductsAreAdded();
//        for (WebElement cartProduct : ProductsAdded) {
//            String productAdded = cartProduct.getText();
//            System.out.println("Found product: " + productAdded);
//        }
//        List<String> prices = ch.getProductPrices();
//        List<String> quantities = ch.getProductQuantities();
//        List<String> totals = ch.getProductTotalPrices();
//
//        // Verify each product
//        for (int i = 0; i < prices.size(); i++) {
//            String price = prices.get(i);
//            String quantity = quantities.get(i);
//            String total = totals.get(i);
//
//            System.out.println("Checking product " + (i + 1));
//            Assert.assertTrue(price.contains("Rs"),
//                    "Price should contain Rs but was: " + price);
//            Assert.assertTrue(Integer.parseInt(quantity) > 0,
//                    "Quantity should be > 0 but was: " + quantity);
//            Assert.assertTrue(total.contains("Rs"),
//                    "Total should contain Rs but was: " + total);
//        }
//    }

    @Test
    @Description(
            "This test case examines adding products to cart from recommended items")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("RECOM-001")
    public void AddToCartFromRecommendedItems() {
        scrollToRecommendedItemsSection();
        addRecommendedProductToCart();
        verifyProductsInCart();
        validateCartProductDetails();
    }

    @Step("Scroll to 'Recommended Items' section")
    public void scrollToRecommendedItemsSection() {
        Assert.assertTrue(h.scrollToRecommendedItems().toLowerCase().contains(
                "recommended"),
                "'Recommended' section not found");
        AllureUtil.takeScreenshot(Browser, "Recommended Items Section");
    }

    @Step("Add a recommended product to the cart")
    public void addRecommendedProductToCart() {
        h.addToCartRecommendedProduct();
        AllureUtil.takeScreenshot(Browser, "Recommended Product Added to Cart");
    }

    @Step("Verify products are added to the cart")
    public void verifyProductsInCart() {
        List<WebElement> productsAdded = ch.verifyBothProductsAreAdded();
        for (WebElement cartProduct : productsAdded) {
            String productAdded = cartProduct.getText();
            System.out.println("Found product: " + productAdded);
        }
        AllureUtil.takeScreenshot(Browser, "Products in Cart Verified");
    }

    @Step("Validate price, quantity, and total for each product in the cart")
    public void validateCartProductDetails() {
        List<String> prices = ch.getProductPrices();
        List<String> quantities = ch.getProductQuantities();
        List<String> totals = ch.getProductTotalPrices();

        for (int i = 0; i < prices.size(); i++) {
            String price = prices.get(i);
            String quantity = quantities.get(i);
            String total = totals.get(i);

            System.out.println("Checking product " + (i + 1));
            Assert.assertTrue(price.contains("Rs"),
                    "Price should contain Rs but was: " + price);
            Assert.assertTrue(Integer.parseInt(quantity) > 0,
                    "Quantity should be > 0 but was: " + quantity);
            Assert.assertTrue(total.contains("Rs"),
                    "Total should contain Rs but was: " + total);
        }
        AllureUtil.takeScreenshot(Browser, "Cart Product Details Validated");
    }

}
