package ru.stqa.training.selenium.tests;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
              country_name.add(c.findElement(By.xpath("./td/a[not(contains(@title, 'Edit'))]")).getAttribute("textContent"));
            if (!c.findElement(By.xpath("./td[6]")).getAttribute("textContent").equals("0")){
                country_zone.add(c.findElement(By.xpath("./td/a[not(contains(@title, 'Edit'))]")).getAttribute("href"));
            }
        }

            assertTrue(sort((ArrayList) country_name));

        for(String z: country_zone){
            driver.get(""+z+"");
            List<WebElement> country_on_zone = driver.findElements(By.xpath("//table[contains(@id,'table-zones')]//td[3]"));
            List<String> country_on_zone_name = new ArrayList<>();
            for(WebElement c: country_on_zone){
                if (!c.getAttribute("textContent").equals("")) {
                    country_on_zone_name.add(c.getAttribute("textContent"));
                }
            }
            assertTrue(sort((ArrayList) country_on_zone_name));
        }

        // $x ("//tr[contains(@class,'row')]/td/a[not(contains(@title, 'Edit'))]")
        // $x ("//table[contains(@id,'table-zones')]//td[3]")

    }

}
