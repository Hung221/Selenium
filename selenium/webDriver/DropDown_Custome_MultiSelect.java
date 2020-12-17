

package webDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import junit.framework.Assert;

public class DropDown_Custome_MultiSelect {
	WebDriver driver;
	WebDriverWait explicitWait;
	String [] AllExpected = {"January","April"};
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		explicitWait = new WebDriverWait(driver, 30);
}
	@Test
	public void JqueryCustome() {
		HandleJque("(//button[@class='ms-choice'])[1]", 
				"(//button[@class='ms-choice'])[1]/following-sibling::div//input", 
				AllExpected);
		Assert.assertEquals("19",driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText());
		
	}
	public void HandleJque(String ParentItem, String AllItem,String []AllExpected) {
		// select parent to show all selected options //span[@id='speed-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']
		driver.findElement(By.xpath(ParentItem)).click();
		// Wait to all elements is shown
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(AllItem)));
		// Luu lai thanh List va duyet qua cac Item, AllItem la xapth dai dien cho all options
		List<WebElement> ListAllElements= driver.findElements(By.xpath(AllItem)); 
		int ListLen = ListAllElements.size();
		for (WebElement item:ListAllElements) {
			String Actualtem = item.getText();
			for (String Expected:AllExpected) {
				// Kiem tra so voi Text can chon
				if (Actualtem.equals(Expected)) {
					item.click();
					break;
												}
										}
																					}
												}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}


