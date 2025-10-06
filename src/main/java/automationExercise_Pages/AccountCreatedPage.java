package automationExercise_Pages;

import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage
        extends HomePage {
    
    private self_selenium mySel;
    private final By continueButtonLocator = By.cssSelector("a[data-qa=\"continue-button\"]");

    public AccountCreatedPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }
    public void clickContinueButton()
    {
        mySel.Locator(continueButtonLocator).click();
    }
}
