package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class CheckBox2 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	By CheckBox = By.xpath("//input[@id='eq5']");
	By Radio = By.xpath("//input[@id='engine3']");
	@BeforeClass
	public void beforeClass(){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		//driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		driver.get("https://material.angular.io/components/radio/examples");
		
	}
	public void CheckBoxDefault() {
		if (VerifyCheckedOrNot(CheckBox)== true) {
			System.out.println("This checkBox is checked");
		}
		else {
			driver.findElement((CheckBox)).click();
			System.out.println("This checkbox is checked recently");
		}	
		}
	
	public void RadioCheck() {
		if (VerifyCheckedOrNot(Radio) == true) {
			System.out.println("This radio button is checked");
		}
		else {
				driver.findElement(Radio).click();
				System.out.println("checked success");
			}
	}
	@Test
	public void Customs(){
		// 2 Ways: 1 select by div and verify bu button, or select by button via JS
		By CustomRadio = By.xpath("//input[@id='mat-radio-4-input']");
		
		if (VerifyCheckedOrNot(CustomRadio)==true)
		{
			System.out.println("This radio button is checked");
		}
		else {
			ClickByJs(CustomRadio);
			if (VerifyCheckedOrNot(CustomRadio)== true) {
			System.out.println("checked success");
			}
			else {
				System.out.println("Passed");
			}
		}
	} 	
	public boolean VerifyCheckedOrNot(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void ClickByJs(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	}


