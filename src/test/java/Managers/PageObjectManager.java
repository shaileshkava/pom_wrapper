package Managers;

import org.openqa.selenium.WebDriver;

import Pages.Inbox;
import Pages.ListBox;

public class PageObjectManager {
	
	private WebDriver wdDriver;
	
	private Inbox inboxPage;
	private ListBox listBoxPage;
	
	
	public PageObjectManager(WebDriver driver) {
		this.wdDriver = driver;
	}
	
	public Inbox getInboxPage() {
		if(inboxPage == null)
			inboxPage = new Inbox(wdDriver);
		
		return inboxPage;
	}

	public ListBox getListBoxPage() {
		if(listBoxPage == null)
			listBoxPage = new ListBox(wdDriver);
			
		return listBoxPage;
	}
}