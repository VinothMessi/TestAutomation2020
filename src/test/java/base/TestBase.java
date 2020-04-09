package base;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import browser.BrowserFactory;
import exception.MyException;
import pages.BasePage;
import properties.HoldConfigProperties;
import util.Util;

public class TestBase {

	public HoldConfigProperties config;
	public BrowserFactory bFactory;
	public BasePage basePage;

	String browserType;
	String driverFilePath;
	String browserName;
	String snapShotRootPath;

	protected String snapShotFolder;

	protected WebDriver browser;

	@BeforeSuite
	public void suiteSetUp() throws MyException {
		config = HoldConfigProperties.getInstance();

		browserType = config.properties.get("browserType");
		driverFilePath = config.properties.get("driverFilesPath");
		browserName = config.properties.get("browser");
		snapShotRootPath = config.properties.get("snapShotRootPath");

		snapShotFolder = Util.createDirectory(snapShotRootPath, "snapShots");
	}

	@BeforeClass
	public void beforeEachTestClass() {
		bFactory = BrowserFactory.getInstance();
	}

	@BeforeMethod
	public void beforeEachTestCase() throws MalformedURLException, MyException {
		browser = bFactory.getDriver(browserType, driverFilePath).launch(browserName);

		basePage = new BasePage(browser);
		basePage.maximize();
		basePage.waitImplicitlyFor(20, "seconds");
		basePage.waitTillPageLoadsFor(20, "seconds");

	}

	@AfterMethod
	public void afterEachTestCase() {
		browser.close();
	}

}