package bizprocesses;

import static myconstant.BizWork.*;
import frameLib.FrameUtilities;
import com.onefbusa.afsvcshared.AFService;
import com.onefbusa.afwebservice.AFWebService;

public class Onefbbizprocess {
	private static String webServiceUrl = "http://" + BW_SERVER_NAME + ":" + ONEFBBIZPROCESS_PORT;
	
	private static AFWebService afWebService = new AFWebService();
	static {		
		afWebService.setWebServiceUrl(webServiceUrl);
	}
	
	public static AFService getAFService(){
		return (AFService)afWebService;	
	}	
	
	public static void setScenario(String scenario) {
		RtbUtil.setScenarioName(ONEFBBIZPROCESS_PORT, scenario);
	}
}
