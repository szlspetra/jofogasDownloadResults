package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetupChromeDriver {
	String strCurrDir = System.getProperty("user.dir");
	WebDriver driver;
	
	public SetupChromeDriver() {
	
		System.setProperty("webdriver.chrome.driver", strCurrDir+"\\drivers\\chromedriver.exe");
		//driver = new ChromeDriver(setHeadlessMode());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public ChromeOptions setHeadlessMode() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--headless");
		return co;
	}
}
