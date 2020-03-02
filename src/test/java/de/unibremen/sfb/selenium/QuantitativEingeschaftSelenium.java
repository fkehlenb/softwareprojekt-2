package de.unibremen.sfb.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class QuantitativEingeschaftSelenium {

    @BeforeEach //has been changed from @Before
    public void startBrowser() {
        WebDriverManager.firefoxdriver().setup();
    }
    @Test
    public void testLogin() throws Exception {
        System.out.println(":::::SeleniumTest");

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
            driver.quit();
            throw new Exception("testLoginFalse FAILED");
        }

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
            driver.close();
           throw new Exception("testLoginFalse FAILED");
        }

        //Assert.assertEquals(driver.getCurrentUrl(),"http://localhost:8080/login.xhtml");

    }

}