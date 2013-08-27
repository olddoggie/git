/**
 * The class is obsolete. However, the queries within the class can be used in related new created classes
 */



//package obsoletedatabase;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import frameLib.MyException;
//
//public class ObsoletePricingStrategySql extends DatabaseBase {
//
//	final static String APR_Pen_GRP = "(2,4,22)";
//	final static String APR_Pen_Trans_GRP = "(5,6,7,10,11,12,13,14,15)";
//
//	/**
//	 * @param SameCashLevelCount
//	 *            The count is 2 when a pricing strategy's cash_apr_level1 and
//	 *            cash_apr_level2 is the same.cash_aprl_level is mapped to
//	 *            cash_minimum_apr
//	 * @param PricingStCount
//	 *            When a parent pricing strategy has 3 pricing strategy. The
//	 *            count is 3.
//	 * @return A base pricing strategy with different PricingStCount or SameCashLevelCount
//	 * @throws SQLException
//	 * @throws ClassNotFoundException	 */
//	public static String getBasePricingSt(int SameCashLevelCount, int PricingStCount) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("select top 1 PRICINGSTRATEGY \n");
//		sql.append("from [ProgramBuddy].[dbo].[TBL_PRICING_STRATEGIES] \n");
//		sql.append("where PRICINGSTRATEGY in ( \n");
//		sql.append("select PRICINGSTRATEGY \n");
//		sql.append("from [ProgramBuddy].[dbo].[TBL_PRICING_STRATEGIES] \n");
//		sql.append("where punitive_severity_level in (0,3,20,16,23) \n");
//		sql.append("group by PRICINGSTRATEGY,punitive_severity_level,cash_minimum_apr \n");
//		sql.append("having count(*) = " + SameCashLevelCount + ") \n");
//		sql.append("group by PRICINGSTRATEGY having count(*)=" + PricingStCount + " \n");
//		sql.append("order by PRICINGSTRATEGY \n");
//
//		Connection conn = getMsSqlConnection("AffinityNotes");
//		Statement cs;
//		try {
//			cs = conn.createStatement();
//			ResultSet rs = cs.executeQuery(new String(sql));
//			rs.next();
//			String ps = rs.getString(1);
//			conn.close();
//			return ps;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			throw new MyException("getBasePricingSt(" + SameCashLevelCount + ", " + PricingStCount + ")", e);
//		}
//
//	}
//
//	public static String getPenaltyPricingSt(int PricingStCount) {
//		String sql = getPricingstGroupSql(APR_Pen_GRP, PricingStCount);
//		Connection conn = getMsSqlConnection("AffinityNotes");
//		Statement cs;
//		try {
//			cs = conn.createStatement();
//			ResultSet rs = cs.executeQuery(sql);
//			rs.next();
//			String ps = rs.getString(1);
//			conn.close();
//			return ps;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			throw new MyException("getPenaltyPricingSt(" + PricingStCount + ")", e);
//		}
//
//	}
//
//	private static String getPricingstGroupSql(String pricingStGroup, int PricingStCount) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("select top 1 PRICINGSTRATEGY \n");
//		sql.append("from [ProgramBuddy].[dbo].[TBL_PRICING_STRATEGIES] \n");
//		sql.append("where PRICINGSTRATEGY in \n");
//		sql.append("(select PRICINGSTRATEGY \n");
//		sql.append("from [ProgramBuddy].[dbo].[TBL_PRICING_STRATEGIES] \n");
//		sql.append("where punitive_severity_level in " + pricingStGroup + ") \n");
//		sql.append("group by PRICINGSTRATEGY having count(*)=" + PricingStCount + " \n");
//		sql.append("order by PRICINGSTRATEGY \n");
//		return new String(sql);
//	}
//
//}
