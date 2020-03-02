package de.unibremen.sfb.selenium;

import de.unibremen.sfb.env.EnvironmentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach; //has been changed from org.junit.After

import org.junit.jupiter.api.BeforeEach; //has been changed from org.junit.Before

import org.junit.jupiter.api.Test; //has been changed from org.junit.Test

import static org.junit.jupiter.api.Assertions.assertEquals; //has been changed from org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DemoTest {

    @BeforeEach //has been changed from @Before
    public void startBrowser() {
        WebDriverManager.firefoxdriver().setup();

    }

    @Test
    public void demo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
        String homeUrl = driver.findElement(By.cssSelector("div#logo> a#logo_image ")).getAttribute("href");
        assertEquals(homeUrl, "https://www.blazemeter.com/");
    }

    @AfterEach //has been changed from @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
