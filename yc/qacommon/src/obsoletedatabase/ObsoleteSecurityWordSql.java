/**
 * The class is obsolete. However, the queries within the class can be used in related new created classes
 */


//package obsoletedatabase;
//
//import java.sql.*;
//
//import oracle.jdbc.OracleTypes;
//
//public class ObsoleteSecurityWordSql extends DatabaseBase{
//
//	public static void deleteSecurityWord(String accountNumber) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("declare\n");
//		sql.append("i_account_number number :=");
//		sql.append(accountNumber);
//		sql.append(";\n");
//		sql.append("v_action_id number;\n");
//		sql.append("v_word_id   number;\n");
//		sql.append("v_out       varchar2(250);\n");
//		sql.append("v_sql       varchar2(250);\n");
//		sql.append("cursor c_clear is\n");
//		sql.append("select action_id, account_security_word_id\n");
//		sql.append("  from codadmin.account_security_word_action\n");
//		sql.append("  where account_id = (select account_id from codadmin.account where account_number = i_account_number)\n");
//		sql.append("  order by action_id;\n");
//		sql.append("r_clear c_clear%ROWTYPE;\n");
//		sql.append(" \n");
//		sql.append("begin\n");
//		sql.append("   for r_clear in c_clear loop\n");
//		sql.append("       v_action_id := r_clear.action_id;\n");
//		sql.append("       v_word_id   := r_clear.account_security_word_id;\n");
//		sql.append("       v_sql := 'delete from codadmin.data_mod_actn_log_attr where action_id = '||v_action_id;\n");
//		sql.append("       execute immediate v_sql; \n");
//		sql.append("	   \n");
//		sql.append("       v_sql := 'delete from codadmin.data_mod_action where action_id = '||v_action_id;\n");
//		sql.append("       execute immediate v_sql;  \n");
//		sql.append("       v_sql := 'delete from codadmin.action_contact where action_id = '||v_action_id;\n");
//		sql.append("       execute immediate v_sql;  \n");
//		sql.append("       v_sql := 'delete from codadmin.account_security_word_action where account_security_word_id = '||v_word_id;\n");
//		sql.append("       execute immediate v_sql;  \n");
//		sql.append("        v_sql := 'delete from codadmin.action where action_id = '||v_action_id;\n");
//		sql.append("        execute immediate v_sql;\n");
//		sql.append("       v_sql := 'delete from codadmin.account_acct_security_word where account_security_word_id = '||v_word_id;\n");
//		sql.append("       execute immediate v_sql;  \n");
//		sql.append("       v_sql := 'delete from codadmin.account_security_word where account_security_word_id = '||v_word_id;\n");
//		sql.append("       execute immediate v_sql;   \n");
//		sql.append("   end loop;\n");
//		// sql.append("   commit;\n");
//		sql.append("end;\n");
//		callOracle(new String(sql));
//	}
//
//	public static int addSecurityWord(String accountNumber, String effectiveDate) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("DECLARE\n");
//		sql.append("    v_sec_word_id NUMBER;\n");
//		sql.append("    v_action_id   NUMBER;\n");
//		sql.append("    v_sql         VARCHAR2(250);\n");
//		sql.append("    v_date DATE := '" + effectiveDate + "';\n");
//		sql.append("    v_account_id     NUMBER ;\n");
//		sql.append("    v_party_id       NUMBER ;\n");
//		sql.append("    v_account_number VARCHAR2(300) :='" + accountNumber + "';\n");
//		sql.append("    v_step           CHAR(3);\n");
//		sql.append("  BEGIN\n");
//		sql.append("    SELECT account_id, party_id INTO v_account_id, v_party_id FROM account where account_number = v_account_number;\n");
//		sql.append("    \n");
//		sql.append("    aff_api.aff_add_acct_security_wrd(v_account_id -- i_account_id -- 24764502 internal cca\n");
//		sql.append("    ,systimestamp                                  -- i_from_date (effective date)\n");
//		sql.append("    ,118                                           -- i_employee_id\n");
//		sql.append("    ,'CTH'                                         -- i_fdr_code\n");
//		sql.append("    ,1596                                          -- i_fdr_key_id\n");
//		sql.append("    ,'Christine'                                   -- i_agent_first_name\n");
//		sql.append("    ,'Hoffmann'                                    -- i_agent_last_name\n");
//		sql.append("    ,9                                             -- i_data_channel_type_id\n");
//		sql.append("    ,NULL                                          -- i_contact_id\n");
//		sql.append("    ,v_party_id                                    -- i_contact_party_id --- need change manually using party_id\n");
//		sql.append("    ,16                                            -- i_application_party_id\n");
//		sql.append("    ,2                                             -- i_security_hint_id\n");
//		sql.append("    ,NULL                                          -- i_security_hint_oth\n");
//		sql.append("    ,'Add One'                                     -- i_security_word\n");
//		sql.append("    ,v_sec_word_id );\n");
//		sql.append("    ? := v_sec_word_id;\n");
//		sql.append("    dbms_output.put_line('new acct sec id is: '||v_sec_word_id);\n");
//		sql.append("    \n");
//		sql.append("    SELECT action_id\n");
//		sql.append("    INTO v_action_id\n");
//		sql.append("    FROM codadmin.account_security_word_action\n");
//		sql.append("    WHERE account_security_word_id = v_sec_word_id;\n");
//		sql.append("    \n");
//		sql.append("    v_sql := 'UPDATE codadmin.account_security_word SET from_date = '''||v_date||''', created_timestamp = '''||v_date||''', update_timestamp = '''||v_date||''' WHERE account_security_word_id = '||v_sec_word_id;\n");
//		sql.append("    dbms_output.put_line(v_sql);\n");
//		sql.append("    EXECUTE IMMEDIATE v_sql;\n");
//		sql.append("    v_sql := 'UPDATE codadmin.action SET action_date = '''||v_date||''', entered_timestamp = '''||v_date||''' where action_id = '||v_action_id;\n");
//		sql.append("    dbms_output.put_line(v_sql);\n");
//		sql.append("    EXECUTE IMMEDIATE v_sql;    \n");
//		sql.append("    COMMIT;\n");
//		sql.append("  EXCEPTION\n");
//		sql.append("  WHEN OTHERS THEN\n");
//		sql.append("    dbms_output.put_line('step: '||v_step||' '||SQLERRM);\n");
//		sql.append("END;\n");
//		try {
//			Connection conn = getOracleConnection();
//			CallableStatement cs = conn.prepareCall(new String(sql));
//			cs.registerOutParameter(1, OracleTypes.NUMBER);
//			cs.execute();
//			int v_sec_word_id = cs.getInt(1);
//			conn.close();
//			return v_sec_word_id;
//		} catch (SQLException e) {
//			e.printStackTrace();			
//		}
//		return 0;
//	}
//
//}
