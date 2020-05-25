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
       ProductData onFirstPage = new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-campaigns] a.link div.name")).getAttribute("textContent"))
                        .withRegularPrice(driver.findElement(By.cssSelector("[id=box-campaigns] a.link .regular-price")).getAttribute("textContent"))
                        .withCampaignPrice(driver.findElement(By.cssSelector("[id=box-campaigns] a.link .campaign-price")).getAttribute("textContent"));
        driver.findElement(By.cssSelector("[id=box-campaigns] a.link .regular-price")).getCssValue("color")
                .equals("rgba(119, 119, 119, 1)");
        driver.findElement(By.cssSelector("[id=box-campaigns] a.link .campaign-price")).getCssValue("color")
                .equals("rgba(204, 0, 0, 1)");
        driver.findElement(By.cssSelector("[id=box-campaigns] a.link")).click();
        wait = new WebDriverWait(driver,100);
        ProductData onSecondPage =new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-product] h1.title")).getAttribute("textContent"))
        .withRegularPrice(driver.findElement(By.cssSelector("[id=box-product] s.regular-price")).getAttribute("textContent"))
        .withCampaignPrice(driver.findElement(By.cssSelector("[id=box-product] strong.campaign-price")).getAttribute("textContent"));
        assertTrue(onFirstPage.equals(onSecondPage));

    }
}
