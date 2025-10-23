/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Product_TestCases;

import Authentication_TestCases.AllureUtil;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class SearchProduct {

    private HomePage h;

    private ProductsPage p;

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
        SignUpUser[] users = ProductHelperClass.ReadUsers(
                "SignUpCredentials.json");
        FormUser[] forms = ProductHelperClass.ReadFormData(
                "FormDetails.json");
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

        p = new ProductsPage(Browser);

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

//    @Test(dataProvider = "productData")
//    @Description(
//            "This test case verifies whether the search bar in the products page works as intended or not and all the products searched using this keyword appear")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("PROD-002")
//    public void SearchProducts(Product Products) {
//        h.clickProductsButton();
//        String ActualAllProductText = p.verifyProductPage();
//        String ExpectedAllProductText = "ALL";
//        Assert.assertTrue(ActualAllProductText.contains(ExpectedAllProductText),
//                ActualAllProductText + " should contain " + ExpectedAllProductText);
//        List<WebElement> products = p.searchProduct(Products.productName);
//        String ActualSearchedProductText = p.verifySearchedProductText();
//        String ExpectedSearchedProductText = "SEARCHED";
//        Assert.assertTrue(ActualSearchedProductText.contains(
//                ExpectedSearchedProductText),
//                ActualSearchedProductText + " should contain " + ExpectedSearchedProductText);
//        for (WebElement product : products) {
//            String productName = product.getText().toLowerCase();
//            System.out.println("Found product: " + productName);
//            Assert.assertTrue(productName.contains(Products.productName.
//                    toLowerCase()),
//                    "Product '" + productName + "' does not contain search keyword: " + Products.productName);
//        }
//
//    }
    @Test(dataProvider = "productData")
    @Description(
            "This test case verifies whether the search bar in the products page works as intended or not and all the products searched using this keyword appear")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("PROD-002")
    public void SearchProducts(Product Products) {
        openProductsPage();
        verifyAllProductsText();
        //searchForProduct(Products);
        validateSearchResultsContainKeyword(Products.productName);
        //verifySearchedProductsText();
    }

    @Step("Open Products page")
    public void openProductsPage() {
        h.clickProductsButton();
        AllureUtil.takeScreenshot(Browser, "Products Page Opened");
    }

    @Step("Verify 'ALL PRODUCTS' text is displayed")
    public void verifyAllProductsText() {
        String actualText = p.verifyProductPage();
        String expectedText = "ALL";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "'ALL PRODUCTS' Text Verified");
    }

    @Step("Search for product using keyword from JSON: {productName}")
    public void searchForProduct(Product Products) {
        p.searchProduct(Products.productName);
        AllureUtil.takeScreenshot(Browser,
                "Searched for Product: " + Products.productName);
    }

//    @Step("Verify 'SEARCHED PRODUCTS' text is displayed")
//    public void verifySearchedProductsText() {
//
//    }

    @Step("Validate all search results contain keyword from JSON: {productName}")
    public void validateSearchResultsContainKeyword(String productName) {
        List<WebElement> products = p.searchProduct(productName);
        String actualText = p.verifySearchedProductText();
        String expectedText = "SEARCHED";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "'SEARCHED PRODUCTS' Text Verified");
        for (WebElement product : products) {
            String foundProductName = product.getText().toLowerCase();
            System.out.println("Found product: " + foundProductName);
            Assert.assertTrue(foundProductName.contains(productName.
                    toLowerCase()),
                    "Product '" + foundProductName + "' does not contain search keyword: " + productName);
        }
        AllureUtil.takeScreenshot(Browser,
                "Search Results Validated for Keyword: " + productName);
    }

}
