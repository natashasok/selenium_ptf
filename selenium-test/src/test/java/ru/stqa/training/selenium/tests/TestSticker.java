package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TestSticker extends TestBase {


    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    @Test
    public void testSticker() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> ducks = driver.findElements(By.cssSelector("li[class='product column shadow hover-light']"));
        for (WebElement e: ducks) {
            assertTrue(isElementPresent(driver,By.cssSelector("div[class*='sticker']")));
        }
    }

}
