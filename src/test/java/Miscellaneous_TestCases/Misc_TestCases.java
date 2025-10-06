/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Miscellaneous_TestCases;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 *
 * @author y_all
 */
public class Misc_TestCases {

    private HomePage h;
    private SignUp_Login_Page s;
    private CreateNewAccountPage c;
    private AccountCreatedPage ac;
    private AccountDeletedPage ad;
    private ContactUsPage cu;
    private TestCasesPage tc;
    private ProductsPage p;
    private CartPage ct;
    private CheckoutPage ch;
    private PaymentPage pay;
    private SoftAssert a;
    private WebDriver Browser;
    private self_selenium mySel;

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
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
        s = new SignUp_Login_Page(Browser);
        c = new CreateNewAccountPage(Browser);
        ac = new AccountCreatedPage(Browser);
        ad = new AccountDeletedPage(Browser);
        cu = new ContactUsPage(Browser);
        tc = new TestCasesPage(Browser);
        p = new ProductsPage(Browser);
        ct = new CartPage(Browser);
        ch = new CheckoutPage(Browser);
        pay = new PaymentPage(Browser);
        a = new SoftAssert();
        mySel = new self_selenium(Browser);
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
            "This test case examines whether the Contact Us functionality works as intended or not")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CONT-001")
    public void ContactUs_TestCase() {
        openContactUsPage();
        verifyContactUsHeader();
        fillContactUsForm("Yahia", "yahiallam@gmail.com", "Product",
                "ekvekfvnevnefkvnewkvnkevkenveklfnveklfvnlekfvnkelvnkelfnvewklfvn",
                "C:/file.txt");
        verifyContactUsSuccess();
        returnToHomePage();
    }

    @Step("Open Contact Us page")
    public void openContactUsPage() {
        h.clickContactUsButton();
        AllureUtil.takeScreenshot(Browser, "Contact Us Page Opened");
    }

    @Step("Verify 'GET IN TOUCH' header is displayed")
    public void verifyContactUsHeader() {
        String actual = cu.verifyContactUsPage();
        Assert.assertTrue(actual.contains("GET IN TOUCH"),
                actual + " should contain GET IN TOUCH");
        AllureUtil.takeScreenshot(Browser, "Contact Us Header Verified");
    }

    @Step("Fill Contact Us form with name: {name}, email: {email}, subject: {subject}")
    public void fillContactUsForm(String name,
            String email,
            String subject,
            String message,
            String filePath) {
        cu.ContactUsProcess(name, email, subject, message, filePath);
        AllureUtil.takeScreenshot(Browser, "Contact Us Form Filled");
    }

    @Step("Verify Contact Us success message is displayed")
    public void verifyContactUsSuccess() {
        String actual = cu.verifyContactUsSuccess();
        Assert.assertTrue(actual.contains("Success!"),
                actual + " should contain Success!");
        AllureUtil.takeScreenshot(Browser, "Contact Us Success Verified");
    }

    @Step("Return to home page and verify URL")
    public void returnToHomePage() {
        cu.clickHomeButton();
        String actualURL = mySel.getCurrentURL();
        Assert.assertTrue(actualURL.contains("https://automationexercise.com/"),
                actualURL + " should contain https://automationexercise.com/");
        AllureUtil.takeScreenshot(Browser, "Returned to Home Page");
    }

    @Test
    @Description(
            "This test case examines whether the test case functionality works as intended or not")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("TCP-001")
    public void TestCasePage() {
        openTestCasesPage();
        verifyTestCasesURL();
    }

    @Step("Open Test Cases page")
    public void openTestCasesPage() {
        h.clickTestCasesButton();
        AllureUtil.takeScreenshot(Browser, "Test Cases Page Opened");
    }

    @Step("Verify Test Cases page URL")
    public void verifyTestCasesURL() {
        String actualURL = tc.verifyTestCasePage();
        Assert.assertTrue(actualURL.contains(
                "https://automationexercise.com/test_cases"),
                actualURL + " should contain https://automationexercise.com/test_cases");
        AllureUtil.takeScreenshot(Browser, "Test Cases URL Verified");
    }

    @Test
    @Description(
            "This test case verifies that the subscription works as intended")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("SUB-001")
    public void Subscription_HomePage() {
        verifySubscriptionSection();
        subscribeWithEmail("yahiallam@gmail.com");
    }

    @Step("Verify subscription section is visible on home page")
    public void verifySubscriptionSection() {
        String actual = h.verifySubscriptionText().toLowerCase();
        Assert.assertTrue(actual.contains("subscription"),
                actual + " should contain subscription");
        AllureUtil.takeScreenshot(Browser, "Subscription Section Verified");
    }

    @Step("Subscribe with email: {email}")
    public void subscribeWithEmail(String email) {
        String actualAlert = h.enterEmailSubscriptionField(email);
        Assert.assertTrue(actualAlert.contains(
                "You have been successfully subscribed!"),
                actualAlert + " should contain You have been successfully subscribed!");
        AllureUtil.takeScreenshot(Browser, "Subscription Successful");
    }

    @Test
    @Description(
            "This test case verifies that the subscription works as intended in the cart page")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("SUB-002")
    public void Subscription_CartPage() {
        openCartPage();
        verifySubscriptionSection();
        subscribeWithEmail("yahiallam@gmail.com");
    }

    @Step("Open Cart page")
    public void openCartPage() {
        h.clickCartButton();
        AllureUtil.takeScreenshot(Browser, "Cart Page Opened");
    }

}
