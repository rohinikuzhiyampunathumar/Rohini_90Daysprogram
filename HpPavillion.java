package first21DaysTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HpPavillion {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,50);		
//1) Go to https://store.hp.com/in-en/
		driver.get("https://store.hp.com/in-en/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Mouse over on Laptops menu and click on Pavilion
		Actions builder = new Actions(driver);
		WebElement eleLaptop = driver.findElementByXPath("(//span[text()='Laptops'])[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Laptops'])[1]")));
		builder.moveToElement(eleLaptop).perform();
		WebElement elePavilion = driver.findElementByXPath("//span[text()='Pavilion']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(elePavilion));
		elePavilion.click();
//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		WebElement eleCookiesClose = driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(eleCookiesClose));
		eleCookiesClose.click();
		WebElement eleBlackout = driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inside_closeButton fonticon icon-hclose']")));
		eleBlackout.click();
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		WebElement eleProcessor = driver.findElementByXPath("(//input[@class='product-filter-checkbox'])[2]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='product-filter-checkbox'])[2]")));
		eleProcessor.click();
//4) Hard Drive Capacity -->More than 1TB
		Thread.sleep(5000);
		WebElement ele1TB = driver.findElementByXPath("//span[contains(text(),'More than 1 TB')]");
		ele1TB.click();
		System.out.println("1TB clicked");
//5) Select Sort By: Price: Low to High
		Thread.sleep(3000);
		WebElement elesort = driver.findElementByXPath("(//select[@id='sorter'])[1]");
		Select ddSort = new Select(elesort);
		ddSort.selectByValue("price_asc");
//6) Print the First resulting Product Name and Price
		Thread.sleep(3000);
		WebElement eleFirstResult = driver.findElementByXPath("(//a[@class='product-item-link'])[1]");
		eleFirstResult.click();
//7) Click on Add to Cart
		WebElement elePrice = driver.findElementByXPath("(//span[@class='price-wrapper '])[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOf(elePrice));
		String price = elePrice.getAttribute("data-price-amount");
		int expPrice = Integer.parseInt(price);
		WebElement eleAddCart = driver.findElementByXPath("//button[@id='product-addtocart-button']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(eleAddCart));
		eleAddCart.click();
		Thread.sleep(3000);
//8) Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.switchTo().activeElement();
		Thread.sleep(3000);
		driver.findElementByXPath("(//button[@class='action primary continue-shopping simple-popup-continue-button']/span)[2]").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='action showcart']")));
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
//9) Check the Shipping Option --> Check availability at Pincode
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='pincode']")));
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600117");
		driver.findElementByXPath("//button[@class='primary standard_delivery-button']").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='delivery-days']/dl/dd/span/following-sibling::span")));
		String availability = driver.findElementByXPath("//div[@class='delivery-days']/dl/dd/span/following-sibling::span").getText();
		System.out.println(availability);
//10) Verify the order Total against the product price
		String actualPriceAllChars = driver.findElementByXPath("(//span[@class='price'])[7]").getText();
		String actualPrice = actualPriceAllChars.replaceAll("\\D", "");
		int actPrice = Integer.parseInt(actualPrice);
//11) Proceed to Checkout if Order Total and Product Price matches
		Assert.assertEquals(actPrice, expPrice);
		driver.findElementByXPath("(//button[@id='sendIsCAC'])[1]").click();
//12) Click on Place Order
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Place Order'])[4]")));
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();
//13) Capture the Error message and Print
		String errMsg = driver.findElementByXPath("//div[@class='inside-notice-content-container']/p/b").getText();
		System.out.println(errMsg+"\n test case passed\n");
//14) Close Browser
		driver.quit();	
	}

}
