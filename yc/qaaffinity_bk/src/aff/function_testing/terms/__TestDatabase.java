package aff.function_testing.terms;

import java.sql.SQLException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import database.DatabaseBase;
import database.OdsSql;
import database.PricingStrategySql;

import applications.BaseTestSuite;

import utilityLib.__ApplicationProperties;

public class __TestDatabase  extends BaseTestSuite {
	
	
	@Test
	public void dummy(){		
		System.out.println("done");
		OdsSql.updateExternalStatus("5432270003000144", "L");
	}
	
//	@Test
//	public void dummy(){		
//		System.out.println("done");
//		OdsSql.updateExternalStatus("5432270003000144", "L");
//	}
//	
//	@Test	
//	public void dummy2(){		
//		System.out.println(PricingStrategySql.getPenaltyPricingSt(1));		
//		System.out.println(PricingStrategySql.getBasePricingSt(2, 2));
//		System.out.println("setup");
//	}
}
