package com.cyws.tank.manager.po;

public class TaLocationPo {
	private String EQUIPMENT_ID;
	private String DA_UP;
	private Integer IS_LOCATION; // 是否定位成功
	private Integer LOCATION_TYPE;// 定位类型
	private Double LONGITUDE;// 经度
	private Double LATITUDE;// 纬度
	private String PLACE;// 地址
	private Integer DIRECTION; // 方向
	private Double SPEED; // 速度
	private Integer HEIGTH; // 高度
	private Double R_LONGITUDE;// 真实的经度
	private Double R_LATITUDE;// 真实的纬度
	
	
	public Double getR_LONGITUDE() {
		return R_LONGITUDE;
	}
	public void setR_LONGITUDE(Double r_LONGITUDE) {
		R_LONGITUDE = r_LONGITUDE;
	}
	public Double getR_LATITUDE() {
		return R_LATITUDE;
	}
	public void setR_LATITUDE(Double r_LATITUDE) {
		R_LATITUDE = r_LATITUDE;
	}
	public String getEQUIPMENT_ID() {
		return EQUIPMENT_ID;
	}
	public void setEQUIPMENT_ID(String eQUIPMENT_ID) {
		EQUIPMENT_ID = eQUIPMENT_ID;
	}
	public String getDA_UP() {
		return DA_UP;
	}
	public void setDA_UP(String dA_UP) {
		DA_UP = dA_UP;
	}
	public Integer getIS_LOCATION() {
		return IS_LOCATION;
	}
	public void setIS_LOCATION(Integer iS_LOCATION) {
		IS_LOCATION = iS_LOCATION;
	}
	public Integer getLOCATION_TYPE() {
		return LOCATION_TYPE;
	}
	public void setLOCATION_TYPE(Integer lOCATION_TYPE) {
		LOCATION_TYPE = lOCATION_TYPE;
	}
	public Double getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(Double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public Double getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(Double lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getPLACE() {
		return PLACE;
	}
	public void setPLACE(String pLACE) {
		PLACE = pLACE;
	}
	public Integer getDIRECTION() {
		return DIRECTION;
	}
	public void setDIRECTION(Integer dIRECTION) {
		DIRECTION = dIRECTION;
	}
	public Double getSPEED() {
		return SPEED;
	}
	public void setSPEED(Double sPEED) {
		SPEED = sPEED;
	}
	public Integer getHEIGTH() {
		return HEIGTH;
	}
	public void setHEIGTH(Integer hEIGTH) {
		HEIGTH = hEIGTH;
	}	
}
