package automationExercise_Pages;

import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage
        extends HomePage {

    private self_selenium mySel;
    private final By contactUsTextLocator = By.cssSelector("div.contact-form > h2.title");
    private final By nameFieldLocator = By.cssSelector("input[data-qa=\"name\"]");
    private final By emailFieldLocator = By.cssSelector("input[data-qa=\"email\"]");
    private final By subjectFieldLocator = By.cssSelector("input[data-qa=\"subject\"]");
    private final By yourMessageHereFieldLocator = By.cssSelector("textarea[data-qa=\"message\"]");
    private final By uploadFileButtonLocator = By.cssSelector("input[type=\"file\"]");
    private final By submitButtonLocator = By.cssSelector("input[data-qa=\"submit-button\"]");
    private final By successTextLocator = By.cssSelector("div.status");
    private final By homeButtonLocator = By.cssSelector("div#form-section > a.btn");

    public ContactUsPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }

    public String verifyContactUsPage() {
        return mySel.getText(contactUsTextLocator);
    }

    public void ContactUsProcess(String name,
            String email,
            String subject,
            String message,
            String filePath) {
        mySel.sendKeys(name, nameFieldLocator);
        mySel.sendKeys(email, emailFieldLocator);
        mySel.sendKeys(subject, subjectFieldLocator);
        mySel.sendKeys(message, yourMessageHereFieldLocator);
        mySel.sendKeys(filePath, uploadFileButtonLocator);
        mySel.click(submitButtonLocator);
        mySel.acceptAlert();
    }

    public String verifyContactUsSuccess() {
        return mySel.getText(successTextLocator);
    }

    public void clickHomeButton() {
        mySel.click(homeButtonLocator);
    }
}
