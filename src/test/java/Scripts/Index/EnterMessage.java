package Scripts.Index;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Page.page_demo_first;
import common.Ashot_Screenshot;
import common.BaseClass;
import common.Custom_Exception;

public class EnterMessage extends BaseClass{
  
  static Logger log = Logger.getLogger(EnterMessage.class);
  
  @Parameters({"TestCaseDescription"})	
  @Test
  public void VerifyEnterMessage(String testName) throws Custom_Exception {
	  try{
		  
		  extent = reports.startTest(testName);
		  
		  String url = prop.getProperty("url");
		  getDriver().get(url);
		  extent.log(LogStatus.PASS, "URL is open succesfully", "");
		  Thread.sleep(1000);
		  page_demo_first pdf = new page_demo_first(getDriver());
		  
		  if(!bClickable(pdf.userInput, getDriver(), 5)){
			  new Ashot_Screenshot().captureScreenshot("test", getDriver(), pdf.userInput);
			  extent.log(LogStatus.FAIL, "Element not found", extent.addScreenCapture(pathScreenshot+"test.png"));
			  new Custom_Exception("Element not found");
		  }
		  extent.log(LogStatus.PASS, "Input Message text box is visible");
		  pdf.userInput.sendKeys("This is test message");
		  extent.log(LogStatus.PASS, "Message entered");
		  
		  if(!bClickable(pdf.btnShotMessage, getDriver(), 5)){
			  extent.log(LogStatus.FAIL, "Button not found");
			  new Custom_Exception("Button not found");
		  }
		  extent.log(LogStatus.PASS, "Clicking on button");
		  pdf.btnShotMessage.click();
		  extent.log(LogStatus.PASS, "Clicked on button");
		  
		  pdf.bVisible(pdf.strEnteredMessage,5);
		 
	  }catch(Exception e){
		  e.printStackTrace();
		  new Custom_Exception("Final Exception");
	  }finally{
		  getDriver().quit();
	  }
  }
}