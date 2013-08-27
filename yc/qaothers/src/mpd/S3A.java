package mpd;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

//done
public class S3A {
	private Account a;
	private Logger logger = Logger.getLogger(S3A.class);

	public S3A(Account a){
		this.a = a;
	}

	public void process() {
		printInput();
		overPricingStrategyTable();
		setVariables();
		printOutput();
	}
	
	public void overPricingStrategyTable(){
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
				"MPD_CALC_DESC," +
				"CASH_MINIMUM_APR";
		sql += " from dbo.TBL_PRICING_STRATEGIES ps,dbo.tbl_pricing_strategy_desc des where ps.pricing_strategy_id= des.pricing_strategy_id and PARENT_PRICING_STRATEGY = (select distinct PARENT_PRICING_STRATEGY from dbo.TBL_PRICING_STRATEGIES where PRICINGSTRATEGY='%s')";
		sql += " and punitive_severity_level <> 99 order by punitive_severity_level desc, maximum_current_balance desc";
		sql = String.format(sql, a.F01202_001);
		
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
		a.F02086_007 = (String) psInfo.get("MPD_CALC_DESC");
		}
	}

	
	public void setVariables(){
		a.F01211_001 = a.F30656_001;
		if(a.F01211_001>a.F01920_001){
			a.F01211_001 = a.F01920_001;
		}		
		a.F01912_001=a.F02086_007;
		a.F01913_001=a.F02086_007;
	}
	
	public void printInput(){
		logger.debug("S3A Input ------------------------- ");
		logger.debug("F01202_001:" +  a.F01202_001);
		printPricingStrategyTableVariables();
		
	}
	public void printOutput(){
		logger.debug("S3A Output ------------------------- ");
		logger.debug("F01912_001:" +  a.F01912_001);
		logger.debug("F01913_001:" +  a.F01913_001);
		logger.debug("F01211_001:" +  a.F01211_001);
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
		logger.debug("F02086_007:" +  a.F02086_007);		
	}
	
	public void main(String[] args) {	

		a.F01202_001 = "0SP3";
		a.F01014_001 = 247;
		overPricingStrategyTable();
		System.out.println(a.F01214_001);
}
}
