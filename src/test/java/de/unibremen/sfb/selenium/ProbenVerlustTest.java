package de.unibremen.sfb.selenium;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
public class ProbenVerlustTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void probenVerlust() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1200, 833));
    driver.findElement(By.linkText("Zur Anmeldung")).click();
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
