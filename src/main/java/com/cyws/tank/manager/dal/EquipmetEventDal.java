package com.cyws.tank.manager.dal;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.common.DBBean;
import com.cyws.tank.manager.db.DBHandleHelper;
import com.cyws.tank.manager.po.EquipmetEventPo;

public class EquipmetEventDal {

	private DBHandleHelper DbHelper=new DBHandleHelper();
	
	public List<Map<String, Object>> selectEquipmetEventParameter(String equipmetNo ,String clientId){
		String SQLString="select * FROM ta_equipmet_event_parameter where  equipmetNo='"+equipmetNo+"' and clientId='"+clientId+"' and isDelete=0";
		List<Map<String, Object>> ls_gx_cammand= DbHelper.Query(DBBean.gndata,SQLString);		
		return ls_gx_cammand;
	}
	
	public int AddEquipmetEvent(EquipmetEventPo po){
		StringBuilder sql=new StringBuilder("insert into ta_equipmet_event (");
		if(po.getEquipmetNo()!=null && !"".equals(po.getEquipmetNo())){sql.append("equipmetNo");}
		if(po.getClientId()!=null && !"".equals(po.getClientId())){sql.append(",clientId");}
		if(po.getEventTypeId()!=null){sql.append(",eventTypeId");}
		if(po.getReferenceValue()!=null && !"".equals(po.getReferenceValue())){sql.append(",referenceValue");}
		if(po.getCurrentValue() !=null && !"".equals(po.getCurrentValue()) ){sql.append(",currentValue");}
		if(po.getEventContent()!=null && !"".equals(po.getEventContent())){sql.append(",eventContent");}
		if(po.getPushMobiles()!=null && !"".equals(po.getPushMobiles())){sql.append(",pushMobiles");}
		if(po.getPushEmail()!=null && !"".equals(po.getPushEmail())){sql.append(",pushEmail");}		
		sql.append(") values(");
		if(po.getEquipmetNo()!=null && !"".equals(po.getEquipmetNo())){sql.append("'"+po.getEquipmetNo()+"'");}
		if(po.getClientId()!=null && !"".equals(po.getClientId())){sql.append(",'"+po.getClientId()+"'");}
		if(po.getEventTypeId()!=null){sql.append(",'"+po.getEventTypeId()+"'");}
		if(po.getReferenceValue()!=null  && !"".equals(po.getReferenceValue())){sql.append(",'"+po.getReferenceValue()+"'");}
		if(po.getCurrentValue()!=null  && !"".equals(po.getCurrentValue())){sql.append(",'"+po.getCurrentValue()+"'");}
		if(po.getEventContent()!=null && !"".equals(po.getEventContent())){sql.append(",'"+po.getEventContent()+"'");}
		if(po.getPushMobiles()!=null && !"".equals(po.getPushMobiles())){sql.append(",'"+po.getPushMobiles()+"'");}
		if(po.getPushEmail()!=null && !"".equals(po.getPushEmail())){sql.append(",'"+po.getPushEmail()+"'");}
		sql.append(")");
		int Result=DbHelper.ExecuteSql(DBBean.gndata,sql.toString());
		//System.out.println(sql.toString());
		return Result;
	}
	
	
	
}
