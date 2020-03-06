package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ListBox {
	
	public static WebDriver driver;
	
	public ListBox(WebDriver driver){
		ListBox.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

@FindBy(xpath="//a[contains(.,'List Box')]")
public WebElement lnkListBox;

@FindBy(xpath="//a[contains(.,'JQuery List Box')]")
public WebElement lnkJQueryListBox;

@FindBy(xpath="//button[@class='pAddAll btn btn-primary btn-sm']")
public WebElement btnAddAll;

@FindBy(xpath="//button[@class='pRemoveAll btn btn-primary btn-sm']")
public WebElement btnRemAll;

@FindBys(@FindBy(xpath="//select[@class='form-control pickListSelect pickData']/option"))
public List<WebElement> listAvailableItems;

@FindBys(@FindBy(xpath="//select[@class='form-control pickListSelect pickListResult']/option"))
public List<WebElement> listRemovedItems;

@FindBy(xpath="(//div[@class='col-sm-5'])[2]")
public WebElement removedListBox;

@FindBy(xpath="(//div[@class='col-sm-5'])[1]")
public WebElement availableListBox;

/**This method is used to get list of available and deleted items from list box
 * 
 * @param sideOfList [left | right]
 * @return
 * @author shakava
 */
public List<String> lstGetPickListItems(String sideOfList){
	List<String> list = new ArrayList<String>();
	
	if(sideOfList.equalsIgnoreCase("left")){
		for(WebElement we: listAvailableItems){
			list.add(we.getText());
		}
	}else if(sideOfList.equalsIgnoreCase("right")){
		for(WebElement we: listRemovedItems){
			list.add(we.getText());
		}
		
	}
	
	return list;
}

}
