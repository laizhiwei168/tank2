package com.cyws.tank.manager.bll;

import com.cyws.tank.manager.cache.EquipmetEventCache;
import com.cyws.tank.manager.dal.EquipmetEventDal;
import com.cyws.tank.manager.po.EquipmetEventParameterPo;
import com.cyws.tank.manager.po.EquipmetEventPo;

public class EquipmetEventBll {

	private EquipmetEventDal equipmetEventDal=new EquipmetEventDal();
	private EquipmetEventCache equipmetEventCache=new EquipmetEventCache();
	
	public EquipmetEventParameterPo selectEquipmetEventParameter(String equipmetNo ,String clientId){
		EquipmetEventParameterPo equipmetEventParameterPo=equipmetEventCache.selectEquipmetEventParameter(equipmetNo, clientId);
		return equipmetEventParameterPo;
	}
	
	public int AddEquipmetEvent(EquipmetEventPo po){
		int i=equipmetEventDal.AddEquipmetEvent(po);
		return i;
	}
	
}
