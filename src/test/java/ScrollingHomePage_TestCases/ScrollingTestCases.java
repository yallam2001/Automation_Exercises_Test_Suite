/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package ScrollingHomePage_TestCases;

import Authentication_TestCases.AllureUtil;
import automationExercise_Pages.HomePage;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 *
 * @author y_all
 */
public class ScrollingTestCases {

    private HomePage h;
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
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Browser = new EdgeDriver();
        h = new HomePage(Browser);
        String CurrentURL = h.initializeBrowser();
        String ExpectedURL = "https://automationexercise.com/";
        Assert.assertTrue(CurrentURL.contains(ExpectedURL),
                CurrentURL + " should contain " + ExpectedURL);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        Browser.quit();

    }

    @Test
    @Description("This test case examines scrolling up using scroll up arrow")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("SCR-001")
    public void ScrollingUpWithButton_TC() {
        verifySubscriptionSection();
        scrollUpUsingButton();
    }

    @Step("Verify subscription section is visible")
    public void verifySubscriptionSection() {
        String actual = h.verifySubscriptionText().toLowerCase();
        Assert.assertTrue(actual.contains("subscription"),
                actual + " should contain subscription");
        AllureUtil.takeScreenshot(Browser, "Subscription Section Verified");
    }

    @Step("Scroll up using scroll-up arrow and verify top section contains 'full-fledged'")
    public void scrollUpUsingButton() {
        String actualTopText = h.clickScrollUp().toLowerCase();
        Assert.assertTrue(actualTopText.contains("full-fledged"),
                "Top section text should contain 'full-fledged'");
        AllureUtil.takeScreenshot(Browser, "Scrolled Up Using Button");
    }

    @Test
    @Description(
            "This test case examines scrolling up without using scroll up arrow")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Yahia Allam")
    @Link(name = "Website", url = "https://automationexercise.com/")
    @Issue("SCR-002")
    public void ScrollingUpWithoutButton_TC() {
        verifySubscriptionSection();
        scrollUpWithoutButton();
    }

    @Step("Scroll up without using scroll-up arrow and verify top section contains 'full-fledged'")
    public void scrollUpWithoutButton() {
        String actualTopText = h.scrollUpWithoutButton().toLowerCase();
        Assert.assertTrue(actualTopText.contains("full-fledged"),
                "Top section text should contain 'full-fledged'");
        AllureUtil.takeScreenshot(Browser, "Scrolled Up Without Button");
    }
}
