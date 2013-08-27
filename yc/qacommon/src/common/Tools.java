package common;

import static utilityLib.Tools.*;

import java.util.Calendar;
import java.util.TimeZone;
public class Tools {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( getPaymentDate("MM/dd/yyyy","PST"));
		System.out.println( getPaymentDate("MM/yyyy","PST"));
		

	}
	
	public static String getPaymentDate(String format, String timeZone){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		if(cal.get(Calendar.HOUR_OF_DAY)>=17){
			return addDays(cal, 1, format);
		}else{
			return getDateString(cal,format);
		}
		
	}

}
