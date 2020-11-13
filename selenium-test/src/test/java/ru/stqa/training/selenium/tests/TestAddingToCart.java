package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.Console;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.How.CSS;

public class TestAddingToCart extends TestBase {

    public boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void sizeSmall() {
        WebElement size = driver.findElement(By.cssSelector("[name='options[Size]']"));
        Select sizeSmall = new Select(size);
        sizeSmall.selectByValue("Small");
    }

    private void waitUntilNewCount() {
        WebElement countItemsInCart = driver.findElement(cssSelector("[id='cart'] [class='quantity']"));
        int count = Integer.parseInt(countItemsInCart.getAttribute("textContent"));
        String newCount = Integer.toString(count + 1);
        wait.until(ExpectedConditions.attributeContains(driver.findElement(cssSelector("[id='cart'] [class='quantity']")), "textContent", newCount));
    }

    private void waitUntilHome() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[src='/litecart/images/slides/1-lonely-duck.jpg']")));
    }

    @Test
    public void testAddingToCart() throws InterruptedException {
        wait = new WebDriverWait(driver, 5);
        driver.get("http://localhost/litecart/en/");
        int ducks_to_add = 3;


        By add_cart_css = cssSelector("[class=quantity] [name='add_cart_product']");
        for (int i = 0; i < ducks_to_add; i++) {
            waitUntilHome();

//            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class='product column shadow hover-light'] [class='link']")));
            List<WebElement> products = driver.findElements(cssSelector("[class='product column shadow hover-light'] [class='link']"));
            wait.until(ExpectedConditions.visibilityOf(products.get(0)));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
            button.click();
//            Actions actions = new Actions(driver);
//            actions.moveToElement(button).click(button).build().perform();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(add_cart_css)));
            if (isElementPresent(driver, By.name("options[Size]"))) {
                sizeSmall();
                wait.until(ExpectedConditions.attributeContains(driver.findElement(By.name("options[Size]")), "value", "Small"));

            }
            wait.until(ExpectedConditions.presenceOfElementLocated(add_cart_css));
            driver.findElement(cssSelector("[class=quantity] [name='add_cart_product']")).click();
            waitUntilNewCount();

            System.out.println(i);
            driver.findElement(By.cssSelector("[class='fa fa-home']")).click();
        }

        waitUntilHome();

//        Thread.sleep(1000);

        By checkout = By.xpath("//*[contains(@class,'link') and contains(.,'Checkout')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkout));
        WebElement btnCheckout = wait.until(ExpectedConditions.elementToBeClickable(checkout));
        new Actions(driver).moveToElement(btnCheckout).click(btnCheckout).build().perform();
//        btnCheckout.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='dataTable rounded-corners']")));

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='dataTable rounded-corners']")));
//        Thread.sleep(1000);

        List<WebElement> trs = driver.findElements(By.xpath("//*[contains(@class,'dataTable ')]//td[contains(.,'Duck')]/.."));
        int count = trs.size();

//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class^='shortcut'] li a img")));
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class^='shortcut'] li a img")));
        List<WebElement> shortcuts = driver.findElements(By.cssSelector("[class^='shortcut'] a"));
        if (shortcuts.size() > 0) {
            WebElement btnShortcut = wait.until(ExpectedConditions.elementToBeClickable(shortcuts.get(0)));
            new Actions(driver).moveToElement(btnShortcut).click(btnShortcut).build().perform();
        }

        for (int i = 0; i < count; i++) {
            List<WebElement> removes = driver.findElements(By.cssSelector("[name='remove_cart_item']"));
            if (removes.size() > 0) {
                wait.until(ExpectedConditions.visibilityOf(removes.get(0)));
                WebElement btnRemove = wait.until(ExpectedConditions.elementToBeClickable(removes.get(0)));
                new Actions(driver).moveToElement(btnRemove).click(btnRemove).build().perform();
            }

            trs = driver.findElements(By.xpath("//*[contains(@class,'dataTable ')]//td[contains(.,'Duck')]/.."));
            if (trs.size() > 0) {
                wait.until(ExpectedConditions.invisibilityOf(trs.get(trs.size() - 1)));
            }
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='dataTable rounded-corners']")));

    }

}
