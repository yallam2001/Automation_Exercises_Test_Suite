/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automationExercise_Pages;

import java.util.List;
import mySeleniumFramework.self_selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author y_all
 */
public class ProductsPage
        extends HomePage {

    private final self_selenium mySel;
    private final By AllProductsTextLocator = By.cssSelector(
            "div.features_items > h2.title");
    private final By ProductsVisibleLocator = By.cssSelector(
            "div.productinfo > a[data-product-id=\"43\"]");
    private final By FirstProductVisibleLocator = By.cssSelector(
            "div.productinfo > a[data-product-id=\"1\"]");
    private final By SecondProductVisibleLocator = By.cssSelector(
            "div.productinfo > a[data-product-id=\"2\"]");
    private final By ThirdProductVisibleLocator = By.cssSelector(
            "div.productinfo > a[data-product-id=\"3\"]");
    private final By FourthProductVisibleLocator = By.cssSelector(
            "div.productinfo > a[data-product-id=\"4\"]");
    private final By viewProductButton = By.xpath(
            "/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");
    private final By productNameLocator = By.cssSelector(
            "div.product-information > h2");
    private final By categoryLocator = By.cssSelector(
            "div.product-details > div.col-sm-7 > div.product-information > p:nth-of-type(1)");
    private final By priceTextLocator = By.cssSelector(
            "div.product-details > div.col-sm-7 > div.product-information > span > span");
    private final By availabilityTextLocator = By.cssSelector(
            "div.product-details > div.col-sm-7 > div.product-information > p:nth-of-type(2)");
    private final By conditionTextLocator = By.cssSelector(
            "div.product-details > div.col-sm-7 > div.product-information > p:nth-of-type(3)");
    private final By brandTextLocator = By.cssSelector(
            "div.product-details > div.col-sm-7 > div.product-information > p:nth-of-type(4)");
    private final By searchBarLocator = By.cssSelector("input#search_product");
    private final By searchButton = By.cssSelector("button#submit_search");
    private final By searchedProductText = By.cssSelector(
            "div.features_items > h2");
    private final By searchedProductsLocator = By.cssSelector(
            "div.features_items > div.col-sm-4 > div.product-image-wrapper");
    private final By hoverToAddToCartButton = By.cssSelector(
            "div.overlay-content > a.add-to-cart");
    private final By hoverAddToCartButtonFirstProduct = By.cssSelector(
            "div.col-sm-4:nth-of-type(2) > div.product-image-wrapper > div.single-products > div.product-overlay > div.overlay-content > a");
    private final By hoverAddToCartButtonSecondProduct = By.cssSelector(
            "div.col-sm-4:nth-of-type(3) > div.product-image-wrapper > div.single-products > div.product-overlay > div.overlay-content > a");
    private final By hoverAddToCartButtonThirdProduct = By.cssSelector(
            "div.col-sm-4:nth-of-type(4) > div.product-image-wrapper > div.single-products > div.product-overlay > div.overlay-content > a");
    private final By hoverAddToCartButtonFourthProduct = By.cssSelector(
            "div.col-sm-4:nth-of-type(5) > div.product-image-wrapper > div.single-products > div.product-overlay > div.overlay-content > a");
    private final By continueShoppingButton = By.cssSelector(
            "button.close-modal");
    private final By productAddedPopupLocator = By.cssSelector(
            "div.modal-content");
    private final By viewCartButton = By.cssSelector("p.text-center > a");
    private final By increaseQuantityLocator = By.cssSelector("input#quantity");
    private final By AddToCartButton = By.cssSelector("button[type=\"button\"]");
    private final By addedToCartPopUpLocator = By.cssSelector(
            "div#cartModal > div.modal-confirm > div.modal-content");
    private final By viewCartButtonInsideProduct = By.cssSelector(
            "div.modal-content > div.modal-body > p > a");
    private final By womenCategoryDressText = By.cssSelector("h2.title");
    private final By menCategoryButton = By.cssSelector(
            "h4.panel-title > a[href=\"#Men\"]");
    private final By menCollapseLocator = By.cssSelector(
            "div#Men > div.panel-body > ul");
    private final By tshirtsCategoryButton = By.cssSelector(
            "div#Men > div.panel-body > ul > li:nth-of-type(1) > a");
    private final By menTshirtsCategoryText = By.cssSelector("h2.title");
    private final By brandCategoryText = By.cssSelector(
            "div.brands_products > h2");
    private final By poloBrandButton = By.cssSelector(
            "div.brands-name > ul > li:nth-of-type(1) > a");
    private final By brandTitleText = By.cssSelector("div.features_items > h2");
    private final By SecondBrandButton = By.cssSelector(
            "div.brands-name > ul > li:nth-of-type(6) > a");
    private final By searchedProducts = By.cssSelector(
            "div.product-image-wrapper > div.single-products");
    private final By hoverToSearchedProduct = By.cssSelector(
            "div.col-sm-4:nth-of-type(2) > div.product-image-wrapper > div.single-products");
    private final By writeYourReviewText = By.cssSelector(
            "ul.nav > li.active > a[data-toggle=\"tab\"]");
    private final By nameReviewFieldLocator = By.cssSelector(
            "form#review-form > span > input#name");
    private final By emailReviewFieldLocator = By.cssSelector(
            "form#review-form > span > input#email");
    private final By writeYourReviewTextArea = By.cssSelector(
            "form#review-form > textarea");
    private final By clickReviewButton = By.cssSelector("button#button-review");
    private final By verifySuccessReviewAlert = By.cssSelector(
            "div.alert-success > span");

    public ProductsPage(WebDriver browser) {
        super(browser);
        this.mySel = new self_selenium(browser);
    }

    public String verifyProductPage() {
        return mySel.getText(AllProductsTextLocator);
    }

    public void verifyAllProductsVisible() {
        mySel.scrollToElement(ProductsVisibleLocator);
    }

    public void clickFirstProductViewProductButton() {
        mySel.click(viewProductButton);
    }

    public String verifyProductNameText() {
        return mySel.getText(productNameLocator);
    }

    public String verifyCategoryText() {
        return mySel.getText(categoryLocator);
    }

    public String verifyPriceText() {
        return mySel.getText(priceTextLocator);
    }

    public String verifyAvailabilityText() {
        return mySel.getText(availabilityTextLocator);
    }

    public String verifyConditionText() {
        return mySel.getText(conditionTextLocator);
    }

    public String verifyBrandText() {
        return mySel.getText(brandTextLocator);
    }

    public List<WebElement> searchProduct(String text) {
        mySel.sendKeys(text, searchBarLocator);
        mySel.click(searchButton);
        mySel.explicitWait(searchedProductsLocator);
        return mySel.multipleLocators(searchedProductsLocator);
    }

    public String verifySearchedProductText() {
        return mySel.getText(searchedProductText);
    }

    public void hoverToProduct() {
        //mySel.scrollToElement(FirstProductVisibleLocator);
        mySel.hoverToElement(mySel.Locator(FirstProductVisibleLocator));
        mySel.explicitWait(hoverAddToCartButtonFirstProduct);
        mySel.click(hoverAddToCartButtonFirstProduct);
        mySel.explicitWait(productAddedPopupLocator);
        mySel.click(continueShoppingButton);
        //mySel.scrollToElement(SecondProductVisibleLocator);
        mySel.hoverToElement(mySel.Locator(SecondProductVisibleLocator));
        mySel.explicitWait(hoverAddToCartButtonSecondProduct);
        mySel.click(hoverAddToCartButtonSecondProduct);
        mySel.explicitWait(productAddedPopupLocator);
        mySel.click(viewCartButton);
    }

    public void increaseQuantity(String quantity) {
        mySel.clearText(increaseQuantityLocator);
        mySel.sendKeys(quantity, increaseQuantityLocator);
        mySel.click(AddToCartButton);
        mySel.explicitWait(addedToCartPopUpLocator);
        mySel.click(viewCartButtonInsideProduct);
    }

    public void addToCart() {
        //mySel.scrollToElement(FirstProductVisibleLocator);
        mySel.hoverToElement(mySel.Locator(FirstProductVisibleLocator));
        mySel.explicitWait(hoverAddToCartButtonFirstProduct);
        mySel.click(hoverAddToCartButtonFirstProduct);
        //mySel.explicitWait(productAddedPopupLocator);
        mySel.click(continueShoppingButton);
        //mySel.scrollToElement(SecondProductVisibleLocator);
        mySel.hoverToElement(mySel.Locator(SecondProductVisibleLocator));
        mySel.explicitWait(hoverAddToCartButtonSecondProduct);
        mySel.click(hoverAddToCartButtonSecondProduct);
        //mySel.explicitWait(productAddedPopupLocator);
        mySel.click(continueShoppingButton);
        mySel.hoverToElement(mySel.Locator(ThirdProductVisibleLocator));
        mySel.explicitWait(hoverAddToCartButtonThirdProduct);
        mySel.click(hoverAddToCartButtonThirdProduct);
        //mySel.explicitWait(productAddedPopupLocator);
        mySel.click(continueShoppingButton);
        mySel.hoverToElement(mySel.Locator(FourthProductVisibleLocator));
        mySel.explicitWait(hoverAddToCartButtonFourthProduct);
        mySel.click(hoverAddToCartButtonFourthProduct);
        //mySel.explicitWait(productAddedPopupLocator);
        mySel.click(continueShoppingButton);
    }

    public String verifyWomenCategory() {
        mySel.explicitWait(womenCategoryDressText);
        return mySel.getText(womenCategoryDressText);
    }

    public void clickMenCategory() {
        mySel.click(menCategoryButton);
        mySel.explicitWait(menCollapseLocator);
        mySel.click(tshirtsCategoryButton);
    }

    public String verifyMenTshirtsText() {
        return mySel.getText(menTshirtsCategoryText);
    }

    public String verifyBrandCategoryText() {
        mySel.explicitWait(brandCategoryText);
        return mySel.getText(brandCategoryText);
    }

    public void clickBrandCategory() {
        mySel.click(poloBrandButton);
    }

    public String __verifyBrandCategoryText() {
        mySel.explicitWait(brandTitleText);
        return mySel.getText(brandTitleText);
    }

    public String selectAnotherBrand() {
        mySel.click(SecondBrandButton);
        mySel.explicitWait(brandTitleText);
        return mySel.getText(brandTitleText);
    }

    public void addToCartSearchedProducts() {
        mySel.explicitWait(hoverToSearchedProduct);
        List<WebElement> productsSearched = mySel.multipleLocators(
                hoverToSearchedProduct);
        for (WebElement productSearched : productsSearched) {
            mySel.hoverToElement(productSearched);
            //mySel.explicitWait(hoverToAddToCartButton);
            mySel.click(hoverToAddToCartButton);
            //mySel.explicitWait(continueShoppingButton);
            mySel.click(continueShoppingButton);
        }
    }

    public void clickAddToCartSearchedProduct() {

        mySel.hoverToElement(mySel.Locator(searchedProducts));
        mySel.explicitWait(hoverAddToCartButtonFirstProduct);
        mySel.click(hoverAddToCartButtonFirstProduct);
        //mySel.explicitWait(productAddedPopupLocator);
        mySel.click(continueShoppingButton);
    }

    public String verifyWriteYourReviewText() {
        return mySel.getText(writeYourReviewText);
    }

    public String fillReview(String name,
            String email,
            String review) {
        mySel.sendKeys(name, nameReviewFieldLocator);
        mySel.sendKeys(email, emailReviewFieldLocator);
        mySel.sendKeys(review, writeYourReviewTextArea);
        mySel.click(clickReviewButton);
        return mySel.getText(verifySuccessReviewAlert);
    }
}
