package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL {

	private static String cellValue = "";
	static XSSFWorkbook workbook;
	static FileInputStream fis;

	public static void main(String[] args) throws IOException {

		String value = getData("testSearch", "searchdata");

		System.out.println("cell value is : " + value);
		System.out.println(getData("testSearch", "Filter"));
		System.out.println(getData("testSearch", "Max"));
	}

	public static String getData(String sheetName, String Header) throws IOException {
		// fileInputStream argument
		try {
			fis = new FileInputStream("src/main/java/nandha/resources/testdata.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
		}

		workbook = new XSSFWorkbook(fis);

		String TC = getCell("RunManager", "SheetName", "TestCases", sheetName);
		cellValue = getCell(sheetName, "TestCases", Header, TC);

		workbook.close();
		return cellValue;
	}

	private static String getCell(String SheetName, String row, String col, String comp) {
		XSSFSheet sheet = workbook.getSheet(SheetName);
		Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
		Row firstrow = rows.next();

		int column = cellNum(firstrow, row);
		int colHeader = cellNum(firstrow, col);

		while (rows.hasNext()) {
			System.out.println("Column : " + column);
			System.out.println("ColumnHeader : " + colHeader);
			Row r = rows.next();
			if (r.getCell(column).getStringCellValue().equalsIgnoreCase(comp)) {
				Cell c = r.getCell(colHeader);
				System.out.println("Cell value  : " + c);
				if (c.getCellType() == CellType.STRING) {
					cellValue = c.getStringCellValue();
				} else {
					cellValue = NumberToTextConverter.toText(c.getNumericCellValue());

				}
			}
			break;
		}

		return cellValue;

	}

	private static int cellNum(Row firstrow, String header) {
		Iterator<Cell> cel = firstrow.cellIterator();// row is collection of cells
		int k = 0;

		while (cel.hasNext()) {
			Cell value = cel.next();
			if (value.getStringCellValue().equalsIgnoreCase(header)) {
				return k;
			}
			k++;

		}
		return 0;
	}
	
	public static void setData(String sheetName,String col, String setValue) throws IOException {
		// fileInputStream argument
		try {
			fis = new FileInputStream("src/main/java/nandha/resources/testdata.xlsx");
		} catch (IOException e) {
			e.printStackTrace();

		}

		workbook = new XSSFWorkbook(fis);

		String TC = getCell("RunManager", "SheetName", "TestCases", sheetName);
		setCell(sheetName,"TestCases", col, TC, setValue);

		FileOutputStream fos = new FileOutputStream("src/main/java/nandha/resources/testdata.xlsx");
		workbook.write(fos);
		fos.close();
	}

	private static void setCell(String SheetName, String row,String col, String tC, String setVal) {

		XSSFSheet sheet = workbook.getSheet(SheetName);
		Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
		Row firstrow = rows.next();
		
		int column = cellNum(firstrow, row);
		int colHeader = cellNum(firstrow, col);
		if (colHeader == 0) {
		    // Create a new cell at the end of the last column in the first row
		    Row firstRow = sheet.getRow(0); // Assuming sheet is your Sheet object
		    int lastCellNum = firstRow.getLastCellNum();
		    Cell newCell = firstRow.createCell(lastCellNum);
		    newCell.setCellValue(col);// Set the value as needed
		    colHeader = firstRow.getLastCellNum();
		}
		

		while (rows.hasNext()) {

			Row r = rows.next();

			System.out.println("Set Column Num : "+ colHeader);
			if (r.getCell(column).getStringCellValue().equalsIgnoreCase(tC)) {
				Cell c = r.createCell(colHeader);
				c.setCellValue(setVal);
				break;
			}
		}

	}


	
	
}