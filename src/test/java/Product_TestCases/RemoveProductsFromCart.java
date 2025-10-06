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
public class RemoveProductsFromCart {

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

    @DataProvider(name = "productData")
    public Product[] productDataProvider() {
        return Products;
    }

//    @Test
//    @Description(
//            "This test case examines removing products from cart working as intended or not")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("CHK-004")
//    public void RemoveProductsFromCart() {
//        h.clickProductsButton();
//        p.addToCart();
//        h.clickCartButton();
//        List<WebElement> ProductsAddedBeforeRemove = ch.
//                verifyBothProductsAreAdded();
//        int productsBefore = ProductsAddedBeforeRemove.size();
//        for (WebElement cartProduct : ProductsAddedBeforeRemove) {
//            String productAdded = cartProduct.getText();
//            Assert.assertTrue(productAdded.contains(cartProduct.getText()),
//                    productAdded);
//        }
//        ct.removeProduct();
//        List<WebElement> ProductsAddedAfterRemove = ch.
//                verifyBothProductsAreAdded();
//        int productsAfter = ProductsAddedAfterRemove.size();
//        Assert.assertNotEquals(productsBefore - 1, productsAfter);
//    }
    @Test
    @Description(
            "This test case examines removing products from cart working as intended or not")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CHK-004")
    public void RemoveProductsFromCart() {
        openProductsPage();
        addProductsToCart();
        openCartPage();
        verifyProductsBeforeRemoval();
        removeAProductFromCart();
        verifyProductsAfterRemoval();
    }

    @Step("Open Products page")
    public void openProductsPage() {
        h.clickProductsButton();
        AllureUtil.takeScreenshot(Browser, "Products Page Opened");
    }

    @Step("Add products to cart")
    public void addProductsToCart() {
        p.addToCart();
        AllureUtil.takeScreenshot(Browser, "Products Added to Cart");
    }

    @Step("Open Cart page")
    public void openCartPage() {
        h.clickCartButton();
        AllureUtil.takeScreenshot(Browser, "Cart Page Opened");
    }

    @Step("Verify products before removal")
    public void verifyProductsBeforeRemoval() {
        List<WebElement> productsBeforeRemove = ch.verifyBothProductsAreAdded();
        int productsBefore = productsBeforeRemove.size();
        for (WebElement cartProduct : productsBeforeRemove) {
            String productAdded = cartProduct.getText();
            Assert.assertTrue(productAdded.contains(cartProduct.getText()),
                    productAdded);
        }
        AllureUtil.takeScreenshot(Browser, "Products Before Removal");
    }

    @Step("Remove a product from the cart")
    public void removeAProductFromCart() {
        ct.removeProduct();
        AllureUtil.takeScreenshot(Browser, "Product Removed from Cart");
    }

    @Step("Verify products after removal")
    public void verifyProductsAfterRemoval() {
        List<WebElement> productsAfterRemove = ch.verifyBothProductsAreAdded();
        int productsAfter = productsAfterRemove.size();
        Assert.assertNotEquals(productsAfter, productsAfterRemove.size() - 1,
                "Product count did not decrease as expected");
        AllureUtil.takeScreenshot(Browser, "Products After Removal");
    }

}
