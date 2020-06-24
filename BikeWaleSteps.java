package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hooks.Hooks;

public class BikeWaleSteps {

	private Hooks hooks;
	 private WebDriverWait wt;
	 private Actions builder;
	 private JavascriptExecutor js;
		
		public BikeWaleSteps(Hooks hooks) {
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
		
		@Given("Go to https://www.bikewale.com/")
		public void launchbigBasket() throws InterruptedException {
				hooks.getDriver().get("https://www.bikewale.com/");
				hooks.getDriver().manage().window().maximize();
				hooks.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		}
		@When("Go to menu and click new bikes")
		public void selctMSComputer() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='navbarBtn nav-icon']")));
			hooks.getDriver().findElementByXPath("//span[@class='navbarBtn nav-icon']").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='New Bikes']")));
			hooks.getDriver().findElementByXPath("//a[@title='New Bikes']").click();
			
		}
		@And("Click New Bikes Then compare bikes")
		public void compareBikes() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Compare Bikes']")));
			hooks.getDriver().findElementByXPath("//a[text()='Compare Bikes']").click();
		}
		@And("Add first bike as Royal Enfield and model as Thunderbird 350")
		public void addFirstBike() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='add-icon'])[1]")));
			hooks.getDriver().findElementByXPath("(//span[@class='add-icon'])[1]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='chosen-container chosen-container-single']")));
			hooks.getDriver().findElementByXPath("//div[@class='chosen-container chosen-container-single']").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[2]")));
			WebElement brand1 = hooks.getDriver().findElementByXPath("(//input[@type='text'])[2]");
			brand1.click();
			brand1.sendKeys("Royal Enfield");
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[text()='Royal Enfield']/parent::li")));
			hooks.getDriver().findElementByXPath("//em[text()='Royal Enfield']/parent::li").click();
			Thread.sleep(1000);
			hooks.getDriver().findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[2]").click();
			hooks.getDriver().findElementByXPath("//li[text()='Thunderbird 350']").click();
		}
		@And("Add second bike Jawa, model as 42 and version Dual Channel ABS - BS VI")
		public void addSecondBike() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='add-icon'])[2]")));
			hooks.getDriver().findElementByXPath("(//span[@class='add-icon'])[2]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='chosen-container chosen-container-single'])[4]")));
			hooks.getDriver().findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[4]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[5]")));
			WebElement brand1 = hooks.getDriver().findElementByXPath("(//input[@type='text'])[5]");
			brand1.click();
			brand1.sendKeys("Jawa");
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[text()='Jawa']/parent::li")));
			hooks.getDriver().findElementByXPath("//em[text()='Jawa']/parent::li").click();
			Thread.sleep(1000);
			hooks.getDriver().findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[5]").click();
			hooks.getDriver().findElementByXPath("//li[text()='42']").click();
			hooks.getDriver().findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[6]").click();
			hooks.getDriver().findElementByXPath("//li[text()='Dual Channel ABS - BS VI']").click();
		}
		@And("Add bike brand Kawasaki model as Ninja 300")
		public void thirdBike() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='add-icon'])[3]")));
			hooks.getDriver().findElementByXPath("(//span[@class='add-icon'])[3]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='chosen-container chosen-container-single'])[7]")));
			hooks.getDriver().findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[7]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[8]")));
			WebElement brand1 = hooks.getDriver().findElementByXPath("(//input[@type='text'])[8]");
			brand1.click();
			brand1.sendKeys("Kawasaki");
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[text()='Kawasaki']/parent::li")));
			hooks.getDriver().findElementByXPath("//em[text()='Kawasaki']/parent::li").click();
			hooks.getDriver().findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[8]").click();
			hooks.getDriver().findElementByXPath("//li[text()='Ninja 300']").click();
		}
		@And("click compare")
		public void clickCompare() {
			hooks.getDriver().findElementByXPath("//button[@id='btnCompare']").click();
		}
		@Then("Find and print the maximum overall rating of all the bikes and find the max")
		public void printRating() {
			List<WebElement> ratingList = hooks.getDriver().findElementsByXPath("//span[@class='font20 font-bold']");
			List<Float> maxRatingList = new ArrayList<Float>();
			for (WebElement eachElement : ratingList) {
				String rating= eachElement.getText();
				System.out.println(rating);
				float ratingFloat = Float.parseFloat(rating);
				maxRatingList.add(ratingFloat);
			}
			Collections.sort(maxRatingList);
			System.out.println("the max rating is "+maxRatingList.get(maxRatingList.size()-1));
		}
}
