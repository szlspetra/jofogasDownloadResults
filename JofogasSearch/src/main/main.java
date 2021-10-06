package main;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import commons.*;
import pages.*;

public class main {
	
	SetupChromeDriver setChroDri;
	WebDriver driver;
	BrowserFunctions broFun;
	ListView listView;
	String strURLBase = "https://ingatlan.jofogas.hu/";
	String strCities = "pest/biatorbagy+budakalasz+budakeszi+budaors+diosd+erd-parkvaros+nagykovacsi+paty+pilisborosjeno+solymar+soskut+tarnok+torokbalint+urom/";
	String strTipus = "haz?";
	String strMaxPrice = "max_price=50000000";		
			
	@BeforeTest
	public void setPage() {

		setChroDri = new SetupChromeDriver();
		driver = setChroDri.getDriver();
		broFun = new BrowserFunctions(driver);
		//Go to page (concate url)
		broFun.goToPage(strURLBase+strCities+strTipus+strMaxPrice);
		//Accept Cookies
		listView = new ListView(driver);
		listView.clickToCookieButton();
	}
	
	@Test
	public void main() {
		
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}