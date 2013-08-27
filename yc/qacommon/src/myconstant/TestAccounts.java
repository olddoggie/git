package myconstant;

import java.util.ResourceBundle;

import utilityLib.Tools;

import myaccount.AccountFakeFdr;


public class TestAccounts {
	private static ResourceBundle rb = ResourceBundle.getBundle("testAccounts");	
	public static final String CCA_FAKEFDR1_ACCOUNT_NUMBER = rb.getString("cca_fakefdr1");
	public static final String CCA_FAKEFDR2_ACCOUNT_NUMBER = rb.getString("cca_fakefdr2");	
	public static final String CCA_MCYCLE1_ACCOUNT_NUMBER = rb.getString("cca_mcycle1");		
	
	public static final String CCA_BAT_ACCOUNT_NUMBER = rb.getString("cca_bat");	
	public static final String DDA_BAT_ACCOUNT_NUMBER = rb.getString("dda_bat");
	
	public static final String DDA_ACTIVE1_ACCOUNT_NUMBER = rb.getString("dda_active1");
	public static final String DDA_CLOSED1_ACCOUNT_NUMBER = rb.getString("dda_closed1");	
	public static final String DDA_DORMANT1_ACCOUNT_NUMBER = rb.getString("dda_dormant1");		
	public static final String DDA_LOCKED1_ACCOUNT_NUMBER = rb.getString("dda_locked1");	
	
	public static final String DDA_ACTIVE2_ACCOUNT_NUMBER = rb.getString("dda_active2");
	public static final String DDA_CLOSED2_ACCOUNT_NUMBER = rb.getString("dda_closed2");	
	public static final String DDA_DORMANT2_ACCOUNT_NUMBER = rb.getString("dda_dormant2");		
	public static final String DDA_LOCKED2_ACCOUNT_NUMBER = rb.getString("dda_locked2");
	
	// ACCOUNT OBJECTS
	public static final AccountFakeFdr CCA_FAKEFDR1 = new AccountFakeFdr(CCA_FAKEFDR1_ACCOUNT_NUMBER);
	public static final AccountFakeFdr CCA_FAKEFDR2 = new AccountFakeFdr(CCA_FAKEFDR2_ACCOUNT_NUMBER);	
	//public static final AccountMCycle CCA_MCYCLE1 = new AccountFakeFdr(CCA_MCYCLE1_ACCOUNT_NUMBER);
	
	
	public static final AccountFakeFdr DDA_ACTIVE1 = new AccountFakeFdr(DDA_ACTIVE1_ACCOUNT_NUMBER);
	public static final AccountFakeFdr DDA_CLOSED1 = new AccountFakeFdr(DDA_CLOSED1_ACCOUNT_NUMBER);
	public static final AccountFakeFdr DDA_DORMANT1 = new AccountFakeFdr(DDA_DORMANT1_ACCOUNT_NUMBER);
	public static final AccountFakeFdr DDA_LOCKED1 = new AccountFakeFdr(DDA_LOCKED1_ACCOUNT_NUMBER);
	
	public static final AccountFakeFdr DDA_ACTIVE2 = new AccountFakeFdr(DDA_ACTIVE2_ACCOUNT_NUMBER);
	public static final AccountFakeFdr DDA_CLOSED2 = new AccountFakeFdr(DDA_CLOSED2_ACCOUNT_NUMBER);
	public static final AccountFakeFdr DDA_DORMANT2 = new AccountFakeFdr(DDA_DORMANT2_ACCOUNT_NUMBER);
	public static final AccountFakeFdr DDA_LOCKED2 = new AccountFakeFdr(DDA_LOCKED2_ACCOUNT_NUMBER);
}
