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

public class checkBox {
	//Note neu default thi se hight light khi inspect, customs se bi hidden
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	// . is class
	By LoginBut = By.cssSelector("button.fhs-btn-login");
	// # is id
	By User = By.cssSelector("input#login_username");
	By Pass = By.xpath("//input[@id='login_password']");
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.fahasa.com/customer/account/create");
		explicitWait = new WebDriverWait(driver, 30);
	}
	@Test
	public void TestCheckBox() {
		//negative login page
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']/a")).click();
		// verify login button is disable
		Assert.assertFalse(isEnableorNot(LoginBut));
		SleepInSecond(1);
		driver.findElement(User).sendKeys("HungHo@gmail.com");
		driver.findElement(Pass).sendKeys("123456");
		// Verify LoginBt is Enable after typing
		SleepInSecond(1);
		Assert.assertTrue(isEnableorNot(LoginBut));
		driver.navigate().refresh();
		//negative to loginPage
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']/a")).click();
		SleepInSecond(1);
		removeAtributbte(LoginBut);
		SleepInSecond(2);
		Assert.assertTrue(isEnableorNot(LoginBut));
		 //div[@class='fhs-input-box checked-error']/label[contains(.,'Số điện thoại/Email')]/following-sibling::div[@class='fhs-input-alert']
		driver.findElement(LoginBut).click();
		Assert.assertEquals("Thông tin này không thể để trống", driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']/label[contains(.,'Số điện thoại/Email')]/following-sibling::div[@class='fhs-input-alert']")).getText());
	}
	public boolean isEnableorNot(By by){
		//WebElement element = driver.findElement(by);
		if (driver.findElement(by).isEnabled()) {
			System.out.println("This button is Enable");
			return true;
		}
		else {
			System.out.println("This button is Disable");
			return false;
		}
	}
	public void removeAtributbte(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	public void SleepInSecond(long secondsSlepp) {
		try {
			Thread.sleep(secondsSlepp * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
