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
		// log.info("onStart -> Test Tag Name: " + context.getName());
		try {
			report = ReportManager.getInstance(testReportFolder, testReportName);
			ITestNGMethod methods[] = context.getAllTestMethods();
			for (ITestNGMethod method : methods) {
				// log.info(method.getMethodName());
			}
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		// log.info("onFinish -> Test Tag Name: " + context.getName());
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		ExtentTest test = report.createTest(result.getInstanceName() + " :: " + result.getMethod().getMethodName());
		testCase.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// log.info("onTestSuccess -> Test Method Name: " + result.getName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Method " + methodName + " Successful" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testCase.get().log(Status.PASS, m);
	}

	public void onTestFailure(ITestResult result) {
		// log.info("onTestFailure -> Test Method Name: " + result.getName());
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
			e1.printStackTrace();
		} catch (MyException e1) {
			e1.printStackTrace();
		}

		try {
			testCase.get().fail("<b>" + "<font color=red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			testCase.get().fail("Test Method Failed, cannot attach screenshot");
		}

		String logText = "<b>" + "Test Method " + methodName + " Failed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		testCase.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		// log.info("onTestSkipped -> Test Method Name: " + result.getName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Method " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testCase.get().log(Status.PASS, m);
	}

}