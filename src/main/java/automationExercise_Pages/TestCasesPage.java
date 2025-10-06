package automationExercise_Pages;

import mySeleniumFramework.self_selenium;
import org.openqa.selenium.WebDriver;

public class TestCasesPage
        extends HomePage {

    private self_selenium mySel;

    public TestCasesPage(WebDriver browser) {
        super(browser);
        mySel = new self_selenium(browser);
    }
    public String verifyTestCasePage()
    {
        return mySel.getCurrentURL();
    }
}
