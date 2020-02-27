package de.unibremen.sfb.Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QuantitativEingeschaftSelenium {

        //chromedriver().version("77.0.3865.40").setup()
    @Test
    public void testBrowser() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080");
    }

}
