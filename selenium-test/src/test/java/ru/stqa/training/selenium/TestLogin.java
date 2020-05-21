package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.log.LogInputStream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestLogin {
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

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,30);
    }

    @Test
    public void testLogin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait = new WebDriverWait(driver,30);
        List<WebElement> elementList= driver.findElements(By.cssSelector("li[id=app-]>a"));
      List<String> listLink = new ArrayList<>();
        for (WebElement e: elementList) {
           listLink.add(e.getAttribute("href"));
           System.out.println(e.getAttribute("href"));

        }
        for ( String l: listLink){
            driver.findElement(By.cssSelector("a[href='"+ l +"']")).click();
            if (areElementsPresent(driver, By.cssSelector("li[class=selected] li"))) {
                System.out.println("ok");
                List<WebElement> elementIn =driver.findElements(By.cssSelector("li[class=selected] li"));

                List<String> listLink2 = new ArrayList<>();
                for (WebElement e: elementIn) {
                    listLink2.add(e.getText());
                    System.out.println(e.getText());
                }
                for ( String l2: listLink2){
                    driver.findElement(By.cssSelector("span[class=name'"+ l2 +"']")).click();
                }

            }else System.out.println("ne ok");
            assertTrue(isElementPresent(driver, By.cssSelector("td[id=content]>h1")));
        }

    }



    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
