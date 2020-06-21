package steps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import hooks.Hooks;

public class JustDialSteps{

 private Hooks hooks;
 private WebDriverWait wt;
 private Actions builder;
 private JavascriptExecutor js;
 public static int priceInt;
	
	public JustDialSteps(Hooks hooks) {
		this.hooks = hooks;
	}

	public WebDriverWait getWait() {
		wt = new WebDriverWait(hooks.getDriver(),30);
		return wt;
	}
	
	public JavascriptExecutor getJs() {
		js = (JavascriptExecutor)hooks.getDriver();
		return js;
	}
	public Actions getActions() {
		builder = new Actions(hooks.getDriver());
		return builder;
	}
	
	@Given("Go to https://www.justdial.com/")
	public void launchbigBasket() {
		hooks.getDriver().get("https://www.justdial.com/");
		hooks.getDriver().manage().window().maximize();
		hooks.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wt = new WebDriverWait(hooks.getDriver(), 40);
	}

@And("Click on Air Tickets")
public void clickAirTickets() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='hotkeys-text']/parent::a[contains(@title,'Air Tickets')]")));
	hooks.getDriver().findElementByXPath("//span[@class='hotkeys-text']/parent::a[contains(@title,'Air Tickets')]").click();
}
@And("Type Chennai and choose Chennai, IN - Chennai Airport \\(MAA) as Leaving From")
public void chooseLeavingFrom() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("departure")));
	WebElement eleFrom = hooks.getDriver().findElementById("departure");
	eleFrom.click();
	eleFrom.sendKeys("Chennai");
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Chennai, IN - Chennai Airport (MAA)']")));
	hooks.getDriver().findElementByXPath("//li[text()='Chennai, IN - Chennai Airport (MAA)']").click();
}
@And("Type Toronto and select Toronto, CA - Toronto City Centre Airport \\(YTZ) as Going To")
public void selectToAirport() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("arrival")));
	WebElement eleTo = hooks.getDriver().findElementById("arrival");
	eleTo.click();
	eleTo.sendKeys("Toronto");
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Toronto, CA - Toronto City Centre Airport (YTZ)']")));
	hooks.getDriver().findElementByXPath("//li[text()='Toronto, CA - Toronto City Centre Airport (YTZ)']").click();	
}
@And("Set Departure as 2020, July 22")
public void setDate() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("departDate")));
	hooks.getDriver().findElementById("departDate");
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Next']")));
	hooks.getDriver().findElementByXPath("//span[text()='Next']").click();
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='22']")));
	hooks.getDriver().findElementByXPath("//a[text()='22']").click();
}
@And("Add Adult 2, Children 1 click and Search")
public void setPassengers() throws InterruptedException {
	hooks.getDriver().findElementByXPath("//span[text()='Next']").click();
	hooks.getDriver().findElementByXPath("(//span[@class='plus'])[1]").click();
	hooks.getDriver().findElementByXPath("(//span[@class='plus'])[2]").click();
	hooks.getDriver().findElementByXPath("//input[@class='btn inputbtn hidden-xs']").click();
}
@And("Select Air Canada from multi-airline itineraries")
public void selectAirCanada() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Air Canada')]")));
	hooks.getDriver().findElementByXPath("//label[contains(text(),'Air Canada')]").click();	
}
@And("Click on Price to sort the result")
public void sortPrice() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Price')]")));
	hooks.getDriver().findElementByXPath("//a[contains(text(),'Price')]").click();
}
@And("Click on +Details of first result under Price")
public void clickDetailsFirstResult() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'+ Details')])[1]")));
	hooks.getDriver().findElementByXPath("(//a[contains(text(),'+ Details')])[1]").click();
}
@And("Capture the Flight Arrival times")
public void captureFlightSrrivalTime() throws InterruptedException {
//	Thread.sleep(500);
//	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='mainText'])[2]")));
//	String arrivalTime = hooks.getDriver().findElementByXPath("(//span[@class='mainText'])[2]").getText();
	List<String> arrivalTimeList = new ArrayList<String>();
	Thread.sleep(500);
	for(int i = 1; i <10; i=i+4) {
		String arrTime = hooks.getDriver().findElementByXPath("//table[@class='detTab']/tbody/tr["+i+"]/td[6]/span").getText();
		arrivalTimeList.add(arrTime);
	}
	System.out.println("The arrival times are :");
	for (String eachItem : arrivalTimeList) {
		System.out.println(eachItem);
	}
}
@And("Capture the total price in a list and Click on Book")
public void totalPrice() throws InterruptedException {
	System.out.println("The fare details are as below :");
	Map<String, Integer> fareMap = new LinkedHashMap<String,Integer>();
	List<WebElement> priceNameList = hooks.getDriver().findElementsByXPath("//ul[@class='detUL']/li");
	for(int i = 2; i <=priceNameList.size()-2; i++) {
		
		String heading;
		if(i == priceNameList.size()-2) {
			heading = hooks.getDriver().findElementByXPath("//ul[@class='detUL']/li["+i+"]/span").getText();
			
		}
		else{
			heading = hooks.getDriver().findElementByXPath("//ul[@class='detUL']/li["+i+"]/a").getText();
			
		}
		String priceString = hooks.getDriver().findElementByXPath("//ul[@class='detUL']/li["+i+"]/div/span").getText();
		priceInt = Integer.parseInt(priceString);
		fareMap.put(heading, priceInt);
	}
	for (Entry<String,Integer> eachEntry : fareMap.entrySet()) {
		System.out.println(eachEntry.getKey()+"-->"+eachEntry.getValue());
	}
	hooks.getDriver().findElementByXPath("(//a[text()='BOOK'])[1]").click();
	Thread.sleep(2000);
}
@And("Capture the Airport name base on the list of time")
public void airportNameTime() throws InterruptedException {
	Thread.sleep(500);
	wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='detTabS detTab']/tbody/tr")));
	List<WebElement> arPortList = hooks.getDriver().findElementsByXPath("//table[@class='detTabS detTab']/tbody/tr");
	List<String> airportName = new ArrayList<String>();
	for(int i = 2; i <=arPortList.size(); i+=4) {
		String departNameString = hooks.getDriver().findElementByXPath("//table[@class='detTabS detTab']/tbody/tr["+i+"]/td[2]").getText();
		String departName = departNameString.replaceAll("\n", ",");
		airportName.add(departName);
		String airportNameString = hooks.getDriver().findElementByXPath("//table[@class='detTabS detTab']/tbody/tr["+i+"]/td[4]").getText();
		String arrivalAirport = airportNameString.replaceAll("\n", ",");
		airportName.add(arrivalAirport);
	}
	List<WebElement> timeList = hooks.getDriver().findElementsByXPath("//span[@class='dettime']");
	List<String> time = new ArrayList<String>();
	
	for(int i = 0; i <timeList.size(); i++) {
		String timeString = timeList.get(i).getText();
		time.add(timeString);
	}
	
	for(int i = 0; i <timeList.size(); i++) {
		if(i%2 != 0) {
			System.out.println("The arrival time is "+time.get(i)+" and the arrival airport is "+airportName.get(i));
		}
		else {
			System.out.println("The arrival time is "+time.get(i)+" and the arrival airport is "+airportName.get(i));
		}
	}
	
}
@And("Capture the total fare and print the difference amount from previous total price")
public void captureDiffFare() {
	String totalFare = hooks.getDriver().findElementByXPath("//span[@id='totalFare']/b").getText();
	int totalFareInt = Integer.parseInt(totalFare);
	System.out.println("the total fare is "+totalFareInt);
	System.out.println("The difference in amount is "+(totalFareInt-priceInt));
	hooks.getDriver().quit();
}


}
