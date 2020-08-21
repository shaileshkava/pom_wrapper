package Scripts.Inbox;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Managers.PageObjectManager;
import common.Ashot_Screenshot;
import common.BaseClass;
import common.Custom_Exception;
import common.ProjException;

public class EnterMessage extends BaseClass{
  
  static Logger log = Logger.getLogger(EnterMessage.class);
  
  @Parameters({"TestCaseDescription"})	
  @Test
  public void VerifyEnterMessage(String testName) throws Custom_Exception, IOException {
	  try{
		  String sOSName = getOSName();
		  String sBrowserName = setBrowser;
		  extent = reports.startTest(testName);
		  extent.log(LogStatus.PASS, "<B>OS Name</B>:"+sOSName);
		  String url = prop.getProperty("url");
		  getDriver().get(url);
		  extent.log(LogStatus.PASS, "URL is open succesfully", "");
		  PageObjectManager pm = new PageObjectManager(getDriver());
		  
		  if(bVisible(pm.getInboxPage().closePopup, getDriver(), 3))
				pm.getInboxPage().closePopup.click();
		  
		  if(!bVisible(pm.getInboxPage().userInput, getDriver(), 5))
			  throw new  ProjException("User input element not found###EnterMessage.png");
		  
		  extent.log(LogStatus.PASS, "Input Message text box is visible");
		  pm.getInboxPage().userInput.sendKeys("This is test message");
		  extent.log(LogStatus.PASS, "Message entered");
		  
		  if(!bClickable(pm.getInboxPage().btnShotMessage, getDriver(), 5))
			  throw new  ProjException("Show message button not found###EnterMessage.png");
		  
		  extent.log(LogStatus.PASS, "Clicking on button");
		  pm.getInboxPage().btnShotMessage.click();
		  extent.log(LogStatus.PASS, "Clicked on button");
		  
		  if(!bVisible(pm.getInboxPage().strEnteredMessage, getDriver(), 5))
			  throw new  ProjException("Message is not appearing below test box###EnterMessage.png");
		  
		 extent.log(LogStatus.PASS, "Entered message is appearing");
		 
	  }catch(ProjException pe) {
			String strMsgAndFileName[] = pe.getMessage().split("###");
			System.out.println(getClass().getSimpleName() + ": " + strMsgAndFileName[0]);
			new Ashot_Screenshot().captureScreenshot(strMsgAndFileName[1], getDriver());
			
			extent.log(LogStatus.FAIL, strMsgAndFileName[0], extent.addScreenCapture(pathScreenshot + strMsgAndFileName[1]+".jpg"));
			assertTrue(false);
			/* pe.getMessage(); */
		}catch (Exception e) {
			e.printStackTrace(System.out);
			String className = this.getClass().getName();
			extent.log(LogStatus.FAIL, "Some exection generated "+e.getMessage(), extent.addScreenCapture(pathScreenshot + className+".jpg"));
		} finally {
			getDriver().quit();
		}
	}
}