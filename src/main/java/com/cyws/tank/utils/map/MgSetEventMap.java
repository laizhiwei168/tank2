package com.cyws.tank.utils.map;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cyws.tank.bean.common.MgSetEventConsts;
import com.cyws.tank.utils.common.DateUtils;
import com.cyws.tank.utils.po.MgSetEventMapPo;

/**
 * 主要处理设备上报不同的事件上报时间记录类
 * */
public class MgSetEventMap {
	 private Map<String,MgSetEventMapPo>  MgSetEventCache = new ConcurrentHashMap<>();
	 
	 private MgSetEventMap(){}	 
	 private static MgSetEventMap _instance=new MgSetEventMap();
	 
	 public static MgSetEventMap getInstance(){
		 return _instance;
	 }
	 
	// 逻辑判断设备上传上报时间与现在上报时间对比，是否超过30分钟，如果是则下发，否则就过滤掉
		 public boolean compareOut30Minute(String EQUIPMENT_ID,int EventType,String datetime){
			 boolean is_compare=false;
			 String old_dateTime=null;
			 MgSetEventMapPo po=MgSetEventCache.get(EQUIPMENT_ID);
			 if(po!=null){
				 switch (EventType) {
					case MgSetEventConsts.mgset_event_type_levels_up:
						if(po.getLEVELS_UP_DATETIME()!=null){
							old_dateTime=po.getLEVELS_UP_DATETIME();
						}
						break;	
					case MgSetEventConsts.mgset_event_type_levels_down:
						if(po.getLEVELS_DOWN_DATETIME()!=null){
							old_dateTime=po.getLEVELS_DOWN_DATETIME();
						}
						break;
					case MgSetEventConsts.mgset_event_type_pressure_up:
						if(po.getPRESSURE_UP_DATETIME()!=null){
							old_dateTime=po.getPRESSURE_UP_DATETIME();
						}
						break;
					case MgSetEventConsts.mgset_event_type_pressure_down:
						if(po.getPRESSURE_DOWN_DATETIME()!=null){
							old_dateTime=po.getPRESSURE_DOWN_DATETIME();
						}
						break;
					case MgSetEventConsts.mgset_event_type_vacuum_up:
						if(po.getVACUUM_UP_DATETIME()!=null){
							old_dateTime=po.getVACUUM_UP_DATETIME();
						}
						break;
					case MgSetEventConsts.mgset_event_type_vacuum_down:
						if(po.getVACUUM_DOWN_DATETIME()!=null){
							old_dateTime=po.getVACUUM_DOWN_DATETIME();
						}
						break;
					}
			 }
			 if(old_dateTime!=null){
				 try {
					long date_long=DateUtils.getDatePoor(old_dateTime, datetime);
					if(date_long>30){
						is_compare=true;
						addMgSetEvent(EQUIPMENT_ID, EventType, datetime);
					}
				 } catch (ParseException e) {
					e.printStackTrace();
				 }
			 }else{
				 is_compare=true;
				 addMgSetEvent(EQUIPMENT_ID, EventType, datetime);
			 }
			 return is_compare;
		 }

	 // 存储设备在触发不同事件的时间
	 private void addMgSetEvent(String EQUIPMENT_ID,int EventType,String dateTime){
		 MgSetEventMapPo po=MgSetEventCache.get(EQUIPMENT_ID);
		 if(po!=null){
			 switch (EventType) {
				case MgSetEventConsts.mgset_event_type_levels_up:					
					po.setLEVELS_UP_DATETIME(dateTime);
					break;	
				case MgSetEventConsts.mgset_event_type_levels_down:					
					po.setLEVELS_DOWN_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_pressure_up:					
					po.setPRESSURE_UP_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_pressure_down:					
					po.setPRESSURE_DOWN_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_vacuum_up:					
					po.setVACUUM_UP_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_vacuum_down:					
					po.setVACUUM_DOWN_DATETIME(dateTime);
					break;
				}
		 }else{
			  po=new MgSetEventMapPo();
			  switch (EventType) {
				case MgSetEventConsts.mgset_event_type_levels_up:
					po.setLEVELS_UP_DATETIME(dateTime);
					break;	
				case MgSetEventConsts.mgset_event_type_levels_down:
					po.setLEVELS_DOWN_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_pressure_up:
					po.setPRESSURE_UP_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_pressure_down:
					po.setPRESSURE_DOWN_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_vacuum_up:
					po.setVACUUM_UP_DATETIME(dateTime);
					break;
				case MgSetEventConsts.mgset_event_type_vacuum_down:
					po.setVACUUM_DOWN_DATETIME(dateTime);
					break;
				}
			  MgSetEventCache.put(EQUIPMENT_ID, po);
		 }
	 } 
	 	 	  
}
