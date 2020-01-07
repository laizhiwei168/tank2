package com.cyws.tank.manager.db;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.cyws.tank.manager.common.DBBean;

public class DBFactory {
	public static MysqldbPool gndatamysqldbPool= null;
	public static MysqldbPool tankdatamysqldbPool= null;
	public static MysqldbPool tankdbsqldbPool = null;
	public static DruidPooledConnection getConnection(String DBname){		
		DruidPooledConnection dpc = null;
		if(DBBean.gndata.equals(DBname)){
			if(gndatamysqldbPool==null){
				gndatamysqldbPool=new MysqldbPool();				
			}
			dpc=gndatamysqldbPool.getConnection("/resources/gndatajdbc.properties");
		}else if(DBBean.tankdata.equals(DBname)){
			if(tankdatamysqldbPool==null){
				tankdatamysqldbPool=new MysqldbPool();				
			}
			dpc=tankdatamysqldbPool.getConnection("/resources/tankdatajdbc.properties");
		}else if(DBBean.tankdb.equals(DBname)){
			if(tankdbsqldbPool==null){
				tankdbsqldbPool=new MysqldbPool();
			}
			dpc=tankdbsqldbPool.getConnection("/resources/tankdbjdbc.properties");
		}
		return dpc;
	}
	
}
