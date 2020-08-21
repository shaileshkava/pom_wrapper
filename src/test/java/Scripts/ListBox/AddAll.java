package Scripts.ListBox;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Managers.PageObjectManager;
import Pages.ListBox;
import common.Ashot_Screenshot;
import common.BaseClass;
import common.Custom_Exception;
import common.ProjException;

public class AddAll extends BaseClass{
  @Parameters({"TestCaseDescription"})
  @Test
  public void AddList(String testName) throws IOException {
	  
	  try{
		  String screenshot;
		  extent = reports.startTest(testName);
		  
		  String url = prop.getProperty("url");
		  getDriver().get(url);
		  
		  extent.log(LogStatus.PASS, "URL is open succesfully", "");
		  
		  PageObjectManager pm = new PageObjectManager(getDriver());
		  
		  if(bVisible(pm.getInboxPage().closePopup, getDriver(), 3))
				pm.getInboxPage().closePopup.click();
		  
		  extent.log(LogStatus.PASS, "Clicking on ListBox", "");
		  pm.getListBoxPage().lnkListBox.click();
		  
		  if(!bVisible(pm.getListBoxPage().lnkJQueryListBox, getDriver(), 5))
			  throw new ProjException("JQuery list link is not present###jQueryMissing");
	      
		  extent.log(LogStatus.PASS, "JQuery link is available to click","");
		  
		  pm.getListBoxPage().lnkJQueryListBox.click();
		  
		  if(!bVisible(pm.getListBoxPage().btnAddAll, getDriver(), 5))
			  throw new ProjException("Add All button is missing###addAllMissing");
		
		  extent.log(LogStatus.PASS, "Clicking on Add all to verify func.","");
		 
		  pm.getListBoxPage().btnAddAll.click();
		  
		  if(!bVisible(pm.getListBoxPage().listRemovedItems.get(1), getDriver(), 5))
			  throw new ProjException("Remove All button is missing###removeAllMissing");
		  
		  extent.log(LogStatus.PASS, "Clicking on Remove all to verify func.","");
		  
		  if(pm.getListBoxPage().listAvailableItems.size()>0)
			  throw new ProjException("List not added succesfully###removeAllMissing");
		  
		  screenshot = "addedAllItems";
		  extent.log(LogStatus.PASS, "List added succesfully",extent.addScreenCapture(pathScreenshot+screenshot+screenshotExt) );
		  
		  extent.log(LogStatus.PASS, pm.getListBoxPage().lstGetPickListItems("right").toString());
		  
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