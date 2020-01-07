package com.cyws.tank.manager.bll;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.dal.TaLocationDal;
import com.cyws.tank.manager.po.TaLocationPo;

public class TaLocationBll {
	private TaLocationDal taLocationDal=new TaLocationDal();
	
	//处理位置信息插入
		public int addLocationTaDataNew(TaLocationPo po){
			List<Map<String, Object>> ls_taData=taLocationDal.selectTaLocationByID(po.getEQUIPMENT_ID());
			if(ls_taData.isEmpty()){
				//插入最新表数据
				taLocationDal.AddTaLocationNew(po);
			}else{
				//更新最新表数据
				taLocationDal.updateTaLocationNew(po);			
			}
			//插入历史数据
			taLocationDal.AddLocation(po);
			return 1;
		}
		

}
