package common;

import org.testng.Assert;


public class Custom_Exception extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**This class is used to add custom exception message, it will send iListner status 
	 * as false to mark test case fail
	 *  
	 * @author shakava
	 *
	 */
	public Custom_Exception(String message) {
		super(message);
		Assert.assertTrue(false,message);
	}
	/**This class is used to add custom exception message, it will send iListner status 
	 * as false to mark test case fail
	 *  
	 * @author shakava
	 *
	 */
	public Custom_Exception(String message, Throwable cause){
		
		super(message, cause);
		Assert.assertTrue(false,message);
	}
}
