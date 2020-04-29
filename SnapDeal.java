package first21DaysTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SnapDeal {
	public static ChromeDriver driver;
	public static WebDriverWait wt;
	public static int ItemPrice;
	public static int deliItemCharge;
	public static int expItemTotal;
	
	
	public static void calcDeliveryPriceTotal() throws InterruptedException {
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='payBlkBig']")));
		String priceString = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		ItemPrice = Integer.parseInt(priceString);
		String deliAllchars = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String deliSpace = deliAllchars.replaceAll("[\\D]", "");
		deliItemCharge = Integer.parseInt(deliSpace);
		expItemTotal = deliItemCharge+ItemPrice;
		}
	
	public static void main(String[] args) throws InterruptedException {
		

		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		wt = new WebDriverWait(driver,50);
//1) Go to https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Mouse over on Toys, Kids' Fashion & more and click on Toys
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Toys, Kids')]")));
		WebElement eleToysCategory = driver.findElementByXPath("//span[contains(text(),'Toys, Kids')]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleToysCategory).perform();
//3) Click Educational Toys in Toys & Games
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Educational Toys']")));
		driver.findElementByXPath("//span[text()='Educational Toys']").click();
//‎4) Click the Customer Rating 4 star and Up 
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='avgRating-4.0']")));
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
//5) Click the offer as 40-50
		Thread.sleep(2000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[contains(@for,'50')])[1]")));
		driver.findElementByXPath("(//label[contains(@for,'50')])[1]").click();
//6) Check the availability for the pincode
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your pincode']")));
		WebElement elezipCode = driver.findElementByXPath("//input[@placeholder='Enter your pincode']");
		elezipCode.click();
		elezipCode.sendKeys("600117", Keys.TAB);
		driver.findElementByXPath("//button[text()='Check']").click();
//7) Click the Quick View of the first product 
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='product-title'])[1]")));
		WebElement eleTitle = driver.findElementByXPath("(//p[@class='product-title'])[1]");
		builder.moveToElement(eleTitle).perform();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(text(),'Quick View')])[1]")));
		driver.findElementByXPath("(//div[contains(text(),'Quick View')])[1]").click();
//8) Click on View Details
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'view details')]")));
		driver.findElementByXPath("//a[contains(text(),'view details')]").click();
//9) Capture the Price of the Product and Delivery Charge
		calcDeliveryPriceTotal();
		System.out.println("Toy price alone is "+ItemPrice+" and delivery charge is "+deliItemCharge+ " and the calculated total Price is "+expItemTotal);
//10) Validate the You Pay amount matches the sum of (price+deliver charge)
		driver.findElementByXPath("(//div[@id='add-cart-button-id'])[1]").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='you-pay']/span[@class='price']")));
		String actTotalAllchars = driver.findElementByXPath("//div[@class='you-pay']/span[@class='price']").getText();
		String actTotalString = actTotalAllchars.replaceAll("[\\D]", "");
		int actItemTotal = Integer.parseInt(actTotalString);		
		System.out.println("Actual Toy Total in You pay is "+actItemTotal+" and the expected total is "+expItemTotal);
		if(actItemTotal==(expItemTotal)) {
			System.out.println("actual and expected toy price matches, validation passed");
//11) Search for Sanitizer
			WebElement eleSearchIp = driver.findElementById("inputValEnter");
			eleSearchIp.click();
			eleSearchIp.sendKeys("sanitizer", Keys.ENTER);
//12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'BioAyurveda Neem Power  Hand Sanitizer')]")));
			WebElement eleSanit = driver.findElementByXPath("//p[contains(text(),'BioAyurveda Neem Power  Hand Sanitizer')]");
			builder.moveToElement(eleSanit).perform();
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(text(),'Quick View')])[1]")));
			driver.findElementByXPath("(//div[contains(text(),'Quick View')])[1]").click();
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'view details')]")));
			driver.findElementByXPath("//a[contains(text(),'view details')]").click();	
//13) Capture the Price and Delivery Charge
			calcDeliveryPriceTotal();
			System.out.println("Sanitizer price alone is "+ItemPrice+" and delivery charge is "+deliItemCharge+ " and the calculated total Price is "+expItemTotal);
//14) Click on Add to Cart
			Thread.sleep(1000);
			driver.findElementByXPath("(//div[@id='add-cart-button-id'])[1]").click();
			Thread.sleep(2000);
//15) Click on Cart 
			driver.findElementByXPath("//span[text()='Cart']").click();
//16) Validate the Proceed to Pay matches the total amount of both the products
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='item-subtotal-black'])[1]")));
			String firstItemAllChars = driver.findElementByXPath("(//span[@class='item-subtotal-black'])[1]").getText();
			String firstItemString = firstItemAllChars.replaceAll("\\D", "");
			int firstItem = Integer.parseInt(firstItemString);
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='item-subtotal-black'])[4]")));
			String secItemAllChars = driver.findElementByXPath("(//span[@class='item-subtotal-black'])[4]").getText();
			String secItemString = secItemAllChars.replaceAll("\\D", "");
			int secItem = Integer.parseInt(secItemString);
			String pPPriceAllChars = driver.findElementByXPath("//input[@class='btn btn-xl rippleWhite cart-button']").getAttribute("value");
			String ppPriceString = pPPriceAllChars.replaceAll("\\D", "");
			int ppPrice = Integer.parseInt(ppPriceString);
			int actualTotal = firstItem+secItem;
			System.out.println("The actual total is "+actualTotal+" and the proceed to pay price is "+ppPrice);
			if(actualTotal==ppPrice) {
				System.out.println("testCase Passed");
			}
			else {
				System.out.println("Test case failed");
			}
		}
		else {
			System.out.println("actual and expected toy price DOESNOT match, Validation failed");
		}
		
//17) Close all the windows
		driver.manage().deleteAllCookies();
		driver.quit();
		
	}

}
