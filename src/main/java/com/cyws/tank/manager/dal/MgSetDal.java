package com.cyws.tank.manager.dal;

import com.cyws.tank.manager.common.DBBean;
import com.cyws.tank.manager.db.DBHandleHelper;
import com.cyws.tank.manager.po.MgSetPo;

public class MgSetDal {
	DBHandleHelper DbHelper=new DBHandleHelper();
	
	public int AddMgSet(MgSetPo po){
		StringBuilder sql=new StringBuilder("insert into mg_set (");
		if(po.getID()!=null && !"".equals(po.getID())){sql.append("ID");}
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append(",EQUIPMENT_ID");}
		if(po.getSETER_ID()!=null && !"".equals(po.getSETER_ID())){sql.append(",SETER_ID");}
		if(po.getSEND_TYPE()!=null){sql.append(",SEND_TYPE");}
		if(po.getLEVEL_DOWN()!=null ){sql.append(",LEVEL_DOWN");}
		if(po.getLEVEL_UP()!=null){sql.append(",LEVEL_UP");}
		if(po.getTYPES()!=null){sql.append(",TYPES");}
		if(po.getALARM_RATE()!=null){sql.append(",ALARM_RATE");}
		if(po.getDA_ADD()!=null &&! "".equals(po.getDA_ADD())){sql.append(",DA_ADD");}
		if(po.getDA_UPDATE()!=null &&! "".equals(po.getDA_UPDATE())){sql.append(",DA_UPDATE");}
		if(po.getSET_MOBILE()!=null &&! "".equals(po.getSET_MOBILE())){sql.append(",SET_MOBILE");}
		if(po.getSET_NAME()!=null &&! "".equals(po.getSET_NAME())){sql.append(",SET_NAME");}
		if(po.getIS_DELETE()!=null){sql.append(",IS_DELETE");}
		sql.append(") values(");
		if(po.getID()!=null && !"".equals(po.getID())){sql.append("'"+po.getID()+"'");}
		if(po.getEQUIPMENT_ID()!=null && !"".equals(po.getEQUIPMENT_ID())){sql.append(",'"+po.getEQUIPMENT_ID()+"'");}
		if(po.getSETER_ID()!=null && !"".equals(po.getSETER_ID())){sql.append(",'"+po.getSETER_ID()+"'");}
		if(po.getSEND_TYPE()!=null){sql.append(",'"+po.getSEND_TYPE()+"'");}
		if(po.getLEVEL_DOWN()!=null ){sql.append(",'"+po.getLEVEL_DOWN()+"'");}
		if(po.getLEVEL_UP()!=null){sql.append(",'"+po.getLEVEL_UP()+"'");}
		if(po.getTYPES()!=null){sql.append(",'"+po.getTYPES()+"'");}
		if(po.getALARM_RATE()!=null){sql.append(",'"+po.getALARM_RATE()+"'");}
		if(po.getDA_ADD()!=null &&! "".equals(po.getDA_ADD())){sql.append(",'"+po.getDA_ADD()+"'");}
		if(po.getDA_UPDATE()!=null &&! "".equals(po.getDA_UPDATE())){sql.append(",'"+po.getDA_UPDATE()+"'");}
		if(po.getSET_MOBILE()!=null &&! "".equals(po.getSET_MOBILE())){sql.append(",'"+po.getSET_MOBILE()+"'");}
		if(po.getSET_NAME()!=null &&! "".equals(po.getSET_NAME())){sql.append(",'"+po.getSET_NAME()+"'");}
		if(po.getIS_DELETE()!=null){sql.append(",'"+po.getIS_DELETE()+"'");}
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());
		return Result;
	}
	
}
