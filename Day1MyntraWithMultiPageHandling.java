package first21DaysTestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day1MyntraWithMultiPageHandling {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		boolean isManGo = true;
		Actions builder = new Actions(driver);
		WebElement eleWomen =  driver.findElementByXPath("(//a[@class='desktop-main'])[2]");
		builder.moveToElement(eleWomen).perform();
		WebElement eleJacketsnCoats = driver.findElementByLinkText("Jackets & Coats");
		builder.moveToElement(eleJacketsnCoats).perform();
		eleJacketsnCoats.click();
		WebElement eleTotalCoatsnJackets = driver.findElementByXPath("//span[@class='title-count']");
		Thread.sleep(500);
		WebDriverWait wt = new WebDriverWait(driver,30);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='title-count']")));
		String allItemsWithwords = eleTotalCoatsnJackets.getText();
		String totalCoatsnJackets = allItemsWithwords.replaceAll("[^0-9]", "");
		System.out.println(totalCoatsnJackets);
		WebElement eleJackets = driver.findElementByXPath("(//span[@class='categories-num'])[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='categories-num']")));
		String jacketsWithwords = eleJackets.getText();
		String totalJackets = jacketsWithwords.replaceAll("[^0-9]", "");
		WebElement eleCoats = driver.findElementByXPath("(//span[@class='categories-num'])[2]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='categories-num']")));
		String coatsWithwords = eleCoats.getText();
		String totalCoats = coatsWithwords.replaceAll("[^0-9]", "");
		int actualTotal = Integer.parseInt(totalCoats)+Integer.parseInt(totalJackets);
		int expectedTotal = Integer.parseInt(totalCoatsnJackets);
		if(actualTotal==expectedTotal) {
			//driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
			WebElement eleMore = driver.findElementByXPath("//div[@class='brand-more']");
			Thread.sleep(500);
			wt.until(ExpectedConditions.elementToBeClickable(eleMore));
			eleMore.click();
			driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("mango");
			WebElement eleBrandResult = driver.findElementByXPath("//label[@class=' common-customCheckbox']");
			Thread.sleep(500);
			wt.until(ExpectedConditions.elementToBeClickable(eleBrandResult));
			eleBrandResult.click();
			builder.moveToElement(eleBrandResult).perform();
			WebElement eleClosepop = driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']");
			wt.until(ExpectedConditions.elementToBeClickable(eleClosepop));
			builder.moveToElement(eleClosepop).perform();
			eleClosepop.click();
			Thread.sleep(3000);
			try {
			WebElement elepagesfind = driver.findElementByXPath("//li[@class='pagination-paginationMeta']");
			String pagesWithWords = elepagesfind.getText();
			String[] pagesString = pagesWithWords.split(" ");
			String firstPageString = pagesString[1];
			String totalPagesString = pagesString[pagesString.length-1];
			int totalPages = Integer.parseInt(totalPagesString);
			System.out.println("Total pages is "+totalPages);
			for(int i= 0; i < totalPages; i++) {
			List<WebElement> eleSearchResult = driver.findElementsByXPath("//h3[@class='product-brand']");
			for(int j = 0; j < eleSearchResult.size(); j++) {
				List<WebElement> eleSearchResultStale = driver.findElementsByXPath("//h3[@class='product-brand']");
				String brandName = eleSearchResultStale.get(j).getText();
				if(brandName.equalsIgnoreCase("MANGO")) {
					isManGo = true;
				}
				else {
					System.out.println("test case failed as page"+i+" item no "+j+" is not product code Mango");
					break;
				}	
			}
			if(isManGo == false) {
				break;
				}
				else {
					System.out.println("control is in page "+(i+1));
					if(totalPages>1 && i<totalPages-1) {
						driver.findElementByLinkText("Next").click();
						Thread.sleep(3000);
						System.out.println("clicked Next button "+(i+1)+" time");
					}
				}
			}
			}
			catch(NoSuchElementException e) {
				
				List<WebElement> eleSearchResult = driver.findElementsByXPath("//h3[@class='product-brand']");
				
				for(int j = 0; j < eleSearchResult.size(); j++) {
					if(eleSearchResult.get(j).getText().equalsIgnoreCase("MANGO")) {
						isManGo = true;
					}
					else {
						System.out.println("test case failed as item no "+j+" is not product code Mango");
						break;
					}	
			}
			}
			
			finally {
			if(isManGo==true) {
			WebElement eleSortBy = driver.findElementByXPath("//div[@class='sort-sortBy']");
			builder.moveToElement(eleSortBy).perform();
			List<WebElement> eleAllSortItems = driver.findElementsByXPath("//ul[@class='sort-list']/li/label");
			for(int i = 0; i < eleAllSortItems.size(); i++) {
				if(eleAllSortItems.get(i).getText().equals("Better Discount")) {
					eleAllSortItems.get(i).click();
					Thread.sleep(3000);
					break;
				}
			}
			WebElement elebetterDiscount = driver.findElementByXPath("(//span[@class='product-discountedPrice'])[1]");
			String betterDiscount = elebetterDiscount.getText();
			System.out.println("the price of the first better discounted item is --->"+betterDiscount);
			builder.moveToElement(elebetterDiscount).perform();
			driver.findElementByXPath("(//span[@class='product-actionsButton product-wishlist product-prelaunchBtn'])[1]").click();
			Thread.sleep(3000);
			String loginTitle = driver.getTitle();
			if(loginTitle.equals("Login")) {
				System.out.println("Title is "+loginTitle+" and test case passed");
				driver.close();
			}
			else {
				System.out.println("Title is "+loginTitle+" and test case failed");
				driver.close();
			}
		}
		}
	}
}
}