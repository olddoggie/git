package mpd;

import static tools.Account.*;

public class MPDShared {
	public static void runAllSteps(Account a){
		(new S2A(a)).process();
		(new S2B(a)).process();		
		(new S2C(a)).process();
		(new S2D(a)).process();
		(new S3A(a)).process();
		(new S3C(a)).process();
		(new S3D(a)).process();		
		(new S4(a)).process();
		(new S5(a)).process();
		(new S6(a)).process();
		(new S7(a)).process();
	}	

}
