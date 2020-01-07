package com.cyws.tank.utils.oracle;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ConnOracle {
    public static void main(String[] args) throws SQLException {
        OracleJdbcTest test = new OracleJdbcTest();
        /*try {
            test.query("drop table student");
        } catch (SQLException e) {}*/
         
       // test.query("create table student(id int, name nchar(20))");
         
       //test.query("insert into student values(1,'zhangsan')");
         
       // test.query("insert into student values(2,'lisi')");
         
        List<Map<String, Object>> ls_gx= test.query("select * from ta_data_now_w where EQUIPMENT_ID='15200395' and DA_UP >= to_date('2014-06-01','yyyy-mm-dd') ", true);      
		for(Map<String, Object> map:ls_gx){        
			for(String key:map.keySet()){
				System.out.print(key+"="+map.get(key)+" , ");
			}
			System.out.println();
		}
         
        test.close();
    }
}