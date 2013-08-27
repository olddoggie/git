package tools;

import static tools.Account.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class S2A {

	private static Logger logger = Logger.getLogger(S2A.class);
	public static void main(String[] args) {
		F01202_001 = "0SP3";
		F01014_001 = 247;
		readFromProgramBuddy();
		logger.warn(F01214_001);
	}

	static final double collectionsMinDue = 0.0265;
	static final double CollectionsAlternateMinDue = 0.035;
	static final double CollectionsMinAddOnAmt = 10;
	static final double MaximumMFAFAddOnAmt = 4.59;
	static final double CollectionsMinPaymentDueAmt = 0;
	static final double MaximumLateFeeAllowable = 35;
	public static void setDefault() {
		F01902_001 = collectionsMinDue;
		F01914_001 = CollectionsAlternateMinDue;
		F01917_001 = CollectionsMinAddOnAmt;
		F01918_001 = MaximumMFAFAddOnAmt;
		F01915_001 = CollectionsMinPaymentDueAmt;
		F01920_001 = MaximumLateFeeAllowable;
	}

	/**
	 * this is stub out in account.java
	 */
	public static void readFromFDR() {
	}

	// there are some override
	public static void readFromProgramBuddy() {
		/*
		 * select PRICINGSTRATEGY ,MINIMUM_CURRENT_BALANCE
		 * ,MAXIMUM_CURRENT_BALANCE ,APR_SPREAD ,PRIME_RATE
		 * ,PURCHASE_MINIMUM_APR ,LATE_FEE ,OCL_FEE
		 * ,MINIMUM_PAYMENT_DUE_MINIMUM_AMOUNT ,MINIMUM_PAYMENT_DUE_PERCENT
		 * ,MINIMUM_PAYMENT_DUE_CALCULATION_FLAG ,APR_SPREAD_CASH
		 * ,CASH_PRIME_RATE ,CASH_MINIMUM_APR from dbo.TBL_PRICING_STRATEGIES
		 * where PRICINGSTRATEGY='0SP3'and MINIMUM_CURRENT_BALANCE<=360 and
		 * MAXIMUM_CURRENT_BALANCE>=360
		 */
		String sql = "select PRICINGSTRATEGY," +
				"MINIMUM_CURRENT_BALANCE ," +
				"MAXIMUM_CURRENT_BALANCE," +
				"APR_SPREAD," +
				"LATE_FEE," +
				"OCL_FEE," +
				"PRIME_RATE ," +
				"PURCHASE_MINIMUM_APR," +
				"MINIMUM_PAYMENT_DUE_PERCENT ," +
				"Minimum_payment_due_Minimum_amount ," +
				"MINIMUM_PAYMENT_DUE_CALCULATION_FLAG," +
				"APR_SPREAD_CASH," +
				"CASH_PRIME_RATE," +
				"CASH_MINIMUM_APR";
		sql += " from dbo.TBL_PRICING_STRATEGIES";
		sql += " where PRICINGSTRATEGY = '%s' and MINIMUM_CURRENT_BALANCE<=%f and MAXIMUM_CURRENT_BALANCE>=%f";
		sql = String.format(sql, F01202_001, F01014_001, F01014_001);
		List<Map<String, Object>> myList = Tools.PROGRAMBUDDY.queryForList(sql);
		if(myList.size()>0){
		Map<String, Object> psInfo = myList.get(0);
		F01202_003 = (String) psInfo.get("PRICINGSTRATEGY");
		F01214_001 = ((BigDecimal) psInfo.get("MINIMUM_CURRENT_BALANCE")).doubleValue();
		F01215_001 = ((BigDecimal) psInfo.get("MAXIMUM_CURRENT_BALANCE")).doubleValue();
		F01208_001 = (Double) psInfo.get("APR_SPREAD");
		F01209_001 = (Double) psInfo.get("PRIME_RATE");
		F01210_001 = (Double) psInfo.get("PURCHASE_MINIMUM_APR");

		F01211_001 = ((BigDecimal) psInfo.get("LATE_FEE")).doubleValue();
		F01212_001 = ((BigDecimal) psInfo.get("OCL_FEE")).doubleValue();
		F01206_001 = (Double) psInfo.get("MINIMUM_PAYMENT_DUE_PERCENT");
		F01207_001 = ((BigDecimal) psInfo.get("Minimum_payment_due_Minimum_amount")).doubleValue();
		F01205_001 = (Integer) psInfo.get("MINIMUM_PAYMENT_DUE_CALCULATION_FLAG");
		F01216_001 = (Double) psInfo.get("APR_SPREAD_CASH");
		F01217_001 = (Double) psInfo.get("CASH_PRIME_RATE");
		F01218_001 = (Double) psInfo.get("CASH_MINIMUM_APR");
		}
	}

	public static void printInput(){
		logger.warn("S2A Input ------------------------- ");
		logger.warn("F01202_001:" +  F01202_001);
		logger.warn("F01014_001:" +  F01014_001);
		logger.warn("F02073_001:" +  F02073_001);
		logger.warn("F01003:" +  F01003);
		logger.warn("F01016_001:" +  F01016_001);
		logger.warn("F02089_001:" +  F02089_001);
		logger.warn("F02090_001:" +  F02090_001);
		logger.warn("F01049_001:" +  F01049_001);
		logger.warn("F30476_001:" +  F30476_001);		
		logger.warn("F30656_001:" +  F30656_001);
		logger.warn("F02188_001:" +  F02188_001);
		
	}
	public static void printOutput(){
		logger.warn("S2A Output ------------------------- ");
		logger.warn("F01902_001:" +  F01902_001);
		logger.warn("F01914_001:" +  F01914_001);
		logger.warn("F01917_001:" +  F01917_001);		
		logger.warn("F01915_001:" +  F01915_001);
		logger.warn("F01920_001:" +  F01920_001);
		logger.warn("F01202_003:" +  F01202_003);
		printPricingStrategyTableVariables();
		logger.warn("");
	}
	
	
	
	public static void printPricingStrategyTableVariables(){
		logger.warn("--pricing strateby variables");
		logger.warn("F01214_001:" +  F01214_001);
		logger.warn("F01215_001:" +  F01215_001);
		logger.warn("F01208_001:" +  F01208_001);
		logger.warn("F01209_001:" +  F01209_001);
		logger.warn("F01210_001:" +  F01210_001);
		logger.warn("F01211_001:" +  F01211_001);
		logger.warn("F01212_001:" +  F01212_001);
		logger.warn("F01206_001:" +  F01206_001);
		logger.warn("F01207_001:" +  F01207_001);
		logger.warn("F01205_001:" +  F01205_001);
		logger.warn("F01216_001:" +  F01216_001);
		logger.warn("F01217_001:" +  F01217_001);
		logger.warn("F01218_001:" +  F01218_001);
	}
	
	
	public static void process() {
		printInput();
		readFromFDR();
		readFromProgramBuddy();
		setDefault();
		printOutput();

	}

}
