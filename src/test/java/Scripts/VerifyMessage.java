package Scripts;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import common.Ashot_Screenshot;
import common.BaseClass;
import common.Custom_Exception;
import site.pages.classes.page_demo_first;

public class VerifyMessage extends BaseClass{
  
  @Test
  public void VerifyEnterMessage() throws Custom_Exception {
	  try{
		  
		  extent = reports.startTest("VerifyEnterMessage");
		  
		  String url = prop.getProperty("url");
		  System.out.println("URL "+url);
		  driver.get(url);
		  extent.log(LogStatus.PASS, "URL is open succesfully", "");
		  Thread.sleep(1000);
		  page_demo_first pdf = new page_demo_first();
		  
		  if(pdf.bVisible(pdf.userInput)){
			  pdf.highlightElement(pdf.userInput);
			  new Ashot_Screenshot().captureScreenshot("test", driver, pdf.userInput);
			  //extent.log(LogStatus.FAIL, "Element not found");
			  extent.log(LogStatus.FAIL, "Element not found", extent.addScreenCapture(pathScreenshot+"test.png"));
			  new Custom_Exception("Element not found");
		  }
		  extent.log(LogStatus.PASS, "Input Message is visible");
		  pdf.userInput.sendKeys("This is test message");
		  extent.log(LogStatus.PASS, "Message entered");
		  
		  if(!pdf.bVisible(pdf.btnShotMessage)){
			  extent.log(LogStatus.FAIL, "Button not found");
			  new Custom_Exception("Button not found");
		  }
		  extent.log(LogStatus.PASS, "Clicking on button");
		  pdf.btnShotMessage.click();
		  extent.log(LogStatus.PASS, "Clicked on button");
		  
		  Thread.sleep(5000);
	  }catch(Exception e){
		  e.printStackTrace();
		  throw new Custom_Exception("Final Exception");
	  }finally{
		  driver.quit();
	  }
  }
}
