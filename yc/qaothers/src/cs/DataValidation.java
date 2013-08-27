package cs;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilityLib.Tools;
import static myconstant.SpringDb.*;
public class DataValidation{
	int updateCount=0, addCount=0, deleteCount=0;
//	static ApplicationContext context = new ClassPathXmlApplicationContext("datasource.xml");
//	public static final JdbcTemplate CS = (JdbcTemplate) context.getBean("csJdbcTemplate");
	@DataProvider(name = "school_table")
	private Object[][] getDataTable() {
		String xlFilePath = "cs820_database_updates.xls";
		String sheetName = "data";
		String tableName = "school_table";
		String[][] datatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName, 0);
		for (String[] row:datatTable){
			if(row[1].equals("471823")||row[1].equals("471828")||row[1].equals("333837")||row[1].equals("334064")||row[1].matches("00.*")){
				System.out.println(row[1]);
			}else{
				if(row[7].equals("A")){
					addCount++;
				}else if(row[7].equals("U")){
					updateCount++;
				}else{
					deleteCount++;
				}
			}
	
		
		}
		System.out.println("updateCount--" + updateCount);
		System.out.println("addCount--" + addCount);
		System.out.println("deleteCount--" + deleteCount);
		return datatTable;
	}

	@Test(dataProvider = "school_table")
	public void dataTest(String execution, String code,String name, String city, String state, String country, String type,
			String change_code, String change_date) {
		//System.out.println(code + name+city+state+country+school_type+change_code+change_date);
		String sql="select hs_code,hs_name, hs_city, hs_state, hs_country,hs_type ,thru_date from csadmin.high_school_list where hs_code ="  + code;
		Map<String, Object> m = CS.queryForMap(sql);
		String dCode = (String) m.get("hs_code");		
		String dType = (String) m.get("hs_type");
		String dName = (String) m.get("hs_name");		
		String dCity = (String) m.get("hs_city");
		String dState;
		if(m.get("hs_state")==null){
			dState="";
		}else{
			dState = (String) m.get("hs_state");
		}

		String dCountry;
		if(m.get("hs_country")==null){
			 dCountry="";
		}else{
			dCountry = (String) m.get("hs_country");
		}
		//String dCountry = (String) m.get("hs_country");
		Assert.assertEquals(dName.trim(),name.trim());
		Assert.assertEquals(dCity.trim(),city.trim());
		Assert.assertEquals(dState.trim(),state.trim());
		Assert.assertEquals(dCountry.trim(),country.trim());		
		Assert.assertEquals(dType.trim(),type.trim());		
		
	}
	//@Test
	public void testThruDate(){
		//String sql = "select count(*) from csadmin.high_school_list where thru_date=to_date( '05/10/2013','mm-dd-yyyy')";
		String sql="select hs_code,hs_name, hs_city, hs_state, hs_country,hs_type ,thru_date from csadmin.high_school_list where hs_code =12137" ;
		Map<String, Object> m = CS.queryForMap(sql);
		String dThruDate = (String) m.get("thru_date");
		System.out.println(dThruDate);
	}

}
