package first21DaysTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wt = new WebDriverWait(driver,30);
		int count = 0;
//1)Go to https://www.zalando.com/
		driver.get("https://www.zalando.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//2) Get the Alert text and print it
		String alertText = driver.switchTo().alert().getText();
		System.out.println("The Alert tect is "+alertText);
		driver.switchTo().alert().accept();
//3) Close the Alert box and click on Zalando.uk
		driver.findElementByXPath("//a[contains(text(),'Zalando.uk')]").click();
//4) Click Women--> Clothing and click Coat 
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'s OK')]")));
		driver.findElementByXPath("//button[contains(text(),'s OK')]").click();
		WebElement eleClothing = driver.findElementByXPath("(//span[text()='Clothing'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleClothing).perform();
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Coats']")));
		driver.findElementByXPath("//span[text()='Coats']").click();
//5) Choose Material as cotton (100%) and Length as thigh-length
		JavascriptExecutor js= (JavascriptExecutor)driver;
		Thread.sleep(500);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='filter by Material']")));
		WebElement eleMaterial = driver.findElementByXPath("//button[@aria-label='filter by Material']");
		builder.moveToElement(eleMaterial).perform();
		Thread.sleep(500);
		eleMaterial.click();
		Thread.sleep(500);
		WebElement eleCotton = driver.findElementByXPath("//span[text()='cotton (100%)']");
		js.executeScript("arguments[0].click();", eleCotton);
		Thread.sleep(500);
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(500);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='filter by Length']")));
		WebElement eleLength = driver.findElementByXPath("//button[@aria-label='filter by Length']");
		builder.moveToElement(eleLength).perform();
		Thread.sleep(500);
		eleLength.click();
		WebElement eleThighhigh = driver.findElementByXPath("//span[text()='thigh-length']");
		js.executeScript("arguments[0].click();", eleThighhigh);
		Thread.sleep(1000);
		WebElement eleSave= driver.findElementByXPath("//button[text()='Save']");
		js.executeScript("arguments[0].click();", eleSave);
//6) Click on Q/S designed by MANTEL - Parka coat
		WebElement eleMantel = driver.findElementByXPath("//div[text()='Q/S designed by']/following-sibling::div[contains(text(),'MANTEL - Parka')]");
		js.executeScript("arguments[0].click();", eleMantel);
//7) Check the availability for Color as Olive and Size as 'M'
//8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
//9) Add to bag only if Standard Delivery is free
		driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
		
		try {
			driver.findElementByXPath("//h2[text()='Out of stock']");
			System.out.println("The selected color Olive is not available");
			driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
			Thread.sleep(1000);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Choose your size']")));
			driver.findElementByXPath("//button[@aria-label='Choose your size']").click();
			Thread.sleep(500);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='M']/parent::div")));
			driver.findElementByXPath("//span[text()='M']/parent::div").click();
			try {
				driver.findElementByXPath("//h2[text()='Out of stock']");
				System.out.println("The Size medium is not available in blue color");
				count++;
			}
			catch(Exception med) {
				Thread.sleep(500);
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='AtOZbZ'])[2]")));
				String shipping = driver.findElementByXPath("(//span[@class='AtOZbZ'])[2]").getText();
				
					if(shipping.contains("Free")) {
						driver.findElementByXPath("//span[text()='Add to bag']").click();			
					}
						else{
					System.out.println("The Shipping is not free");
					count++;
				}
			}
			
		}
		catch(Exception e) {
			Thread.sleep(1000);
			driver.findElementByXPath("//button[@aria-label='Choose your size']").click();
			Thread.sleep(1000);
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='M']/parent::div")));
			driver.findElementByXPath("//span[text()='M']/parent::div").click();
			try {
				Thread.sleep(500);
				driver.findElementByXPath("//h2[text()='Out of stock']");
				Thread.sleep(500);
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='close']/parent::button")));
				driver.findElementByXPath("//span[text()='close']/parent::button").click();
				System.out.println("The Size medium is not available in Olive color");
				driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
				Thread.sleep(1000);
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Choose your size']")));
				driver.findElementByXPath("//button[@aria-label='Choose your size']").click();
				Thread.sleep(500);
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='M']/parent::div")));
				driver.findElementByXPath("//span[text()='M']/parent::div").click();
				try {
					driver.findElementByXPath("//h2[text()='Out of stock']");
					System.out.println("The Size medium is not available in blue color");
					count++;
				}
				catch(Exception freedel) {
					Thread.sleep(500);
					wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='AtOZbZ'])[2]")));
					String shipping = driver.findElementByXPath("(//span[@class='AtOZbZ'])[2]").getText();
					
						if(shipping.contains("Free")) {
							driver.findElementByXPath("//span[text()='Add to bag']").click();			
						}
							else{
						System.out.println("The Shipping is not free");
						count++;
					}
				}
			
				
			}
			catch(Exception medium) {
				Thread.sleep(500);
				wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='AtOZbZ'])[2]")));
				String shipping = driver.findElementByXPath("(//span[@class='AtOZbZ'])[2]").getText();
				
					if(shipping.contains("Free")) {
						driver.findElementByXPath("//span[text()='Add to bag']").click();			
					}
						else{
					System.out.println("The Shipping is not free");
					count++;
				}
				
			}
			}
		finally {
			if(count==0) {
//10) Mouse over on Your Bag and Click on "Go to Bag"
	Thread.sleep(500);
	wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='z-navicat-header_navToolItemLink']")));
	WebElement eleCart = driver.findElementByXPath("//a[@class='z-navicat-header_navToolItemLink']");
	builder.moveToElement(eleCart).perform();
	Thread.sleep(500);
	wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Go to bag']")));
	driver.findElementByXPath("//div[text()='Go to bag']").click();
//11) Capture the Estimated Deliver Date and print
	String estDelivery = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();
	System.out.println("The estimated Delivery period "+estDelivery);
//12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
	Thread.sleep(500);
	wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Free delivery & returns')]")));
	WebElement eleDeliv = driver.findElementByXPath("//a[contains(text(),'Free delivery & returns')]");
	builder.moveToElement(eleDeliv).perform();
	String deliveryMsg = driver.findElementByXPath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]").getAttribute("title");
	System.out.println("The free shipping and returns message is "+deliveryMsg);
//13) Click on FREE DELIVERY & RETURNS
	driver.findElementByXPath("//a[contains(text(),'Free delivery & returns')]").click();
//14) Click on Start chat in the Start chat and go to the new window
	Thread.sleep(1000);
	wt.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='channel__button'])[1]")));
	WebElement eleChat = driver.findElementByXPath("(//div[@class='channel__button'])[1]");
	builder.moveToElement(eleChat).perform();
	Thread.sleep(500);
	eleChat.click();
	Set<String> winSet = driver.getWindowHandles();
	List<String> winList = new ArrayList<String>(winSet);
	driver.switchTo().window(winList.get(1));
//15) Enter you first name and a dummy email and click Start Chat
	driver.findElementById("prechat_customer_name_id").sendKeys("nameabc");
	driver.findElementById("prechat_customer_email_id").sendKeys("abc@gmail.com");
	driver.findElementById("prechat_submit").click();
//16) Type Hi, click Send and print thr reply message and close the chat window.
	Thread.sleep(500);
	wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("liveAgentChatTextArea")));
	driver.findElementById("liveAgentChatTextArea").sendKeys("hi",Keys.TAB);
	WebElement eleSend = driver.findElementByXPath("//button[@class='liveAgentChatElement liveAgentSendButton']");
	js.executeScript("arguments[0].click();", eleSend);
	Thread.sleep(2000);
	List<WebElement> eleResponse = driver.findElementsByXPath("//span[@class='client']/following::span[@class='operator']/span");
	for(int i = 0; i <eleResponse.size(); i++) {
		String msg = eleResponse.get(i).getText();
		System.out.println(msg);
	}
	
			}
					}

	}

}
