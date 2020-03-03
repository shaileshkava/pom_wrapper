package com.generate.testng;

import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class CreateTestNG {
	public static void main(String a[]){
		XLSReader2 suite = new XLSReader2("Data/TestCases.xlsx");
		//suite.getTests("select count(*) from Sheet2 WHERE Active='Y'");
		suite.getTests("SELECT * from Sheet2 WHERE Parallel!='' LIMIT 1");
		suite.getTests("select * from Sheet2 WHERE Active='Y'", "TestNG_Regression.xml");
		suite.getTests("select * from Sheet2 WHERE Active='Y' AND Smoke='Y'", "TestNG_Smoke.xml");
		
		
		/*TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add("TestNG_Regression.xml");//path to xml..
	    testng.setTestSuites(suites);
	    testng.run();*/
	}
}