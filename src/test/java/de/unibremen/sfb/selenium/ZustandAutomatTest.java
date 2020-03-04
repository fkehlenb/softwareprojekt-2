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
public class ZustandAutomatTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void zustandAutomat() throws InterruptedException {
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
