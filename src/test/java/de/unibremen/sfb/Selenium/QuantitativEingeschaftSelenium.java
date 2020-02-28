package de.unibremen.sfb.Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QuantitativEingeschaftSelenium {

        //chromedriver().version("77.0.3865.40").setup()
    @Test
    public void testLogin() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        try {
        driver.get("http://localhost:8080/login.xhtml");
        WebElement admin = driver.findElement(By.id("username"));
        admin.click();
        admin.sendKeys("admin");
        Thread.sleep(500);
        WebElement password = driver.findElement(By.id("password"));
        Thread.sleep(500);
        password.click();
        Thread.sleep(500);
        password.sendKeys("12345678");
        Thread.sleep(500);
        WebElement button = driver.findElement(By.id("j_idt39"));
        Thread.sleep(500);
        button.click();
        Thread.sleep(500);
        //Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/login.xhtml");
        }catch (Exception e){

        }
        driver.quit();
    }

    @AfterMethod
    public void close(){

    }

    @Test
    public void testLoginFalse() throws Exception {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        try {
            driver.get("http://localhost:8080/login.xhtml");
            WebElement admin = driver.findElement(By.id("username"));
            admin.click();
            admin.sendKeys("admin");
            Thread.sleep(500);
            WebElement password = driver.findElement(By.id("password"));
            Thread.sleep(500);
            password.click();
            Thread.sleep(500);
            password.sendKeys("000000");
            Thread.sleep(500);
            WebElement button = driver.findElement(By.id("j_idt39"));
            Thread.sleep(500);
            button.click();
            Thread.sleep(500);
        }catch (Exception e){
           throw new Exception("jhg");
        }

        //Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/login.xhtml");
        driver.close();
    }

}
