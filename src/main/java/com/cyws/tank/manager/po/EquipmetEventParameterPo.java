package com.cyws.tank.manager.po;

public class EquipmetEventParameterPo {

	private EventParameterPo levels_up;// 液位上限
	private EventParameterPo levels_down;// 液位下限
	private EventParameterPo pressure_up;// 压力上限
	private EventParameterPo pressure_down;// 压力下限	
	private EventParameterPo vacuum_up;// 气体漏气上限
	private EventParameterPo vacuum_down;// 气体漏气下限
	
	public EventParameterPo getLevels_up() {
		return levels_up;
	}
	public void setLevels_up(EventParameterPo levels_up) {
		this.levels_up = levels_up;
	}
	public EventParameterPo getLevels_down() {
		return levels_down;
	}
	public void setLevels_down(EventParameterPo levels_down) {
		this.levels_down = levels_down;
	}
	public EventParameterPo getPressure_up() {
		return pressure_up;
	}
	public void setPressure_up(EventParameterPo pressure_up) {
		this.pressure_up = pressure_up;
	}
	public EventParameterPo getPressure_down() {
		return pressure_down;
	}
	public void setPressure_down(EventParameterPo pressure_down) {
		this.pressure_down = pressure_down;
	}
	public EventParameterPo getVacuum_up() {
		return vacuum_up;
	}
	public void setVacuum_up(EventParameterPo vacuum_up) {
		this.vacuum_up = vacuum_up;
	}
	public EventParameterPo getVacuum_down() {
		return vacuum_down;
	}
	public void setVacuum_down(EventParameterPo vacuum_down) {
		this.vacuum_down = vacuum_down;
	}
	
}
