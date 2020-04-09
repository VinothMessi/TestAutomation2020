package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import base.TestBase;
import exception.MyException;

public class SanityTest extends TestBase {
	
	@Test
	public void sanityTest() throws IOException, MyException {
		browser.get("http://www.google.com");
		basePage.takeASnapAndSaveAs(snapShotFolder, "test.png");
	}
	
}