package myconstant;

import java.util.ResourceBundle;

public class BizWork {
	private static ResourceBundle rb = ResourceBundle.getBundle("bizWork");
//	public static final String SECURITY_AGENT_FDR_CODE = "JXY";
	
	//public static final String BW_SERVER_URL = "http://qsfoapv2.1fbusa.com";
	public static final String BW_SERVER_NAME = rb.getString("bw_server");
	public static final String BW_SERVER_USER_NAME = rb.getString("bw_server_user_name");
	public static final String BW_SERVER_PASSWORD = rb.getString("bw_server_password");
	
	public static final String ONEFBBIZPROCESS_PORT = rb.getString("1fbbizprocess_port");
	public static final String ACCOUNTBIZPROCESS_PORT = rb.getString("accountbizprocess_port");
}

