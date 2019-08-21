package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockLibrary 
{
	WebDriver driver;
	String res;

	//appLaunch 
	public String  appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver", 
				"D:\\Vasu@7am\\StockAccounting\\CommonJarFiles\\chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.get(url);
		driver.manage().window().maximize();
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
		
			res="Pass";
		}else
		{
			
			res="Fail";
		}
		return res;
	}
	
	//appLogin
	
	public String appLogin(String username,String password)
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		//validation
		if (driver.findElement(By.id("logout")).isDisplayed()) 
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
		return res;	
		
	}
	//supplierCreation
	public String supplierCreation(String supplierName,String address,String city,String country,String contactPerson,String phNumber,String emailId,String mobileNumber,String notes) throws Exception
	{
		driver.findElement(By.xpath("//*[@id='mi_a_suppliers']")).click();
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		
		String exp_data=driver.findElement(By.xpath("//*[@id='x_Supplier_Number']")).getAttribute("value");
		
		driver.findElement(By.xpath("//*[@id='x_Supplier_Name']")).sendKeys(supplierName);
		driver.findElement(By.xpath("//*[@id='x_Address']")).sendKeys(address);
		driver.findElement(By.xpath("//*[@id='x_City']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id='x_Country']")).sendKeys(country);
		driver.findElement(By.xpath("//*[@id='x_Contact_Person']")).sendKeys(contactPerson);
		driver.findElement(By.xpath("//*[@id='x_Phone_Number']")).sendKeys(phNumber);
		driver.findElement(By.xpath("//*[@id='x__Email']")).sendKeys(emailId);
		driver.findElement(By.xpath("//*[@id='x_Mobile_Number']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//*[@id='x_Notes']")).sendKeys(notes);
		//action
		/*Actions action=new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();*/
		
		driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		//validation
		Thread.sleep(2000);
		if (driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).isDisplayed()) 
		{
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
			Thread.sleep(2000);
		
		}else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
			Thread.sleep(2000);
			
		}
		
		String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		
		if (exp_data.equals(act_data)) 
		{

			res="Pass";
		}else
		{
			
			res="Fail";
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
		return res;
	}
	
	//stock categories
	public String stockCategories(String cname) throws Exception
	{
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']"))).build().perform();
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']"))).click().build().perform();
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		driver.findElement(By.xpath("//*[@id='x_Category_Name']")).sendKeys(cname);
		driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		//validation
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(cname);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		Thread.sleep(2000);
		String act_data=driver.findElement(By.xpath("//*[@id='el1_a_stock_categories_Category_Name']")).getText();
		//System.out.println(act_data);
		if (cname.equals(act_data)) 
		{
			res="Pass";
		}else
		{
			
			res="Fail";
		}
		return res;
	}
	
	//appLogout
	
	public String appLogout()
	{
		driver.findElement(By.id("logout")).click();
		
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		//validation
				if(driver.findElement(By.id("username")).isDisplayed())
				{
				
					res="Pass";
				}else
				{
					
					res="Fail";
				}
				return res;
	}
	
	//appClose
	
	public void appClose()
	{
		driver.close();
	}
	
	
	
}
