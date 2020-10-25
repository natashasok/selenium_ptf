package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class TestNewCustomer extends TestBase{
    public String emailRandom;
    public String randomEmail(){

        Random random = new Random();
        String result = "";
        result += String.valueOf(random.nextInt());

        return emailRandom = "nat" + result + "@gmail.com";

    }

    public void countryUS(){
        WebElement country= driver.findElement(By.cssSelector("[class='select2-hidden-accessible']"));
        Select selectCountry = new Select(country);
        selectCountry.selectByValue("US");
    }
    public void zoneAlabama(){
        WebElement zone= driver.findElement(By.cssSelector("[name='zone_code']"));
        Select selectZone = new Select(zone);
        selectZone.selectByValue("AL");
    }

    @Test
    public void testNewCustomer() {
        driver.get("http://localhost/litecart/en/");
        WebElement newCustumerLink= driver.findElement(By.xpath("//*[contains(@href,'create')] [contains(.,'New customers')]"));
        newCustumerLink.click();
        wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@name,'create_account')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'firstname')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'lastname')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'address1')]")));
        WebElement firstname= driver.findElement(By.xpath("//input[contains(@name,'firstname')]"));
        WebElement lastname= driver.findElement(By.xpath("//input[contains(@name,'lastname')]"));
        WebElement address1= driver.findElement(By.xpath("//input[contains(@name,'address1')]"));
        WebElement postcode= driver.findElement(By.xpath("//input[contains(@name,'postcode')]"));
        WebElement phone= driver.findElement(By.xpath("//input[contains(@name,'phone')]"));
        WebElement city= driver.findElement(By.xpath("//input[contains(@name,'city')]"));
        WebElement password= driver.findElement(By.xpath("//td[contains(.,'Desired Password')]// input[contains(@name,'password')]"));
        WebElement email= driver.findElement(By.xpath("//input[contains(@name,'email')]"));
        WebElement create_account= driver.findElement(By.xpath("//button[contains(@name,'create_account')]"));
        WebElement confirmed_password= driver.findElement(By.xpath("//input[contains(@name,'confirmed_password')]"));
        firstname.sendKeys("Natalia");
        lastname.sendKeys("Sokolova");
        address1.sendKeys("Sovetskaya 18");
        countryUS();
        postcode.sendKeys("15000");
        city.sendKeys("Yaroslavl");
        phone.sendKeys("9991456734");
        String email1 = randomEmail();
        email.sendKeys(email1);
        password.sendKeys("123456");
        confirmed_password.sendKeys("123456");
        create_account.click();
        zoneAlabama();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(.,'Desired Password')]// input[contains(@name,'password')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'confirmed_password')]")));
        driver.findElement(By.xpath("//td[contains(.,'Desired Password')]// input[contains(@name,'password')]")).sendKeys("123456");
        driver.findElement(By.xpath("//input[contains(@name,'confirmed_password')]")).sendKeys("123456");

        driver.findElement(By.xpath("//button[contains(@name,'create_account')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//footer//*[contains(@href,'logout') and contains(.,'Logout')]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'email')]"))).sendKeys(email1);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'password')]"))).sendKeys("123456");
        WebElement buttonLogin= driver.findElement(By.xpath("//button[contains(@name,'login')]"));
        buttonLogin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//footer//*[contains(@href,'logout') and contains(.,'Logout')]"))).click();
    }
}
