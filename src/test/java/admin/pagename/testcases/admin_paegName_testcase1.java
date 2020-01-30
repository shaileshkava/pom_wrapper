package admin.pagename.testcases;

import common.Custom_Exception;

public class admin_paegName_testcase1 {
	public static void main(String args[]) throws Custom_Exception{
		try{
			int i=10;
			if(i!=9)
				throw new Custom_Exception("Testing purpose only");
			
			System.out.println("This is testing");
		}catch(Custom_Exception e){
			System.out.println("Custom error generated1");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
