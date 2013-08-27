package myaccount;

import static myconstant.SpringDb.*;
import static utilityLib.Tools.getDateString;
import java.util.Calendar;


public class AccountFakeFdr extends Account{

	public AccountFakeFdr(String accountNumber) {
		super(accountNumber);
	}
	
	public void updateExternalStatus(String status) {
		String sqlStr = "update FFDRADMIN.CAP c set c.EXTR_STTS_CD = '" + status + "' where c.entr_id = '" + accountNumber + "'";
		COD.update(sqlStr);
	}
	public void updateInternalStatus(String status) {
		String sqlStr = "update FFDRADMIN.CAP c set c.INRL_STTS_CD = '" + status + "' where c.entr_id = '" + accountNumber + "'";
		COD.update(sqlStr);
	}

	
	public void restoreFakeFDRAccount() {
		StringBuilder sql = new StringBuilder();
		sql.append("update ffdradmin.cap c set ");
		sql.append("ACCT_ID='" + accountNumber + "',");
		sql.append("ACCT_NR='',");
		sql.append("ACTV_CTD_AM='',");
		sql.append("ADDR_CNTN_LINE_ONE_TX='ADDRESS20085610746',");
		sql.append("ADDR_CNTN_LINE_TWO_TX='APT#2',");
		sql.append("ADDR_SBDV_ONE_TX='DE',");
		sql.append("AUTH_TOTL_AM='',");
		sql.append("AVLB_CRDT_AM='5000',");
		sql.append("BHVR_SCOR_ID='',");
		sql.append("BRTH_DT='1971-03-21',");
		sql.append("BSNS_PHON_ID='4155555555',");
		sql.append("CITY_NM='DAKOTA DUNES',");
		sql.append("CLLC_CD='',");
		sql.append("CLNT_CLSS_TWLV_CD='',");
		// CRBR_RPRT_CD is not allowed to be null);
		sql.append("CRBR_RPRT_CD='1',");
		sql.append("CRBR_SCOR_ID='',");
		sql.append("CRDT_LIMT_AM='10000',");
		sql.append("CRRN_BAL_AM='100',");
		sql.append("CRRN_MPD_AM='300',");
		sql.append("CRRN_PRCN_STGY_ID='',");
		sql.append("CTD_LATE_CHRG_AM='',");
		sql.append("CTD_OVRL_FEE_AM='',");
		sql.append("CTD_UNPD_BPD_AM='33.33',");
		sql.append("CUST_NM='LNAME,AUTO_FNAME103',");
		sql.append("DAYS_DLNQ_CT='',");
		sql.append("DLNQ_AM='30',");
		sql.append("DLNQ_FIVE_CYCL_AM='',");
		sql.append("DLNQ_FOUR_CYCL_AM='',");
		sql.append("DLNQ_ONE_CYCL_AM='',");
		sql.append("DLNQ_SEVN_CYCL_AM='',");
		sql.append("DLNQ_SIX_CYCL_AM='',");
		sql.append("DLNQ_THRE_CYCL_AM='',");
		sql.append("DLNQ_TWO_CYCL_AM='',");
		sql.append("EMAL_HOME_ADDR_TX='jyu@1fbusa.com',");
		sql.append("ENTR_ID='" + accountNumber + "',");
		sql.append("EXTR_STTS_CD=' ',");
		sql.append("EXTR_STTS_RESN_CD='',");
		sql.append("FEE_WAVR_LATE_CHRG_CD='0',");
		sql.append("FEE_WAVR_OVRL_CD='0',");
		sql.append("FIXD_PYMT_AM='',");
		sql.append("HOME_PHON_ID='9258291124',");
		sql.append("INRL_STTS_CD='',");
		sql.append("LAST_PRCN_STGY_ID='',");
		sql.append("LAST_PYMT_AM='81',");

		sql.append("LAST_STMT_BAL_AM='100',");

		sql.append("LAST_STTS_CODE_CHNG_DT='',");
		sql.append("LS_SHRT_TERM_AMRT_CT='',");
		sql.append("PSTL_CD='57049',");
		sql.append("RTRN_CHCK_CT='',");
		sql.append("SOCL_SCRT_ID='511223435',");
		sql.append("TOTL_AUTH_TODY_CT='',");
		sql.append("USER_FLAG_ONE_TO_FOUR_ID='',");

		// sql.append("BLLN_CYCL_CD='01',");
		//
		// Calendar cal = Calendar.getInstance();
		//
		// if(cal.get(Calendar.DAY_OF_MONTH)>=27) cal.add(Calendar.MONTH, 1);
		// // set day as 27 based on the cycle code. Hard-coded
		// cal.set(Calendar.DATE, 27);
		// String nextPaymentDueDate = getDateString(cal, "MM-dd-yy");
		// sql.append("NEXT_PYMN_DUE_DT='" + nextPaymentDueDate + "',");
		//
		// cal.set(Calendar.DATE, 2);
		// String statementDate = getDateString(cal, "yyMMdd");
		// sql.append("STMT_DT='" + statementDate + "',");
		//
		// // subtract one month from the current month
		// cal.add(Calendar.MONTH, -1);
		// cal.set(Calendar.DATE, 2);
		// String lastStatementDate = getDateString(cal, "yyMMdd");
		// sql.append("LAST_STMT_DT='" + lastStatementDate + "',");
		//
		// cal.set(Calendar.DATE, 27);
		// String lastPaymentDate = getDateString(cal, "yyMMdd");
		// sql.append("LAST_PYMT_DT='" + lastPaymentDate + "'");

		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_MONTH) >= 27) {
			sql.append("BLLN_CYCL_CD='20',");

			cal.set(Calendar.DATE, 29);
			String statementDate = getDateString(cal, "yyMMdd");
			sql.append("STMT_DT='" + statementDate + "',");

			cal.set(Calendar.DATE, 23);
			String lastPaymentDate = getDateString(cal, "yyMMdd");
			sql.append("LAST_PYMT_DT='" + lastPaymentDate + "'");

			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DATE, 23);
			String nextPaymentDueDate = getDateString(cal, "MM-dd-yy");
			sql.append("NEXT_PYMN_DUE_DT='" + nextPaymentDueDate + "',");

			cal.add(Calendar.MONTH, -2);
			cal.set(Calendar.DATE, 23);
			String lastStatementDate = getDateString(cal, "yyMMdd");
			sql.append("LAST_STMT_DT='" + lastStatementDate + "',");
		} else {
			sql.append("BLLN_CYCL_CD='01',");

			// set day as 27 based on the cycle code. Hard-coded
			cal.set(Calendar.DATE, 27);
			String nextPaymentDueDate = getDateString(cal, "MM-dd-yy");
			sql.append("NEXT_PYMN_DUE_DT='" + nextPaymentDueDate + "',");

			cal.set(Calendar.DATE, 2);
			String lastStatementDate = getDateString(cal, "yyMMdd");
			sql.append("LAST_STMT_DT='" + lastStatementDate + "',");
			
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DATE, 1);
			String statementDate = getDateString(cal, "yyMMdd");
			sql.append("STMT_DT='" + statementDate + "',");
			
			cal.add(Calendar.MONTH, -2);
			cal.set(Calendar.DATE, 27);
			String lastPaymentDate = getDateString(cal, "yyMMdd");
			sql.append("LAST_PYMT_DT='" + lastPaymentDate + "'");
		}

		sql.append(" where c.entr_id = '" + accountNumber + "'");
		COD.update(sql.toString());
	}


	public void updateCurrentBalance(String balance) {

	}

	public void updateCrbrRprtCdInFakeFDR(String code) {
		String sqlStr = "update FFDRADMIN.CAP c set c.CRBR_RPRT_CD = '" + code + "' where c.entr_id = '" + accountNumber + "'";
		COD.update(sqlStr);

	}


//	private static int[] getAccountIdsByAccountTypeId(String partyId, String accountTypeId) {
//		int[] accountIdArr = new int[10];
//		String strSql = "select account_id from account where party_id=" + partyId + " and account_type_id = " + accountTypeId
//				+ " and thru_date>sysdate";
//		ResultSet rs = getOracleResultSet(strSql);
//		try {
//			int i = 0;
//			while (rs.next()) {
//				accountIdArr[i] = rs.getInt(1);
//				i++;
//				System.out.println(rs.getInt(1));
//			}
//		} catch (SQLException e) {
//			throw new MyException("getExternalDdaAccountIds()", e);
//		}
//
//		return accountIdArr;
//	}
	
}
