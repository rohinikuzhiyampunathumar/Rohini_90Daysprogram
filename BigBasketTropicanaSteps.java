package steps;

import java.util.List;
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

public class BigBasketTropicanaSteps{

 private Hooks hooks;
 private WebDriverWait wt;
 private Actions builder;
 private JavascriptExecutor js;
 static List<WebElement> buttonList;
	
	public BigBasketTropicanaSteps(Hooks hooks) {
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
	
	@Given("Go to https://www.bigbasket.com/")
	public void launchbigBasket() {
		hooks.getDriver().get("https://www.bigbasket.com/");
		hooks.getDriver().manage().window().maximize();
		hooks.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wt = new WebDriverWait(hooks.getDriver(), 40);
	}
	@And("mouse over on  Shop by Category")
	public void shopByCategory() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-toggle meganav-shop']")));
		WebElement eleCategory = hooks.getDriver().findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
		getActions().moveToElement(eleCategory).perform();		
	}
	@And("Go to Beverages and Fruit juices & Drinks")
	public void selectBeverages() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Beverages'])[2]")));
		WebElement eleBeverages = hooks.getDriver().findElementByXPath("(//a[text()='Beverages'])[2]");
		getActions().moveToElement(eleBeverages).perform();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Fruit Juices & Drinks'])[2]")));
		hooks.getDriver().findElementByXPath("(//a[text()='Fruit Juices & Drinks'])[2]").click();		
	}
	@And("Click on JUICES")
	public void clickJuices() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Juices'])[1]")));
	hooks.getDriver().findElementByXPath("(//span[text()='Juices'])[1]").click();
	}
	@And("click Tropicana and Real under Brand")
	public void clickTropicana() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Real'])[1]")));
	hooks.getDriver().findElementByXPath("(//span[text()='Real'])[1]").click();
	Thread.sleep(500);
	getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Tropicana'])[1]")));
	hooks.getDriver().findElementByXPath("(//span[text()='Tropicana'])[1]").click();
	}
	@Then("Check count of the products from each Brands and total count")
	public void verifyEachBrandCount() throws InterruptedException {
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h6[@class='ng-binding']")));
		List<WebElement> resultList = hooks.getDriver().findElementsByXPath("//h6[@class='ng-binding']");
		int totalCount = resultList.size();
		int realCount = 0;
		int tropicanaCount = 0;
		for (WebElement eachItem : resultList) {
			if(eachItem.getText().equalsIgnoreCase("Real")) {
				realCount++;
			}
			else if(eachItem.getText().equalsIgnoreCase("Tropicana")) {
				tropicanaCount++;
			}
		}
		System.out.println("The real count is "+realCount);
		System.out.println("The tropicana count is "+tropicanaCount);
		if((realCount+tropicanaCount)==totalCount) {
			System.out.println("The real and tropicana juice count matches with total");
		}
		else {
			System.err.println("The real and tropicana juice count matches with total");
		}
	}
	@And("Check whether the products is availabe with Add button")
	public void verifyProdWithAddBtn() throws InterruptedException {
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h6[@class='ng-binding']/following::button[@class='btn btn-add col-xs-9']")));
		buttonList = hooks.getDriver().findElementsByXPath("//h6[@class='ng-binding']/following::button[@class='btn btn-add col-xs-9']");
		int totalbtn =buttonList.size();
		int addBtnCount = 0;
		int otherBtnCount = 0;
		for (WebElement eachBtn : buttonList) {
			if(eachBtn.getText().contains("ADD")) {
				addBtnCount++;
			}
			else {
				otherBtnCount++;
			}
		}
		System.out.println("The total juices with add button is "+addBtnCount);
		System.out.println("The total juices without add button is "+otherBtnCount);
		
		if(totalbtn == addBtnCount) {
			System.out.println("All products are available with add button");
		}
		else {
			System.err.println("Not all products are available with add button");
		}
	}
	@And("Add the First listed available product")
	public void AddFirstJuiceResult() throws InterruptedException {
		for(int i =0; i <buttonList.size(); i++) {
			if(buttonList.get(i).getText().contains("ADD")) {
				Thread.sleep(1000);
				buttonList.get(i).click();
				System.out.println("Clicked add button");
				Thread.sleep(2000);
				break;
			}
			}
		}			
	@And("click on Change Address")
	public void changeLocationClick() throws InterruptedException {
		Thread.sleep(1000);
//		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Change Location'])[1]")));
		hooks.getDriver().findElementByXPath("(//a[text()='Change Location'])[1]").click();
	}
	@And("Select CHennai as City, Alandur-600016,Chennai as Area  and click Continue")
	public void selectCityChennai() throws InterruptedException {
		String eleCity = hooks.getDriver().findElementByXPath("(//span[@class='btn btn-default form-control ui-select-toggle'])[1]/span/span").getText();
		if(!eleCity.contains("Chennai")) {
			WebElement eleselectCity = hooks.getDriver().findElementByXPath("(//span[@class='btn btn-default form-control ui-select-toggle'])[1]/span/span");
			eleselectCity.click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[starts-with(@class,'form-control ui-select-search')])[1]")));
			WebElement eleSearachCity = hooks.getDriver().findElementByXPath("(//input[starts-with(@class,'form-control ui-select-search')])[1]");
			eleSearachCity.click();
			eleSearachCity.sendKeys("Chennai");
			Thread.sleep(500);
//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Chennai'])[2]/parent::a")));
			WebElement eleChennai = hooks.getDriver().findElementByXPath("//span[text()='Chennai']/parent::a");
			getJs().executeScript("arguments[0].click();", eleChennai);
		}
		Thread.sleep(500);
		hooks.getDriver().findElementByXPath("//input[@id='areaselect']").sendKeys("600016");
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='600016']")));
		hooks.getDriver().findElementByXPath("//strong[text()='600016']").click();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='continue']")));
		WebElement eleContinue = hooks.getDriver().findElementByXPath("//button[@name='continue']");
		getActions().moveToElement(eleContinue).perform();
		Thread.sleep(1000);
		eleContinue.click();
		System.out.println("Clicked on continue button");
	}
	@And("Mouse over on My Basket print the product name count and price")
	public void verifyCountPrice() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'btn btn-default basket-btn-drop')]")));
		WebElement eleBasket = hooks.getDriver().findElementByXPath("//a[contains(@class,'btn btn-default basket-btn-drop')]");
		getActions().moveToElement(eleBasket).perform();
		String ProdName = hooks.getDriver().findElementByXPath("//div[@class='product-name']/a").getText();
		String countAllChars = hooks.getDriver().findElementByXPath("//div[@class='product-qty ng-binding']").getText();
		String countString = "";
		String price = "";
		int xcount =0;
		for(int i=0; i <countAllChars.length(); i++) {
			if(!(countAllChars.charAt(i)=='x')&&xcount==0) {
				countString = countString+countAllChars.charAt(i);
			}
			else if((countAllChars.charAt(i)=='x')&&xcount==0) {
				xcount++;
			}
			else if(!(countAllChars.charAt(i)=='x')&&xcount>0) {
				price = price+countAllChars.charAt(i);
			}			
		}
		String count = countString.replaceAll(" ", "");
		String priceString = price.replaceAll(" ", "");
		int countInt = Integer.parseInt(count);
		double priceInt = Double.parseDouble(priceString);
		System.out.println("The product name is "+ProdName);
		System.out.println("The prod count is "+countInt);
		System.out.println("the price is "+priceInt);
	}
	@And("Click View Basket and Checkout")
	public void viewAndCheckout() {
		hooks.getDriver().findElementByXPath("//button[text()='View Basket & Checkout']").click();
	}
	@And("Click the close button and close the browser")
	public void clickCloseAndBrowserClose() throws InterruptedException {
	Thread.sleep(500);
	getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='close'])[1]")));
	hooks.getDriver().findElementByXPath("(//button[@class='close'])[1]").click();
	hooks.getDriver().quit();
	}
}
