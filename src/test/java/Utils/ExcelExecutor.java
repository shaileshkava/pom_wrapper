package Utils;

import java.io.IOException;

public class ExcelExecutor {
	static String filePath = "src/test/resources/TestData/TestData.xlsx";
	
	public static void main(String[] args) throws IOException {
		
		ExcelRead er = new ExcelRead(filePath, "Users");
		/*int colCount = er.getColCount();
		int rowCount = er.getRowCount();*/
		
		//er.getEntireSheetData();
		//er.getColNum("userid");
		//er.getDataForSelectedCol("userid");
		Object obj[] = er.getDataForSelectedCols("userid");
		
		System.out.println("Object Length = "+obj.length);
		for(int i=0; i<obj.length; i++){
			System.out.println(obj[i]);
		}
	}

}