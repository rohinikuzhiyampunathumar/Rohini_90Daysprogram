package first21DaysTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,30);
		
//1) Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Click Hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();
//3) Enter city as Goa, and choose Goa, India
		driver.findElementById("city").click();
		Thread.sleep(3000);
		WebElement eleEnterCity = driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']");
		//Thread.sleep(500);
		//wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open']")));
		eleEnterCity.sendKeys("Goa",Keys.ENTER);
		WebElement eleCityName = driver.findElementByXPath("//p[text()='Goa, India']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOf(eleCityName));
		eleCityName.click();
//4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		WebElement eleFromDate = driver.findElementByXPath("(//div[text()='15'])[2]");
		eleFromDate.click();
		driver.findElementByXPath("(//div[text()='20'])[2]").click();
//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		WebElement eleRoomsnGuests = driver.findElementByXPath("//input[@id='guest']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='guest']")));
		eleRoomsnGuests.click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-cy='adults-2']")));
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement eleNoChildren = driver.findElementByXPath("//select[@class='ageSelectBox']");
		Select ddNoChildren = new Select(eleNoChildren);
		ddNoChildren.selectByVisibleText("12");
		driver.findElementByXPath("//button[text()='APPLY']").click();
//6) Click Search button
		driver.findElementById("hsw_search_button").click();
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
//7) Select locality as Baga
		WebElement eleBaga = driver.findElementByXPath("//label[text()='Baga']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(eleBaga));
		eleBaga.click();
//8) Select 5 start in Star Category under Select Filters
		WebElement ele5Start = driver.findElementByXPath("//label[text()='5 Star']");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(ele5Start));
		ele5Start.click();
//9) Click on the first resulting hotel and go to the new window
		WebElement elefirstResult = driver.findElementByXPath("(//p[@itemprop='address'])[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(elefirstResult));
		elefirstResult.click();
		Set<String> searchSet = driver.getWindowHandles();
		List<String> searchList = new ArrayList<String>(searchSet);
		driver.switchTo().window(searchList.get(1));
//10) Print the Hotel Name 
		WebElement elePickedHotel = driver.findElementById("detpg_hotel_name");
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.id("detpg_hotel_name")));
		String hotelName = elePickedHotel.getText();
		System.out.println("Hotel name is "+hotelName);
//11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
//		Set<String> emiSet = driver.getWindowHandles();
//		List<String> emiList = new ArrayList<String>(emiSet);
//		driver.switchTo().window(emiList.get(2));
		driver.switchTo().activeElement();
		WebElement eleEmiplan = driver.findElementByXPath("(//span[@class='latoBold font12 pointer makeFlex hrtlCenter right blueText'])[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(eleEmiplan));
		eleEmiplan.click();
		driver.findElementByXPath("//span[@class='close']").click();
//		driver.switchTo().window(emiList.get(1));
//12) Click on BOOK THIS NOW
		driver.findElementById("detpg_headerright_book_now").click();
		driver.switchTo().activeElement();
		driver.findElementByXPath("//span[@class='close']").click();
//13) Print the Total Payable amount
		WebElement eleAmount = driver.findElementById("revpg_total_payable_amt");
		String amountAllchars = eleAmount.getText();
		String amount = amountAllchars.replaceAll("[^0-9,]", "");
		System.out.println("Amount is "+amount);
//14) Close all windows
		driver.quit();		
		
	}

}
