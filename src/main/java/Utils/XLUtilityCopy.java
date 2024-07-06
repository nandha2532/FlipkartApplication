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

public class XLUtilityCopy {

String cellValue="";
public String getData(String sheetName,String TC,String Header) throws IOException{
	//fileInputStream argument
	
	FileInputStream fis=new FileInputStream("src/main/java/nandha/resources/testdata.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(fis);

	int sheets=workbook.getNumberOfSheets();
	
	

	for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				XSSFSheet sheet=workbook.getSheetAt(i);
				//Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
				Row firstrow= rows.next();
				Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
				int k=0, n=0;
				int column = 0,colHeader = 0;

				while(ce.hasNext())
				{
					Cell value=ce.next();

					if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
					column=k;
					}
					k++;
					if(value.getStringCellValue().equalsIgnoreCase(Header))
					{
						colHeader=n;
					}
					n++;
				}
				
				while(rows.hasNext())
				{

				Row r=rows.next();

				if(r.getCell(column).getStringCellValue().equalsIgnoreCase(TC))
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
			}	

		}
	workbook.close();
	return cellValue;
}

//public void getcellNum() {
//	XSSFSheet sheetTC = workbook.getSheet("RunManager");
//	Iterator<Row>  rowTC= sheetTC.iterator();// sheet is collection of rows
//	Row row1= rowTC.next();
//	Iterator<Cell> cel =row1.cellIterator();//row is collection of cells
//	int a=0, b=0;
//	int sheetNum = 0,TcNum = 0;
//
//	while(cel.hasNext())
//	{
//		Cell value=cel.next();
//
//		if(value.getStringCellValue().equalsIgnoreCase("sheetName"))
//		{
//			sheetNum=a;
//		}
//		a++;
//		if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
//		{
//			TcNum=b;
//		}
//		b++;
//	}
//	
//	
//	


//public static void main(String[] args) throws IOException {
//// TODO Auto-generated method stub
//
//}
}	

