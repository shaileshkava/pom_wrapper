package Scripts.Inbox;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Pages.Inbox;
import common.Ashot_Screenshot;
import common.BaseClass;
import common.Custom_Exception;

public class VerifyEnteredMessage extends BaseClass {
	@Parameters({ "TestCaseDescription", "data" })
	@Test
	public void verifyMessage(String testName, String data) {
		try {

			extent = reports.startTest(testName);

			String url = prop.getProperty("url");
			getDriver().get(url);
			extent.log(LogStatus.PASS, "URL is open succesfully", "");
			Thread.sleep(1000);
			Inbox pdf = new Inbox(getDriver());

			if (!bVisible(pdf.userInput, getDriver(), 5)) {
				new Ashot_Screenshot().captureScreenshot("test", getDriver(), pdf.userInput);
				extent.log(LogStatus.FAIL, "Element not found", extent.addScreenCapture(pathScreenshot + "test.png"));
				new Custom_Exception("Element not found");
			}
			extent.log(LogStatus.PASS, "Input Message textbox is visible");

			String strEnteredMessage = pdf.strEnteredMessage.getText().trim();
			pdf.userInput.sendKeys(data);

			if (!bVisible(pdf.btnShotMessage, getDriver(), 5)) {
				extent.log(LogStatus.FAIL, "Button not found");
				new Custom_Exception("Button not found");
			}
			extent.log(LogStatus.PASS, "Clicking on button");
			pdf.btnShotMessage.click();
			extent.log(LogStatus.PASS, "Clicked on button");

			bVisible(pdf.strEnteredMessage, getDriver(), 5);

			new Ashot_Screenshot().captureScreenshot("test1", getDriver(), pdf.userInput);
			extent.log(LogStatus.PASS, "Messaged entered as expected",
					extent.addScreenCapture(pathScreenshot + "test1.png"));

			if (strEnteredMessage.equalsIgnoreCase(data)) {
				new Ashot_Screenshot().captureScreenshot("test1", getDriver(), pdf.userInput);
				extent.log(LogStatus.FAIL, "Message could not enter properly",
						extent.addScreenCapture(pathScreenshot + "test1.png"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			new Custom_Exception("Final Exception");
		} finally {
			getDriver().quit();
		}
	}
}