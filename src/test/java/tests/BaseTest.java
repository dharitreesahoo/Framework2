package tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import browserConfiguration.ChromeBrowser;
import browserConfiguration.FirefoxBrowser;
import browserConfiguration.IExploreBrowser;
import config.BrowserType;
import config.ObjectReader;
import config.PropertyReader;
import helpers.ExcelHelper;
import helpers.JavaScriptHelper;
import helpers.LoggerHelper;
import helpers.ResourceHelper;
import util.ExtentManager;


public class BaseTest {
	public static ExtentReports extent;
	public static ExtentTest test;
	//public static ExtentTest Parenttest;
	public static ExtentTest Childtest;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(BaseTest.class);
	public static File reportDirectery;
	String lineSeparator = System.getProperty("line.separator");

	@BeforeSuite
	public void beforeSuite() throws Exception {
		extent = ExtentManager.getInstance();
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		setUpDriver(ObjectReader.reader.getBrowserType());
		reportDirectery = new File(ResourceHelper.getResourcePath("/resources/screenShots"));
		test = extent.createTest(getClass().getSimpleName());
		

	}

	@BeforeClass
	public void beforeClass() throws Exception {
		this.getApplicationUrl("https://www.seleniumeasy.com/test/basic-first-form-demo.html");

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		//test.log(Status.INFO, method.getName()+"**************test started***************");
		log.info("**************"+method.getName()+"Started*************** ");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			//test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(), driver);
			//test.addScreenCaptureFromPath(imagePath);
			//Childtest = test.createNode(result.getMethod().getMethodName()+" Results..Expand the see the detailed report");
			//Childtest = test.createNode(result.getMethod().getMethodName()+ MarkupHelper.createLabel("Status of TC", ExtentColor.INDIGO));
			//Childtest.log(Status.FAIL, MarkupHelper.createLabel("Tc failed", ExtentColor.RED));
			Childtest.addScreenCaptureFromPath(imagePath);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			//test.log(Status.PASS, result.getName()+" is pass");
			//Childtest = test.createNode(result.getMethod().getMethodName()+" Results..Expand the see the detailed report");
			//Childtest.log(Status.PASS, MarkupHelper.createLabel("User is able to Check check box", ExtentColor.BLUE));
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		log.info("**************" + result.getName() + "Finished*************** \n");
		extent.flush();
	}

	@AfterTest
	public void afterTest() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	public void setUpDriver(BrowserType btype) throws Exception {
		driver = getBrowserObject(btype);
		driver.manage().window().maximize();
	}

	public String captureScreen(String fileName, WebDriver driver) {
		if (driver == null) {
			log.info("driver is null..");
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectery + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen...");
		new JavaScriptHelper(driver).zoomInBy60Percentage();
		String screen = captureScreen("", driver);
		new JavaScriptHelper(driver).zoomInBy100Percentage();
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getBrowserObject(BrowserType btype) throws Exception {

		try {
			switch (btype) {
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);

			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
			default:
				throw new Exception("Driver not Found: " + btype.name());
			}
		} catch (Exception e) {
			// log.info(e.getMessage());
			throw e;
		}
	}

	public void getApplicationUrl(String url) {
		driver.get(url);
		// logExtentReport("navigating to ..." + url);
	}

	public static void logExtentReport(String s1) {
		Childtest.log(Status.INFO, s1);
	}

	/*public String[][] getExcelData(String excelName, String sheetName) {
		String excellocation = ResourceHelper.getResourcePath("/resources/testData/") + excelName;
		ExcelHelper readDataFromExcelSheet = new ExcelHelper();
		return readDataFromExcelSheet.getExcelData(excellocation, sheetName);
	}*/
}
