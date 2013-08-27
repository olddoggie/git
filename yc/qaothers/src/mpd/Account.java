package mpd;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

// not resolved fields : F02086_001, F02188_001,F01911_001
public class Account {
	
	/** ????????CTD Total Unpaid Fees & FC */
	// this field only used in s7b.
	public  double F02188_001 = 0;;

	// s2a
	/** Fixed Payment Amt -- FDR/CAP/FIXD_PYMT_AM --s1b */
	public  double F01020_001 = 0;
	/** Pricing Code --- FDR/CAP/CRRN_PRCN_STGY_ID -- pricing strategy */
	public  String F01202_001 = "TRAD";
	/** Mthd Ovrd Current CPPORM FDR/GetCAPPORMUserData */
	public  String F02073_001 = "";

	/** Last Stmt LF Amt -- CAP/LS_LATE_FEE_AM */
	public  double F02089_001=0;
	/** Last Stmt OCL Amt -- CAP/LS_OVRL_FEE_AM*/
	public  double F02090_001=0;
	/** Last Stmt Interest Amt -- CAP/LS_INTR_BLLD_AM*/
	public  double F02091_001 = 0; //234.58
	/** Cash Advance Outstanding  --CAP/CASH_ADVN_OTST_BAL_AM */
	public  double F01049_001 = 0; //1700

	
	// Create a function to map these fields to rtb file
	/** Misc 13 Pos 34 --- CAP/MISC_THRT_ID */
	public  String F30476_001="";
	/** Misc 13 Pos 35-36 --- CAP/MISC_THRT_ID */
	public  String F30477_001="";
	/** Misc 13 Pos 38 --- CAP/MISC_THRT_ID */
	public  String F30479_001="";
	/** Misc 9 Pos 2-3 CAP/MISC_NINE_ID>*/
	public  String F30695_001="";
	/** Mthd Ovrd Current FDR/getCPPFLC */
	public  String F02257_001 = "";
	
	/** accumulative negam counter -- CAP/LS_SHRT_TERM_AMRT_CT - s3c */
	public  String F01195_001="0";
	/** Mthd Ovrd Current CPPOMP -- CAP/GetCAPPOMPUserData - s2c */
	public  String F02100_001="";


	
	/** Credit Limit -- CAP/CRDT_LIMT_AM -- s5 */
	public  double F01021_001=350;
	/** Current Balance -- CRRN_BAL_AM */
	public  double F01014_001 = 8188.87;		
	/** CAP>EXTR_STTS_CD **/
	public  String F01030= " ";
	/** 7Cycle Delq Amt */
	public  double F12106 =0;
	/** 6Cycle Delq Amt */
	public  double F12105 =0;
	/** 5Cycle Delq Amt */
	public  double F12104 =0;
	/** 4Cycle Delq Amt */
	public  double F12103 =0;
	/** 3Cycle Delq Amt */
	public  double F12102 =0;
	/** 2Cycle Delq Amt */
	public  double F12101 =0;
	/** 1Cycle Delq Amt -- CAP/DLNQ_ONE_CYCL_AM */
	public  double F12100 =0;


	public final double collectionsMinDue = 0.0265;
	public final double CollectionsAlternateMinDue = 0.035;
	public final double CollectionsMinAddOnAmt = 10;
	public final double MaximumMFAFAddOnAmt = 4.59;
	public final double CollectionsMinPaymentDueAmt = 0;
	public final double MaximumLateFeeAllowable = 35;
	
	/** OverCredit Limit Amt  */
	public  double F01016_001;	
	/** Maximum Late Fee Override Amount */
	public  double F30656_001;
	/** Late fee type Id */
	public  int F02258_004;	
	/** Delq Bucket Id	 */
	public  int F01003;
	
	public void setCalucatedFields(){
		/** OverCredit Limit Amt  */
		F01016_001 = getF01016_001();
		/** Late fee type Id */
		F02258_004 = getF02258_004();	
		/** Maximum Late Fee Override Amount */
		F30656_001 =getF30656_001();
		/** Delq Bucket Id	 */
		F01003 =getF01003() ;
	}
	
	public  int getF01003(){
		if(F01030.equalsIgnoreCase("Z")) return 7;
		else if(F12106>0) return 9;
		else if(F12105>0) return 6;
		else if(F12104>0) return 5;
		else if(F12103>0) return 4;
		else if(F12102>0) return 3;
		else if(F12101>0) return 2;
		else if(F12100>0) return 1;
		else return 8;		
	}
	
	public  double getF01016_001(){
		double overCreditLimitAmt = F01014_001 - F01021_001;
		if(overCreditLimitAmt<0) {return 0;}
		else {return overCreditLimitAmt;}
	}
	
	public  int getF02258_004(){
		// add the logic to get the late fee type id based on pricing strategy
		int lateFeeTypeId;
		List externalStatusList = Arrays.asList("B", "I", "Z");
		if(externalStatusList.contains(F01030)){lateFeeTypeId = 0;}
		else if (F02257_001.equalsIgnoreCase("SSRANOFE")){lateFeeTypeId = 0;}
		else if (F02257_001.equalsIgnoreCase("OPTOUT$0")){lateFeeTypeId = 0;}
		else if (F02257_001.equalsIgnoreCase("OPTOUT$5")){lateFeeTypeId = 4;}
		else if (F02257_001.equalsIgnoreCase("OPTOUT29")){lateFeeTypeId = 5;}
		else if (F02257_001.equalsIgnoreCase("LATE29")){lateFeeTypeId = 6;}
		else if (F02257_001.equalsIgnoreCase("OPTTR250")){lateFeeTypeId = 7;}
		else if (F02257_001.equalsIgnoreCase("TR250-35")){lateFeeTypeId = 8;}
		else if (F02257_001.equalsIgnoreCase("OPTOUT37")){lateFeeTypeId = 3;}		
		else if (F02257_001.equalsIgnoreCase("LATE35B")){lateFeeTypeId = 9;}
		else if (F02257_001.equalsIgnoreCase("OPTTIER2")){lateFeeTypeId = 2;}
		else if (F02257_001.equalsIgnoreCase("OPTR235")){lateFeeTypeId = 10;}		
		else if (F02257_001.equalsIgnoreCase("SAFETR35")){lateFeeTypeId = 11;}
		else if (F02257_001.equalsIgnoreCase("SFTR2-35")){lateFeeTypeId = 10;}
		else if (F02257_001.equalsIgnoreCase("LATE35")){lateFeeTypeId = 9;}		
		else if (F02257_001.equalsIgnoreCase("LATEFE45")){lateFeeTypeId = 12;}
		else if (F02257_001.equalsIgnoreCase("TIERED")){lateFeeTypeId = 1;}
		else if (F02257_001.equalsIgnoreCase("TIER2")){lateFeeTypeId = 2;}
		else lateFeeTypeId=99;
		return lateFeeTypeId;
	}
	
	public  double getF30656_001(){
		double maxLateFeeOverrideAmount =99999;
		if(F02258_004==0){maxLateFeeOverrideAmount = 0;}
		else if(F02258_004==4){maxLateFeeOverrideAmount = 5;}
		else if(ArrayUtils.contains(new int[]{5, 6}, F02258_004) ){maxLateFeeOverrideAmount = 29;}
		else if(ArrayUtils.contains(new int[]{1, 8, 9, 10, 11}, F02258_004)){maxLateFeeOverrideAmount = 35;}
		else if(ArrayUtils.contains(new int[]{2, 3, 7}, F02258_004)){maxLateFeeOverrideAmount = 37;}
		else if(ArrayUtils.contains(new int[]{12, 99}, F02258_004) ){maxLateFeeOverrideAmount = 45;}
		// in the requirement it is ####;
		else if(F02258_004==98){maxLateFeeOverrideAmount = 99999;}
		return maxLateFeeOverrideAmount;		
	}
	
	public Account(){

	}
	
	public static void main(String[] args){
		Account a = new Account();
		a.F02258_004= 4;
		a.setCalucatedFields();
		System.out.println(a.F30656_001);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	///// These data are used to calculate F01908_001 (cycle to date FC), which will not affect the MPD result
	//F01908_001 and F01909_001 will be in the output of getuserdata web service
	// (Current Date less First day of Current Cycle F20031:002+1)) - s4 */
	// collrep/coll_getCycleDueDates -- input to SP: cpicsp:CycleCode -- we can stub out this SP
	// today() - cycledate + 1
	/** Days Since Scheduled Cycle -- s4*/
	public  int F01911_001=10;
	/** First day of current cycle */
	//F20031_002 = F01006:009 Acct Cycle Code look-up the Cdate less than current date + 1 day
	public  int F20031_002;
	/** CAP/BLLN_CYCL_CD */
	public  int F01006_009 = 6;
	// /////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////
	// the below are calcuated fields
	// /////////////////////////////////////////////////////////
	// s2a -- second column
	/** Pricing Code --- pricing strategy */
	public  String F01202_003;
	/** Minimum Current Balance */
	public  double F01214_001;
	/** Maximum Current Balance */
	public  double F01215_001;
	/** Pricing Strategy APR Spread Purchases */
	public  double F01208_001;
	/** Pricing Strategy Prime Rate */
	public  double F01209_001;
	/** Pricing Strategy APR Purchase Minimum */
	public  double F01210_001;
	/** Pricing Strategy Late Fee Chargeable */
	public  double F01211_001;
	/** Pricing Strategy OCL Fee Chargeable */
	public  double F01212_001;
	/** pricing Strategy Minimum Due Type Flag */
	public  int F01205_001;
	/** Pricing Strategy Minimum Payment Due Percent */
	public  double F01206_001;
	/** Pricing Strategy Minimum Payment Due Amt */
	public  double F01207_001;
	/** Pricing Strategy APR Spread Cash */
	public  double F01216_001;
	/** Pricing Strategy Prime Rate Cash */
	public  double F01217_001;
	/** Pricing Strategy APR Cash Minimum */
	public  double F01218_001;
	/** Current MPD Description */
	public String F02086_001 ="";
	public String F02086_007;
	
	// s2a -- 3rd column
	/**
	 * Collections Minimum % Due Allowable ---
	 * GlobalVariables/Constants/CollectionsMinDue
	 */
	public  double F01902_001;
	/** Collections Alternate Minimum % Due */
	public  double F01914_001;
	/** Maximum MF/AF Add On Amt */
	public  double F01918_001;
	/** Collections Minimum Add On Amt */
	public  double F01917_001;
	/** Collections Minimum Payment Due Amt Allowable */
	public  double F01915_001;
	/** Maximum Late Fee Allowable */
	public  double F01920_001;

	// /////////////////////////////////////////////////////////
	/** Estimated MPR_CASH */
	public  double F01906_001;
	/** Estimated monthly percentage rate mechandise. MPR_MDC */
	public  double F01904_001;
	/** Estimated DPR_MDC */
	public  double F01903_001;
	/** Estimated DPR_CASH */
	public  double F01905_001;
	/** Estimated CTD_FC_MDC */
	public  double F01908_001;
	/** Estimated CTD_FC_CASH */
	public  double F01909_001;

	/** MPD Add On Amt */
	public  double F02092_001;

	/** Projected Current Balance -- s4 */
	public  double F01921_001;	
	
	// /////////////////////////////////////////////////////////	
	// pricing strategy mpd
	/** Pricing Strategy Forecasted MPD */
	public double F01907_001;
	
	//cure mpd
	/** Cure Forecasted MPD --[Pricing Min Pmt Due] */
	public  double F20034_003;
	/** Estimated Cure Forecasted MPD Description --- [Pricing Min Pmt Due] */
	public  String F01913_001;
	
	//mpd
	/** Forecasted MPD */
	public  double F01900_001;
	/** Estimated Forecasted MPD Description */
	public  String F01912_001;


}
