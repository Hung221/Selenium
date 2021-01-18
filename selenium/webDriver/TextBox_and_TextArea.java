package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

@Test
public class TextBox_and_TextArea {
	// Text box ko co xuong dong
	// Get value text by attribute "value"
	// \n: la xuong dong \t: tab
	// text box is disable --> remove disable by JS --> send key--> Expected: should NOT submit success
	// chan quang cao bang cach add vao host in drivers
	// collaps and expand code setting in Windows --> ..--> Keys
	//Editor clone de chia 2 man hinh eclips
	WebDriver driver;
	String User = "mngr299432 ", Pass = "qyjyjYz", Manager;
	By LoginBtn = By.name("btnLogin");
	By Verify = By.xpath("//tr[@class=\"heading3\"]/td");
	String Name ="Autoo", DoB= "22/11/1996", Add= "343 PXL PN", City="HCM", State="On", Pin="123456", Phone="0123456789", Email="abc@gmail.com", PassWord="123456789", emailAdd="";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
	}
	public void Text_box() {
		driver.findElement(By.name("uid")).sendKeys(User);
		driver.findElement(By.name("password")).sendKeys(Pass);
		driver.findElement(LoginBtn).click();
		// Verify login successfully
		Manager =driver.findElement(Verify).getText().trim();
//		System.out.println(Manager);
//		System.out.println("Manger Id : "+ User);
		Assert.assertEquals(Manager,"Manger Id : mngr299432");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		// Input value to submit
		SleepInSecond(2);
		driver.findElement(By.name("name")).sendKeys(Name);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.name("dob")).sendKeys(DoB);
		driver.findElement(By.name("addr")).sendKeys(Add);
		driver.findElement(By.name("city")).sendKeys(City) ;
		driver.findElement(By.name("state")).sendKeys(State);
		SleepInSecond(2);
		driver.findElement(By.name("pinno")).sendKeys(Pin);
		driver.findElement(By.name("telephoneno")).sendKeys(Phone);
		driver.findElement(By.name("emailid")).sendKeys(emailAdd);
		driver.findElement(By.name("password")).sendKeys(PassWord);
		driver.findElement(By.name("sub")).click();
		SleepInSecond(2);
		// Verify in4 has been registered
		String IdUser = driver.findElement(By.xpath("//table[@id='customer']//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(IdUser);
		
		
	}
	public void SleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void EmailRand() {
			Random rand = new Random();
			emailAdd = "automation" + rand.nextInt(999) + "@gmail.com";;
		}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	}
