package tools;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import static tools.Account.*;
import static myconstant.SpringDb.*;
//done
public class S3A {
	private static Logger logger = Logger.getLogger(S3A.class);
	public static void main(String[] args) {	

			F01202_001 = "0SP3";
			F01014_001 = 247;
			overPricingStrategyTable();
			System.out.println(F01214_001);
	}

	public static void process() {
		printInput();
		overPricingStrategyTable();
		setVariables();
		printOutput();
	}
	
	public static void overPricingStrategyTable(){
		String sql = "select top 1 PRICINGSTRATEGY," +
				"MINIMUM_CURRENT_BALANCE ," +
				"MAXIMUM_CURRENT_BALANCE," +
				"APR_SPREAD," +
				"LATE_FEE," +
				"OCL_FEE," +
				"PRIME_RATE ," +
				"Minimum_payment_due_Minimum_amount," +
				"PURCHASE_MINIMUM_APR," +
				"MINIMUM_PAYMENT_DUE_PERCENT ," +
				"MINIMUM_PAYMENT_DUE_CALCULATION_FLAG," +
				"APR_SPREAD_CASH,CASH_PRIME_RATE," +
				"CASH_MINIMUM_APR";
		sql += " from dbo.TBL_PRICING_STRATEGIES where PARENT_PRICING_STRATEGY = (select distinct PARENT_PRICING_STRATEGY from dbo.TBL_PRICING_STRATEGIES where PRICINGSTRATEGY='%s')";
		sql += " and punitive_severity_level <> 99 order by punitive_severity_level, maximum_current_balance desc";
		sql = String.format(sql, F01202_001);
		
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
		F01218_001 = (Double) psInfo.get("CASH_MINIMUM_APR");}
	}
	

	public static void setVariables(){
		F01211_001 = F30656_001;
		if(F01211_001>F01920_001){
			F01211_001 = F01920_001;
		}
	}
	
	public static void printInput(){
		logger.warn("S3A Input ------------------------- ");
		logger.warn("F01202_001:" +  F01202_001);
		printPricingStrategyTableVariables();
		
	}
	public static void printOutput(){
		logger.warn("S3A Output ------------------------- ");
		logger.warn("F01211_001:" +  F01211_001);
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
}
