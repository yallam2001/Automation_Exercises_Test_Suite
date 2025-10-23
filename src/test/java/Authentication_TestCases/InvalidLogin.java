/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Authentication_TestCases;


import automationExercise_Pages.HomePage;
import automationExercise_Pages.SignUp_Login_Page;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
public class InvalidLogin {

    private HomePage h;
    private SignUp_Login_Page s;

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
    @DataProvider(name = "mergedData")
    public Object[][] mergedDataProvider() throws FileNotFoundException {
        LoginUser[] loginUsers = AuthHelperClass.ReadLoginData(
                "LoginCredentials.json");
        SignUpUser[] signUpUsers = AuthHelperClass.ReadUsers(
                "SignUpCredentials.json");
        FormUser[] formUsers = AuthHelperClass.ReadFormData("FormDetails.json");

        int size = Math.min(Math.min(loginUsers.length, signUpUsers.length),
                formUsers.length);
        Object[][] data = new Object[size][3];

        for (int i = 0; i < size; i++) {
            data[i][0] = loginUsers[i];
            data[i][1] = signUpUsers[i];
            data[i][2] = formUsers[i];
        }

        return data;
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Browser = new EdgeDriver();
        h = new HomePage(Browser);
        s = new SignUp_Login_Page(Browser);

        openHomePage();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        Browser.quit();
    }

//    @Test(dataProvider = "loginData")
//    @Description(
//            "This test case examines whether the login function works as intended or not with incorrect email and password")
//    @Severity(SeverityLevel.CRITICAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("AUTH-003")
//    public void InvalidLogin(LoginUser loginUser) {
//        h.clickLoginButton();
//        String ExpectedverifyLoginText = s.loginToYourAccountText();
//        String ActualverifyLoginText = "Login to your account";
//        Assert.assertTrue(ActualverifyLoginText.
//                contains(ExpectedverifyLoginText),
//                ActualverifyLoginText + " should contain " + ExpectedverifyLoginText);
//        s.loginProcess(loginUser.email, loginUser.password);
//        String ExpectedincorrectEmailAndPassword = s.
//                incorrectEmailAndPasswordText();
//        String ActualincorrectEmailAndPassword = "Your email or password is incorrect!";
//        Assert.assertTrue(ActualincorrectEmailAndPassword.contains(
//                ExpectedincorrectEmailAndPassword),
//                ActualincorrectEmailAndPassword + " should contain " + ExpectedincorrectEmailAndPassword);
//    }
    @Test(dataProvider = "mergedData")
    @Description(
            "This test case examines whether the login function works as intended or not with incorrect email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("AUTH-003")
    public void InvalidLogin(LoginUser loginUser,
            SignUpUser testUser,
            FormUser formData) {
        openLoginPage();
        verifyLoginText();
        attemptLogin(loginUser);
        verifyLoginFailure();
    }

    @Step("Open home page")
    public void openHomePage() {
        String currentURL = h.initializeBrowser();
        Assert.
                assertTrue(currentURL.
                        contains("https://automationexercise.com/"));
        AllureUtil
                .takeScreenshot(Browser, "Home Page");
    }

    @Step("Open login page")
    public void openLoginPage() {
        h.clickLoginButton();
        AllureUtil.takeScreenshot(Browser, "Login Page Opened");
    }

    @Step("Verify 'Login to your account' text is displayed")
    public void verifyLoginText() {
        String expected = s.loginToYourAccountText();
        Assert.assertTrue("Login to your account".contains(expected),
                "'Login to your account' should contain " + expected);
        AllureUtil.takeScreenshot(Browser, "Login Text Verified");
    }

    @Step("Attempt login with email: {loginUser.email}")
    public void attemptLogin(LoginUser loginUser) {
        s.loginProcess(loginUser.email, loginUser.password);
        AllureUtil.takeScreenshot(Browser, "Login Attempted");
    }

    @Step("Verify login failure message is displayed")
    public void verifyLoginFailure() {
        String expected = s.incorrectEmailAndPasswordText();
        Assert.assertTrue("Your email or password is incorrect!".contains(
                expected),
                "'Your email or password is incorrect!' should contain " + expected);
        AllureUtil.takeScreenshot(Browser, "Login Failure Verified");
    }
}
