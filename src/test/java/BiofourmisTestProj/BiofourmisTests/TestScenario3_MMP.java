package BiofourmisTestProj.BiofourmisTests;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestScenario3_MMP {

	public static String browser = "chrome"; 
	// Used only chrome because the workflow was having problem to run the two browsers at the same time, you can test by Passing String browser in setup method. 
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(TestScenario3_MMP.class);
	
	@Parameters("browser")
	@BeforeMethod	
	public void setup() { 
	
		if(browser.equals("chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "True");	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
			log.info("Chrome Browser launched !!!");
		
		} else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Vaneet Bish\\TestAutomation\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox Browser launched !!!");
		}
		
		log.info("Open make my trip website ");
	    driver.get("https://www.makemytrip.com/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);    
	    
	}
	
	@Test
	public void validatemakemyTripDeals() {
		
		log.info("Select Bangaluru to Hyderabad flights");
		driver.findElement(By.xpath("//img[@alt='Make My Trip']")).click();
		
	    WebElement moreBtn = driver.findElement(By.xpath("//span[@class='chNavIcon appendBottom2 chSprite chMore']"));
        Actions action = new Actions(driver);
        action.moveToElement(moreBtn).build().perform();
        
        WebElement deals = driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[10]/div/a[7]"));
        deals.click();
		
	 Set<String> st = driver.getWindowHandles();
	 for(String handle:st) {
		 driver.switchTo().window(handle);
		 System.out.println(handle);
	 }
	 
	 driver.findElement(By.xpath("//input[@id='hp-widget__sfrom']")).click();
	 driver.findElement(By.xpath("//input[@id='hp-widget__sfrom']")).sendKeys("Bengaluru");	 
	 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("Store the from-city flight details in dynamic list ");
		
		List<WebElement>dynamicList = driver.findElements(By.xpath("//div[@class='locationFilter autocomplete_from']//*[@id='ui-id-1']")); 
		
		for(int i=0; i<dynamicList.size(); i++)
		{
			String text = dynamicList.get(i).getText();
			System.out.println("Text is" + text);
			if(text.contains("Bengaluru"))
			{
				dynamicList.get(i).click();
				break;			
				}									
	     }
		
		driver.findElement(By.xpath("//input[@id='hp-widget__sTo']")).sendKeys("Hyderabad");	 
		 try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("Store the To-city flight details in dynamic list ");
			
			List<WebElement>dynamicList1 = driver.findElements(By.xpath("//div[@class='locationFilter autocomplete_to']//*[@id='ui-id-2']"));
			
			
			for(int j=0; j<dynamicList1.size(); j++)
			{	
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String text1 = dynamicList1.get(j).getText();
				System.out.println("Text is" + text1);
				if(text1.contains("Hyderabad, India"))
				{				
					dynamicList1.get(j).click();
					break;			
					}									
		     }
			
			log.info("Select the calendar date after 2 weeks and then search the available flights ");	
			driver.findElement(By.xpath("//*[@class='ui-datepicker-group ui-datepicker-group-first']/table/tbody/tr[5]/td[5]/a")).click();
			driver.findElement(By.xpath("//button[@id='searchBtn']")).click();
		
			log.info("Get the number of flight details ");
			By eachSearchElement = By.xpath("//*[@id='left-side--wrapper']/div[3]");
			List <WebElement> allflightDetails = driver.findElements(eachSearchElement);
			int totalFlights = allflightDetails.size();
			System.out.println("There are " + totalFlights + " present on the page");   
		
	}
	
	@AfterMethod
	public void tearDown() {

		driver.quit();

	}
}
