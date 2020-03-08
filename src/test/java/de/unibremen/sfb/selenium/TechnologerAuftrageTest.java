package de.unibremen.sfb.selenium;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * <p>TechnologerAuftrageTest class.</p>
 *
 * @author Santiago and Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class TechnologerAuftrageTest {
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
    public void technologerAuftrage() throws InterruptedException {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1200, 833));
        Thread.sleep(700);
        driver.findElement(By.xpath("//p/a")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//td[2]/input")).click();
        Thread.sleep(700);
        driver.findElement(By.id("username")).sendKeys("t");
        Thread.sleep(700);
        driver.findElement(By.xpath("//tr[3]/td[2]/input")).click();
        Thread.sleep(700);
        driver.findElement(By.id("password")).sendKeys("12345678");
        Thread.sleep(700);
        driver.findElement(By.xpath("//button/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//div[2]/div/h3/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//div[2]/div/div/ul/li/a/span[2]")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//li/ul/li[2]/a/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//button/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//button/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//td/input")).click();
        Thread.sleep(700);
        driver.findElement(By.id("j_idt33:kommentar")).sendKeys("testKommentar");
        Thread.sleep(700);
        driver.findElement(By.xpath("//tr[9]/td/button/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//div[3]/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//li/div/div[2]/span")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//button[2]/span[2]")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//tr[13]/td/button/span")).click();
        Thread.sleep(700);
    }
}
