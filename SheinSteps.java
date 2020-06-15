package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SheinSteps {

	public static ChromeDriver driver;
	public static WebDriverWait wt;
	public static JavascriptExecutor js;

	@Given("open https://www.shein.in/")
	public void openShein() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.shein.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wt = new WebDriverWait(driver, 40);
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c-coupon-box']//i[1]")));
		driver.findElementByXPath("//div[@class='c-coupon-box']//i[1]").click();
	}
	@When("you Mouseover on Clothing and click Jeans")
	public void clickJeans() throws InterruptedException {
	WebElement eleClothing = driver.findElementByXPath("(//div[@class='nav2-sec-ctn j-nav2-sec-ctn j-sa-nav2-sec-ctn '])[2]");
	Actions builder = new Actions(driver);
	builder.moveToElement(eleClothing).perform();
	Thread.sleep(500);
	wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Jeans')])[1]")));
	WebElement eleJeans = driver.findElementByXPath("(//a[contains(text(),'Jeans')])[1]");
	js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", eleJeans);
	}
	@And("Choose Black under Jeans product count")
	public void blackJeans() throws InterruptedException {
		Thread.sleep(2000);
		WebElement eleBlack = driver.findElementByXPath("//a[text()='Black']");
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", eleBlack);
		System.out.println("Clicked black under jeans");
		
	}
	@And("check size as medium")
	public void sizeMedium() throws InterruptedException {
		js = (JavascriptExecutor)driver;
		Thread.sleep(1000);
		WebElement eleSize = driver.findElementByXPath("//span[text()='Size']/parent::li");
		js.executeScript("arguments[0].click();", eleSize);
		Thread.sleep(1000);
		WebElement eleMedium = driver.findElementByXPath("(//span[@class='attr-check-box']/parent::a)[9]");
		js.executeScript("arguments[0].click();", eleMedium);
	}
	@Then("check whether the color is black")
	public void verifyBlack() throws InterruptedException {
		Thread.sleep(1000);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='attr-item-pic j-attr-item']/a/i")));
		WebElement eleColor = driver.findElementByXPath("//span[@class='attr-item-pic j-attr-item']/a/i");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleColor).perform();
		WebElement eleActualColor = driver.findElementByXPath("//span[@class='attr-item-pic j-attr-item']/a/span");
		if(eleActualColor.getText().equalsIgnoreCase("Black")) {
			System.out.println("The color selected is black");
		}
		else {
			System.out.println("The color selected is NOT black");
		}
	}
	@And("Click first item to Add to Bag")
	public void clickFirstItem() throws InterruptedException {
		Actions builder = new Actions(driver);
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='c-goodsitem__ratiowrap']/a)[1]")));
		WebElement eleFirstResult = driver.findElementByXPath("(//div[@class='c-goodsitem__ratiowrap']/a)[1]");
		builder.moveToElement(eleFirstResult).perform();
		driver.findElementByXPath("//button[contains(text(),'Add to Bag')][1]").click();
	}
	@And("Click the size as M and click Submit")
	public void SelectMediumInResult() throws InterruptedException {
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='opt-size j-quick-add-opt']/span[contains(text(),'M')]")));
		driver.findElementByXPath("//label[@class='opt-size j-quick-add-opt']/span[contains(text(),'M')]").click();
		driver.findElementByXPath("(//button[contains(text(),'Submit')])[1]").click();	
	}
	@And("Click view Bag")
	public void clickViewBag() throws InterruptedException {
		Thread.sleep(500);
		wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='view-bag']/a")));
		driver.findElementByXPath("//div[@class='view-bag']/a").click();		
	}
	@Then("Check the size is Medium or not")
	public void verifyMedium() throws InterruptedException {
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='gd-size']/em")));
		String sizeActual = driver.findElementByXPath("//span[@class='gd-size']/em").getText();
		if(sizeActual.equals("M")) {
			System.out.println("Size is "+sizeActual+" and the test case passed");
		}
		else {
			System.err.println("Size is "+sizeActual+" and the test case FAILED");
		}
	}
	@And("Close Shein browser")
	public void closeSheinBrowser(){
		driver.quit();
	}
}
