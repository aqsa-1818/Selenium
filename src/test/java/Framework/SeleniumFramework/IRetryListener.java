package Framework.SeleniumFramework;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryListener implements IRetryAnalyzer {
	
	//This class help to rerun failed testcase

	int count= 0;
	int maxTry=1;
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



//===============================================
//Suite
//Total tests run: 6, Passes: 4, Failures: 1, Skips: 0, Retries: 1
//===============================================

