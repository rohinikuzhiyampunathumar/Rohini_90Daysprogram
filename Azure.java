package first21DaysTestCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Azure {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		 //  chromePrefs.put("profile.default_content_settings.popups", 0);
        //chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("download.default_directory", "C:\\Users\\rohini kumar\\Desktop\\Rohini\\Sele\\Exercises\\OutputFiles");
     //   chromePrefs.put("safebrowsing.enabled", "false");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);        
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,30);
//		1)Go to https://azure.microsoft.com/en-in/
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		2)Click on Pricing
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Pricing']")));
		driver.findElementByXPath("//a[text()='Pricing']").click();
//		3) Click on Pricing Calculator
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Pricing calculator')]")));
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
//		4) Click on Containers
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-event-property='containers']")));
		driver.findElementByXPath("//button[@data-event-property='containers']").click();
//		5) Select Container Instances
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@title='Container Instances'])[2]")));
		driver.findElementByXPath("(//button[@title='Container Instances'])[2]").click();
//		6) Click on Container Instance Added View
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='View']")));
		driver.findElementByXPath("//a[text()='View']").click();
//		7) Select Region as "South India"
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@aria-label='Region'])[1]")));
		WebElement eleRegion = driver.findElementByXPath("(//select[@aria-label='Region'])[1]");
		Select ddRegion = new Select(eleRegion);
		ddRegion.selectByVisibleText("South India");
//		8) Set the Duration as 180000 seconds
		WebElement eleDuration = driver.findElementByXPath("//input[@aria-label='Seconds']");
		eleDuration.clear();
		eleDuration.sendKeys("80000");
//		9) Select the Memory as 4GB
		WebElement eleMemory = driver.findElementByXPath("//select[@name='memory']");
		Select ddMemory = new Select(eleMemory);
		ddMemory.selectByVisibleText("4 GB");
//		10) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
//		11) Select Indian Rupee  as currency
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='select currency-dropdown']")));
		WebElement eleCurr = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select ddCurr = new Select(eleCurr);
		ddCurr.selectByVisibleText("Indian Rupee (₹)");
//		12) Print the Estimated monthly price
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='numeric']/span)[6]")));
		String monthEst = driver.findElementByXPath("(//span[@class='numeric']/span)[6]").getText();
		System.out.println("The monthly estimate is "+monthEst);
//		13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
//		14) Verify the downloded file in the local folder
		File file = new File("C:\\Users\\rohini kumar\\Desktop\\Rohini\\Sele\\Exercises\\OutputFiles\\ExportedEstimate.xlsx");   
		//Desktop desktop = Desktop.getDesktop();  
		Thread.sleep(1000);
		if(file.exists()) {        
			System.out.println("Containers Instance file exists in the specied path");
			//desktop.open(file);
		}  
//		15) Navigate to Example Scenarios and Select CI/CD for Containers
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement eleEx = driver.findElementByXPath("//a[text()='Example Scenarios']");
		js.executeScript("arguments[0].click();", eleEx);
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='CI/CD for Containers']")));
		WebElement eleCICon = driver.findElementByXPath("//button[@title='CI/CD for Containers']");
		js.executeScript("arguments[0].click();", eleCICon);
//		16) Click Add to Estimate
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to estimate']")));
		WebElement eleAdd = driver.findElementByXPath("//button[text()='Add to estimate']");
		js.executeScript("arguments[0].click();", eleAdd);
//		17) Change the Currency as Indian Rupee
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='select currency-dropdown']")));
		WebElement eleCurrCI = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select ddCurrCI = new Select(eleCurrCI);
		ddCurrCI.selectByVisibleText("Indian Rupee (₹)");
//		18) Enable SHOW DEV/TEST PRICING
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Show Dev/Test Pricing']")));
		WebElement eleDevPricing = driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']");
		js.executeScript("arguments[0].click();", eleDevPricing);
//		19) Export the Estimate
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='calculator-button button-transparent export-button']")));
		WebElement eleExport = driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']");
		js.executeScript("arguments[0].click();", eleExport);
		Thread.sleep(2000);
//		20) Verify the downloded file in the local folder		
		File fileCI = new File("C:\\Users\\rohini kumar\\Desktop\\Rohini\\Sele\\Exercises\\OutputFiles\\ExportedEstimate (1).xlsx");   
		if(fileCI.exists()) {        
			System.out.println("CI CD Containers Estimate file exists in the specied path");
		}  
		
	}

}
