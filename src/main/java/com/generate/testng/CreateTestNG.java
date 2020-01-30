package com.generate.testng;

public class CreateTestNG {
	public static void main(String a[]){
		XLSReader2 suite = new XLSReader2("Data/TestCases.xlsx");
		//suite.getTests("select count(*) from Sheet2 WHERE Active='Y'");
		suite.getTests("SELECT * from Sheet2 WHERE Parallel!='' LIMIT 1");
		suite.getTests("select * from Sheet2 WHERE Active='Y'", "TestNG_Regression.xml");
		suite.getTests("select * from Sheet2 WHERE Active='Y' AND Smoke='Y'", "TestNG_Smoke.xml");
	}
}