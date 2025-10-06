package automationExercise_Pages;

import java.time.Duration;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateNewAccountPage
        extends HomePage {

    private self_selenium mySel;
    private Select select;
    private final By[] locators = {By.cssSelector("div.login-form > h2.title"), //Title
        By.cssSelector("label[for=\"id_gender1\"]"), //Male option
        By.cssSelector("label[for=\"id_gender2\"]"), //Female Option
        By.cssSelector("input.form-control#name"), //Name field
        By.cssSelector("input.form-control#email"), //Email field(disabled)
        By.cssSelector("input[type=\"password\"]"), //Password field
        By.cssSelector("select#days"), //days drop down
        By.cssSelector("select#months"), //months dropdown
        By.cssSelector("select#years"), //years dropdown
        By.cssSelector("input#newsletter"), //newsletter checkbox
        By.cssSelector("input#optin"), //special offer checkbox
        By.cssSelector("input#first_name"), //first_name field
        By.cssSelector("input#last_name"), //last_name field
        By.cssSelector("input#company"), //company field
        By.cssSelector("input[data-qa=\"address\"]"), //Address field
        By.cssSelector("select[data-qa=\"country\"]"), //Country field
        By.cssSelector("input[data-qa=\"state\"]"), //State field
        By.cssSelector("input[data-qa=\"city\"]"), //city field
        By.cssSelector("input[data-qa=\"zipcode\"]"), //zipcode field
        By.cssSelector("input[data-qa=\"mobile_number\"]"), //mobile number field
        By.cssSelector("button[type=\"submit\"][data-qa=\"create-account\"]"),//button
        By.cssSelector("h2[data-qa=\"account-created\"]"),
        By.cssSelector("input#name")
    };

    public CreateNewAccountPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }

    public String CreateNewAccountConfirmation() {
        //mySel.explicitWait(locators[0]);
        return mySel.getText(locators[0]);
    }

    public void FillForm(
            String password,
            String day,
            String month,
            String year,
            String first_name,
            String last_name,
            String company,
            String Address,
            String country,
            String State,
            String city,
            String zipcode,
            String mobile_number) {
        //mySel.explicitWait(locators[0]);
        mySel.selectRadioButton(locators[1]);
        //mySel.sendKeys(name, locators[22]);
        mySel.sendKeys(password, locators[5]);
        mySel.selectDropDownByValue(locators[6], day);
        mySel.selectDropDownByValue(locators[7], month);
        mySel.selectDropDownByValue(locators[8], year);
        mySel.check_check_box(locators[9]);
        mySel.check_check_box(locators[10]);
        mySel.sendKeys(first_name, locators[11]);
        mySel.sendKeys(last_name, locators[12]);
        mySel.sendKeys(company, locators[13]);
        mySel.sendKeys(Address, locators[14]);
        mySel.selectDropDownByContainsVisibleText(locators[15], country);
        mySel.sendKeys(State, locators[16]);
        mySel.sendKeys(city, locators[17]);
        mySel.sendKeys(zipcode, locators[18]);
        mySel.sendKeys(mobile_number, locators[19]);
        //mySel.implicitWait(Duration.ofSeconds(10));
    }

    public boolean isDisabled() {
        String emailText = mySel.getText(locators[4]);
        if (emailText.contains("@")) {
            return mySel.isDisabled(locators[4]);
        } else {
            return mySel.isEnabled(locators[4]);
        }
    }

    public void clickContinueButtonAfterInput() {
        mySel.Locator(locators[20]).click();
    }

    public String CreateAccountConfirm() {
        mySel.explicitWait(locators[21]);
        return mySel.getText(locators[21]);
    }

    public void enterMobileNumber(String mobile_number) {
        mySel.clearText(locators[19]);
        mySel.sendKeys(mobile_number, locators[19]);
    }

    public String getMobileNumberFieldValue() {
        return mySel.getText(locators[19]);
    }

    public void enterNameField(String name) {
        mySel.clearText(locators[22]);
        mySel.sendKeys(name, locators[22]);
    }

    public void tryEditNameField(String name) {
        mySel.clearText(locators[22]);
        mySel.sendKeys(name, locators[22]);
    }

    public String getNameFieldValue() {
        return mySel.getText(locators[22]);
    }
}
