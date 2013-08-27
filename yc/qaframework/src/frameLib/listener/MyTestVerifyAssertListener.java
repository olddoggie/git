package frameLib.listener;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import frameLib.MyVerifyAssert;
import static frameLib.PrintTestCases.*;

// This class is to enable multiple verifies (vs assert) in a test case
// Now, test case will continue execution even one verify failed
// base class: TestListenAdapter>IInvokedMethodListener
public class MyTestVerifyAssertListener extends TestListenerAdapter2 {
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) 
	{
		if (method.isTestMethod()) {
			//print("<br>");
			//print("Test Case Name --" + method.getTestMethod().getMethodName());
			//@Test(priority = 15,description="Login Affinity")
			if(method.getTestMethod().getDescription()==null)
			{
				//print("<Description>");
			}
			else
			{
				print("<TC Description> " + method.getTestMethod().getDescription());
			}
			

		}
	}
	
	public String parseStringForHtml(String s){
		s= s.replaceAll("at\\s*[org,java,sun].*", "").replaceAll("(?m)^\\t*$[\n\r]{1,}", "");
		s = StringEscapeUtils.escapeHtml(s);
//		s = s.replaceAll("\n", "<br>");
//		//remove the last <br>
//		s = s.substring(0, s.lastIndexOf("<")-1);
		return s;
	}
	
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		System.out.println(method.getTestMethod().toString());
		Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			List<Throwable> verificationFailures = MyVerifyAssert.getVerificationFailures();

			//if there are verification failures...
			if (verificationFailures.size() > 0) {
				
				//set the test to failed
				result.setStatus(ITestResult.FAILURE);

				//if there is an assertion failure add it to verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());
				}
				
				int size = verificationFailures.size();
				//if there's only one failure just set that
				if (size == 1) {
					//result.setThrowable(verificationFailures.get(0));
					String tmp = Utils.stackTrace(verificationFailures.get(0), false)[1];
					result.setThrowable(new Throwable(parseStringForHtml(tmp)));					
				} else {
					//create a failure message with all failures and stack traces (except last failure)
					StringBuilder failureMessage = new StringBuilder("Multiple failures (").append(size).append("):\n");
					for (int i = 0; i < size; i++) {
						failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":\n");
						Throwable t = verificationFailures.get(i);
						String fullStackTrace = Utils.stackTrace(t, false)[1];
						failureMessage.append(fullStackTrace).append("");
					}					
					Throwable merged = new Throwable(parseStringForHtml(failureMessage.toString()));					
					result.setThrowable(merged);

				}
			}
		}
	}
	
}
