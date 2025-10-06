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
public class PaymentPage
        extends HomePage {

    private self_selenium mySel;

    public PaymentPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }
    private final By NameOnCardFieldLocator = By.cssSelector(
            "input[data-qa=\"name-on-card\"]");
    private final By CardNumberFieldLocator = By.cssSelector(
            "input[data-qa=\"card-number\"]");
    private final By cvcFieldLocator = By.cssSelector("input[data-qa=\"cvc\"]");
    private final By expiryMonthFieldLocator = By.cssSelector(
            "input[data-qa=\"expiry-month\"]");
    private final By expiryYearFieldLocator = By.cssSelector(
            "input[data-qa=\"expiry-year\"]");
    private final By PayAndOrderButton = By.cssSelector(
            "button[data-qa=\"pay-button\"]");

    public void enterCardDetails(String cardName,
            String cardNumber,
            String cvv,
            String expiryMonth,
            String expiryYear) {
        mySel.sendKeys(cardName, NameOnCardFieldLocator);
        mySel.sendKeys(cardNumber, CardNumberFieldLocator);
        mySel.sendKeys(cvv, cvcFieldLocator);
        mySel.sendKeys(expiryMonth, expiryMonthFieldLocator);
        mySel.sendKeys(expiryYear, expiryYearFieldLocator);
        mySel.click(PayAndOrderButton);
    }
}
