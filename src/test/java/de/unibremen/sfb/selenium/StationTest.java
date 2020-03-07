package de.unibremen.sfb.selenium;

import org.junit.jupiter.api.Test;;
import org.junit.jupiter.api.BeforeEach;;
import org.junit.jupiter.api.AfterEach;;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.remote.Abs;
import java.util.*;

/**
 * <p>StationTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class StationTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  /**
   * <p>setUp.</p>
   */
  @BeforeEach
  public void setUp() {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    ChromeOptions chromeOptions = new ChromeOptions();
//    chromeOptions.addArguments("--headless");
////driver = new ChromeDriver(chromeOptions);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  /**
   * <p>tearDown.</p>
   */
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  /**
   * <p>stationTest.</p>
   *
   * @throws java.lang.InterruptedException if any.
   */
  @Test
  public void stationTest() throws InterruptedException {
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
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li/a")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//li/ul/li[3]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt69")).sendKeys("testSelenium");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//button/span[2]")).click();
    Thread.sleep(700);
  }
}
