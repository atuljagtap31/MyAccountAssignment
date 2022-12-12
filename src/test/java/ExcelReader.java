import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
	private Workbook wb;
	public ExcelReader(String fileNameWithLocation) {
		try {
			FileInputStream inputFile = new FileInputStream(fileNameWithLocation);
			if (fileNameWithLocation.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(inputFile);
			} else if (fileNameWithLocation.endsWith(".xls")) {
				wb = new HSSFWorkbook(inputFile);
			} else
				System.out.println("Invalid File Format...Kindly use .xls/.xlsx");
		} catch (Exception E) {
			System.out.println("Error with File Reading");
			System.out.println("Make sure it is .xls/.xlsx " + E.getMessage());
		}
	}
	public String getCellData(String sheetName, int row, int col) {
		String data="";
		try{
		data = wb.getSheet(sheetName).getRow(row).getCell(col).toString();}
		catch (NullPointerException e){
			System.out.println("Reading Empty data from Table");
		}
		return data;
	}
}
