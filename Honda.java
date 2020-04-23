package first21DaysTestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Honda {

	 public static ChromeDriver driver;
	 public static WebDriverWait wt;	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		wt = new WebDriverWait(driver,50);
		Map<String, String> hm =  new LinkedHashMap<String, String>();
		String model = "";
		String price = "";
//1)1) Go to https://www.honda2wheelersindia.com/
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='close']")));
		driver.findElementByXPath("//button[@class='close']").click();
//2) Click on scooters and click dio
		driver.findElementByXPath("(//a[text()='Scooter'])[1]").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[contains(@src,'dio')])[1]")));
		driver.findElementByXPath("(//img[contains(@src,'dio')])[1]").click();
//3) Click on Specifications and mouseover on ENGINE
		engineClick();
//4) Get Displacement value
		double dioDispVal = getDisplacement();
//5) Go to Scooters and click Activa 125
		driver.findElementByXPath("(//a[text()='Scooter'])[1]").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[contains(@src,'activa')])[1]")));
		driver.findElementByXPath("(//img[contains(@src,'activa-125')])[1]").click();
//6) Click on Specifications and mouseover on ENGINE
		engineClick();
//7) Get Displacement value
		double activaDispVal = getDisplacement();
//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		System.out.println("The Displacement value of Dio is "+dioDispVal+" and the displacement value of Activa is "+activaDispVal);
		if(dioDispVal>activaDispVal) {
			System.out.println("Dio has better displacement than Activa");
		}
		else if(dioDispVal<activaDispVal) {
			System.out.println("Activa has better displacement than dio");
		}
//9) Click FAQ from Menu 
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='FAQ'])[1]")));
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();
//10) Click Activa 125 BS-VI under Browse By Product
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Activa 125')])[2]")));
		driver.findElementByXPath("(//a[contains(text(),'Activa 125')])[2]").click();
//11) Click  Vehicle Price 
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),' Vehicle Price')]")));
		driver.findElementByXPath("//a[contains(text(),' Vehicle Price')]").click();
//12) Make sure Activa 125 BS-VI selected and click submit
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ModelID6']")));
		WebElement activa = driver.findElementByXPath("//select[@id='ModelID6']");
		Select activadd = new Select(activa);
		String selectedVal = activadd.getFirstSelectedOption().getText();
		if(selectedVal.equalsIgnoreCase("Activa 125 BS-VI")){
			System.out.println("Activa 125 BS-VI has been selected");
			driver.findElementById("submit6").click();
//13) click the price link
			wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'price')]")));
			driver.findElementByXPath("//a[contains(@href,'price')]").click();
//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(1));
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("StateID")));
			WebElement elestate = driver.findElementById("StateID");
			Select eleStatedd = new Select(elestate);
			eleStatedd.selectByVisibleText("Tamil Nadu");
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("CityID")));
			WebElement eleCity = driver.findElementById("CityID");
			Select eleCitydd = new Select(eleCity);
			eleCitydd.selectByVisibleText("Chennai");
//15) Click Search
			wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
			driver.findElementByXPath("//button[text()='Search']").click();
//16) Print all the 3 models and their prices
			WebElement table = driver.findElementByXPath("//table[@id='gvshow']/tbody");
			List<WebElement> allRows = table.findElements(By.tagName("tr"));
			for(int i = 0; i < allRows.size(); i++) {
				List<WebElement> allCells = allRows.get(i).findElements(By.tagName("td"));
				if(i == 0) {
					model = allCells.get(1).getText();
					price = allCells.get(2).getText();
				}
				else {
					model = allCells.get(0).getText();
					price = allCells.get(1).getText();
				}
				hm.put(model, price);				
			}
			for (Entry<String,String> e : hm.entrySet()) {
				System.out.println("The Model is "+e.getKey()+" and the Price is "+e.getValue());
			}
		}
		else {
			System.out.println("Activa 125 BS-VI NOT selected, TC Failed");
		}
//17) Close the Browser(all windows)
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	public static double getDisplacement() {
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Displacement']/following-sibling::span")));
		String dispValAllChars = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
		String dispValspace = dispValAllChars.replaceAll("[^0-9.]", "");
		String dispVal = dispValspace.trim();
		double actDispVal = Double.parseDouble(dispVal);
		return actDispVal;
	}
	public static void engineClick() {
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Specifications']")));
		driver.findElementByXPath("//a[text()='Specifications']").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='ENGINE']")));
		WebElement eleEngine = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleEngine).perform();
	}
}
