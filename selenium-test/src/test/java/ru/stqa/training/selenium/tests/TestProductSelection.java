package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.ProductData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestProductSelection extends TestBase{

    @Test
    public void testProductSelection() {
        driver.get("http://localhost/litecart/en/");
        WebElement regularPriceOnFirstPage = driver.findElement(By.cssSelector("[id=box-campaigns] a.link .regular-price"));
        WebElement campaignPriceOnFirstPage = driver.findElement(By.cssSelector("[id=box-campaigns] a.link .campaign-price"));
       ProductData onFirstPage = new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-campaigns] a.link div.name")).getAttribute("textContent"))
                        .withRegularPrice(regularPriceOnFirstPage.getAttribute("textContent"))
                        .withCampaignPrice(campaignPriceOnFirstPage.getAttribute("textContent"));
        regularPriceOnFirstPage.getCssValue("color")
                .equals("rgba(119, 119, 119, 1)");
        campaignPriceOnFirstPage.getCssValue("color")
                .equals("rgba(204, 0, 0, 1)");
        assertTrue(regularPriceOnFirstPage.getSize().getHeight() < campaignPriceOnFirstPage.getSize().getHeight());
        assertTrue(regularPriceOnFirstPage.getSize().getWidth() < campaignPriceOnFirstPage.getSize().getWidth());
        driver.findElement(By.cssSelector("[id=box-campaigns] a.link")).click();
        WebElement regularPriceOnSecondPage = driver.findElement(By.cssSelector("[id=box-product] s.regular-price"));
        WebElement campaignPriceOnSecondPage = driver.findElement(By.cssSelector("[id=box-product] strong.campaign-price"));
                ProductData onSecondPage =new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-product] h1.title")).getAttribute("textContent"))
        .withRegularPrice(regularPriceOnSecondPage.getAttribute("textContent"))
        .withCampaignPrice(campaignPriceOnSecondPage.getAttribute("textContent"));
        assertTrue(onFirstPage.equals(onSecondPage));

        regularPriceOnSecondPage.getCssValue("color")
                .equals("rgba(102, 102, 102, 1)");
        campaignPriceOnSecondPage.getCssValue("color")
                .equals("rgba(204, 0, 0, 1)");
        assertTrue(regularPriceOnSecondPage.getSize().getWidth()  < campaignPriceOnSecondPage.getSize().getWidth());
        assertTrue(regularPriceOnSecondPage.getSize().getHeight()  < campaignPriceOnSecondPage.getSize().getHeight());

    }
}
