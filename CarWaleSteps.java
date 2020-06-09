package steps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CarWaleSteps {

	public static ChromeDriver driver;
	public static WebDriverWait wt;
	public static JavascriptExecutor js;
	public static List<WebElement> kmList;
	public static List<Integer> kmIntList;
	public static int count = 0;
	
	@Given("User opens the Chrome Browser")
	public void OpenChrome() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		wt = new WebDriverWait(driver,30);
		js = (JavascriptExecutor)driver;

	}
	@And("Launch https://www.carwale.com/")
	public void LaunchCarWale() {
		try{
			driver.get("https://www.carwale.com/");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@And("User maximise the browser")
	public void maximizeBrowser() {
		try{
			driver.manage().window().maximize();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("User set the default implicit timeout")
	public void setTimeOut() {
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@When("Select the City as (.*)$")
	public void selectCity(String place) throws InterruptedException {
		try{
			driver.findElementByXPath("//div[@class='global-search']").click();
		
		Thread.sleep(1000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='placesQuery']")));
		WebElement elePlace = driver.findElementByXPath("//input[@id='placesQuery']");
		elePlace.click();
		elePlace.sendKeys(place);
		Thread.sleep(2000);
		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(@class,'ui-menu-item')]/a/strong")));
		List<WebElement> resultPlaceList = driver.findElementsByXPath("//li[contains(@class,'ui-menu-item')]/a/strong");
		for(int i=0; i < resultPlaceList.size(); i++) {
			if(resultPlaceList.get(i).getText().contains(place)) {
				resultPlaceList.get(i).click();
				break;
			}
		}
		driver.findElementByXPath("//span[@id='closeLocIcon']").click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("click on Used")
	public void clickUsed() throws InterruptedException {
		try{
			Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='welcome-box__search-item js-welcome-box__list-item']")));
		driver.findElementByXPath("//li[@class='welcome-box__search-item js-welcome-box__list-item']").click();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("Select budget min 8L and max 12L and click Search")
	public void selectBudget() {
		try {
		driver.findElementByXPath("//ul[@id='minPriceList']/li[text()='8 Lakh']").click();
		driver.findElementByXPath("//ul[@id='maxPriceList']/li[text()='12 Lakh']").click();
		driver.findElementByXPath("//button[@id='btnFindCar']").click();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("Select Cars with Photos under Only Show Cars With")
	public void carsWithPic(){
		try {
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='nomoreTips cur-pointer'])[1]")));
		driver.findElementByXPath("(//a[@class='nomoreTips cur-pointer'])[1]").click();
		}
		catch(Exception tips) {
			tips.printStackTrace();
		}
		try {
			driver.findElementByXPath("//li[@name='CarsWithPhotos']").click();
		}
		catch(Exception pics) {
			pics.printStackTrace();
		}
			
		}
	@And("Select Manufacturer as Hyundai --> Creta")
	public void selectCar() {
		try {
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Hyundai')]/parent::li")));
			WebElement eleHyundai = driver.findElementByXPath("//span[contains(text(),'Hyundai')]/parent::li");
			js.executeScript("window.scrollBy(0,2000)","");
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", eleHyundai);
		}
		catch(Exception make) {
			make.printStackTrace();
		}
		try {
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Creta')]/parent::li")));
			WebElement eleCreta = driver.findElementByXPath("//span[contains(text(),'Creta')]/parent::li");
			js.executeScript("arguments[0].click();", eleCreta);
		}
		catch(Exception model) {
			model.printStackTrace();
		}
	}
	@And("Select Fuel Type as Petrol")
	public void selectFuel() throws InterruptedException {
		try{
			js.executeScript("window.scrollBy(0,3000)","");		
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Fuel')]/parent::div")));
		driver.findElementByXPath("//h3[contains(text(),'Fuel')]/parent::div").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Petrol']/parent::li[@name='Petrol']")));
		driver.findElementByXPath("//span[text()='Petrol']/parent::li[@name='Petrol']").click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@And("Select Best Match as KM: Low to High")
	public void selectlowKmSort() {
		try {
			WebElement eleSort = driver.findElementByXPath("//select[@id='sort']");
			Select ddSort = new Select(eleSort);
			ddSort.selectByVisibleText("KM: Low to High");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Then("Validate the Cars are listed with KMs Low to High")
	public void validateKmLowToHigh() {
		try {
			kmList = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");
			
			for(int i = 0; i <kmList.size(); i++) {
				String kmString = kmList.get(i).getText().replaceAll("\\D", "");
				System.out.println("The kilometer value is "+kmString);
				int kmInt = Integer.parseInt(kmString);
				kmIntList = new ArrayList<Integer>();
				kmIntList.add(kmInt);
			}
			for(int j = 0; j <kmIntList.size()-1; j++) {
				if(!(kmIntList.get(j)<kmIntList.get(j+1))) {
					System.out.println("The value "+kmIntList.get(j)+" is greater than the next value "+kmIntList.get(j+1));
					System.out.println("Validation Failed");
					Assert.assertTrue((kmIntList.get(j)<kmIntList.get(j+1)));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("Add the least KM ran car to Wishlist")
	public void addLeastKMWishList() {
	try{
		driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])[1]").click();
		count++;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	@And("Go to Wishlist and click on More Details")
	public void clickWishListNMoreDetails() throws InterruptedException {
		try {
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+count+"']")));
		driver.findElementByXPath("//li[@class='action-box shortlistBtn']").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'More details')]")));
		driver.findElementByXPath("//a[contains(text(),'More details')]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList =  new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	@Then("Print all the details under Overview in the same way as displayed in application")
	public void printDetails() {
		try {
			List<WebElement> overViewList = driver.findElementsByXPath("//div[@id='overview']/div/ul/li");
			Map<String,String> values = new LinkedHashMap<String, String>();
			for(int i = 0; i <overViewList.size(); i++) {
				 List<WebElement> valuesList = overViewList.get(i).findElements(By.tagName("div"));				 
				 values.put(valuesList.get(0).getText(),valuesList.get(1).getText());
			}
			for (Entry<String,String> eachEntry : values.entrySet()) {
				System.out.println(eachEntry.getKey()+"-->"+eachEntry.getValue());
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	@And("Close the browser")
	public void closeAll() {
		driver.quit();
	}
	
}
