package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

/**
 * This class contains methods which are common.
 * @author shakava
 *
 */
public class ReusableMethods {
	
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
		System.out.println(dtf.format(now).toString());
		sDateFormat = dtf.format(now).toString();
		return sDateFormat;
	}
}