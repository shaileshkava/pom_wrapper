package common;

import org.testng.Assert;

public class Custom_Exception extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public Custom_Exception(String message) {
		super(message);
		Assert.assertTrue(false,message);
	}
	
	public Custom_Exception(String message, Throwable cause){
		
		super(message, cause);
		Assert.assertTrue(false,message);
	}
}
