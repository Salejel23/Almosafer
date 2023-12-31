package Almosafer;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
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

import net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class TestCases extends parametersClass {
	
	@BeforeTest
	public void Setup() {
	theStartToWebsite();
	}
	
//	@Test(description = "this is a happy scenario",priority = 1)
//	public void CheckLanguageDefultIsEnglish() {
//		checkTheLanguageFunction("en");
//		softassert.assertAll();
//	
//	}
//	
//	@Test(description = "this is a sad scenario",priority = 2)
//		public void CheckLanguageDefultIsArabic() {
//		checkTheLanguageFunction("ar");
//		softassert.assertAll();
//	}
	
//	@Test(description ="this is to check the currency SAR",priority = 3 )
//	public void ChekTheCurrency() {
//		
//		checkTheCurrencyFunction("SAR");
//			softassert.assertAll();
//	}
	
//	@Test(enabled=true)
//	public void CheckTheContactNumber() {
//		
//		checkTheContactNumFunction("+966554400000");
//		softassert.assertAll();
//	}
	
//	@Test()
//	public void CheckLogoApple () {
//		CheckLogoFunction(driver.findElement(By.xpath("//img[@alt='apple-pay']")));
//		softassert.assertAll();
//	}
	
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
	
	@Test(enabled = false )
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
		
		WebElement InputVistor = driver.findElement(By.className("tln3e3-1"));
		Select select = new Select(InputVistor);
		int randomIndexVistor = rand.nextInt(2);
		select.selectByIndex(randomIndexVistor);
		
		WebElement SearchButton = driver.findElement(By.className("sc-1vkdpp9-6"));
		SearchButton.click();

		Thread.sleep(25000);

		String HotelSearchResult = driver.findElement(By.className("sc-cClmTo")).getText();

		if (driver.getCurrentUrl().contains("ar")) {
			boolean ActualResult = HotelSearchResult.contains("وجدنا");
			boolean ExpectedResult = true;
			Assert.assertEquals(ActualResult, ExpectedResult);

			WebElement Lowestpric = driver.findElement(By.className("kgqEve"));
			Lowestpric.click();

		} else {
			boolean ActualResult = HotelSearchResult.contains("found");
			boolean ExpectedResult = true;
			Assert.assertEquals(ActualResult, ExpectedResult);

			WebElement Lowestprice = driver.findElement(By.className("eSXwxY"));
			Lowestprice.click();
		}

		Thread.sleep(6000);

		WebElement PriceSection = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> Prices = PriceSection.findElements(By.className("Price__Value"));


			int LowesPrice = Integer.parseInt(Prices.get(0).getText());
			int highestPrice = Integer.parseInt(Prices.get(Prices.size() - 1).getText());
			
			System.out.println(LowesPrice+"This is the lowest price");
			System.out.println(highestPrice+"This is the highest price");
			Assert.assertEquals(highestPrice > LowesPrice, true);

	}
	
	@AfterTest
	public void endTest() {
	
	}
}
