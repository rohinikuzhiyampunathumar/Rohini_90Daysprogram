package first21DaysTestCases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JustDial {

	public static void main(String[] args) throws InterruptedException, IOException {
//1) https://www.justdial.com/
				System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");			
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");				
				ChromeDriver driver = new ChromeDriver(options);
				driver.get("https://www.justdial.com/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String actPh = "";
//2) Click Auto Care in the left menu
				driver.findElementByXPath("//span[text()='Auto care']").click();
				Thread.sleep(1000);
//3) Click Car Repair
				driver.findElementByXPath("//span[text()='Car Repair']").click();
				Thread.sleep(1000);
//4) Click Car Brand as Hyundai
				driver.findElementByXPath("(//span[text()='Hyundai'])[1]").click();
				Thread.sleep(1000);
//5) Click Make as Hyundai Xcent
				driver.findElementByXPath("(//span[text()='Hyundai Xcent'])[1]").click();
				Thread.sleep(2000);		
				try {
				  WebDriverWait wait = new WebDriverWait(driver, 30);
				  wait.until(ExpectedConditions.visibilityOf(driver.findElementByCssSelector("section#best_deal_div>section>span")));
				  driver.findElementByCssSelector("section#best_deal_div>section>span").click();
				}catch(Exception e) {
					System.out.println("Notification not found");
				}
				finally {
					Map<String,String> hm = new LinkedHashMap<String,String>();
					hm.put("+", "dc");
					hm.put("(", "fe");
					hm.put(")", "hg");
					hm.put("-", "ba");
					hm.put("0", "acb");
					hm.put("1", "yz");
					hm.put("2", "wx");
					hm.put("3", "vu");
					hm.put("4", "ts");
					hm.put("5", "rq");
					hm.put("6", "po");
					hm.put("7", "nm");
					hm.put("8", "lk");
					hm.put("9", "ji");
					List<String> sortedCompanyName = new ArrayList<String>();
					List<String> sortedPhoneNo = new ArrayList<String>();
					List<WebElement> eleRating = driver.findElementsByXPath("//span[@class='green-box']");
					for (int i = 0; i < eleRating.size(); i++) {
						String ratingChar = eleRating.get(i).getText();
						float rating = Float.parseFloat(ratingChar);
						if (rating >= 4.5) {
							WebElement eleVote = driver.findElementByXPath("(//span[@class='green-box'])["+(i+1)+"]/following-sibling::span[contains(text(),'Votes')]");
							String votesChars = eleVote.getText();
							String voteallchars = votesChars.trim(); 
							String votesString = voteallchars.replaceAll("[^0-9]", ""); 
							int vote = Integer.parseInt(votesString); 
							  if (vote > 50) {
							  String companyName = driver.findElementByXPath("((//span[@class='green-box'])["+(i+1)+"]/preceding::span[@class='lng_cont_name'])["+(i+1)+"]").getText();
							  System.out.println(companyName); 
							  sortedCompanyName.add(companyName); 
							  if(i > 0) {
								  List<WebElement> PhoneNum = driver.findElementsByXPath("((//span[@class='green-box'])["+(i+1)+"]/preceding::p[@class='newrtings '])["+i+"]/following-sibling::p/span/span[contains(@class,'mobilesv icon')]");
							  int phSize = PhoneNum.size();
							  for(int ph = 0; ph<phSize; ph++) {
								  String name = PhoneNum.get(ph).getAttribute("class");
								  String convertedPh = name.substring(14);
								 for (Entry<String, String> eachEntry: hm.entrySet()) {
									if(eachEntry.getValue().equalsIgnoreCase(convertedPh)) {
										actPh = actPh+(eachEntry.getKey());
									}
								}
							  }
							  System.out.println("The value of decoded Ph Number is "+actPh);
							  sortedPhoneNo.add(actPh);
							  actPh = "";
							  }
							 else if(i==0){
								 List<WebElement> phoneNumI = driver.findElementsByXPath("(//span[@class='green-box'])["+(i+1)+"]/parent::a/parent::p/following-sibling::p/span/span[contains(@class,'mobilesv icon')]");
								 int phSizeI = phoneNumI.size();
								  System.out.println("size is "+phSizeI);		 
								  for(int ph = 0; ph<phSizeI; ph++) {
									  String name = phoneNumI.get(ph).getAttribute("class");
									  //System.out.println("Class Name is  --> "+name);
									  String convertedPh = name.substring(14);
									  //System.out.println("The converted Ph is "+convertedPh);
									 for (Entry<String, String> eachEntry: hm.entrySet()) {
										if(eachEntry.getValue().equalsIgnoreCase(convertedPh)) {
											actPh = actPh+(eachEntry.getKey());
										}
									}
									  
								  }
								  System.out.println("The value of decoded Ph Number is "+actPh);
								  sortedPhoneNo.add(actPh);
								  actPh = "";
							 }
								 
							 
						}
					}
						}
					File file = new File("C:\\Users\\rohini kumar\\Desktop\\Selenium\\90DayProgram\\ExcelOutput.xlsx");
					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sh = wb.createSheet("Output");
			for(int j=0 ; j<sortedCompanyName.size();j++ ) {
				
				sh.createRow(j).createCell(0).setCellValue(sortedCompanyName.get(j));	
				sh.getRow(j).createCell(1).setCellValue(sortedPhoneNo.get(j));	
				
			}
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			wb.close();
				}
//Close browser
				driver.quit();
			
		}
			}
