package nandha.TestComponents;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import Utils.ReadXLSdata;
import Utils.XLUtilityCopy;

public class excel {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
//	ReadXLSdata xl = new ReadXLSdata();
//	String data = xl.getData("searchdata");
//	System.out.println(data);
//	
//	System.out.println(xl.getData("Filter"));
//	System.out.println(xl.getData("Max"));
		
		
		XLUtilityCopy xl = new XLUtilityCopy();
		System.out.println(xl.getData("testSearch", "TC02", "searchdata"));
		System.out.println(xl.getData("testSearch", "TC02", "Filter"));
		System.out.println(xl.getData("testSearch", "TC02", "Max"));
		
		System.out.println(xl.getData("testSearch", "TC03", "searchdata"));
		System.out.println(xl.getData("testSearch", "TC03", "Filter"));
		System.out.println(xl.getData("testSearch", "TC03", "Max"));
	}
		

}
