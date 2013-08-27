package mpd;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class S2A {
	private Logger logger = Logger.getLogger(S2A.class);
	private Account a;

	public S2A(Account a){
		this.a = a;
	}
	
	
	public void process() {
		printInput();
		readFromFDR();
		readFromProgramBuddy();
		setDefault();
		printOutput();
	}
	
	public void setDefault() {
		a.F01902_001 = a.collectionsMinDue;
		a.F01914_001 =  a.CollectionsAlternateMinDue;
		a.F01917_001 =  a.CollectionsMinAddOnAmt;
		a.F01918_001 =  a.MaximumMFAFAddOnAmt;
		a.F01915_001 =  a.CollectionsMinPaymentDueAmt;
		a.F01920_001 =  a.MaximumLateFeeAllowable;
	}

	/**
	 * this is stub out in account.java
	 */
	public void readFromFDR() {
	}

	// there are some override
	public void readFromProgramBuddy() {
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
				"MPD_CALC_DESC," +
				"CASH_MINIMUM_APR";
		sql += " from dbo.TBL_PRICING_STRATEGIES ps,dbo.tbl_pricing_strategy_desc des";
		sql += " where ps.pricing_strategy_id= des.pricing_strategy_id and PRICINGSTRATEGY = '%s' and MINIMUM_CURRENT_BALANCE<=%f and MAXIMUM_CURRENT_BALANCE>=%f";
		sql = String.format(sql, a.F01202_001, a.F01014_001, a.F01014_001);
		List<Map<String, Object>> myList = Tools.PROGRAMBUDDY.queryForList(sql);
		if(myList.size()>0){
		Map<String, Object> psInfo = myList.get(0);
		a.F01202_003 = (String) psInfo.get("PRICINGSTRATEGY");
		a.F01214_001 = ((BigDecimal) psInfo.get("MINIMUM_CURRENT_BALANCE")).doubleValue();
		a.F01215_001 = ((BigDecimal) psInfo.get("MAXIMUM_CURRENT_BALANCE")).doubleValue();
		a.F01208_001 = (Double) psInfo.get("APR_SPREAD");
		a.F01209_001 = (Double) psInfo.get("PRIME_RATE");
		a.F01210_001 = (Double) psInfo.get("PURCHASE_MINIMUM_APR");

		a.F01211_001 = ((BigDecimal) psInfo.get("LATE_FEE")).doubleValue();
		a.F01212_001 = ((BigDecimal) psInfo.get("OCL_FEE")).doubleValue();
		a.F01206_001 = (Double) psInfo.get("MINIMUM_PAYMENT_DUE_PERCENT");
		a.F01207_001 = ((BigDecimal) psInfo.get("Minimum_payment_due_Minimum_amount")).doubleValue();
		a.F01205_001 = (Integer) psInfo.get("MINIMUM_PAYMENT_DUE_CALCULATION_FLAG");
		a.F01216_001 = (Double) psInfo.get("APR_SPREAD_CASH");
		a.F01217_001 = (Double) psInfo.get("CASH_PRIME_RATE");
		a.F01218_001 = (Double) psInfo.get("CASH_MINIMUM_APR");
		a.F02086_001 = (String) psInfo.get("MPD_CALC_DESC");
		}
	}
	
	public void printInput(){
		logger.debug("S2A Input ------------------------- ");
		logger.debug("F01202_001:" +  a.F01202_001);
		logger.debug("F01014_001:" +  a.F01014_001);
		logger.debug("F02073_001:" +  a.F02073_001);
		logger.debug("F01003:" +  a.F01003);
		logger.debug("F01016_001:" +  a.F01016_001);
		logger.debug("F02089_001:" +  a.F02089_001);
		logger.debug("F02090_001:" +  a.F02090_001);
		logger.debug("F01049_001:" +  a.F01049_001);
		logger.debug("F30476_001:" +   a.F30476_001);		
		logger.debug("F30656_001:" +   a.F30656_001);
		logger.debug("F02188_001:" +  a.F02188_001);
		
	}
	public void printOutput(){
		logger.debug("S2A Output ------------------------- ");
		logger.debug("F02086_001:" +  a.F02086_001);
		logger.debug("F01902_001:" +  a.F01902_001);
		logger.debug("F01914_001:" +  a.F01914_001);
		logger.debug("F01917_001:" +  a.F01917_001);		
		logger.debug("F01915_001:" +  a.F01915_001);
		logger.debug("F01920_001:" +  a.F01920_001);
		logger.debug("F01202_003:" +  a.F01202_003);		
		printPricingStrategyTableVariables();
		logger.debug("");
	}
	
	
	
	public void printPricingStrategyTableVariables(){
		logger.debug("--pricing strateby variables");
		logger.debug("F01214_001:" +  a.F01214_001);
		logger.debug("F01215_001:" +  a.F01215_001);
		logger.debug("F01208_001:" +  a.F01208_001);
		logger.debug("F01209_001:" +  a.F01209_001);
		logger.debug("F01210_001:" +  a.F01210_001);
		logger.debug("F01211_001:" +  a.F01211_001);
		logger.debug("F01212_001:" +  a.F01212_001);
		logger.debug("F01206_001:" +  a.F01206_001);
		logger.debug("F01207_001:" +  a.F01207_001);
		logger.debug("F01205_001:" +  a.F01205_001);
		logger.debug("F01216_001:" +  a.F01216_001);
		logger.debug("F01217_001:" +  a.F01217_001);
		logger.debug("F01218_001:" +  a.F01218_001);
		logger.debug("F02086_001:" +  a.F02086_001);		
	}
	
	public static  void main(String[] args) {
		Account a = new Account();
		S2A s2a= new S2A(a);		
		a.F01202_001 = "0SP3";
		a.F01014_001 = 247;
		s2a.process();
		s2a.logger.debug(a.F01202_003);
	}

	
}
