package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class KkboxTestJava {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.kkbox.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testKkboxTestJava() throws Exception {
    driver.get(baseUrl + "/play/");
    driver.findElement(By.id("uid")).clear();
    driver.findElement(By.id("uid")).sendKeys("aahshen@gmail.com");
    driver.findElement(By.id("pwd")).clear();
    driver.findElement(By.id("pwd")).sendKeys("the04081003");
    driver.findElement(By.id("login-btn")).click();
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("清平調");
    driver.findElement(By.id("search_btn_cnt")).click();
    driver.findElement(By.linkText("王菲&鄧麗君 (Faye Wong & Teresa Teng) - 清平調 搶先聽")).click();
    driver.findElement(By.linkText("電台")).click();
    driver.findElement(By.xpath("//div[@id='player']/div[6]/a/i")).click();
    driver.findElement(By.xpath("//div[@id='player']/div[6]/a/i")).click();
    driver.findElement(By.xpath("//a[@id='pauseBtn']/i")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
