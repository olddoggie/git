package frameLib;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class FrameUtilities {

//	public static void setScenarioName(String bwServer, String port, String scenario) {
//		try {
//			URL url = new URL("http://" + bwServer + ":" + port + "/RegressionTestbed?" + scenario);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.getInputStream();
//			conn.disconnect();
//		} catch (Exception e) {
//			throw new MyException("rtb set failed");
//		}
//	}
//
//	public static void setScenarioFor1fbBizProcess(String scenario) {
//		setScenarioName(BW_SERVER_NAME, ONEFBBIZPROCESS_PORT, scenario);
//	}
//
//	public static void setScenarioForAccountBizProcess(String scenario) {
//		setScenarioName(BW_SERVER_NAME, ACCOUNTBIZPROCESS_PORT, scenario);
//	}

	public static String creatDefaultPattern(String... strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb = sb.append(".*?").append(s);
		}
		sb.append(".*?");
		return sb.toString();
	}

	public static String formatToday(String format) {
		String s = new SimpleDateFormat(format).format(new Date());
		return s;
	}

	public static String sysDay() {
		Calendar cal = new GregorianCalendar();
		return Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
	}

	public static String sysMonth() {
		Calendar cal = new GregorianCalendar();
		return Integer.toString(cal.get(Calendar.MONTH));
	}

	public static String sysYear() {
		Calendar cal = new GregorianCalendar();
		return Integer.toString(cal.get(Calendar.YEAR));
	}

	public static String addDays2Today(int days, String format) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		return new SimpleDateFormat(format).format(c.getTime());
	}

//	public static void verifyAllElementsPresent(BasePage bp) {
//
//		String pageTitle = bp.getPageTitle();
//		Class<? extends BasePage> c = bp.getClass();
//		Field[] fArr = c.getDeclaredFields();
//		for (Field f : fArr) {
//			if (f.getType().getName().contains("MyWebElement")) {
//				String locator;
//				try {
//					locator = ((MyWebElement) f.get(bp)).getLocator();
//					try {
//						((MyWebElement) f.get(bp)).waitUntilElementPresent();
//					} catch (Exception e) {
//						throw new MyException(pageTitle + "---" + locator);		
//					}
//				} catch (Exception e1) {
//					throw new MyException(e1);	
//				}
//
//			}
//
//		}
//
//	}

	public static void mySleep(double d) {
		long time = System.currentTimeMillis();
		while (System.currentTimeMillis() < (time + d * 1000)) {

		}
	}
	
	public static void main(String[] args) {
		// System.out.println(getDay(260,"MM/dd/yyyy"));
		// System.out.println(sysYear());
		// System.out.println(sysDay());
		// System.out.println(creatDefaultPattern(""));
		// System.out.println(creatDefaultPattern("aa"));
		// System.out.println(creatDefaultPattern("aa", "bb", "cc"));
		// System.out.println("aa bb  ".matches(creatDefaultPattern("aa",
		// "bb")));
	}
}
