package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class BaseClass extends ReusableMethods {
	
	public static String filePath = "src/test/resources/TestData/TestData.xlsx";
	private static String resourcePath="src/test/resources/";
	private static BaseClass instance = null;
	public static Properties property=null;
	private static String qaProperty = resourcePath+"Properties/qa.properties";
	private static String defaultProperty = resourcePath+"Properties/Default/exec.properties";
	private static String stagProperty = resourcePath+"Properties/stag.properties";
	private static String prodProperty = resourcePath+"Properties/prod.properties";
	public static ExtentReports reports;
	public ExtentTest extent;

	ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static Properties prop;

	public final static String pathScreenshot = System.getProperty("user.dir") + File.separator + "Screenshot"
			+ File.separator;
	public final static String screenshotExt = ".png";
	public final static String pathProject = System.getProperty("user.dir");
	
	public String strEnvironment=System.getProperty("env.ENVIRONMENT")==null?"QA":System.getProperty("env.ENVIRONMENT");
	
	static Logger log = Logger.getLogger(BaseClass.class);
	public String setBrowser;
	
	public static BaseClass getInstance() {
		if (instance == null) {
			instance = new BaseClass();
		}
		return instance;
	}
	
	/**
	 * This method is initialize driver based on given parameter name as browser
	 * [Chrome, FF, Edge]
	 * 
	 * @param driverName
	 * @return
	 * @author shakava
	 */
	@Parameters({"browser"})
	@BeforeMethod
	public final void setDriver(String browser) throws Exception{
		setBrowser = browser;
		switch(browser){
		
		case "chrome":
			WebDriverManager.chromedriver().setup();
			webDriver.set(new ChromeDriver());
		break;
		
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			webDriver.set(new FirefoxDriver());
		break;
		
		case "ie":
			WebDriverManager.edgedriver().setup();
			webDriver.set(new EdgeDriver());
		break;
		
		case "edge":
			/*EdgeOptions options = new EdgeOptions();
			//options.setProxy(proxy)
*/			System.setProperty("webdriver.edge.driver", resourcePath+"Drivers/MicrosoftWebDriver.exe");
			WebDriver driver = new EdgeDriver();
			//WebDriverManager.edgedriver().setup();
			webDriver.set(driver);
		break;
		
		case "safari":
			webDriver.set(new SafariDriver());
		
		}
	}
	
	public WebDriver getDriver() {
		webDriver.get().manage().window().maximize();
		return webDriver.get();
	}
	
	/**
	 * Initialize extent report for specified file name
	 * 
	 * @param reportName
	 *            [filename to log report]
	 */
	@Parameters({ "report_name" })
	@BeforeSuite
	public void setUp() {
		String reportName = "Report-" + getCurrentDateNTime("yyyy-MM-dd-HH-mm-ss");
		//String reportFileName = reportName + ".html";
		String reportFileName = "TestExecutionReport.html";
		reports = new ExtentReports("./ExecutionReport/" + reportFileName, true);
		reports.loadConfig(new File("extent-config.xml"));
	}
	
	@BeforeSuite
	public void createScreenshotFolder(){
		String screenshotDir = "Screenshot";
		String executionDir = "ExecutionReport";
		
		File dir = new File(screenshotDir);
		File ExecutionReport = new File(executionDir);
		
		if(!dir.exists()){
			log.info("Directory is not present");
			dir.mkdir();
		}else{
			log.info("Directory is already presented");
		}
		
		if(!ExecutionReport.exists()){
			log.info("Directory is not present");
			dir.mkdir();
		}else{
			log.info("Directory is already presented");
		}
	}
	
	/**Load property files relevant to given argument from Maven command
	 * @author shakava
	 */
	@BeforeSuite
	public void propertiesFileReader(){
		if(prop == null){			
			prop = new Properties();
			try {
				
				/*
				 * if(strEnvironment.equalsIgnoreCase("qa")){ prop.load(new
				 * FileInputStream(qaProperty)); }else
				 * if(strEnvironment.equalsIgnoreCase("stage")){ prop.load(new
				 * FileInputStream(stagProperty)); }else
				 * if(strEnvironment.equalsIgnoreCase("prod")){ prop.load(new
				 * FileInputStream(prodProperty)); }
				 */
				
				if(strEnvironment.equalsIgnoreCase("qa")) 
					FileUtils.copyFile(new File(qaProperty), new File(defaultProperty));
				if(strEnvironment.equalsIgnoreCase("stage"))
					FileUtils.copyFile(new File(stagProperty), new File(defaultProperty));
				if(strEnvironment.equalsIgnoreCase("prod"))
					FileUtils.copyFile(new File(prodProperty), new File(defaultProperty));
				
				prop.load(new FileInputStream(defaultProperty));
				
				System.out.println("user + pass ["+prop.getProperty("username")+"==="+prop.getProperty("password"));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method takes result after each method execution for Extent Report
	 * 
	 * @param result
	 */
	@AfterMethod
	public void getResult(ITestResult result) {
		System.out.println("TestCaseStatus == "+result.getStatus());
		if (result.getStatus() == ITestResult.FAILURE) {
			//extent.log(LogStatus.FAIL, "Failed Test Case " + result.getThrowable());
			extent.log(LogStatus.FAIL, result.getName() + " Test case is filed");
		} if (result.getStatus() == ITestResult.SUCCESS) {
			extent.log(LogStatus.PASS, "Test case is Pass " + result.getName());
		} if (result.getStatus() == ITestResult.SKIP) {
			extent.log(LogStatus.SKIP, "Test case is Skipped " + result.getName());
		}
	}
	
	@AfterSuite
	public void tearDown() {
		// Removing log4j.log file from Log directory which has size of 0 byte
		removeSingleFileOrAllFilesInDirectory("file", "./Logs/Log4j.log");

		// Flushing extent report after completing all test cases
		reports.endTest(extent);
		reports.flush();
	}
}
