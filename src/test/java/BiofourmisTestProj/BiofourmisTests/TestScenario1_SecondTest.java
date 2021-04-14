package BiofourmisTestProj.BiofourmisTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestScenario1_SecondTest {
	
	public static String browser = "chrome"; 
	// Used only chrome because the workflow was having problem to run the two browsers at the same time, you can test by Passing String browser in setup method. 
	// Run as test ng Suite from testng.xml
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(TestScenario1_FirstTest.class);
	
	@Parameters("browser")
	@BeforeMethod	
	public void setup() { 
	
		if(browser.equals("chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "True");	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();		
		
		} else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Vaneet Bish\\TestAutomation\\geckodriver.exe");
			driver = new FirefoxDriver();
			//log.info("Firefox Browser launched !!!");
		}
	    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    
	    
	}
	
	@Test
	public void validatePayment() 
	{
		log.info("login with newly created user");
        driver.findElement(By.xpath("//*[@id='email']")).isEnabled();
	    driver.findElement(By.xpath("//*[@id='email']")).sendKeys("vaneet12345@gmail.com"); 
	    driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("vaneet12345");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("Mouser over to woment tshirt page");
		WebElement btnWomen = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(btnWomen).build().perform();
		
		List<WebElement> anchorTags = btnWomen.findElements(By.tagName("a"));
		System.out.println("Numer of Elements" + anchorTags.size());
		
		for(WebElement menu: anchorTags) {
			if(menu.getText().equals("T-shirts")) {
				menu.click();
				break;
			}
		}
		log.info("Add to cart and proceed to checkout");
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img")).click();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button")).click();
		WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"cgv\"]"));
		checkBox.click();
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
		driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();			
		
	}
	
	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	

}
