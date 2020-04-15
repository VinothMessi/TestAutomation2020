package base;

import static constants.Constants.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import excel.ExcelUtility;

import browser.BrowserFactory;
import exception.MyException;
import pages.FlightConfirmationPage;
import pages.FlightDetailsPage;
import pages.HomePage;
import pages.RegistrationConfirmationPage;
import pages.RegistrationPage;
import pages.SelectFlightsPage;
import pages.SignOnPage;
import properties.HoldConfigProperties;
import report.ReportManager;
import snapshot.SnapShot;
import util.Util;

public class TestBase {

	public static HoldConfigProperties config;
	public BrowserFactory bFactory;
	public SnapShot snapShot;

	protected HomePage page;
	protected RegistrationPage registerPage;
	protected RegistrationConfirmationPage registerConfirmationPage;
	protected FlightDetailsPage flightDetailsPage;
	protected SelectFlightsPage selectFlights;
	protected FlightConfirmationPage flightConfirmationPage;
	protected SignOnPage signOnPage;

	public static String browserType;
	public static String driverFilePath;
	public static String browserName;
	public static String snapShotRootPath;
	public static String testReportRootPath;
	public static String excelRootPath;

	public static String appURL;
	public static String hubURL;
	public static String snapShotFolder;
	public static String testReportFolder;
	public static String testReportName;
	public static String excelName;
	public static String excelSheetName;

	// public static WebDriver browser;
	protected WebDriver driver;
	public ThreadLocal<WebDriver> browser = new ThreadLocal<WebDriver>();

	/* <---------- SnapShot Instance ---------> */
	public static ThreadLocal<SnapShot> snap = new ThreadLocal<SnapShot>();

	protected static ExcelUtility excelUtility;

	/* <---------- Log4j Instance ---------> */
	public static Logger log;

	public static ExtentReports report;

	@BeforeSuite
	public void suiteSetUp() throws MyException, IOException, InterruptedException {

		// Runtime.getRuntime().exec("cmd /c start start_Grid.bat");
		// Thread.sleep(10000);

		config = HoldConfigProperties.getInstance();

		/* <---------- Initializing Log4j ---------> */
		log = LogManager.getLogger("AutomationTesting2020");

		browserType = config.properties.get("browserType");
		driverFilePath = config.properties.get("driverFilesPath");
		browserName = config.properties.get("browser");
		appURL = config.properties.get("appURL");
		hubURL = config.properties.get("hubURL");

		excelRootPath = config.properties.get("excelRootPath");
		excelName = config.properties.get("excelName");
		excelSheetName = config.properties.get("excelSheetName");

		// excelUtility = new ExcelUtility(
		// "C:\\Automation_Testing_2020\\myProjects\\roughWork\\src\\test\\resources\\testdata\\",
		// "testdata.xlsx");

		snapShotRootPath = PROJECT_PATH + config.properties.get("snapShotRootPath");
		snapShotFolder = Util.createDirectory(snapShotRootPath, "snapShots");

		testReportRootPath = config.properties.get("testReportRootPath");
		testReportName = config.properties.get("testReportName");
		testReportFolder = Util.createDirectory(testReportRootPath, "report");

		report = ReportManager.getInstance(testReportFolder, testReportName);
	}

	@BeforeTest
	@Parameters({ "noOfPassengers", "expectedPrice" })
	public void beforeEachTestClass(String noOfPassengers, String expectedPrice)
			throws MalformedURLException, MyException {

		bFactory = BrowserFactory.getInstance();

		// driver = bFactory.getDriver(browserType, hubURL).launch(browserName);
		driver = bFactory.getDriver(browserType, driverFilePath).launch(browserName);
		browser.set(driver);
		
		snapShot = new SnapShot(snapShotFolder,browser.get());
		snap.set(snapShot);

		page = new HomePage(browser.get());
		page.maximize();
		page.waitImplicitlyFor(WAIT_TIME);
		page.waitTillPageLoadsFor(WAIT_TIME);
	}

	@AfterTest
	public void afterEachTestCase() {
		browser.get().quit();
	}

	@AfterSuite
	public void tearDown() throws IOException, InterruptedException {
		// Runtime.getRuntime().exec("cmd /c start stop_Grid.bat");
		// Thread.sleep(5000);
		// Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

}