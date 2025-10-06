/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automationExercise_Pages;

import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author y_all
 */
public class AccountDeletedPage
        extends HomePage {

    private self_selenium mySel;
    private final By AccountDeletedTextLocator = By.cssSelector("h2[data-qa=\"account-deleted\"]");
    private final By continueButtonLocator = By.cssSelector("a[data-qa=\"continue-button\"]");
    
    public AccountDeletedPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }
    public String confirmAccountDeleted()
    {
        return mySel.getText(AccountDeletedTextLocator);
    }
    public void clickContinueButton()
    {
        mySel.Locator(continueButtonLocator).click();
    }
}
