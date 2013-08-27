package myaccount;

import static myconstant.SpringDb.*;
import static myconstant.TestAccounts.*;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;

import utilityLib.Tools;

public class AccountMCycle extends Account{

	public AccountMCycle(String accountNumber) {
		super(accountNumber);
	}

	/** 	  
	 * @param accountNumber
	 * @param externalStatus
	 * @return
	 * can't set external status to Z
	 */
	public void updateExternalStatus(String status) {
		String strSql = "EXECUTE FRPC 'CNONMON', '" + accountNumber + "', 'CS', '" + status + "'";
		ODS.execute(strSql);
	}

	/**
	 * @param authorizationFlag is in nm sa1
	 * value can't be blank. value can be white space (" ")
	 * ODS script example: EXECUTE FRPC "CNONMON", '4017240003000028', "CF", " "
	 */
	public void updateAuthorizationFlag(String authorizationFlag) {
		String strSql = "EXECUTE FRPC 'CNONMON', '" + accountNumber + "', 'CF', '" + authorizationFlag + "'";
		ODS.execute(strSql);
	}
	

	/**
	 * @param fieldPosition specify the field position of client controls in nm cc screen
	 * 216 is  client controls
	 * ODS script example: EXECUTE FRPC "CNONMON", "4017240003000028", "216",4, "S"
	 */
	public void updateClientControl(int fieldPosition, String value) {
		String strSql = "EXECUTE FRPC 'CNONMON', '" + accountNumber + "', '216', " + fieldPosition + ",'" + value + "'";
		ODS.execute(strSql);
	}
	
	/**
	 * @param value Misc13 is in nm cs screen --- #13
	 * value can't be blank. value can be white space (" ")
	 * ODS script example: EXECUTE FRPC 'CNONMON', '4017240003000028', '791', 19, 2, 1, ' '
	 */
	public void updateMis13(int position, int length, String value) {
		String strSql = "EXECUTE FRPC 'CNONMON', '" + accountNumber + "', '791', 19," + position + "," + length + ", '" + value + "'";
		ODS.execute(strSql);
	}
	
	/**
	 * @param anualFeeIndicator F01027 is in nm sa1 screen --- ANNUAL CHARGE RATE
	 * ODS script example:EXECUTE FRPC "CNONMON", "4017240003000028", "055", "2"
	 */
	public void updateAnualFeeIndicator_F01027(String anualFeeIndicator) {
		String strSql = "EXECUTE FRPC 'CNONMON', '" + accountNumber + "', '055'" + anualFeeIndicator + "'";
		ODS.execute(strSql);
	}	
	
	/**
	 * @param pricingStrategy is in nm st screen --- CURRENT STRATEGY
	 * ODS script example:EXECUTE FRPC 'CNONMON', '4017240003000028', '168', 'CP', 'DF01'
	 */
	public void updatePricingStrategy(String pricingStrategy) 
	{
//		Here is how came to the query below.
//		1 : I could not find updating cardholder pricing in any specific chapter of the ODS document, RPC manual.
//		2 : I know that updating the cardholder pricing is a non-monetary action (NonMon)
//		3 : I looked in the manual : Cardholder Non-Monetary Transactions, chapter 4 : unformatted transactions. (each NonMon has its own number)
//		4 : I did a search for 'cardholder pricing' and found that NM*168 changes the current pricing.
//		5 : The doc gave the example Examples: NM*168*0220020039015001*CP*1234 (this is what we would use within the FDR 3270 session)
//		            NM = Non Monetary
//		            168 = NonMon number
//		            0220020039015001 = account number
//		            CP = Cardholder Pricing
//		            1234 = New Pricing name
//		6 : I plugged NM*168 parameters into the basic CNONMON call which is EXECUTE CNONMON, "ACCT#", "nonmon #", "parm1", "parm2", "parm3", to get 
//		EXECUTE FRPC "CNONMON", "4017240003000432", "168", "CP", "DF02"
//		7 : Then I tested it using the ASP Client sample in the Intranet DD
		String strSql = "EXECUTE FRPC 'CNONMON', '" + accountNumber + "', '168', 'CP', '" + pricingStrategy + "'";
		ODS.execute(strSql);
	}
	
	
	/**
	 * @param cppomp is in npo screen. Keep on type F8 to the right screen  --overnight
	 * ODS script example:EXEC FRPC "PCF_METHOD_OVERRIDE_UPDATE",@ACCT_ID = '4017240003000028',@SRVC_SBJC_SCTN_ID = 'CPPOMP',@CRRN_ID = "TIER$30"
	 */
	public void updateCppomp(String cppomp) {
		String strSql = "EXECUTE FRPC 'PCF_METHOD_OVERRIDE_UPDATE',@ACCT_ID =  '" + accountNumber + "',@SRVC_SBJC_SCTN_ID = 'CPPOMP',@CRRN_ID = , '" + cppomp + "'";
		ODS.execute(strSql);
	}	

	/**
	 * @param cpporm is in npo screen. Keep on type F8 to the right screen --overnight
	 * ODS script example:EXEC FRPC "PCF_METHOD_OVERRIDE_UPDATE",@ACCT_ID = '4017240003000028',@SRVC_SBJC_SCTN_ID = 'CPPOMP',@CRRN_ID = "TIER$30"
	 */
	public void updateCpporm(String cpporm) {
		String strSql = "EXECUTE FRPC 'PCF_METHOD_OVERRIDE_UPDATE',@ACCT_ID =  '" + accountNumber + "',@SRVC_SBJC_SCTN_ID = 'CPPORM',@CRRN_ID = , '" + cpporm + "'";
		ODS.execute(strSql);
	}	
	
	/**
	 * @param noteId is the id of the note that will be deleted
	 * This function will delete cis note or memo note from an account
	 * If noteId doesn't exist, no note will be will be deleted
	 */
	public void deleteNote(String accountNumber, int noteId){
//		delete note --- it will take overnight to take effect
//		--- 804 is the note id (Sequence Identifier)
//		--- 168 is the agent code (Operator Identifier)
//		EXECUTE FRPC "CMEMDEL", '5432270003000144', "804", "168"
		String strSql =  "EXECUTE FRPC 'CMEMDEL','" +  accountNumber + "','" + noteId + "','168'";
		ODS.execute(strSql);
	}
	
	/**
	 * @param note is part of the note. The whole note is Add Note --- note
	 * This function will add cis note or memo note to an account
	 */
	public void AddNote(String accountNumber, String note){
//		add memo
//		168 is the agent code (Operator Identifier)	
//		EXECUTE FRPC "CMEMADD", '5432270003000144', '168', 'Add Note--123'
		String strSql =  "EXECUTE FRPC 'CMEMADD','" +  accountNumber + "','168', 'Add Note--" + note + "'";
		ODS.execute(strSql);
	}
	
	
	/**
	 * @param note is part of the note. The whole note is Add Note --- note
	 * This function will add XAA note to an account
	 */
	public void AddXaaNote(String accountNumber, String note){
//		add XAA memo
//		168 is the agent code (Operator Identifier)	
//		EXECUTE FRPC "XAAPROC", "168", "4017240003000028", "LL1116"
		String strSql =  "EXECUTE FRPC 'XAAPROC','168','" +  accountNumber + "', 'Add XAA Note--" + note + "'";
		ODS.execute(strSql);
	}
	
	
	
	///////////////////////////////////////////////
	
	
	private String getCapField2(String field){
		String sqlStr = String.format("SELECT %s from cap where entr_id = '%s'", field, security.decrypt(accountNumber));
//		if(ODS.queryForList(sqlStr).size()==0){
//			return " ";
//		}else{
//			return ODS.queryForObject(sqlStr, String.class);
//		}	
		String result = " ";
		try {
			result = ODS.queryForObject(sqlStr, String.class);
		} catch (IncorrectResultSizeDataAccessException e) {
			return result;
		}
		return result;
	}
	
	
	public String getCapField(String field){
		String sqlStr = String.format("SELECT %s from cap where entr_id = '%s'", field, accountNumber);
		if(ODS.queryForList(sqlStr).size()==0){
			return " ";
		}else{
			return ODS.queryForObject(sqlStr, String.class);
		}
	}
	
	public void associateFdrAccountToCodAccount(String sourceAcctNumber){
		//select * from account where account_type_id = 1 and account_number like '^5%' and party_id in (select party_id from security_credential) and rownum=1
		String sql = String.format("update account set account_number='%s' where account_number='%s'", security.encrypt("1234123412341234"),security.encrypt(accountNumber));
		COD.update(sql);
		sql = String.format("update account set account_number = '%s' where account_number ='%s'", security.encrypt(accountNumber), security.encrypt(sourceAcctNumber));
		COD.update(sql);
		//syncSSN();
	}
	
	public void syncSSN(){
		String fdrSSN = security.encrypt(getCapField("SOCL_SCRT_ID"));
		String sqlStr = String.format("update person set ssn = '%s' where party_id in %s", fdrSSN, partyIdSql());
		COD.update(sqlStr);
		
	}
	public boolean IsPaperless(){
		if(getCapField("next_stmt_hold_cd").equalsIgnoreCase("W")){
			return true;
		}else{
			return false;
		}

		// for automation, if == w, mean paperless
		//FDR>NEXT_STMT_HOLD_CD
		//affinity cdp cycle cd
		//nm cs --- 3270  --- w is paperless n is mail
//		If Statement Delivery Setting [F24011:001] == "Online Only" and Stmt Hold Code[F01200:001] == "W", then W=Paperless
//		If Statement Delivery Setting [F24011:001] == "U.S. Mail" and Stmt Hold Code[F01200:001] == "W", then W=CSS Only
//		If Statement Delivery Setting [F24011:001] cannot be read from COD and Stmt Hold Code[F01200:001] == "W", then display has(#) mark sign
//
//				C,Y,1-9= Send to 1FBUSA
//				D=Contact Supv
//				N, V, blank =Send to CM
//				R=Hold Corr
//				S=Send to FDR R&I
//		return "w or n";
	}
	
	public void postSaleTransaction(String amount)
	{
//			Field description:
//			Adjustment batch : example CA for cash, PS for sale -- ca119 -- 119 is job id and can be change to 229 or something else
//			System number --- 3750 -- for visa --- 8227 for mastcard
//			Amount : dollarcents -- 10000
//			Account number --- 4453263053900674
//			Adjustment letter --- 5179 --- in fdr --- JL 5179
//			Type -- 05
//			Prin --- 0000
//			cash --- 254 / purchases -- 253
//			Merch # 401724000000018 for Purchases or 401724000000091 
		
		
//		String strSql = EXECUTE FRPC "ADJUSTMENT_BATCH_ADD", "05", "PS119", "3750", "0000",
//				"10000", , "5179", "1", "4453263053900674", "253", , "401724000000018", "10000", , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,

		
		
	}
	
	public void postPurchaseTransaction(String amount)
	{
//		EXECUTE FRPC "ADJUSTMENT_BATCH_ADD", "05", "ca119", "3750", "0000",
//		"10000", , "5179", "1", "4453263053900674", "254", , "401724000000091", "10000", , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,

	}
	
	 public void AddCisNote(String note){	
//			add memo
//			168 is the agent code (Operator Identifier)	
//			EXECUTE FRPC "CMEMADD", '5432270003000144', '168', 'Add Note--123'
			String strSql =  "EXECUTE FRPC 'CMEMADD','" +  accountNumber + "','168', 'Add Note--" + note + "'";
			ODS.execute(strSql);
	 }
	//
	//
	// public DeleteCisNote(){
	//
	// for (int i = 1; i < 1000; i++) {
	// OdsSql.deleteNote("4465752158012357", i);
	// }
	// System.out.println("done");
	// }

	public boolean callOdsRpc(String strSql) {
		boolean flag = false;
		try {
			ODS.execute(strSql);
		} catch (Exception e) {
			if (e.getMessage().contains("CMPLT")) {
				flag = true;
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		AccountMCycle CCA_BAT = new AccountMCycle(CCA_BAT_ACCOUNT_NUMBER);
				
		String s = CCA_BAT.getCapField2("LAST_STMT_BAL_AM");
		System.out.println("aaa" + utilityLib.Tools.trimLeadingZeros(s) );
	}
}
