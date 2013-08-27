package rtbLib;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

//import rtb.lib.*;

import org.ddsteps.spring.DDStepsSpringTestCase;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.onefbusa.afddo.UserDataInputDDO;
import com.onefbusa.afsvcshared.AFService;

/**
 * This class is parent class of test cases to do some common operation of test
 * cases
 * 
 * @author eden.liu
 * 
 */
public abstract class OnefbBizServiceBaseTestCase {

	public AFService afService;
	public UserDataInputDDO userDataInputDDO;

	public void setScenarioName(String rtbCommand, String scenario) {
		WebConversation wc = new WebConversation();
		try {
			WebResponse wr = wc.getResponse(rtbCommand + scenario);
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		} catch (SAXException e) {

		}

	}

	// public AFService getAfService() {
	// return afService;
	// }
	// public void setAfService(AFService afService) {
	// this.afService = afService;
	// }
	// public UserDataInputDDO getUserDataInputDDO() {
	// return userDataInputDDO;
	// }
	// public void setUserDataInputDDO(UserDataInputDDO userDataInputDDO) {
	// this.userDataInputDDO = userDataInputDDO;
	// }

}
