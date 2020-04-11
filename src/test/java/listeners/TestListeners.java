package listeners;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.TestBase;
import exception.MyException;
import report.ReportManager;

public class TestListeners extends TestBase implements ITestListener {

	private static ExtentReports report;
	private static ThreadLocal<ExtentTest> testCase = new ThreadLocal<ExtentTest>();

	public void onStart(ITestContext context) {
		log.info("onStart -> Test Tag Name: " + context.getName());
		try {
			report = ReportManager.getInstance(testReportFolder, testReportName);
			ITestNGMethod methods[] = context.getAllTestMethods();
			for (ITestNGMethod method : methods) {
				log.info(method.getMethodName());
			}
		} catch (Exception e) {
			log.info("Error while executing onStart, full stack trace follows:", e);
		}
	}

	public void onFinish(ITestContext context) {
		try {
			log.info("onFinish -> Test Tag Name: " + context.getName());
			report.flush();
		} catch (Exception e) {
			log.error("Error while executing onFinish, full stack trace follows:", e);
		}
	}

	public void onTestStart(ITestResult result) {
		try {
			ExtentTest test = report.createTest(result.getInstanceName() + " :: " + result.getMethod().getMethodName());
			testCase.set(test);
		} catch (Exception e) {
			log.error("Error while executing onTestStart, full stack trace follows:", e);
		}
	}

	public void onTestSuccess(ITestResult result) {
		try {
			log.info("onTestSuccess -> Test Method Name: " + result.getName());
			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "Test Method " + methodName + " Successful" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			testCase.get().log(Status.PASS, m);
		} catch (Exception e) {
			log.error("Error while executing onTestSuccess, full stack trace follows:", e);
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			log.info("onTestFailure -> Test Method Name: " + result.getName());
			String methodName = result.getMethod().getMethodName();
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testCase.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=red>"
							+ "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>"
							+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

			String path = "";
			try {
				path = page.takeASnapAndSaveAs(snapShotFolder, methodName + ".png");
			} catch (IOException e1) {
				testCase.get().fail("Test Method Failed, cannot take screenshot");
				log.error("Test Method Failed, cannot take screenshot", e1);
			} catch (MyException e1) {
				testCase.get().fail("Test Method Failed, cannot take screenshot");
				log.error("Test Method Failed, cannot take screenshot", e1);
			}

			try {
				testCase.get().fail("<b>" + "<font color=red>" + "Screenshot of failure" + "</font>" + "</b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				testCase.get().fail("Test Method Failed, cannot attach screenshot");
				log.error("Test Method Failed, cannot attach screenshot", e);
			}

			String logText = "<b>" + "Test Method " + methodName + " Failed" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			testCase.get().log(Status.FAIL, m);
		} catch (Exception e) {
			log.error("Error while executing onTestFailure, full stack trace follows:", e);
		}
	}

	public void onTestSkipped(ITestResult result) {
		try {
			log.info("onTestSkipped -> Test Method Name: " + result.getName());
			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "Test Method " + methodName + " Skipped" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			testCase.get().log(Status.PASS, m);
		} catch (Exception e) {
			log.error("Error while executing onTestSkipped, full stack trace follows:", e);
		}
	}

}