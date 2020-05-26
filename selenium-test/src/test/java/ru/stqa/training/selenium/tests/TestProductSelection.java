package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.ProductData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestProductSelection extends TestBase {

    private int[] getArrayColorFromRGBAString(String rgba) {
        String[] numbers = rgba.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        int a = Integer.parseInt(numbers[3].trim());
        return new int[]{r, g, b, a};
    }

    private boolean priceGrey(String rgba) {
        int[] rgbaArray = getArrayColorFromRGBAString(rgba);
        return rgbaArray[0] == rgbaArray[1] && rgbaArray[1] == rgbaArray[2];
    }
    private boolean priceRed(String rgba) {
        int[] rgbaArray = getArrayColorFromRGBAString(rgba);
        return rgbaArray[1] == 0 && rgbaArray[2] == 0;
    }

    @Test
    public void testProductSelection() {
        driver.get("http://localhost/litecart/en/");
        WebElement regularPriceOnFirstPage = driver.findElement(By.cssSelector("[id=box-campaigns] a.link .regular-price"));
        WebElement campaignPriceOnFirstPage = driver.findElement(By.cssSelector("[id=box-campaigns] a.link .campaign-price"));
        ProductData onFirstPage = new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-campaigns] a.link div.name")).getAttribute("textContent"))
                .withRegularPrice(regularPriceOnFirstPage.getAttribute("textContent"))
                .withCampaignPrice(campaignPriceOnFirstPage.getAttribute("textContent"));
        assertTrue(priceGrey(regularPriceOnFirstPage.getCssValue("color")));
        assertEquals("line-through", regularPriceOnFirstPage.getCssValue("text-decoration-line"));
        assertTrue(priceRed(campaignPriceOnFirstPage.getCssValue("color")));
        assertEquals("700", campaignPriceOnFirstPage.getCssValue("font-weight"));
        assertTrue(regularPriceOnFirstPage.getSize().getHeight() < campaignPriceOnFirstPage.getSize().getHeight());
        assertTrue(regularPriceOnFirstPage.getSize().getWidth() < campaignPriceOnFirstPage.getSize().getWidth());
        driver.findElement(By.cssSelector("[id=box-campaigns] a.link")).click();
        WebElement regularPriceOnSecondPage = driver.findElement(By.cssSelector("[id=box-product] s.regular-price"));
        WebElement campaignPriceOnSecondPage = driver.findElement(By.cssSelector("[id=box-product] strong.campaign-price"));
        ProductData onSecondPage = new ProductData().withTitle(driver.findElement(By.cssSelector("[id=box-product] h1.title")).getAttribute("textContent"))
                .withRegularPrice(regularPriceOnSecondPage.getAttribute("textContent"))
                .withCampaignPrice(campaignPriceOnSecondPage.getAttribute("textContent"));
        assertTrue(onFirstPage.equals(onSecondPage));

        assertTrue(priceGrey(regularPriceOnSecondPage.getCssValue("color")));
        assertEquals("line-through", regularPriceOnSecondPage.getCssValue("text-decoration-line"));

        assertTrue(priceRed(campaignPriceOnSecondPage.getCssValue("color")));
        assertEquals("700", campaignPriceOnSecondPage.getCssValue("font-weight"));
        assertTrue(regularPriceOnSecondPage.getSize().getWidth() < campaignPriceOnSecondPage.getSize().getWidth());
        assertTrue(regularPriceOnSecondPage.getSize().getHeight() < campaignPriceOnSecondPage.getSize().getHeight());

    }
}
