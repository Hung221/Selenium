package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Drop_Down {
// cac loai drop down 
	//normal: select --> options dung selenium de handle
	// Loai Custom: ul --> li/ div-->div ..
	WebDriver driver;
	Select select;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void Testcase1() {
		select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(select.isMultiple());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
