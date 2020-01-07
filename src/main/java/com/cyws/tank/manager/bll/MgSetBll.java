package com.cyws.tank.manager.bll;

import com.cyws.tank.manager.dal.MgSetDal;
import com.cyws.tank.manager.po.MgSetPo;

public class MgSetBll {

	private MgSetDal mgSetDal=new MgSetDal();
	
	public int AddMgSet(MgSetPo po){
		return mgSetDal.AddMgSet(po);
	}
}
