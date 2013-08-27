package mpd;
import static myconstant.SpringDb.PROGRAMBUDDY;
import static tools.Account.*;

import org.apache.log4j.Logger;

// need clarify where  F02086_001 is from
public class S1 {

	private Logger logger = Logger.getLogger(S1.class);
	private Account a;

	public S1(Account a){
		this.a = a;
	}	
	
	public void process(){
		s1a_process();
	}
	
	public void s1a_process(){
//		F01912_001 = F02086_001;
//		F01913_001 = F02086_001;
		a.F01912_001 = "";
		a.F01913_001 = "";
	}
	
	public void s1b_process(){

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
