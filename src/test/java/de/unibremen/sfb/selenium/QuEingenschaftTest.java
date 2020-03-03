package de.unibremen.sfb.selenium;// Generated by Selenium IDE
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
public class QuEingenschaftTest {
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
  public void quEingenschaft() throws InterruptedException {
    //Anmeldung
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1440, 900));
    driver.findElement(By.linkText("Zur Anmeldung")).click();
    driver.findElement(By.id("username")).click();
    Thread.sleep(400);
    driver.findElement(By.id("username")).sendKeys("admin");
    Thread.sleep(400);
    driver.findElement(By.id("password")).sendKeys("12345678");
    Thread.sleep(400);
    driver.findElement(By.xpath("//button/span")).click();
    //EingeschaftMenu
    Thread.sleep(400);
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(400);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li[2]/a")).click();
    Thread.sleep(400);
    driver.findElement(By.xpath("//li[7]/a/span")).click();
    Thread.sleep(400);
    //QualittivForm
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(400);
    driver.findElement(By.id("j_idt58:nameQl")).sendKeys("pruebaEin");
    Thread.sleep(400);
    driver.findElement(By.xpath("//td[2]/button/span")).click();
    Thread.sleep(400);
    driver.findElement(By.xpath("//td[4]/form/div/button/span[2]")).click();
    Thread.sleep(400);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(400);
    driver.findElement(By.id("j_idt58:nameQl")).sendKeys("quilEinTest");
    Thread.sleep(400);
    driver.findElement(By.xpath("//td[2]/button/span")).click();
    Thread.sleep(400);
    driver.findElement(By.xpath("//div[2]/div/div/div/form/table/tbody/tr/td[2]/input")).click();
    Thread.sleep(400);
    driver.findElement(By.id("j_idt84:name")).sendKeys("quant");
    Thread.sleep(400);
    driver.findElement(By.xpath("//tr[2]/td[2]/input")).click();
    Thread.sleep(400);
    driver.findElement(By.id("j_idt84:value")).sendKeys("50");
    Thread.sleep(400);
    driver.findElement(By.xpath("//tr[4]/td[2]/button/span")).click();
    Thread.sleep(400);
    driver.findElement(By.xpath("//td[6]/form/div/button/span[2]")).click();
    Thread.sleep(400);
  }
}
