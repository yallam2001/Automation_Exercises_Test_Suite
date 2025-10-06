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
public class ViewCategoryProducts {

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
//            "This test case verifies viewing the right product by category")
//    @Severity(SeverityLevel.NORMAL)
//    @Owner("Yahia Allam")
//    @Link(name = "Website", url = "https://automationexercise.com/")
//    @Issue("CAT-001")
//    public void ViewProductsByCategory() {
//        Assert.assertTrue(h.verifyCategory().toLowerCase().contains("category"),
//                h.verifyCategory() + " should contain category");
//        h.clickWomenCategory();
//        System.out.println(p.verifyWomenCategory());
//        String ActualURL = "https://automationexercise.com/category_products/1";
//        Assert.assertEquals(ActualURL,
//                "https://automationexercise.com/category_products/1");
//        Assert.assertTrue(p.verifyWomenCategory().toLowerCase().
//                contains("dress"),
//                p.verifyWomenCategory() + " should contain women");
//        p.clickMenCategory();
//        Assert.assertTrue(p.verifyMenTshirtsText().toLowerCase().contains(
//                "tshirts"), p.verifyMenTshirtsText() + " should contain tshirts");
//
//    }
    @Test
    @Description("This test case verifies viewing the right product by category")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("CAT-001")
    public void ViewProductsByCategory() {
        verifyCategorySectionVisible();
        selectWomenCategory();
        verifyWomenCategoryProducts();
        selectMenCategory();
        verifyMenCategoryProducts();
    }

    @Step("Verify category section is visible")
    public void verifyCategorySectionVisible() {
        String actualText = h.verifyCategory();
        Assert.assertTrue(actualText.toLowerCase().contains("category"),
                actualText + " should contain category");
        AllureUtil.takeScreenshot(Browser, "Category Section Verified");
    }

    @Step("Select 'Women' category")
    public void selectWomenCategory() {
        h.clickWomenCategory();
        AllureUtil.takeScreenshot(Browser, "Women Category Selected");
    }

    @Step("Verify products for 'Women' category")
    public void verifyWomenCategoryProducts() {
        String currentURL = mySel.getCurrentURL(); // Assuming you have a method to get current URL
        Assert.assertEquals(currentURL,
                "https://automationexercise.com/category_products/1",
                "URL after selecting Women category is incorrect");
        String actualText = p.verifyWomenCategory();
        Assert.assertTrue(actualText.toLowerCase().contains("dress"),
                actualText + " should contain dress");
        AllureUtil.takeScreenshot(Browser, "Women Category Products Verified");
    }

    @Step("Select 'Men' category")
    public void selectMenCategory() {
        p.clickMenCategory();
        AllureUtil.takeScreenshot(Browser, "Men Category Selected");
    }

    @Step("Verify products for 'Men' category")
    public void verifyMenCategoryProducts() {
        String actualText = p.verifyMenTshirtsText();
        Assert.assertTrue(actualText.toLowerCase().contains("tshirts"),
                actualText + " should contain tshirts");
        AllureUtil.takeScreenshot(Browser, "Men Category Products Verified");
    }

}
