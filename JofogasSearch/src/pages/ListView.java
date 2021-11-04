package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListView {
	WebDriver driver;
	String strCookieButton = "//*[@id=\"CybotCookiebotDialogBodyButtonAccept\"]";
	String strListElements = "//*[@class=\"list-items\"]//*[@class=\"item-title\"]//a";
	String nextPageButton_exist = "//*[@class=\"ad-list-pager-item ad-list-pager-item-next active-item js_hist_li js_hist jofogasicon-right\"]";
	
	public ListView(WebDriver driver) {
		this.driver = driver;
	}
	
	/** 
	* Click the cookie button
	*/
	public void clickToCookieButton () {
		try {
			driver.findElement(By.xpath(strCookieButton)).click();
			
		} catch (Exception e) {
			return;
		}
	}
	
	
	
	public String getListElements () {
		return strListElements;	
	}
	
	public void clickNextPage () {
		driver.findElement(By.xpath(nextPageButton_exist)).click();
	}
	
	public boolean isNextPageButtonExists() {
		try {
			driver.findElement(By.xpath(nextPageButton_exist));
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}


}
