package automationExercise_Pages;

import java.util.List;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage
        extends HomePage {

    private self_selenium mySel;
    private final By productsAdded = By.cssSelector(
            "td.cart_description h4 a");
    private final By productsPrices = By.cssSelector("td.cart_price");
    private final By productsQuantities = By.cssSelector(
            "td.cart_quantity button");
    private final By productsTotalPrices = By.cssSelector("td.cart_total p");
    private final By proceedToCheckoutButton = By.cssSelector("a.check_out");
    private final By CheckoutPopUpLocator = By.cssSelector("div.modal-content");
    private final By registerButton = By.cssSelector("div.modal-body > p > a");
    private final By removeProductButton = By.cssSelector("td.cart_delete > a.cart_quantity_delete:nth-of-type(1)");

    public CartPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
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
    public void clickProceedToCheckout()
    {
        mySel.click(proceedToCheckoutButton);
        mySel.explicitWait(CheckoutPopUpLocator);
        mySel.click(registerButton);
    }
    public void clickProceedToCheckoutAfterRegister()
    {
        mySel.click(proceedToCheckoutButton);
    }
    public void removeProduct()
    {
        mySel.click(removeProductButton);
    }
}
