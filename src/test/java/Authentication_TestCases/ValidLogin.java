package Authentication_TestCases;

import automationExercise_Pages.AccountCreatedPage;
import automationExercise_Pages.AccountDeletedPage;
import automationExercise_Pages.CreateNewAccountPage;
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
import mySeleniumFramework.self_selenium;
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
import org.testng.asserts.SoftAssert;

public class ValidLogin {

    private HomePage h;
    private SignUp_Login_Page s;
    private CreateNewAccountPage c;
    private AccountCreatedPage ac;
    private AccountDeletedPage ad;
    private SoftAssert a;
    private WebDriver Browser;
    private self_selenium mySel;
    static LoginUser[] LoginUsers;

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

    @DataProvider(name = "loginData")
    public LoginUser[] LoginDataProvider() {
        return LoginUsers;
    }

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
        c = new CreateNewAccountPage(Browser);
        ac = new AccountCreatedPage(Browser);
        ad = new AccountDeletedPage(Browser);
        a = new SoftAssert();
        mySel = new self_selenium(Browser);
        openHomePage();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        Browser.quit();
    }

//    @Test(dataProvider = "loginData")
//    @Description(
//            "This test case examines whether the login function works as intended or not with correct email and password")
//    @Severity(SeverityLevel.CRITICAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("AUTH-002")
//    public void ValidLogin(LoginUser loginUser) {
//        h.clickLoginButton();
//        String ExpectedverifyLoginText = s.loginToYourAccountText();
//        String ActualverifyLoginText = "Login to your account";
//        Assert.assertTrue(ActualverifyLoginText.
//                contains(ExpectedverifyLoginText),
//                ActualverifyLoginText + " should contain " + ExpectedverifyLoginText);
//        s.loginProcess(loginUser.email, loginUser.password);
//        String ActualLoggedInAsText = "Logged in as";
//        String ExpectedLoggedInAsText = h.verifyLoggedInAsButton();
//        Assert.assertTrue(ActualLoggedInAsText.contains(ExpectedLoggedInAsText),
//                ActualLoggedInAsText + " should contain " + ExpectedLoggedInAsText);
//        h.clickDeleteAccountButton();
//        ad.clickContinueButton();
//    }
    @Test(dataProvider = "mergedData")
    @Description(
            "This test case examines whether the login function works as intended or not with correct email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("AUTH-002")
    public void ValidLogin(LoginUser loginUser,
            SignUpUser testUser,
            FormUser formData) {
//        navigateToLoginPage();
//        verifyNewUserSignupText();
//        enterNameAndEmail(testUser);
//        verifyCreateNewAccountPage();
//        fillSignUpForm(formData);
//        verifyAccountCreated();
//        continueAfterAccountCreated();
//        verifyLoggedInAs();
        navigateToLoginPage();
        verifyLoginText();
        performLogin(loginUser);
        verifyLoggedInAs();
        deleteAccount();
        confirmAccountDeletion();
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

    @Step("Navigate to Login page")
    public void navigateToLoginPage() {
        String actual = h.clickLoginButton();
        Assert.assertTrue(actual.
                contains("https://automationexercise.com/login"));
        AllureUtil
                .takeScreenshot(Browser, "Login Page");
    }

    @Step("Verify 'New User Signup!' text appears")
    public void verifyNewUserSignupText() {
        String actual = s.newUserSignUpText();
        Assert.assertTrue(actual.contains("New User Signup!"));
        AllureUtil
                .takeScreenshot(Browser, "New User Signup text");
    }

    @Step("Enter name and email then click Sign Up")
    public void enterNameAndEmail(SignUpUser testUser) {
        s.enterNameField(testUser.name);
        s.enterEmailAddressField(testUser.email);
        s.clickSignUpButton();
        AllureUtil
                .takeScreenshot(Browser, "After entering name/email");
    }

    @Step("Verify 'ENTER ACCOUNT INFORMATION' page is shown")
    public void verifyCreateNewAccountPage() {
        String actual = c.CreateNewAccountConfirmation();
        Assert.assertTrue(actual.contains("ENTER ACCOUNT INFORMATION"));
        AllureUtil
                .takeScreenshot(Browser, "Create Account Information Page");
    }

    @Step("Validate mobile number field accepts only digits")
    public void validateMobileNumberField(FormUser formData) {
        c.enterMobileNumber(formData.mobile_number);
        AllureUtil.takeScreenshot(Browser,
                "Entered Mobile Number: " + formData.mobile_number);

        if (!formData.mobile_number.matches("\\d+")) {
            String actualValue = c.getMobileNumberFieldValue();
            a.assertFalse(actualValue.matches("\\d+"),
                    "Bug: Mobile number field accepted non-digit characters: " + actualValue);
        }
    }

    @Step("Validate name field is not editable after initial input")
    public void validateNameFieldEditability(FormUser formData,
            String newName) {
        c.enterNameField(formData.name);
        c.tryEditNameField("magdy");
        AllureUtil.takeScreenshot(Browser, "Attempted to edit name field");

        String actualName = c.getNameFieldValue();
        a.assertEquals(actualName, formData.name,
                "Bug: Name field was editable after initial input. Expected: " + formData.name + ", Found: " + actualName);
    }

    @Step("Fill the sign-up formData with user details")
    public void fillSignUpForm(FormUser formData) {
        c.FillForm(formData.password, formData.day,
                formData.month,
                formData.year, formData.first_name, formData.last_name,
                formData.company,
                formData.Address, formData.country, formData.State,
                formData.city,
                formData.zipcode, formData.mobile_number);
        validateMobileNumberField(formData);
        validateNameFieldEditability(formData, "magdy");
        c.clickContinueButtonAfterInput();
        AllureUtil
                .takeScreenshot(Browser, "Form filled");
    }

    @Step("Verify 'ACCOUNT CREATED!' message")
    public void verifyAccountCreated() {
        String actual = c.CreateAccountConfirm();
        Assert.assertTrue(actual.contains("ACCOUNT CREATED!"));
        AllureUtil
                .takeScreenshot(Browser, "Account Created Confirmation");
    }

    @Step("Click continue after account creation")
    public void continueAfterAccountCreated() {
        ac.clickContinueButton();
        AllureUtil
                .takeScreenshot(Browser, "After clicking Continue");
    }

    @Step("Verify 'Logged in as' text is displayed")
    public void verifyLoggedInAs() {
        String expected = h.verifyLoggedInAsButton();
        Assert.assertTrue("Logged in as".contains(expected));
        AllureUtil
                .takeScreenshot(Browser, "Logged in as confirmation");
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

    @Step("Perform login with email: {loginUser.email}")
    public void performLogin(LoginUser loginUser) {
        s.loginProcess(loginUser.email, loginUser.password);
        AllureUtil.takeScreenshot(Browser, "Login Submitted");
    }

    @Step("Click Delete Account button")
    public void deleteAccount() {
        h.clickDeleteAccountButton();
        AllureUtil.takeScreenshot(Browser, "Delete Account Clicked");
    }

    @Step("Confirm account deletion")
    public void confirmAccountDeletion() {
        ad.clickContinueButton();
        AllureUtil.takeScreenshot(Browser, "Account Deletion Confirmed");
    }
}
