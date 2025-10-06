package automationExercise_Pages;

import java.util.ArrayList;
import java.util.List;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage
        extends HomePage {

    private self_selenium mySel;

    public CheckoutPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }

    private final By deliveryAddressLocator = By.cssSelector(
            "ul#address_delivery");
    private final By deliveryAddressDetailsLocator = By.cssSelector(
            "ul#address_delivery > li");
    private final By billingAddressLocator = By.
            cssSelector("ul#address_invoice");
    private final By billingAddressDetailsLocator = By.cssSelector(
            "ul#address_invoice > li");
    private final By reviewOrderTextLocator = By.
            cssSelector("div.step-one > h2");
    private final By productsAdded = By.cssSelector(
            "td.cart_description h4 a");
    private final By productsPrices = By.cssSelector("td.cart_price");
    private final By productsQuantities = By.cssSelector(
            "td.cart_quantity button");
    private final By productsTotalPrices = By.cssSelector("td.cart_total p");
    private final By descriptionCommentLocator = By.cssSelector(
            "textarea.form-control");
    private final By placeOrderButton = By.cssSelector("a.check_out");
    private final By CheckoutSuccessLocator = By.cssSelector(
            "div#success_message > div.alert");
    private final By downloadInvoiceButton = By.cssSelector("a.check_out");
    private final By clickContinueButton = By.cssSelector("a[data-qa=\"continue-button\"]");
    public CheckoutHelperClass getDeliveryAddress() {
        mySel.explicitWait(deliveryAddressLocator);
        List<WebElement> lines = mySel.multipleLocators(
                deliveryAddressDetailsLocator);
        return mapAddress(lines);
    }

    public CheckoutHelperClass getBillingAddress() {
        mySel.explicitWait(billingAddressLocator);
        List<WebElement> lines = mySel.multipleLocators(
                billingAddressDetailsLocator);
        return mapAddress(lines);
    }

    private CheckoutHelperClass mapAddress(List<WebElement> lines) {
        CheckoutHelperClass addr = new CheckoutHelperClass();
        addr.name = lines.get(1).getText();
        addr.company = lines.get(2).getText();
        addr.address = lines.get(3).getText();
        addr.cityStateZip = lines.get(4).getText();
        addr.country = lines.get(5).getText();
        addr.mobile = lines.get(6).getText();
        return addr;
    }

    public String verifyReviewYourOrderText() {
        mySel.explicitWait(reviewOrderTextLocator);
        return mySel.getText(reviewOrderTextLocator);
    }

    public List<WebElement> verifyBothProductsAreAdded() {
        mySel.explicitWait(productsAdded);
        System.out.println("Both products appeared");
        return mySel.multipleLocators(productsAdded);
    }

    public List<String> getProductNames() {
        return mySel.multipleLocators(productsAdded).stream().map(
                WebElement::getText).toList();
    }

    public List<String> getProductPrices() {
        return mySel.multipleLocators(productsPrices).stream().map(
                WebElement::getText).toList();
    }

    public List<String> getProductQuantities() {
        return mySel.multipleLocators(productsQuantities).stream().map(
                WebElement::getText).toList();
    }

    public List<String> getProductTotalPrices() {
        return mySel.multipleLocators(productsTotalPrices).stream().map(
                WebElement::getText).toList();
    }

    public void placeOrder(String text) {
        mySel.sendKeys(text, descriptionCommentLocator);
        mySel.click(placeOrderButton);
    }

    public String verifyOrderSuccess() {
        //mySel.explicitWait(CheckoutSuccessLocator);
        return mySel.getText(CheckoutSuccessLocator);
    }
    public void clickDownloadInvoice()
    {
        mySel.click(downloadInvoiceButton);
        mySel.click(clickContinueButton);
    }
}
