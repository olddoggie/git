package bizprocesses;

import com.onefbusa.afddo.AgentDDO;
import com.onefbusa.afddo.TermsAPRInputDDO;
import com.onefbusa.afddo.UserDataInputDDO;
import com.onefbusa.afddo.UserQueryDDO;

import static myconstant.TestAccounts.*;
import static myconstant.General.*;
import static myconstant.BizWork.*;
public class AfServiceDdo {
	//static String accountNumber = "5432270003000144";
	String accountNumber = CCA_MCYCLE1_ACCOUNT_NUMBER;
	int contactId = 0;
//	String agentCode;
//	String userName;
//	String password;

	AfServiceDdo(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	AfServiceDdo() {		
	}
	
	AgentDDO getAgentDDO() {
		AgentDDO agentDDO = new AgentDDO();
		agentDDO.setAgentCode(SECURITY_AGENT_FDR_CODE);
		agentDDO.setAgentLogin(ODS_USER_NAME);
		agentDDO.setAgentPW(ODS_PASSWORD);
		return agentDDO;
	}

	UserQueryDDO getUserQueryDDO() {
		UserQueryDDO userQueryDDO = new UserQueryDDO();
		userQueryDDO.setAcctNum(accountNumber);
		userQueryDDO.setContactId(contactId);
		return userQueryDDO;
	}

	UserDataInputDDO getUserDataInputDDO() {
		UserDataInputDDO userDataInputDDO = new UserDataInputDDO();
		userDataInputDDO.setAgentCode(SECURITY_AGENT_FDR_CODE);
		userDataInputDDO.setAgentProfile("");
		userDataInputDDO.setChannelId(3);
		userDataInputDDO.setCollectorGroup("");
		userDataInputDDO.setEmployeeId(1068);
		userDataInputDDO.setSourceId(4);
		//0 to test CSR, 1 to test Collections
		userDataInputDDO.setUserDataQueryId(2);
		//userDataInputDDO.setVruCallId(0);
		userDataInputDDO.setUserQueryDDO(getUserQueryDDO());
		return userDataInputDDO;
	}
	TermsAPRInputDDO getTermsAPRInputDDO() {
		TermsAPRInputDDO termsAPRInputDDO = new TermsAPRInputDDO();
		termsAPRInputDDO.setAcctNum(accountNumber);
		termsAPRInputDDO.setContactId(contactId);
		return termsAPRInputDDO;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
