package BiofourmisTestProj.BiofourmisTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestScenario2_MMP {

	public static String browser = "chrome"; 
	// Used only chrome because the workflow was having problem to run the two browsers at the same time, you can test by Passing String browser in setup method. 
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(TestScenario2_MMP.class);
	
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
	public void validatemakemyTrip() {
		
		log.info("Select Hyderabad to Banglore flights");
		driver.findElement(By.xpath("//img[@alt='Make My Trip']")).click();
		driver.findElement(By.xpath("//li[@class='selected']//span[@class='tabsCircle appendRight5']")).isSelected();
		System.out.println("One way selected");
		driver.findElement(By.xpath("//div[@class='fsw_inputBox searchCity inactiveWidget ']/label")).click();
		driver.findElement(By.xpath("//div[@class='hsw_autocomplePopup autoSuggestPlugin']/div/input")).sendKeys("Hyderabad");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("Store the from-city flight details in dynamic list ");
		List<WebElement>dynamicList = driver.findElements(By.cssSelector("p[class='font14 appendBottom5 blackText"));
		
		for(int i=0; i<dynamicList.size(); i++)
		{
			String text = dynamicList.get(i).getText();
			System.out.println("Text is" + text);
			if(text.contains("Hyderabad"))
			{
				dynamicList.get(i).click();
				break;			
				}									
	     }
		
		driver.findElement(By.xpath("//div[@class='hsw_autocomplePopup autoSuggestPlugin']/div/input")).sendKeys("Bengaluru");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("Store the To-city flight details in dynamic list ");
		
		List<WebElement>dynamicList1 = driver.findElements(By.cssSelector("p[class='font14 appendBottom5 blackText"));
		for(int j=0; j<dynamicList1.size(); j++)
		{
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String text1= dynamicList1.get(j).getText();
			System.out.println("Text is" + text1);
			if(text1.contains("Bengaluru"))
			{
				dynamicList1.get(j).click();
				break;
		     }
		
		}	
		
		log.info("Select the calendar date after 2 weeks and then search the available flights ");	
		driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label, 'Apr 30 2021')]")).click();
		driver.findElement(By.xpath("//a[contains(text(), 'Search')]")).click();
		
		log.info("Filter Indigo and Non stop filter criteria ");
		driver.findElement(By.xpath("//div[@class='makeFlex hrtlCenter flexOne']//span[@title='IndiGo']")).click();
		driver.findElement(By.xpath("//div[@class='makeFlex hrtlCenter flexOne']//span[@title='Non Stop']")).click();
		driver.findElement(By.xpath("//*[@id=\"search-button\"]")).click();
	
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
