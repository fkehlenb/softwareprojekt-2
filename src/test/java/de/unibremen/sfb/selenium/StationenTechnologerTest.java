package de.unibremen.sfb.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>StationenTechnologerTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class StationenTechnologerTest {
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
    chromeOptions.addArguments("--headless");
    driver = new ChromeDriver(chromeOptions);
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
   * <p>technologer.</p>
   *
   * @throws java.lang.InterruptedException if any.
   */
  @Test
  public void technologer() throws InterruptedException {
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
    driver.findElement(By.xpath("//li/ul/li/a/span")).click();
    Thread.sleep(700);
    //Formular
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
  }
}
