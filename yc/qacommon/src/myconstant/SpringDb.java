package myconstant;

import static myconstant.FsConfig.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringDb {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("datasource.xml");
	public static final JdbcTemplate AFFINITY_NOTES = (JdbcTemplate) context.getBean("affinityNotesJdbcTemplate");	
	public static final JdbcTemplate AUTOPAY = (JdbcTemplate) context.getBean("autopayJdbcTemplate");
	public static final JdbcTemplate PROGRAMBUDDY = (JdbcTemplate) context.getBean("programBuddyJdbcTemplate");	
	public static final JdbcTemplate COD = (JdbcTemplate) context.getBean("codJdbcTemplate");
	public static final JdbcTemplate ODS = (JdbcTemplate) context.getBean("odsJdbcTemplate");
	public static final JdbcTemplate CS = (JdbcTemplate) context.getBean("csJdbcTemplate");
	
	public static void main(String[] args) {
//		String sqlStr = "update account set party_id =" + INACTIVE_PARTY_ID + " where party_id = " + ACTIVE_PARTY_ID + ";";
//		COD.update(sqlStr);
//		sqlStr = "update party_account_role set party_id =" + INACTIVE_PARTY_ID + " where party_id = " + ACTIVE_PARTY_ID + ";";
//		COD.execute(sqlStr);
		
		
//		StringBuilder sql = new StringBuilder();
//		sql.append("declare\n");
//		sql.append("i_account_id number;\n");
//		sql.append("cursor my_cursor is select account_id from codadmin.account where party_id = '" + ACTIVE_PARTY_ID
//				+ "' and account_type_id =2; \n");
//		sql.append("begin \n");
//		sql.append("if not my_cursor%isopen then \n");
//		sql.append("open my_cursor; \n");
//		sql.append("end if; \n");
//		sql.append("loop \n");
//		sql.append("fetch my_cursor into i_account_id; \n");
//		sql.append("exit when my_cursor%NOTFOUND; \n");
//		sql.append("codadmin.legal_docs_api.fscm_agree_to_legal_doc(i_account_id,3,1,sysdate); \n");
//		sql.append("end loop; \n");
//		sql.append("end;");
//		COD.execute(sql.toString());
//		System.out.println("done");
		
		
		String strSql = "EXECUTE FRPC 'CNONMON', '" + "4017240003001042" + "', 'CS', ' '";		
		callOdsRpc(strSql);
		//int autopayNotesCount = affinityNotes.queryForInt("select count(*) from cisnote where mem_cardhdlr='4000000000000119'");
		//int autopayNotesCount = affinityNotes.queryForInt("select count(*) from cisnote where mem_cardhdlr= ?", new Object[]{"4000000000000119"});
//		String lname1 = (String) AUTOPAY.queryForList("select * from autopay where location = ?", "DD").get(2).get("lname1");
//		System.out.println("lname1 is " + lname1);
//		lname1 = (String) AUTOPAY.queryForList("select * from autopay where location = ? and payamt = ?", "DD", 12).get(0).get("lname1");
//		System.out.println("lname1 is " + lname1);
//		lname1 = (String) AUTOPAY.queryForList("select * from autopay where location = ?", "DD").get(2).get("lname1");
//		System.out.println("lname1 is " + lname1);
	}
	
	public static boolean callOdsRpc(String strSql) {
		boolean flag = false;
		try {
			ODS.execute(strSql);
		} catch (Exception e) {
			if (e.getMessage().contains("CMPLT")) {
				flag = true;
			}
		}
		return flag;
	}
	

}
