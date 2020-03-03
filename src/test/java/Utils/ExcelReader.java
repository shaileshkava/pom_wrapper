package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static File file;
	FileInputStream fis;
	/**Constructor to load excel and create objects 'Workbook' and 'Excel'
	 * 
	 * @param filePath
	 * @param SheetName
	 * @throws IOException
	 */
	ExcelReader(String filePath, String SheetName) throws IOException{
		try{
			//file = new File(filePath);
			file = new File("./Data/TestData.xlsx");
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(SheetName);
		}catch(FileNotFoundException fne){
			fne.printStackTrace();
		}
	}
	/**This method will return last used row in excel
	 * 
	 * @return
	 */
	public int getRowCount(){
		return sheet.getLastRowNum();
	}
	
	public int getColCount(){
		return sheet.getRow(sheet.getFirstRowNum()).getPhysicalNumberOfCells();
	}
	
	public void getEntireSheetData() throws IOException{
		//From sheet iterate through row and column
		int totalRow = getRowCount();
		int totalCol = getColCount();
		XSSFRow row;
		XSSFCell cell = null;
		int firstUsedRow = sheet.getFirstRowNum();
		
		for(int i=firstUsedRow; i<=totalRow; i++){
			row = sheet.getRow(i);
			
			if(row != null){
				for(int j=row.getFirstCellNum(); j<=totalCol; j++){
					//cell = row.getCell(j);
					cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					//if(cell.getCellType() != Cell.CELL_TYPE_BLANK)
					System.out.print(cell.getRichStringCellValue() + " | ");
					//System.out.println("|");
				}
			}
				
			System.out.println("");
		}
		
	}
	
	/**This method is used to get column position from excel
	 * 
	 * @param colName
	 * @return
	 */
	public int getColNum(String colName){
		
		int searchedColNumber=0;
		int totalCol = getColCount();
		
		XSSFRow row;
		XSSFCell cell = null;
		
		int firstUsedRow = sheet.getFirstRowNum();
		//System.out.println("First used row = "+firstUsedRow+"==="+totalCol);
		row = sheet.getRow(firstUsedRow);
		for(int i=0; i<=totalCol; i++){
			//cell = row.getCell(i);
			cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			//System.out.println(cell.getRichStringCellValue());
			
			if(cell.getCellType() != Cell.CELL_TYPE_BLANK){
				if(cell.getRichStringCellValue().toString().toLowerCase().trim()
						.equalsIgnoreCase(colName.toLowerCase().trim()))
				{
					searchedColNumber = i;
					break;
				}
			}
		}
		System.out.println("Identified col number [ "+searchedColNumber+" ]");
		return searchedColNumber;
	}
	
	public void getDataForSelectedCol(String colName){
		int getValueForCol = getColNum(colName);
		int firstUsedRow = sheet.getFirstRowNum();
		int totalRowCount = getRowCount();
		
		System.out.println("[ "+firstUsedRow+" ] [ "+totalRowCount+" ]");
		
		XSSFRow row;
		XSSFCell cell = null;
		
		for(int i=firstUsedRow+1; i<=totalRowCount; i++){
			row = sheet.getRow(i);
			
			if(row!=null){
				cell = row.getCell(getValueForCol);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(cell.getCellType() != Cell.CELL_TYPE_BLANK)
					System.out.println(i+"==="+cell.getRichStringCellValue());
			}
		}
	}
	
	/**This method will return records of given column name in Object
	 * 
	 * @param colName
	 * @return data in object
	 */
	public Object[] getDataForSelectedCols(String colName){
		
		int getValueForCol = getColNum(colName);
		int firstUsedRow = sheet.getFirstRowNum();
		int totalRowCount = getRowCount();
		
		Object obj[] = new Object[totalRowCount-firstUsedRow];
		
		System.out.println("[ "+firstUsedRow+" ] [ "+totalRowCount+" ]");
		
		XSSFRow row;
		XSSFCell cell = null;
		
		for(int i=firstUsedRow+1; i<=totalRowCount; i++){
			row = sheet.getRow(i);
			
			if(row!=null){
				cell = row.getCell(getValueForCol);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(cell.getCellType() != Cell.CELL_TYPE_BLANK)
					obj[i-1] = cell.getRichStringCellValue();
					System.out.println(i+"==="+cell.getRichStringCellValue());
			}
		}
		
		return obj;
	}
}