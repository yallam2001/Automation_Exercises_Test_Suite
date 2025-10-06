package mySeleniumFramework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class self_selenium {

    private WebDriver browser;
    private final WebDriverWait wait;
    private Actions a;
    private Select select;
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public WebDriver getBrowser() {
        return this.browser;
    }

    public void setBrowser(WebDriver aBrowser) {
        this.browser = aBrowser;
    }

    public Actions getA() {
        return this.a;
    }

    public void setA(Actions aA) {
        this.a = aA;
    }

    public Select getSelect() {
        return this.select;
    }

    public void setSelect(Select aSelect) {
        this.select = aSelect;
    }

    public self_selenium(WebDriver browser) {
        this.browser = browser;
        this.a = new Actions(browser);
        this.wait = new WebDriverWait(browser, DEFAULT_TIMEOUT);
    }

    public void initializeBrowser(String URL) {
        getBrowser().navigate().to(URL);
        getBrowser().manage().window().maximize();
    }

    public void setWindowSize(int width,
            int height) {
        getBrowser().manage().window().setSize(
                new org.openqa.selenium.Dimension(width, height));
    }

    public WebElement Locator(By loc) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(loc));
        } catch (Exception e) {
            throw new RuntimeException("No Such element " + loc, e);
        }
    }

    public List<WebElement> multipleLocators(By loc) {
        List<WebElement> elements = new ArrayList<>();
        elements.addAll(getBrowser().findElements(loc));
        return elements;
    }

    public void implicitWait(Duration time) {
        getBrowser().manage().timeouts().implicitlyWait(time);
    }

    public void explicitWait(By loc) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
    }

    public void explicitWait_Alert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void fluentWait(By loc,
            Duration time,
            Duration pollingTime,
            String timeoutMessage,
            Class<? extends Throwable> exceptionType) {
        Wait<WebDriver> fluentWait = new FluentWait<>(getBrowser())
                .withTimeout(time) // Maximum wait time
                .pollingEvery(pollingTime) // Check every 500ms
                .withMessage(timeoutMessage)
                .ignoring(exceptionType);       // Ignore this exception during polling
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(loc));
    }

    public String getPageTitle() {
        return getBrowser().getTitle();
    }

    public String getCurrentURL() {
        return getBrowser().getCurrentUrl();
    }

    public void click(By loc) {
        explicitWait(loc);
        Locator(loc).click();
    }

    public void rightClick(By loc) {
        setA(new Actions(getBrowser()));
        getA().contextClick(Locator(loc)).perform();
    }

    public void sendKeys(String text,
            By loc) {
        Locator(loc).sendKeys(text);
    }

    public String getText(By loc) {
        return Locator(loc).getText();
    }

    public void selectDropDownByValue(By loc,
            String visibleText) {
        setSelect(new Select(Locator(loc)));
        getSelect().selectByVisibleText(visibleText);
    }

    public void selectDropDownByValue(String value,
            By loc) {
        setSelect(new Select(Locator(loc)));
        getSelect().selectByValue(value);
    }

    public void selectDropDownByIndex(By loc,
            int index) {
        setSelect(new Select(Locator(loc)));
        getSelect().selectByIndex(index);
    }

    public void selectDropDownByContainsVisibleText(By loc,
            String text) {
        setSelect(new Select(Locator(loc)));
        getSelect().selectByContainsVisibleText(text);
    }

    public void dragAndDrop(By sourceLocator,
            By targetLocator) {
        setA(new Actions(getBrowser()));
        getA().dragAndDrop(Locator(sourceLocator), Locator(targetLocator)).
                perform();
    }

    public void check_check_box(By loc) {
        if (!(Locator(loc).isSelected())) {
            Locator(loc).click();
        }
    }

    public void uncheck_check_box(By loc) {
        if (Locator(loc).isSelected()) {
            Locator(loc).click();
        }
    }

    public void selectRadioButton(By loc) {
        if (!(Locator(loc).isSelected())) {
            Locator(loc).click();
        }
    }

    public void switchToWindowByTitle(String windowTitle) {
        getBrowser().switchTo().window(windowTitle);
    }

    public void switchToWindowByHandle(String WindowHandle) {
        // Store the original window handle
        String originalWindow = getBrowser().getWindowHandle();

        // Click the link that opens a new window
        getBrowser().findElement(By.linkText("Click Here")).click();

        // Wait for the new window to appear and switch to it
        Set<String> allWindows = getBrowser().getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                getBrowser().switchTo().window(windowHandle);
                break;
            }
        }

        // Now we are in the new window/tab - print its title
        System.out.println("New window title: " + getBrowser().getTitle());

        // Optionally, close the new window and switch back to the original window
        getBrowser().close(); // closes current window

        // Switch back to the original window
        getBrowser().switchTo().window(originalWindow);
    }

    public void closeCurrentWindow() {
        getBrowser().close();
    }

    public void navigateBack() {
        getBrowser().navigate().back();
    }

    public void navigateForward() {
        getBrowser().navigate().forward();
    }

    public void refreshPage() {
        getBrowser().navigate().refresh();
    }

    public void scrollToElement(By loc) {
        getA().scrollToElement(Locator(loc)).perform();
    }

    public void acceptAlert() {
        implicitWait(Duration.ofSeconds(10));
        getBrowser().switchTo().alert().accept();
    }

    public void dismissAlert() {
        implicitWait(Duration.ofSeconds(10));
        getBrowser().switchTo().alert().dismiss();
    }

    public String getAlertText() {
        implicitWait(Duration.ofSeconds(10));
        return getBrowser().switchTo().alert().getText();
    }

    public void sendTexttoAlert(String text) {
        implicitWait(Duration.ofSeconds(10));
        getBrowser().switchTo().alert().sendKeys(text);  // Enter text
        getBrowser().switchTo().alert().accept();  // Click OK
    }

    public void uploadFile(By loc,
            String filePath) {
        Locator(loc).sendKeys(filePath);
    }

    public void quitBrowser() {
        getBrowser().quit();
    }

    public void clearText(By loc) {
        Locator(loc).clear();
    }

    public boolean isDisabled(By loc) {
        return !(Locator(loc).isEnabled());
    }

    public boolean isEnabled(By loc) {
        return (Locator(loc).isEnabled());
    }

    public void hoverToElement(WebElement element) {
        getA().moveToElement(element).perform();
    }
}
