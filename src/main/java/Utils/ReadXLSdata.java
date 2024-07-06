package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSdata {
	
    public String getData(String colHeader) throws EncryptedDocumentException, IOException  {
    
    	String filePath = "src/main/java/nandha/resources/testdata.xlsx";
//    	/SeleniumFramework/src/main/java/nandha/resources/testdata.xlsx
    	String cellValue = "";
    	String sheetName= "";
    	String TcId="";
        // Use try-with-resources to ensure resources are closed after use
        FileInputStream fis = new FileInputStream(new File(filePath));
//        Workbook workbook = WorkbookFactory.create(fis);
        XSSFWorkbook wb = new XSSFWorkbook(fis); // Use HSSFWorkbook for XLS files
    	
    	 try {

     	    Sheet sh = wb.getSheet("Testcase");
     	    for (int i = 1; i <= sh.getLastRowNum(); i++) {
     	    	 Row row1 = sh.getRow(i);
     	    	 Cell col1 = row1.getCell(2);
     	    	 
     	    	 if(col1.toString().equalsIgnoreCase("Yes")) {
     	    		sheetName = row1.getCell(0).toString();
     	    		TcId = row1.getCell(1).toString();
     	    	 }
     	    }
     	    
     	    cellValue = getcell(wb,sheetName,TcId,colHeader);

     	} catch (Exception e) {
     	    e.printStackTrace();
     	}
    	 return cellValue;
}
    
    private String getcell(Workbook book,String sheet,String Tc,String col) throws EncryptedDocumentException, IOException {

    	String cellVal ="";
       try {

    	    Sheet sh = book.getSheet(sheet);

    	    int rowNum = 0;
    	    int colNum = 0;
//    	    get row Number
    	    for (int i = 0; i <= sh.getLastRowNum(); i++) {
    	    	Row row = sh.getRow(i);
    	    	Cell col1 = row.getCell(0);
    	    	if(col1.toString().equalsIgnoreCase(Tc)) {
    	    		rowNum = i;
    	    		break;
    	    		}
    	    }
    	    
//    	    Get Column Number
    	    Row row1 = sh.getRow(0);
    	    for (int i = 0; i <= row1.getLastCellNum(); i++) {
    	    	Cell col1 = row1.getCell(i);
    	    	if(col1.toString().equalsIgnoreCase(col)) {
    	    		colNum = i;
    	    		break;
    	    		}
    	    }
    	    
//    	    get Cell value
    	    	Row r  = sh.getRow(rowNum);
    	    	Cell value = r.getCell(colNum);
    	    	cellVal = value.toString();
  
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
       
       return cellVal;
       }
        
}