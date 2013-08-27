package myconstant;

import java.util.ResourceBundle;

public class General {
	private static ResourceBundle rb = ResourceBundle.getBundle("general");
	public static final String ODS_USER_NAME = rb.getString("ods_user_name");
	public static final String ODS_PASSWORD = rb.getString("ods_password");		
	public static final String SECURITY_AGENT_FDR_CODE = rb.getString("security_agent_fdr_code");	
	public static final int SECURITY_AGENT_FDR_INDEX = Integer.parseInt(rb.getString("security_agent_fdr_index"));	
	public static final String NORMAL_AGENT_FDR_CODE = rb.getString("normal_agent_fdr_code");
	public static final int NORMAL_AGENT_FDR_INDEX = Integer.parseInt(rb.getString("normal_agent_fdr_index"));	
	
	public static final String IP_ADDRESS = rb.getString("ip_address");	
	public static final String EMAIL = rb.getString("email");
	public static final String APP_SERVER_URL = rb.getString("app_server_url");
	public static final String TIME_ZONE = rb.getString("time_zone");
	

	public static void main(String[] args){
		System.out.println(IP_ADDRESS + ODS_USER_NAME );
	}
}

