package com.cyws.tank.utils.po;

public class FL_EventLimitPo {
	//温度
	private Double outgoing_temperature_up;
	private Double outgoing_temperature_down;
    //压力
	private Double outgoing_pressure_up;
	private Double outgoing_pressure_down;
    //瞬时
	private Double outbound_instantaneous_flow_up;
	private Double outbound_instantaneous_flow_down;

    //卸车区压力上限报警
	private Double unloading_area_pressure;

	// 液位
	private Double outgoing_liquidLevel_up;
	private Double outgoing_liquidLevel_down;

	// 储罐压力
	private Double tank_pressure_up;
	private Double tank_pressure_down;

	public Double getTank_pressure_up() {
		return tank_pressure_up;
	}

	public void setTank_pressure_up(Double tank_pressure_up) {
		this.tank_pressure_up = tank_pressure_up;
	}

	public Double getTank_pressure_down() {
		return tank_pressure_down;
	}

	public void setTank_pressure_down(Double tank_pressure_down) {
		this.tank_pressure_down = tank_pressure_down;
	}

	public Double getOutgoing_liquidLevel_up() {
		return outgoing_liquidLevel_up;
	}

	public void setOutgoing_liquidLevel_up(Double outgoing_liquidLevel_up) {
		this.outgoing_liquidLevel_up = outgoing_liquidLevel_up;
	}

	public Double getOutgoing_liquidLevel_down() {
		return outgoing_liquidLevel_down;
	}

	public void setOutgoing_liquidLevel_down(Double outgoing_liquidLevel_down) {
		this.outgoing_liquidLevel_down = outgoing_liquidLevel_down;
	}

	public Double getOutgoing_temperature_up() {
		return outgoing_temperature_up;
	}

	public void setOutgoing_temperature_up(Double outgoing_temperature_up) {
		this.outgoing_temperature_up = outgoing_temperature_up;
	}

	public Double getOutgoing_temperature_down() {
		return outgoing_temperature_down;
	}

	public void setOutgoing_temperature_down(Double outgoing_temperature_down) {
		this.outgoing_temperature_down = outgoing_temperature_down;
	}

	public Double getOutgoing_pressure_up() {
		return outgoing_pressure_up;
	}

	public void setOutgoing_pressure_up(Double outgoing_pressure_up) {
		this.outgoing_pressure_up = outgoing_pressure_up;
	}

	public Double getOutgoing_pressure_down() {
		return outgoing_pressure_down;
	}

	public void setOutgoing_pressure_down(Double outgoing_pressure_down) {
		this.outgoing_pressure_down = outgoing_pressure_down;
	}

	public Double getOutbound_instantaneous_flow_up() {
		return outbound_instantaneous_flow_up;
	}

	public void setOutbound_instantaneous_flow_up(Double outbound_instantaneous_flow_up) {
		this.outbound_instantaneous_flow_up = outbound_instantaneous_flow_up;
	}

	public Double getOutbound_instantaneous_flow_down() {
		return outbound_instantaneous_flow_down;
	}

	public void setOutbound_instantaneous_flow_down(Double outbound_instantaneous_flow_down) {
		this.outbound_instantaneous_flow_down = outbound_instantaneous_flow_down;
	}

	public Double getUnloading_area_pressure() {
		return unloading_area_pressure;
	}

	public void setUnloading_area_pressure(Double unloading_area_pressure) {
		this.unloading_area_pressure = unloading_area_pressure;
	}
	
	

}
