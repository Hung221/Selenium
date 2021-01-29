package webDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Alert_someTest {
	WebDriver driver;
	JavascriptExecutor JsExecutor;
	Actions action;
	WebDriverWait explicitWait;
	Alert alert;
	String project_location = System.getProperty("user.dir");
	String fireFoxAuthen = project_location + "\\AutoIt\\authen_firefox.exe" ;
	String username = "admin";
	String password = "admin";
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		JsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
	}

	public void Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		SleepInSeconds(3);
		alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
	}

	public void Prompt_Alert() {
		String Key = "Testing";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		SleepInSeconds(3);
		alert = driver.switchTo().alert();
		alert.sendKeys(Key);
		alert.accept();
		String Compare = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertTrue(Compare.contains(Key));
	}

	public void Authen_Alert_ByLink() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).isDisplayed();
		  // In case link from other page call the funtion getAuthenticationUrl
	}
	@Test
	public void Authen_Alert_ByLink_AutoIT() throws IOException {
		Runtime.getRuntime().exec(new String[]{fireFoxAuthen,username,password});
		// Excute Auto IT script
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).isDisplayed();
		
		
	}
	public String getAuthenticationUrl (String oldUrl,String user,String pass) {
		String newURL = null;
		String urlValue[]= oldUrl.split("//");
		newURL = urlValue[0] +"//" + user +":" + pass + "@" + urlValue[1];
		return newURL;
	}
	public void SleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
