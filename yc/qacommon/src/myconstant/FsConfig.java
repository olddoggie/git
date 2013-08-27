package myconstant;

import java.util.ResourceBundle;

public class FsConfig {
	private static ResourceBundle rb = ResourceBundle.getBundle("fsConfig");
	public static final String ACTIVE_PARTY_ID = rb.getString("active_party_id");
	public static final String INACTIVE_PARTY_ID = rb.getString("inactive_party_id");
	//public static final String FS_DOMAIN = rb.getString("domain");
	public static final String FS_INSTANCE = rb.getString("instance");
	
}

