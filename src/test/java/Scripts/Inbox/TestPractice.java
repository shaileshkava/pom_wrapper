package Scripts.Inbox;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import common.ReusableMethods;

public class TestPractice {

	public static void main(String[] args) throws FilloException {
		ReusableMethods rm = new ReusableMethods();
		Recordset rs = rm.getExcelFromSQL("SELECT * FROM message");
		
		while(rs.next()) {
			System.out.println(rs.getField("Tetst_Message"));
		}
		
		rm.closeRecordsetExcel();
	}

}
