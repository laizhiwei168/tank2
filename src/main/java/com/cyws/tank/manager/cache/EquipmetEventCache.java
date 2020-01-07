package com.cyws.tank.manager.cache;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.dal.EquipmetEventDal;
import com.cyws.tank.manager.po.EquipmetEventParameterPo;
import com.cyws.tank.manager.po.EventParameterPo;

public class EquipmetEventCache {
	private EquipmetEventDal equipmetEventDal=new EquipmetEventDal();
	
	public EquipmetEventParameterPo selectEquipmetEventParameter(String equipmetNo ,String clientId){
		EquipmetEventParameterPo equipmetEventParameter =new EquipmetEventParameterPo();
		List<Map<String, Object>> ls_EquipmetEventParameter=equipmetEventDal.selectEquipmetEventParameter(equipmetNo, clientId);
		
		if(!ls_EquipmetEventParameter.isEmpty()){
			for(Map<String, Object> map: ls_EquipmetEventParameter){
				int eventtype=(int)map.get("eventTypeId");
				EventParameterPo eventParameterPo=handleParameter(map);
				HandleDeviceparameter(eventtype, eventParameterPo, equipmetEventParameter);
			}
		}
		
		return equipmetEventParameter;
	}
	
	private EventParameterPo handleParameter(Map<String, Object> map){
		EventParameterPo po=new EventParameterPo();		 
		if(map.get("defaultValue")!=null&& !"".equals(map.get("defaultValue"))){
			po.setDefaultValue(map.get("defaultValue").toString());
		}
		if(map.get("isSMS")!=null){
			po.setSMS((boolean) map.get("isSMS"));
		}
		if(map.get("mobiles")!=null && !"".equals(map.get("mobiles"))){
			po.setMobiles(map.get("mobiles").toString());
		}
		if(map.get("isEmail")!=null){
			po.setEmail((boolean) map.get("isEmail"));
		}
		if(map.get("emails")!=null && !"".equals(map.get("emails"))){
			po.setEmails(map.get("emails").toString());
		}
		return po;
	}
	
	private void HandleDeviceparameter(int eventtype,EventParameterPo eventParameter,EquipmetEventParameterPo equipmetEventParameter){
		// 设置基本事件参数类	
		switch (eventtype) {
		case 1:	
			equipmetEventParameter.setLevels_up(eventParameter);
			break;
		case 2:	
			equipmetEventParameter.setLevels_down(eventParameter);
			break;
		case 3:	
			equipmetEventParameter.setPressure_up(eventParameter);
			break;
		case 4:	
			equipmetEventParameter.setPressure_down(eventParameter);
			break;
		case 5:	
			equipmetEventParameter.setVacuum_up(eventParameter);
			break;
		case 6:	
			equipmetEventParameter.setVacuum_down(eventParameter);
			break;
		}
	}
}
