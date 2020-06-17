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
import cucumber.api.java.en.When;
import hooks.Hooks;

public class DemoCRMSteps {
	
	private Hooks hooks;
	private static WebDriverWait wt;
	private JavascriptExecutor js;
	private Actions builder;
	
	public DemoCRMSteps(Hooks hooks) {
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
	public void select(WebElement eleDD, String ddValue) {
		Select sel = new Select(eleDD);
		sel.selectByVisibleText(ddValue);
	}
	@Given("Go to https://demo.1crmcloud.com/")
	public void launchDemoCRM() {
		hooks.getDriver().get("https://demo.1crmcloud.com/");
		hooks.getDriver().manage().window().maximize();
		hooks.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wt = new WebDriverWait(hooks.getDriver(), 40);
	}
	@When("you Give username as (.*) and password as (.*)")
	public void login(String uName, String pwd) {
		hooks.getDriver().findElementById("login_user").sendKeys(uName);
		hooks.getDriver().findElementById("login_pass").sendKeys(pwd);
	}
	@And("Choose theme as Claro Theme and login")
	public void selectTheme() {
		WebElement eleTheme = hooks.getDriver().findElementById("login_theme");
		select(eleTheme,"Claro Theme");
		hooks.getDriver().findElementById("login_button").click();
	}
	@And("Go to Sales and Marketting and click Sales Home")
	public void salesHome() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sales & Marketing']")));
		WebElement eleSalesM = hooks.getDriver().findElementByXPath("//div[text()='Sales & Marketing']");
		getActions().moveToElement(eleSalesM).perform();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sales & Marketing']")));
		hooks.getDriver().findElementByXPath("//div[text()='Sales & Marketing']").click();
	}
	@And("Click Create contact")
	public void clickCreate() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Create Contact']")));
		hooks.getDriver().findElementByXPath("//div[text()='Create Contact']").click();	
	}
	@And("Select Title and type First name, Last Name, Email and Phone Numbers")
	public void enterPersonalDetails() throws InterruptedException {
		Thread.sleep(3000);
		WebElement eleTitle = hooks.getDriver().findElementByXPath("//div[@id='DetailFormsalutation-input']");
		eleTitle.click();
		Thread.sleep(2000);
		hooks.getDriver().findElementByXPath("//div[text()='Ms.']").click();
		WebElement eleFName = hooks.getDriver().findElementById("DetailFormfirst_name-input");
		eleFName.click();
		eleFName.sendKeys("Rohini", Keys.TAB);
		WebElement eleLName = hooks.getDriver().findElementById("DetailFormlast_name-input");
		eleLName.click();
		eleLName.sendKeys("Sowmya", Keys.TAB);
		WebElement eleEmail = hooks.getDriver().findElementById("DetailFormemail1-input");
		eleEmail.click();
		eleEmail.sendKeys("abc@gmail.com", Keys.TAB);
		WebElement elePhNo = hooks.getDriver().findElementById("DetailFormphone_work-input");
		elePhNo.click();
		elePhNo.sendKeys("1234567891", Keys.TAB);
	}
	@And("Select Lead Source as Public Relations and Business Roles")
	public void selectRoles() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DetailFormlead_source-input']")));
		hooks.getDriver().findElementByXPath("//div[@id='DetailFormlead_source-input']").click();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Public Relations']")));
		hooks.getDriver().findElementByXPath("//div[text()='Public Relations']").click();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DetailFormbusiness_role-input']")));
		hooks.getDriver().findElementByXPath("//div[@id='DetailFormbusiness_role-input']").click();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Admin']")));
		hooks.getDriver().findElementByXPath("//div[text()='Admin']").click();
	}
	@And("Fill the Primary Address, City, State, Country and Postal Code and click Save")
	public void addressDetails() throws InterruptedException {
		hooks.getDriver().findElementById("DetailFormprimary_address_street-input").sendKeys("123 abc street", Keys.TAB);
		hooks.getDriver().findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai", Keys.TAB);
		Thread.sleep(1000);
		hooks.getDriver().findElementById("DetailFormprimary_address_state-input").sendKeys("TamilNadu");
		Thread.sleep(1000);
		hooks.getDriver().findElementById("DetailFormprimary_address_country-input").click();
		hooks.getDriver().findElementById("DetailFormprimary_address_country-input").sendKeys("India", Keys.TAB);
		hooks.getDriver().findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600001", Keys.TAB);
		hooks.getDriver().findElementById("DetailForm_save2-label").click();
	}
	@And("Click create under Leads")
	public void clickCreateinLeads() throws InterruptedException {
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Create'])[2]")));
		hooks.getDriver().findElementByXPath("(//span[text()='Create'])[2]").click();
		hooks.getDriver().switchTo().activeElement();
	}
	@And("Fill First & Last name, Status as In Process, Title as Manager and Department as Sales")
	public void fillLeadDetails() throws InterruptedException {
		hooks.getDriver().findElementById("QuickCreateForm_0first_name-input").sendKeys("Sowmya", Keys.TAB);
		hooks.getDriver().findElementById("QuickCreateForm_0last_name-input").sendKeys("Rohini", Keys.TAB);
		hooks.getDriver().findElementById("QuickCreateForm_0status-input").click();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='In Process']")));
		hooks.getDriver().findElementByXPath("//div[text()='In Process']").click();
		hooks.getDriver().findElementById("QuickCreateForm_0title-input").sendKeys("Manager", Keys.TAB);
		hooks.getDriver().findElementById("QuickCreateForm_0department-input").sendKeys("Sales", Keys.TAB);
	}
	@And("Select Lead as Public Relations and fill department, Email and Phone Number")
	public void leadDeptDetails() throws InterruptedException {
		hooks.getDriver().findElementById("QuickCreateForm_0lead_source-input").click();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='Public Relations'])[2]")));
		hooks.getDriver().findElementByXPath("(//div[text()='Public Relations'])[2]").click();
		hooks.getDriver().findElementById("QuickCreateForm_0phone_work-input").sendKeys("9876543210", Keys.TAB);
		hooks.getDriver().findElementById("QuickCreateForm_0email1-input").sendKeys("cba@gmail.com", Keys.TAB);
	}
	@And("Click Save and View")
	public void clickSaveView() {
		hooks.getDriver().findElementById("QuickCreateForm_0_save_view-label").click();
	}
	@And("Mouse over on Today's Activities and click Meetings")
	public void clickMeetings() throws InterruptedException {
		Thread.sleep(2000);
		WebElement eleToday = hooks.getDriver().findElementByXPath("//div[contains(text(),'s Activities')]");
		getActions();
		builder.moveToElement(eleToday).perform();
		Thread.sleep(500);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Meetings']")));
		hooks.getDriver().findElementByXPath("//div[text()='Meetings']").click();
	}
	@And("Click Create and type Subject as Project Status Status as Planned and Time as tomorrow 3 pm")
	public void enterMeeting() throws InterruptedException {
		Thread.sleep(3000);
		hooks.getDriver().findElementByXPath("(//button[@class='input-button first'])[1]").click();
		Thread.sleep(2000);
		WebElement eleSubject = hooks.getDriver().findElementByXPath("//input[@id='DetailFormname-input']");
		eleSubject.click();
		eleSubject.sendKeys("Project Status");
		Thread.sleep(2000);
		WebElement eledate = hooks.getDriver().findElementById("DetailFormdate_start");
		eledate.click();
		Thread.sleep(2000);
		WebElement eleDatePick = hooks.getDriver().findElementByXPath("(//div[contains(@class,'current selected responsive')]/following-sibling::div)[1]");
		eleDatePick.click();
		WebElement eleTime = hooks.getDriver().findElementByXPath("(//input[@class='input-text'])[4]");
		eleTime.clear();
		eleTime.sendKeys("15:00",Keys.ENTER);
	}
	@And("Click Add paricipants, add your created Lead name and click Save")
	public void clickAddParticipants() throws InterruptedException {
		Thread.sleep(2000);
		hooks.getDriver().findElementByXPath("//button[@name='addInvitee']").click();
		Thread.sleep(2000);
		hooks.getDriver().findElementByXPath("//div[@cLASS='input-label input-field-icon lg']/following-sibling::input").sendKeys("Sowmya Rohini");
		Thread.sleep(1000);
		hooks.getDriver().findElementByXPath("//div[text()='Sowmya Rohini']").click();
		Thread.sleep(1000);
		hooks.getDriver().findElementByXPath("//button[@id='DetailForm_save2']").click();
	}
	@And("Click contacts under Sales and Marketting, search the lead Name and click the name from the result")
	public void searchCreatedLead() throws InterruptedException {
		WebElement eleSandM =hooks.getDriver().findElementByXPath("//a[contains(@class,'sales-marketing')]");
		getActions();
		builder.moveToElement(eleSandM).perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Contacts')]")));
		hooks.getDriver().findElementByXPath("//a[contains(text(),'Contacts')]").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("filter_text")));
		WebElement eleSear = hooks.getDriver().findElementById("filter_text");
		eleSear.sendKeys("Rohini Sowmya");
		Thread.sleep(1000);
		eleSear.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Rohini Sowmya')]")));
		hooks.getDriver().findElementByXPath("//a[contains(text(),'Sowmya Rohini')]").click();
		Thread.sleep(2000);
	}
	@And("Check weather the Meeting is assigned to the contact")
	public void verifyMeeting() {
		String actSubject = hooks.getDriver().findElementByXPath("//a[@class='listViewNameLink']").getText();
		String actStatus = hooks.getDriver().findElementByXPath("((//a[@class='listViewNameLink'])[1]/following::td[@class='listViewTd'])[1]").getText();
		String actDate = hooks.getDriver().findElementByXPath("((//a[@class='listViewNameLink'])[1]/following::span[@class='text-number'])[1]").getText();
		if(actSubject.equalsIgnoreCase("Project Status")&&actStatus.equals("Planned")&&actDate.equalsIgnoreCase("Tomorrow, 15:00")){
			System.out.println("meeting is assigned correctly, Test case passed");
		}
		else {
			System.out.println("Meeting not found, test case failed");
		}
		hooks.getDriver().findElementById("DetailForm_delete-label").click();
		hooks.getDriver().switchTo().alert().accept();
		hooks.getDriver().quit();
	}
	
	
	
}
