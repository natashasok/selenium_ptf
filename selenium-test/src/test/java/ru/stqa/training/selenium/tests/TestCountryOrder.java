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

    public boolean sort(ArrayList list) {
        List sortedList = new ArrayList(list);
        Collections.sort(sortedList);
        boolean sorted = sortedList.equals(list);
        return sorted;
    }

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
            //if (!c.getAttribute("title").equals("0")){
             //   country_zone.add(c.getAttribute("textContent"));
           // }
        }


        //List sortedList = new ArrayList(country_name);
        //Collections.sort(sortedList);
        assertTrue(sort((ArrayList) country_name));


    }

}
