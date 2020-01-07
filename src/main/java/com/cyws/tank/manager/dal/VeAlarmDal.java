package com.cyws.tank.manager.dal;

import com.cyws.tank.manager.common.DBBean;
import com.cyws.tank.manager.db.DBHandleHelper;
import com.cyws.tank.manager.po.VeAlarmPo;

public class VeAlarmDal {

    private DBHandleHelper DbHelper=new DBHandleHelper();
	
	public int AddVeAlarm(VeAlarmPo po){
		StringBuilder sql=new StringBuilder("insert into ve_alarm (");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("EQUIPMENT_ID");}
		if(po.getTYPE()!=null){sql.append(",TYPE");}
		if(po.getACTIVER()!=null){sql.append(",ACTIVER");}
		if(po.getALARM()!=null ){sql.append(",ALARM");}
		if(po.getDA_UP()!=null){sql.append(",DA_UP");}		
		sql.append(") values(");
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append("'"+po.getEQUIPMENT_ID()+"'");}
		if(po.getTYPE()!=null){sql.append(",'"+po.getTYPE()+"'");}
		if(po.getACTIVER()!=null){sql.append(",'"+po.getACTIVER()+"'");}
		if(po.getALARM()!=null ){sql.append(",'"+po.getALARM()+"'");}
		if(po.getDA_UP()!=null){sql.append(",'"+po.getDA_UP()+"'");}
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());  
		return Result;
	}
}
