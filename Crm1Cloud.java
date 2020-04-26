package first21DaysTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crm1Cloud {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,30);
//1) Go to https://demo.1crmcloud.com/
		driver.get("https://demo.1crmcloud.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Give username as admin and password as admin
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");
//3) Choose theme as Claro Theme
		WebElement eleTheme = driver.findElementById("login_theme");
		Select ddTheme = new Select(eleTheme);
		ddTheme.selectByVisibleText("Flex Theme");
		driver.findElementByXPath("//span[text()='Login']").click();
//4) Click on Sales and Marketting
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'sales-marketing')]")));
		driver.findElementByXPath("//a[contains(@class,'sales-marketing')]").click();
//5) Click Create contact
		Actions builder = new Actions(driver);
		WebElement eleCreateCont = driver.findElementByXPath("//span[text()='Create Contact']");
		builder.moveToElement(eleCreateCont).click().perform();
		
//6) Select Title and type First name, Last Name, Email and Phone Numbers
		Thread.sleep(5000);
		WebElement eleTitle = driver.findElementById("DetailFormsalutation-input");
		eleTitle.click();
		driver.findElementByXPath("//div[text()='Ms.']").click();
		eleTitle.sendKeys(Keys.ENTER);
		driver.findElementById("DetailFormfirst_name-input").sendKeys("Rohini", Keys.TAB);
		driver.findElementById("DetailFormlast_name-input").sendKeys("Rohini", Keys.TAB);
		driver.findElementById("DetailFormemail1-input").sendKeys("rri@gmail.com", Keys.TAB);
		driver.findElementById("DetailFormphone_work-input").sendKeys("9111111119",Keys.TAB);
//7) Select Lead Source as "Public Relations"
		driver.findElementById("DetailFormlead_source-input").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Public Relations']")));
		driver.findElementByXPath("//div[text()='Public Relations']").click();
//8) Select Business Roles as "Sales"
		driver.findElementById("DetailFormbusiness_role-input").click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sales']")));
		driver.findElementByXPath("//div[text()='Sales']").click();
//9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("123 abc Street,\n abcpuram", Keys.TAB);
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai", Keys.TAB);
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("Tamil Nadu",Keys.TAB);
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India",Keys.TAB);
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600006",Keys.TAB);
		WebElement eleSave = driver.findElementByXPath("//button[@id='DetailForm_save']");
		builder.moveToElement(eleSave).click().perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='_form_header']/h3")));
//10) Mouse over on Today's Activities and click Meetings
		WebElement eleTodayAc = driver.findElementByXPath("//a[@class='mouseonly menu-tab today-activities']");
		builder.moveToElement(eleTodayAc).perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Meetings')]")));
		driver.findElementByXPath("//a[contains(text(),'Meetings')]").click();
//11) Click Create
		Thread.sleep(3000);
		//wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='input-button first'])[1]")));
		driver.findElementByXPath("(//button[@class='input-button first'])[1]").click();
//12) Type Subject as "Project Status" , Status as "Planned" 
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("DetailFormname-input")));
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		driver.findElementById("DetailFormstatus-input").click();
		driver.findElementByXPath("//div[text()='Planned']").click();
//13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
		Thread.sleep(2000);
		WebElement eledate = driver.findElementById("DetailFormdate_start");
		eledate.click();
		Thread.sleep(2000);
		WebElement eleDatePick = driver.findElementByXPath("(//div[@class='grid-cell number-cell text-right day inside current selected quiet responsive']/following::div[@class='grid-cell number-cell text-right day inside responsive'])[1]");
		eleDatePick.click();
		WebElement eleTime = driver.findElementByXPath("(//input[@class='input-text'])[4]");
		eleTime.clear();
		eleTime.sendKeys("15:00",Keys.ENTER);
		WebElement eleDuration = driver.findElementById("DetailFormduration-time");
		eleDuration.clear();
		eleDuration.sendKeys("1h", Keys.TAB);
//14) Click Add paricipants, add your created Contact name and click Save
		Thread.sleep(2000);
		driver.findElementByXPath("//span[contains(text(),'Add Participants')]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@cLASS='input-label input-field-icon lg']/following-sibling::input").sendKeys("Rohini Rohini");
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Rohini Rohini']").click();
		driver.findElementByXPath("(//span[text()='Save'])[3]").click();
//15) Go to Sales and Marketting-->Contacts
		WebElement eleSandM = driver.findElementByXPath("//a[contains(@class,'sales-marketing')]");
		builder.moveToElement(eleSandM).perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Contacts')]")));
		driver.findElementByXPath("//a[contains(text(),'Contacts')]").click();
//16) search the lead Name and click the name from the result
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter_text")));
		WebElement eleSear = driver.findElementById("filter_text");
		eleSear.sendKeys("Rohini Rohini");
		Thread.sleep(1000);
		eleSear.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Rohini Rohini')]")));
		driver.findElementByXPath("//a[contains(text(),'Rohini Rohini')]").click();
		Thread.sleep(2000);
//17) Check weather the Meeting is assigned to the contact under Activities Section.
		String actSubject = driver.findElementByXPath("//a[@class='listViewNameLink']").getText();
		String actStatus = driver.findElementByXPath("((//a[@class='listViewNameLink'])[1]/following::td[@class='listViewTd'])[1]").getText();
		String actDate = driver.findElementByXPath("((//a[@class='listViewNameLink'])[1]/following::span[@class='text-number'])[1]").getText();
		if(actSubject.equalsIgnoreCase("Project Status")&&actStatus.equals("Planned")&&actDate.equalsIgnoreCase("Tomorrow, 15:00")){
			System.out.println("meeting is assigned correctly, Test case passed");
		}
		else {
			System.out.println("Meeting not found, test case failed");
		}
		driver.findElementById("DetailForm_delete-label").click();
		driver.switchTo().alert().accept();
//Close Browser
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
