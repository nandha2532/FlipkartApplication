package nandha.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	public static ExtentReports getReportProject() {
//		ExtentReports,ExtentSparkReporter
		String path =System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentSparkReporter--> help to config the report
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Fipkart Web Automation Results");
		reporter.config().setDocumentTitle("Test Result");
		
//		ExtentReports extent = new ExtentReports();
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nandha");
		try {
			return extent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return extent;
	}

}
