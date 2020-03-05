package de.unibremen.sfb.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;;
import org.junit.jupiter.api.BeforeEach;;
import org.junit.jupiter.api.AfterEach;;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class PsvTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void psv() throws InterruptedException {
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
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li[2]/a/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[2]/ul/li[5]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/div/div/ul/li")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//button")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/div/div/ul/li")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("j_idt62:j_idt72")).sendKeys("pkvName");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/button/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/div/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:PSKList:0:j_idt80")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div/div[2]/div/div/ul/li")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:PSKList:0:j_idt80")).sendKeys("pkvNametest");
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div[2]/div/button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/div/a[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/table/tbody/tr/td[3]/button/span[2]")).click();
    Thread.sleep(700);
  }
}
