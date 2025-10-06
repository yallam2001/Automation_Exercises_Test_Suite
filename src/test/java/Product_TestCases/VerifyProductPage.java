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
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
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
public class VerifyProductPage {

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

//        @Test
//        @Description(
//                "This test case verifies that the product and product details page works as intended")
//        @Severity(SeverityLevel.NORMAL)
//        @Owner("Yahia Allam")
//        @Link(name = "Website", url = "https://automationexercise.com/")
//        @Issue("PROD-001")
//        public void TC8_Req008() {
//            h.clickProductsButton();
//            String ActualAllProductText = p.verifyProductPage();
//            String ExpectedAllProductText = "ALL";
//            Assert.assertTrue(ActualAllProductText.contains(ExpectedAllProductText),
//                    ActualAllProductText + " should contain " + ExpectedAllProductText);
//            p.clickFirstProductViewProductButton();
//            //To verify all products visible, maybe take screenshot after calling AllProductsVisible method
//            String ActualProductNameText = p.verifyProductNameText();
//            String ExpectedProductNameText = "Blue";
//            Assert.assertTrue(ActualProductNameText.
//                    contains(ExpectedProductNameText),
//                    ActualProductNameText + " should contain " + ExpectedProductNameText);
//            String ActualCategoryText = p.verifyCategoryText();
//            String ExpectedCategoryText = "Category: Women > Tops";
//            Assert.assertTrue(ActualCategoryText.contains(ExpectedCategoryText),
//                    ActualCategoryText + " should contain " + ExpectedCategoryText);
//            String ActualPriceText = p.verifyPriceText();
//            String ExpectedPriceText = "Rs. 500";
//            Assert.assertTrue(ActualPriceText.contains(ExpectedPriceText),
//                    ActualPriceText + " should contain " + ExpectedPriceText);
//            String ActualAvailabilityText = p.verifyAvailabilityText();
//            String ExpectedAvailabilityText = "Availability:";
//            Assert.assertTrue(ActualAvailabilityText.contains(
//                    ExpectedAvailabilityText),
//                    ActualAvailabilityText + " should contain " + ExpectedAvailabilityText);
//            String ActualConditionText = p.verifyConditionText();
//            String ExpectedConditionText = "Condition:";
//            Assert.assertTrue(ActualConditionText.contains(ExpectedConditionText),
//                    ActualConditionText + " should contain " + ExpectedConditionText);
//            String ActualBrandText = p.verifyBrandText();
//            String ExpectedBrandText = "Brand:";
//            Assert.assertTrue(ActualBrandText.contains(ExpectedBrandText),
//                    ActualBrandText + " should contain " + ExpectedBrandText);
//        }
    @Test
    @Description(
            "This test case verifies that the product and product details page works as intended")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("PROD-001")
    public void VerifyProductPage() {
        openProductsPage();
        verifyAllProductsText();
        openFirstProductDetails();
        verifyProductName();
        verifyProductCategory();
        verifyProductPrice();
        verifyProductAvailability();
        verifyProductCondition();
        verifyProductBrand();
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

    @Step("Open first product details page")
    public void openFirstProductDetails() {
        p.clickFirstProductViewProductButton();
        AllureUtil.takeScreenshot(Browser, "First Product Details Page");
    }

    @Step("Verify product name contains 'Blue'")
    public void verifyProductName() {
        String actualText = p.verifyProductNameText();
        String expectedText = "Blue";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "Product Name Verified");
    }

    @Step("Verify product category is 'Category: Women > Tops'")
    public void verifyProductCategory() {
        String actualText = p.verifyCategoryText();
        String expectedText = "Category: Women > Tops";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "Product Category Verified");
    }

    @Step("Verify product price is 'Rs. 500'")
    public void verifyProductPrice() {
        String actualText = p.verifyPriceText();
        String expectedText = "Rs. 500";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "Product Price Verified");
    }

    @Step("Verify product availability contains 'Availability:'")
    public void verifyProductAvailability() {
        String actualText = p.verifyAvailabilityText();
        String expectedText = "Availability:";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "Product Availability Verified");
    }

    @Step("Verify product condition contains 'Condition:'")
    public void verifyProductCondition() {
        String actualText = p.verifyConditionText();
        String expectedText = "Condition:";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "Product Condition Verified");
    }

    @Step("Verify product brand contains 'Brand:'")
    public void verifyProductBrand() {
        String actualText = p.verifyBrandText();
        String expectedText = "Brand:";
        Assert.assertTrue(actualText.contains(expectedText),
                actualText + " should contain " + expectedText);
        AllureUtil.takeScreenshot(Browser, "Product Brand Verified");
    }

}
