package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL {
	
private static String cellValue="";
static XSSFWorkbook workbook;
static FileInputStream fis;
	public static void main(String[] args) throws IOException {

		String value = getData("testSearch","searchdata");
		
		System.out.println("cell value is : "+ value);
		System.out.println(getData("testSearch","Filter"));
		System.out.println(getData("testSearch","Max"));
		}


public static String getData(String sheetName,String Header) throws IOException{
	//fileInputStream argument
	try {
	fis=new FileInputStream("src/main/java/nandha/resources/testdata.xlsx");
	}catch (IOException e) {
        e.printStackTrace();

    }
		
	workbook=new XSSFWorkbook(fis);


				String TC = getCell("RunManager","SheetName","TestCases",sheetName);
				cellValue = getCell(sheetName,"TestCases",Header,TC);
				
	workbook.close();
	return cellValue;
}

private static String getCell(String SheetName,String row,String col,String comp) {

	XSSFSheet sheet = workbook.getSheet(SheetName);
	Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
	Row firstrow= rows.next();
	Iterator<Cell> cel=firstrow.cellIterator();//row is collection of cells
	int k=0, n=0;
	int column = 0,colHeader = 0;

	while(cel.hasNext())
	{
		Cell value=cel.next();

		if(value.getStringCellValue().equalsIgnoreCase(row))
		{
		column=k;
		}
		k++;
		if(value.getStringCellValue().equalsIgnoreCase(col))
		{
			colHeader=n;
		}
		n++;
	}
	
	while(rows.hasNext())
	{

	Row r=rows.next();

	if(r.getCell(column).getStringCellValue().equalsIgnoreCase(comp))
	{
		Cell c =r.getCell(colHeader);
		if(c.getCellType()==CellType.STRING)
		{
			cellValue=c.getStringCellValue();
		}
		else{
			cellValue=NumberToTextConverter.toText(c.getNumericCellValue());

			}
		}
	}
	
	return cellValue;
	
}	
}

