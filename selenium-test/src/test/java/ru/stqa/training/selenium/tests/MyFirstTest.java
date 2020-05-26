package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

   @Before
  public void start(){
       driver = new ChromeDriver();
       wait = new WebDriverWait(driver,10);
   }

   @Test
   public void myFirstTest() {
       driver.get("");
       driver.findElement(By.name("username")).sendKeys("admin");
       driver.findElement(By.name("password")).sendKeys("admin");
       driver.findElement(By.name("login")).click();
       wait = new WebDriverWait(driver,30);
       driver.findElement(By.name("q")).sendKeys("webdriver");
       driver.findElement(By.name("btnK")).click();
       //wait.until(titleIs("webdriver - Поиск в Google"));
       // "table.report-table tr:nth-child(1) > td:nth-child(3)"
       //#apPivotTable1 > div.component-container > div > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(3)
       //#apPivotTable1 > div.component-container > div > div:nth-child(2) > table > tbody > tr:nth-child(1)
       //#apPivotTable1 > div.component-container > div > div:nth-child(2) > table > tbody > tr:nth-child(5)
   }

   @After
    public void stop(){
       driver.quit();
       driver = null;
   }
}
