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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>zAdminTest class.</p>
 *
 * @author Santiago and Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class zAdminTest {
    private WebDriver driver;
    private String spoofUserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";
    /**
     * This Class Shows, that even when correctly configured. The command  new PhantomJSDriver(); is buggy
     * Stack Overflow recommends updating the PhantomJS and Selenium to current Version
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
                    new String[] { "--web-security=false",
                            "--ssl-protocol=any", "--ignore-ssl-errors=true",
                            "--webdriver-loglevel=INFO" });

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
  public void admin() throws InterruptedException {
    //Anmeldung
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1440, 900));
   driver.findElement(By.xpath("//p/a")).click();
    driver.findElement(By.id("username")).click();
    Thread.sleep(700);
    driver.findElement(By.id("username")).sendKeys("admin");
    Thread.sleep(700);
    driver.findElement(By.id("password")).sendKeys("12345678");
    Thread.sleep(700);
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
    //UserVerwaltung
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li/a")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li/ul/li/a/span")).click();
    Thread.sleep(700);
    //DatenFiller
    driver.findElement(By.id("form:vorname")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:vorname")).sendKeys("prueba");
    Thread.sleep(700);
    driver.findElement(By.id("form:nachname")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:nachname")).sendKeys("prueba");
    Thread.sleep(700);
    driver.findElement(By.id("form:email")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:email")).sendKeys("prueba@prueba.de");
    Thread.sleep(700);
    driver.findElement(By.id("form:telefon")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:telefon")).sendKeys("12345678");
    Thread.sleep(700);
    driver.findElement(By.id("form:userName")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:userName")).sendKeys("prueba");
    Thread.sleep(700);
    driver.findElement(By.id("form:password")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:password")).sendKeys("prueba");
    Thread.sleep(700);
    driver.findElement(By.id("form:console_label")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:console_1")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.cssSelector("#form\\3Avalue1 > .ui-button-text")).click();
    Thread.sleep(700);
    driver.findElement(By.cssSelector("#tbl\\3A 0\\3Aj_idt140\\3Aj_idt142 > .ui-button-text")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:vorname")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:vorname")).sendKeys("prueb");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[5]/div/div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//tr[5]/td[5]/div/div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/button/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div/button/span[2]")).click();
    Thread.sleep(700);
  }
}
