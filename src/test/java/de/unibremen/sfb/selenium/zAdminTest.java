package de.unibremen.sfb.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
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

public class zAdminTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void admin() throws InterruptedException {
    //Anmeldung
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1440, 900));
    driver.findElement(By.linkText("Zur Anmeldung")).click();
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
