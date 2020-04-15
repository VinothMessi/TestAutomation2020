package dataprovider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.TestBase;
import excel.ExcelUtility;
import exception.MyException;
import excel.ExcelReader;
//import exception.MyException;

public class DataProviderUtility extends TestBase {

	public ThreadLocal<ExcelUtility> excel = new ThreadLocal<>();

	@DataProvider(name = "Book-Flight-Module")
	public Object[][] bookFlight(Method m) throws MyException {
		excel.set(excelUtility);
		Object[][] testDataArray = null;
		excel.get().setExcelFile("Book-Flight-Module");
		testDataArray = excel.get().getTestData(m.getName());
		return testDataArray;
	}

}