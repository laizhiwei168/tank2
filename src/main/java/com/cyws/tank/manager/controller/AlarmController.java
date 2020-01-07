package com.cyws.tank.manager.controller;

import com.cyws.tank.manager.bll.VeAlarmBll;
import com.cyws.tank.manager.po.VeAlarmPo;
import com.cyws.tank.utils.enums.AlarmEnum;
import com.cyws.tank.utils.po.FL_EventLimitPo;
import com.cyws.tank.utils.po.FL_EventValuesPo;

public class AlarmController {
	
	private VeAlarmBll veAlarmBll=new VeAlarmBll();
	
	public void handleAlarm(FL_EventLimitPo eventLimitPo,FL_EventValuesPo eventValuesPo){
		VeAlarmPo po=new VeAlarmPo();
		po.setEQUIPMENT_ID(eventValuesPo.getEQUIPMENT_ID());
		po.setDA_UP(eventValuesPo.getDA_UP());
		
		// 温度上限 
		if(eventLimitPo.getOutgoing_temperature_up()!=null){
			
			if(eventValuesPo.getWorkTemperature1()!=0) {
				if(eventValuesPo.getWorkTemperature1()>eventLimitPo.getOutgoing_temperature_up()) {					
					po.setTYPE(AlarmEnum.tank1_temperature_up.getCode());
					po.setALARM(AlarmEnum.tank1_temperature_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature2()!=0) {
				if(eventValuesPo.getWorkTemperature2()>eventLimitPo.getOutgoing_temperature_up()) {
					po.setTYPE(AlarmEnum.tank2_temperature_up.getCode());
					po.setALARM(AlarmEnum.tank2_temperature_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature3()!=0) {
				if(eventValuesPo.getWorkTemperature3()>eventLimitPo.getOutgoing_temperature_up()) {
					po.setTYPE(AlarmEnum.tank3_temperature_up.getCode());
					po.setALARM(AlarmEnum.tank3_temperature_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature4()!=0) {
				if(eventValuesPo.getWorkTemperature4()>eventLimitPo.getOutgoing_temperature_up()) {
					po.setTYPE(AlarmEnum.tank4_temperature_up.getCode());
					po.setALARM(AlarmEnum.tank4_temperature_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
		}
		
		// 温度下限
		if(eventLimitPo.getOutgoing_temperature_down()!=null){
			
			if(eventValuesPo.getWorkTemperature1()!=0) {
				if(eventValuesPo.getWorkTemperature1()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPE(AlarmEnum.tank1_temperature_down.getCode());
					po.setALARM(AlarmEnum.tank1_temperature_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature2()!=0) {
				if(eventValuesPo.getWorkTemperature2()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPE(AlarmEnum.tank2_temperature_down.getCode());
					po.setALARM(AlarmEnum.tank2_temperature_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature3()!=0) {
				if(eventValuesPo.getWorkTemperature3()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPE(AlarmEnum.tank3_temperature_down.getCode());
					po.setALARM(AlarmEnum.tank3_temperature_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature4()!=0) {
				if(eventValuesPo.getWorkTemperature4()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPE(AlarmEnum.tank4_temperature_down.getCode());
					po.setALARM(AlarmEnum.tank4_temperature_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}			
		}
		
		// 压力上限
		if(eventLimitPo.getOutgoing_pressure_up()!=null){
			
			if(eventValuesPo.getWorkPressure1()!=0) {
				if(eventValuesPo.getWorkPressure1()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPE(AlarmEnum.tank1_pressure_up.getCode());
					po.setALARM(AlarmEnum.tank1_pressure_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure2()!=0) {
				if(eventValuesPo.getWorkPressure2()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPE(AlarmEnum.tank2_pressure_up.getCode());
					po.setALARM(AlarmEnum.tank2_pressure_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure3()!=0) {
				if(eventValuesPo.getWorkPressure3()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPE(AlarmEnum.tank3_pressure_up.getCode());
					po.setALARM(AlarmEnum.tank3_pressure_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure4()!=0) {
				if(eventValuesPo.getWorkPressure4()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPE(AlarmEnum.tank4_pressure_up.getCode());
					po.setALARM(AlarmEnum.tank4_pressure_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}			
		}
		// 压力下限
		if(eventLimitPo.getOutgoing_pressure_down()!=null){
			
			if(eventValuesPo.getWorkPressure1()!=0) {
				if(eventValuesPo.getWorkPressure1()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPE(AlarmEnum.tank1_pressure_down.getCode());
					po.setALARM(AlarmEnum.tank1_pressure_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure2()!=0) {
				if(eventValuesPo.getWorkPressure2()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPE(AlarmEnum.tank2_pressure_down.getCode());
					po.setALARM(AlarmEnum.tank2_pressure_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure3()!=0) {
				if(eventValuesPo.getWorkPressure3()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPE(AlarmEnum.tank3_pressure_down.getCode());
					po.setALARM(AlarmEnum.tank3_pressure_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure4()!=0) {
				if(eventValuesPo.getWorkPressure4()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPE(AlarmEnum.tank4_pressure_down.getCode());
					po.setALARM(AlarmEnum.tank4_pressure_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}			
		}
		
		// 瞬时流量上限
		if(eventLimitPo.getOutbound_instantaneous_flow_up()!=null){
			
			if(eventValuesPo.getStandardFlow1()!=0) {
				if(eventValuesPo.getStandardFlow1()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPE(AlarmEnum.tank1_instantaneous_up.getCode());
					po.setALARM(AlarmEnum.tank1_instantaneous_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow2()!=0) {
				if(eventValuesPo.getStandardFlow2()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPE(AlarmEnum.tank2_instantaneous_up.getCode());
					po.setALARM(AlarmEnum.tank2_instantaneous_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow3()!=0) {
				if(eventValuesPo.getStandardFlow3()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPE(AlarmEnum.tank3_instantaneous_up.getCode());
					po.setALARM(AlarmEnum.tank3_instantaneous_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow4()!=0) {
				if(eventValuesPo.getStandardFlow4()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPE(AlarmEnum.tank4_instantaneous_up.getCode());
					po.setALARM(AlarmEnum.tank4_instantaneous_up.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}			
		}
		
		// 瞬时流量下限
		if(eventLimitPo.getOutbound_instantaneous_flow_down()!=null){
			
			if(eventValuesPo.getStandardFlow1()!=0) {
				if(eventValuesPo.getStandardFlow1()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPE(AlarmEnum.tank1_instantaneous_down.getCode());
					po.setALARM(AlarmEnum.tank1_instantaneous_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow2()!=0) {
				if(eventValuesPo.getStandardFlow2()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPE(AlarmEnum.tank2_instantaneous_down.getCode());
					po.setALARM(AlarmEnum.tank2_instantaneous_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow3()!=0) {
				if(eventValuesPo.getStandardFlow3()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPE(AlarmEnum.tank3_instantaneous_down.getCode());
					po.setALARM(AlarmEnum.tank3_instantaneous_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow4()!=0) {
				if(eventValuesPo.getStandardFlow4()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPE(AlarmEnum.tank4_instantaneous_down.getCode());
					po.setALARM(AlarmEnum.tank4_instantaneous_down.getMsg());
					veAlarmBll.AddAlarm(po);
				}
			}
		}
		
		
	}

}
