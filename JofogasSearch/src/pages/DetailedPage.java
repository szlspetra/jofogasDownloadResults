package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.*;

public class DetailedPage {
	String strPrice = "//*[@class=\"price-value\"]";
	String strSize = "//*[@class=\"size\"]";
	String strCity = "//*[contains(@itemprop, 'address')]//*[contains(@class, 'Value')]";
	String strCategory = "//*[contains(@class, 'Category')]//*[contains(@class, 'Value')]";
	String strCondition = "//*[contains(@class, 'realestate_condition')]//*[contains(@class, 'Value')]";
	String strHeating = "//*[contains(@class, 'heating')]//*[contains(@class, 'Value')]";
	String strBuilding = "//*[contains(@class, 'building_type')]//*[contains(@class, 'Value')]";
	String strProprietary = "//*[contains(@class, 'proprietary')]//*[contains(@class, 'Value')]";
	String strGargen = "//*[contains(@class, 'garden_size')]//*[contains(@class, 'Value')]";
	String strDescription = "//*[contains(@id, 'linkifyDescription')]";
	String strPics = "//*[contains(@id, 'gallery_pic')]";
	
	WebDriver driver;

	public DetailedPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPrice() {
		try {
			return driver.findElement(By.xpath(strPrice)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getSize() {
		try {
			return driver.findElement(By.xpath(strSize)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getCity() {
		try {
			return driver.findElement(By.xpath(strCity)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getCategory() {
		try {
			return driver.findElement(By.xpath(strCategory)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getCondition() {
		try {
			return driver.findElement(By.xpath(strCondition)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getHeating() {
		try {
			return driver.findElement(By.xpath(strHeating)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getBuilding() {
		try {
			return driver.findElement(By.xpath(strBuilding)).getText();			
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getProprietary() {
		try {
			return driver.findElement(By.xpath(strProprietary)).getText();			
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getGarden() {
		try {
			return driver.findElement(By.xpath(strGargen)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getDescription() {
		try {
			return driver.findElement(By.xpath(strDescription)).getText();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public List getPicsUrl() {
		ElementFunctions ef = new ElementFunctions(driver);
		return ef.getAttributeValues(strPics, "data-gallery-url");
	}
	
}
