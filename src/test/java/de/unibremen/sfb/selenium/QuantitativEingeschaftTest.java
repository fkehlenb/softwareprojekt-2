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

public class QuantitativEingeschaftTest {
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
  public void quantitativEingeschaft() throws InterruptedException {
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
