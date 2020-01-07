package com.cyws.tank.manager.dal;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.common.DBBean;
import com.cyws.tank.manager.db.DBHandleHelper;
import com.cyws.tank.manager.po.TaLocationPo;


public class TaLocationDal {

	DBHandleHelper DbHelper=new DBHandleHelper();
	
	// 查询最新数据信息表
	public List<Map<String, Object>> selectTaLocationByID(String EQUIPMENT_ID){
		String SQLString ="SELECT * FROM ta_equipmet_location_new where equipment_id='"+EQUIPMENT_ID+"'";
		List<Map<String, Object>> ls_taData= DbHelper.Query(DBBean.gndata,SQLString);
		return ls_taData;
	}
	
	/**
	 * 插入位置信息最新表数据
	 * @param po
	 * @return
	 */
	public int AddTaLocationNew(TaLocationPo po){
		StringBuilder sql=new StringBuilder("insert into ta_equipmet_location_new (");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("EQUIPMENT_ID");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",DA_UP");}		
		if(po.getIS_LOCATION()!=null){sql.append(",IS_LOCATION");}
		if(po.getLOCATION_TYPE()!=null){sql.append(",LOCATION_TYPE");}		
		if(po.getLONGITUDE()!=null){sql.append(",LONGITUDE");}
		if(po.getLATITUDE()!=null ){sql.append(",LATITUDE");}
		if(po.getR_LONGITUDE()!=null){sql.append(",R_LONGITUDE");}
		if(po.getR_LATITUDE()!=null ){sql.append(",R_LATITUDE");}
		if(po.getPLACE()!=null){sql.append(",PLACE");}
		if(po.getDIRECTION()!=null){sql.append(",DIRECTION");}
		if(po.getSPEED()!=null){sql.append(",SPEED");}
		if(po.getHEIGTH()!=null){sql.append(",HEIGTH");}		
		sql.append(") values(");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("'"+po.getEQUIPMENT_ID()+"'");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",'"+po.getDA_UP()+"'");}
		if(po.getIS_LOCATION()!=null){sql.append(",'"+po.getIS_LOCATION()+"'");}
		if(po.getLOCATION_TYPE()!=null){sql.append(",'"+po.getLOCATION_TYPE()+"'");}	
		if(po.getLONGITUDE()!=null){sql.append(",'"+po.getLONGITUDE()+"'");}
		if(po.getLATITUDE()!=null){sql.append(",'"+po.getLATITUDE()+"'");}
		if(po.getR_LONGITUDE()!=null){sql.append(",'"+po.getR_LONGITUDE()+"'");}
		if(po.getR_LATITUDE()!=null){sql.append(",'"+po.getR_LATITUDE()+"'");}
		if(po.getPLACE()!=null){sql.append(",'"+po.getPLACE()+"'");}
		if(po.getDIRECTION()!=null){sql.append(",'"+po.getDIRECTION()+"'");}
		if(po.getSPEED()!=null){sql.append(",'"+po.getSPEED()+"'");}
		if(po.getHEIGTH()!=null){sql.append(",'"+po.getHEIGTH()+"'");}
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());
		return Result;
	}
	
	/**
	 * 更改位置信息最新表数据
	 * @param po
	 * @return
	 */
	public int updateTaLocationNew(TaLocationPo po){
		StringBuilder sql=new StringBuilder("update ta_equipmet_location_new set ");
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append("DA_UP='"+po.getDA_UP()+"'");}
		if(po.getIS_LOCATION()!=null){sql.append(",IS_LOCATION='"+po.getIS_LOCATION()+"'");}
		if(po.getLOCATION_TYPE()!=null){sql.append(",LOCATION_TYPE='"+po.getLOCATION_TYPE()+"'");}	
		if(po.getLONGITUDE()!=null){sql.append(",LONGITUDE='"+po.getLONGITUDE()+"'");}
		if(po.getLATITUDE()!=null ){sql.append(",LATITUDE='"+po.getLATITUDE()+"'");}		
		if(po.getR_LONGITUDE()!=null){sql.append(",R_LONGITUDE='"+po.getR_LONGITUDE()+"'");}
		if(po.getR_LATITUDE()!=null ){sql.append(",R_LATITUDE='"+po.getR_LATITUDE()+"'");}		
		if(po.getDIRECTION()!=null){sql.append(",DIRECTION='"+po.getDIRECTION()+"'");}
		if(po.getSPEED()!=null){sql.append(",SPEED='"+po.getSPEED()+"'");}
		if(po.getHEIGTH()!=null){sql.append(",HEIGTH='"+po.getHEIGTH()+"'");}
		if(po.getPLACE()!=null){sql.append(",PLACE='"+po.getPLACE()+"'");}
		sql.append(" where EQUIPMENT_ID='" + po.getEQUIPMENT_ID()+"'");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());
		return Result;
	}
	
	
	/**
	 * 插入位置信息数据的历史记录
	 * @param po
	 * @return
	 */
	public int AddLocation(TaLocationPo po){
		StringBuilder sql=new StringBuilder("insert into ta_equipmet_location (");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("EQUIPMENT_ID");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",DA_UP");}		
		if(po.getIS_LOCATION()!=null){sql.append(",IS_LOCATION");}
		if(po.getLOCATION_TYPE()!=null){sql.append(",LOCATION_TYPE");}		
		if(po.getLONGITUDE()!=null){sql.append(",LONGITUDE");}
		if(po.getLATITUDE()!=null ){sql.append(",LATITUDE");}
		if(po.getR_LONGITUDE()!=null){sql.append(",R_LONGITUDE");}
		if(po.getR_LATITUDE()!=null ){sql.append(",R_LATITUDE");}
		if(po.getPLACE()!=null){sql.append(",PLACE");}
		if(po.getDIRECTION()!=null){sql.append(",DIRECTION");}
		if(po.getSPEED()!=null){sql.append(",SPEED");}
		if(po.getHEIGTH()!=null){sql.append(",HEIGTH");}		
		sql.append(") values(");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("'"+po.getEQUIPMENT_ID()+"'");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",'"+po.getDA_UP()+"'");}
		if(po.getIS_LOCATION()!=null){sql.append(",'"+po.getIS_LOCATION()+"'");}
		if(po.getLOCATION_TYPE()!=null){sql.append(",'"+po.getLOCATION_TYPE()+"'");}	
		if(po.getLONGITUDE()!=null){sql.append(",'"+po.getLONGITUDE()+"'");}
		if(po.getLATITUDE()!=null){sql.append(",'"+po.getLATITUDE()+"'");}
		if(po.getR_LONGITUDE()!=null){sql.append(",'"+po.getR_LONGITUDE()+"'");}
		if(po.getR_LATITUDE()!=null){sql.append(",'"+po.getR_LATITUDE()+"'");}
		if(po.getPLACE()!=null){sql.append(",'"+po.getPLACE()+"'");}
		if(po.getDIRECTION()!=null){sql.append(",'"+po.getDIRECTION()+"'");}
		if(po.getSPEED()!=null){sql.append(",'"+po.getSPEED()+"'");}
		if(po.getHEIGTH()!=null){sql.append(",'"+po.getHEIGTH()+"'");}
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(Result+"  "+sql.toString());
		return Result;
	}
	
	/*public static void main(String[] args) {
		TaLocationDal taLocationDal=new TaLocationDal();
		
		TaLocationPo po=new TaLocationPo();
		po.setEQUIPMENT_ID("123456");
		po.setDA_UP("2018-11-26 13:58:01");
		po.setIS_LOCATION(1); // 是否定位成功
		po.setLOCATION_TYPE(1);// 定位类型
		po.setLONGITUDE(113.012);// 经度
		po.setLATITUDE(23.20);// 纬度
		po.setPLACE("");// 地址
		po.setDIRECTION(1); // 方向
		po.setSPEED(2.0); // 速度
		po.setHEIGTH(3); // 高度
		
		//taLocationDal.AddTaLocationNew(po);
		//taLocationDal.AddLocation(po);
		List<Map<String, Object>> ls_map=taLocationDal.selectTaLocationByID("123456");
		
		for(Map<String, Object> map :ls_map){
			for(String key:map.keySet()){
				System.out.print(key+":"+map.get(key)+"; ");
			}
			System.out.println();
		}
	}*/
}
