package first21DaysTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Day2Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//2) Mouseover on Brands and Mouseover on Popular
		WebDriverWait wt = new WebDriverWait(driver, 30);
		Actions builder = new Actions(driver);
		WebElement brands = driver.findElementByXPath("//li[@class='menu-dropdown-icon']/a");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='menu-dropdown-icon']/a")));
		builder.moveToElement(brands).perform();
		System.out.println("hovered over brands");
		WebElement popular = driver.findElementByXPath("//a[text()='Popular']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Popular']")));
		builder.moveToElement(popular).perform();
		popular.click();
		//3) Click L'Oreal Paris
		WebElement eleLorealParis = driver.findElementByXPath("(//a[contains(@href,'brands/loreal-paris')])[3]/img");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(@href,'brands/loreal-paris')])[3]/img")));
		eleLorealParis.click();
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
		WebElement eleLorealTitle = driver.findElementByXPath("//div[@class='filter-heading-section ']");
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='filter-heading-section ']")));
		String lorealTitle = eleLorealTitle.getText();
		if(lorealTitle.contains("L'OREAL PARIS")) {
			System.out.println("The title is "+lorealTitle);
		//5) Click sort By and select customer top rated
			driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
			driver.findElementByXPath("(//div[@class='control__indicator radio'])[4]").click();
		//6) Click Category and click Shampoo
			WebElement eleCategory = driver.findElementByXPath("(//div[@class='filter-sidebar__filter-title pull-left'])[1]");
			Thread.sleep(500);
			wt.until(ExpectedConditions.elementToBeClickable(eleCategory));
			eleCategory.click();
			driver.findElementByXPath("(//div[@class='control__indicator'])[30]").click();
		//7) check whether the Filter is applied with Shampoo
			WebElement eleFilterShampoo = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']/li");
			Thread.sleep(500);
			wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='pull-left applied-filter-lists']/li")));
			String filterShampooText = eleFilterShampoo.getText();
			Assert.assertEquals(filterShampooText, "Shampooclose");
		//8) Click on L'Oreal Paris Colour Protect Shampoo
			WebElement eleColorProtectShampoo = driver.findElementByXPath("//h2[contains(@title, 'Paris Colour Protect Shampoo')]");
			Thread.sleep(500);
			wt.until(ExpectedConditions.elementToBeClickable(eleColorProtectShampoo));
			eleColorProtectShampoo.click();
			Set<String> winSetShampooClick = driver.getWindowHandles();
			List<String> winListShampooClick = new ArrayList<String>(winSetShampooClick);
			driver.switchTo().window(winListShampooClick.get(2));
		//9) GO to the new window and select size as 175ml
			WebElement eleSize= driver.findElementByXPath("//span[text()='175ml']");
			String shampooSize = eleSize.getText();
			Thread.sleep(500);
			wt.until(ExpectedConditions.elementToBeClickable(eleSize));
			eleSize.click();
		//10) Print the MRP of the product
			String mrp = driver.findElementByXPath("//span[@class='post-card__content-price-offer']").getText();
			WebElement eleShampooDesc = driver.findElementByXPath("//h1[@class='product-title']");
			String shampooDescAllChars = eleShampooDesc.getText();
			String shampooDesc = shampooDescAllChars.replaceAll("[\\\r\\\n]+", "");
			System.out.println("MRP of "+shampooDesc+" is "+mrp);
		//Delete Existing items from the bag if present
			WebElement eleCart = driver.findElementByXPath("//div[@class='AddBagIcon']");
			WebElement eleNoOfBagItems = driver.findElementByXPath("//div[contains(@class,'BagItems')]");
			String noOfBagITems = eleNoOfBagItems.getText();
			if(!noOfBagITems.isEmpty()) {
			if(Integer.parseInt(noOfBagITems)>0) {
				eleCart.click();
				driver.findElementByXPath("//i[@class='remove-product']").click();
				driver.findElementByXPath("//button[text()='Yes']").click();
				driver.findElementByXPath("//button[@class='back-btn']").click();
			}}
			
		//11) Click on ADD to BAG
			driver.findElementByXPath("(//button[contains(@class,'product-list__cart-btn')])[1]").click();
			WebElement eleProductAdded = driver.findElementByXPath("(//div[@class='mm-text']/span)[2]");
			Thread.sleep(500);
			wt.until(ExpectedConditions.textToBePresentInElement(eleProductAdded, "Product has been added to bag."));
			Assert.assertEquals(eleProductAdded.getText(), "Product has been added to bag.");
		//12) Go to Shopping Bag
			eleCart.click();
		//Verify the item in the cart is the one added earlier
			WebElement eleCartItem = driver.findElementByXPath("//div[@class='product-name']");
			String cartItem = eleCartItem.getText();
			WebElement eleCartItemSize = driver.findElementByXPath("//span[@class='swatch-name']");
			String cartItemSize =  eleCartItemSize.getText();
			if(shampooDesc.contains(cartItem)&&cartItemSize.contains(shampooSize)) {
				System.out.println("Shampoo selected was "+shampooDesc+" and the shampoo in the cart is "+cartItem);
				System.out.println("Size of the shampoo selected was "+shampooSize+" and the size in the cart is "+cartItemSize);
		//13) Print the Grand Total amount
				String grandTotal = driver.findElementByXPath("(//div[@class='value'])[4]").getText();
				System.out.println("The grand total is "+grandTotal);
		//14) Click Proceed
				driver.findElementByXPath("//i[@class='proceed-arrow']").click();
		//15) Click on Continue as Guest
				WebElement eleGuesButton = driver.findElementByXPath("//button[@class='btn full big']");
				Thread.sleep(500);
				wt.until(ExpectedConditions.elementToBeClickable(eleGuesButton));
				eleGuesButton.click();
		//16) Print the warning message (delay in shipment)
				WebElement eleWarningMsg = driver.findElementByXPath("//div[@class='message']");
				Thread.sleep(500);
				wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message']")));
				System.out.println("The warning message is "+eleWarningMsg.getText());
				System.out.println("Test Case Passed");
			}
			else {
				System.out.println("Shampoo selected was "+shampooDesc+" and the shampoo in the cart is "+cartItem);
				System.out.println("Size of the shampoo selected was "+shampooSize+" and the size in the cart is "+cartItemSize);
				System.out.println("Test case Failed");
			}			
		}
		else {
			System.out.println("Title is not L'OREAL PARIS and test case failed ");
		}
		
		//17) Close all windows
		driver.quit();

	}

}
