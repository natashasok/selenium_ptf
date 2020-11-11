package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class TestAddProduct extends TestBase {

    public void soldOutStatusT() {
        WebElement soldOutStatus = driver.findElement(By.cssSelector("[name='sold_out_status_id']"));
        Select soldOutStatusT = new Select(soldOutStatus);
        soldOutStatusT.selectByValue("2");
    }
    public void manufacturer_ACME() {
        WebElement manufacturer = driver.findElement(By.cssSelector("[name='manufacturer_id']"));
        Select manufacturer_ACME = new Select(manufacturer);
        manufacturer_ACME.selectByValue("1");
    }
    public void purchasePriceCurrencyCodeUSD() {
        WebElement purchasePriceCurrencyCode = driver.findElement(By.cssSelector("[name='purchase_price_currency_code']"));
        Select purchasePriceCurrencyCodeUSD = new Select(purchasePriceCurrencyCode);
        purchasePriceCurrencyCodeUSD.selectByValue("USD");
    }
    public void defaultCategorySub() {
        WebElement defaultCategory = driver.findElement(By.cssSelector("[name='default_category_id']"));
        Select defaultCategorySub = new Select(defaultCategory);
        defaultCategorySub.selectByValue("2");
    }

    public String randomName(){

        Random random = new Random();
        String result = "";
        result += String.valueOf(random.nextInt());

        return "NewDuck" + result;
    }

    public void uploadFile(){
        File path = new File("src/test/resources/filesForLoading/duck.png");
        WebElement file = driver.findElement(By.xpath("//*[contains(@type,'file')]"));
        file.sendKeys(path.getAbsolutePath());
    }


    //public void SetDatepicker(WebDriver driver, String cssSelector, String date) {
    //    new WebDriverWait(driver, 20).until(webDriver -> driver.findElement(By.cssSelector(cssSelector)).isDisplayed());
    //    ((JavascriptExecutor) driver).executeScript(String.format("$(%s).datepicker('setDate', %s)", cssSelector, date));
   // }

    @Test
    public void testAddProduct() throws InterruptedException {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        WebElement addNewProduct = driver.findElement(By.xpath("//*[contains(@class,'button') and contains(.,'Add New Product')]"));
        addNewProduct.click();
        wait = new WebDriverWait(driver, 20);
        sleep(2000);
        WebElement tabGeneral = driver.findElement(By.xpath("//*[contains(@href,'general')]"));
        tabGeneral.click();
        WebElement elEnable = driver.findElement(By.xpath("//*[contains(@type, 'radio')]/../../*[contains(.,'En')]"));
        elEnable.click();
        WebElement elName = driver.findElement(By.xpath("//*[contains(@name, 'name[en]')]"));
        String nameMyDuck= randomName();
        elName.sendKeys(nameMyDuck);
        WebElement elCode = driver.findElement(By.xpath("//td/*[contains(.,'Code')]/../input"));
        elCode.sendKeys("999123");
        WebElement elCategoriesSubcategory = driver.findElement(By.xpath("//*[contains(.,'Subcategory')]/../td/input"));
        elCategoriesSubcategory.click();
        defaultCategorySub();
        WebElement elGenderUnisex = driver.findElement(By.xpath("//*[contains(.,'Unisex')]/../td/input"));
        elGenderUnisex.click();
        WebElement elQuantity = driver.findElement(By.xpath("//input[contains(@name,'quantity')]"));
        elQuantity.sendKeys("99");
        soldOutStatusT();
        WebElement date_valid_from = driver.findElement(By.cssSelector("[name='date_valid_from']"));
        date_valid_from.sendKeys("11-11-2020");
        WebElement date_valid_to = driver.findElement(By.cssSelector("[name='date_valid_to']"));
        date_valid_to.sendKeys("11-11-2021");
        uploadFile();

        sleep(2000);

        WebElement tabInformation = driver.findElement(By.xpath("//*[contains(@href,'information')]"));
        tabInformation.click();
        sleep(2000);
        manufacturer_ACME();
        WebElement keywords = driver.findElement(By.cssSelector("[name='keywords']"));
        keywords.sendKeys("nat_duck");
        WebElement shortDescription= driver.findElement(By.cssSelector("[name='short_description[en]']"));
        shortDescription.sendKeys("my duck");
        WebElement description= driver.findElement(By.cssSelector("[class^='trumbowyg-editor']"));
        description.sendKeys("my new duck");
        WebElement headTitle= driver.findElement(By.cssSelector("[name='head_title[en]']"));
        headTitle.sendKeys("Duck N");
        WebElement metaDescription= driver.findElement(By.cssSelector("[name='meta_description[en]']"));
        metaDescription.sendKeys("natasha");
        sleep(2000);

        WebElement tabPrices = driver.findElement(By.xpath("//*[contains(@href,'prices')]"));
        tabPrices.click();
        sleep(2000);
        WebElement purchasePrice= driver.findElement(By.cssSelector("[name='purchase_price']"));
        purchasePrice.clear();
        purchasePrice.sendKeys("12");
        purchasePriceCurrencyCodeUSD();
        WebElement grossPricesU= driver.findElement(By.cssSelector("[name='gross_prices[USD]']"));
        grossPricesU.clear();
        grossPricesU.sendKeys("12");
        WebElement grossPricesE= driver.findElement(By.cssSelector("[name='gross_prices[EUR]']"));
        grossPricesE.clear();
        grossPricesE.sendKeys("9");

        sleep(2000);
        WebElement save= driver.findElement(By.cssSelector("[name='save"));
        save.click();

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        sleep(2000);
        WebElement myduck= driver.findElement(By.xpath(String.format("//*[contains(@class,'dataTable')]//td[contains(.,%s)]",nameMyDuck)));
        assertTrue(myduck.isEnabled());
    }
}
