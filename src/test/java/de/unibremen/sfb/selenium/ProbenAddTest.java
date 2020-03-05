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

public class ProbenAddTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless");
    driver = new ChromeDriver(chromeOptions);
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void probenAdd() throws InterruptedException {
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
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/a/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/ul/li[6]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt67")).sendKeys("A22.222");
    Thread.sleep(700);
    driver.findElement(By.id("form:j_idt72")).sendKeys("100");
    Thread.sleep(700);
    driver.findElement(By.xpath("//button/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//div[2]/div/h3")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/a/span[2]")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/ul/li[7]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:pid")).sendKeys("A22.222");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:pAnzahl")).sendKeys("50");
    Thread.sleep(700);
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/ul/li[3]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/ul/li[7]/a/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//td/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:pid")).sendKeys("A22.222");
    Thread.sleep(700);
    driver.findElement(By.xpath("//td[2]/input")).click();
    Thread.sleep(700);
    driver.findElement(By.id("form:pAnzahl")).sendKeys("50");
    Thread.sleep(700);
    driver.findElement(By.xpath("//button/span")).click();
    Thread.sleep(700);
    driver.findElement(By.xpath("//li[4]/ul/li[3]/a/span")).click();
    Thread.sleep(700);
  }
}
