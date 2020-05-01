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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarWale {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,50);
		List<Integer> kilom = new ArrayList<Integer>();
		List<String> petrolcar = new ArrayList<String>();
		int count =0;
		int petrolcount = 0;
		int petrolMore = 0;
//1)Go to https://www.carwale.com/
		driver.get("https://www.carwale.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Click on Used
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-tabs='usedCars']")));
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();
//3) Select the City as Chennai
		Thread.sleep(3000);
		WebElement eleCity = driver.findElementById("usedCarsList");
		eleCity.click();
		eleCity.sendKeys("Chennai", Keys.ENTER);
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();
//4) Select budget min (8L) and max(12L) and Click Search
		driver.findElementById("minInput").sendKeys("8", Keys.TAB);
		WebElement maxRs = driver.findElementById("maxInput");
		maxRs.sendKeys("12",Keys.TAB);
		driver.findElementById("btnFindCar").click();
		Thread.sleep(2000);
//5) Select Cars with Photos under Only Show Cars With
		Thread.sleep(1000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'show anymore tips')])[1]")));
		driver.findElementByXPath("(//a[contains(text(),'show anymore tips')])[1]").click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement eleCarPhotos = driver.findElementByXPath("//span[text()='Cars with Photos']");
		js.executeScript("arguments[0].click()", eleCarPhotos);

//6) Select Manufacturer as "Hyundai" --> Creta
		Thread.sleep(1000);
		WebElement eleHyundai = driver.findElementByXPath("//li[@class='us-sprite makeLi']/span[contains(text(),'Hyundai')]");
		js.executeScript("arguments[0].click()", eleHyundai);
		Thread.sleep(1000);
		WebElement eleCreta = driver.findElementByXPath("//span[text()='Creta']");
		js.executeScript("arguments[0].click()", eleCreta);
//7) Select Fuel Type as Petrol
		WebElement eleFuel = driver.findElementByXPath("//div[@name='fuel']");
		js.executeScript("arguments[0].click()", eleFuel);
		Thread.sleep(1000);
		WebElement petrol = driver.findElementByXPath("//span[text()='Petrol']");
		js.executeScript("arguments[0].click()", petrol);
//8) Select Best Match as "KM: Low to High"
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort")));
		WebElement eleSort = driver.findElementById("sort");
		Select ddSort = new Select(eleSort);
		ddSort.selectByVisibleText("KM: Low to High");
		Thread.sleep(2000);
		
//9) Validate the Cars are listed with KMs Low to High
		List<WebElement> eleresTable = driver.findElementsByXPath("//table[@class='card-detail__vehicle-data']/tbody/tr");
		for(int i = 0; i < eleresTable.size(); i++) {
			List<WebElement> eleallcols= eleresTable.get(i).findElements(By.tagName("td"));
			WebElement eleKms = eleallcols.get(0);
			String kmsAllchars = eleKms.getAttribute("title");
			String kmsString = kmsAllchars.replaceAll("\\D", "");
			int kms = Integer.parseInt(kmsString);
			kilom.add(kms);
			WebElement elefuelType = eleallcols.get(1).findElement(By.tagName("span"));
			String fuelType = elefuelType.getAttribute("title");
			petrolcar.add(fuelType);
			}
		for(int i = 0; i <kilom.size()-1; i++) {
			if(petrolcar.get(i).equalsIgnoreCase("Petrol")&&petrolcar.get(i+1).equalsIgnoreCase("Petrol")) {
				if(kilom.get(i)>kilom.get(i+1)) {
					System.out.println("The result car number "+(i+1)+" has greater kilometer than the car at number "+(i+2)+" in the result");
					count++;
				}
				else {
					System.out.println("The result car number "+(i+1)+" has kilometer value of "+kilom.get(i)+" with fuel type "+petrolcar.get(i)+" has lesser kilometer than the car at number "+(i+2)+" has kilometer of "+kilom.get(i+1)+" with fuel type "+petrolcar.get(i+1));
				}
				}
			else if(petrolcar.get(i).contains("Petrol + 1")&&petrolcar.get(i+1).equalsIgnoreCase("Petrol + 1")) {
				if(kilom.get(i)>kilom.get(i+1)) {
					System.out.println("The result car number "+i+" has greater kilometer than the car at number "+i+1+" in the result");
					count++;
				}
				else {
					System.out.println("The result car number "+(i+1)+" has kilometer value of "+kilom.get(i)+" with fuel type "+petrolcar.get(i)+" has lesser kilometer than the car at number "+(i+2)+" has kilometer of "+kilom.get(i+1)+" with fuel type "+petrolcar.get(i+1));
					petrolcount++;
					if(petrolcount ==1) {
						petrolMore = i;
					}
				}
			}
				else if(petrolcar.get(i).equalsIgnoreCase("Petrol")&&petrolcar.get(i+1).equalsIgnoreCase("Petrol + 1")&&(i+1==kilom.size()-1)) {
					System.out.println("The result car number "+(i+2)+" has kilometer value of "+kilom.get(i+1)+" with fuel type "+petrolcar.get(i+1));
					petrolcount++;
					if(petrolcount ==1) {
						petrolMore = (i+1);
				}
				}
		}
		if(count == 0) {
			System.out.println("The car kilometers are printing correctly from Low to high");
//10) Add the least KM ran car to Wishlist
			List<WebElement> eleLessKilo = driver.findElementsByXPath("//span[contains(@class,'shortlist-icon--inactive')]");
			Thread.sleep(2000);
			if(kilom.get(0)<kilom.get(petrolMore)) {
				WebElement elesmall = eleLessKilo.get(0);
				js.executeScript("arguments[0].click();", elesmall);	
			}
			else if(kilom.get(0)>kilom.get(petrolMore)) {
				WebElement elesmall =eleLessKilo.get(petrolMore);
				js.executeScript("arguments[0].click();", elesmall);
				}
//11) Go to Wishlist and Click on More Details
			Thread.sleep(1000);
			driver.findElementByXPath("//li[@class='action-box shortlistBtn']").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//a[text()='More details »']").click();
//12) Print all the details under Overview in the Same way as displayed in application
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(1));
			List<WebElement> eleFeatures = driver.findElementsByXPath("//div[@class='overview-list padding-bottom10']/ul/li/div[contains(@class,'equal-width')]");
			for(int i = 0; i <eleFeatures.size(); i++) {
				String features = eleFeatures.get(i).getText();
				if(i%2!=0) {
					
					System.out.println("---->"+features);
				}
				else {
					System.out.print(features);
				}
			}
		}
		else {
			System.out.println("Car kilometers are not printing in low to high");
		}
//13) Close the browser.
		driver.quit();

	}
}
