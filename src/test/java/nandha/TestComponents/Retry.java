package nandha.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

//	If a test has failed and if it has to be rerun then
//	to tell the test that it has to reach this 
//	retryAnalyzer = Retry.class --> add this to the test 
	
	int count = 0;
	int maxTry = 1;
	
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry) {
			
			count++;
			return true;
		}
		
		return false;
	}

}
