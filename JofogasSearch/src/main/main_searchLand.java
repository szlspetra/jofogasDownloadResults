package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import commons.*;
import pages.*;

public class main_searchLand {
	
	SetupChromeDriver setChroDri;
	WebDriver driver;
	BrowserFunctions broFun;
	ListView listView;
	ElementFunctions eleFun;
	List<String> strAdURL;
	String strURLBase = "https://ingatlan.jofogas.hu/";
	String strCities = "pest/biatorbagy+budakalasz+budaors+diosd+herceghalom+pilisborosjeno+pilisszentivan+pilisvorosvar+pomaz+solymar+tarnok+torokbalint+urom/";
	String strCities_Bp = "budapest/i-kerulet+ii-kerulet+iii-kerulet+iv-kerulet+xi-kerulet+xii-kerulet+xiii-kerulet+xiv-kerulet+xxii-kerulet/";
	String strType_Land = "telek-fold?";
	String strPrice_land = "max_price=25000000&min_price=5000000";	
	
	String arrData[];
	
	@BeforeTest
	public void setPage() {

		setChroDri = new SetupChromeDriver();
		driver = setChroDri.getDriver();
		broFun = new BrowserFunctions(driver);
		eleFun = new ElementFunctions(driver);
		//Go to page 
		broFun.goToPage(strURLBase+strCities+strType_Land+strPrice_land);
		//Accept Cookies
		listView = new ListView(driver);
		listView.clickToCookieButton();
		System.out.println("setPage OK");
	}
	
	@Test (priority = 0)
	public void saveURLs() {
		strAdURL = new ArrayList<String>();
		while(listView.isNextPageButtonExists()) {
			strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
			listView.clickNextPage();
		}
		System.out.println("saveURLs OK");
		
	}@Test (priority = 1)
	public void setBpAds() {
		broFun.goToPage(strURLBase+strCities_Bp+strType_Land+strPrice_land);
		System.out.println(strURLBase+strCities_Bp+strType_Land+strPrice_land);

		strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
		while(listView.isNextPageButtonExists()) {
			strAdURL.addAll(eleFun.getAttributeValues(listView.getListElements(), "href"));
			listView.clickNextPage();
		}
		System.out.println("setBpAds OK");
	}
		
	@Test (priority = 2)
	public void openAds() {
		DetailedPage dp = new DetailedPage(driver);
		arrData = new String[]{"Price","City","Drainage","Electicity","Water","Gas","Land size","Build in","Description","URL"};
		Excel e = new Excel();
		String strPath = "JofogasSearch\\output\\land_pest.xlsx";
		
		//Set header in Excel
		try {
			e.writeExcel(strPath, "withUtilities", arrData);
			e.writeExcel(strPath, "other", arrData);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for (int i=0; i<strAdURL.size();i++) {
			//Go to ads URL
			System.out.println(strAdURL.get(i));
			broFun.goToPage(strAdURL.get(i));
			arrData = new String[]{dp.getPrice(),dp.getCity(),dp.getDrainage(),dp.getElecticity(),dp.getWater(),dp.getGas(),dp.getLandSize(),dp.getBuildIn(),dp.getDescription(),strAdURL.get(i)};	
			if((dp.getDrainage().equals("van") || dp.getDrainage().equals("utcában") || dp.getDrainage().equals("telken belül")) &&
				(dp.getElecticity().equals("van") || dp.getElecticity().equals("utcában") || dp.getElecticity().equals("telken belül"))	&&
				(dp.getWater().equals("van") || dp.getWater().equals("utcában") || dp.getWater().equals("telken belül"))) {
				try {
					e.writeExcel(strPath, "withUtilities", arrData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					e.writeExcel(strPath, "other", arrData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		System.out.println("openAds OK");
	}
	
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}