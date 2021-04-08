package webDriver;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JE {
	WebDriver driver; 
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait= new WebDriverWait(driver, 10);
		//explicitWait = new WebDriverWait(driver, timeout);
		//jsExecutor = (JavascriptExecutor) driver;
		
	}
	@Test
	public void TestJE() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		sleepInSecond(2);
		String domain = (String) executeForBrowser(driver, "return document.domain");
		Assert.assertEquals("live.demoguru99.com", domain);
		String url = (String) executeForBrowser(driver, "return document.URL");
		Assert.assertEquals("http://live.demoguru99.com/", url);
		clickToElementByJS(driver, "//a[text()='Mobile']");
		sleepInSecond(2);
		clickToElementByJS(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span");
		sleepInSecond(2);
		String expected = "Samsung Galaxy was added to your shopping cart.";
		Assert.assertTrue(getInnerText(driver).contains(expected));
		clickToElementByJS(driver, "//a[text()='Customer Service']");
		String titleCustomerService = (String)executeForBrowser(driver, "return document.title");
		Assert.assertEquals("Customer Service", titleCustomerService);
		scrollToBottomPage(driver);
		String Email = randomEmail();
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", Email);
		clickToElementByJS(driver, "//button[@title='Subscribe']");
		sleepInSecond(2);
		String expected2 = "Thank you for your subscription.";
		Assert.assertTrue(getInnerText(driver).contains(expected2));
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		String domain2 = (String) executeForBrowser(driver, "return document.domain");
		Assert.assertEquals("demo.guru99.com", domain2);
		// abc
	}
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}
	public String getInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText");
		return textActual;
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		//explicitWait = new WebDriverWait(driver, timeout);
		//jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	public void sleepInSecond(long Seconds) {
		try {
			Thread.sleep(Seconds * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public WebElement getElement (WebDriver driver ,String XpathLocator) {
		WebElement Element = driver.findElement(By.xpath(XpathLocator));
		return Element;
	}
	public String randomEmail() {
		Random rand = new Random();
		String emailR = "automation" + rand.nextInt(999) + "@gmail.com";
		return emailR;
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
		
	}

}
