
package de.unibremen.sfb.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Web_driver;
import org.openqa.selenium.phantomjs.PhantomJS_driver;
import org.openqa.selenium.phantomjs.PhantomJS_driverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * <p>EingeschaftenTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class EingeschaftenTest {
    private Web_driver _driver;
    private String spoofUserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";

    protected Web_driver get_driver() {
        if (this.__driver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setJavascriptEnabled(true);
            caps.setCapability(
                    PhantomJS_driverService.PHANTOMJS_PAGE_SETTINGS_PREFIX
                            + "userAgent", spoofUserAgent);

            caps.setCapability(PhantomJS_driverService.PHANTOMJS_CLI_ARGS,
                    new String[]{"--web-security=false",
                            "--ssl-protocol=any", "--ignore-ssl-errors=true",
                            "--web_driver-loglevel=INFO"});

            PhantomJS_driverService service = new PhantomJS_driverService.Builder()
                    .usingPort(8080)
                    .usingPhantomJSExecutable(new File("../phantomjs-2.1.1-linux-x86_64"))
                    .build();
            this.__driver = new PhantomJS_driver(service, caps);
        }

        return this.__driver;
    }

    /**
     * <p>setUp.</p>
     */
    @BeforeEach
    public void setUp() {
        // Download link is http://phantomjs.org/download.html
        // Set implicit wait
        _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Open "Google.com and search SoftwareTestingMaterial.com"
    }

    /**
     * <p>tearDown.</p>
     */
    @AfterEach
    public void tearDown() {
        _driver.quit();
    }

    /**
     * <p>eingeschaftenTest.</p>
     *
     * @throws java.lang.InterruptedException if any.
     */
    @Test
    public void eingeschaftenTest() throws InterruptedException {
        //Anmeldung
        _driver.get("http://localhost:8080/");
        _driver.manage().window().setSize(new Dimension(1440, 900));
        _driver.findElement(By.xpath("//p/a")).click();
        _driver.findElement(By.id("username")).click();
        Thread.sleep(700);
        _driver.findElement(By.id("username")).sendKeys("admin");
        Thread.sleep(700);
        _driver.findElement(By.id("password")).sendKeys("12345678");
        Thread.sleep(700);
        _driver.findElement(By.xpath("//button/span")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//div[2]/div/h3")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//div[2]/div/div/ul/li[2]/a/span[2]")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//li[7]/a/span")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//td[2]/input")).click();
        Thread.sleep(700);
        //Formular
        _driver.findElement(By.id("j_idt63:nameQl")).sendKeys("pruebaTest");
        Thread.sleep(700);
        _driver.findElement(By.xpath("//td[4]/button/span")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//td[3]/div/a/span")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//div[2]/input")).click();
        Thread.sleep(700);
        _driver.findElement(By.id("form2:qEin:0:nameQl2")).sendKeys("other");
        Thread.sleep(700);
        _driver.findElement(By.xpath("//td[3]/div/a[2]/span")).click();
        Thread.sleep(700);
        _driver.findElement(By.xpath("//button/span[2]")).click();
        Thread.sleep(700);
    }
}
