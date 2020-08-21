package common;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

/**
 * This class contains methods which are common.
 * @author shakava
 *
 */
public class ReusableMethods {
	Fillo fillo;
	Connection connection;
	Recordset records;
	/** This method will remove single file or all files listed in given directory
	 * 
	 * @param type = [dir | file]
	 * @param path = [Directory or complete filepath]
	 */
	public void removeSingleFileOrAllFilesInDirectory(String type, String path){
		
		  try{
			  File file = new File(path);
			  
			  if(type.equalsIgnoreCase("dir")){
				  File[] f = file.listFiles();
					
				  for(int i=0; i<f.length; i++){
					System.out.println(f[i].getName());  
					f[i].delete();
					Thread.sleep(1000);
					System.out.println("Deleted");
				  }
			  }else{
				  file.delete();
			  }
			  
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	  }
	
	/**This method is used to get current date and time and given format as a string
	 * 
	 * @param sFormat in string
	 * @return
	 */
	public String getCurrentDateNTime(String sFormat){
		String sDateFormat = null;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(sFormat);
		LocalDateTime now = LocalDateTime.now();
		
		//System.out.println(dtf.format(now).toString());
		sDateFormat = dtf.format(now).toString();
		return sDateFormat;
	}
	
	/**This method is used to highlight given web element
	 * 
	 * @param we
	 * @param driver
	 */
	public void highlightWebElement(WebElement we, WebDriver driver){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", we);
	}
	
	public FluentWait<WebDriver> fluentWaitObj(WebDriver driver){
		FluentWait<WebDriver> fWait = new FluentWait<>(driver)
				.withTimeout(Duration.of(50, ChronoUnit.SECONDS))
				.pollingEvery(Duration.of(5, ChronoUnit.MILLIS))
				.ignoring(Exception.class);
		
		System.out.println("Returning fluent wait object");
		
		return fWait;
	}
	
	
	/**This method is used to wait until webelement is visible by specified seconds
	 * 
	 * @param we (Webelement name)
	 * @param secToWait (Seconds to wait)
	 * @return - boolen
	 */
	
	public boolean bVisible(WebElement we, WebDriver driver, int secToWait){
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean bStatus = true;
		
		try{
			FluentWait<WebDriver> fWait = fluentWaitObj(driver);
			
			/*
			 * FluentWait<WebDriver> fWait = new FluentWait<>(driver)
			 * .withTimeout(Duration.of(50, ChronoUnit.SECONDS))
			 * .pollingEvery(Duration.of(5, ChronoUnit.MILLIS)) .ignoring(Exception.class);
			 */
					
			fWait.until(ExpectedConditions.visibilityOf(we));
			
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, secToWait);
			 * wait.until(ExpectedConditions.visibilityOf(we));
			 */
		}catch(Exception e){
			bStatus = false;
		}
		
		return bStatus;
	}
	
	/**This method is used to wait until web element is clickable
	 * 
	 * @param we [Name of Web Element]
	 * @param driver
	 * @param secToWait
	 * @return - boolean
	 */
			
	public boolean bClickable(WebElement we, WebDriver driver, int secToWait){
		boolean bStatus = true;
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, secToWait);
			wait.until(ExpectedConditions.elementToBeClickable(we));
		}catch(Exception e){
			bStatus = false;
		}
		
		return bStatus;
	}
	
	public Recordset getExcelFromSQL(String sSQL) throws FilloException {
		fillo = new Fillo();
		connection = fillo.getConnection(BaseClass.filePath);
		System.out.println(connection);
		String sSQLQuery = sSQL;
		records = connection.executeQuery(sSQLQuery);
		
		return records;
	}
	
	public void closeRecordsetExcel() {
		records.close();
		connection.close();
	}
	
	public String getOSName() {
		//String osName = null;
		
		return System.getProperty("os.name");
	}
}