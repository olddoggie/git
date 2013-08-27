package myconstant;

import java.util.ResourceBundle;

public class AccountDefaultValues {
	private static ResourceBundle rb = ResourceBundle.getBundle("accountDefaultValues");
	public static final String PRIMARY_FIRST_NAME_DEFAULT = rb.getString("dda.pFirstName");
	public static final String PRIMARY_LAST_NAME_DEFAULT = rb.getString("dda.pLastName");
	public static final String ADDRESS_LINE_1_DEFAULT = rb.getString("dda.addressLine1");	

	public static final String CITY_DEFAULT = rb.getString("dda.city");	
	public static final String STATE_DEFAULT = rb.getString("dda.state");		
	public static final String ZIP5_DEFAULT = rb.getString("dda.zip5");	
	public static final String ABA_NUMBER_DEFAULT = rb.getString("dda.abaNumber");	
	public static final String CHECKING_ACCOUNT_NUMBER_DEFAULT = rb.getString("dda.checkAcountNumber");		
	public static final String CHECKING_ACCOUNT_NUMBER_LAST_4_DEFAULT = rb.getString("dda.checkAcountNumberLast4");	
}
