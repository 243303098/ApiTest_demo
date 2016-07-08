package boc.api.ass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static XSSFSheet ExcelSheet;
	public static XSSFWorkbook ExcelBook;
	public static XSSFRow Row;
	public static XSSFCell Cell;

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheet(SheetName);
	}
	
	public static Integer getLastRowNum(String Path, String SheetName) throws IOException{
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelBook = new XSSFWorkbook(ExcelFile);
		ExcelSheet = ExcelBook.getSheetAt(0);
		return ExcelSheet.getLastRowNum();
	}

	public static void setCellData(String Result, int RowNum, int ColNum, String Path) throws Exception {
		Row = ExcelSheet.getRow(RowNum);
		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if (Cell == null) {
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(Result);
		} else {
			Cell.setCellValue(Result);
		}
		FileOutputStream fileOut = new FileOutputStream(Path);
		ExcelBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public static String getCellDate(int RowNum, int CloNum) {
		Cell = ExcelSheet.getRow(RowNum).getCell(CloNum);
		Cell.setCellType(Cell.CELL_TYPE_STRING);
		String cellData = Cell.getStringCellValue();
		return cellData;
	}
}