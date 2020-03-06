package de.unibremen.sfb.env;

import org.openqa.selenium.WebDriver;

/**
 * <p>RunEnvironment class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class RunEnvironment {

    private static WebDriver webDriver;

    /**
     * <p>Getter for the field <code>webDriver</code>.</p>
     *
     * @return a {@link org.openqa.selenium.WebDriver} object.
     */
    public static WebDriver getWebDriver() {
        return webDriver;
    }

    static void setWebDriver(WebDriver webDriver) {
        RunEnvironment.webDriver = webDriver;
    }
}
