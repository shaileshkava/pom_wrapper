package Scripts.Inbox;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.LogStatus;

import Managers.PageObjectManager;
import common.Ashot_Screenshot;
import common.BaseClass;
import common.ProjException;

public class VerifyEnteredMessage extends BaseClass {
	@Parameters({ "TestCaseDescription", "data" })
	@Test
	public void verifyMessage(String testName, @Optional String data) throws ProjException, IOException {
		try {
			System.out.println("Running test");
			extent = reports.startTest(testName,
					"<p><span style='color:blue;font-weight:bold'>OS: </span>"+getOSName()+
					"</p>\n<p><span style='color:blue;font-weight:bold'>Browser Name: </span>"+setBrowser+"</p>");
			
			/*
			 * ExcelRead er = new ExcelRead(filePath, "message"); Object obj[] =
			 * er.getDataForSelectedCols("Tetst_Message");
			 */
			
			//extent.log(LogStatus.INFO, "<strong>OS: </strong>"+getOSName()+" <strong>Browser Name: </strong>"+setBrowser);
			String url = prop.getProperty("url");
			getDriver().get(url);
			extent.log(LogStatus.PASS, "URL is open succesfully", "");
			Thread.sleep(1000);
			//Inbox pdf = new Inbox(getDriver());
			PageObjectManager pm = new PageObjectManager(getDriver());
			
			
			if(bVisible(pm.getInboxPage().closePopup, getDriver(), 3))
				pm.getInboxPage().closePopup.click();
			 
			
			if (!bVisible(pm.getInboxPage().userInput, getDriver(), 5)) {
				System.out.println("User input not found");
				//new Ashot_Screenshot().captureScreenshot("test", getDriver());
				//new Ashot_Screenshot().captureScreenshot("test", getDriver(), pm.getInboxPage().userInput);
				//extent.log(LogStatus.FAIL, "Element not found", extent.addScreenCapture(pathScreenshot + "test.jpg"));
				throw new ProjException("Element not found###test1");
				//throw new Custom_Exception("Element not found");
			}
			extent.log(LogStatus.PASS, "Input Message textbox is visible");
			
			Recordset rs = getExcelFromSQL("SELECT * FROM message");
			
			
			while(rs.next()) {
				String strMessage = rs.getField("Tetst_Message");
				pm.getInboxPage().userInput.sendKeys(strMessage);

				if (!bVisible(pm.getInboxPage().btnShotMessage, getDriver(), 5)) 
					throw new ProjException("Button not found###test1");
				
				extent.log(LogStatus.PASS, "Clicking on button");
				pm.getInboxPage().btnShotMessage.click();
				extent.log(LogStatus.PASS, "Clicked on button");

				if(!bVisible(pm.getInboxPage().strEnteredMessage, getDriver(), 5))
					throw new ProjException("On Submit button entered message is not appearing###test1");
				
				extent.log(LogStatus.PASS, "Entered message is appearing");
				
				String strEnteredMessage = pm.getInboxPage().strEnteredMessage.getText().trim();
				
				if(!strEnteredMessage.equalsIgnoreCase(strMessage)) 
					throw new ProjException("Entered message is not proper ["+strEnteredMessage+"]["+strMessage+"]###test1");
				
				extent.log(LogStatus.PASS, "Entered message is proper ["+strEnteredMessage+"]["+strMessage+"]");
				
				pm.getInboxPage().userInput.clear();
			}
			
		}catch(ProjException pe) {
			String strMsgAndFileName[] = pe.getMessage().split("###");
			System.out.println(getClass().getSimpleName() + ": " + strMsgAndFileName[0]);
			new Ashot_Screenshot().captureScreenshot(strMsgAndFileName[1], getDriver());
			extent.log(LogStatus.FAIL, strMsgAndFileName[0], extent.addScreenCapture(pathScreenshot + strMsgAndFileName[1]+".jpg"));
			assertTrue(false);
			/* pe.getMessage(); */
		}catch (Exception e) {
			e.printStackTrace(System.out);
			String className[] = this.getClass().getName().split(".");
			String clsName = className[className.length-1];
			new Ashot_Screenshot().captureScreenshot(clsName, getDriver());
			extent.log(LogStatus.FAIL, "Some exection generated "+e.getMessage(), extent.addScreenCapture(pathScreenshot + clsName+".jpg"));
		} finally {
			getDriver().quit();
		}
	}
}