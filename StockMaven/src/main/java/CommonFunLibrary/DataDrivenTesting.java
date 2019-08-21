package CommonFunLibrary;

import Utilities.ExcelFileUtil;

public class DataDrivenTesting
{

	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtil excel=new ExcelFileUtil();
		StockLibrary app=new StockLibrary();
		app.appLaunch("http://webapp.qedge.com/login.php");
		app.appLogin("admin", "master");
		
		for (int i = 1; i <=excel.rowCount("SupplierData"); i++) 
		{
			String supplierName=excel.getData("SupplierData", i, 0);
			String address=excel.getData("SupplierData", i, 1);
			String city=excel.getData("SupplierData", i, 2);
			String country=excel.getData("SupplierData", i, 3);
			String cPerson=excel.getData("SupplierData", i, 4);
			String pNumber=excel.getData("SupplierData", i, 5);
			String email=excel.getData("SupplierData", i, 6);
			String mNumber=excel.getData("SupplierData", i, 7);
			String note=excel.getData("SupplierData", i, 8);
			
			String results=app.supplierCreation(supplierName, address, city, country, cPerson, pNumber, email, mNumber, note);
			excel.setData("SupplierData", i, 9, results);
		}
		
		

	}

}
