package roughwork;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import browser.BrowserFactory;
import exception.MyException;

public class SampleTest {
	
	@Test
	public void TC() throws MalformedURLException, MyException {
		BrowserFactory bFactory = BrowserFactory.getInstance();
		bFactory.getDriver("Local", "C:\\Automation_Testing_2020\\myProjects\\AutomationTesting2020\\resources\\drivers\\").launch("Chrome");
	}

}