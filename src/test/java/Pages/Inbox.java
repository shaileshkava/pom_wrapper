package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Inbox {
	
	public static WebDriver driver;
	
	public Inbox(WebDriver driver){
		Inbox.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-message")
	public WebElement userInput;
	
	@FindBy(xpath="//button[contains(text(),'Show Message')]")
	public WebElement  btnShotMessage;
	
	@FindBy(xpath="//span[@id='display']")
	public WebElement strEnteredMessage;
	
//Methods can be started from here
	
}
