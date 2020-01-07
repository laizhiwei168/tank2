package com.cyws.tank.utils.enums;

public enum AlarmEnum {
	tank1_temperature_up(11,"1号箱温度过高报警"),
	tank1_temperature_down(12,"1号箱温度过低报警"),
	tank2_temperature_up(13,"2号箱温度过高报警"),
	tank2_temperature_down(14,"2号箱温度过低报警"),
	tank3_temperature_up(15,"3号箱温度过高报警"),
	tank3_temperature_down(16,"3号箱温度过低报警"),
	tank4_temperature_up(17,"4号箱温度过高报警"),	
	tank4_temperature_down(18,"4号箱温度过低报警"),
	
	tank1_pressure_up(19,"1号箱压力过高报警"),
	tank1_pressure_down(20,"1号箱压力过低报警"),
	tank2_pressure_up(21,"2号箱压力过高报警"),
	tank2_pressure_down(22,"2号箱压力过低报警"),
	tank3_pressure_up(23,"3号箱压力过高报警"),
	tank3_pressure_down(24,"3号箱压力过低报警"),
	tank4_pressure_up(25,"4号箱压力过高报警"),
	tank4_pressure_down(26,"4号箱压力过低报警"),
	
	tank1_instantaneous_up(27,"1号箱瞬时流量过高报警"),
	tank1_instantaneous_down(28,"1号箱瞬时流量过低报警"),
	tank2_instantaneous_up(29,"2号箱瞬时流量过高报警"),
	tank2_instantaneous_down(30,"2号箱瞬时流量过低报警"),
	tank3_instantaneous_up(31,"3号箱瞬时流量过高报警"),
	tank3_instantaneous_down(32,"3号箱瞬时流量过低报警"),
	tank4_instantaneous_up(33,"4号箱瞬时流量过高报警"),
	tank4_instantaneous_down(34,"4号箱瞬时流量过低报警"),
	
	unloading_area_pressure(40,"卸车区压力报警"),
	;

	 private  int code; //枚举字符串名称
	 private  String msg; //枚举字符串名称
	 
	private AlarmEnum(int code,String msg){
		 this.code=code;
		 this.msg=msg;
	 }

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	 
}
