package Scripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import common.Ashot_Screenshot;
import common.BaseClass;

public class Test2 extends BaseClass{
	static Logger log = Logger.getLogger(Test2.class);
  
  @Test
  public void Login_Fail() throws IOException{
	  //WebDriver driverNew = DriverManager.getWebDriver();
	  System.out.println("ClassName "+this.getClass().getName());
	  extent = reports.startTest("Login_Fail");
	  extent.log(LogStatus.FAIL, extent.addScreenCapture("C:/Users/shakava/Downloads/download.jfif"));
	  log.info("From Test2");
	  driver.get("https://www.google.co.in/");
	  
	  new Ashot_Screenshot().captureScreenshot("test_2", driver);
	  driver.quit();
  }
}
