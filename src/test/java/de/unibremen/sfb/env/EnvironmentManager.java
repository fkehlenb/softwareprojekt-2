package de.unibremen.sfb.env;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EnvironmentManager {

    public static void initWebDriver() {
        WebDriver driver = new FirefoxDriver();
        RunEnvironment.setWebDriver(driver);
    }

    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }
}
