package com.cyws.tank.manager.SubscribeNotify.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.data.Re_LongitudeAndLatitude;
import com.cyws.tank.manager.tool.TurnSlipTool;
import com.cyws.tank.codec.vo.LocationStatus;
import com.cyws.tank.codec.vo.req.LocationInfoUploadMsg;
import com.cyws.tank.manager.SubscribeNotify.ISubscribeNotify;
import com.cyws.tank.manager.bll.TaDataBll;
import com.cyws.tank.manager.bll.TaLocationBll;
import com.cyws.tank.manager.po.TaLocationPo;
import com.cyws.tank.utils.common.DateUtils;

public class LocationInfoSubscribeNotify implements ISubscribeNotify {
	private TaDataBll taDataBll=new TaDataBll();
	private TaLocationBll taLocationBll=new TaLocationBll();
	@Override
	public void HandleSubscribeNotify(Object deviceData) {
		LocationInfoUploadMsg locationInfoUploadMsg=(LocationInfoUploadMsg) deviceData;
		//System.out.println(locationInfoUploadMsg);
		LocationStatus locationStatus=locationInfoUploadMsg.getLocationStatus();
		 String terminalPhone=locationInfoUploadMsg.getMsgHeader().getTerminalPhone();
		 int warningFlagField=locationInfoUploadMsg.getWarningFlagField();//报警信息
		 int statusField=locationInfoUploadMsg.getStatusField();
		 Double latitude=locationInfoUploadMsg.getLatitude();
		 Double longitude=locationInfoUploadMsg.getLongitude();
		 int elevation=locationInfoUploadMsg.getElevation();
		 Double speed=locationInfoUploadMsg.getSpeed();
		 int direction=locationInfoUploadMsg.getDirection();
		 Date time=locationInfoUploadMsg.getTime();
		 
		 if(locationStatus.isIs_lat()){
			 latitude=latitude*-1;
		 }
		 if(locationStatus.isIs_log()){
			 longitude=longitude*-1;
		 }
		 int is_LOCATION=0;
		 if(!locationStatus.isIslocation()){
			 is_LOCATION=1;
		 }
		 
		 // 需要匹配设备是否注册
		 System.out.println("terminalPhone:"+terminalPhone);
		//读取数据库，查询设备是否注册
	    List<Map<String, Object>> ls_taTank= taDataBll.selectTaTankById(terminalPhone);
	    if(ls_taTank.isEmpty()){
	    	return ;
	    }
	    
	    // 转偏经纬度
	    double ts_latitude=0;
	    double ts_longitude=0;
	    try {
	    	// 由于这里的转偏接口有问题 调用错误，才会停止
	    	 //TurnSlipTool turnSlipTool=new TurnSlipTool();
	 		// Re_LongitudeAndLatitude re_LongitudeAndLatitude=turnSlipTool.ToGaoDeTurnSlip(longitude, latitude);
	 		// ts_latitude=re_LongitudeAndLatitude.getLatitude();
	 		 //ts_longitude=re_LongitudeAndLatitude.getLongitude();
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    if(ts_latitude==0 && ts_longitude==0){
	    	ts_latitude=latitude;
	    	ts_longitude=longitude;
	    }
	    
	    //获取位置信息
	    String pLACE="";// BaiduLocation.getBaiduLocation(longitude, latitude);
	    //System.out.println("pLACE:"+pLACE);
	    String s_time=DateUtils.datetime2String(time);
	    // System.out.println(s_time);
	    Map<String, Object> taTank_map=ls_taTank.get(0);
	    String eQUIPMENT_ID=taTank_map.get("EQUIPMENT_NO").toString();// 获取注册好的eqipmentID
	    TaLocationPo po=new TaLocationPo();
		po.setEQUIPMENT_ID(eQUIPMENT_ID);
		po.setDA_UP(s_time);
		po.setIS_LOCATION(is_LOCATION); // 是否定位成功
		po.setLOCATION_TYPE(1);// 定位类型
		po.setLONGITUDE(ts_longitude);// 经度
		po.setLATITUDE(ts_latitude);// 纬度
		po.setR_LATITUDE(latitude);// 真实纬度
		po.setR_LONGITUDE(longitude);// 真实经度
		po.setPLACE("");// 地址
		po.setDIRECTION(direction); // 方向
		po.setSPEED(speed); // 速度
		po.setHEIGTH(elevation); // 高度
		taLocationBll.addLocationTaDataNew(po);		
	}
}
