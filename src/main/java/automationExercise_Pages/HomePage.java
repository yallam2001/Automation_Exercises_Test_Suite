package automationExercise_Pages;

import java.time.Duration;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver browser;
    private self_selenium mySel;
    private final By loginButtonLocator = By.cssSelector("i.fa-lock");
    private final By ProductsButtonLocator = By.cssSelector("i.card_travel");
    private final By CartButtonLocator = By.cssSelector(
            ".nav > li:nth-of-type(3)");
    private final By loggedInAsLocator = By.cssSelector("i.fa-user");
    private final By deleteAccountButton = By.cssSelector("i.fa-trash-o");
    private final By logoutButtonLocator = By.cssSelector(
            "ul.nav > li:nth-of-type(4) > a[style=\"color:brown;\"]");
    private final By ContactUsButtonLocator = By.cssSelector(
            "ul.nav > li:nth-of-type(8) > a > i.fa-envelope");
    private final By testCasesButtonLocator = By.cssSelector(
            "ul.nav > li:nth-of-type(5) > a > i.fa-list");
    private final By footerLocator = By.cssSelector("footer#footer");
    private final By subscriptionTextLocator = By.cssSelector(
            "div.row > div.col-sm-3 > div.single-widget > h2");
    private final By emailFieldLocator = By.cssSelector("input[type=\"email\"]");
    private final By arrowButton = By.cssSelector("button[type=\"submit\"]");
    private final By successfulSubscription = By.
            cssSelector("div.alert-success");
    private final By categoryTextLocator = By.cssSelector(
            "div.left-sidebar > h2");
    private final By womenExpandLocator = By.cssSelector("div.in");
    private final By womenCategoryButton = By.cssSelector(
            "h4.panel-title > a[href=\"#Women\"] > span.badge");
    private final By dressCategoryButton = By.cssSelector(
            "div#Women.in > div.panel-body > ul > li:nth-of-type(1) > a");
    private final By recommededItemsText = By.cssSelector(
            "div.recommended_items > h2");
    private final By addToCartRecommendedProduct = By.cssSelector(
            "div.recommended_items > div.slide > div.carousel-inner > div.item > div.col-sm-4 > div.product-image-wrapper > div.single-products > div.productinfo > a[data-product-id=\"1\"]");
    private final By waitForRecommendedProduct = By.cssSelector(
            "div.recommended_items > div.slide > div.carousel-inner > div.item > div.col-sm-4 > div.product-image-wrapper > div.single-products");
    private final By waitForAddToCartPopup = By.cssSelector("div.modal-content");
    private final By viewCartButton = By.cssSelector("div.modal-body > p > a");
    private final By scrollUpButton = By.cssSelector("a#scrollUp");
    private final By verifyScrollUpText = By.cssSelector(
            "div#slider-carousel > div.carousel-inner > div.item > div.col-sm-6 > h2");
    private final By scrollUpWithoutButton = By.cssSelector("section#slider");

    public HomePage(WebDriver browser) {
        this.browser = browser;
        this.mySel = new self_selenium(browser);
    }

    public String initializeBrowser() {
        mySel.initializeBrowser("https://automationexercise.com/");
        return mySel.getCurrentURL();
    }

    public String clickLoginButton() {
        mySel.Locator(loginButtonLocator).click();
        return mySel.getCurrentURL();
    }

    public String clickProductsButton() {
        mySel.Locator(ProductsButtonLocator).click();
        return mySel.getCurrentURL();
    }

    public String clickCartButton() {
        mySel.Locator(CartButtonLocator).click();
        return mySel.getCurrentURL();
    }

    public void closeTab() {
        mySel.closeCurrentWindow();
    }

    public void acceptAlert() {
        mySel.explicitWait_Alert();
        mySel.acceptAlert();
    }

    public void dismissAlert() {
        mySel.explicitWait_Alert();
        mySel.dismissAlert();
    }

    public String verifyLoggedInAsButton() {
        return mySel.getText(loggedInAsLocator);
    }

    public void clickDeleteAccountButton() {
        //mySel.hoverToElement(mySel.Locator(deleteAccountButton));
        mySel.click(deleteAccountButton);
    }

    public String clickLogoutButton() {
        mySel.click(logoutButtonLocator);
        return mySel.getCurrentURL();
    }

    public void clickContactUsButton() {
        mySel.click(ContactUsButtonLocator);
    }

    public void clickTestCasesButton() {
        mySel.click(testCasesButtonLocator);
    }

    public String verifySubscriptionText() {
        mySel.scrollToElement(footerLocator);
        mySel.explicitWait(subscriptionTextLocator);
        return mySel.getText(subscriptionTextLocator);
    }

    public String enterEmailSubscriptionField(String text) {
        mySel.sendKeys(text, emailFieldLocator);
        mySel.click(arrowButton);
        mySel.explicitWait(successfulSubscription);
        return mySel.getText(successfulSubscription);
    }

    public String verifyCategory() {
        return mySel.getText(categoryTextLocator);
    }

    public void clickWomenCategory() {
        mySel.click(womenCategoryButton);
        mySel.explicitWait(womenExpandLocator);
        mySel.explicitWait(dressCategoryButton);
        mySel.click(dressCategoryButton);
    }

    public String scrollToRecommendedItems() {
        mySel.scrollToElement(recommededItemsText);
        return mySel.getText(recommededItemsText);
    }

    public void addToCartRecommendedProduct() {
        mySel.explicitWait(waitForRecommendedProduct);
        mySel.click(addToCartRecommendedProduct);
        mySel.explicitWait(waitForAddToCartPopup);
        mySel.click(viewCartButton);
    }

    public String clickScrollUp() {
        mySel.click(scrollUpButton);
        mySel.explicitWait(verifyScrollUpText);
        return mySel.getText(verifyScrollUpText);
    }
    public String scrollUpWithoutButton()
    {
        mySel.scrollToElement(scrollUpWithoutButton);
        mySel.explicitWait(verifyScrollUpText);
        return mySel.getText(verifyScrollUpText);
    }
}
