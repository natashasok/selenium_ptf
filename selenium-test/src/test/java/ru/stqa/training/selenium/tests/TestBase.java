package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;
    WebDriverWait wait;
    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,30);
        System.out.println("before");
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
        System.out.println("after");
    }
}
