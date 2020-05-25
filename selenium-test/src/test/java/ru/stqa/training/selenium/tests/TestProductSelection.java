package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.ProductData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestProductSelection extends TestBase{

    @Test
    public void testProductSelection() {
        driver.get("http://localhost/litecart/en/");
       ProductData onFirstPage = new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-campaigns] a.link")).getAttribute("textContent"))
                        .withRegularPrice(driver.findElement(By.cssSelector("[id=box-campaigns] a.link .regular-price")).getAttribute("textContent"))
        .withCampaignPrice(driver.findElement(By.cssSelector("[id=box-campaigns] a.link .campaign-price")).getAttribute("textContent"));
        driver.findElement(By.cssSelector("[id=box-campaigns] a.link")).click();
        wait = new WebDriverWait(driver,30);
        ProductData onSecondPage =new ProductData().withTitle(driver.findElement(By.cssSelector("[h1.title]")).getAttribute("textContent"))
        .withRegularPrice(driver.findElement(By.cssSelector("[s.regular-price]")).getAttribute("textContent"))
        .withCampaignPrice(driver.findElement(By.cssSelector("[strong.campaign-price]")).getAttribute("textContent"));
        assertTrue(onFirstPage.equals(onSecondPage));
    }
}
