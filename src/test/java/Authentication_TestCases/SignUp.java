/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
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
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;

/**
 *
 * @author y_all
 */
public class SignUp {

    private HomePage h;
    private SignUp_Login_Page s;
    private CreateNewAccountPage c;
    private AccountCreatedPage ac;
    private AccountDeletedPage ad;
    private SoftAssert a;
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
        SignUpUser[] users = AuthHelperClass.ReadUsers("SignUpCredentials.json");
        FormUser[] forms = AuthHelperClass.ReadFormData("FormDetails.json");
        // If you want to map them one-to-one (user[i] with formData[i]):
        int size = Math.min(users.length, forms.length);
        Object[][] data = new Object[size][2];
        for (int i = 0; i < size; i++) {
            data[i][0] = users[i];
            data[i][1] = forms[i];
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
        openHomePage();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        Browser.quit();
    }

//    @Test(dataProvider = "combinedData")
//    @Description(
//            "This test case tests whether the signup formData works as intended or not")
//    @Severity(SeverityLevel.CRITICAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("AUTH-001")
//    public void SignUpScenarios(SignUpUser testUser,
//            FormUser formData) {
//        String ActualURL = h.clickLoginButton();
//        String ExpectedURL = "https://automationexercise.com/login";
//        Assert.assertTrue(ActualURL.contains(ExpectedURL),
//                ActualURL + " should contain " + ExpectedURL);
//        String ActualSignUp = s.newUserSignUpText();
//        String ExpectedSignUp = "New User Signup!";
//        Assert.assertTrue(ActualSignUp.contains(ExpectedSignUp),
//                ActualSignUp + " should contain " + ExpectedSignUp);
//        s.enterNameField(testUser.name);
//        s.enterEmailAddressField(testUser.email);
//        s.clickSignUpButton();
//
    ////        String alreadyExistExpected = "Email Address already exist!";
////
////        if (s.emailAlreadyExists().contains(alreadyExistExpected)) {
////            // Assertion to confirm it’s the correct message
////            Assert.assertTrue(s.emailAlreadyExists().contains(alreadyExistExpected),
////                    s.emailAlreadyExists() + " should contain " + alreadyExistExpected);
////
////            // If email already exists → enter a new one
////            s.enterNameField(testUser.name);
////            s.enterEmailAddressField(testUser.email);
////            s.clickSignUpButton();
////        } else {
////            // If no error → assert that the error message should not appear
////            Assert.
////                    assertFalse(s.emailAlreadyExists().
////                            contains(alreadyExistExpected),
////                            "Unexpected error: " + s.emailAlreadyExists());
////        }
//        String ActualCreateNewAccount = c.CreateNewAccountConfirmation();
//        String ExpectedCreateNewAccount = "ENTER ACCOUNT INFORMATION";
//        Assert.assertTrue(ActualCreateNewAccount.contains(
//                ExpectedCreateNewAccount),
//                ActualCreateNewAccount + " should contain " + ExpectedCreateNewAccount);
//        c.FillForm(formData.name,formData.password, formData.day, formData.month,
//                formData.year, formData.first_name, formData.last_name,
//                formData.company, formData.Address, formData.country,
//                formData.State, formData.city, formData.zipcode,
//                formData.mobile_number);
//        String ActualCreateAccount = c.CreateAccountConfirm();
//        String ExpectedCreateAccount = "ACCOUNT CREATED!";
//        Assert.assertTrue(ActualCreateAccount.contains(ExpectedCreateAccount),
//                ActualCreateAccount + " should contain " + ExpectedCreateAccount);
//        ac.clickContinueButton();
////        h.dismissAlert();
////        h.acceptAlert();
//        String ActualLoggedInAsText = "Logged in as";
//        String ExpectedLoggedInAsText = h.verifyLoggedInAsButton();
//        Assert.assertTrue(ActualLoggedInAsText.contains(ExpectedLoggedInAsText),
//                ActualLoggedInAsText + " should contain " + ExpectedLoggedInAsText);
//        h.clickDeleteAccountButton();
//        String AccountDeletedText = ad.confirmAccountDeleted();
//        String ExpectedAccountDeletedText = "ACCOUNT DELETED!";
//        Assert.assertTrue(AccountDeletedText.
//                contains(ExpectedAccountDeletedText),
//                AccountDeletedText + " should contain " + ExpectedAccountDeletedText);
//        ad.clickContinueButton();
//    }
//}
    
    @Test(dataProvider = "combinedData")
    @Description(
            "This test case tests whether the signup formData works as intended or not")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("AUTH-001")
    public void SignUpScenarios(SignUpUser testUser,
            FormUser formData) {
        //openHomePage();
        navigateToLoginPage();
        verifyNewUserSignupText();
        enterNameAndEmail(testUser);
        verifyCreateNewAccountPage();
        fillSignUpForm(formData);
        verifyAccountCreated();
        continueAfterAccountCreated();
        verifyLoggedInAs();
        deleteAccount();
        verifyAccountDeleted();
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
        c.enterNameField("magdy");
        c.tryEditNameField(newName);
        AllureUtil.takeScreenshot(Browser, "Attempted to edit name field");

        String actualName = c.getNameFieldValue();
        a.assertEquals(actualName, "magdy",
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

    @Step("Click Delete Account button")
    public void deleteAccount() {
        h.clickDeleteAccountButton();
        AllureUtil
                .takeScreenshot(Browser, "After clicking delete");
    }

    @Step("Verify 'ACCOUNT DELETED!' message")
    public void verifyAccountDeleted() {
        String actual = ad.confirmAccountDeleted();
        Assert.assertTrue(actual.contains("ACCOUNT DELETED!"));
        AllureUtil
                .takeScreenshot(Browser, "Account Deleted Confirmation");
        ad.clickContinueButton();
    }
}
