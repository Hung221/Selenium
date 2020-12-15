package webDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	By job1 = By.id("job1");
	String SelectedResult="";
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void isNotMultibleSelected() {
		select = new Select(driver.findElement(job1));
		Assert.assertFalse(select.isMultiple());
		// index
		select.selectByIndex(5);
		SelectedResult = select.getFirstSelectedOption().getText();
		Assert.assertEquals(SelectedResult, "Security Testing");
		// visible text
		Sleep(2);
		select.selectByVisibleText("Security Testing");
		SelectedResult = select.getFirstSelectedOption().getText();
		Assert.assertEquals(SelectedResult, "Security Testing");
		//value
		select.selectByValue("security");
		SelectedResult = select.getFirstSelectedOption().getText();
		Assert.assertEquals(SelectedResult, "Security Testing");
		// verify size
		int sizeS = select.getOptions().size();
		System.out.println(sizeS);
		Assert.assertEquals(sizeS, 10);	
	}
	@Test
	public void isMultibleSelected() {
		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		List <WebElement> ItemSelected = select.getAllSelectedOptions();
		List <String> TextOfItemselected = new ArrayList<String>();
		for (WebElement item: ItemSelected) {
			TextOfItemselected.add(item.getText());
		}
		System.out.println(TextOfItemselected);
		Assert.assertTrue(TextOfItemselected.contains("Automation"));
		Assert.assertTrue(TextOfItemselected.contains("Mobile"));
		Assert.assertTrue(TextOfItemselected.contains("Desktop"));
		// deSelect 
		//select.deselectAll();
		//Sleep(2);
		//Assert.assertEquals(TextOfItemselected, null);
	}
	public void Sleep(long tts) {
		try {
			Thread.sleep(tts * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		} 
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
