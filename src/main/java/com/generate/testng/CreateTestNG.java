package com.generate.testng;

public class CreateTestNG {
	public static void main(String a[]){
		XLSReader2 suite = new XLSReader2("src/test/resources/TestCases/TestCases.xlsx");
		
		suite.getTests("SELECT * from Sheet2 WHERE Parallel!='' "
				+ "LIMIT 1");
		
		suite.getTests("select * from Sheet2 WHERE Active='Y' "
				+ "AND className!=''", "TestNG_Regression.xml");
		
		suite.getTests("select * from Sheet2 WHERE Active='Y' "
				+ "AND Smoke='Y' AND className!=''", "TestNG_Smoke.xml");
	}
}