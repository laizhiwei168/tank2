package com.cyws.tank.manager.bll;

import com.cyws.tank.manager.dal.VeAlarmDal;
import com.cyws.tank.manager.po.VeAlarmPo;

public class VeAlarmBll {
	VeAlarmDal dal=new VeAlarmDal();
	
	public int AddAlarm(VeAlarmPo po){
		return dal.AddVeAlarm(po);
	}
}
