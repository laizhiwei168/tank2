package com.cyws.tank.manager.controller;

import com.cyws.tank.bean.common.MgSetEventConsts;
import com.cyws.tank.manager.bll.MgSetBll;
import com.cyws.tank.manager.po.MgSetEventBean;
import com.cyws.tank.manager.po.MgSetPo;
import com.cyws.tank.utils.common.DateUtils;
import com.cyws.tank.utils.common.StringUtils;
import com.cyws.tank.utils.map.MgSetEventMap;

public class MgSetController {

	private MgSetBll mgSetBll=new MgSetBll();
	
	public void handleMgSet(MgSetEventBean mgSetEventBean){
		String EQUIPMENT_ID= mgSetEventBean.getEQUIPMENT_ID();
		String datetime=DateUtils.getDoDayTime();
		MgSetPo po=new MgSetPo();
		po.setID(StringUtils.createID());
		po.setEQUIPMENT_ID(EQUIPMENT_ID);
		po.setSETER_ID("");// 设置人
		po.setSEND_TYPE(2);// 发送类型		
		po.setDA_ADD(datetime);// 添加时间
		//po.setSET_MOBILE(sET_MOBILE); // 报警手机号码
		//po.setSET_NAME(sET_NAME);// 称呼
		po.setIS_DELETE(0);// 是否删除
		// 液位
		if(mgSetEventBean.getLEVELS_TYPE()!=null && mgSetEventBean.getLEVELS_VALUE()!=null){
			int EventType=0;
			if(mgSetEventBean.getLEVELS_TYPE()==0){
				po.setLEVEL_UP(mgSetEventBean.getLEVELS_VALUE());// 上限
				EventType=MgSetEventConsts.mgset_event_type_levels_up;
			}else if(mgSetEventBean.getLEVELS_TYPE()==1){
				po.setLEVEL_DOWN(mgSetEventBean.getLEVELS_VALUE());// 下限
				EventType=MgSetEventConsts.mgset_event_type_levels_down;
			}
			po.setTYPES(2); // 列表（液位2、压力3、出口压力5、温度7，气体泄漏11，停车报警13）
			// 逻辑判断设备是否超过上报时间
			boolean is_compareOut30Minute= MgSetEventMap.getInstance().compareOut30Minute(EQUIPMENT_ID, EventType, datetime);
			if(is_compareOut30Minute){
				mgSetBll.AddMgSet(po);
			}
		}
		// 压力
		if(mgSetEventBean.getPRESSURE_TYPE()!=null && mgSetEventBean.getPRESSURE_VALUE()!=null){
			int EventType=0;
			if(mgSetEventBean.getPRESSURE_TYPE()==0){
				po.setLEVEL_UP(mgSetEventBean.getPRESSURE_VALUE());// 上限
				EventType=MgSetEventConsts.mgset_event_type_pressure_up;
			}else if(mgSetEventBean.getPRESSURE_TYPE()==1){
				po.setLEVEL_DOWN(mgSetEventBean.getPRESSURE_VALUE());// 下限
				EventType=MgSetEventConsts.mgset_event_type_pressure_down;
			}			
			po.setTYPES(3); // 列表（液位2、压力3、出口压力5、温度7，气体泄漏11，停车报警13）
			// 逻辑判断设备是否超过上报时间
			boolean is_compareOut30Minute= MgSetEventMap.getInstance().compareOut30Minute(EQUIPMENT_ID, EventType, datetime);
			if(is_compareOut30Minute){
				mgSetBll.AddMgSet(po);
			}
		}
		
		// 气体泄漏
		if(mgSetEventBean.getVACUUM_TYPE()!=null && mgSetEventBean.getVACUUM_VALUE()!=null){
			int EventType=0;
			if(mgSetEventBean.getVACUUM_TYPE()==0){
				po.setLEVEL_UP(mgSetEventBean.getVACUUM_VALUE());// 上限
				EventType=MgSetEventConsts.mgset_event_type_vacuum_up;
			}else if(mgSetEventBean.getVACUUM_TYPE()==1){
				po.setLEVEL_DOWN(mgSetEventBean.getVACUUM_VALUE());// 下限
				EventType=MgSetEventConsts.mgset_event_type_vacuum_down;
			}			
			po.setTYPES(11); // 列表（液位2、压力3、出口压力5、温度7，气体泄漏11，停车报警13）
			// 逻辑判断设备是否超过上报时间
			boolean is_compareOut30Minute= MgSetEventMap.getInstance().compareOut30Minute(EQUIPMENT_ID, EventType, datetime);
			if(is_compareOut30Minute){
				//处理设备上报记录
				mgSetBll.AddMgSet(po);
			}
		}
	}
}
