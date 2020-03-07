package de.unibremen.sfb.selenium;

import org.junit.jupiter.api.Test;;
;
import org.junit.jupiter.api.AfterEach;;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>ExperimentStationTestTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class ExperimentStationTestTest {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    /** Constant <code>dCaps</code> */
    protected static DesiredCapabilities dCaps;
  /**
   * <p>setUp.</p>
   */
  @BeforeEach
  public void setUp() {
      dCaps = new DesiredCapabilities();
      dCaps.setJavascriptEnabled(true);
      dCaps.setCapability("takesScreenshot", false);

      driver = new PhantomJSDriver(dCaps);
      baseUrl = "http://assertselenium.com/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  /**
   * <p>tearDown.</p>
   */
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  /**
   * <p>experimentStationTest.</p>
   *
   * @throws java.lang.InterruptedException if any.
   */
  @Test
  public void experimentStationTest() throws InterruptedException {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1200, 833));
    Thread.sleep(700);
   driver.findElement(By.xpath("//p/a")).click();
    Thread.sleep(700);
    driver.findElement(By.id("username")).click();
    Thread.sleep(700);
    driver.findElement(By.id("username")).sendKeys("admin");
    Thread.sleep(700);
    driver.findElement(By.id("password")).sendKeys("12345678");
    Thread.sleep(500);
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li/a")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li/ul/li[2]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt70")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt70")).sendKeys("Hola");
    Thread.sleep(700);
    driver.findElement(By.xpath("//label")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[6]/div[2]/table/tbody/tr[2]/td")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[6]/div/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li/div/div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/div/div[3]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[8]/div[2]/ul/li/div/div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/button/span")).click();
    Thread.sleep(700);
    //Loschen
    driver.findElement(By.xpath("//td[6]/button/span[2]")).click();
    Thread.sleep(700);
  }
}
