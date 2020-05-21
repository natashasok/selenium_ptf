package ru.stqa.training.selenium;

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

public class TestSticker {
    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
    }

    @Test
    public void testSticker() {
        driver.get("http://localhost/litecart/en/");


        List<WebElement> ducks = driver.findElements(By.cssSelector("li[class='product column shadow hover-light']"));
        for (WebElement e: ducks) {
            assertTrue(isElementPresent(driver,By.cssSelector("div[class*='sticker']")));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
