package com.cyws.tank.manager.db;

import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.cyws.tank.utils.common.PropertiesUtil;

public class MysqldbPool {
	private  String dbtype = null;
	private  DruidDataSource dds = null;
	
	public  DruidPooledConnection getConnection(String propertiesfile) {
	      DruidPooledConnection dpc = null;
			if (propertiesfile == null || propertiesfile.equals(""))
				return null;
			
			try {
				if(dds==null){
				Properties properties = PropertiesUtil.GetProperties(propertiesfile);
				dds = (DruidDataSource) DruidDataSourceFactory
						.createDataSource(properties);
				}
				dbtype = dds.getDbType();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				dpc = dds.getConnection();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return dpc;
		}
}
