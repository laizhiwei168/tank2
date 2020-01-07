package com.cyws.tank.manager.dbutility;

import java.sql.SQLException;
import java.util.Properties;

//import org.apache.log4j.Logger;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.cyws.tank.utils.common.PropertiesUtil;

public class DbPoolConnection {
	//private static Logger logger = Logger.getLogger(DbPoolConnection.class);

	private static String dbtype = null;
	private static DruidDataSource dds = null;
	/*
	 * static { Properties properties = loadPropertyFile("jdbc.properties"); try
	 * { dds = (DruidDataSource) DruidDataSourceFactory
	 * .createDataSource(properties);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
	public static String getDbType() {
		if (dbtype == null) {
			Properties properties = PropertiesUtil.GetProperties("/resources/jdbc.properties");//resources/jdbc.properties
			dbtype = properties.get("dbType").toString();
		}
		return dbtype;
	}

	public static String getDbType(String dbconfig) {

		Properties properties = PropertiesUtil.GetProperties(dbconfig);
		dbtype = properties.get("dbType").toString();

		return dbtype;
	}

	public static Properties getProperties() {
		Properties properties=PropertiesUtil.GetProperties("/resources/jdbc.properties");
		return properties;
	}

	public static DruidPooledConnection getConnection(String propertiesfile) {
		DruidPooledConnection dpc = null;
		
		if (propertiesfile == null || propertiesfile.equals(""))
			propertiesfile = "/resources/jdbc.properties";
		Properties properties = PropertiesUtil.GetProperties(propertiesfile);
		try {
			if(dds==null){
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
