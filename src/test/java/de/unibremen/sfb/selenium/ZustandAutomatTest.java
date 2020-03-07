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

import java.util.*;

/**
 * <p>ZustandAutomatTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class ZustandAutomatTest {
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
//driver = new ChromeDriver(chromeOptions);
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
   * <p>zustandAutomat.</p>
   *
   * @throws java.lang.InterruptedException if any.
   */
  @Test
  public void zustandAutomat() throws InterruptedException {
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
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li[2]/a/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[2]/ul/li[3]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/div/div/ul/li")).click();
    Thread.sleep(700);
    driver.findElement(By.id("j_idt75:name")).sendKeys("zATest");
    Thread.sleep(700);
    driver.findElement(By.xpath("//div/button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//tr[2]/td[4]/button/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[5]/div/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:table:0:j_idt96")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div/div[2]/div/div/ul/li")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:table:0:j_idt96")).sendKeys("zATestname");
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div[2]/div/button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[5]/div/a[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("j_idt63:j_idt69")).sendKeys("NeueEingeschaft");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/button/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[3]/table/tbody/tr/td[4]/button/span[2]")).click();
    Thread.sleep(700);
  }
}
