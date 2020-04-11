package base;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import browser.BrowserFactory;
import exception.MyException;
import pages.FlightDetailsPage;
import pages.HomePage;
import pages.RegistrationConfirmationPage;
import pages.RegistrationPage;
import properties.HoldConfigProperties;
import util.Util;

import static constants.Constants.*;

public class TestBase {

	public HoldConfigProperties config;
	public BrowserFactory bFactory;

	public HomePage page;
	public RegistrationPage registerPage;
	public RegistrationConfirmationPage registerConfirmationPage;
	public FlightDetailsPage flightDetailsPage;

	String browserType;
	String driverFilePath;
	String browserName;
	String snapShotRootPath;
	String testReportRootPath;
	
	protected String appURL;
	protected String snapShotFolder;
	protected static String testReportFolder;
	protected static String testReportName;

	protected WebDriver browser;
	
	/* <---------- Log4j Instance ---------> */
	public static Logger log;

	@BeforeSuite
	public void suiteSetUp() throws MyException {
		
		config = HoldConfigProperties.getInstance();
		
		/* <---------- Initializing Log4j ---------> */
		log = LogManager.getLogger("AutomationTesting2020");

		browserType = config.properties.get("browserType");
		driverFilePath = config.properties.get("driverFilesPath");
		browserName = config.properties.get("browser");
		appURL = config.properties.get("appURL");
		snapShotRootPath = config.properties.get("snapShotRootPath");
		testReportRootPath = config.properties.get("testReportRootPath");
		testReportName = config.properties.get("testReportName");

		snapShotFolder = Util.createDirectory(snapShotRootPath, "snapShots");
		testReportFolder = Util.createDirectory(testReportRootPath, "report");
	}

	@BeforeClass
	public void beforeEachTestClass() throws MalformedURLException, MyException {
		bFactory = BrowserFactory.getInstance();
		browser = bFactory.getDriver(browserType, driverFilePath).launch(browserName);

		page = new HomePage(browser);
		page.maximize();
		page.waitImplicitlyFor(WAIT_TIME);
		page.waitTillPageLoadsFor(WAIT_TIME);
	}

	@AfterClass
	public void afterEachTestCase() {
		browser.quit();
	}

}