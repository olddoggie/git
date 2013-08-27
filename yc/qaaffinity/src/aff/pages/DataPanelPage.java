package aff.pages;

import static frameLib.PrintTestCases.print;
import frameLib.MyWebElement;

public class DataPanelPage extends AffinityBasePage {
	public static MyWebElement accountSecuritySection = myWebelement("account_sec_word_3");
	public static MyWebElement vdpSelect = myWebelement("vdp_select");
	public static MyWebElement accountSecurityLinkInVerifyVDP = myWebelement("accountSecurityLinkInVerifyVDP");
	public static MyWebElement constantDataPanel = myWebelement("cdp_wrap");
	public static MyWebElement variableDataPanel = myWebelement("vdp_wrap");
	public static MyWebElement updateButton = myWebelement("updateBtn");
	public static MyWebElement monPanel = myWebelement("mon");
	public static MyWebElement strtgyPanel = myWebelement("strat");
	
	
	//csr panel
	public static MyWebElement verifyPanel = myWebelement("verify");
	public static MyWebElement PaymentPanel = myWebelement("pmt");
	
	public static MyWebElement minPmtDueAmount = myWebelement("cssSelector:span[class='showTip min_pmt_due']");
	public static MyWebElement pricingMinPmtDueAmount = myWebelement("cssSelector:span[class='showTip prc_min_pmt_due']");
	public static MyWebElement minPmtHoverOver = myWebelement("min_pmt_due");
	public static MyWebElement pricingMinPmtHoverOver = myWebelement("prc_min_pmt_due");
	public static MyWebElement verdAddressButton = myWebelement("verdAddressBtn");
	
	
	public static DataPanelPage load() {
		vdpSelect.waitUntilElementPresentInFrame(ACCOUNT_FRAME);
		//vdpSelect.waitUntilElementEnabledInFrame(ACCOUNT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new DataPanelPage();
	}

}
