package com.cyws.tank.manager.dbutility;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.alibaba.druid.pool.DruidPooledConnection;

//import com.alibaba.fastjson.JSON;
public class DbHelper {

	public  String dbconfig = null;
	public  String dbType = null;

	public DbHelper() {
		if (dbType == null || dbType.equals(""))
			dbType = DbPoolConnection.getDbType();
	}

	public DbHelper(String dbconfig) {
		this.dbconfig = dbconfig;
		dbType = DbPoolConnection.getDbType(dbconfig);
	}

	public void setDbconfig(String _dbconfig) {
		dbconfig = _dbconfig;
	}

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

	public List ResultToListMap(ResultSet rs) {
		List list = null;

		try {
			list = new ArrayList();
			while (rs.next()) {
				ResultSetMetaData md = rs.getMetaData();
				Map map = new HashMap();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					map.put(md.getColumnLabel(i), rs.getObject(i));//.toLowerCase()
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

	// / <summary>
	// / 执行查询语句，返回List
	// / </summary>
	// / <param name="SQLString">查询语句</param>
	// / <returns>DataSet</returns>
	public List Query(String SQLString) {

		List list = null;
		ResultSet rs = null;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {

			conn = DbPoolConnection.getConnection(dbconfig);
			pstm = conn.prepareStatement(SQLString);
			rs = pstm.executeQuery();
			list = ResultToListMap(rs);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();
		} finally {
			free(conn, pstm);
		}
		
		return list;
	}

	public List Query(String SQLString, Object... paramters) {
		ResultSet rs = null;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		List list = null;
		try {

			conn = DbPoolConnection.getConnection(dbconfig);
			pstm = conn.prepareStatement(SQLString);
			for (int i = 0; i < paramters.length; i++) {
				// if(paramters[i].getClass().getSimpleName().equals("String")){
				// pstm.setString(i + 1, paramters[i].toString());
				// }else
				pstm.setObject(i + 1, paramters[i]);
			}
			rs = pstm.executeQuery();
			list = ResultToListMap(rs);
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();
		} finally {
			free(conn, pstm);
		}

	
	

		return list;
	}

	// / <summary>
	// / 执行一条计算查询结果语句，返回查询结果（object）。
	// / </summary>
	// / <param name="SQLString">计算查询结果语句</param>
	// / <returns>查询结果（object）</returns>
	public Object GetSingle(String SQLString) {

		Object result = null;
		ResultSet rs = null;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DbPoolConnection.getConnection(dbconfig);
			pstm = conn.prepareStatement(SQLString);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();
		} finally {
			free(conn, pstm);
		}
		return result;
	}

	public Object GetSingle(String SQLString, Object... paramters) {

		Object result = null;
		ResultSet rs = null;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DbPoolConnection.getConnection(dbconfig);
			pstm = conn.prepareStatement(SQLString);
			for (int i = 0; i < paramters.length; i++) {
				pstm.setObject(i + 1, paramters[i]);
			}
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();
		} finally {
			free(conn, pstm);
		}
		return result;
	}

	// / <summary>
	// / 执行SQL语句，返回影响的记录数
	// / </summary>
	// / <param name="SQLString">SQL语句</param>
	// / <returns>影响的记录数</returns>
	public int ExecuteSql(String SQLString) {

		int res = 0;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DbPoolConnection.getConnection(dbconfig);	
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(SQLString);			
			pstm.executeUpdate();
			conn.commit();
			res = 1;

		} catch (SQLException e) {
			try {
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

	public int ExecuteSql(String SQLString, Object... paramters) {

		int res = 0;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DbPoolConnection.getConnection(dbconfig);
			pstm = conn.prepareStatement(SQLString);
			if (paramters != null) {
				for (int i = 0; i < paramters.length; i++) {
					if (paramters[i] != null)
						pstm.setObject(i + 1, paramters[i]);
				}
			}
			pstm.executeUpdate();
			pstm.close();
			conn.close();

			res = 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();
			res = 0;
		}
		return res;
	}

	public Object insertWithReturnPrimeKey(String SQLString,
			Object... paramters) {

		Object res = null;
		ResultSet rs = null;
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		try {
			conn = DbPoolConnection.getConnection(dbconfig);
			pstm = conn.prepareStatement(SQLString,
					Statement.RETURN_GENERATED_KEYS);
			if (paramters != null) {
				for (int i = 0; i < paramters.length; i++) {
					if (paramters[i] != null)
						pstm.setObject(i + 1, paramters[i]);
				}
			}

			pstm.execute();
			rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				res = rs.getObject(1);
			}
			rs.close();
			pstm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();

		}
		return res;
	}

	// / <summary>
	// / 根据条件返回查询到的List
	// / </summary>
	// / <param name="TableName">表名</param>
	// / <param name="conditions">条件</param>
	// / <returns></returns>
	public List FindByConditions(Object obj, String conditions) {
		Common<Object> cobi = new Common<Object>();
		if (conditions == null)
			conditions = "1=1";
		if (dbType.equals("mysql"))
			conditions = conditions.replace("lower(nvl(", "lower(ifnull(");
		if (dbType.equals("sqlserver"))
			conditions = conditions.replace("lower(nvl(", "lower(ISNULL(");
		String sql = "select * from "
				+ cobi.GetModelInfo(obj)[0]
				+ (conditions.trim().length() <= 0 ? "" : " where "
						+ conditions);
		return Query(sql);
	}

	// / <summary>
	// / 分页查询，返回当前页的DataTable
	// / </summary>
	// / <param name="TableName">表名@param
	// / <param name="AutoID">自动增长列@param
	// / <param name="start">起始记录号@param
	// / <param name="limit">分页大小@param
	// / <param name="conditions">附加条件</param>
	// / <returns></returns>
	public List FindByPage(Object obj, String start, String limit,
			String conditions) {

		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		String sql = "select  * from " + ModelInfo[0] + " order by "
				+ ModelInfo[1] + " desc limit " + start + "," + limit;
		if (conditions != null && !conditions.trim().equals("")) {
			if (dbType.equals("mysql")) {
				conditions = conditions.replace("lower(nvl(", "lower(ifnull(");
				sql = "select  * from " + ModelInfo[0] + " where   "
						+ conditions + " order by " + ModelInfo[1]
						+ " desc limit " + start + "," + limit;
			}

			if (dbType.equals("sqlserver")) {
				conditions = conditions.replace("lower(nvl(", "lower(ISNULL(");
				sql = "select top " + limit + " * from " + ModelInfo[0]
						+ " where " + ModelInfo[1] + " not in(select top "
						+ start + " " + ModelInfo[1] + " from " + ModelInfo[0]
						+ "  where " + conditions + " order by " + ModelInfo[1]
						+ " desc) and  (" + conditions + ")  order by "
						+ ModelInfo[1] + " desc";
			}
			if (dbType.equals("oracle")) {
				if (start == null || start == "")
					start = "0";
				if (limit == null || limit == "")
					limit = "10000";
				int begin = Integer.parseInt(start) + 1;
				int end = Integer.parseInt(start) + Integer.parseInt(limit);
				if (!conditions.toLowerCase().contains("order by")) {
					sql = "select * from(select a_a.*,ROWNUM rn from(select * from "
							+ ModelInfo[0]
							+ " where "
							+ conditions
							+ " order by "
							+ ModelInfo[1]
							+ " desc) a_a) where rn between "
							+ begin
							+ " and "
							+ end;
				} else {
					sql = "select * from(select a_a.*,ROWNUM rn from(select * from "
							+ ModelInfo[0]
							+ " where "
							+ conditions
							+ ") a_a) where rn between "
							+ begin
							+ " and "
							+ end;
				}
			}

		} else {
			if (dbType.equals("sqlserver")) {

				sql = "select top " + limit + " * from " + ModelInfo[0]
						+ " where " + ModelInfo[1] + " not in(select top "
						+ start + " " + ModelInfo[1] + " from " + ModelInfo[0]
						+ " order by " + ModelInfo[1] + " desc)   order by "
						+ ModelInfo[1] + " desc";
			}
			if (dbType.equals("oracle")) {
				if (start == null || start == "")
					start = "0";
				if (limit == null || limit == "")
					limit = "10000";
				int begin = Integer.parseInt(start) + 1;
				int end = Integer.parseInt(start) + Integer.parseInt(limit);

				sql = "select * from(select a_a.*,ROWNUM rn from(select * from "
						+ ModelInfo[0]
						+ "  "
						+ ") a_a) where rn between "
						+ begin + " and " + end;

			}

		}
		return Query(sql);
	}

	public List FindByPage(Object obj, String start, String limit,
			String conditions, String sortFileld, String sortDirection) {

		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		String sortStr = "";
		if (sortDirection == null || sortDirection.equals("null"))
			sortDirection = "";
		if (sortFileld != null && !sortFileld.equals(""))
			sortStr += " order by " + sortFileld + " " + sortDirection;
		String sql = "select  * from " + ModelInfo[0] + " " + sortStr
				+ "  limit " + start + "," + limit;
		if (conditions != null && !conditions.trim().equals("")) {
			if (dbType.equals("mysql")) {
				conditions = conditions.replace("lower(nvl(", "lower(ifnull(");
				sql = "select  * from " + ModelInfo[0] + " where   "
						+ conditions + "" + sortStr + "  limit " + start + ","
						+ limit;
			}

			if (dbType.equals("sqlserver")) {
				conditions = conditions.replace("lower(nvl(", "lower(ISNULL(");
				sql = "select top " + limit + " * from " + ModelInfo[0]
						+ " where " + ModelInfo[1] + " not in(select top "
						+ start + " " + ModelInfo[1] + " from " + ModelInfo[0]
						+ "  where " + conditions + "  " + sortStr + " "
						+ ") and  (" + conditions + ")   " + sortStr;
			}

			if (dbType.equals("oracle")) {
				if (start == null || start == "")
					start = "0";
				if (limit == null || limit == "")
					limit = "10000";
				int begin = Integer.parseInt(start) + 1;
				int end = Integer.parseInt(start) + Integer.parseInt(limit);
				sql = "select * from(select a_a.*,ROWNUM rn from(select * from "
						+ ModelInfo[0]
						+ " where "
						+ conditions
						+ sortStr
						+ ") a_a) where rn between " + begin + " and " + end;
			}
		} else {
			if (dbType.equals("sqlserver")) {

				sql = "select top " + limit + " * from " + ModelInfo[0]
						+ " where " + ModelInfo[1] + " not in(select top "
						+ start + " " + ModelInfo[1] + " from " + ModelInfo[0]
						+ "   " + sortStr + " " + ")     " + sortStr;
			}

			if (dbType.equals("oracle")) {
				if (start == null || start == "")
					start = "0";
				if (limit == null || limit == "")
					limit = "10000";
				int begin = Integer.parseInt(start) + 1;
				int end = Integer.parseInt(start) + Integer.parseInt(limit);
				sql = "select * from(select a_a.*,ROWNUM rn from(select * from "
						+ ModelInfo[0]
						+ " "
						+ sortStr
						+ ") a_a) where rn between " + begin + " and " + end;
			}
		}
		return Query(sql);
	}

	public List FindByPage(String strSQL, String start, String limit,
			String conditions, String sortFileld, String sortDirection) {

		String sortStr = "";
		if (sortDirection == null || sortDirection.equals("null"))
			sortDirection = "";
		if (sortFileld != null && !sortFileld.equals(""))
			sortStr += " order by " + sortFileld + " " + sortDirection;
		String sql = "select  * from (" + strSQL + " " + sortStr + ")A  limit "
				+ start + "," + limit;
		if (conditions != null && !conditions.trim().equals("")) {
			if (dbType.equals("mysql")) {
				conditions = conditions.replace("lower(nvl(", "lower(ifnull(");
				sql = "select  * from (" + strSQL + " where   " + conditions
						+ "" + sortStr + ")A  limit " + start + "," + limit;
			}

			if (dbType.equals("sqlserver")) {
				conditions = conditions.replace("lower(nvl(", "lower(ISNULL(");
				sql = "select top " + limit
						+ " * from (select row_number() over( " + sortStr
						+ " ) as rownumber,B.* from (" + strSQL + ")B where "
						+ conditions + " )A where rownumber >" + start;
			}

			if (dbType.equals("oracle")) {
				if (start == null || start == "")
					start = "0";
				if (limit == null || limit == "")
					limit = "10000";
				int begin = Integer.parseInt(start) + 1;
				int end = Integer.parseInt(start) + Integer.parseInt(limit);
				sql = "select * from(select ROWNUM rn, a_a.* from(" + strSQL
						+ " where " + conditions + sortStr
						+ ") a_a) where rn between " + begin + " and " + end;
			}
		} else {

			if (dbType.equals("sqlserver")) {

				sql = "select top " + limit
						+ " * from (select row_number() over( " + sortStr
						+ " ) as rownumber,B.* from (" + strSQL
						+ ")B )A where rownumber >" + start;
			}

			if (dbType.equals("oracle")) {
				if (start == null || start == "")
					start = "0";
				if (limit == null || limit == "")
					limit = "10000";
				int begin = Integer.parseInt(start) + 1;
				int end = Integer.parseInt(start) + Integer.parseInt(limit);
				sql = "select * from(select ROWNUM rn, a_a.* from(" + strSQL
						+ " ) a_a) where rn between " + begin + " and " + end;
			}
		}
		return Query(sql);
	}

	// / <summary>
	// / 返回符合记录的数据条数
	// / </summary>
	// / <param name="TableName">表名@param
	// / <param name="conditions">条件@param
	// / <returns></returns>
	public String GetCountByConditions(Object obj, String conditions) {

		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		String sql = "select count(*) from " + ModelInfo[0];
		if (conditions != null && !conditions.trim().equals("")) {
			if (dbType.equals("mysql"))
				conditions = conditions.replace("lower(nvl(", "lower(ifnull(");
			if (dbType.equals("sqlserver"))
				conditions = conditions.replace("lower(nvl(", "lower(ISNULL(");
			sql += " where " + conditions;

		}

		Map map = (Map) Query(sql).get(0);
		Iterator iter = map.entrySet().iterator();
		if (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			return value.toString();
		}

		return "0";
	}

	public String GetCountByConditions(String TableName, String conditions) {

		String sql = "select count(*) from " + TableName;
		if (conditions != null && !conditions.trim().equals("")) {
			if (dbType.equals("mysql"))
				conditions = conditions.replace("lower(nvl(", "lower(ifnull(");
			if (dbType.equals("sqlserver"))
				conditions = conditions.replace("lower(nvl(", "lower(ISNULL(");
			sql += " where " + conditions;
		}

		Map map = (Map) Query(sql).get(0);
		Iterator iter = map.entrySet().iterator();
		if (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			return value.toString();
		}

		return "0";
	}

	public int ExecuteArrayParam(String SQLString, int rows,
			Object... paramters) {
		DruidPooledConnection conn = null;

		PreparedStatement pstm = null;
		int res = 0;
		try {
			conn = DbPoolConnection.getConnection(dbconfig);
			PreparedStatement ps = conn.prepareStatement(SQLString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int j = 0; j < rows; j++) {
				for (int i = 0; i < paramters.length; i++) {
					Object[] obs = (Object[]) paramters[i];
					if (obs != null) {

						if (obs[j] == null) {
							ps.setObject(i + 1, null);
							continue;
						}
						String type = obs[j].getClass().getSimpleName();
						/*
						 * if (type.equalsIgnoreCase("int") ||
						 * type.equalsIgnoreCase("integer")) { ps.setInt(i + 1,
						 * Integer.parseInt(obs[j].toString())); } else if
						 * (type.equalsIgnoreCase("long")) { ps.setString(i + 1,
						 * "'"+obs[j].toString()+"'"); } else if
						 * (type.equalsIgnoreCase("double")) { ps.setString(i +
						 * 1, "'"+obs[j].toString()+"'"); } else
						 */

						if (type.equalsIgnoreCase("date")
								|| type.equalsIgnoreCase("datetime")
								|| type.equalsIgnoreCase("Timestamp")) {
							ps.setTimestamp(i + 1, new Timestamp(
									((Date) (obs[j])).getTime()));
						} else {
							ps.setObject(i + 1, obs[j]);
						}

					}
				}
				ps.addBatch();
			}

			ps.executeBatch();
			ps.close();
			conn.close();

			res = 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e.getMessage() + "\r\n" + SQLString, e);
			e.printStackTrace();
			res = 0;
		}
		return res;
	}

	// / <summary>
	// / 根据表名删除记录
	// / </summary>
	// / <param name="TableName">表名</param>
	// / <param name="AutoID">自动增长id</param>
	// / <param name="value">id值</param>
	// / <returns>是否删除</returns>
	public Boolean Delete(Object obj) {
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		String sql = "Delete from " + ModelInfo[0] + " where " + ModelInfo[1]
				+ "=";
		Class t = obj.getClass();
		Field[] pis = t.getDeclaredFields();
		for (int i = 0; i < pis.length; i++) {
			if (pis[i].getName().toLowerCase()
					.equals(ModelInfo[1].toLowerCase())) {
				sql += cobi.GetValue(obj, pis[i]).toString();
				break;
			}
		}
		if (ExecuteSql(sql) > 0)
			return true;
		else
			return false;
	}

	public Boolean Delete(Object obj, String conditions) {
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		String sql = "Delete from "
				+ ModelInfo[0]
				+ (conditions.trim().length() > 0 ? " where " + conditions : "");
		if (ExecuteSql(sql) > 0)
			return true;
		else
			return false;
	}

	// / <summary>
	// / 添加一条记录
	// / </summary>
	// / @param<param name="obj">表名称对象
	// / @param<param name="AutoID">自增列
	// / <returns>是否插入成功</returns>
	public Boolean Insert(Object obj) {
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		StringBuilder Columns = new StringBuilder("");
		StringBuilder Values = new StringBuilder("");
		Class t = obj.getClass();
		String sql = "insert into " + ModelInfo[0] + " ({0}) values ({1})";
		Field[] pis = t.getDeclaredFields();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object paramter = null;
		for (int i = 0; i < pis.length; i++) {
			if (!pis[i].getName().toLowerCase()
					.equals(ModelInfo[1].toLowerCase())) {
				Columns.append(pis[i].getName());
				if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("string"))
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("date")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("datetime")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("Timestamp")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						if (dbType.equals("oracle")) {
							Values.append("to_date('"
									+ sdf.format(
											(Date) (cobi.GetValue(obj, pis[i])))
											.replace("'", "’")
									+ "','YYYY-MM-DD HH24:MI:SS')");
						} else
							Values.append("'"
									+ sdf.format(
											(Date) (cobi.GetValue(obj, pis[i])))
											.replace("'", "’") + "'");
					else
						Values.append("null");
				} else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("double")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("int")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("integer")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append(cobi.GetValue(obj, pis[i]).toString()
								.replace("'", "’"));
					else
						Values.append("null");
				} else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("byte[]")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						paramter = cobi.GetValue(obj, pis[i]);

				} else {
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				}
				Columns.append(",");
				Values.append(",");
			} else {
				if (dbType.equals("oracle")) {
					if (pis[i].getType().getSimpleName().toLowerCase()
							.equals("int")
							|| pis[i].getType().getSimpleName().toLowerCase()
									.equals("integer")) {
						Columns.append(pis[i].getName());
						if (!ModelInfo[2].equals(""))
							Values.append(ModelInfo[2] + ".nextval");
						else
							Values.append("'"
									+ cobi.GetValue(obj, pis[i]).toString()
											.replace("'", "’") + "'");
					} else {
						Columns.append(pis[i].getName());
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					}
					Columns.append(",");
					Values.append(",");
				}

			}
		}
		int index1 = Columns.toString().lastIndexOf(',');
		Columns.deleteCharAt(index1);
		int index2 = Values.toString().lastIndexOf(',');
		Values.deleteCharAt(index2);
		if (paramter != null) {
			if (ExecuteSql(
					MessageFormat.format(sql, Columns.toString(),
							Values.toString()), paramter) > 0)
				return true;
			else
				return false;
		} else {
			if (ExecuteSql(MessageFormat.format(sql, Columns.toString(),
					Values.toString())) > 0)
				return true;
			else
				return false;
		}

	}

	public Boolean BatchInsert(Object[] obj) {
		int n = obj.length;
		if (n == 0)
			return true;
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj[0]);

		Class t = obj[0].getClass();
		String sql = "insert into " + ModelInfo[0] + " ({0}) values ({1})";
		Field[] pis = t.getDeclaredFields();
		Boolean res = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int m = (int) (Math.ceil(n / 1000.0));
		for (int ii = 0; ii < m; ii++) {
			int start = ii * 1000;
			int end = (ii + 1) * 1000;
			if (end > n)
				end = n;
			int limit = end - start;

			StringBuilder Columns = new StringBuilder("");
			StringBuilder Values = new StringBuilder("");
			List<Object> myPrameters = new ArrayList<Object>();
			for (int i = 0; i < pis.length; i++) {
				String dttype = pis[i].getType().getSimpleName().toString();
				if (!pis[i].getName().toString().toLowerCase()
						.equals(ModelInfo[1].toLowerCase())) {
					Columns.append(pis[i].getName().toString());
					Values.append("?");
					Object op = new Object();
					Object[] _parasvalue = new Object[limit];
					/*
					 * if (pis[i].getType().getSimpleName().toLowerCase()
					 * .equals("string")){ for (int j = 0; j < limit; j++) {
					 * if(cobi.GetValue(obj[j], pis[i])!=null) _parasvalue[j]
					 * =cobi.GetValue(obj[j], pis[i]).toString(); else
					 * _parasvalue[j] =""; } }else if
					 * (pis[i].getType().getSimpleName().toLowerCase()
					 * .equals("date") ||
					 * pis[i].getType().getSimpleName().toLowerCase()
					 * .equals("datetime") ||
					 * pis[i].getType().getSimpleName().toLowerCase()
					 * .equals("Timestamp")) { for (int j = 0; j < limit; j++) {
					 * if(cobi.GetValue(obj[j], pis[i])!=null) _parasvalue[j] =
					 * (Date) (cobi.GetValue(obj[j], pis[i])); else
					 * _parasvalue[j] =""; } }else if
					 * (pis[i].getType().getSimpleName().toLowerCase()
					 * .equals("int") ||
					 * pis[i].getType().getSimpleName().toLowerCase()
					 * .equals("integer")) { for (int j = 0; j < limit; j++) {
					 * if(cobi.GetValue(obj[j], pis[i])!=null) _parasvalue[j]
					 * =Integer.parseInt(cobi.GetValue(obj[j],
					 * pis[i]).toString()) ; else _parasvalue[j] =""; } }else if
					 * (pis[i].getType().getSimpleName()
					 * .equalsIgnoreCase("double")){ for (int j = 0; j < limit;
					 * j++) { if(cobi.GetValue(obj[j], pis[i])!=null)
					 * _parasvalue[j] =Double.parseDouble(cobi.GetValue(obj[j],
					 * pis[i]).toString()); else _parasvalue[j] =""; }
					 * 
					 * }else{
					 */
					for (int j = 0; j < limit; j++) {
						// if(cobi.GetValue(obj[j], pis[i])!=null)
						_parasvalue[j] = cobi.GetValue(obj[j], pis[i]);
						// else _parasvalue[j] ="";
					}
					// }

					op = _parasvalue;
					myPrameters.add(op);

				} else {
					if (dbType.equals("oracle")) {
						if (dttype.equalsIgnoreCase("int")
								|| dttype.equalsIgnoreCase("integer")
								|| dttype.equalsIgnoreCase("long")) {
							Columns.append(pis[i].getName().toString());
							String sqlq = "Select " + ModelInfo[2]
									+ ".Nextval from dual";

							int returnID = Integer.parseInt(GetSingle(sqlq)
									.toString()); // 获取ID

							Values.append("?");
							Object op = new Object();
							Object[] _parasvalue = new Object[limit];
							sqlq = "";
							for (int j = 0; j < limit; j++) {
								_parasvalue[j] = returnID + j;

							}
							sqlq += "declare\r\n";
							sqlq += "   LastValue   integer;\r\n";
							sqlq += "   begin\r\n";
							sqlq += "       loop\r\n";
							sqlq += "         select   "
									+ ModelInfo[2]
									+ ".nextval   into   LastValue   from   dual; \r\n";
							sqlq += "           exit   when   LastValue   >= "
									+ (returnID + limit) + ";\r\n";
							sqlq += "       end loop;\r\n";
							sqlq += "   end;\r\n";
							ExecuteSql(sqlq.replace("\r\n", " "));
							op = _parasvalue;
							myPrameters.add(op);

							Columns.append(",");
							Values.append(",");
						} else {
							Columns.append(pis[i].getName());

							Values.append("?");
							Object op = new Object();
							Object[] _parasvalue = new Object[limit];
							for (int j = 0; j < limit; j++) {
								_parasvalue[j] = cobi.GetValue(obj[j], pis[i]);
							}
							op = _parasvalue;
							myPrameters.add(op);
						}
					} else {
						if (dttype.equalsIgnoreCase("int")
								|| dttype.equalsIgnoreCase("integer")
								|| dttype.equalsIgnoreCase("long")) {

						} else {
							Columns.append(pis[i].getName());

							Values.append("?");
							Object op = new Object();
							Object[] _parasvalue = new Object[limit];
							for (int j = 0; j < limit; j++) {
								_parasvalue[j] = cobi.GetValue(obj[j], pis[i]);
							}
							op = _parasvalue;
							myPrameters.add(op);
						}
					}

				}
				if (!pis[i].getName().toString().toLowerCase()
						.equals(ModelInfo[1].toLowerCase())
						|| !(dttype.equalsIgnoreCase("int")
								|| dttype.equalsIgnoreCase("integer") || dttype
									.equalsIgnoreCase("long"))) {
					Columns.append(",");
					Values.append(",");
				}

			}

			int index1 = Columns.toString().lastIndexOf(',');
			Columns.deleteCharAt(index1);
			int index2 = Values.toString().lastIndexOf(',');
			Values.deleteCharAt(index2);
			Object[] objpramaters = new Object[myPrameters.size()];
			for (int i = 0; i < myPrameters.size(); i++) {
				objpramaters[i] = myPrameters.get(i);
			}
			if (ExecuteArrayParam(
					MessageFormat.format(sql, Columns.toString(),
							Values.toString()), limit, objpramaters) > 0)
				res = true;
			else
				res = false;

		}

		return res;

	}

	// / <summary>
	// / 添加一条记录,并返回主键ID
	// / </summary>
	// / <param name="obj">表名称对象</param>
	// / <param name="AutoID">自增列</param>
	// / <returns>是否插入成功</returns>
	public Object InsertReturnAutoID(Object obj) {
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		StringBuilder Columns = new StringBuilder("");
		StringBuilder Values = new StringBuilder("");
		Class t = obj.getClass();
		String sql = "insert into " + ModelInfo[0] + " ({0}) values ({1})";
		Field[] pis = t.getDeclaredFields();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object paramter = null;
		String returnID = "";
		for (int i = 0; i < pis.length; i++) {
			if (!pis[i].getName().toLowerCase()
					.equals(ModelInfo[1].toLowerCase())) {
				Columns.append(pis[i].getName());
				if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("string"))
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("datetime")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("date")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("Timestamp")) {
					if (cobi.GetValue(obj, pis[i]) != null) {
						if (dbType.equals("oracle")) {
							Values.append("to_date('"
									+ sdf.format(
											(Date) (cobi.GetValue(obj, pis[i])))
											.replace("'", "’")
									+ "','YYYY-MM-DD HH24:MI:SS')");
						} else
							Values.append("'"
									+ sdf.format(
											(Date) (cobi.GetValue(obj, pis[i])))
											.replace("'", "’") + "'");
					} else
						Values.append("null");
				} else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("double")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("int")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("integer")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				} else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("byte[]")) {
					if (cobi.GetValue(obj, pis[i]) != null) {
						paramter = cobi.GetValue(obj, pis[i]);
					}
					Values.append("?");

				} else {
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				}
				Columns.append(",");
				Values.append(",");
			} else {
				if (dbType.equals("oracle")) {
					if (pis[i].getType().getSimpleName().toLowerCase()
							.equals("int")
							|| pis[i].getType().getSimpleName().toLowerCase()
									.equals("integer")) {
						Columns.append(pis[i].getName());
						if (!ModelInfo[2].equals("")) {
							String sqlt = "Select " + ModelInfo[2]
									+ ".nextval from dual";
							// OracleConnection conn = new
							// OracleConnection(connectionString);
							// OracleCommand cmd = conn.CreateCommand();
							// conn.Open();
							// cmd.CommandText = sql;
							returnID = GetSingle(sqlt).toString(); // 获
							Values.append(returnID);
						} else
							Values.append("'"
									+ cobi.GetValue(obj, pis[i]).toString()
											.replace("'", "’") + "'");
					} else {
						Columns.append(pis[i].getName());
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					}
					Columns.append(",");
					Values.append(",");
				}

			}
		}
		int index1 = Columns.toString().lastIndexOf(',');
		Columns.deleteCharAt(index1);
		int index2 = Values.toString().lastIndexOf(',');
		Values.deleteCharAt(index2);
		if (returnID.equals(""))
			return insertWithReturnPrimeKey(
					MessageFormat.format(sql, Columns.toString(),
							Values.toString()), paramter);
		else {
			ExecuteSql(
					MessageFormat.format(sql, Columns.toString(),
							Values.toString()), paramter);
			return returnID;
		}

	}

	public Boolean BatchDelete(Object[] obj) {
		int n = obj.length;
		if (n == 0)
			return true;
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj[0]);

		Class t = obj[0].getClass();
		Field[] pis = t.getDeclaredFields();
		int m = (int) (Math.ceil(n / 1000.0));
		Boolean res = false;
		for (int ii = 0; ii < m; ii++) {
			int start = ii * 1000;
			int end = (ii + 1) * 1000;
			if (end > n)
				end = n;
			int limit = end - start;

			String sql = "Delete from " + ModelInfo[0] + " where "
					+ ModelInfo[1] + " in";
			for (int i = 0; i < pis.length; i++) {
				String dttype = pis[i].getType().getSimpleName().toString();
				if (pis[i].getName().toString().toLowerCase()
						.equals(ModelInfo[1].toLowerCase())) {
					sql += "(";
					if (dttype.equalsIgnoreCase("int")
							|| dttype.equalsIgnoreCase("integer")) {
						// OracleParameter op = new
						// OracleParameter(pis[i].Name.ToString(),
						// OracleDbType.Int64);
						// object[] _parasvalue = new object[limit];
						for (int j = 0; j < limit; j++) {
							sql += cobi.GetValue(obj[j], pis[i]).toString()
									+ ",";

						}
						int index1 = sql.lastIndexOf(',');
						if (index1 > -1)
							sql = sql.substring(0, index1);
						sql = sql + ")";
						// op.Value = _parasvalue;
						// myPrameters.Add(op);
					} else {
						// OracleParameter op = new
						// OracleParameter(pis[i].Name.ToString(),
						// OracleDbType.Varchar2);
						// object[] _parasvalue = new object[limit];
						for (int j = 0; j < limit; j++) {
							sql += "'"
									+ cobi.GetValue(obj[j], pis[i]).toString()
									+ "',";
						}
						int index1 = sql.lastIndexOf(',');
						if (index1 > -1)
							sql = sql.substring(0, index1);
						sql = sql + ")";
						// op.Value = _parasvalue;
						// myPrameters.Add(op);
					}
					break;
				}
			}
			if (ExecuteSql(sql) > 0)
				res = true;
			else
				res = false;
			// OracleParameter[] Pramas = new
			// OracleParameter[myPrameters.Count];
			// for (int i = 0; i < myPrameters.Count; i++)
			// {
			// Pramas[i] = myPrameters[i];
			// }
			// if (ExecuteArrayParam(sql, limit, Pramas) > 0)
			// res = true;
			// else
			// res = false;
		}
		return res;
	}

	public Boolean BatchUpdate(Object[] obj) {
		return BatchUpdate(obj, null);
	}

	public Boolean BatchUpdate(Object[] obj, String tablename) {
		int n = obj.length;
		if (n == 0)
			return true;
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj[0]);

		Class t = obj[0].getClass();
		String conditions = ModelInfo[1] + "=";

		String sql = "update " + ModelInfo[0] + " set ";
		if (tablename != null && tablename != "")
			sql = "update " + tablename + " set ";
		Field[] pis = t.getDeclaredFields();

		int m = (int) (Math.ceil(n / 1000.0));
		Boolean res = false;
		for (int ii = 0; ii < m; ii++) {
			int start = ii * 1000;
			int end = (ii + 1) * 1000;
			if (end > n)
				end = n;
			int limit = end - start;
			List<Object> myPrameters = new ArrayList<Object>();
			StringBuilder Values = new StringBuilder("");
			for (int i = 0; i < pis.length; i++) {
				String dttype = pis[i].getType().getSimpleName().toString();
				if (!pis[i].getName().toString().toLowerCase()
						.equals(ModelInfo[1].toLowerCase())) {
					Values.append(pis[i].getName());
					Values.append("=");
					Values.append("?");

					Object op = new Object();
					Object[] _parasvalue = new Object[limit];
					for (int j = 0; j < limit; j++) {
						_parasvalue[j] = cobi.GetValue(obj[j], pis[i]);
					}
					op = _parasvalue;
					myPrameters.add(op);

				} else {
					conditions += "?";

					Object op = new Object();
					Object[] _parasvalue = new Object[limit];
					for (int j = 0; j < limit; j++) {
						_parasvalue[j] = cobi.GetValue(obj[j], pis[i]);
					}
					op = _parasvalue;
					myPrameters.add(op);
				}
				if (!pis[i].getName().toString().toLowerCase()
						.equals(ModelInfo[1].toLowerCase())) {
					Values.append(",");
				}
			}

			int index2 = Values.toString().lastIndexOf(',');
			Values.deleteCharAt(index2);
			Object[] objpramaters = new Object[myPrameters.size()];
			for (int i = 1; i < myPrameters.size(); i++) {
				objpramaters[i - 1] = myPrameters.get(i);
			}
			objpramaters[myPrameters.size() - 1] = myPrameters.get(0);
			if (ExecuteArrayParam(sql + Values.toString().replace(",,", ",")
					+ " where " + conditions, limit, objpramaters) > 0)
				res = true;
			else {
				res = false;
			}
		}

		return res;
	}

	// / <summary>
	// / 更新记录
	// / </summary>
	// / <param name="obj">表实体</param>
	// / <param name="AutoID">自动id</param>
	// / <returns>是否更新成功</returns>
	public Boolean Update(Object obj) {
		Common<Object> cobi = new Common<Object>();
		String[] ModelInfo = cobi.GetModelInfo(obj);
		StringBuilder Values = new StringBuilder("");
		String conditions = ModelInfo[1] + "=";
		Class t = obj.getClass();
		String sql = "update " + ModelInfo[0] + " set ";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Field[] pis = t.getDeclaredFields();
		Object paramter = null;
		for (int i = 0; i < pis.length; i++) {
			if (!pis[i].getName().toLowerCase()
					.equals(ModelInfo[1].toLowerCase())) {
				Values.append(pis[i].getName());
				Values.append("=");
				if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("string"))
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("date")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("datetime")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("Timestamp")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						if (dbType.equals("oracle")) {
							Values.append("to_date('"
									+ sdf.format(
											(Date) (cobi.GetValue(obj, pis[i])))
											.replace("'", "’")
									+ "','YYYY-MM-DD HH24:MI:SS')");
						} else
							Values.append("'"
									+ sdf.format(
											(Date) (cobi.GetValue(obj, pis[i])))
											.replace("'", "’") + "'");
					else
						Values.append("null");
				} else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("byte[]")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						paramter = cobi.GetValue(obj, pis[i]);
					Values.append("?");

				} else if (pis[i].getType().getSimpleName().toLowerCase()
						.equals("double")
						|| pis[i].getType().getSimpleName().toLowerCase()
								.equals("int")) {
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append(cobi.GetValue(obj, pis[i]).toString()
								.replace("'", "’"));
					else
						Values.append("null");
				} else {
					if (cobi.GetValue(obj, pis[i]) != null)
						Values.append("'"
								+ cobi.GetValue(obj, pis[i]).toString()
										.replace("'", "’") + "'");
					else
						Values.append("null");
				}
				Values.append(",");
			} else
				conditions += cobi.GetValue(obj, pis[i]).toString();
		}
		int index2 = Values.toString().lastIndexOf(',');
		Values.deleteCharAt(index2);
		if (ExecuteSql(sql + Values.toString() + " where " + conditions,
				paramter) > 0)
			return true;
		else
			return false;
	}

}
