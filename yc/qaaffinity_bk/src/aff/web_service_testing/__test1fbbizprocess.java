package aff.web_service_testing;

import java.util.Properties;

import utilityLib.__ApplicationProperties;
import utilityLib.Tools;

import com.onefbusa.afddo.StringDDO;
import com.onefbusa.afddo.UserDataDDO;
import com.onefbusa.afddo.UserDataInputDDO;
import com.onefbusa.afsvcshared.AFService;
import com.onefbusa.afwebservice.AFWebService;

public class __test1fbbizprocess {
	static String LOCAL_HOST = "http://192.168.1.167:8004";
	static String STG_HOST = "http://s363apv3.1fbusa.com:8004";
	static String QA_HOST = "http://qsfoapv2.1fbusa.com:8004";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Object[][] retObjArr = Tools.getTableArray("resource/xls/QA_Minimum_Payment_Due_Test.xls", "test_Step_1_4");
		
		AFWebService afWebService = new AFWebService();
		afWebService.setWebServiceUrl(LOCAL_HOST);
		AFService service = afWebService;
		//service.getValidValueSets(new StringDDO());
		AfServiceDdo afServiceDdo =  new AfServiceDdo();
		afServiceDdo.setAccountNumber("5407703047906208");
		//afServiceDdo.setAccountNumber("5407703047016032");
		//afServiceDdo.setAccountNumber("4017240716936500");
		
		UserDataInputDDO userDataInputDDO =  afServiceDdo.getUserDataInputDDO();

		
		UserDataDDO userDataDDO  = service.getUserData(userDataInputDDO);
		System.out.println(userDataDDO.getAcctNum() + userDataDDO.getCurrentMPDDesc());
		System.out.println("done");
	}

}
