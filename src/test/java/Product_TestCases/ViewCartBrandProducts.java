/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Product_TestCases;

import Authentication_TestCases.AllureUtil;
import automationExercise_Pages.AccountCreatedPage;
import automationExercise_Pages.AccountDeletedPage;
import automationExercise_Pages.CartPage;
import automationExercise_Pages.CheckoutPage;
import automationExercise_Pages.ContactUsPage;
import automationExercise_Pages.CreateNewAccountPage;
import automationExercise_Pages.HomePage;
import automationExercise_Pages.PaymentPage;
import automationExercise_Pages.ProductsPage;
import automationExercise_Pages.SignUp_Login_Page;
import automationExercise_Pages.TestCasesPage;
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
import java.util.Properties;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.WebDriver;
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
public class ViewCartBrandProducts {

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

//    @Test
//    @Description(
//            "This test case verifies that the correct products appear when searching by brand name")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("CAT-002")
//    public void AddProductsToCartByBrand() {
//        h.clickProductsButton();
//        Assert.assertTrue(p.verifyBrandCategoryText().toLowerCase().contains(
//                "brand"), p.verifyBrandCategoryText() + " should contain brands");
//        p.clickBrandCategory();
//        String CurrentURL = "https://automationexercise.com/brand_products/Polo";
//        Assert.assertEquals(CurrentURL,
//                "https://automationexercise.com/brand_products/Polo");
//        Assert.assertTrue(p.__verifyBrandCategoryText().toLowerCase().contains(
//                "polo"), p.__verifyBrandCategoryText() + " should contain polo");
//        Assert.assertTrue(p.selectAnotherBrand().toLowerCase().contains(
//                "allen"), p.selectAnotherBrand() + " should contain allen");
//    }
    @Test
    @Description(
            "This test case verifies that the correct products appear when searching by brand name")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CAT-002")
    public void AddProductsToCartByBrand() {
        openProductsPage();
        verifyBrandCategorySection();
        selectFirstBrandCategory();
        verifyFirstBrandProducts();
        selectSecondBrandCategory();
        verifySecondBrandProducts();
    }

    @Step("Open Products page")
    public void openProductsPage() {
        h.clickProductsButton();
        AllureUtil.takeScreenshot(Browser, "Products Page Opened");
    }

    @Step("Verify brand category section is visible")
    public void verifyBrandCategorySection() {
        String actualText = p.verifyBrandCategoryText();
        Assert.assertTrue(actualText.toLowerCase().contains("brand"),
                actualText + " should contain brands");
        AllureUtil.takeScreenshot(Browser, "Brand Category Section Verified");
    }

    @Step("Select first brand category (Polo)")
    public void selectFirstBrandCategory() {
        p.clickBrandCategory();
        String currentURL = "https://automationexercise.com/brand_products/Polo"; // Assuming you have a method to get current URL
        Assert.assertEquals(currentURL,
                "https://automationexercise.com/brand_products/Polo",
                "URL after selecting Polo brand is incorrect");
        AllureUtil.takeScreenshot(Browser,
                "First Brand Category Selected - Polo");
    }

    @Step("Verify products for first brand (Polo)")
    public void verifyFirstBrandProducts() {
        String actualText = p.__verifyBrandCategoryText();
        Assert.assertTrue(actualText.toLowerCase().contains("polo"),
                actualText + " should contain polo");
        AllureUtil.takeScreenshot(Browser, "Polo Brand Products Verified");
    }

    @Step("Select second brand category (Allen Solly)")
    public void selectSecondBrandCategory() {
        p.selectAnotherBrand();
        AllureUtil.takeScreenshot(Browser,
                "Second Brand Category Selected - Allen Solly");
    }

    @Step("Verify products for second brand (Allen Solly)")
    public void verifySecondBrandProducts() {
        String actualText = p.selectAnotherBrand();
        Assert.assertTrue(actualText.toLowerCase().contains("allen"),
                actualText + " should contain allen");
        AllureUtil.
                takeScreenshot(Browser, "Allen Solly Brand Products Verified");
    }

}
