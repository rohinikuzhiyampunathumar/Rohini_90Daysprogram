package first21DaysTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,30);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int discountAmt = 0;
//1)Go to https://www.ajio.com/shop/sale 
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ic-close-quickview']")));
		driver.findElementByXPath("//div[@class='ic-close-quickview']").click();
//2) Enter Bags in the Search field and Select Bags in Women Handbags 
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")));
		WebElement eleSearch = driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']");
		eleSearch.click();
		eleSearch.clear();
		eleSearch.sendKeys("bags");
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Bags in '])[1]/parent::a")));
		driver.findElementByXPath("(//span[text()='Bags in '])[1]/parent::a").click();
//3) Click on five grid and Select SORT BY as "What's New" 
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'five-grid-container')]")));
		driver.findElementByXPath("//div[contains(@class,'five-grid-container')]").click();
		Thread.sleep(1000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='filter-dropdown']/select")));
		WebElement eleSort = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select sortdd = new Select(eleSort);
		sortdd.selectByVisibleText("What's New");
//4) Enter Price Range Min as 2000 and Max as 5000  
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='price']")));
		WebElement elePrice = driver.findElementByXPath("//span[text()='price']");
		Actions builder = new Actions(driver);
		builder.moveToElement(elePrice);
		elePrice.click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='minPrice']")));
		driver.findElementByXPath("//input[@id='minPrice']").click();
		driver.findElementByXPath("//input[@id='minPrice']").sendKeys("2000",Keys.TAB);
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='maxPrice']")));
		driver.findElementByXPath("//input[@id='maxPrice']").click();
		driver.findElementByXPath("//input[@id='maxPrice']").sendKeys("5000",Keys.ENTER);
//5) Click on the product "Puma Ferrari LS Shoulder Bag"  
		Thread.sleep(1000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Ferrari LS Shoulder Bag']/parent::div")));
		WebElement elePuma = driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']/parent::div");
		js.executeScript("arguments[0].click();", elePuma);
		Thread.sleep(1000);
//6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon  
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='prod-price-section']/div")));
		String actualPriceAllChars = driver.findElementByXPath("//div[@class='prod-price-section']/div").getText();
		String actualPrice = actualPriceAllChars.replaceAll("\\D","");
		int expPriceInt = Integer.parseInt(actualPrice);
		System.out.println("Actual Price is "+expPriceInt);
		String offerText = driver.findElementByXPath("//div[@class='promo-desc']").getText();
		String offerMinAmt = offerText.substring(22, 26);
		int offerMinAmtInt = Integer.parseInt(offerMinAmt);
		System.out.println("Offer min amount is "+offerMinAmtInt);
		String couponCodeAllChars = driver.findElementByXPath("//div[@class='promo-title']").getText();
		String couponCodeString = couponCodeAllChars.replaceAll("\n", "");
		String coupCode= couponCodeString.substring(8);
		System.out.println("The actual coupon code is "+coupCode);
		String discPriceAllChars = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
		String discPrice = discPriceAllChars.replaceAll("\\D", "");
		int discPriceInt = Integer.parseInt(discPrice);
		System.out.println("The discounted Price is "+discPriceInt);
		if(expPriceInt>offerMinAmtInt) {
			discountAmt = expPriceInt-discPriceInt;
			System.out.println("The discount amount is "+discountAmt);
//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
			driver.findElementByXPath("//div[@class='edd-pincode-msg-container']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//input[@name='pincode']").click();
			driver.findElementByXPath("//input[@name='pincode']").sendKeys("635001");
			driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
			int count=0;
			try {
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='edd-message-success-details-highlighted']")));
			String expDeliDate = driver.findElementByXPath("//span[@class='edd-message-success-details-highlighted']").getText();
			System.out.println("Expected Delivery Date is"+expDeliDate);
			}			
			catch(Exception e) {
				System.out.println("Item not available for zip code provided");
				count++;
			}
//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
			finally{
				if(count==0) {
				driver.findElementByXPath("//div[@class='other-info-toggle']").click();
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='other-info']")));
			String custAdd = driver.findElementByXPath("//span[@class='other-info']").getText();
			System.out.println("The customer care address is "+custAdd);
//9) Click on ADD TO BAG and then GO TO BAG
			driver.findElementByXPath("//div[@class='pdp-addtocart-button']").click();
			Thread.sleep(1000);
			//wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='GO TO BAG']/parent::div/parent::a")));
			WebElement eleGoBag = driver.findElementByXPath("//span[text()='GO TO BAG']/parent::div/parent::a");
			js.executeScript("arguments[0].click()", eleGoBag);
//10) Check the Order Total before apply coupon  
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='price-value bold-font']")));
			String actualOrderAmtAll = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
			String actualOrderAmt = actualOrderAmtAll.replaceAll("[^0-9.]", "");
			String actualTotal = "";
			int num = 0;
			for(int i=0;i<actualOrderAmt.length();i++) {
				if(actualOrderAmt.charAt(i)!='.') {
					num++;
					actualTotal= actualTotal+actualOrderAmt.charAt(i);
				}
				else {
					if(num>0) {
						break;
					}
					if(num>0) {
						break;
					}
				}
			}
			double actualOrderDouble = Double.parseDouble(actualTotal);
			int actualOrderInt = (int) actualOrderDouble;
			System.out.println("The integer value of order total is "+actualOrderInt);
			if(actualOrderInt==expPriceInt) {
				System.out.println("Order price matches");
//11) Enter Coupon Code and Click Apply  
				driver.findElementById("couponCodeInput").sendKeys(coupCode);
				Thread.sleep(500);
				driver.findElementByXPath("//button[text()='Apply']").click();
//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details 
				Thread.sleep(500);
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Coupon savings']/following-sibling::span")));
				String actSavingsAll = driver.findElementByXPath("//span[text()='Coupon savings']/following-sibling::span").getText();
				String actSavingString = actSavingsAll.replaceAll("[^0-9.]", "");
				String actSaving = actSavingString.substring(1);
				System.out.println("the actual savings amount is-->"+actSaving);
				Double actSavingDouble = Double.parseDouble(actSaving);
				int actSavingInt = (int) Math.round(actSavingDouble);
				System.out.println("Integer value of actual saving is "+actSavingInt);
				if(actSavingInt==discountAmt) {
					System.out.println("discount matches");
//13) Click on Delete and Delete the item from Bag  
					driver.findElementByXPath("//div[@class='delete-btn']").click();
					Thread.sleep(500);
					wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='closet-btn']/following-sibling::div")));
					driver.findElementByXPath("//div[@class='closet-btn']/following-sibling::div").click();
//14) Close all the browsers
					driver.quit();
				}
				else {
					System.out.println("Discount price doesn't match");
				}
			}
			else {
				System.out.println("Order price doesn't match");
			}
		}
		else {
			System.out.println("Discount is not applicable for this product");
		}

			}
		}
		
	
	}

}
