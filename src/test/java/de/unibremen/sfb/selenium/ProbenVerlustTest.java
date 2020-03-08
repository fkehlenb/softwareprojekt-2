package de.unibremen.sfb.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

;
;
;
;

/**
 * <p>ProbenVerlustTest class.</p>
 *
 * @author Santiago and Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class ProbenVerlustTest {
    private WebDriver driver;
    private String spoofUserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";

    /**
     * This Class Shows, that even when correctly configured. The command  new PhantomJSDriver(); is buggy
     * Stack Overflow recommends updating the PhantomJS and Selenium to current Version
     *
     * @return the Driver
     */
    public WebDriver getDriver() {
        if (this.driver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setJavascriptEnabled(true);
            caps.setCapability(
                    PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX
                            + "userAgent", spoofUserAgent);

            caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                    new String[]{"--web-security=false",
                            "--ssl-protocol=any", "--ignore-ssl-errors=true",
                            "--webdriver-loglevel=INFO"});

            PhantomJSDriverService service = new PhantomJSDriverService.Builder()
                    .usingPort(8081)
                    .usingPhantomJSExecutable(new File("/usr/local/bin/phantomjs"))
                    .build();
            this.driver = new PhantomJSDriver(service, caps);
        }
        return this.driver;
    }

    /**
     * <p>tearDown.</p>
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * <p>setUp.</p>
     */
    @BeforeEach
    public void setUp() {
        driver = getDriver();
        // Download link is http://phantomjs.org/download.html
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void probenVerlust() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1200, 833));
        driver.findElement(By.xpath("//p/a")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.xpath("//button/span")).click();
        driver.findElement(By.xpath("//li[4]/ul/li[7]/a/span")).click();
        driver.findElement(By.xpath("//td/input")).click();
        driver.findElement(By.id("form:pid")).sendKeys("A22.222");
        driver.findElement(By.xpath("//td[2]/input")).click();
        driver.findElement(By.id("form:pAnzahl")).sendKeys("50");
        driver.findElement(By.xpath("//button/span")).click();
        driver.findElement(By.xpath("//li[4]/ul/li[3]/a/span")).click();
        driver.findElement(By.xpath("//li[4]/ul/li[7]/a/span")).click();
        driver.findElement(By.xpath("//td/input")).click();
        driver.findElement(By.id("form:pid")).sendKeys("A22.222");
        driver.findElement(By.xpath("//td[2]/input")).click();
        driver.findElement(By.id("form:pAnzahl")).sendKeys("50");
        driver.findElement(By.xpath("//button/span")).click();

        driver.findElement(By.xpath("//li[4]/ul/li[3]/a")).click();
    }
}
