package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-fullscreen");
        driver = new ChromeDriver(capabilities);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,5);
        System.out.println("before");
    }
   @After
    public void stop(){
        driver.quit();
        driver = null;
        System.out.println("after");
    }

    public void loginAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait = new WebDriverWait(driver,30);
    }
    public boolean sort(ArrayList list) {
        List sortedList = new ArrayList(list);
        Collections.sort(sortedList);
        boolean sorted = sortedList.equals(list);
        return sorted;
    }
}
