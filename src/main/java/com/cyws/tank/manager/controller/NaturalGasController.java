package com.cyws.tank.manager.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.manager.bll.TaDataBll;
import com.cyws.tank.manager.po.MgSetEventBean;
import com.cyws.tank.manager.po.TaDataPo;
import com.cyws.tank.push.client.XSocketClientManager;
import com.cyws.tank.utils.common.DateUtils;
import com.cyws.tank.utils.common.HttpClientUtil;
import com.cyws.tank.utils.common.TankVolumeCalculate;
import com.cyws.tank.utils.log.LogManager;
import com.cyws.tank.utils.po.Old_EventValuePo;
import com.cyws.tank.utils.po.PushJiTeToUserPo;
import com.cyws.tank.utils.po.PushSensorToUserPo;
import com.cyws.tank.utils.po.ReEventResult;
import com.cyws.tank.utils.pushuser.PushJiXingUser;
import com.cyws.tank.utils.serializer.FastJsonSerializer;


/**
 * 需要更具客户推送数据
 * @author lzw
 *
 */
public class NaturalGasController {

	private TaDataBll taDataBll=new TaDataBll();
	private MgSetController mgSetController=new MgSetController();
	private TankVolumeCalculate tankVolumeCalculate=new TankVolumeCalculate();
	
	public void assemble01(ExtensionInitiativeData extensionInitiativeData){
		
		String terminalPhone=extensionInitiativeData.getPackageData().getMsgHeader().getTerminalPhone();
		
	    //读取数据库，查询设备是否注册
	    List<Map<String, Object>> ls_taTank = taDataBll.selectTaTankById(terminalPhone);
	    if(ls_taTank.isEmpty()){//没有注册的设备不处理
	    	System.out.println("==exit============terminalPhone:"+terminalPhone);
	    	return ;
	    }
	    Map<String, Object> taTank_map=ls_taTank.get(0);//new HashMap<String, Object>();//ls_taTank.get(0);
		
	    
		// 获取设备上报的原始数据
		// 上报时间
		String dateTime=extensionInitiativeData.getDateTime();
		// 传感器个数
		int sensorNumber=extensionInitiativeData.getSensorNumber();		
		// 压力1
		long pressure1=extensionInitiativeData.getPressure1();		
		// 液位
		long liquidLevel=extensionInitiativeData.getLiquidLevel();		
		// 压力2
		long pressure2=extensionInitiativeData.getPressure2();		
		// 气体泄漏
		long gasReveal=extensionInitiativeData.getGasReveal();		
		// 温度
		long temperature=extensionInitiativeData.getTemperature();		
		// 掉电检测
		long powerDownDetection=extensionInitiativeData.getPowerDownDetection();		
		// 门开关
		long doorOnOff=extensionInitiativeData.getDoorOnOff();
	    // 电池电量
		long battery=extensionInitiativeData.getBattery();
		
		
		// 获取基础数据公式常量值
	    Double pressureRange=taTank_map.get("PRESSURE_RANGE")==null?0.0:Double.valueOf(taTank_map.get("PRESSURE_RANGE").toString());
	    Double liquidLevelRange=taTank_map.get("LEVEL_RANGE")==null?0.0:Double.valueOf(taTank_map.get("LEVEL_RANGE").toString());
	    Double gasRevealRange=taTank_map.get("GAS_RANGE")==null?0.0:Double.valueOf(taTank_map.get("GAS_RANGE").toString());
	    Double LEVEL_MAX=taTank_map.get("LEVEL_MAX")==null?null:Double.valueOf(taTank_map.get("LEVEL_MAX").toString());
	    Double LEVEL_UP=taTank_map.get("LEVEL_UP")==null?null:Double.valueOf(taTank_map.get("LEVEL_UP").toString());;
	    Double LEVEL_DOWN=taTank_map.get("LEVEL_DOWN")==null?null:Double.valueOf(taTank_map.get("LEVEL_DOWN").toString());
	    Double PRESSURE_MAX=taTank_map.get("PRESSURE_MAX")==null?null:Double.valueOf(taTank_map.get("PRESSURE_MAX").toString());
	    Double PRESSURE_UP=taTank_map.get("PRESSURE_UP")==null?null:Double.valueOf(taTank_map.get("PRESSURE_UP").toString());
	    Double PRESSURE_DOWN=taTank_map.get("PRESSURE_DOWN")==null?null:Double.valueOf(taTank_map.get("PRESSURE_DOWN").toString());
	    String eQUIPMENT_ID=taTank_map.get("EQUIPMENT_NO").toString();// 获取注册好的eqipmentID
	    String SHAPE=taTank_map.get("SHAPE")==null?null:taTank_map.get("SHAPE").toString();
	    String PK_ORG=taTank_map.get("pk_org")==null?null:taTank_map.get("pk_org").toString();
	    String NAME=taTank_map.get("NAME")==null?null:taTank_map.get("NAME").toString();
	    Double LEVEL_CHECK=taTank_map.get("LEVEL_CHECK")==null?null:Double.valueOf(taTank_map.get("LEVEL_CHECK").toString());
	    
	    
	    	    
	    // 处理基础数据公式转换 
	    Double pressure1_KPA=0.0;
	    Double pressure2_KPA=0.0;
	    Double liquidLevel_KPA=0.0;
	    
	    if(pressure1>600){
	    	pressure1_KPA=pressureRange*((pressure1-600)/(2400.0));
	    }
	    if(pressure2>600){
	    	pressure2_KPA=pressureRange*((pressure2-600)/(2400.0));
	    }
	    if(liquidLevel>600){
	    	liquidLevel_KPA=liquidLevelRange*((liquidLevel-600)/(2400.0))*101.972;
	    }
	    Double gasReveal_KPA=gasRevealRange*((gasReveal-600)/(2000.0));
	    
	    // 处理液位偏差值
	    if(LEVEL_CHECK!=null){
	    	//Double LEVEL_CHECK= Double.valueOf(taTank_map.get("LEVEL_CHECK").toString());
	 		 if(liquidLevel_KPA!=0){
	 			liquidLevel_KPA=liquidLevel_KPA+LEVEL_CHECK;
	 		 }	 		 
	    }
	    
	    // 处理最大液位判断事件与限制最大液位值
	    if(LEVEL_MAX!=null){//处理液位最大值处理
	    	if(LEVEL_MAX<liquidLevel_KPA){
	    		liquidLevel_KPA=LEVEL_MAX;
	    	}
	    }
	    
	    
	   // System.out.println(pressure1_KPA+"     "+pressure2_KPA+"    "+liquidLevel_KPA);
	    //电量处理
	    Double do_battery=battery*0.001;
	    
	    // 处理门状态
	    String str_doorOnOff= Integer.toBinaryString((int)doorOnOff);
	    StringBuffer strbf=new StringBuffer();
    	 if(str_doorOnOff.length()<8) {//解析状态信息
    		for(int i=0;i<8-str_doorOnOff.length();i++) {
    			strbf.append("0");
    		}
    		strbf.append(str_doorOnOff);
    	 }else {
    		strbf.append(str_doorOnOff);
    	 }
    	int bf_length=strbf.length();
    	String door1=strbf.substring(bf_length-1, bf_length);
    	String door2=strbf.substring(bf_length-2, bf_length-1);
    	String relay1=strbf.substring(bf_length-3, bf_length-2);
    	String relay2=strbf.substring(bf_length-4, bf_length-3);
    	
    	//处理容积
    	Double Density=0.0;
    	Double V=0.00;
    	Double maxDvolume=0.0;
    	if(liquidLevel_KPA>0 && taTank_map.get("DIAMETER")!=null && taTank_map.get("LENGTH")!=null && taTank_map.get("STOREUNITNUM")!=null && taTank_map.get("SHAPE")!=null){
    		Double Diameter =Double.valueOf(taTank_map.get("DIAMETER").toString());
        	Double Length =Double.valueOf(taTank_map.get("LENGTH").toString());
            Density =Double.valueOf(taTank_map.get("STOREUNITNUM").toString());
        	String tankShape=taTank_map.get("SHAPE").toString();
            maxDvolume =Double.valueOf(taTank_map.get("VOLUME").toString());    	
            V=tankVolumeCalculate.CalVolume(liquidLevel_KPA, Diameter, Length, Density, tankShape, maxDvolume);          
    	}
    	
    	// 质量百分比
    	BigDecimal b_v = null;
		if(maxDvolume!=0 && V!=0){
			 BigDecimal d = new BigDecimal(V+""); 
			 BigDecimal r = new BigDecimal(maxDvolume+"");
			 b_v = d.divide(r,2,BigDecimal.ROUND_HALF_UP).setScale(2,RoundingMode.HALF_EVEN);
		}
    	
    	String  da_up=DateUtils.BCDdatetime2datetime(dateTime);
	    // 组装数据实体
	    TaDataPo po=new TaDataPo();
	    po.setEQUIPMENT_ID(eQUIPMENT_ID);
		po.setDA_UP(da_up);
		po.setPRESSURE(pressure1_KPA>0?pressure1_KPA:0);
		po.setPRESSURE1(pressure2_KPA>0?pressure2_KPA:0);
		po.setLEVELS(liquidLevel_KPA>0?liquidLevel_KPA:0);
		po.setGAS(gasReveal_KPA>0?gasReveal_KPA:0);
		po.setTEMPERATURE(Double.valueOf(temperature));
		po.setELECTRIC(do_battery);
		po.setDOOR1(Integer.valueOf(door1));
		po.setDOOR2(Integer.valueOf(door2));
		po.setCURRENT_VOLUME(V);
		po.setSTOREUNITNUM(Density);
		po.setLEVEL_MAX(LEVEL_MAX);
				
		int result=taDataBll.addInfoTaDataNew(po);
		
		// 处理液位无效值是否处理报警
		boolean is_Handle=true;
		if(result==2){
			is_Handle=false;
		}
		
		// 触发事件处理
		ReEventResult reEventResult=null;
		if(is_Handle){
			Old_EventValuePo old_EventValuePo=new Old_EventValuePo();
		    old_EventValuePo.setEQUIPMENT_ID(eQUIPMENT_ID);
		    old_EventValuePo.setLEVEL_UP(LEVEL_UP);
		    old_EventValuePo.setLEVEL_DOWN(LEVEL_DOWN);
		    old_EventValuePo.setPRESSURE_MAX(PRESSURE_MAX);
		    old_EventValuePo.setPRESSURE_UP(PRESSURE_UP);
		    old_EventValuePo.setPRESSURE_DOWN(PRESSURE_DOWN);
		    old_EventValuePo.setLEVEL_MAX(LEVEL_MAX);
		    old_EventValuePo.setLiquidLevel_KPA(liquidLevel_KPA);
		    old_EventValuePo.setPressure1_KPA(pressure1_KPA);
		    
			reEventResult=HandleEvent(old_EventValuePo);
		}
		
		
		if("0001A1100876788XE1".equals(PK_ORG)){//需要根据客户标识来推送数据
			PushJiXingUser pushJiXingUser=new PushJiXingUser();
			PushSensorToUserPo pushSensorToUserPo=new PushSensorToUserPo();		
			if(reEventResult!=null){ // 判断是否有事件触发
				String str="";
				pushSensorToUserPo.setAlarm("1");// 是否报警
				if(reEventResult.isIs_level_event()){
					str="液位报警  ";
				}
				if(reEventResult.isIs_pressure_event()){
					str=str+"压力报警  ";
				}
				pushSensorToUserPo.setAlarm_status(str);// 报警信息
			}else{
				pushSensorToUserPo.setAlarm("0");// 是否报警
			}
			
			Map<String, Object> log_lat_map=getLogAndLat(eQUIPMENT_ID);
			String LONGITUDE=log_lat_map.get("LONGITUDE")==null?"":log_lat_map.get("LONGITUDE").toString();
			String LATITUDE=log_lat_map.get("LATITUDE")==null?"":log_lat_map.get("LATITUDE").toString();
			
			pushSensorToUserPo.setGuan_number(NAME);// 罐箱编号
			pushSensorToUserPo.setSj(da_up);// 时间
			pushSensorToUserPo.setF_sbsjsjjg("0");// 上报时间间隔
			pushSensorToUserPo.setF_yl((pressure1_KPA>0?pressure1_KPA:0)+"");// 压力
			pushSensorToUserPo.setF_wd(temperature+"");// 温度
			pushSensorToUserPo.setF_zlbfb(b_v+"");// 质量百分比
			pushSensorToUserPo.setLng(LONGITUDE);// 经度
			pushSensorToUserPo.setLat(LATITUDE);// 纬度
			pushSensorToUserPo.setF_dian(do_battery.toString());// 电量
			pushSensorToUserPo.setF_jzzl(Density.toString());// 介质质量
			pushSensorToUserPo.setNumber(eQUIPMENT_ID);// 设备号
			//pushSensorToUserPo.setImei(imei);// 设备IMEI
			//pushSensorToUserPo.setF_cggcrj(f_cggcrj); // 公称容积
			pushSensorToUserPo.setF_rqlx(SHAPE); // 容器类型
			//pushSensorToUserPo.setF_jz(f_jz);// 介质
			
			pushJiXingUser.handlePushUser(pushSensorToUserPo);// 推送数据给集兴客户
		}
		
		
		// 谭总 这个推送使用在集团管理所有设备时使用到的 -- 胸科医院
		if("10015900287".equals(terminalPhone) || "11115900400".equals(terminalPhone) ){			
			Map<String, Object> pushJiTeToUserPo=new HashMap<String, Object>();
			pushJiTeToUserPo.put("EQUIPMENT_ID", eQUIPMENT_ID);
			pushJiTeToUserPo.put("LEVELS", liquidLevel_KPA.intValue());
			pushJiTeToUserPo.put("PRESSURE", pressure1_KPA.intValue());
			pushJiTeToUserPo.put("DA_UP", da_up);
			String  json=new FastJsonSerializer().toJSON(pushJiTeToUserPo);
			String url="http://xk.shhanqian.com:18080/man/equipment/data";
			String xml=HttpClientUtil.doPostFormTojson(url, null,json);
			//XSocketClientManager.getClientManager().pushData(json);
			// 打印日志
			String log=json+"   "+xml;
			LogManager.getInstance().PrintSentPushUserLog("zhongjiToUser1",log);//打印log
		}
		
		if( "11115900133".equals(terminalPhone) || "11115900134".equals(terminalPhone)){			
			Map<String, Object> pushJiTeToUserPo=new HashMap<String, Object>();
			pushJiTeToUserPo.put("EQUIPMENT_ID", eQUIPMENT_ID);
			pushJiTeToUserPo.put("LEVELS", liquidLevel_KPA.intValue());
			pushJiTeToUserPo.put("PRESSURE", pressure1_KPA.intValue());
			pushJiTeToUserPo.put("DA_UP", da_up);
			String  json=new FastJsonSerializer().toJSON(pushJiTeToUserPo);
			String url="http://lh.shhanqian.com:18080/man/equipment/data";
			String xml=HttpClientUtil.doPostFormTojson(url, null,json);
			//XSocketClientManager.getClientManager().pushData(json);
			// 打印日志
			String log=json+"   "+xml;
			LogManager.getInstance().PrintSentPushUserLog("zhongjiToUser1",log);//打印log
		}
		
		
		// socket push data  根据客户推送数据到客户云端
		//System.out.println("PK_ORG:"+PK_ORG);
		/*PushUserBean oldPushUserBean=PushUserManager.getInstance().getPushUserBean("123456789");
		if(oldPushUserBean!=null && oldPushUserBean.isConnect()){
			Map<String, Object> log_lat_map=getLogAndLat(eQUIPMENT_ID);
			String LONGITUDE=log_lat_map.get("LONGITUDE")==null?"":log_lat_map.get("LONGITUDE").toString();
			String LATITUDE=log_lat_map.get("LATITUDE")==null?"":log_lat_map.get("LATITUDE").toString();
			
			
					
			NaturalGasPushData naturalGasPushData=new NaturalGasPushData();			
			naturalGasPushData.setEquipmentId(eQUIPMENT_ID);//设备号 
			naturalGasPushData.setEquipmentNo(NAME);; // 设备编号
			naturalGasPushData.setDataTime(da_up);// 时间
			naturalGasPushData.setLongitude(LONGITUDE);// 经度
			naturalGasPushData.setLatitude(LATITUDE);// 纬度
			naturalGasPushData.setLiquidLevel((liquidLevel_KPA>0?liquidLevel_KPA:0)+"");//液位
			naturalGasPushData.setPressure((pressure1_KPA>0?pressure1_KPA:0)+"");// 压力
			naturalGasPushData.setMediumQuality(Density.toString());//质量
			naturalGasPushData.setTemperature(temperature+"");// 温度
			naturalGasPushData.setBattery(battery+"");;// 电池电量
			naturalGasPushData.setQualityPercentage(b_v+"");;// 质量百分比	
			
			PushData pushData=new PushData();
			pushData.setData(naturalGasPushData);
			pushData.setType(PushClientConsts.msg_id_Push_data);
			String  json=new FastJsonSerializer().toJSON(pushData);
			try {
				oldPushUserBean.getXsocke().write(json);
				oldPushUserBean.getXsocke().flush();
				
				// 打印日志
				LogManager.getInstance().PrintSentPushUserLog(oldPushUserBean.getUserName(),json);//打印log
			} catch (BufferOverflowException e) {				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		
	}
	
	private ReEventResult HandleEvent(Old_EventValuePo old_EventValuePo){
		boolean is_mg_set_event=false;
		boolean is_level_event=false;
		boolean is_pressure_event=false;
		ReEventResult reEventResult=null;
		MgSetEventBean mgSetEventBean=new MgSetEventBean();
		mgSetEventBean.setEQUIPMENT_ID(old_EventValuePo.getEQUIPMENT_ID());
		Double LEVEL_MAX=old_EventValuePo.getLEVEL_MAX();
		Double liquidLevel_KPA=old_EventValuePo.getLiquidLevel_KPA();
		Double LEVEL_UP=old_EventValuePo.getLEVEL_UP();
		Double LEVEL_DOWN=old_EventValuePo.getLEVEL_DOWN();
		
		Double PRESSURE_MAX=old_EventValuePo.getPRESSURE_MAX();
		Double pressure1_KPA=old_EventValuePo.getPressure1_KPA();
		Double PRESSURE_UP=old_EventValuePo.getPRESSURE_UP();
		Double PRESSURE_DOWN=old_EventValuePo.getPRESSURE_DOWN();
		// 处理最大液位判断事件与限制最大液位值
	    if(LEVEL_MAX!=null){//处理液位最大值处理	    	
	    	double level_double=(liquidLevel_KPA/LEVEL_MAX)*100;
	    	if(LEVEL_UP!=null && level_double>LEVEL_UP){
	    		// 液位超过最大上限事件
	    		mgSetEventBean.setLEVELS_TYPE(0);
	    		mgSetEventBean.setLEVELS_VALUE(level_double-LEVEL_UP);
	    		is_mg_set_event=true;
	    		is_level_event=true;
	    	}
	    	if(LEVEL_DOWN!=null && level_double<LEVEL_DOWN){
	    		// 液位超过低于下限事件
	    		mgSetEventBean.setLEVELS_TYPE(1);
	    		mgSetEventBean.setLEVELS_VALUE(LEVEL_DOWN-level_double);
	    		is_mg_set_event=true;
	    		is_level_event=true;
	    	}
	    }
	    
	    //  处理最大压力判断事件与限制最大压力值
	    if(PRESSURE_MAX!=null){
	    	double pressure_double=(pressure1_KPA/PRESSURE_MAX)*100;
	    	if(PRESSURE_UP!=null && pressure_double>PRESSURE_UP){
	    		// 液位超过最大上限事件
	    		mgSetEventBean.setPRESSURE_TYPE(0);
	    		mgSetEventBean.setPRESSURE_VALUE(pressure_double-PRESSURE_UP);
	    		is_mg_set_event=true;
	    		is_pressure_event=true;
	    	}
	    	if(PRESSURE_DOWN!=null && pressure_double<PRESSURE_DOWN){
	    		// 液位超过低于下限事件
	    		mgSetEventBean.setPRESSURE_TYPE(1);
	    		mgSetEventBean.setPRESSURE_VALUE(PRESSURE_DOWN-pressure_double);
	    		is_mg_set_event=true;
	    		is_pressure_event=true;
	    	}
	    }
	    
	    // 处理事件
    	if(is_mg_set_event){
    		mgSetController.handleMgSet(mgSetEventBean);
    		reEventResult=new ReEventResult();
    		reEventResult.setIs_level_event(is_level_event);
    		reEventResult.setIs_pressure_event(is_pressure_event);
    	}
    	return reEventResult;
	}
	
	private Map<String, Object> getLogAndLat(String EQUIPMENT_ID){
		Map<String, Object> map_logAndLat=new HashMap<String, Object>();
		List<Map<String, Object>>  lst_map=taDataBll.selectTaLocationByID(EQUIPMENT_ID);
		if(!lst_map.isEmpty()){
			Object LONGITUDE=lst_map.get(0).get("R_LONGITUDE"); 
			Object LATITUDE=lst_map.get(0).get("R_LATITUDE");
			// 转偏经纬度
			/*if(LONGITUDE!=null&& LATITUDE!=null){
			    TurnSlipTool turnSlipTool=new TurnSlipTool();
			    Re_LongitudeAndLatitude re_LongitudeAndLatitude=turnSlipTool.ToGaoDeTurnSlip(Double.valueOf(LONGITUDE.toString()), Double.valueOf(LATITUDE.toString()));
			    LATITUDE=re_LongitudeAndLatitude.getLatitude();
			    LONGITUDE=re_LongitudeAndLatitude.getLongitude();
			}*/
			map_logAndLat.put("LONGITUDE", LONGITUDE);
			map_logAndLat.put("LATITUDE", LATITUDE);
		}
		return map_logAndLat;
	}
	
}
