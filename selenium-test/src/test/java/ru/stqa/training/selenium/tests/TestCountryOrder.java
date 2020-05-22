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
        List<String> country_on_zone_name = new ArrayList<>();
        List<WebElement> country = driver.findElements(By.cssSelector("tr[class=row]"));
        for(WebElement c: country) {
              country_name.add(c.findElement(By.xpath("./td/a[not(contains(@title, 'Edit'))]")).getAttribute("textContent"));
            if (!c.findElement(By.xpath("./td[6]")).getAttribute("textContent").equals("0")){
                country_zone.add(c.findElement(By.xpath("./td/a[not(contains(@title, 'Edit'))]")).getAttribute("href"));
            }
        }

            assertTrue(sort((ArrayList) country_name));
        //for (int i=0; i<country_zone.size(); i++){
         //  System.out.println(country_zone.get(i));
      //  }

        for(String z: country_zone){
            //System.out.println(driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//a[not(contains(@title, 'Edit')) and contains(@href, '"+z+"')]")).getAttribute("textContent"));
            //driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//a[not(contains(@title, 'Edit')) and contains(@href, '"+z+"')]")).click();
            driver.get(""+z+"");
            List<WebElement> country_on_zone = driver.findElements(By.xpath("//table[contains(@id,'table-zones')]//td[3]"));
            for(WebElement c: country_on_zone){
                country_on_zone_name.add(c.getAttribute("textContent"));
                System.out.println(c.getAttribute("textContent"));
            }
            assertTrue(sort((ArrayList) country_on_zone_name));
        }
       // for(WebElement c: country) {
      //      driver.findElements(By.cssSelector(""));
      //  }
        //row[not(contains(title,'Edit'))]
        // $x ("//tr[contains(@class,'row')]/td/a[not(contains(@title, 'Edit'))]")
        // $x ("//table[contains(@id,'table-zones')]//td[3]")

    }

}
