package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary 
{

	WebDriver driver;
	//startBrowser
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("FireFox"))
		{
			driver= new FirefoxDriver();
		}else
			if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", 
						"D:\\OJTVasu\\StockMaven\\ExecutableFiles\\chromedriver.exe");
				driver=new ChromeDriver();
			}else
				if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE"))
				{
					System.setProperty("webdriver.ie.driver", 
							"D:\\OJTVasu\\StockMaven\\ExecutableFiles\\IEDriverServer.exe");
					driver=new InternetExplorerDriver();
				}
		return driver;
	}
	//openApplication
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.manage().window().maximize();
		driver.get(PropertyFileUtil.getValueForKey("URL"));
	}
	
	//clickAction
	
	public static void clickAction(WebDriver driver,String locatorType,String loctorValue)
	{
		
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(loctorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(loctorValue)).click();
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(loctorValue)).click();
				}
	}
	//typeAction
	
	public static void typeAction(WebDriver driver,String locatorType,String loctorValue,String data)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(loctorValue)).clear();
			driver.findElement(By.id(loctorValue)).sendKeys(data);
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(loctorValue)).clear();
				driver.findElement(By.name(loctorValue)).sendKeys(data);
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(loctorValue)).clear();
					driver.findElement(By.xpath(loctorValue)).sendKeys(data);
				}
	}
	//closeBrowser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waittime)
	{
		WebDriverWait myWait=new WebDriverWait(driver, Integer.parseInt(waittime));
		
		if (locatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				}
	}
	
	public static void mouseAction(WebDriver driver)
	{
		Actions action=new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
	{
		String data="";
		if(locatorType.equalsIgnoreCase("id"))
		{
			data=driver.findElement(By.id(locatorValue)).getAttribute("value");
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				data=driver.findElement(By.name(locatorValue)).getAttribute("value");
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
				}
		
		FileWriter fw=new FileWriter("D:\\OJTVasu\\StockMaven\\CaptureData\\Data.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	
	public static void tableValidation(WebDriver driver,String colNum) throws Throwable
	{
		FileReader fr=new FileReader("D:\\OJTVasu\\StockMaven\\CaptureData\\Data.txt");
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		
		int colnum1=Integer.parseInt(colNum);
		Thread.sleep(2000);
		if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).isDisplayed()) 
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
			Thread.sleep(2000);
		
		}else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
			Thread.sleep(2000);
		}
		
		WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable")));
		
		List<WebElement> rows=webtable.findElements(By.tagName("tr"));
		
		for (int i =1; i<=rows.size(); i++) 
		{
			String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]"+"/td["+colnum1+"]/div/span/span")).getText();
			System.out.println(act_data);
			Assert.assertEquals(exp_data,act_data);
			break;
		}
	}
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}
	//sample method
	public void sample()
	{
		System.out.println("VasuDeva");
	}
}
