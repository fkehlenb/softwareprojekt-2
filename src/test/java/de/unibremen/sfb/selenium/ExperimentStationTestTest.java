package de.unibremen.sfb.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;;
;
import org.junit.jupiter.api.AfterEach;;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class ExperimentStationTestTest {
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
  public void experimentStationTest() throws InterruptedException {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1200, 833));
    Thread.sleep(700);
    driver.findElement(By.linkText("Zur Anmeldung")).click();
    Thread.sleep(700);
    driver.findElement(By.id("username")).click();
    Thread.sleep(700);
    driver.findElement(By.id("username")).sendKeys("admin");
    Thread.sleep(700);
    driver.findElement(By.id("password")).sendKeys("12345678");
    Thread.sleep(500);
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/div/ul/li/a")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li/ul/li[2]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt70")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt70")).sendKeys("Hola");
    Thread.sleep(700);
    driver.findElement(By.xpath("//label")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[6]/div[2]/table/tbody/tr[2]/td")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[6]/div/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li/div/div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/div/div[3]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[8]/div[2]/ul/li/div/div[2]/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[4]/button/span")).click();
    Thread.sleep(700);
    //Loschen
    driver.findElement(By.xpath("//td[6]/button/span[2]")).click();
    Thread.sleep(700);
  }
}
