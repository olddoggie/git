package myaccount;

import static myconstant.SpringDb.*;
import static myconstant.FsConfig.*;
public class TestBed {
	public static void add2TestBed(Account account) {
		String accountNumber = account.getAccountNumber();
		String sqlStr = "update account set party_id =" + ACTIVE_PARTY_ID + " where account_number ='" + accountNumber + "'";
		COD.update(sqlStr);
		sqlStr = "update party_account_role set party_id =" + ACTIVE_PARTY_ID
				+ " where account_id in (select account_id from account where account_number  ='" + accountNumber + "')";
		COD.update(sqlStr);
	}

	public static void resetTestBed() {
		String sqlStr = "update account set party_id =" + INACTIVE_PARTY_ID + " where party_id = " + ACTIVE_PARTY_ID ;
		COD.update(sqlStr);
		sqlStr = "update party_account_role set party_id =" + INACTIVE_PARTY_ID + " where party_id = " + ACTIVE_PARTY_ID;
		COD.execute(sqlStr);
	}	

}
