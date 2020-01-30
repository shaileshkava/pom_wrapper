package site.pages.classes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.BaseClass;

public class page_demo_first {
	
	public static WebDriver driver;
	
	public page_demo_first(){
		page_demo_first.driver = BaseClass.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-message")
	public WebElement userInput;
	
	@FindBy(xpath="//button[contains(text(),'Show Message')]")
	public WebElement  btnShotMessage;
	
	
	/**This method is used to wait until webelement is visible by specified seconds
	 * 
	 * @param we (Webelement name)
	 * @param secToWait (Seconds to wait)
	 * @return - boolen
	 */
	public boolean bVisible(WebElement we, int secToWait){
		boolean bVisibility=true;
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, secToWait);
			wait.until(ExpectedConditions.visibilityOf(we));
		}catch(Exception e){
			bVisibility = false;
		}
		
		return bVisibility;
	}
	public boolean bVisible(WebElement we){
		return bVisible(we, 5);
	}
	
	/**This method is used to wait until webelement is clickable by specified seconds
	 * 
	 * @param we (Webelement name)
	 * @param secToWait (Seconds to wait)
	 * @return - boolen
	 */
	public boolean bClickable(WebElement we, int secToWait){
		boolean bIsClickable = true;
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, secToWait);
			wait.until(ExpectedConditions.elementToBeClickable(we));
		}catch(Exception e){
			bIsClickable=false;
		}
		
		return bIsClickable;
	}
	
	public void highlightElement(WebElement we){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", we);
	}
}
