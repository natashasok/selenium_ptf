package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCountryOrder extends TestBase {
    @Test
      public void testCountryOrder(){
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<String> country_name = new ArrayList<>();
        List<String> country_zone = new ArrayList<>();
        List<WebElement> country = driver.findElements(By.cssSelector("tr[class=row] a"));
        for(WebElement c: country) {
            if (!c.getAttribute("title").equals("Edit")){
               country_name.add(c.getAttribute("textContent"));
            }
            //if (){
             //   country_zone.add(c.getAttribute("textContent"));
           // }
        }
        ArrayList<String> sortedList = (ArrayList<String>) country_name;
        Collections.sort(sortedList);
        assertEquals(sortedList, country_name);

    }

}
