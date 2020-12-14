package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Drop_down_custom {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.ph/");
		//5 Steps de dung 
		// Wait visible o dang viewport: nhung gi co the nhin thay
		// Neu co element can drag de thay dung wait present
		// Cac elenment mat sau khi click dung Dev tool Pause script or dung FireFox old version

}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

