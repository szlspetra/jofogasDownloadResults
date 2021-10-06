package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetupChromeDriver {
	String strCurrDir = System.getProperty("user.dir");
	WebDriver driver;
	
	public SetupChromeDriver() {
	
		System.setProperty("webdriver.chrome.driver", strCurrDir+"\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
