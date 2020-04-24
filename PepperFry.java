package first21DaysTestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,50);
		int count = 0;
//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='popup-close'])[5]")));
		driver.findElementByXPath("(//a[@class='popup-close'])[5]").click();
//2) Mouseover on Furniture and click Office Chairs under Chairs
		WebElement eleFurniture = driver.findElementByXPath("(//a[text()='Furniture'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleFurniture).perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Office Chairs']")));
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
//3) click Executive Chairs
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Executive Chairs']")));
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
//4) Change the minimum Height as 50 in under Dimensions
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='clipFilterDimensionHeightValue'])[1]")));
		WebElement eleMinHgt = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		eleMinHgt.clear();
		eleMinHgt.sendKeys("50", Keys.ENTER);
//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Poise Executive Chair in Black Colour']")));
		WebElement elechairWL = driver.findElementByXPath("(//a[text()='Poise Executive Chair in Black Colour']/following::a[@id='clip_wishlist_'])[1]");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", elechairWL);
		count++;
//6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Homeware'])[1]")));
		WebElement eleHomeWare = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
		builder.moveToElement(eleHomeWare).perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Pressure Cookers']")));
		driver.findElementByXPath("//a[text()='Pressure Cookers']").click();
//7) Select Prestige as Brand
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='brandsnamePrestige']")));
		driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
//8) Select Capacity as 1-3 Ltr
		Thread.sleep(3000);
		WebElement eleCookerqty = driver.findElementByXPath("//label[contains(text(),'1 Ltr - 3 Ltr')]");
		eleCookerqty.click();
//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		Thread.sleep(2000);
		WebElement eleCookerWL = driver.findElementByXPath("(//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']/following::a[@id='clip_wishlist_'])[1]");
		eleCookerWL.click();
		count++;
//10) Verify the number of items in Wishlist
		Thread.sleep(2000);
		String actWLString = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		System.out.println("The String value is "+actWLString);
		int actWLItems =  Integer.parseInt(actWLString);
		System.out.println("The count is "+count);
		System.out.println("The items in wishList is "+actWLItems);
		Assert.assertEquals(actWLItems, count);
//11) Navigate to Wishlist
		driver.findElementByXPath("//a[contains(@class,'wistlist_img')]").click();
//12) Move Pressure Cooker only to Cart from Wishlist
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr')])[3]/following::a[@class='addtocart_icon']")));
		driver.findElementByXPath("(//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr')])[3]/following::a[@class='addtocart_icon']").click();
//13) Check for the availability for Pincode 600128
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='srvc_pin_text']")));
		WebElement elePinCode = driver.findElementByXPath("//input[@class='srvc_pin_text']");
		elePinCode.clear();
		elePinCode.sendKeys("600128", Keys.TAB);
		driver.findElementByXPath("//a[text()='Check']").click();
//14) Click Proceed to Pay Securely
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Proceed to pay securely ']")));
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='PLACE ORDER'])[1]")));
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ordersummary_accordian_header']")));
		driver.findElementByXPath("//div[@id='ordersummary_accordian_header']").click();
//16) Capture the screenshot of the item under Order Item
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='onepge_ordersummary slick-slide slick-current slick-active']/figure")));
		WebElement eleCartItem = driver.findElementByXPath("//li[@class='onepge_ordersummary slick-slide slick-current slick-active']/figure");
		File src = eleCartItem.getScreenshotAs(OutputType.FILE);
		File dst = new File("./Snaps/PepperFryCart.png");
		FileUtils.copyFile(src, dst);
//17) Close the browser
		driver.manage().deleteAllCookies();
		driver.quit();
		
	}

}
