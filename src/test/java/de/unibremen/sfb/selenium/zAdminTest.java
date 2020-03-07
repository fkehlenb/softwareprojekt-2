package de.unibremen.sfb.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
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
 * <p>zAdminTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class zAdminTest {
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
   * <p>admin.</p>
   *
   * @throws java.lang.InterruptedException if any.
   */
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
