package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ec2_Web_Element {
	WebDriver driver;

	@BeforeClass
	public void beforeCla() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.manage().window().maximize();
		driver.get("https://live.demoguru99.com/");
	}
	
	@Test
	public void Test1(){
		
	}
	
	@AfterClass
	public void After() {
		driver.quit();
		
	}
}
