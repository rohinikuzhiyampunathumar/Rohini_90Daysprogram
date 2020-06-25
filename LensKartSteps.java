package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class LensKartSteps {
	private Hooks hooks;
	 private WebDriverWait wt;
	 private Actions builder;
	 private JavascriptExecutor js;
		
		public LensKartSteps(Hooks hooks) {
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
		
		@Given("Go to https://www.lenskart.com/")
		public void launchbigBasket() throws InterruptedException {
				hooks.getDriver().get("https://www.lenskart.com/");
				hooks.getDriver().manage().window().maximize();
				hooks.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		}
		@When("Mouseover on Contact Lenses")
		public void mOContactLenses() {
			WebElement eleLens = hooks.getDriver().findElementByXPath("//a[text()='Contact Lenses']");
			getActions().moveToElement(eleLens).perform();
		}
		@And("Click on Monthly under Explore By Disposability")
		public void monthClick() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Monthly']/parent::a")));
			hooks.getDriver().findElementByXPath("//span[text()='Monthly']/parent::a").click();
		}
		@And("Select brand as Aqualens")
		public void selectAquaLens() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Aqualens')]/parent::label")));
			hooks.getDriver().findElementByXPath("//span[contains(text(),'Aqualens')]/parent::label").click();
		}
		@And("Click on the first product")
		public void clickFirstProd() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='productWidgetBox_widgetImage'])[1]")));
			hooks.getDriver().findElementByXPath("(//div[@class='productWidgetBox_widgetImage'])[1]").click();
			WebElement eleWhiteSpace = hooks.getDriver().findElementByXPath("//div[@class='pdpHeaderActions_list noDitto']");
			getActions().moveToElement(eleWhiteSpace).perform();
		}
		@And("Click Buy Now")
		public void clickBuyNow() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='BUY NOW']")));
			hooks.getDriver().findElementByXPath("//button[text()='BUY NOW']").click();
		}
		@And("Select No of boxes as 2 and Power as -1 for both eyes")
		public void selectBoxes() throws InterruptedException {
			Thread.sleep(1000);
			WebElement elebox1 = hooks.getDriver().findElementByXPath("(//select[@name='boxes'])[1]");
			Select ddbox1 = new Select(elebox1);
			ddbox1.selectByVisibleText(" 2 Box");
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@name='boxes'])[2]")));
			WebElement elebox2 = hooks.getDriver().findElementByXPath("(//select[@name='boxes'])[2]");
			Select ddbox2 = new Select(elebox2);
			ddbox2.selectByVisibleText("2 Box");
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Please Select'])[1]")));
			hooks.getDriver().findElementByXPath("(//span[text()='Please Select'])[1]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='-1.00']")));
			hooks.getDriver().findElementByXPath("//div[text()='-1.00']").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Please Select'])[1]")));
			hooks.getDriver().findElementByXPath("(//span[text()='Please Select'])[1]").click();
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='-1.00']")));
			hooks.getDriver().findElementByXPath("//div[text()='-1.00']").click();
		}
		@And("Type your name in User's name")
		public void enterUserName() {
			WebElement eleUName = hooks.getDriver().findElementByXPath("//input[@id='example-text-input']");
			eleUName.click();
			eleUName.sendKeys("Rohini", Keys.TAB);
		}
		@And("click Save and continue")
		public void clickContinue() {
			hooks.getDriver().findElementByXPath("(//button[text()='SAVE & CONTINUE'])[1]").click();
		}
		@Then("Print total amount and click Proceed to Checkout")
		public void proceedCheckOut() throws InterruptedException {
			Thread.sleep(500);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Order Total')]/following-sibling::span")));
			String eleTotal = hooks.getDriver().findElementByXPath("//span[contains(text(),'Order Total')]/following-sibling::span").getText();
			System.out.println("The total is "+eleTotal);
			hooks.getDriver().findElementByXPath("//span[text()='Proceed To Checkout']/parent::a").click();
		}
}
