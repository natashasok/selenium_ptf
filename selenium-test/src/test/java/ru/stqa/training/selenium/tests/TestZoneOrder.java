package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestZoneOrder extends TestBase{
    @Test
    public void testZoneOrder(){
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> zone_country = driver.findElements(By.xpath("//table[contains(@class,'dataTable')]//td[3]/a"));
        List<String> country = new ArrayList<>();
        for(WebElement c: zone_country) {
            country.add(c.getAttribute("href"));
        }
       for(String c: country){
            driver.get(""+c+"");
            List<WebElement> zone_country1 = driver.findElements(By.xpath("//table[contains(@class,'dataTable')]//td[3]"));
           List<String> country_selected = new ArrayList<>();
           for (WebElement z: zone_country1) {
               country_selected.add(z.findElement(By.xpath("select/option[@selected]")).getAttribute("text"));
          }
           assertTrue(sort((ArrayList) country_selected));
        }

    }
}
