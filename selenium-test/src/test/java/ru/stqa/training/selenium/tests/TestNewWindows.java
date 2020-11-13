package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

public class TestNewWindows extends TestBase{

 public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
     return new ExpectedCondition<String>() {
         public String apply(WebDriver driver) {
             Set<String> handles = driver.getWindowHandles();
             handles.removeAll(oldWindows);
             return handles.size() >0 ? handles.iterator().next(): null;
         }
     };
 }

    @Test
    public void testNewWindows() throws InterruptedException {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        WebElement btnAddNewCountry = driver.findElement(By.xpath("//*[contains(@class,'button') and contains(.,'Add New Country')]"));
        btnAddNewCountry.click();
        wait = new WebDriverWait(driver, 5);

        //жду загрузку новой страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td button[name=add_zone]")));

        //нахожу все кнопки для перехода в новое окно
        List<WebElement> linksToNewWindows = driver.findElements(By.cssSelector("form a[target=_blank]"));
        int countLinksToW = linksToNewWindows.size();

        for (int i=0; i<countLinksToW ; i++) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            linksToNewWindows.get(i).click();
           //sleep(4500);
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            System.out.println(i);
            driver.switchTo().window(mainWindow);
        }
    }
}
