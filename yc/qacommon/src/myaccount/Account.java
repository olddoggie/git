package myaccount;

import static myconstant.SpringDb.*;
import static myconstant.TestAccounts.CCA_BAT_ACCOUNT_NUMBER;
import static myconstant.AccountDefaultValues.*;
import static myconstant.General.*;
import static myconstant.FsConfig.*;
import static utilityLib.Tools.quote;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.onefbusa.securityservice.SecurityServiceDecorator;

import utilityLib.Tools;

public class Account {
	
	static SecurityServiceDecorator security = new SecurityServiceDecorator();
	protected String accountNumber;

	
	protected String partyIdSql() {
		String sql = String.format("(select party_id from account where account_number='%s')", security.encrypt(accountNumber));
		return sql;

	}

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
//	public boolean isInCod(){
//		String sqlStr = String.format("select account_number from account where account_number = '%s'", accountNumber);
//		int record = COD.queryForList(sqlStr).size();
//		if(record==0) return false;
//		else return true;
//	}
	
	

	public String[] getRegisterInfo() {
		String sqlStr = String.format("select primary_name , ssn, birth_date from account a, person p where a.party_id = p.party_id and account_number = '%s' and a.thru_date>sysdate", security.encrypt(accountNumber));
		Map<String, Object> registerInfo = COD.queryForMap(sqlStr);
		String[] info = new String[8];
		info[0] = (String) registerInfo.get("primary_name");
		String temp = security.decrypt((String) registerInfo.get("ssn"));
		info[1] = temp.substring(0, 3);
		info[2] = temp.substring(3, 6);
		info[3] = temp.substring(6, 9);
		Date tempDate = (Date) registerInfo.get("birth_date");
		String formattedDate = new SimpleDateFormat("MMMMM-dd-yyyy")
				.format(tempDate);
		String[] dateArr = formattedDate.split("-");
		info[4] = dateArr[0];
		info[5] = dateArr[1];
		info[6] = dateArr[2];
		info[7] = accountNumber;
		return info;

	}

	public void resetFsPassword(String password) {
		String sqlStr = String
				.format("update security_credential set password= '%s', salt= '' where party_id = "
						+ "(select party_id from account where account_number = '%s')",
						password, security.encrypt(accountNumber));
		COD.update(sqlStr);
	}

	public void unRegister() {
		String sqlStr = "delete from codadmin.APPLICATION_LOGIN al where al.security_credential_id in "
				+ "(select sc.security_credential_id from codadmin.SECURITY_CREDENTIAL sc where sc.party_id = "
				+ partyIdSql() + ")";
		COD.update(sqlStr);
		sqlStr = "delete from codadmin.SECURITY_CREDENTIAL sc where sc.party_id = "
				+ partyIdSql();
		COD.update(sqlStr);
	}

	public void unRegisterStg() {
		int partyId = COD.queryForInt(partyIdSql());		
		StringBuilder sql = new StringBuilder();
		sql.append("declare\n");
		sql.append("v_pty_id number :=" + partyId +  ";\n");
		sql.append("begin \n");
		sql.append("delete_registration(v_pty_id);\n");
		sql.append("end;");
		COD.execute(sql.toString());
	}
	
	
	public void unRegister(String userName) {
		String sqlStr = String
				.format("delete from application_login where security_credential_id in (select security_credential_id from security_credential where user_name = '%s')",
						userName);
		COD.update(sqlStr);
		sqlStr = String.format(
				"delete from security_credential where user_name = '%s'",
				userName);
		COD.update(sqlStr);
	}

	public void deletePartyLock() {
		String partyLockActionId = String.format(
				"(select party_lock_id from party_lock where party_id in %s)",
				partyIdSql());
		String sqlStr = String
				.format("delete codadmin.party_lock_action pla where pla.party_lock_id in %s ",
						partyLockActionId);
		COD.update(sqlStr);
		sqlStr = "delete codadmin.party_lock pl where pl.party_id ="
				+ partyIdSql();
		COD.update(sqlStr);
	}

	public void deletePartyStrikes() {
		// String sqlStr = "delete codadmin.party_strike ps where ps.party_id ="
		// + ACTIVE_PARTY_ID;
		String sqlStr = "delete codadmin.party_strike ps where ps.party_id ="
				+ partyIdSql();
		COD.update(sqlStr);
	}

	public void deleteIpLock() {
		String sqlStr = "delete codadmin.application_access aa where aa.ip_address ="
				+ quote(IP_ADDRESS);
		COD.update(sqlStr);
	}

	public void deleteLock() {
		deletePartyStrikes();
		deletePartyLock();
		deleteIpLock();
	}

	public void addCcaHold() {
		// String sql =
		// "insert into dbo.PaymentHold (AcctNum, PaymentAcctNum, PaymentAcctTypeId, ReleaseDate, CreatedBy, CreatedOn, HoldReasonTypeId, ReleasedOn) values ('"
		// + accountNumber + "', '" + accountNumber +
		// "', '1', 'YYYY-MM-DD', 'AD\\NTuserID', 'YYYY-MM-DD', '1', 'YYYY-MM-DD')";
		String sql = "insert into dbo.PaymentHold (AcctNum, PaymentAcctNum, PaymentAcctTypeId, ReleaseDate, CreatedBy, CreatedOn, HoldReasonTypeId, ReleasedOn) values ('"
				+ accountNumber
				+ "', '"
				+ security.decrypt(accountNumber)
				+ "', '1', '9999-01-01', 'AD\\NTuserID', '2013-01-01', '1', '9999-01-01')";
		AUTOPAY.update(sql);
	}

	// ???
	public void addDdaHold(String paymentAbaNumber, String externalDdaAccount) {
		String sql = "insert into dbo.PaymentHold (AcctNum, PaymentAcctNum, PaymentAcctTypeId, PaymentABANum, ReleaseDate, CreatedBy, CreatedOn, HoldReasonTypeId, ReleasedOn) values('"
				+ accountNumber
				+ "', '"
				+ externalDdaAccount
				+ "', '4', '"
				+ paymentAbaNumber
				+ "', '9999-01-01', 'AD\\NTuserID', '2013-01-01', '13', '9999-01-01')";
		AUTOPAY.update(sql);
	}

	public void deleteAccountOnHold() {
		String sql = "delete from dbo.PaymentHold where AcctNum="
				+ quote(accountNumber);
		AUTOPAY.update(sql);
	}

	public void deleteAutopay() {
		String strSql = "delete from dbo.autopay where creditcardnum1 ="
				+ quote(accountNumber);
		AUTOPAY.update(strSql);
	}

	public void deleteAffinityNotes() {
		String strSql = "delete from affinitynotes.dbo.cisnote where mem_cardhdlr ="
				+ quote(accountNumber);
		AFFINITY_NOTES.update(strSql);
	}

	public void consentDdaLegalTerm() {
		StringBuilder sql = new StringBuilder();
		sql.append("declare\n");
		sql.append("i_account_id number;\n");
		sql.append("cursor my_cursor is select account_id from codadmin.account where party_id = "
				+ partyIdSql() + " and account_type_id =2; \n");
		sql.append("begin \n");
		sql.append("if not my_cursor%isopen then \n");
		sql.append("open my_cursor; \n");
		sql.append("end if; \n");
		sql.append("loop \n");
		sql.append("fetch my_cursor into i_account_id; \n");
		sql.append("exit when my_cursor%NOTFOUND; \n");
		sql.append("codadmin.legal_docs_api.fscm_agree_to_legal_doc(i_account_id,3,1,sysdate); \n");
		sql.append("end loop; \n");
		sql.append("end;");
		COD.execute(sql.toString());
	}

	public void deleteAllEnrolledDdas() {
		String partyId = COD.queryForObject(partyIdSql(), String.class);
		deleteEnrolledDdas(partyId);
		deleteEnrolledDdas(ACTIVE_PARTY_ID);
		deleteEnrolledDdas(INACTIVE_PARTY_ID);
	}

	private void deleteEnrolledDdas(String partyId) {
		StringBuilder sql = new StringBuilder();
		sql.append("declare\n");
		sql.append("i_account_id number;\n");
		sql.append("cursor my_cursor is select account_id from codadmin.account where party_id = "
				+ partyId + " and account_type_id =2 and thru_date>sysdate; \n");
		sql.append("begin \n");
		sql.append("if not my_cursor%isopen then \n");
		sql.append("open my_cursor; \n");
		sql.append("end if; \n");
		sql.append("loop \n");
		sql.append("fetch my_cursor into i_account_id; \n");
		sql.append("exit when my_cursor%NOTFOUND; \n");
		sql.append("codadmin.fs_eautopay_api.fs_epay_cm_delete_enrolled_dda("
				+ partyId + ",i_account_id);\n");
		sql.append("end loop; \n");
		sql.append("end;");
		COD.execute(sql.toString());
	}

	// public void deleteEnrolledDdas(String abaNumber,
	// String externalDDAaccountNumber) {
	// String account_id = String
	// .format("select account_id from demand_deposit_account where bank_id in (select bank_id from bank where aba_number = '%s') and account_id in (select account_id from account where account_number = '%s'",
	// abaNumber, externalDDAaccountNumber);
	// String cursorStr = String
	// .format(" cursor my_cursor is select party_id, account_id from account where account_id in (%s); \n",
	// account_id);
	//
	// StringBuilder sql = new StringBuilder();
	// sql.append("declare\n");
	// sql.append("i_account_id number;\n");
	// sql.append("i_party_id number;\n");
	// sql.append(cursorStr);
	// sql.append("begin \n");
	// sql.append("if not my_cursor%isopen then \n");
	// sql.append("open my_cursor; \n");
	// sql.append("end if; \n");
	// sql.append("loop \n");
	// sql.append("fetch my_cursor into i_party_id,i_account_id; \n");
	// sql.append("exit when my_cursor%NOTFOUND; \n");
	// sql.append("codadmin.fs_eautopay_api.fs_epay_cm_delete_enrolled_dda(i_party_id ,i_account_id);\n");
	// sql.append("end loop; \n");
	// sql.append("end;");
	// COD.execute(sql.toString());
	// }

	public void deleteEnrolledDdasByAccountNumber(
			String externalDDAaccountNumber) {
		String cursorStr = String
				.format(" cursor my_cursor is select party_id, account_id from account where account_number in (%s); \n",
						externalDDAaccountNumber);

		StringBuilder sql = new StringBuilder();
		sql.append("declare\n");
		sql.append("i_account_id number;\n");
		sql.append("i_party_id number;\n");
		sql.append(cursorStr);
		sql.append("begin \n");
		sql.append("if not my_cursor%isopen then \n");
		sql.append("open my_cursor; \n");
		sql.append("end if; \n");
		sql.append("loop \n");
		sql.append("fetch my_cursor into i_party_id,i_account_id; \n");
		sql.append("exit when my_cursor%NOTFOUND; \n");
		sql.append("codadmin.fs_eautopay_api.fs_epay_cm_delete_enrolled_dda(i_party_id ,i_account_id);\n");
		sql.append("end loop; \n");
		sql.append("end;");
		COD.execute(sql.toString());
	}

	public void enrollDda() {
		enrollDda("011305749");
	}

	public void enrollDda(String routingNumber) {
		enrollDda(routingNumber, CHECKING_ACCOUNT_NUMBER_DEFAULT);
	}

	public void enrollDda(String routingNumber, String paymentAcctNumber) {
		// int i_party_id = Integer.valueOf(partyIdSql()).intValue();
		// String partyIdSql =
		// "(select convert(varchar,party_id) from account where account_number="
		// + quote(accountNumber) + ")";
		// String i_party_id = COD.queryForObject(partyIdSql, String.class);
		String i_party_id = COD.queryForObject(partyIdSql(), BigInteger.class)
				.toString();
		String i_check_acct_number = quote(paymentAcctNumber);
		String i_aba_routing_number = quote(routingNumber);
		String i_primary_name = quote(PRIMARY_FIRST_NAME_DEFAULT + " "
				+ PRIMARY_LAST_NAME_DEFAULT);
		String i_secondary_name = "''";
		String i_address_line1 = quote(ADDRESS_LINE_1_DEFAULT);
		String i_address_line2 = "''";
		String i_city = quote(CITY_DEFAULT);
		String i_state = quote(STATE_DEFAULT);
		String i_zip5 = quote(ZIP5_DEFAULT);
		String i_zip4 = "''";
		String i_country = "''";
		String i_acct_number_last4 = quote(CHECKING_ACCOUNT_NUMBER_LAST_4_DEFAULT);
		int i_acct_type_id = 2;

		StringBuilder sql = new StringBuilder();
		sql.append("declare\n");
		sql.append("o_account_id number(8);\n");
		sql.append("begin \n");
		sql.append("codadmin.fs_eautopay_api.fs_epay_cm_enroll_dda(");
		sql.append(i_party_id + "," + i_check_acct_number + ","
				+ i_aba_routing_number + ",");
		sql.append(i_primary_name + "," + i_secondary_name + ","
				+ i_address_line1 + ",");
		sql.append(i_address_line2 + "," + i_city + "," + i_state + ",");
		sql.append(i_zip5 + "," + i_zip4 + "," + i_country + ",");
		sql.append(i_acct_number_last4 + "," + i_acct_type_id + ","
				+ "o_account_id);\n");
		// sql.append( "commit;\n");
		sql.append("end;");
		COD.execute(sql.toString());
	}

	public static void restoreCodAccount() {
		// update account table>primary name/last name
		// update person table>ssn
		// update security_credential> login
	}

	public static void main(String[] args) {
		System.out.println(IP_ADDRESS);
		Account t1 = new Account("5432270003000532");
		t1.deleteLock();
		// 4017240003000010
		// 4017240003001018
		// 4017240003001026
		// 4017240003001034
		// 4017240003001042
		// 5432270003001019
		// 5432270003001027
		// 5432270003001035
		// 5432270003001043
		// 5432270003001050
		// Account t1 = new Account("4017240003001018");

		// AccountFakeFdr CCA_MCYCLE1 = new AccountFakeFdr(
		// CCA_MCYCLE1_ACCOUNT_NUMBER);
		// CCA_MCYCLE1.deleteAccountOnHold();
		// CCA_MCYCLE1.deleteLock();
		// // CCA_MCYCLE1.deleteLock();
		// // CCA_MCYCLE1.deleteAllEnrolledDdas();
		// // CCA_MCYCLE1.enrollDda(ABA_NUMBER_DEFAULT);
		// //t1.getRegisterInfo();
		// TestBed.resetTestBed();
		// TestBed.add2TestBed(CCA_FAKEFDR1);
		// TestBed.add2TestBed(CCA_FAKEFDR2);
		// // CCA_MCYCLE1.enrollDda();
		// // CCA_FAKEFDR1.deleteAccountOnHold();
		// // CCA_MCYCLE1.deleteAutopay();
		//
		// CCA_MCYCLE1.deleteAllEnrolledDdas();
		// CCA_MCYCLE1.enrollDda();
		// CCA_MCYCLE1.addCcaHold();
		// CCA_FAKEFDR1.deleteAllEnrolledDdas();
		// CCA_FAKEFDR1.enrollDda();
		// CCA_FAKEFDR1.addCcaHold();
		// CCA_FAKEFDR1.deleteAccountOnHold();

		// Account t1 = new Account("4017240003000358");
		// t1.addCcaHold();
		// t1.deleteAccountOnHold();

		// CCA_FAKEFDR2.deleteAutopay();
		// System.out.println("done");
	}
}
