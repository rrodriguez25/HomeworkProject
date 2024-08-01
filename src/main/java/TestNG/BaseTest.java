package TestNG;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ConfigVariables.ConfigProperties;

public class BaseTest {

	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public ConfigProperties config = new ConfigProperties();
	//public static ConfigProperties testConfig;
	
	@BeforeSuite
	public void initialiseExtentReports()
	{
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("CSVComparisonReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}
	
	@BeforeTest
	public void setup(ITestContext context) throws IOException
	{
		extentTest = extentReports.createTest(context.getName());
		//testConfig = new ConfigProperties();
	}
	
	@BeforeClass
	public void beforeClass() throws IOException
	{
		
	}
	
	@AfterMethod
	public void checkStatus(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest.fail(result.getThrowable());
		} 
		else if (result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.pass("PASS");
		}
	}
	
	
	@AfterSuite
	public void generateExtentReports() throws IOException
	{
		extentReports.flush();
		Desktop.getDesktop().browse(new File("CSVComparisonReport.html").toURI());
	}
}
