package first21DaysTestCases;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket {

	public static ChromeDriver driver;
	public double getTotal() {
		Map<Double,Double> cart = new LinkedHashMap<Double,Double>();
		List<WebElement> eleCartList = driver.findElementsByXPath("//div[@qa='pcsMB']");
		for(int i = 0; i<eleCartList.size(); i++) {
			String itemAllChars = eleCartList.get(i).getText();			
			String[] item = itemAllChars.split(" x ");
			for(int j = 0; j <item.length-1; j++) {
			cart.put(Double.parseDouble(item[j+1]), Double.parseDouble(item[j]));
			}
		}
		double total = 0;
		for (Entry<Double, Double> eachdigit : cart.entrySet()) {
			total = total+(eachdigit.getValue()*eachdigit.getKey());
		}
		System.out.println("Total is ****"+total);
		return total;
	}
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,50);
//1) Go to https://www.bigbasket.com/
		driver.get("https://www.bigbasket.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//select location as Chennai
		WebElement eleLocation = driver.findElementByXPath("//span[@ng-bind='vm.user.currentAddress.city_name']");
		wt.until(ExpectedConditions.visibilityOf(eleLocation));
		
		if(!eleLocation.getText().contains("Chennai")) {
			eleLocation.click();
			driver.findElementByXPath("(//span[@class='btn btn-default form-control ui-select-toggle'])[1]").click();
			WebElement eleSelectLoc = driver.findElementByXPath("(//input[@aria-label='Select box'])[1]");
			eleSelectLoc.sendKeys("Chennai");
			WebElement eleChennai = driver.findElementByXPath("(//span[text()='Chennai'])[1]");
			wt.until(ExpectedConditions.visibilityOf(eleChennai));
			eleChennai.click();
		}
			WebElement elepincode = driver.findElementById("areaselect");
			eleLocation.click();
			elepincode.sendKeys("600006");
			Thread.sleep(3000);
			elepincode.sendKeys(Keys.ENTER);
			driver.findElementByXPath("//button[text()='Continue']").click();
//2) mouse over on  Shop by Category 
		Actions builder = new Actions(driver);
		WebElement eleCategory = driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
		builder.moveToElement(eleCategory).perform();
//3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
		WebElement eleFoodGrains = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		builder.moveToElement(eleFoodGrains).perform();
		WebElement eleRiceItems = driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]");
		builder.moveToElement(eleRiceItems).perform();
//4) Click on Boiled & Steam Rice
		driver.findElementByXPath("(//a[text()='Boiled & Steam Rice'])[2]").click();
		Thread.sleep(3000);
//5) Choose the Brand as bb Royal
		WebElement elebbRoyal = driver.findElementByXPath("(//span[text()='bb Royal'])[1]");
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(elebbRoyal));
		//js.executeScript("arguments[0].click();", elebbRoyal);
		elebbRoyal.click();
		Thread.sleep(5000);
//6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		WebElement elePonnidd = driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]");
		wt.until(ExpectedConditions.visibilityOf(elePonnidd));
		elePonnidd.click();
		driver.findElement(By.xpath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]/span/ul/li/a/span[text()='5 kg']")).click();
//7) print the price of Rice
		String riceprice = driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[@class='discnt-price']/span)[1]").getText();
		System.out.println("The price of the selected rice is Rs."+riceprice);
//8) Click Add button
		driver.findElementByXPath("((//a[text()='Ponni Boiled Rice - Super Premium'])[1]/following::button[@class='btn btn-add col-xs-9'])[1]").click();
//9) Verify the success message displayed 
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-title']")));
		String riceSuccessmsg= driver.findElementByXPath("//div[@class='toast-title']").getText();
		System.out.println(riceSuccessmsg);
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
//10) Type Dal in Search field and enter
		driver.findElementById("input").sendKeys("Dal", Keys.ENTER);
//12) Go to Toor/Arhar Dal and select 2kg & set Qty 2
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']")));
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[@class='btn btn-default dropdown-toggle form-control'])[1]").click();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[text()='2 kg'])[1]").click();
		WebElement eleToorqty = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[text()='Qty']/following-sibling::input)[1]");
		eleToorqty.clear();
		eleToorqty.sendKeys("2");
//13) Print the price of Dal
		String dalPrice = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[@class='discnt-price']/span)[1]").getText();
		System.out.println("The price of toor dal is Rs."+dalPrice);
//14) Click Add button
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[contains(text(),'Add')])[1]").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-title']")));
		String dalSuccessmsg= driver.findElementByXPath("//div[@class='toast-title']").getText();
		System.out.println(dalSuccessmsg);
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
//15) Mouse hover on My Basket 
		WebElement eleBasket = driver.findElementByXPath("//span[@title='Your Basket']");
		builder.moveToElement(eleBasket).perform();
		Thread.sleep(3000);
//16) Validate the Sub Total displayed for the selected items
		BigBasket bb = new BigBasket();
		double actDoubleTotal = bb.getTotal();
		String expTotal = driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
		double expDoubletotal = Double.parseDouble(expTotal);
		System.out.println("Calculated Subtotal is "+actDoubleTotal+" and the expected Subtotal is "+expDoubletotal);
		if(actDoubleTotal==expDoubletotal) {
			System.out.println("SubTotal Matches");
//17) Reduce the Quantity of Dal as 1 
			driver.findElementByXPath("(//button[@qa='decQtyMB'])[2]").click();
			Thread.sleep(2000);
//18) Validate the Sub Total for the current items
			String expnewTotal = driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
			double expNewDoubletotal = Double.parseDouble(expnewTotal);
			double actnewDoubleTotal = bb.getTotal();
			System.out.println("Calculated Subtotal is "+actnewDoubleTotal+" and the expected Subtotal is "+expNewDoubletotal);
			if(actnewDoubleTotal==expNewDoubletotal) {
				System.out.println("SubTotal Matches, scenario passed");
					}
			else {
				System.out.println("Subtotal doesn't match TC failed");
			}
	}
		else {
			System.out.println("Subtotal doesn't match, TC failed");
		}
//19) Close the Browser
		driver.manage().deleteAllCookies();
		driver.close();
		
	}
		

}
