package webDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import netscape.javascript.JSException;

public class UserInteraction {
// release dung cho click and hold
// Khi chay case related to UserInteraction ko dung den chuot & ban phim
// # giua driver.click and action.click action click co hanh dong move truoc moi click
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor JsExecutor;
	Actions action;
	String ProjectFolder = System.getProperty("user.dir");
	String JsFilePath = ProjectFolder + "\\/DragAndDrop\\drag_and_drop_helper.js";
	String JqueFilePath = ProjectFolder + "\\/DragAndDrop\\jquery_load_helper.js";
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		JsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	public void HoverTest1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		WebElement Hover1 = driver.findElement(By.id("age"));
		SleepInSecond(3);
		action.moveToElement(Hover1).perform();
		AssertJUnit.assertTrue(driver.findElement(By.cssSelector(".ui-tooltip-content")).isDisplayed());
		
	}
	
	public void HoverTest2() {
		driver.get("https://www.myntra.com/");
		WebElement Hover2 =driver.findElement(By.xpath("//a[@data-group='kids']"));
		action.moveToElement(Hover2).perform();
		SleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		String a = driver.getTitle();
		System.out.println(a);
		AssertJUnit.assertEquals("Kids Home Bath - Buy Kids Home Bath online in India", a);
	}
	
	public void HoverTest3() {
		driver.get("https://hn.telio.vn/");
		SleepInSecond(3);
		WebElement Hover3 = driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//span[text()='Bánh kẹo']"));
		action.moveToElement(Hover3).perform();
		SleepInSecond(3);
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@class='cdz-main-menu left-navigation hidden-xs']//ul[@class='groupdrop-link']//a[text()='Lương khô']")).isDisplayed());
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
	}

	public void TestCaseClickAndHold() {
		//click from number 1
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		WebElement Number1 = driver.findElement(By.xpath("//li[text()='1']"));
		WebElement Number4 = driver.findElement(By.xpath("//li[text()='4']"));
		action.clickAndHold(Number1).moveToElement(Number4).release().perform();
		// Verify 4 number is selected 
		AssertJUnit.assertEquals(4, driver.findElements(By.xpath("//li[@class= 'ui-state-default ui-selectee ui-selected']")).size()); 
	}

	public void TestCaseClickRandom() {
		//click from number 1
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		// WebElement Number1 = driver.findElement(By.xpath("//li[text()='1']"));
		//WebElement Number4 = driver.findElement(By.xpath("//li[text()='4']"));
		List <WebElement> AllNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(AllNumber.get(0))
		.click(AllNumber.get(1))
		.click(AllNumber.get(4))
		.click(AllNumber.get(6));
		action.keyDown(Keys.CONTROL).perform();
		
		// Verify 4 number is selected 
		AssertJUnit.assertEquals(4, driver.findElements(By.xpath("//li[@class= 'ui-state-default ui-selectee ui-selected']")).size()); 
	}
	
	public void TestDoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement DoubleBt = driver.findElement(By.xpath("//div[@class='container']//button[text()='Double click me']"));
		action.doubleClick(DoubleBt).perform();
		SleepInSecond(2);
		// The text will show
		String textDis = driver.findElement(By.xpath("//div[@class='container']//button[text()='Double click me']/following-sibling::p[text()='Hello Automation Guys!']")).getText();
		AssertJUnit.assertEquals("Hello Automation Guys!", textDis);
	}
	
	public void RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement RightClickEle = driver.findElement(By.xpath("//div[@class='document']//span[text()='right click me']"));
		action.contextClick(RightClickEle).perform();
		SleepInSecond(2);
		WebElement QuitOption = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//span[text()='Quit']"));
		if (QuitOption.isDisplayed() == true) {
			action.moveToElement(QuitOption).perform();
			if (driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed()) {
				System.out.println("Hover successful on Quit Option");
				action.click(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();;
				SleepInSecond(2);
				driver.switchTo().alert().accept();
				AssertJUnit.assertFalse(QuitOption.isDisplayed());
			}
		}
		else {
			System.out.println("Can NOT find the Element Quit");
		}
	}
	
	public void TestDragAndDrop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().window().maximize();
		SleepInSecond(2);
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		SleepInSecond(2);
		WebElement Drag = driver.findElement(By.cssSelector("#droptarget"));
		WebElement Drop = driver.findElement(By.cssSelector("#draggable"));
		action.dragAndDrop(Drag, Drop).perform();
		//Verify Message 
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());
		//RGBA sang Hex corlor
		//Assert.assertEquals(Drop.getCssValue("background-color"), "#03a9f4");
		AssertJUnit.assertEquals(Drop.getCssValue("background-color"), "rgb(3,169,244)");
		
	}
	@Test
	public void DragAndDropHTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		driver.manage().window().maximize();
		// Get locater 2 object
		String A = "#column-a";
		String B = "#column-b";
		// Doc noi dung file
		String jsContent =readFile(JsFilePath);
		//String JqueContent = readFile(JqueFilePath);
		// Nhung Jque vao
		// Error: call back a funtion, it mean Jque is active by default
		//JsExecutor.executeScript(JqueContent);
		// A to B
		jsContent = jsContent + "$(\"" + A + "\").simulateDragDrop({ dropTarget: \"" + B + "\"});";
		// Nhung JS
		JsExecutor.executeScript(jsContent);
		SleepInSecond(3);
		AssertJUnit.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		JsExecutor.executeScript(jsContent);
		SleepInSecond(3);
		AssertJUnit.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
	}
	public boolean isElementDisplayed(String locator) {
		
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	public String readFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
}
	public void SleepInSecond(long secondsSlepp) {
		try {
			Thread.sleep(secondsSlepp * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
