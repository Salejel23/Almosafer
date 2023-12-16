package Almosafer;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCases {
	String TheWebSite="https://global.almosafer.com/en";
	WebDriver driver= new ChromeDriver();  
	SoftAssert softassert=new SoftAssert();
	Random rand = new Random();
	
	String [] arabicCitiesNames= {"جدة","دبي"};
	String [] englishCitiesNames= {"dubai","jeddah","Riyadh"};
	
	int randomArbic=rand.nextInt(arabicCitiesNames.length) ;
	int randomEng=rand.nextInt( englishCitiesNames.length);
	

	
	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get(TheWebSite);
		WebElement ClickWelcome= driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		ClickWelcome.click();
	}
	
	@Test(enabled=false)
	public void CheckLanguage() {
		String ActualLang=driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLang="en";
		softassert.assertEquals(ActualLang,ExpectedLang,"this is to check the language");
	}
	
	@Test(enabled=false)
	public void ChekTheCurrency() {
		
		String ExpectedCurrency="SAR";
		WebElement CurrencyElement= driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String ActualCurrency= CurrencyElement.getText();
		softassert.assertEquals(ActualCurrency, ExpectedCurrency);		
	}
	
	@Test(enabled=false)
	public void CheckTheContactNum() {
		
		String EcpectedContactNumber="+966554400000";
		WebElement ContactElement= driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualContactNum=ContactElement.getText();
		softassert.assertEquals(ActualContactNum, EcpectedContactNumber);
	}
	
	@Test(enabled=false)
	public void CheckQitafLogo () {
		WebElement QitafLogo= driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']"));
		boolean isQitafLogoDisplayed=QitafLogo.isDisplayed();
		boolean expectedQitafLogoIsDisplayed=true;
		
		softassert.assertEquals(isQitafLogoDisplayed, expectedQitafLogoIsDisplayed);
	}
	
	@Test(enabled=false)
	public void HotelTabIsNotSelected() {
		WebElement HotelTab=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	String ActualSelectArea= HotelTab.getAttribute("aria-selected");
	String ExpectedSelectArea="false";
	
	Assert.assertEquals(ActualSelectArea, ExpectedSelectArea);
	
	}
	
	@Test(enabled = false)
	public void CheckTheDepatureAndArrivalDate() {
		WebElement ActualDepatureDate= driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement ActualReturnDate= driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
		
		int  ActualDepatureDateValue= Integer.parseInt(ActualDepatureDate.getText());
		int  ActualReturnDateValue= Integer.parseInt(ActualReturnDate.getText());
		
		LocalDate today= LocalDate.now();
		int ExpectedDepatureDateValue=today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDateValue=today.plusDays(2).getDayOfMonth();
		
		Assert.assertEquals(ActualDepatureDateValue,ExpectedDepatureDateValue );
		Assert.assertEquals(ActualReturnDateValue,ExpectedReturnDateValue);
		
		String DayElementOnTheWebsite= driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']")).getText().toUpperCase();
		Assert.assertEquals(DayElementOnTheWebsite, today.plusDays(1).getDayOfWeek().toString());
				}
	
	@Test(invocationCount = 1)
	public void changeLanguageOfTheWebsite() throws InterruptedException {
		
		String[] UrleWebsite={"https://www.almosafer.com/en?ncr=1","https://www.almosafer.com/ar?ncr=1"};
		int randomIndex= rand.nextInt(UrleWebsite.length);
		driver.get(UrleWebsite[randomIndex]);
	//this is to click hotel tab
		WebElement TabHotel= driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		TabHotel.click();
		Thread.sleep(2000);
		
		WebElement SearchInput= driver.findElement(By.className("phbroq-2"));
		
	String ActualLang=driver.findElement(By.tagName("html")).getAttribute("lang");
		
		if (driver.getCurrentUrl().contains("ar")) {
			
			Assert.assertEquals(ActualLang, "ar");
			SearchInput.sendKeys(arabicCitiesNames[randomArbic]);
			Thread.sleep(2000);
			
			WebElement ListOfCity=driver.findElement(By.className("phbroq-4"));
			List<WebElement>Item=ListOfCity.findElements(By.tagName("li"));
			Item.get(1).click();
		}else{
			Assert.assertEquals(ActualLang, "en");
			SearchInput.sendKeys(englishCitiesNames[randomEng]);
			Thread.sleep(2000);
			
			WebElement ListOfCity=driver.findElement(By.className("phbroq-4"));
			List<WebElement>Item=ListOfCity.findElements(By.tagName("li"));
			Item.get(1).click();
		}
		
		WebElement InputVistor=driver.findElement(By.className("tln3e3-1"));
		Select select = new Select(InputVistor);
		select.selectByIndex(1);
	}
	
	@AfterTest
	public void endTest() {
		softassert.assertAll();
	}
}
