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
 * <p>QuantitativEingeschaftTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class QuantitativEingeschaftTest {
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
   * <p>quantitativEingeschaft.</p>
   *
   * @throws java.lang.InterruptedException if any.
   */
  @Test
  public void quantitativEingeschaft() throws InterruptedException {
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
    //Menu
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li[2]/a/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[7]/a/span")).click();
    Thread.sleep(700);
    //Formular
    driver.findElement(By.xpath("//tr[5]/td/div/div[2]/table/tbody/tr/td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("j_idt85:name")).sendKeys("quiantiTest");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("j_idt85:value")).sendKeys("34");
    Thread.sleep(700);
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("j_idt85:j_idt97")).sendKeys("seconds");
    Thread.sleep(700);
    driver.findElement(By.xpath("//tr[2]/td[4]/button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[5]/div/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form3:qEin2:0:value2")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form3:qEin2:0:value2")).sendKeys("345");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[5]/div/a[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/button/span[2]")).click();
    Thread.sleep(700);
  }
}
