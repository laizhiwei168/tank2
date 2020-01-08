package com.cyws.tank.manager.controller;

import com.cyws.tank.manager.bll.MgSetBll;
import com.cyws.tank.manager.po.MgSetPo;
import com.cyws.tank.utils.common.StringUtils;
import com.cyws.tank.utils.enums.AlarmEnum;
import com.cyws.tank.utils.po.FL_EventLimitPo;
import com.cyws.tank.utils.po.FL_EventValuesPo;

/**
 * */
public class Alarm_2_Controller {

	private MgSetBll mgSetBll=new MgSetBll();
	
	public void handleAlarm(FL_EventLimitPo eventLimitPo,FL_EventValuesPo eventValuesPo){
		
		
		MgSetPo po=new MgSetPo();
		po.setID(StringUtils.createID());
		po.setEQUIPMENT_ID(eventValuesPo.getEQUIPMENT_ID());		
		po.setDA_ADD(eventValuesPo.getDA_UP());// 添加时间
		po.setIS_DELETE(0);// 是否删除
		
		// 温度上限 
		if(eventLimitPo.getOutgoing_temperature_up()!=null){
			
			if(eventValuesPo.getWorkTemperature1()!=0) {
				if(eventValuesPo.getWorkTemperature1()>eventLimitPo.getOutgoing_temperature_up()) {
					po.setTYPES(AlarmEnum.tank1_temperature_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature1());// 上限
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature2()!=0) {
				if(eventValuesPo.getWorkTemperature2()>eventLimitPo.getOutgoing_temperature_up()) {				
					po.setTYPES(AlarmEnum.tank2_temperature_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature2());
					mgSetBll.AddMgSet(po);
				}
			}			
			
			if(eventValuesPo.getWorkTemperature3()!=0) {
				if(eventValuesPo.getWorkTemperature3()>eventLimitPo.getOutgoing_temperature_up()) {
					po.setTYPES(AlarmEnum.tank3_temperature_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature3());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature4()!=0) {
				if(eventValuesPo.getWorkTemperature4()>eventLimitPo.getOutgoing_temperature_up()) {
					po.setTYPES(AlarmEnum.tank4_temperature_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature4());
					mgSetBll.AddMgSet(po);
				}
			}
			
		}
		
		// 温度下限
		if(eventLimitPo.getOutgoing_temperature_down()!=null){
			
			if(eventValuesPo.getWorkTemperature1()!=0) {
				if(eventValuesPo.getWorkTemperature1()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPES(AlarmEnum.tank1_temperature_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature1());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature2()!=0) {
				if(eventValuesPo.getWorkTemperature2()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPES(AlarmEnum.tank2_temperature_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature2());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature3()!=0) {
				if(eventValuesPo.getWorkTemperature3()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPES(AlarmEnum.tank3_temperature_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature3());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkTemperature4()!=0) {
				if(eventValuesPo.getWorkTemperature4()<eventLimitPo.getOutgoing_temperature_down()) {
					po.setTYPES(AlarmEnum.tank4_temperature_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkTemperature4());
					mgSetBll.AddMgSet(po);
				}
			}			
		}
		
		// 压力上限
		if(eventLimitPo.getOutgoing_pressure_up()!=null){
			
			if(eventValuesPo.getWorkPressure1()!=0) {
				if(eventValuesPo.getWorkPressure1()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPES(AlarmEnum.tank1_pressure_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure1());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure2()!=0) {
				if(eventValuesPo.getWorkPressure2()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPES(AlarmEnum.tank2_pressure_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure2());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure3()!=0) {
				if(eventValuesPo.getWorkPressure3()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPES(AlarmEnum.tank3_pressure_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure3());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure4()!=0) {
				if(eventValuesPo.getWorkPressure4()>eventLimitPo.getOutgoing_pressure_up()) {
					po.setTYPES(AlarmEnum.tank4_pressure_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure4());
					mgSetBll.AddMgSet(po);
				}
			}			
		}
		// 压力下限
		if(eventLimitPo.getOutgoing_pressure_down()!=null){
			
			if(eventValuesPo.getWorkPressure1()!=0) {
				if(eventValuesPo.getWorkPressure1()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPES(AlarmEnum.tank1_pressure_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure1());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure2()!=0) {
				if(eventValuesPo.getWorkPressure2()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPES(AlarmEnum.tank2_pressure_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure2());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure3()!=0) {
				if(eventValuesPo.getWorkPressure3()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPES(AlarmEnum.tank3_pressure_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure3());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getWorkPressure4()!=0) {
				if(eventValuesPo.getWorkPressure4()<eventLimitPo.getOutgoing_pressure_down()) {
					po.setTYPES(AlarmEnum.tank4_pressure_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getWorkPressure4());
					mgSetBll.AddMgSet(po);
				}
			}			
		}
		
		// 瞬时流量上限
		if(eventLimitPo.getOutbound_instantaneous_flow_up()!=null){
			
			if(eventValuesPo.getStandardFlow1()!=0) {
				if(eventValuesPo.getStandardFlow1()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPES(AlarmEnum.tank1_instantaneous_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow1());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow2()!=0) {
				if(eventValuesPo.getStandardFlow2()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPES(AlarmEnum.tank2_instantaneous_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow2());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow3()!=0) {
				if(eventValuesPo.getStandardFlow3()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPES(AlarmEnum.tank4_instantaneous_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow3());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow4()!=0) {
				if(eventValuesPo.getStandardFlow4()>eventLimitPo.getOutbound_instantaneous_flow_up()) {
					po.setTYPES(AlarmEnum.tank4_instantaneous_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow4());
					mgSetBll.AddMgSet(po);
				}
			}			
		}
		
		// 瞬时流量下限
		if(eventLimitPo.getOutbound_instantaneous_flow_down()!=null){
			
			if(eventValuesPo.getStandardFlow1()!=0) {
				if(eventValuesPo.getStandardFlow1()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPES(AlarmEnum.tank1_instantaneous_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow1());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow2()!=0) {
				if(eventValuesPo.getStandardFlow2()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPES(AlarmEnum.tank2_instantaneous_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow2());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow3()!=0) {
				if(eventValuesPo.getStandardFlow3()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPES(AlarmEnum.tank3_instantaneous_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow3());
					mgSetBll.AddMgSet(po);
				}
			}
			
			if(eventValuesPo.getStandardFlow4()!=0) {
				if(eventValuesPo.getStandardFlow4()<eventLimitPo.getOutbound_instantaneous_flow_down()) {
					po.setTYPES(AlarmEnum.tank4_instantaneous_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getStandardFlow4());
					mgSetBll.AddMgSet(po);
				}
			}
		}


		// 液位上限
		if(eventLimitPo.getOutgoing_liquidLevel_up()!=null){

			if(eventValuesPo.getLiquidLevel1()!=0) {
				if(eventValuesPo.getLiquidLevel1()>eventLimitPo.getOutgoing_liquidLevel_up()) {
					po.setTYPES(AlarmEnum.tank1_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel1());
					mgSetBll.AddMgSet(po);
				}
			}

			if(eventValuesPo.getLiquidLevel2()!=0) {
				if(eventValuesPo.getLiquidLevel2()>eventLimitPo.getOutgoing_liquidLevel_up()) {
					po.setTYPES(AlarmEnum.tank2_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel2());
					mgSetBll.AddMgSet(po);
				}
			}

			if(eventValuesPo.getLiquidLevel3()!=0) {
				if(eventValuesPo.getLiquidLevel3()>eventLimitPo.getOutgoing_liquidLevel_up()) {
					po.setTYPES(AlarmEnum.tank3_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel3());
					mgSetBll.AddMgSet(po);
				}
			}

			if(eventValuesPo.getLiquidLevel4()!=0) {
				if(eventValuesPo.getLiquidLevel4()>eventLimitPo.getOutgoing_liquidLevel_up()) {
					po.setTYPES(AlarmEnum.tank4_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel4());
					mgSetBll.AddMgSet(po);
				}
			}
		}
		// 液位下限
		if(eventLimitPo.getOutgoing_liquidLevel_down()!=null){

			if(eventValuesPo.getLiquidLevel1()!=0) {
				if(eventValuesPo.getLiquidLevel1()<eventLimitPo.getOutgoing_liquidLevel_down()) {
					po.setTYPES(AlarmEnum.tank1_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel1());
					mgSetBll.AddMgSet(po);
				}
			}

			if(eventValuesPo.getLiquidLevel2()!=0) {
				if(eventValuesPo.getLiquidLevel2()<eventLimitPo.getOutgoing_liquidLevel_down()) {
					po.setTYPES(AlarmEnum.tank2_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel2());
					mgSetBll.AddMgSet(po);
				}
			}

			if(eventValuesPo.getLiquidLevel3()!=0) {
				if(eventValuesPo.getLiquidLevel3()<eventLimitPo.getOutgoing_liquidLevel_down()) {
					po.setTYPES(AlarmEnum.tank3_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel3());
					mgSetBll.AddMgSet(po);
				}
			}

			if(eventValuesPo.getLiquidLevel4()!=0) {
				if(eventValuesPo.getLiquidLevel4()<eventLimitPo.getOutgoing_liquidLevel_down()) {
					po.setTYPES(AlarmEnum.tank4_level_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getLiquidLevel4());
					mgSetBll.AddMgSet(po);
				}
			}
		}
		

		// 储罐压力
		if(eventLimitPo.getTank_pressure_up()!=null) {

			if (eventValuesPo.getTankPressure() != 0) {
				if (eventValuesPo.getTankPressure() > eventLimitPo.getOutgoing_liquidLevel_up()) {
					po.setTYPES(AlarmEnum.storageTank_pressure_up.getCode());
					po.setLEVEL_UP(eventValuesPo.getTankPressure());
					mgSetBll.AddMgSet(po);
				}
			}
		}

		if(eventLimitPo.getTank_pressure_down()!=null) {

			if (eventValuesPo.getTankPressure() != 0) {
				if (eventValuesPo.getTankPressure() < eventLimitPo.getOutgoing_liquidLevel_down()) {
					po.setTYPES(AlarmEnum.storageTank_pressure_down.getCode());
					po.setLEVEL_UP(eventValuesPo.getTankPressure());
					mgSetBll.AddMgSet(po);
				}
			}
		}
	}

}
