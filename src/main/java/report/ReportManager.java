package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import exception.MyException;
import util.Util;

import static constants.Constants.*;

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
		Util.verify(reportDirectory, reportName, REPORT_EXT);
		StringBuilder path = new StringBuilder().append(reportDirectory).append("//").append(reportName);

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path.toString());
		sparkReporter.config().setTheme(REPORT_THEME);
		sparkReporter.config().setDocumentTitle(DOC_NAME);
		sparkReporter.config().setEncoding(REPORT_ENCODING);
		sparkReporter.config().setReportName(REPORT_NAME);

		testReport = new ExtentReports();
		testReport.setSystemInfo("OS", OS);
		testReport.setSystemInfo("Selenium Version", SELENIUM_VERSION);
		testReport.attachReporter(sparkReporter);

		return testReport;
	}

}