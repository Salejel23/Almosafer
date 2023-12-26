package Almosafer;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class parametersClass {
	String TheWebSite="https://global.almosafer.com/en";
	WebDriver driver= new ChromeDriver();  
	SoftAssert softassert=new SoftAssert();
	Random rand = new Random();
	
	String [] arabicCitiesNames= {"جدة","دبي"};
	String [] englishCitiesNames= {"dubai","jeddah","Riyadh"};
	
	int randomArbic=rand.nextInt(arabicCitiesNames.length) ;
	int randomEng=rand.nextInt( englishCitiesNames.length);
	
	public void theStartToWebsite() {
		
		driver.manage().window().maximize();
		driver.get(TheWebSite);
		WebElement ClickWelcome= driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		ClickWelcome.click();
	}
	
	public void checkTheLanguageFunction(String theLanguageNeedToCheck) {
		
		String ActualLang=driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLang=theLanguageNeedToCheck;
		softassert.assertEquals(ActualLang,ExpectedLang,"this is to check the language");
	}
	
	public void checkTheCurrencyFunction(String ExpectedCurrency) {
		
		String theCurrencyCheck= ExpectedCurrency;
		WebElement CurrencyElement= driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String ActualCurrency= CurrencyElement.getText();
		softassert.assertEquals(ActualCurrency,theCurrencyCheck);	
	}
	
	public void checkTheContactNumFunction(String ContactNum) {
		
		String EcpectedContactNumber=ContactNum;
		
		WebElement ContactElement= driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualContactNum=ContactElement.getText();
		softassert.assertEquals(ActualContactNum, EcpectedContactNumber);
	}
	
	public void CheckLogoFunction(WebElement TheLogo) {
		WebElement expectedCheckToLogo=TheLogo;
		boolean isLogoDisplayed= expectedCheckToLogo.isDisplayed();
		boolean expectedLogoIsDisplayed=true;
		
		softassert.assertEquals(isLogoDisplayed, expectedLogoIsDisplayed);
		
	}
}
