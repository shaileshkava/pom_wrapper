package Scripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import common.Ashot_Screenshot;
import common.BaseClass;

public class Test1 extends BaseClass{
	static Logger log = Logger.getLogger(Test1.class);
	
  @Test
  public void Login() throws Exception{
	  extent = reports.startTest("Login");
	  extent.log(LogStatus.PASS, "This is pass step");
	  //WebDriver driverNew = DriverManager.getWebDriver();
	  log.debug("This message is from Test Class1");
	  
	  //new Custom_Exception("This is custom error message");
	  
	  System.out.println("After custom error message");
	  log.info("Property Value = "+prop.getProperty("url"));
	  driver.get("http://www.yahoo.com");
	  new Ashot_Screenshot().captureScreenshot("test_1", driver);
	  driver.quit();
  }
}