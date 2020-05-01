package first21DaysTestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Shiksha {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,30);		
//1)Go to https://studyabroad.shiksha.com/
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='menuTab-div fnt-wt melabel'])[3]")));
		WebElement eleColleges = driver.findElementByXPath("(//label[@class='menuTab-div fnt-wt melabel'])[3]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleColleges).perform();
		driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();
//3) Select GRE under Exam Accepted and Score 300 & Below
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='GRE']/preceding-sibling::span")));
		driver.findElementByXPath("//p[text()='GRE']/preceding-sibling::span").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[text()='GRE']/following::select[@class='score-select-field'])[1]")));
		WebElement eleGreScore=driver.findElementByXPath("(//p[text()='GRE']/following::select[@class='score-select-field'])[1]");
		Select ddGreScore = new Select(eleGreScore);
		ddGreScore.selectByVisibleText("300 & below");
//4) Max 10 Lakhs under 1st year Total fees, USA under countries
		WebElement eleFees = driver.findElementByXPath("//p[text()='Max 10 Lakhs']/preceding-sibling::span");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", eleFees);
		Thread.sleep(1000);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='USA']/parent::p/preceding-sibling::span[@class='common-sprite'])")));
		driver.findElementByXPath("(//a[text()='USA']/parent::p/preceding-sibling::span[@class='common-sprite'])").click();
//5) Select Sort By: Low to high 1st year total fees
		Thread.sleep(1000);
		WebElement eleSortResult = driver.findElementById("categorySorter");
		Select ddSortResult = new Select(eleSortResult);
		ddSortResult.selectByVisibleText("Low to high 1st year total fees");
		Thread.sleep(1000);
//6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation facilities
		List<WebElement> eleUniv = driver.findElementsByXPath("//p[text()='Public university']/span");
		System.out.println("university size is "+eleUniv.size());
		List<WebElement> eleScholar = driver.findElementsByXPath("//p[text()='Scholarship']/span");
		System.out.println("Scholarship size is "+eleScholar.size());
		List<WebElement> eleAccomodation = driver.findElementsByXPath("//p[text()='Accommodation']/span");
		System.out.println("Accomodation size is "+eleAccomodation.size());
		for(int i=0; i<eleUniv.size();i++) {
			if(eleUniv.get(i).getAttribute("class").contains("tick")&&eleScholar.get(i).getAttribute("class").contains("tick")&&eleAccomodation.get(i).getAttribute("class").contains("tick")) {
				List<WebElement> eleAdd = driver.findElementsByXPath("//p[contains(text(),'compare')]/preceding-sibling::span");
				Thread.sleep(1000);
				WebElement eleAddtoCompare = eleAdd.get(i);
				js.executeScript("arguments[0].click();", eleAddtoCompare);
				}
		}
//7) Select the first college under Compare with similar colleges 
		driver.findElementByXPath("(//a[@class='add-tag-title'])[1]").click();
//8) Click on Compare College>
		driver.findElementByXPath("//strong[contains(text(),'Compare Colleges')]").click();
//9) Select When to Study as 2021
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='2021']")));
		driver.findElementByXPath("//strong[text()='2021']").click();
//10) Select Preferred Countries as USA
		WebElement eleCountry = driver.findElementByXPath("//div[text()='Preferred Countries']");
		js.executeScript("arguments[0].click();", eleCountry);
		WebElement eleUSA = driver.findElementByXPath("//label[contains(@for,'USA')]/span");
		js.executeScript("arguments[0].click();", eleUSA);
//11) Select Level of Study as Masters
		driver.findElementByXPath("//strong[text()='Masters']").click();
//12) Select Preferred Course as MS
		WebElement eleCourse = driver.findElementByXPath("//div[text()='Preferred Course']");
		js.executeScript("arguments[0].click();", eleCourse);
		driver.findElementByXPath("//li[text()='MS']").click();
//13) Select Specialization as "Computer Science & Engineering"		
		WebElement eleSpec = driver.findElementByXPath("//div[text()='Preferred Specialisations']");
		js.executeScript("arguments[0].click();", eleSpec);
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
//14) Click on Sign Up
		WebElement eleSign = driver.findElementByXPath("//a[contains(text(),'Sign Up')]");
		js.executeScript("arguments[0].click();", eleSign);
//15) Print all the warning messages displayed on the screen for missed mandatory fields
		List<WebElement> eleError = driver.findElementsByXPath("//div[contains(@style,'block')]/div/following-sibling::div");
		for(int i = 0; i <eleError.size();i++) {
			if(!eleError.get(i).getText().isEmpty()) {
			System.out.println(eleError.get(i).getText());
			}
		}
//close browser
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
