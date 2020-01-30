package common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Scripts.Test1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends ReusableMethods{
  public static ExtentReports reports;
  public ExtentTest extent;
  public static WebDriver driver;
  public static Properties prop;
  
  public final static String pathScreenshot = System.getProperty("user.dir")+File.separator+"Screenshot"+File.separator;
  public final static String pathProject = System.getProperty("user.dir");
  
  static Logger log = Logger.getLogger(Test1.class);
  
  /**This method is initialize driver based on given parameter name as browser [Chrome, FF, Edge, Safari]
   * 
   * @param driverName
   * @return
   */
  @Parameters({"browser"})
  @BeforeMethod
  public WebDriver initDriver(String driverName){
		System.out.println("Selected Browser = "+driverName);
	  
		if(driverName.toLowerCase().equalsIgnoreCase("firefox")){
			//System.out.println("firefox");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(driverName.toLowerCase().equalsIgnoreCase("chrome")){
			//System.out.println("chrome");
			WebDriverManager.chromedriver().setup();
			//System.out.println("initiate");
			driver = new ChromeDriver();
		}else if(driverName.toLowerCase().equalsIgnoreCase("ie") || driverName.toLowerCase().equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		//DriverManager.setWebDriver(driver);
		driver.manage().window().maximize();
		return driver;
		
	}
  
  
  /**Initialize extent report for specified file name
   * 
   * @param reportName [filename to log report]
   */
  @Parameters({"report_name"})
  @BeforeSuite
  public void setUp() {
	  String reportName = "Report-"+getCurrentDateNTime("yyyy-MM-dd-HH-mm-ss");
	  String reportFileName = reportName+".html";
	  reports = new ExtentReports("./ExecutionReport/"+reportFileName,true);
	  reports.loadConfig(new File("extent-config.xml"));
  }
  
  
  @BeforeSuite
  public void loadDefaultProperty() throws Exception {
		String filePath = "Properties/Execution_Property/run.properties";
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		prop = new Properties();
		prop.load(fis);
	}
  
  @BeforeSuite
  public void removeLogs(){
	  //removeSingleFileOrAllFilesInDirectory("dir", "./Logs");
	  //removeSingleFileOrAllFilesInDirectory("dir", "./ExecutionReport");
  }
  
  /**This method takes result after each method execution for Extent Report
   * 
   * @param result
   */
  @AfterMethod
  public void getResult(ITestResult result){
	  
	  if(result.getStatus() == ITestResult.FAILURE){
		  extent.log(LogStatus.FAIL, "Failed Test Case "+result.getThrowable());
		 //extent.log(LogStatus.FAIL, "Test case is failed ");
		  extent.log(LogStatus.FAIL, result.getName()+" Test case is filed");
	  }else if(result.getStatus() == ITestResult.SUCCESS){
		  extent.log(LogStatus.PASS, "Test case is Pass "+result.getName());
	  }else if(result.getStatus() == ITestResult.SKIP){
		  extent.log(LogStatus.SKIP, "Test case is Skipped "+result.getName());
	  }
  }
  
  @AfterSuite
  public void tearDown(){
	  //Removing log4j.log file from Log directory which has size of 0 byte
	  removeSingleFileOrAllFilesInDirectory("file", "./Logs/Log4j.log");
	  
	  //Flushing extent report after completing all test cases
	  reports.endTest(extent);
	  reports.flush();
  }
}
