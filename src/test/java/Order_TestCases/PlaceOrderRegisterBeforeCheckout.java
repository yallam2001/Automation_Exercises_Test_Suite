/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Order_TestCases;

import Authentication_TestCases.AllureUtil;
import automationExercise_Pages.AccountCreatedPage;
import automationExercise_Pages.AccountDeletedPage;
import automationExercise_Pages.CartPage;
import automationExercise_Pages.CheckoutHelperClass;
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
import java.util.List;
import java.util.Properties;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
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
public class PlaceOrderRegisterBeforeCheckout {

    private HomePage h;
    private SignUp_Login_Page s;
    private CreateNewAccountPage c;
    private AccountCreatedPage ac;
    private AccountDeletedPage ad;
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
    @DataProvider(name = "combinedData")
    public Object[][] setUpClass() throws FileNotFoundException {
        RegisterBeforeCheckoutUser[] registerBeforeCheckoutUser = OrderHelperClass.
                ReadRegisterBeforeCheckoutData("RegisterCheckoutDetails.json");
        FormUser[] forms = OrderHelperClass.
                ReadFormData("FormDetails.json");
        // If you want to map them one-to-one (user[i] with form[i]):
        int size = Math.min(registerBeforeCheckoutUser.length, forms.length);
        Object[][] data = new Object[size][2];
        for (int i = 0; i < size; i++) {
            data[i][0] = registerBeforeCheckoutUser[i];
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
        s = new SignUp_Login_Page(Browser);
        c = new CreateNewAccountPage(Browser);
        ac = new AccountCreatedPage(Browser);
        ad = new AccountDeletedPage(Browser);
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

    @Test(dataProvider = "combinedData")
    @Description(
            "This test case examines registering an account before placing an order works as intended or not")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CHK-002")
    public void PlaceOrderRegisterBeforeCheckout(RegisterBeforeCheckoutUser registerBeforeCheckoutUser,
            FormUser formData) {
        openLoginPage();
        verifyNewUserSignUpText();
        enterSignUpDetails(registerBeforeCheckoutUser.name, registerBeforeCheckoutUser.email);
        verifyCreateNewAccountPage();
        fillAccountForm(formData);
        verifyAccountCreated();
        continueAfterAccountCreation();
        verifyLoggedInAs();
        addProductsToCart();
        proceedToCheckoutAfterRegister();
        verifyDeliveryAndBillingAddresses(formData);
        verifyReviewOrderSection();
        verifyProductsInOrder();
        placeOrderWithComment("gjgjgjgjgjggjgjgjgjgjgjggjgjgjgjgj");
        enterCardDetails(formData.cardName, formData.cardNumber, formData.cvv,
                formData.expiryMonth, formData.expiryYear);
        verifyOrderSuccess();
        acceptOrderAlert();
        deleteAccount();
        confirmAccountDeletion();
    }

    @Step("Open login page")
    public void openLoginPage() {
        String actualURL = h.clickLoginButton();
        Assert.assertTrue(actualURL.contains(
                "https://automationexercise.com/login"),
                actualURL + " should contain https://automationexercise.com/login");
        AllureUtil.takeScreenshot(Browser, "Login Page Opened");
    }

    @Step("Verify 'New User Signup!' text is displayed")
    public void verifyNewUserSignUpText() {
        String actual = s.newUserSignUpText();
        Assert.assertTrue(actual.contains("New User Signup!"),
                actual + " should contain New User Signup!");
        AllureUtil.takeScreenshot(Browser, "New User Signup Text Verified");
    }

    @Step("Enter sign-up details: Name = {name}, Email = {email}")
    public void enterSignUpDetails(String name,
            String email) {
        s.enterNameField(name);
        s.enterEmailAddressField(email);
        s.clickSignUpButton();
        AllureUtil.takeScreenshot(Browser, "Sign-Up Details Entered");
    }

    @Step("Verify 'ENTER ACCOUNT INFORMATION' page is displayed")
    public void verifyCreateNewAccountPage() {
        String actual = c.CreateNewAccountConfirmation();
        Assert.assertTrue(actual.contains("ENTER ACCOUNT INFORMATION"),
                actual + " should contain ENTER ACCOUNT INFORMATION");
        AllureUtil.takeScreenshot(Browser, "Create New Account Page Verified");
    }

    @Step("Fill account form with data from JSON")
    public void fillAccountForm(FormUser formData) {
        c.FillForm(formData.password, formData.day,
                formData.month,
                formData.year, formData.first_name, formData.last_name,
                formData.company, formData.Address, formData.country,
                formData.State, formData.city, formData.zipcode,
                formData.mobile_number);
        AllureUtil.takeScreenshot(Browser, "Account Form Filled");
    }

    @Step("Verify account creation success")
    public void verifyAccountCreated() {
        String actual = c.CreateAccountConfirm();
        Assert.assertTrue(actual.contains("ACCOUNT CREATED!"),
                actual + " should contain ACCOUNT CREATED!");
        AllureUtil.takeScreenshot(Browser, "Account Created Verified");
    }

    @Step("Continue after account creation")
    public void continueAfterAccountCreation() {
        ac.clickContinueButton();
        AllureUtil.takeScreenshot(Browser, "Continued After Account Creation");
    }

    @Step("Verify 'Logged in as' text is displayed")
    public void verifyLoggedInAs() {
        String expected = h.verifyLoggedInAsButton();
        Assert.assertTrue("Logged in as".contains(expected),
                "'Logged in as' should contain " + expected);
        AllureUtil.takeScreenshot(Browser, "Logged In As Verified");
    }

    @Step("Add products to cart")
    public void addProductsToCart() {
        h.clickProductsButton();
        p.addToCart();
        h.clickCartButton();
        AllureUtil.takeScreenshot(Browser, "Products Added to Cart");
    }

    @Step("Proceed to checkout after registering")
    public void proceedToCheckoutAfterRegister() {
        ct.clickProceedToCheckoutAfterRegister();
        AllureUtil.takeScreenshot(Browser,
                "Proceeded to Checkout After Register");
    }

    @Step("Verify delivery and billing addresses match form data")
    public void verifyDeliveryAndBillingAddresses(FormUser formData) {
        CheckoutHelperClass delivery = ch.getDeliveryAddress();
        CheckoutHelperClass billing = ch.getBillingAddress();

        a.assertTrue(delivery.name.contains(formData.first_name));
        a.assertTrue(delivery.name.contains(formData.last_name));
        a.assertEquals(delivery.company, formData.company);
        a.assertEquals(delivery.address, formData.Address);
        a.assertTrue(delivery.cityStateZip.contains(formData.city));
        a.assertTrue(delivery.cityStateZip.contains(formData.zipcode));
        a.assertEquals(delivery.country, formData.country);
        a.assertEquals(delivery.mobile, formData.mobile_number);
        a.assertEquals(delivery.toString(), billing.toString());

        AllureUtil.takeScreenshot(Browser,
                "Delivery & Billing Addresses Verified");
    }

    @Step("Verify 'Review Your Order' section is displayed")
    public void verifyReviewOrderSection() {
        a.assertTrue(ch.verifyReviewYourOrderText().toLowerCase().contains(
                "review your order"));
        AllureUtil.takeScreenshot(Browser, "Review Your Order Section Verified");
    }

    @Step("Verify products in order")
    public void verifyProductsInOrder() {
        List<WebElement> productsAdded = ch.verifyBothProductsAreAdded();
        for (WebElement cartProduct : productsAdded) {
            System.out.println("Found product: " + cartProduct.getText());
        }

        List<String> prices = ch.getProductPrices();
        List<String> quantities = ch.getProductQuantities();
        List<String> totals = ch.getProductTotalPrices();

        for (int i = 0; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i).contains("Rs"),
                    "Price should contain Rs but was: " + prices.get(i));
            Assert.assertTrue(Integer.parseInt(quantities.get(i)) > 0,
                    "Quantity should be > 0 but was: " + quantities.get(i));
            Assert.assertTrue(totals.get(i).contains("Rs"),
                    "Total should contain Rs but was: " + totals.get(i));
        }

        AllureUtil.takeScreenshot(Browser, "Products in Order Verified");
    }

    @Step("Place order with comment: {comment}")
    public void placeOrderWithComment(String comment) {
        ch.placeOrder(comment);
        AllureUtil.takeScreenshot(Browser, "Order Placed with Comment");
    }

    @Step("Enter card details from JSON")
    public void enterCardDetails(String cardName,
            String cardNumber,
            String cvv,
            String expiryMonth,
            String expiryYear) {
        pay.enterCardDetails(cardName, cardNumber, cvv, expiryMonth, expiryYear);
        AllureUtil.takeScreenshot(Browser, "Card Details Entered");
    }

    @Step("Verify order success message is displayed")
    public void verifyOrderSuccess() {
        Assert.assertTrue(ch.verifyOrderSuccess().toLowerCase().contains(
                "your order"));
        AllureUtil.takeScreenshot(Browser, "Order Success Verified");
    }

    @Step("Accept order confirmation alert")
    public void acceptOrderAlert() {
        mySel.acceptAlert();
        AllureUtil.takeScreenshot(Browser, "Order Alert Accepted");
    }

    @Step("Delete account")
    public void deleteAccount() {
        h.clickDeleteAccountButton();
        AllureUtil.takeScreenshot(Browser, "Delete Account Clicked");
    }

    @Step("Confirm account deletion")
    public void confirmAccountDeletion() {
        Assert.assertTrue(ad.confirmAccountDeleted().toLowerCase().contains(
                "deleted"));
        ad.clickContinueButton();
        AllureUtil.takeScreenshot(Browser, "Account Deletion Confirmed");
    }

}
