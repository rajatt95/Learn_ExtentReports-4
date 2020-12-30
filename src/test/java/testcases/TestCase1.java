package testcases;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Customized Automation Extent Reports");

		htmlReporter.config().setReportName("Automation test results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		/* htmlReporter.config().setTheme(Theme.DARK); */

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation Engineer", "Rajat Verma");
		extent.setSystemInfo("Organization", "ATMECS Technologies");
		extent.setSystemInfo("Build no.", "ATMECS-1234");
	}

	@AfterTest
	public void endReport() {

		extent.flush();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		String methodName = result.getMethod().getMethodName();

		if (result.getStatus() == ITestResult.FAILURE) {

			String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			test.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
					+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
					+ " \n");
 
			/*
			 * try {
			 * 
			 * ExtentManager.captureScreenshot(); testReport.get().fail("<b>" +
			 * "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
			 * MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
			 * .build()); } catch (IOException e) {
			 * 
			 * }
			 */

			String failureLogg = "TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			test.log(Status.FAIL, m);

			/*
			 * String logText = "<b>" + "Test Case: " + methodName.toUpperCase() +
			 * "  -- FAILED" + "</b>"; Markup m = MarkupHelper.createLabel(logText,
			 * ExtentColor.RED); test.fail(m);
			 * 
			 */ } else if (result.getStatus() == ITestResult.SKIP) {
			String logText = "<b>" + "Test Case: " + methodName.toUpperCase() + "  -- SKIPPED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			test.skip(m);

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String logText = "<b>" + "Test Case: " + methodName.toUpperCase() + "  -- PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);

		}

	}

	@Test
	public void doUserReg() {
		test = extent.createTest("User Reg test");
		Assert.fail("User Reg test failed");
	}

	@Test
	public void isSkip() {
		test = extent.createTest("Skip test");
		throw new SkipException("Skipping the test case");
	}

	@Test
	public void doLogin() {
		test = extent.createTest("Login test");
		System.out.println("Executing Login test");
	}
}
