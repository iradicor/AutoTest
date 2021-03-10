package com.luobo.cases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luobo.dataread.RangeDatabyPOI;

public class BrowserTest {

	WebDriver driver;
	
	@DataProvider(name="muke")
	public Object[][] getData() throws IOException {
		String filePath = "d:/luobo/test.xlsx";
		Object[][] array = RangeDatabyPOI.poiRangeData(filePath);
		return array;
	}
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforetest(String aa, String bb) {
		System.out.println("start---");
		if(aa.equals("Chrome")) {
			System.out.println("Chrome");
			driver = new ChromeDriver();
		}else if (aa.equals("IE")) {
			System.out.println("IE");
			driver = new InternetExplorerDriver(); 
		}else {
			System.out.println("error---");
		}
		
		driver.manage().window().maximize();
		driver.get(bb);
	}
	
	@Test(dataProvider = "muke")
	public void aTest(String s1) throws InterruptedException {
		driver.findElement(By.id("kw")).sendKeys(s1);
		Thread.sleep(2000);
		driver.findElement(By.id("su")).click();
		System.out.println("2000");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void quit() {
		driver.close();
	}
	
}
