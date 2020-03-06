package de.unibremen.sfb.env;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * <p>EnvironmentManager class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class EnvironmentManager {

    /**
     * <p>initWebDriver.</p>
     */
    public static void initWebDriver() {
        WebDriver driver = new FirefoxDriver();
        RunEnvironment.setWebDriver(driver);
    }

    /**
     * <p>shutDownDriver.</p>
     */
    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }
}
