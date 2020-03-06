package de.unibremen.sfb.selenium;

import org.junit.jupiter.api.Test;;
import org.junit.jupiter.api.BeforeEach;;
import org.junit.jupiter.api.AfterEach;;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

/**
 * <p>ProbenVerlustTest class.</p>
 *
 * @author Liam
 * @version $Id: $Id
 * @since 1.0
 */
public class ProbenVerlustTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  /**
   * <p>setUp.</p>
   */
  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
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
   * <p>probenVerlust.</p>
   */
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
    {
      WebElement element = driver.findElement(By.cssSelector(".ui-button-text"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.xpath("//li[4]/ul/li[3]/a")).click();
  }
}
