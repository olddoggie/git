package com.onefbusa.op.util;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class OPSpringDB {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("op_datasource.xml");
	public static final JdbcTemplate opDB = (JdbcTemplate) context.getBean("opJdbcTemplate");	

    public static  List<Map<String, Object>> select(String sql){
        //http://www.iteye.com/topic/53526
        // List rows = opJdbcTemplate.queryForList("SELECT * FROM role");
        
        List<Map<String, Object>> rows = opDB.queryForList(sql);
        //Iterator it = rows.iterator();
        //Map dataMap = (Map) it.next();
        
        return rows;

    }

    
    public static void delete(String sql){
    	opDB.execute(sql);
    }
	
    public static void main(String[] args){

        String sql = "SELECT * FROM role";

        List<Map<String, Object>> users = select(sql);

        for (Object role : users) {
            System.out.println(((Map)role).get("id"));
            System.out.println(((Map)role).get("enable"));
            System.out.println(((Map)role).get("name"));
        }

    }
	
//	    while(it.hasNext()) {  
//            Map userMap = (Map) it.next();  
//            System.out.print(userMap.get("id") + "\t");  
//            System.out.print(userMap.get("enable") + "\t");  
//            System.out.print(userMap.get("name") + "\t");  
//        }
//	    
	    
	
//	public static boolean callOdsRpc(String strSql) {
//		boolean flag = false;
//		try {
//			ODS.execute(strSql);
//		} catch (Exception e) {
//			if (e.getMessage().contains("CMPLT")) {
//				flag = true;
//			}
//		}
//		return flag;
//	}
	

}
