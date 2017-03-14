package seleniumTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class kkboxTest {
	  private WebDriver driver;
	  private String baseUrl;

	  @Before
	  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/Shen/KKBOX_Interview_Presentation/chromedriver");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    baseUrl = "https://www.kkbox.com";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testKkboxTestJava() throws Exception {
	    driver.get(baseUrl + "/play/");
	    
	    driver.findElement(By.id("uid")).clear();
	    driver.findElement(By.id("uid")).sendKeys("aahshen@gmail.com");
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("the04081003");
	    driver.findElement(By.id("login-btn")).click();
	    
	    // check side-bar
	    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@translate, '我的音樂庫')]")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@translate, '線上精選')]")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@translate, '電台')]")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@translate, '一起聽')]")).isDisplayed());
	   
	    // search song
	    driver.findElement(By.xpath("//input[@placeholder='請輸入關鍵字']")).clear();
	    driver.findElement(By.xpath("//input[@placeholder='請輸入關鍵字']")).sendKeys("清平調");
	    driver.findElement(By.id("search_btn_cnt")).click();
	    driver.findElement(By.partialLinkText("王菲&鄧麗君")).click();
	    
	    // wait and show media info for 3 seconds
	    WebDriverWait waitMediaInfo = new WebDriverWait(driver,10);
	    waitMediaInfo.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("media"))));
	    TimeUnit.SECONDS.sleep(3); 
	    
	    // go to radio and random choose a radio station
	    driver.findElement(By.linkText("電台")).click();
	    
	    Actions action = new Actions(driver);
	    String radioXPATH = randomSelectRadio();
        
	    action.moveToElement(driver.findElement(By.xpath(radioXPATH))).build().perform();
	    
        // click radio button when it is visible
        WebDriverWait waitBtnRadio = new WebDriverWait(driver,10);
        String playRadioBtnXPATH = radioXPATH + "/a";
        waitBtnRadio.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(playRadioBtnXPATH))));
        driver.findElement(By.xpath(playRadioBtnXPATH)).click();
        
        try{
	    	WebDriverWait waitAlert = new WebDriverWait(driver, 3);
	    	waitAlert.until(ExpectedConditions.alertIsPresent());
	    	Alert alert = driver.switchTo().alert();
	    	alert.accept();
	    }
	    catch(TimeoutException timeE){
	    	System.out.println("No Alert");
	    }
	    catch(NoAlertPresentException eleException){
	    	System.out.println("No Alert");
	    }
        
        // play song for 8 seconds
        TimeUnit.SECONDS.sleep(8); 
        
        // play next song for 5 seconds
        driver.findElement(By.xpath("//a[contains(@title, '不喜歡')]")).click();
        TimeUnit.SECONDS.sleep(5); 
        
        // play next song for 5 seconds
        driver.findElement(By.xpath("//a[contains(@title, '不喜歡')]")).click();
        TimeUnit.SECONDS.sleep(5); 
        
        // pause song for 5 seconds
        driver.findElement(By.xpath("//a[@id='pauseBtn']/i")).click();
        TimeUnit.SECONDS.sleep(5); 
        
        // play song for 5 seconds
        driver.findElement(By.xpath("//a[@id='playBtn']/i")).click();
        TimeUnit.SECONDS.sleep(5); 
        
        // pause song
        driver.findElement(By.xpath("//a[@id='pauseBtn']/i")).click();
        TimeUnit.SECONDS.sleep(3);
        
        // finish and close browser
        driver.close();
        driver.quit();
        
	  }

	  public String randomSelectRadio(){
		  Random randomRadioStation = new Random();
		  int n = randomRadioStation.nextInt(10) + 1;
		  //System.out.println(Integer.toString(n));
		  return "//*[@id='promote-stations']/div/ul/li["+Integer.toString(n)+"]/div/div[1]/div";
		  
	  }
	 
}
