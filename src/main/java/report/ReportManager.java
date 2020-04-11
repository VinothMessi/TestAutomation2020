package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import exception.MyException;
import util.Util;

public class ReportManager {

	private static ExtentReports testReport;

	private ReportManager() {
	}

	public static ExtentReports getInstance(String reportPath, String reportName) throws MyException {
		if (testReport == null) {
			createInstance(reportPath, reportName);
		}
		return testReport;
	}

	private static synchronized ExtentReports createInstance(String reportDirectory, String reportName)
			throws MyException {
		Util.verify(reportDirectory, reportName, "html");
		StringBuilder path = new StringBuilder().append(reportDirectory).append("//").append(reportName);

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path.toString());
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("AutomationTesting2020");
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setReportName("Automation Execution");

		testReport = new ExtentReports();
		testReport.setSystemInfo("OS", "Windows 10");
		testReport.setSystemInfo("Automation Framework", "Test Automaation in 2020");
		testReport.attachReporter(sparkReporter);

		return testReport;
	}

}