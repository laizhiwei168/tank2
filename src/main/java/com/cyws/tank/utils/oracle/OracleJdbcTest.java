package com.cyws.tank.utils.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.cyws.tank.utils.common.PropertiesUtil;

public class OracleJdbcTest {
    //数据库连接对象
    private static Connection conn = null;
    private static DruidPooledConnection dpconn=null;
     
    private static String driver = "oracle.jdbc.driver.OracleDriver"; //驱动
     
    private static String url = "jdbc:oracle:thin:@//10.33.81.104:1521/s2g1_sscdb2_srv"; //连接字符串
     
    private static String username = "tanks"; //用户名
     
    private static String password = "tanks#2270"; //密码
     
     
    // 获得连接对象
    private static synchronized DruidPooledConnection getdpConn(){
        if(dpconn == null){
        	DruidDataSource dds =null;
            try {
                Class.forName(driver);
                Properties properties =new Properties();
                properties.put("url", url);
                properties.put("username", username);
                properties.put("password", password);
                 dds  = (DruidDataSource) DruidDataSourceFactory
						.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
            	dpconn = dds.getConnection();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
        }
        return dpconn;
    }
    
     // 获得连接对象
    private static synchronized Connection getConn(){
        if(conn == null){
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    
     
    //执行查询语句
    public List query(String sql, boolean isSelect) throws SQLException{
        PreparedStatement pstmt;
        List list = null;
        try {
        	list = new ArrayList();
            pstmt = getdpConn().prepareStatement(sql);
            //建立一个结果集，用来保存查询出来的结果
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	
            	ResultSetMetaData md = rs.getMetaData();
				Map map = new HashMap();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					map.put(md.getColumnLabel(i), rs.getObject(i));					
				}
				list.add(map);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
     
    public void query(String sql) throws SQLException{
        PreparedStatement pstmt;
        pstmt = getConn().prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
    }
     
     
    //关闭连接
    public void close(){
        try {
            getConn().close();
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
}