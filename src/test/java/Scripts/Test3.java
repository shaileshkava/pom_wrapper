package Scripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import common.Ashot_Screenshot;
import common.BaseClass;

public class Test3 extends BaseClass{
	static Logger log = Logger.getLogger(Test3.class);
  @Test
  public void f() throws IOException{
	  log.error("This is from Test3 Class");
	  extent = reports.startTest("Login_Skip");
	  extent.log(LogStatus.SKIP, "This is Skipped test case");
	  driver.get("https://app.testunity.com/");
	  new Ashot_Screenshot().captureScreenshot("test_33", driver);
	  driver.quit();
  }
}
