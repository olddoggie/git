package fs;
import java.util.ResourceBundle;
import static myconstant.FsConfig.*;
import static myconstant.General.*;

public class FsConstants {
	private static ResourceBundle rb = ResourceBundle.getBundle("fsConstant");
	public static final String FS_URL="https://" + APP_SERVER_URL+ FS_INSTANCE + rb.getString("home.url");
	public static final String FS_LOGOUT_URL="https://" + APP_SERVER_URL+ FS_INSTANCE + rb.getString("logout.url");	
	public static final String FSCM_MOBILE="https://" + APP_SERVER_URL+ FS_INSTANCE + rb.getString("mobile.url");	
	public static final String FS_TESTBED_USERNAME=rb.getString("fs_testbed_username");
	public static final String FS_PASSWORD =rb.getString("fs_password");
	
	public static void main(String[] args) {
		System.out.println(FS_URL);
		System.out.println(FS_LOGOUT_URL);
		System.out.println(FSCM_MOBILE);
	}

}
