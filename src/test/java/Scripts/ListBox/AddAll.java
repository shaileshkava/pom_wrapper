package Scripts.ListBox;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Page.ListBox;
import common.Ashot_Screenshot;
import common.BaseClass;
import common.Custom_Exception;
import common.ReusableMethods;

public class AddAll extends BaseClass{
  @Parameters({"TestCaseDescription"})
  @Test
  public void AddList(String testName) {
	  
	  try{
		  String screenshot;
		  extent = reports.startTest(testName);
		  
		  String url = prop.getProperty("url");
		  getDriver().get(url);
		  
		  extent.log(LogStatus.PASS, "URL is open succesfully", "");
		  
		  ListBox lb = new ListBox(getDriver());
		  
		  extent.log(LogStatus.PASS, "Clicking on ListBox", "");
		  lb.lnkListBox.click();
		  
		  if(!bVisible(lb.lnkJQueryListBox, getDriver(), 5)){
			  screenshot = "jQueryMissing";
			  new Ashot_Screenshot().captureScreenshot(screenshot, getDriver(), lb.lnkJQueryListBox);
			  extent.log(LogStatus.FAIL, "JQuery list link is not present", extent.addScreenCapture(pathScreenshot+screenshot+screenshotExt));
			  
			  new Custom_Exception("WebElement not present");
		  }
		  extent.log(LogStatus.PASS, "JQuery link is available to click","");
		  
		  lb.lnkJQueryListBox.click();
		  
		  if(!bVisible(lb.btnAddAll, getDriver(), 5)){
			  screenshot = "addAll_Missing";
			  new Ashot_Screenshot().captureScreenshot(screenshot, getDriver(), lb.btnAddAll);
			  extent.log(LogStatus.FAIL, "Add All button is missing",extent.addScreenCapture(pathScreenshot+screenshot+screenshotExt));
			  
			  new Custom_Exception("Add all button is missing");
		  }
		  
		  extent.log(LogStatus.PASS, "Clicking on Add all to verify func.","");
		  lb.btnAddAll.click();
		  
		  bVisible(lb.listRemovedItems.get(1), getDriver(), 5);
		  
		  screenshot = "listAdding";
		  new Ashot_Screenshot().captureScreenshot(screenshot, getDriver(), lb.removedListBox);
		  if(lb.listAvailableItems.size()>00){
			  extent.log(LogStatus.FAIL, "List not added succesfully",extent.addScreenCapture(pathScreenshot+screenshot+screenshotExt) );
			  
			  new Custom_Exception("List not added");
		  }
		  
		  extent.log(LogStatus.PASS, "List added succesfully",extent.addScreenCapture(pathScreenshot+screenshot+screenshotExt) );
		  
		  extent.log(LogStatus.PASS, lb.lstGetPickListItems("right").toString());
		  
	  }catch(Exception e){
		  new Custom_Exception("Final Exception");
		  e.printStackTrace();
	  }finally{
		  getDriver().quit();
	  }
	}
}