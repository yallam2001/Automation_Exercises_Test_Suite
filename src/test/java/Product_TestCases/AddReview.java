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
import java.util.Properties;
import org.openqa.selenium.WebDriver;
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
public class AddReview {

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
        FormUser[] forms = ProductHelperClass.ReadFormData("FormDetails.json");
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
//            "This test case examines the review functionality")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("REV-001")
//    public void AddReviewOnProduct() {
//        Assert.assertTrue(h.clickProductsButton().contains(
//                "https://automationexercise.com/products"));
//        p.clickFirstProductViewProductButton();
//        Assert.assertTrue(p.verifyWriteYourReviewText().toLowerCase().contains(
//                "write your review"));
//        Assert.assertTrue(p.fillReview("yahia", "yahia@gmail.com",
//                "hghghghghghghghghghghghghghghghghghghghghghghghghghghghghghghghghg").
//                contains("Thank you"));
//    }

    @Test
    @Description("This test case examines the review functionality")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("REV-001")
    public void AddReviewOnProduct() {
        openProductsPage();
        openFirstProductDetails();
        verifyWriteYourReviewSection();
        submitProductReview("yahia", "yahia@gmail.com",
                "hghghghghghghghghghghghghghghghghghghghghghghghghghghghghghghghghg");
    }

    @Step("Open Products page")
    public void openProductsPage() {
        Assert.assertTrue(h.clickProductsButton()
                .contains("https://automationexercise.com/products"),
                "Products page URL is incorrect");
        AllureUtil.takeScreenshot(Browser, "Products Page Opened");
    }

    @Step("Open first product details page")
    public void openFirstProductDetails() {
        p.clickFirstProductViewProductButton();
        AllureUtil.takeScreenshot(Browser, "First Product Details Page");
    }

    @Step("Verify 'Write Your Review' section is visible")
    public void verifyWriteYourReviewSection() {
        Assert.assertTrue(p.verifyWriteYourReviewText()
                .toLowerCase().contains("write your review"),
                "'Write Your Review' text not found");
        AllureUtil.takeScreenshot(Browser, "Write Your Review Section");
    }

    @Step("Submit product review with name: {name}, email: {email}")
    public void submitProductReview(String name,
            String email,
            String review) {
        Assert.assertTrue(p.fillReview(name, email, review)
                .contains("Thank you"),
                "Review submission confirmation not found");
        AllureUtil.takeScreenshot(Browser, "Review Submitted");
    }

}
