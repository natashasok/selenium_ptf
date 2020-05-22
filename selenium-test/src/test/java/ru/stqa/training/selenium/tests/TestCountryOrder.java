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
        List<WebElement> country = driver.findElements(By.cssSelector("tr[class=row]"));
        for(WebElement c: country) {
            //driver.findElement(By.xpath("//td/a[not(contains(@title, 'Edit'))]")).getAttribute("textContent");
            //if (!c.getAttribute("title").equals("Edit")){
              country_name.add(c.findElement(By.xpath("./td/a[not(contains(@title, 'Edit'))]")).getAttribute("textContent"));
           // }
        }
        assertTrue(sort((ArrayList) country_name));
        for (int i=0; i<country_name.size(); i++){
            System.out.println(country_name.get(i));
        }
       // for(WebElement c: country) {
      //      driver.findElements(By.cssSelector(""));
      //  }
        //row[not(contains(title,'Edit'))]
        // $x ("//tr[contains(@class,'row')]/td/a[not(contains(@title, 'Edit'))]")

    }

}
