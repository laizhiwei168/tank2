package com.cyws.tank.manager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.druid.pool.DruidPooledConnection;

public class DBHandleHelper {
	/**
	 * 
	 * @param dbName 数据库连接别名
	 * @param SQLString
	 * @return
	 */
	public List Query(String dbName,String SQLString) {

		List list = null;
		ResultSet rs = null;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DBFactory.getConnection(dbName);
			pstm = conn.prepareStatement(SQLString);
			rs = pstm.executeQuery();
			list = ResultToListMap(rs);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			free(conn, pstm);
		}
		
		return list;
	}
	/**
	 * 
	 * @param dbName 数据库连接别名
	 * @param SQLString
	 * @return
	 */
	public int ExecuteSql(String dbName,String SQLString) {

		int res = 0;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DBFactory.getConnection(dbName);	
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(SQLString);			
			pstm.executeUpdate();
			conn.commit();
			res = 1;

		} catch (SQLException e) {
			try {
				//e.printStackTrace();
				conn.rollback();
				System.out.println("==============DruidPooledConnection  rollback================");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			//e.printStackTrace();
			res = 0;
		}finally {
			free(conn, pstm);
		}
		return res;
	}
	
	/**
	 * 把返回的结果组装成map
	 * @param rs
	 * @return
	 */
	public List ResultToListMap(ResultSet rs) {
		List list = null;

		try {
			list = new ArrayList();
			while (rs.next()) {
				ResultSetMetaData md = rs.getMetaData();
				Map map = new HashMap();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					map.put(md.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			list = null;

		}
		return list;
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 * @param pstm
	 */
	public void free(DruidPooledConnection conn, PreparedStatement pstm) {
		/*
		 * try { if (rs != null&&!rs.isClosed()) { rs.close();
		 * 
		 * } } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		try {
			if (pstm != null) {
				pstm.close();
				pstm=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
				conn=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
