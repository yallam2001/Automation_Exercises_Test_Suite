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
import java.util.List;
import java.util.Properties;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class SearchProduct_CartLogin {

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
    @DataProvider(name = "mergedData")
    public Object[][] setUpClass() throws FileNotFoundException {
        SignUpUser[] users = ProductHelperClass.ReadUsers(
                "SignUpCredentials.json");
        FormUser[] forms = ProductHelperClass.ReadFormData(
                "FormDetails.json");
        Products = ProductHelperClass.ReadProductData("Products.json");
        LoginUser[] loginUser = ProductHelperClass.ReadLoginData(
                "LoginCredentials.json");
        // If you want to map them one-to-one (user[i] with form[i]):
        int size = Math.min(Math.min(users.length, forms.length),
                loginUser.length);
        Object[][] data = new Object[size][4];
        for (int i = 0; i < size; i++) {
            data[i][0] = users[i];
            data[i][1] = forms[i];
            data[i][2] = Products[i];
            data[i][3] = loginUser[i];
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

    @DataProvider(name = "productData")
    public Product[] productDataProvider() {
        return Products;
    }

//    @Test(dataProvider = "mergedData")
//    @Description(
//            "This test case examines that the cart contains the searched products after logging in")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("CAT-003")
//    public void SearchProducts_and_VerifyCartAfterLogin(SignUpUser testUser,
//            FormUser formData,
//            Product Products,
//            LoginUser loginUser) {
//        h.clickProductsButton();
//        Assert.assertTrue(p.verifyProductPage().toLowerCase().contains(
//                "all products"),
//                p.verifyProductPage() + " should contain all products");
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
//        p.addToCartSearchedProducts();
//        Assert.assertTrue(h.clickCartButton().contains(
//                "https://automationexercise.com/view_cart"),
//                ActualSearchedProductText);
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
//        Assert.assertTrue(h.clickCartButton().contains(
//                "https://automationexercise.com/view_cart"),
//                ActualSearchedProductText);
//        String ExpectedverifyLoginText = s.loginToYourAccountText();
//        String ActualverifyLoginText = "Login to your account";
//        Assert.assertTrue(ActualverifyLoginText.
//                contains(ExpectedverifyLoginText),
//                ActualverifyLoginText + " should contain " + ExpectedverifyLoginText);
//        s.loginProcess(loginUser.email, loginUser.password);
//        Assert.assertTrue(h.clickCartButton().contains(
//                "https://automationexercise.com/view_cart"),
//                ActualSearchedProductText);
//        for (WebElement cartProduct : ProductsAdded) {
//            String productAdded = cartProduct.getText();
//            System.out.println("Found product: " + productAdded);
//        }
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
    @Test(dataProvider = "mergedData")
    @Description(
            "This test case examines that the cart contains the searched products after logging in")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CAT-003")
    public void SearchProducts_and_VerifyCartAfterLogin(SignUpUser testUser,
            FormUser formData,
            Product Products,
            LoginUser loginUser) {
        openProductsPage();
        verifyAllProductsText();
        //searchForProduct(Products.productName);
        validateSearchResultsContainKeyword(Products.productName);
        //verifySearchedProductsText();
        addSearchedProductsToCart();
        openCartPageAndVerifyURL();
        verifyProductsInCart();
        validateCartProductDetails();
        openCartPageAndVerifyURL();
        navigateToLoginPage();
        verifyLoginPageText();
        verifyNewUserSignupText();
        enterNameAndEmail(testUser);
        verifyCreateNewAccountPage();
        fillSignUpForm(formData);
        verifyAccountCreated();
        continueAfterAccountCreated();
        navigateToLoginPage();
        loginWithCredentials(loginUser.email, loginUser.password);
        openCartPageAndVerifyURL();
        verifyProductsInCart();
        validateCartProductDetails();
    }

    @Step("Open Products page")
    public void openProductsPage() {
        h.clickProductsButton();
        AllureUtil.takeScreenshot(Browser, "Products Page Opened");
    }

    @Step("Verify 'ALL PRODUCTS' text is displayed")
    public void verifyAllProductsText() {
        String actualText = p.verifyProductPage();
        Assert.assertTrue(actualText.toLowerCase().contains("all products"),
                actualText + " should contain all products");
        AllureUtil.takeScreenshot(Browser, "'ALL PRODUCTS' Text Verified");
    }

    @Step("Search for product using keyword from JSON: {productName}")
    public void searchForProduct(String productName) {
        p.searchProduct(productName);
        AllureUtil.takeScreenshot(Browser,
                "Searched for Product: " + productName);
    }

//    @Step("Verify 'SEARCHED PRODUCTS' text is displayed")
//    public void verifySearchedProductsText() {
//    }
    @Step("Validate all search results contain keyword from JSON: {productName}")
    public void validateSearchResultsContainKeyword(String productName) {
        List<WebElement> products = p.searchProduct(productName);
        String actualText = p.verifySearchedProductText();
        Assert.assertTrue(actualText.contains("SEARCHED"),
                actualText + " should contain SEARCHED");
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

    @Step("Add searched products to cart")
    public void addSearchedProductsToCart() {
        p.addToCartSearchedProducts();
        AllureUtil.takeScreenshot(Browser, "Searched Products Added to Cart");
    }

    @Step("Open cart page and verify URL")
    public void openCartPageAndVerifyURL() {
        String url = h.clickCartButton();
        Assert.assertTrue(url.contains(
                "https://automationexercise.com/view_cart"),
                "Cart URL is incorrect: " + url);
        AllureUtil.takeScreenshot(Browser, "Cart Page Opened");
    }

    @Step("Verify products in cart")
    public void verifyProductsInCart() {
        List<WebElement> productsAdded = ch.verifyBothProductsAreAdded();
        for (WebElement cartProduct : productsAdded) {
            String productAdded = cartProduct.getText();
            System.out.println("Found product: " + productAdded);
        }
        AllureUtil.takeScreenshot(Browser, "Products in Cart Verified");
    }

    @Step("Validate price, quantity, and total for each product in cart")
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

    @Step("Navigate to Login page")
    public void navigateToLoginPage() {
        String actual = h.clickLoginButton();
        Assert.assertTrue(actual.
                contains("https://automationexercise.com/login"));
        AllureUtil
                .takeScreenshot(Browser, "Login Page");
    }

    @Step("Verify 'Login to your account' text is displayed")
    public void verifyLoginPageText() {
        String expected = s.loginToYourAccountText();
        Assert.assertTrue("Login to your account".contains(expected),
                "'Login to your account' should contain " + expected);
        AllureUtil.takeScreenshot(Browser, "Login Page Verified");
    }

    @Step("Login with email from JSON: {email}")
    public void loginWithCredentials(String email,
            String password) {
        s.loginProcess(email, password);
        AllureUtil.takeScreenshot(Browser, "Logged in with Email: " + email);
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
        validateNameFieldEditability(formData, formData.name);
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
}
