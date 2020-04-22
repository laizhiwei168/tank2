package com.cyws.tank.manager.dal;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.common.DBBean;
import com.cyws.tank.manager.db.DBHandleHelper;
import com.cyws.tank.manager.po.TaDataPo;

public class TaDataDal {
	DBHandleHelper DbHelper=new DBHandleHelper();
	
	public List<Map<String, Object>> selectTaTankById(String EQUIPMENT_ID){
		//String SQLString="SELECT * FROM ta_tank WHERE EQUIPMENT_NO='"+EQUIPMENT_ID+"' and IS_DELETE='0'";
		//String SQLString="SELECT * FROM ta_equipmet where EQUIPMENT_NO='"+EQUIPMENT_ID+"' and IS_DELETE=0";
		//String SQLString="SELECT * FROM ta_equipmet as a left join ta_item AS b on a.ITEM_ID=b.ID  where a.EQUIPMENT_NO='"+EQUIPMENT_ID+"' and a.IS_DELETE=0";
		String SQLString ="SELECT a.*,b.STOREUNITNUM FROM ta_equipmet as a left join bd_material AS b on a.ITEM_ID=b.PK_MATERIAL  where a.EQUIPMENT_NO='"+EQUIPMENT_ID+"' and a.IS_DELETE=0";
		List<Map<String, Object>> ls_taTank= DbHelper.Query(DBBean.gndata,SQLString);		
		return ls_taTank;
	}
	
	public List<Map<String, Object>> selectdbAdress(String pk_address){
		String SQLString ="SELECT * FROM bd_address WHERE PK_ADDRESS='"+pk_address+"'";
		List<Map<String, Object>> ls_db_address= DbHelper.Query(DBBean.gndata,SQLString);		
		return ls_db_address;
	}
	
	// 查询最新数据信息表
	public List<Map<String, Object>> selectTaDataByID(String EQUIPMENT_ID){
		//String SQLString="select *  FROM ta_data_new WHERE EQUIPMENT_ID='"+EQUIPMENT_ID+"'";
		String SQLString ="SELECT * FROM ta_equipmet_data_new where equipment_id='"+EQUIPMENT_ID+"'";
		List<Map<String, Object>> ls_taData= DbHelper.Query(DBBean.gndata,SQLString);
		return ls_taData;
	}
	
	// 查询最新数据信息表
	public List<Map<String, Object>> selectTaLocationByID(String EQUIPMENT_ID){
		String SQLString ="SELECT * FROM ta_equipmet_location_new where equipment_id='"+EQUIPMENT_ID+"'";
		List<Map<String, Object>> ls_taData= DbHelper.Query(DBBean.gndata,SQLString);
		return ls_taData;
	}
	
	/**
	 * 插入最新表数据
	 * @param po
	 * @return
	 */
	public int AddTaDataNew(TaDataPo po){
		StringBuilder sql=new StringBuilder("insert into ta_equipmet_data_new (");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("EQUIPMENT_ID");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",DA_UP");}
		if(po.getLONGITUDE()!=null){sql.append(",LONGITUDE");}
		if(po.getLATITUDE()!=null ){sql.append(",LATITUDE");}
		if(po.getDIRECTION()!=null){sql.append(",DIRECTION");}
		if(po.getTEMPERATURE()!=null){sql.append(",TEMPERATURE");}
		if(po.getSPEED()!=null){sql.append(",SPEED");}
		if(po.getHEIGTH()!=null){sql.append(",HEIGTH");}
		if(po.getMILEAGE()!=null){sql.append(",MILEAGE");}
		if(po.getPRESSURE()!=null){sql.append(",PRESSURE");}
		
		if(po.getLEVELS()!=null){sql.append(",LEVELS");}
		if(po.getVACUUM()!=null){sql.append(",VACUUM");}
		if(po.getDOOR()!=null){sql.append(",DOOR");}
		if(po.getGAS()!=null){sql.append(",GAS");}
		if(po.getDIP_X()!=null){sql.append(",DIP_X");}
		if(po.getR_LATITUDE()!=null){sql.append(",R_LATITUDE");}
		if(po.getR_LONGITUDE()!=null){sql.append(",R_LONGITUDE");}
		if(po.getFUEL()!=null){sql.append(",FUEL");}
		if(po.getIS_LOCATION()!=null){sql.append(",IS_LOCATION");}
		if(po.getACCELERATOR_X()!=null){sql.append(",ACCELERATOR_X");}
		
		if(po.getSTATE()!=null){sql.append(",STATE");}
		if(po.getPLACE()!=null){sql.append(",PLACE");}
		if(po.getDIP_Y()!=null){sql.append(",DIP_Y");}
		if(po.getACCELERATOR_Y()!=null){sql.append(",ACCELERATOR_Y");}
		if(po.getCURRENT_VOLUME()!=null){sql.append(",CURRENT_VOLUME");}
		if(po.getELECTRIC()!=null){sql.append(",ELECTRIC");}
		if(po.getTEMPERATURE1()!=null){sql.append(",TEMPERATURE1");}
		if(po.getTEMPERATURE2()!=null){sql.append(",TEMPERATURE2");}
		if(po.getTEMPERATURE3()!=null){sql.append(",TEMPERATURE3");}
		if(po.getTEMPERATURE4()!=null){sql.append(",TEMPERATURE4");}

		if(po.getAddSmellyNumber()!=null){sql.append(",addSmellyNumber");}
		
		if(po.getDOOR1()!=null){sql.append(",DOOR1");}
		if(po.getDOOR2()!=null){sql.append(",DOOR2");}
		if(po.getDOOR3()!=null){sql.append(",DOOR3");}
		if(po.getDOOR4()!=null){sql.append(",DOOR4");}
		if(po.getVOLTAGE1()!=null){sql.append(",VOLTAGE1");}
		if(po.getVOLTAGE2()!=null){sql.append(",VOLTAGE2");}
		if(po.getVOLTAGE3()!=null){sql.append(",VOLTAGE3");}
		if(po.getVOLTAGE4()!=null){sql.append(",VOLTAGE4");}
		if(po.getPRESSURE1()!=null){sql.append(",PRESSURE1");}
		if(po.getPRESSURE2()!=null){sql.append(",PRESSURE2");}
		
		if(po.getPRESSURE3()!=null){sql.append(",PRESSURE3");}
		if(po.getPRESSURE4()!=null){sql.append(",PRESSURE4");}
		if(po.getSTAND_ADD_UP()!=null){sql.append(",STAND_ADD_UP");}
		if(po.getSTAND_ADD_UP1()!=null){sql.append(",STAND_ADD_UP1");}
		if(po.getLEVELS1()!=null){sql.append(",LEVELS1");}
		if(po.getCURRENT_VOLUME1()!=null){sql.append(",CURRENT_VOLUME1");}
		if(po.getWORK_ADD_UP()!=null){sql.append(",WORK_ADD_UP");}
		if(po.getWORK_ADD_UP1()!=null){sql.append(",WORK_ADD_UP1");}
		if(po.getSTAND_FLOW()!=null){sql.append(",STAND_FLOW");}
		if(po.getSTAND_FLOW1()!=null){sql.append(",STAND_FLOW1");}
		
		if(po.getWORK_FLOW()!=null){sql.append(",WORK_FLOW");}
		if(po.getWORK_FLOW1()!=null){sql.append(",WORK_FLOW1");}
		if(po.getGAS1()!=null){sql.append(",GAS1");}
		if(po.getA1()!=null){sql.append(",A1");}
		if(po.getA2()!=null){sql.append(",A2");}
		if(po.getA3()!=null){sql.append(",A3");}
		if(po.getA4()!=null){sql.append(",A4");}
		if(po.getA5()!=null){sql.append(",A5");}
		if(po.getA6()!=null){sql.append(",A6");}
		
		if(po.getB1()!=null){sql.append(",B1");}
		if(po.getB2()!=null){sql.append(",B2");}
		if(po.getB3()!=null){sql.append(",B3");}
		if(po.getB4()!=null){sql.append(",B4");}
		if(po.getB5()!=null){sql.append(",B5");}
		if(po.getB6()!=null){sql.append(",B6");}
		if(po.getB7()!=null){sql.append(",B7");}
		if(po.getC1()!=null){sql.append(",C1");}
		if(po.getC2()!=null){sql.append(",C2");}
		if(po.getC3()!=null){sql.append(",C3");}
		if(po.getC4()!=null){sql.append(",C4");}
		if(po.getC5()!=null){sql.append(",C5");}
		if(po.getF91()!=null){sql.append(",F91");}
		if(po.getF92()!=null){sql.append(",F92");}
		if(po.getF93()!=null){sql.append(",F93");}
		if(po.getUse_volume()!=null){sql.append(",use_volume");}
		if(po.getAdd_volume()!=null){sql.append(",add_volume");}
		if(po.getSTOREUNITNUM()!=null){sql.append(",STOREUNITNUM");}
		if(po.getTANKPRESSURE3()!=null){sql.append(",TANKPRESSURE3");}
		if(po.getTANKPRESSURE4()!=null){sql.append(",TANKPRESSURE4");}
		if(po.getLEVELS3()!=null){sql.append(",LEVELS3");}
		if(po.getLEVELS4()!=null){sql.append(",LEVELS4");}
		sql.append(") values(");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("'"+po.getEQUIPMENT_ID()+"'");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",'"+po.getDA_UP()+"'");}
		if(po.getLONGITUDE()!=null){sql.append(",'"+po.getLONGITUDE()+"'");}
		if(po.getLATITUDE()!=null){sql.append(",'"+po.getLATITUDE()+"'");}
		if(po.getDIRECTION()!=null){sql.append(",'"+po.getDIRECTION()+"'");}
		if(po.getTEMPERATURE()!=null){sql.append(",'"+po.getTEMPERATURE()+"'");}
		
		if(po.getSPEED()!=null){sql.append(",'"+po.getSPEED()+"'");}
		if(po.getHEIGTH()!=null){sql.append(",'"+po.getHEIGTH()+"'");}
		if(po.getMILEAGE()!=null){sql.append(",'"+po.getMILEAGE()+"'");}
		if(po.getPRESSURE()!=null){sql.append(",'"+po.getPRESSURE()+"'");}
		
		if(po.getLEVELS()!=null){sql.append(",'"+po.getLEVELS()+"'");}
		if(po.getVACUUM()!=null){sql.append(",'"+po.getVACUUM()+"'");}
		if(po.getDOOR()!=null){sql.append(",'"+po.getDOOR()+"'");}
		if(po.getGAS()!=null){sql.append(",'"+po.getGAS()+"'");}
		if(po.getDIP_X()!=null){sql.append(",'"+po.getDIP_X()+"'");}
		if(po.getR_LATITUDE()!=null){sql.append(",'"+po.getR_LATITUDE()+"'");}
		if(po.getR_LONGITUDE()!=null){sql.append(",'"+po.getR_LONGITUDE()+"'");}
		if(po.getFUEL()!=null){sql.append(",'"+po.getFUEL()+"'");}
		if(po.getIS_LOCATION()!=null){sql.append(",'"+po.getIS_LOCATION()+"'");}
		if(po.getACCELERATOR_X()!=null){sql.append(",'"+po.getACCELERATOR_X()+"'");}
		
		if(po.getSTATE()!=null){sql.append(",'"+po.getSTATE()+"'");}
		if(po.getPLACE()!=null){sql.append(",'"+po.getPLACE()+"'");}
		if(po.getDIP_Y()!=null){sql.append(",'"+po.getDIP_Y()+"'");}
		if(po.getACCELERATOR_Y()!=null){sql.append(",'"+po.getACCELERATOR_Y()+"'");}
		if(po.getCURRENT_VOLUME()!=null){sql.append(",'"+po.getCURRENT_VOLUME()+"'");}
		if(po.getELECTRIC()!=null){sql.append(",'"+po.getELECTRIC()+"'");}
		if(po.getTEMPERATURE1()!=null){sql.append(",'"+po.getTEMPERATURE1()+"'");}
		if(po.getTEMPERATURE2()!=null){sql.append(",'"+po.getTEMPERATURE2()+"'");}
		if(po.getTEMPERATURE3()!=null){sql.append(",'"+po.getTEMPERATURE3()+"'");}
		if(po.getTEMPERATURE4()!=null){sql.append(",'"+po.getTEMPERATURE4()+"'");}

		if(po.getAddSmellyNumber()!=null){sql.append(",'"+po.getAddSmellyNumber()+"'");}
		
		if(po.getDOOR1()!=null){sql.append(",'"+po.getDOOR1()+"'");}
		if(po.getDOOR2()!=null){sql.append(",'"+po.getDOOR2()+"'");}
		if(po.getDOOR3()!=null){sql.append(",'"+po.getDOOR3()+"'");}
		if(po.getDOOR4()!=null){sql.append(",'"+po.getDOOR4()+"'");}
		if(po.getVOLTAGE1()!=null){sql.append(",'"+po.getVOLTAGE1()+"'");}
		if(po.getVOLTAGE2()!=null){sql.append(",'"+po.getVOLTAGE2()+"'");}
		if(po.getVOLTAGE3()!=null){sql.append(",'"+po.getVOLTAGE3()+"'");}
		if(po.getVOLTAGE4()!=null){sql.append(",'"+po.getVOLTAGE4()+"'");}
		if(po.getPRESSURE1()!=null){sql.append(",'"+po.getPRESSURE1()+"'");}
		if(po.getPRESSURE2()!=null){sql.append(",'"+po.getPRESSURE2()+"'");}
		
		if(po.getPRESSURE3()!=null){sql.append(",'"+po.getPRESSURE3()+"'");}
		if(po.getPRESSURE4()!=null){sql.append(",'"+po.getPRESSURE4()+"'");}
		if(po.getSTAND_ADD_UP()!=null){sql.append(",'"+po.getSTAND_ADD_UP()+"'");}
		if(po.getSTAND_ADD_UP1()!=null){sql.append(",'"+po.getSTAND_ADD_UP1()+"'");}
		if(po.getLEVELS1()!=null){sql.append(",'"+po.getLEVELS1()+"'");}
		if(po.getCURRENT_VOLUME1()!=null){sql.append(",'"+po.getCURRENT_VOLUME1()+"'");}
		if(po.getWORK_ADD_UP()!=null){sql.append(",'"+po.getWORK_ADD_UP()+"'");}
		if(po.getWORK_ADD_UP1()!=null){sql.append(",'"+po.getWORK_ADD_UP1()+"'");}
		if(po.getSTAND_FLOW()!=null){sql.append(",'"+po.getSTAND_FLOW()+"'");}
		if(po.getSTAND_FLOW1()!=null){sql.append(",'"+po.getSTAND_FLOW1()+"'");}
		
		if(po.getWORK_FLOW()!=null){sql.append(",'"+po.getWORK_FLOW()+"'");}
		if(po.getWORK_FLOW1()!=null){sql.append(",'"+po.getWORK_FLOW1()+"'");}
		if(po.getGAS1()!=null){sql.append(",'"+po.getGAS1()+"'");}
		if(po.getA1()!=null){sql.append(",'"+po.getA1()+"'");}
		if(po.getA2()!=null){sql.append(",'"+po.getA2()+"'");}
		if(po.getA3()!=null){sql.append(",'"+po.getA3()+"'");}
		if(po.getA4()!=null){sql.append(",'"+po.getA4()+"'");}
		if(po.getA5()!=null){sql.append(",'"+po.getA5()+"'");}
		if(po.getA6()!=null){sql.append(",'"+po.getA6()+"'");}
		
		if(po.getB1()!=null){sql.append(",'"+po.getB1()+"'");}
		if(po.getB2()!=null){sql.append(",'"+po.getB2()+"'");}
		if(po.getB3()!=null){sql.append(",'"+po.getB3()+"'");}
		if(po.getB4()!=null){sql.append(",'"+po.getB4()+"'");}
		if(po.getB5()!=null){sql.append(",'"+po.getB5()+"'");}
		if(po.getB6()!=null){sql.append(",'"+po.getB6()+"'");}
		if(po.getB7()!=null){sql.append(",'"+po.getB7()+"'");}
		if(po.getC1()!=null){sql.append(",'"+po.getC1()+"'");}
		if(po.getC2()!=null){sql.append(",'"+po.getC2()+"'");}
		if(po.getC3()!=null){sql.append(",'"+po.getC3()+"'");}
		if(po.getC4()!=null){sql.append(",'"+po.getC4()+"'");}
		if(po.getC5()!=null){sql.append(",'"+po.getC5()+"'");}
		if(po.getF91()!=null){sql.append(",'"+po.getF91()+"'");}
		if(po.getF92()!=null){sql.append(",'"+po.getF92()+"'");}
		if(po.getF93()!=null){sql.append(",'"+po.getF93()+"'");}
		if(po.getUse_volume()!=null){sql.append(",'"+po.getUse_volume()+"'");}
		if(po.getAdd_volume()!=null){sql.append(",'"+po.getAdd_volume()+"'");}
		if(po.getSTOREUNITNUM()!=null){sql.append(",'"+po.getSTOREUNITNUM()+"'");}
		if(po.getTANKPRESSURE3()!=null){sql.append(",'"+po.getTANKPRESSURE3()+"'");}
		if(po.getTANKPRESSURE4()!=null){sql.append(",'"+po.getTANKPRESSURE4()+"'");}
		if(po.getLEVELS3()!=null){sql.append(",'"+po.getLEVELS3()+"'");}
		if(po.getLEVELS4()!=null){sql.append(",'"+po.getLEVELS4()+"'");}
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());
		return Result;
	}
	
	/**
	 * 更改最新表数据
	 * @param po
	 * @return
	 */
	public int updateTaDataNew(TaDataPo po){
		StringBuilder sql=new StringBuilder("update ta_equipmet_data_new set ");
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append("DA_UP='"+po.getDA_UP()+"'");}
		if(po.getLONGITUDE()!=null){sql.append(",LONGITUDE='"+po.getLONGITUDE()+"'");}
		if(po.getLATITUDE()!=null ){sql.append(",LATITUDE='"+po.getLATITUDE()+"'");}
		if(po.getDIRECTION()!=null){sql.append(",DIRECTION='"+po.getDIRECTION()+"'");}
		if(po.getTEMPERATURE()!=null){sql.append(",TEMPERATURE='"+po.getTEMPERATURE()+"'");}
		if(po.getSPEED()!=null){sql.append(",SPEED='"+po.getSPEED()+"'");}
		if(po.getHEIGTH()!=null){sql.append(",HEIGTH='"+po.getHEIGTH()+"'");}
		if(po.getMILEAGE()!=null){sql.append(",MILEAGE='"+po.getMILEAGE()+"'");}
		if(po.getPRESSURE()!=null){sql.append(",PRESSURE='"+po.getPRESSURE()+"'");}
		
		if(po.getLEVELS()!=null){sql.append(",LEVELS='"+po.getLEVELS()+"'");}
		if(po.getVACUUM()!=null){sql.append(",VACUUM='"+po.getVACUUM()+"'");}
		if(po.getDOOR()!=null){sql.append(",DOOR='"+po.getDOOR()+"'");}
		if(po.getGAS()!=null){sql.append(",GAS='"+po.getGAS()+"'");}
		if(po.getDIP_X()!=null){sql.append(",DIP_X='"+po.getDIP_X()+"'");}
		if(po.getR_LATITUDE()!=null){sql.append(",R_LATITUDE='"+po.getR_LATITUDE()+"'");}
		if(po.getR_LONGITUDE()!=null){sql.append(",R_LONGITUDE='"+po.getR_LONGITUDE()+"'");}
		if(po.getFUEL()!=null){sql.append(",FUEL='"+po.getFUEL()+"'");}
		if(po.getIS_LOCATION()!=null){sql.append(",IS_LOCATION='"+po.getIS_LOCATION()+"'");}
		if(po.getACCELERATOR_X()!=null){sql.append(",ACCELERATOR_X='"+po.getACCELERATOR_X()+"'");}
		
		if(po.getSTATE()!=null){sql.append(",STATE='"+po.getSTATE()+"'");}
		if(po.getPLACE()!=null){sql.append(",PLACE='"+po.getPLACE()+"'");}
		if(po.getDIP_Y()!=null){sql.append(",DIP_Y='"+po.getDIP_Y()+"'");}
		if(po.getACCELERATOR_Y()!=null){sql.append(",ACCELERATOR_Y='"+po.getACCELERATOR_Y()+"'");}
		if(po.getCURRENT_VOLUME()!=null){sql.append(",CURRENT_VOLUME='"+po.getCURRENT_VOLUME()+"'");}
		if(po.getELECTRIC()!=null){sql.append(",ELECTRIC='"+po.getELECTRIC()+"'");}
		if(po.getTEMPERATURE1()!=null){sql.append(",TEMPERATURE1='"+po.getTEMPERATURE1()+"'");}
		if(po.getTEMPERATURE2()!=null){sql.append(",TEMPERATURE2='"+po.getTEMPERATURE2()+"'");}
		if(po.getTEMPERATURE3()!=null){sql.append(",TEMPERATURE3='"+po.getTEMPERATURE3()+"'");}
		if(po.getTEMPERATURE4()!=null){sql.append(",TEMPERATURE4='"+po.getTEMPERATURE4()+"'");}

		if(po.getAddSmellyNumber()!=null){sql.append(",addSmellyNumber='"+po.getAddSmellyNumber()+"'");}

		if(po.getDOOR1()!=null){sql.append(",DOOR1='"+po.getDOOR1()+"'");}
		if(po.getDOOR2()!=null){sql.append(",DOOR2='"+po.getDOOR2()+"'");}
		if(po.getDOOR3()!=null){sql.append(",DOOR3='"+po.getDOOR3()+"'");}
		if(po.getDOOR4()!=null){sql.append(",DOOR4='"+po.getDOOR4()+"'");}
		if(po.getVOLTAGE1()!=null){sql.append(",VOLTAGE1='"+po.getVOLTAGE1()+"'");}
		if(po.getVOLTAGE2()!=null){sql.append(",VOLTAGE2='"+po.getVOLTAGE2()+"'");}
		if(po.getVOLTAGE3()!=null){sql.append(",VOLTAGE3='"+po.getVOLTAGE3()+"'");}
		if(po.getVOLTAGE4()!=null){sql.append(",VOLTAGE4='"+po.getVOLTAGE4()+"'");}
		if(po.getPRESSURE1()!=null){sql.append(",PRESSURE1='"+po.getPRESSURE1()+"'");}
		if(po.getPRESSURE2()!=null){sql.append(",PRESSURE2='"+po.getPRESSURE2()+"'");}
		
		if(po.getPRESSURE3()!=null){sql.append(",PRESSURE3='"+po.getPRESSURE3()+"'");}
		if(po.getPRESSURE4()!=null){sql.append(",PRESSURE4='"+po.getPRESSURE4()+"'");}
		if(po.getSTAND_ADD_UP()!=null){sql.append(",STAND_ADD_UP='"+po.getSTAND_ADD_UP()+"'");}
		if(po.getSTAND_ADD_UP1()!=null){sql.append(",STAND_ADD_UP1='"+po.getSTAND_ADD_UP1()+"'");}
		if(po.getLEVELS1()!=null){sql.append(",LEVELS1='"+po.getLEVELS1()+"'");}
		if(po.getCURRENT_VOLUME1()!=null){sql.append(",CURRENT_VOLUME1='"+po.getCURRENT_VOLUME1()+"'");}
		if(po.getWORK_ADD_UP()!=null){sql.append(",WORK_ADD_UP='"+po.getWORK_ADD_UP()+"'");}
		if(po.getWORK_ADD_UP1()!=null){sql.append(",WORK_ADD_UP1='"+po.getWORK_ADD_UP1()+"'");}
		if(po.getSTAND_FLOW()!=null){sql.append(",STAND_FLOW='"+po.getSTAND_FLOW()+"'");}
		if(po.getSTAND_FLOW1()!=null){sql.append(",STAND_FLOW1='"+po.getSTAND_FLOW1()+"'");}
		
		if(po.getWORK_FLOW()!=null){sql.append(",WORK_FLOW='"+po.getWORK_FLOW()+"'");}
		if(po.getWORK_FLOW1()!=null){sql.append(",WORK_FLOW1='"+po.getWORK_FLOW1()+"'");}
		if(po.getGAS1()!=null){sql.append(",GAS1='"+po.getGAS1()+"'");}
		if(po.getA1()!=null){sql.append(",A1='"+po.getA1()+"'");}
		if(po.getA2()!=null){sql.append(",A2='"+po.getA2()+"'");}
		if(po.getA3()!=null){sql.append(",A3='"+po.getA3()+"'");}
		if(po.getA4()!=null){sql.append(",A4='"+po.getA4()+"'");}
		if(po.getA5()!=null){sql.append(",A5='"+po.getA5()+"'");}
		if(po.getA6()!=null){sql.append(",A6='"+po.getA6()+"'");}
		
		if(po.getB1()!=null){sql.append(",B1='"+po.getB1()+"'");}
		if(po.getB2()!=null){sql.append(",B2='"+po.getB2()+"'");}
		if(po.getB3()!=null){sql.append(",B3='"+po.getB3()+"'");}
		if(po.getB4()!=null){sql.append(",B4='"+po.getB4()+"'");}
		if(po.getB5()!=null){sql.append(",B5='"+po.getB5()+"'");}
		if(po.getB6()!=null){sql.append(",B6='"+po.getB6()+"'");}
		if(po.getB7()!=null){sql.append(",B7='"+po.getB7()+"'");}
		if(po.getC1()!=null){sql.append(",C1='"+po.getC1()+"'");}
		if(po.getC2()!=null){sql.append(",C2='"+po.getC2()+"'");}
		if(po.getC3()!=null){sql.append(",C3='"+po.getC3()+"'");}
		if(po.getC4()!=null){sql.append(",C4='"+po.getC4()+"'");}
		if(po.getC5()!=null){sql.append(",C5='"+po.getC5()+"'");}
		if(po.getF91()!=null){sql.append(",F91='"+po.getF91()+"'");}
		if(po.getF92()!=null){sql.append(",F92='"+po.getF92()+"'");}
		if(po.getF93()!=null){sql.append(",F93='"+po.getF93()+"'");}
		if(po.getUse_volume()!=null){sql.append(",use_volume='"+po.getUse_volume()+"'");}
		if(po.getAdd_volume()!=null){sql.append(",add_volume='"+po.getAdd_volume()+"'");}
		if(po.getSTOREUNITNUM()!=null){sql.append(",STOREUNITNUM='"+po.getSTOREUNITNUM()+"'");}
		if(po.getTANKPRESSURE3()!=null){sql.append(",TANKPRESSURE3='"+po.getTANKPRESSURE3()+"'");}
		if(po.getTANKPRESSURE4()!=null){sql.append(",TANKPRESSURE4='"+po.getTANKPRESSURE4()+"'");}
		if(po.getLEVELS3()!=null){sql.append(",LEVELS3='"+po.getLEVELS3()+"'");}
		if(po.getLEVELS4()!=null){sql.append(",LEVELS4='"+po.getLEVELS4()+"'");}
		sql.append(" where EQUIPMENT_ID='" + po.getEQUIPMENT_ID()+"'");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());
		return Result;
	}
	
	
	/**
	 * 插入数据的历史记录
	 * @param po
	 * @return
	 */
	public int AddTaData(TaDataPo po){
		StringBuilder sql=new StringBuilder("insert into ta_equipmet_data (");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("EQUIPMENT_ID");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",DA_UP");}
		if(po.getLONGITUDE()!=null){sql.append(",LONGITUDE");}
		if(po.getLATITUDE()!=null ){sql.append(",LATITUDE");}
		if(po.getDIRECTION()!=null){sql.append(",DIRECTION");}
		if(po.getTEMPERATURE()!=null){sql.append(",TEMPERATURE");}
		if(po.getSPEED()!=null){sql.append(",SPEED");}
		if(po.getHEIGTH()!=null){sql.append(",HEIGTH");}
		if(po.getMILEAGE()!=null){sql.append(",MILEAGE");}
		if(po.getPRESSURE()!=null){sql.append(",PRESSURE");}
		
		if(po.getLEVELS()!=null){sql.append(",LEVELS");}
		if(po.getVACUUM()!=null){sql.append(",VACUUM");}
		if(po.getDOOR()!=null){sql.append(",DOOR");}
		if(po.getGAS()!=null){sql.append(",GAS");}
		if(po.getDIP_X()!=null){sql.append(",DIP_X");}
		if(po.getR_LATITUDE()!=null){sql.append(",R_LATITUDE");}
		if(po.getR_LONGITUDE()!=null){sql.append(",R_LONGITUDE");}
		if(po.getFUEL()!=null){sql.append(",FUEL");}
		if(po.getIS_LOCATION()!=null){sql.append(",IS_LOCATION");}
		if(po.getACCELERATOR_X()!=null){sql.append(",ACCELERATOR_X");}
		
		if(po.getSTATE()!=null){sql.append(",STATE");}
		if(po.getPLACE()!=null){sql.append(",PLACE");}
		if(po.getDIP_Y()!=null){sql.append(",DIP_Y");}
		if(po.getACCELERATOR_Y()!=null){sql.append(",ACCELERATOR_Y");}
		if(po.getCURRENT_VOLUME()!=null){sql.append(",CURRENT_VOLUME");}
		if(po.getELECTRIC()!=null){sql.append(",ELECTRIC");}
		if(po.getTEMPERATURE1()!=null){sql.append(",TEMPERATURE1");}
		if(po.getTEMPERATURE2()!=null){sql.append(",TEMPERATURE2");}
		if(po.getTEMPERATURE3()!=null){sql.append(",TEMPERATURE3");}
		if(po.getTEMPERATURE4()!=null){sql.append(",TEMPERATURE4");}

		if(po.getAddSmellyNumber()!=null){sql.append(",addSmellyNumber");}
		
		if(po.getDOOR1()!=null){sql.append(",DOOR1");}
		if(po.getDOOR2()!=null){sql.append(",DOOR2");}
		if(po.getDOOR3()!=null){sql.append(",DOOR3");}
		if(po.getDOOR4()!=null){sql.append(",DOOR4");}
		if(po.getVOLTAGE1()!=null){sql.append(",VOLTAGE1");}
		if(po.getVOLTAGE2()!=null){sql.append(",VOLTAGE2");}
		if(po.getVOLTAGE3()!=null){sql.append(",VOLTAGE3");}
		if(po.getVOLTAGE4()!=null){sql.append(",VOLTAGE4");}
		if(po.getPRESSURE1()!=null){sql.append(",PRESSURE1");}
		if(po.getPRESSURE2()!=null){sql.append(",PRESSURE2");}
		
		if(po.getPRESSURE3()!=null){sql.append(",PRESSURE3");}
		if(po.getPRESSURE4()!=null){sql.append(",PRESSURE4");}
		if(po.getSTAND_ADD_UP()!=null){sql.append(",STAND_ADD_UP");}
		if(po.getSTAND_ADD_UP1()!=null){sql.append(",STAND_ADD_UP1");}
		if(po.getLEVELS1()!=null){sql.append(",LEVELS1");}
		if(po.getCURRENT_VOLUME1()!=null){sql.append(",CURRENT_VOLUME1");}
		if(po.getWORK_ADD_UP()!=null){sql.append(",WORK_ADD_UP");}
		if(po.getWORK_ADD_UP1()!=null){sql.append(",WORK_ADD_UP1");}
		if(po.getSTAND_FLOW()!=null){sql.append(",STAND_FLOW");}
		if(po.getSTAND_FLOW1()!=null){sql.append(",STAND_FLOW1");}
		
		if(po.getWORK_FLOW()!=null){sql.append(",WORK_FLOW");}
		if(po.getWORK_FLOW1()!=null){sql.append(",WORK_FLOW1");}
		if(po.getGAS1()!=null){sql.append(",GAS1");}
		if(po.getA1()!=null){sql.append(",A1");}
		if(po.getA2()!=null){sql.append(",A2");}
		if(po.getA3()!=null){sql.append(",A3");}
		if(po.getA4()!=null){sql.append(",A4");}
		if(po.getA5()!=null){sql.append(",A5");}
		if(po.getA6()!=null){sql.append(",A6");}
		
		if(po.getB1()!=null){sql.append(",B1");}
		if(po.getB2()!=null){sql.append(",B2");}
		if(po.getB3()!=null){sql.append(",B3");}
		if(po.getB4()!=null){sql.append(",B4");}
		if(po.getB5()!=null){sql.append(",B5");}
		if(po.getB6()!=null){sql.append(",B6");}
		if(po.getB7()!=null){sql.append(",B7");}
		if(po.getC1()!=null){sql.append(",C1");}
		if(po.getC2()!=null){sql.append(",C2");}
		if(po.getC3()!=null){sql.append(",C3");}
		if(po.getC4()!=null){sql.append(",C4");}
		if(po.getC5()!=null){sql.append(",C5");}
		if(po.getF91()!=null){sql.append(",F91");}
		if(po.getF92()!=null){sql.append(",F92");}
		if(po.getF93()!=null){sql.append(",F93");}
		if(po.getUse_volume()!=null){sql.append(",use_volume");}
		if(po.getAdd_volume()!=null){sql.append(",add_volume");}
		if(po.getSTOREUNITNUM()!=null){sql.append(",STOREUNITNUM");}		
		if(po.getTANKPRESSURE3()!=null){sql.append(",TANKPRESSURE3");}
		if(po.getTANKPRESSURE4()!=null){sql.append(",TANKPRESSURE4");}
		if(po.getLEVELS3()!=null){sql.append(",LEVELS3");}
		if(po.getLEVELS4()!=null){sql.append(",LEVELS4");}
		sql.append(") values(");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("'"+po.getEQUIPMENT_ID()+"'");}
		if(po.getDA_UP()!=null &&! "".equals(po.getDA_UP())){sql.append(",'"+po.getDA_UP()+"'");}
		if(po.getLONGITUDE()!=null){sql.append(",'"+po.getLONGITUDE()+"'");}
		if(po.getLATITUDE()!=null){sql.append(",'"+po.getLATITUDE()+"'");}
		if(po.getDIRECTION()!=null){sql.append(",'"+po.getDIRECTION()+"'");}
		if(po.getTEMPERATURE()!=null){sql.append(",'"+po.getTEMPERATURE()+"'");}
		
		if(po.getSPEED()!=null){sql.append(",'"+po.getSPEED()+"'");}
		if(po.getHEIGTH()!=null){sql.append(",'"+po.getHEIGTH()+"'");}
		if(po.getMILEAGE()!=null){sql.append(",'"+po.getMILEAGE()+"'");}
		if(po.getPRESSURE()!=null){sql.append(",'"+po.getPRESSURE()+"'");}
		
		if(po.getLEVELS()!=null){sql.append(",'"+po.getLEVELS()+"'");}
		if(po.getVACUUM()!=null){sql.append(",'"+po.getVACUUM()+"'");}
		if(po.getDOOR()!=null){sql.append(",'"+po.getDOOR()+"'");}
		if(po.getGAS()!=null){sql.append(",'"+po.getGAS()+"'");}
		if(po.getDIP_X()!=null){sql.append(",'"+po.getDIP_X()+"'");}
		if(po.getR_LATITUDE()!=null){sql.append(",'"+po.getR_LATITUDE()+"'");}
		if(po.getR_LONGITUDE()!=null){sql.append(",'"+po.getR_LONGITUDE()+"'");}
		if(po.getFUEL()!=null){sql.append(",'"+po.getFUEL()+"'");}
		if(po.getIS_LOCATION()!=null){sql.append(",'"+po.getIS_LOCATION()+"'");}
		if(po.getACCELERATOR_X()!=null){sql.append(",'"+po.getACCELERATOR_X()+"'");}
		
		if(po.getSTATE()!=null){sql.append(",'"+po.getSTATE()+"'");}
		if(po.getPLACE()!=null){sql.append(",'"+po.getPLACE()+"'");}
		if(po.getDIP_Y()!=null){sql.append(",'"+po.getDIP_Y()+"'");}
		if(po.getACCELERATOR_Y()!=null){sql.append(",'"+po.getACCELERATOR_Y()+"'");}
		if(po.getCURRENT_VOLUME()!=null){sql.append(",'"+po.getCURRENT_VOLUME()+"'");}
		if(po.getELECTRIC()!=null){sql.append(",'"+po.getELECTRIC()+"'");}
		if(po.getTEMPERATURE1()!=null){sql.append(",'"+po.getTEMPERATURE1()+"'");}
		if(po.getTEMPERATURE2()!=null){sql.append(",'"+po.getTEMPERATURE2()+"'");}
		if(po.getTEMPERATURE3()!=null){sql.append(",'"+po.getTEMPERATURE3()+"'");}
		if(po.getTEMPERATURE4()!=null){sql.append(",'"+po.getTEMPERATURE4()+"'");}

		if(po.getAddSmellyNumber()!=null){sql.append(",'"+po.getAddSmellyNumber()+"'");}
		
		if(po.getDOOR1()!=null){sql.append(",'"+po.getDOOR1()+"'");}
		if(po.getDOOR2()!=null){sql.append(",'"+po.getDOOR2()+"'");}
		if(po.getDOOR3()!=null){sql.append(",'"+po.getDOOR3()+"'");}
		if(po.getDOOR4()!=null){sql.append(",'"+po.getDOOR4()+"'");}
		if(po.getVOLTAGE1()!=null){sql.append(",'"+po.getVOLTAGE1()+"'");}
		if(po.getVOLTAGE2()!=null){sql.append(",'"+po.getVOLTAGE2()+"'");}
		if(po.getVOLTAGE3()!=null){sql.append(",'"+po.getVOLTAGE3()+"'");}
		if(po.getVOLTAGE4()!=null){sql.append(",'"+po.getVOLTAGE4()+"'");}
		if(po.getPRESSURE1()!=null){sql.append(",'"+po.getPRESSURE1()+"'");}
		if(po.getPRESSURE2()!=null){sql.append(",'"+po.getPRESSURE2()+"'");}
		
		if(po.getPRESSURE3()!=null){sql.append(",'"+po.getPRESSURE3()+"'");}
		if(po.getPRESSURE4()!=null){sql.append(",'"+po.getPRESSURE4()+"'");}
		if(po.getSTAND_ADD_UP()!=null){sql.append(",'"+po.getSTAND_ADD_UP()+"'");}
		if(po.getSTAND_ADD_UP1()!=null){sql.append(",'"+po.getSTAND_ADD_UP1()+"'");}
		if(po.getLEVELS1()!=null){sql.append(",'"+po.getLEVELS1()+"'");}
		if(po.getCURRENT_VOLUME1()!=null){sql.append(",'"+po.getCURRENT_VOLUME1()+"'");}
		if(po.getWORK_ADD_UP()!=null){sql.append(",'"+po.getWORK_ADD_UP()+"'");}
		if(po.getWORK_ADD_UP1()!=null){sql.append(",'"+po.getWORK_ADD_UP1()+"'");}
		if(po.getSTAND_FLOW()!=null){sql.append(",'"+po.getSTAND_FLOW()+"'");}
		if(po.getSTAND_FLOW1()!=null){sql.append(",'"+po.getSTAND_FLOW1()+"'");}
		
		if(po.getWORK_FLOW()!=null){sql.append(",'"+po.getWORK_FLOW()+"'");}
		if(po.getWORK_FLOW1()!=null){sql.append(",'"+po.getWORK_FLOW1()+"'");}
		if(po.getGAS1()!=null){sql.append(",'"+po.getGAS1()+"'");}
		if(po.getA1()!=null){sql.append(",'"+po.getA1()+"'");}
		if(po.getA2()!=null){sql.append(",'"+po.getA2()+"'");}
		if(po.getA3()!=null){sql.append(",'"+po.getA3()+"'");}
		if(po.getA4()!=null){sql.append(",'"+po.getA4()+"'");}
		if(po.getA5()!=null){sql.append(",'"+po.getA5()+"'");}
		if(po.getA6()!=null){sql.append(",'"+po.getA6()+"'");}
		
		if(po.getB1()!=null){sql.append(",'"+po.getB1()+"'");}
		if(po.getB2()!=null){sql.append(",'"+po.getB2()+"'");}
		if(po.getB3()!=null){sql.append(",'"+po.getB3()+"'");}
		if(po.getB4()!=null){sql.append(",'"+po.getB4()+"'");}
		if(po.getB5()!=null){sql.append(",'"+po.getB5()+"'");}
		if(po.getB6()!=null){sql.append(",'"+po.getB6()+"'");}
		if(po.getB7()!=null){sql.append(",'"+po.getB7()+"'");}
		if(po.getC1()!=null){sql.append(",'"+po.getC1()+"'");}
		if(po.getC2()!=null){sql.append(",'"+po.getC2()+"'");}
		if(po.getC3()!=null){sql.append(",'"+po.getC3()+"'");}
		if(po.getC4()!=null){sql.append(",'"+po.getC4()+"'");}
		if(po.getC5()!=null){sql.append(",'"+po.getC5()+"'");}
		if(po.getF91()!=null){sql.append(",'"+po.getF91()+"'");}
		if(po.getF92()!=null){sql.append(",'"+po.getF92()+"'");}
		if(po.getF93()!=null){sql.append(",'"+po.getF93()+"'");}
		if(po.getUse_volume()!=null){sql.append(",'"+po.getUse_volume()+"'");}
		if(po.getAdd_volume()!=null){sql.append(",'"+po.getAdd_volume()+"'");}
		if(po.getSTOREUNITNUM()!=null){sql.append(",'"+po.getSTOREUNITNUM()+"'");}		
		if(po.getTANKPRESSURE3()!=null){sql.append(",'"+po.getTANKPRESSURE3()+"'");}
		if(po.getTANKPRESSURE4()!=null){sql.append(",'"+po.getTANKPRESSURE4()+"'");}
		if(po.getLEVELS3()!=null){sql.append(",'"+po.getLEVELS3()+"'");}
		if(po.getLEVELS4()!=null){sql.append(",'"+po.getLEVELS4()+"'");}		
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(Result+"  "+sql.toString());
		return Result;
	}
	
	/*public static void main(String[] args) {
		TaDataDal taDataDal=new TaDataDal();
		 List<Map<String, Object>>  ls=taDataDal.selectTaTankById("10016300001");
		 System.out.println("ls:"+ls.size());
		 Double Density =Double.valueOf(ls.get(0).get("STOREUNITNUM").toString());
		 System.out.println(Density);
		//TaDataDal taDataDal=new TaDataDal();
		//List<Map<String, Object>> ls_map=taDataDal.selectTaTankById("12351545");
		for(Map<String, Object> map:ls){
			for(String str:map.keySet()){
				System.out.println(str+" = "+map.get(str));
			}
			System.out.println("=================================");
		}
	}*/
}
