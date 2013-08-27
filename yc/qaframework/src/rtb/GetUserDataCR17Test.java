//package onefbbizprocess_auto;
//
//import lib.UnKnownException;
//import lib.XmlUtil;
//
//import com.onefbusa.afddo.UserDataDDO;
//import com.onefbusa.afddo.UserDataInputDDO;
//import com.onefbusa.afsvcshared.ServiceException;
//
//public class GetUserDataCR17Test extends OnefbBizServiceBaseTestCase {
//	
//	private UserDataInputDDO  userDataInputDDO;
//	private String pricingStrategySeverityLevelDescriptionExpansion = "";
//
//	public UserDataInputDDO getUserDataInputDDO() {
//		return userDataInputDDO;
//	}
//
//	public void setUserDataInputDDO(UserDataInputDDO userDataInputDDO) {
//		this.userDataInputDDO = userDataInputDDO;
//	}
//	
//	public String getPricingStrategySeverityLevelDescriptionExpansion() {
//		return pricingStrategySeverityLevelDescriptionExpansion;
//	}
//
//	public void setPricingStrategySeverityLevelDescriptionExpansion(String pricingStrategySeverityLevelDescriptionExpansion) {
//		this.pricingStrategySeverityLevelDescriptionExpansion = pricingStrategySeverityLevelDescriptionExpansion;
//	}
//	
//	public void testGetUserData() throws InterruptedException{		
//		setScenarioName(scenario);
//		UserDataDDO returnedDDO;
//		try {
//			returnedDDO = afService.getUserData(userDataInputDDO);
//			String actualPricingStrategySeverityLevelDescriptionExpansion = returnedDDO.getPricingStrategySeverityLevelDescriptionExpansion();			
//			assertEquals("Pricing Strategy Severity Level", pricingStrategySeverityLevelDescriptionExpansion, actualPricingStrategySeverityLevelDescriptionExpansion);
//			this.testCase(this, returnedDDO, scenario)	;
//		} catch (ServiceException e) {
//			UnKnownException unKnownException =XmlUtil.GetUnknowException(e.getId(),e.getMessage());
//			this.testCase(this,unKnownException, scenario);
//		}		
//	}	
//}
