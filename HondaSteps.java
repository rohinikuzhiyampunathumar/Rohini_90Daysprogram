package steps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hooks.Hooks;

public class HondaSteps {
	 private Hooks hooks;
	 private WebDriverWait wt;
	 private Actions builder;
	 private JavascriptExecutor js;
	 public static int priceInt;
	 Map<String,String> activa125Map = new LinkedHashMap<String,String>();
	 Map<String,String> dioEngineMap = new LinkedHashMap<String,String>();
		
		public HondaSteps(Hooks hooks) {
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
		
		@Given("Go to https://www.honda2wheelersindia.com/")
		public void launchbigBasket() {
			try{
				hooks.getDriver().get("https://www.honda2wheelersindia.com/");
				hooks.getDriver().manage().window().maximize();
				hooks.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(500);
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='close']")));
				hooks.getDriver().findElementByXPath("//button[@class='close']").click();
			}
			catch(Exception e) {
				System.out.println("unable to launch honda site");
			}			
		}
		@And("Click on scooters and click dio")
		public void clickDioScooter() {
			WebElement eleDio;
			try {
				hooks.getDriver().findElementByXPath("(//a[text()='Scooter'])[1]").click();
				try {
					Thread.sleep(500);
					getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@href,'dio')]/img)[1]")));
					eleDio = hooks.getDriver().findElementByXPath("(//a[contains(@href,'dio')]/img)[1]");
					try {
						eleDio.click();
					}
					catch (Exception e) {
						System.out.println("Unable to click Dio");
					}
				}
				catch(Exception e) {
					System.out.println("the element Dio is not found");
				}
			}
			catch(Exception e) {
				System.out.println("The element scooter not found");
			}			
		}
	@And("Click on Specifications and mouseover on Engine")
	public void clickEngine() {
		try {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Specifications']/parent::li")));
			hooks.getDriver().findElementByXPath("//a[text()='Specifications']/parent::li").click();
			try {
				Thread.sleep(500);
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='ENGINE']/parent::li")));
				WebElement eleEngine = hooks.getDriver().findElementByXPath("//a[text()='ENGINE']/parent::li");
				getActions().moveToElement(eleEngine).perform();
				Thread.sleep(2000);
			}
			catch(Exception e) {
				System.out.println("Unable to mouse hover on engine");
			}
		}
		catch(Exception e) {
			System.out.println("unable to find specifications under Dio");
		}
	}
	@And("Put all the details as key and value into Map")
	public void engineDioDetails() {
		try {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//ul[@class='tab_content'])[2]/li")));
			List<WebElement> engineHeader = hooks.getDriver().findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span[1]");
			List<WebElement> engineValue = hooks.getDriver().findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span[2]");
			
			for(int i=0; i <engineValue.size(); i++) {
				dioEngineMap.put(engineHeader.get(i+1).getText(), engineValue.get(i).getText());
			}
			for (Entry<String, String> eachString : dioEngineMap.entrySet()) {
				System.out.println(eachString.getKey()+"--->"+eachString.getValue());
			}
		}
		catch(Exception e) {
		System.out.println("Couldnot enter all the key and values of engine into map");	
		}
		}
	@And("Go to Scooters and click Activa 125")
	public void clickActiva125() {	
			WebElement eleDio;
			try {
				hooks.getDriver().findElementByXPath("(//a[text()='Scooter'])[1]").click();
				try {
					Thread.sleep(500);
					getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@href,'activa125')]/img)[1]")));
					eleDio = hooks.getDriver().findElementByXPath("(//a[contains(@href,'activa125')]/img)[1]");
					try {
						eleDio.click();
						Thread.sleep(1000);
					}
					catch (Exception e) {
						System.out.println("Unable to click Activa 125");
					}
				}
				catch(Exception e) {
					System.out.println("the element Activa 125 is not found");
				}
			}
			catch(Exception e) {
				System.out.println("The element scooter not found");
			}			
		}
	@And("Put All its Engine Specification into another Map same as like dio")
	public void engineActiva125Details() {
		try {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Specifications']/parent::li")));
			hooks.getDriver().findElementByXPath("//a[text()='Specifications']/parent::li").click();
			try {
				Thread.sleep(500);
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='ENGINE']/parent::li")));
				WebElement eleEngine = hooks.getDriver().findElementByXPath("//a[text()='ENGINE']/parent::li");
				getActions().moveToElement(eleEngine).perform();
				try {
					Thread.sleep(500);
					getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//ul[@class='tab_content'])[2]/li")));
					List<WebElement> engineHeader = hooks.getDriver().findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span[1]");
					List<WebElement> engineValue = hooks.getDriver().findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span[2]");
					
					for(int i=0; i <engineValue.size(); i++) {
						activa125Map.put(engineHeader.get(i+1).getText(), engineValue.get(i).getText());
					}
					for (Entry<String, String> eachString : activa125Map.entrySet()) {
						System.out.println(eachString.getKey()+"--->"+eachString.getValue());
					}
				}
				catch(Exception e) {
				System.out.println("Couldnot enter all the key and values of engine into map");	
				}
			}
			catch(Exception e) {
				System.out.println("Unable to mouse hover on engine");
			}
		}
		catch(Exception e) {
			System.out.println("unable to find specifications under Dio");
		}
	}
	@Then("Compare Dio and Activa Maps and print the different values of the samekeys")
	public void compareMaps() {
		List<String> dioKeys = new ArrayList<String>(dioEngineMap.keySet());
		List<String> dioValues = new ArrayList<String>(dioEngineMap.values());
		List<String> activaKeys = new ArrayList<String>(activa125Map.keySet());
		List<String> activaValues = new ArrayList<String>(activa125Map.values());
		if(dioKeys.size() == activaKeys.size()) {
			for(int i = 0; i <dioKeys.size(); i++) {
				if(dioKeys.get(i).equals(activaKeys.get(i))) {
					if(!dioValues.get(i).equalsIgnoreCase(activaValues.get(i))){
						System.out.println("The dio value "+dioValues.get(i)+" for the key "+dioKeys.get(i)+" doesn't match with the activa value "+activaValues.get(i));
					}
				}
			}
		}
	}
	@When("Click FAQ from Menu and Click dio under Browse By Product")
	public void faqClickDio() throws InterruptedException {
		try{
			hooks.getDriver().findElementByXPath("(//a[text()='FAQ'])[1]").click();
			try {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dio BS-VI']")));
		hooks.getDriver().findElementByXPath("//a[text()='Dio BS-VI']").click();
			}
			catch(Exception e) {
				System.out.println("couldn't click dio under browse by product");
			}
		}
		catch(Exception e) {
			System.out.println("Couldn't click FAQ");
		}
	}
	
	@And("Click  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit")
	public void selectVehicle() {
		try{
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Vehicle Price')]")));
			hooks.getDriver().findElementByXPath("//a[contains(text(),'Vehicle Price')]").click();
			try {
				Thread.sleep(500);
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='SegmentID6']")));
				WebElement eleScooter= hooks.getDriver().findElementByXPath("//select[@id='SegmentID6']");
				Select ddScooter = new Select(eleScooter);
				String scooterText = ddScooter.getFirstSelectedOption().getText();
				Thread.sleep(500);
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[contains(@id,'ModelID')])[6]")));
				WebElement eleDio= hooks.getDriver().findElementByXPath("(//select[contains(@id,'ModelID')])[6]");
				Select ddDio = new Select(eleDio);
				String dioText = ddScooter.getFirstSelectedOption().getText();
				if(!scooterText.contains("Scooter")) {
					ddScooter.selectByVisibleText("Scooter");
				}
				else if(!dioText.contains("Dio BS-VI")) {
					ddDio.selectByVisibleText("Dio BS-VI");
				}
				Thread.sleep(500);
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Submit'])[6]")));
				hooks.getDriver().findElementByXPath("(//button[text()='Submit'])[6]").click();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("click the price link,  Go to the new Window and select the state, city")
	public void priceLinkClick() throws InterruptedException {
		
		try{Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Click here to know the price')]")));
		hooks.getDriver().findElementByXPath("//a[contains(text(),'Click here to know the price')]").click();
		try {
			Set<String> windowHandles = hooks.getDriver().getWindowHandles();
			List<String> winList = new ArrayList<String>(windowHandles);
			hooks.getDriver().switchTo().window(winList.get(1));
			try {
				WebElement eleState = hooks.getDriver().findElementByXPath("//select[@name='StateID']");
				Select ddState = new Select(eleState);
				ddState.selectByVisibleText("Tamil Nadu");
				try {
				WebElement eleCity = hooks.getDriver().findElementByXPath("//select[@name='CityID']");
				Select ddCity = new Select(eleCity);
				ddCity.selectByVisibleText("Chennai");
				try {
					hooks.getDriver().findElementByXPath("//button[text()='Search']").click();
				}
				catch (Exception e) {
					System.out.println("couldn't click the search button");
					e.printStackTrace();
				}
				}
				catch(Exception e) {
					System.out.println("couldn't select the city");
					e.printStackTrace();
				}
			}
			catch(Exception e) {
				System.out.println("couldn't select the state");
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			System.out.println("couldn't switch to new window");
			e.printStackTrace();
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Then("Print the price and model")
	public void printPrice() {
		try {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='gvshow']/tbody/tr/td")));
			List<WebElement> priceList = hooks.getDriver().findElementsByXPath("//table[@id='gvshow']/tbody/tr/td");
			for(int i = 1; i <priceList.size(); i++) {
				if(i%2==0) {
					System.out.println("the Price is "+priceList.get(i).getText());
				}
				else {
					System.out.print("the model is "+priceList.get(i).getText()+"--->");
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("Click Product Enquiry and Fill all the * field except Mobile, check the terms and conditions box and click submit")
	public void prodEnquiry() {
		try{
			hooks.getDriver().findElementByXPath("//a[contains(@href,'product-enquiry')]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='ModelID']")));
			WebElement eleModel = hooks.getDriver().findElementByXPath("//select[@id='ModelID']");
			select(eleModel, "Activa 125 BS-VI");
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='StateID']")));
			WebElement eleState = hooks.getDriver().findElementByXPath("//select[@id='StateID']");
			select(eleState, "Tamil Nadu");
			WebElement eleCity = hooks.getDriver().findElementByXPath("//select[@id='CityID']");
			select(eleCity, "Chennai");
			WebElement eleDealer = hooks.getDriver().findElementByXPath("//select[@id='DealerID']");
			select(eleDealer, "Akshaya Honda");
			WebElement eleTitle = hooks.getDriver().findElementByXPath("//select[@id='TitleID']");
			select(eleTitle, "Ms.");
			hooks.getDriver().findElementByXPath("//input[@id='Name']").sendKeys("abcdefg");
			hooks.getDriver().findElementByXPath("//input[@id='Email']").sendKeys("abc@gmail.com");
			hooks.getDriver().findElementByXPath("//input[@id='TermsAndConditions']").click();
			hooks.getDriver().findElementByXPath("//button[@id='submit']").click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Then("Verify the error message under the mobile number field")
	public void captureErrorMsg() {
		try {
			Thread.sleep(1000);
			System.out.println("The error message(s) as below: ");
			List<WebElement> errorList = hooks.getDriver().findElementsByXPath("//span[@class='text-danger field-validation-error']/span");
			for (WebElement eachElement : errorList) {
				System.out.println(eachElement.getText());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@And("Close honda browser")
	public void closeBrowser() {
		hooks.getDriver().quit();
	}
	public void select(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
}
