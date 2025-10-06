package automationExercise_Pages;

import java.time.Duration;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUp_Login_Page
        extends HomePage {

    private self_selenium mySel;
    private final By newUserSignUpTextLocator = By.className("signup-form");
    private final By nameFieldLocator = By.cssSelector("input[data-qa=\"signup-name\"]");
    private final By emailAddressFieldLocator = By.cssSelector("input[data-qa=\"signup-email\"]");
    private final By signUpButtonLocator = By.cssSelector("button[data-qa=\"signup-button\"]");
    private final By emailAlreadyExistsLocator = By.cssSelector("p[style=\"color: red;\"]");
    private final By loginToYourAccountTextLocator = By.cssSelector("div.login-form > h2");
    private final By enterYourEmailFieldLocator = By.cssSelector("input[data-qa=\"login-email\"]");
    private final By enterYourPasswordLocator = By.cssSelector("input[data-qa=\"login-password\"]");
    private final By loginButtonLocator = By.cssSelector("button[data-qa=\"login-button\"]");
    private final By incorrectEmailAndPasswordTextLocator = By.cssSelector("p[style=\"color: red;\"]");
    public SignUp_Login_Page(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }

    public String newUserSignUpText() {
        mySel.explicitWait(newUserSignUpTextLocator);
        System.out.println(mySel.getText(newUserSignUpTextLocator));
        return mySel.getText(newUserSignUpTextLocator);
    }

    public void enterNameField(String text) {
        mySel.sendKeys(text, nameFieldLocator);
    }

    public void enterEmailAddressField(String text) {
        mySel.sendKeys(text, emailAddressFieldLocator);
    }

    public void clickSignUpButton() {
        mySel.click(signUpButtonLocator);
    }

    public String emailAlreadyExists() {
        mySel.clearText(nameFieldLocator);
        mySel.clearText(emailAddressFieldLocator);
        mySel.explicitWait(emailAlreadyExistsLocator);
        return mySel.getText(emailAlreadyExistsLocator);
    }
    public String loginToYourAccountText()
    {
        return mySel.getText(loginToYourAccountTextLocator);
    }
    public void loginProcess(String email, String password)
    {
        mySel.sendKeys(email, enterYourEmailFieldLocator);
        mySel.sendKeys(password, enterYourPasswordLocator);
        mySel.click(loginButtonLocator);
    }
    public String incorrectEmailAndPasswordText()
    {
        return mySel.getText(incorrectEmailAndPasswordTextLocator);
    }
}
