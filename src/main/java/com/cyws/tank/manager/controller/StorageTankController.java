package com.cyws.tank.manager.controller;

import java.util.List;
import java.util.Map;

import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.manager.bll.TaDataBll;
import com.cyws.tank.manager.po.MgSetEventBean;
import com.cyws.tank.manager.po.TaDataPo;
import com.cyws.tank.utils.common.DateUtils;
import com.cyws.tank.utils.common.TankVolumeCalculate;
import com.cyws.tank.utils.po.FL_EventLimitPo;
import com.cyws.tank.utils.po.FL_EventValuesPo;
import com.cyws.tank.utils.po.Old_EventValuePo;

public class StorageTankController {
	private TaDataBll taDataBll=new TaDataBll();
	private MgSetController mgSetController=new MgSetController();
	private TankVolumeCalculate tankVolumeCalculate=new TankVolumeCalculate();
	
	
	public void assemble03(ExtensionInitiativeData extensionInitiativeData){
		String terminalPhone=extensionInitiativeData.getPackageData().getMsgHeader().getTerminalPhone();	    
	    //读取数据库，查询设备是否注册
	    List<Map<String, Object>> ls_taTank = taDataBll.selectTaTankById(terminalPhone);
	    
	    if(ls_taTank.isEmpty()){//没有注册的设备不处理
	    	System.out.println("==exit============terminalPhone:"+terminalPhone);
	    	return ;
	    }
	    Map<String, Object> taTank_map=ls_taTank.get(0);//new HashMap<String, Object>();//ls_taTank.get(0);
		
	    
		// 上报时间
		String dateTime=extensionInitiativeData.getDateTime();
		// 传感器个数
		int sensorNumber=extensionInitiativeData.getSensorNumber();	
		// 压力1 01
		 double pressure1=Float.intBitsToFloat(extensionInitiativeData.getPressure1());		
		// 液位  02
		 double liquidLevel=Float.intBitsToFloat(extensionInitiativeData.getLiquidLevel());		 
		// 压力2 03
		 double pressure2=Float.intBitsToFloat(extensionInitiativeData.getPressure2());		
		// 气体泄漏  04
		 double gasReveal=Float.intBitsToFloat(extensionInitiativeData.getGasReveal());		
		// 温度  05
		 double temperature=Float.intBitsToFloat(extensionInitiativeData.getTemperature());		
		// 气体泄漏  06
		 double gasReveal2=Float.intBitsToFloat(extensionInitiativeData.getGasReveal2());		
		// 压力3 07
		 double pressure3=Float.intBitsToFloat(extensionInitiativeData.getPressure3());		
		// 液位2  08
		 double liquidLevel2=Float.intBitsToFloat(extensionInitiativeData.getLiquidLevel2());		
		// 温度2  09		
		 double temperature2=Float.intBitsToFloat(extensionInitiativeData.getTemperature2());		 
		 //System.out.println("pressure1:"+pressure1+"liquidLevel:"+liquidLevel+"pressure2:"+pressure2+"gasReveal:"+gasReveal+"temperature:"+temperature+"gasReveal2:"+gasReveal2+"pressure3:"+pressure3+"liquidLevel2:"+liquidLevel2+"temperature2:"+temperature2);
		
		 //工况流量累计 D1
		 double workFlowAddUp=0.0;
		 if(extensionInitiativeData.isIs_workFlowAddUp_8()){
			 workFlowAddUp= Double.longBitsToDouble(extensionInitiativeData.getWorkFlowAddUp());
			
		 }else {
			 workFlowAddUp=Float.intBitsToFloat((int)extensionInitiativeData.getWorkFlowAddUp());
		 }				 
		//标况流量累计 D2
		 double standardFlowAddUp=0.0;
		 if(extensionInitiativeData.isIs_standardFlowAddUp_8()){
			 standardFlowAddUp= Double.longBitsToDouble(extensionInitiativeData.getStandardFlowAddUp());
			
		 }else {
			 standardFlowAddUp=Float.intBitsToFloat((int)extensionInitiativeData.getStandardFlowAddUp());
		 }
		//工况计瞬时流量 D3
		 double workFlow=Float.intBitsToFloat(extensionInitiativeData.getWorkFlow());	
		//标况计瞬时流量 D4
		 double standardFlow=Float.intBitsToFloat(extensionInitiativeData.getStandardFlow());	
		// 温度 D5
		 double workTemperature=Float.intBitsToFloat(extensionInitiativeData.getWorkTemperature());	
		// 压力 D6
		 double workPressure=Float.intBitsToFloat(extensionInitiativeData.getWorkPressure());	
		//剩余流量  D7
		 double residueFlow=Float.intBitsToFloat(extensionInitiativeData.getResidueFlow());
		// System.out.println("workFlowAddUp:"+workFlowAddUp+"standardFlowAddUp:"+standardFlowAddUp+"workFlow:"+workFlow+"standardFlow:"+standardFlow+"workTemperature:"+workTemperature+"workPressure:"+workPressure+"residueFlow:"+residueFlow);
		
		 double liquidLevel3=Float.intBitsToFloat(extensionInitiativeData.getLiquidLevel3());
		 double tankPressure3=Float.intBitsToFloat(extensionInitiativeData.getTankPressure3());
		 
		 //工况流量累计2  D1
		 double workFlowAddUp2=0.0;
		 if(extensionInitiativeData.isIs_workFlowAddUp2_8()){
			 workFlowAddUp2= Double.longBitsToDouble(extensionInitiativeData.getWorkFlowAddUp2());				
		 }else {
			 workFlowAddUp2=Float.intBitsToFloat((int)extensionInitiativeData.getWorkFlowAddUp2());
		 }			 
		//标况流量累计2  D2
		 double standardFlowAddUp2=0.0;
		 if(extensionInitiativeData.isIs_standardFlowAddUp2_8()){
			 standardFlowAddUp2= Double.longBitsToDouble(extensionInitiativeData.getStandardFlowAddUp2());				
		 }else {
			 standardFlowAddUp2=Float.intBitsToFloat((int)extensionInitiativeData.getStandardFlowAddUp2());
		 }
		//工况计瞬时流量2  D3
		 double workFlow2=Float.intBitsToFloat(extensionInitiativeData.getWorkFlow2());	
		//标况计瞬时流量2  D4
		 double standardFlow2=Float.intBitsToFloat(extensionInitiativeData.getStandardFlow2());	
		// 温度2 D5
		 double workTemperature2=Float.intBitsToFloat(extensionInitiativeData.getWorkTemperature2());	
		// 压力2 D6
		 double workPressure2=Float.intBitsToFloat(extensionInitiativeData.getWorkPressure2());	
		//剩余流量2  D7
		 double residueFlow2=Float.intBitsToFloat(extensionInitiativeData.getResidueFlow2());
		//System.out.println("workFlowAddUp2:"+workFlowAddUp2+"  standardFlowAddUp2:"+standardFlowAddUp2+" workFlow2:"+workFlow2+"standardFlow2:"+standardFlow2+"workTemperature2:"+workTemperature2+"workPressure2:"+workPressure2+"residueFlow2:"+residueFlow2);		 
		 
		 double liquidLevel4=Float.intBitsToFloat(extensionInitiativeData.getLiquidLevel4());
		 double tankPressure4=Float.intBitsToFloat(extensionInitiativeData.getTankPressure4());
		 
		 double A1=Float.intBitsToFloat(extensionInitiativeData.getA1());
		 double A2=Float.intBitsToFloat(extensionInitiativeData.getA2());
		 double A3=Float.intBitsToFloat(extensionInitiativeData.getA3());
		 double A4=Float.intBitsToFloat(extensionInitiativeData.getA4());
		 double A5=Float.intBitsToFloat(extensionInitiativeData.getA5());
		 double A6=Float.intBitsToFloat(extensionInitiativeData.getA6());		 
		 //System.out.println(A1+" A2:"+A2+" A3:"+A3+" A4:"+A4+" A5:"+A5+" A6:"+A6);
		 
		 double B1=Float.intBitsToFloat(extensionInitiativeData.getB1());
		 double B2=Float.intBitsToFloat(extensionInitiativeData.getB2());
		 double B3=Float.intBitsToFloat(extensionInitiativeData.getB3());
		 double B4=Float.intBitsToFloat(extensionInitiativeData.getB4());
		 double B5=Float.intBitsToFloat(extensionInitiativeData.getB5());
		 double B6=Float.intBitsToFloat(extensionInitiativeData.getB6());
		 double B7=Float.intBitsToFloat(extensionInitiativeData.getB7());
		 //System.out.println(B1+" B2:"+B2+" B3:"+B3+" B4:"+B4+" B5:"+B5+" B6:"+B6+" B7:"+B7);
		 
		 
		 double C1=Float.intBitsToFloat(extensionInitiativeData.getC1());
		 double C2=Float.intBitsToFloat(extensionInitiativeData.getC2());
		 double C3=Float.intBitsToFloat(extensionInitiativeData.getC3());
		 double C4=Float.intBitsToFloat(extensionInitiativeData.getC4());
		 double C5=Float.intBitsToFloat(extensionInitiativeData.getC5());
		 //System.out.println(C1+" C2:"+C2+" C3:"+C3+" C4:"+C4+" C5:"+C5);
		 
		 /**3号流量计**/
		//工况流量累计3  51
		 double workFlowAddUp3=0.0;
		 if(extensionInitiativeData.isIs_workFlowAddUp3_8()){
			 workFlowAddUp3= Double.longBitsToDouble(extensionInitiativeData.getWorkFlowAddUp3());				
		 }else {
			 workFlowAddUp3=Float.intBitsToFloat((int)extensionInitiativeData.getWorkFlowAddUp3());
		 }			 
		//标况流量累计3  52
		 double standardFlowAddUp3=0.0;
		 if(extensionInitiativeData.isIs_standardFlowAddUp3_8()){
			 standardFlowAddUp3= Double.longBitsToDouble(extensionInitiativeData.getStandardFlowAddUp3());				
		 }else {
			 standardFlowAddUp3=Float.intBitsToFloat((int)extensionInitiativeData.getStandardFlowAddUp3());
		 }
		//工况计瞬时流量3  53
		 double workFlow3=Float.intBitsToFloat(extensionInitiativeData.getWorkFlow3());	
		//标况计瞬时流量3  54
		 double standardFlow3=Float.intBitsToFloat(extensionInitiativeData.getStandardFlow3());	
		// 温度3 55
		 double workTemperature3=Float.intBitsToFloat(extensionInitiativeData.getWorkTemperature3());	
		// 压力3 56
		 double workPressure3=Float.intBitsToFloat(extensionInitiativeData.getWorkPressure3());	
		 
		 /**4号流量计**/
		//工况流量累计4  5A
		 double workFlowAddUp4=0.0;
		 if(extensionInitiativeData.isIs_workFlowAddUp4_8()){
			 workFlowAddUp4= Double.longBitsToDouble(extensionInitiativeData.getWorkFlowAddUp4());				
		 }else {
			 workFlowAddUp4=Float.intBitsToFloat((int)extensionInitiativeData.getWorkFlowAddUp4());
		 }			 
		//标况流量累计4  5B
		 double standardFlowAddUp4=0.0;
		 if(extensionInitiativeData.isIs_standardFlowAddUp4_8()){
			 standardFlowAddUp4= Double.longBitsToDouble(extensionInitiativeData.getStandardFlowAddUp4());				
		 }else {
			 standardFlowAddUp4=Float.intBitsToFloat((int)extensionInitiativeData.getStandardFlowAddUp4());
		 }
		//工况计瞬时流量4  5C
		 double workFlow4=Float.intBitsToFloat(extensionInitiativeData.getWorkFlow4());	
		//标况计瞬时流量4  5D
		 double standardFlow4=Float.intBitsToFloat(extensionInitiativeData.getStandardFlow4());	
		// 温度4 5E
		 double workTemperature4=Float.intBitsToFloat(extensionInitiativeData.getWorkTemperature4());	
		// 压力4 5F
		 double workPressure4=Float.intBitsToFloat(extensionInitiativeData.getWorkPressure4());	
		
		
		// 获取基础数据公式常量值
		Double LEVEL_MAX=taTank_map.get("LEVEL_MAX")==null?null:Double.valueOf(taTank_map.get("LEVEL_MAX").toString());
		Double LEVEL_MAX2=taTank_map.get("levelMax2")==null?null:Double.valueOf(taTank_map.get("levelMax2").toString());
		Double LEVEL_UP=taTank_map.get("LEVEL_UP")==null?null:Double.valueOf(taTank_map.get("LEVEL_UP").toString());
	    Double LEVEL_DOWN=taTank_map.get("LEVEL_DOWN")==null?null:Double.valueOf(taTank_map.get("LEVEL_DOWN").toString());
	    Double PRESSURE_MAX=taTank_map.get("PRESSURE_MAX")==null?null:Double.valueOf(taTank_map.get("PRESSURE_MAX").toString());
	    Double PRESSURE_UP=taTank_map.get("PRESSURE_UP")==null?null:Double.valueOf(taTank_map.get("PRESSURE_UP").toString());
	    Double PRESSURE_DOWN=taTank_map.get("PRESSURE_DOWN")==null?null:Double.valueOf(taTank_map.get("PRESSURE_DOWN").toString());
	    Double LEVEL_CHECK=taTank_map.get("LEVEL_CHECK")==null?null:Double.valueOf(taTank_map.get("LEVEL_CHECK").toString());
	    Double LEVEL_CHECK2=taTank_map.get("levelCheck2")==null?null:Double.valueOf(taTank_map.get("levelCheck2").toString());
	    String eQUIPMENT_ID=taTank_map.get("EQUIPMENT_NO").toString();//
	    
	    //---报警---温度,压力,瞬时流量,卸车区压力---------
	    Double outgoing_temperature_up=taTank_map.get("outgoing_temperature_up")==null?null:Double.valueOf(taTank_map.get("outgoing_temperature_up").toString());
	    Double outgoing_temperature_down=taTank_map.get("outgoing_temperature_down")==null?null:Double.valueOf(taTank_map.get("outgoing_temperature_down").toString());
	    
	    Double outgoing_pressure_up=taTank_map.get("outgoing_pressure_up")==null?null:Double.valueOf(taTank_map.get("outgoing_pressure_up").toString());
	    Double outgoing_pressure_down=taTank_map.get("outgoing_pressure_down")==null?null:Double.valueOf(taTank_map.get("outgoing_pressure_down").toString());
	    
	    Double outbound_instantaneous_flow_up=taTank_map.get("outbound_instantaneous_flow_up")==null?null:Double.valueOf(taTank_map.get("outbound_instantaneous_flow_up").toString());
	    Double outbound_instantaneous_flow_down=taTank_map.get("outbound_instantaneous_flow_down")==null?null:Double.valueOf(taTank_map.get("outbound_instantaneous_flow_down").toString());
	    
	    Double unloading_area_pressure=taTank_map.get("unloading_area_pressure")==null?null:Double.valueOf(taTank_map.get("unloading_area_pressure").toString());
	    
	    
	    // 处理液位偏差值
	    if(LEVEL_CHECK!=null){
	    	//Double LEVEL_CHECK= Double.valueOf(taTank_map.get("LEVEL_CHECK").toString());
	 		 if(liquidLevel!=0){
	 			 liquidLevel=liquidLevel+LEVEL_CHECK;
	 		 }
	 		 
	    }
	   // 处理液位2偏差值
	    if(LEVEL_CHECK2!=null){
		    if(liquidLevel2!=0){
				 liquidLevel2=liquidLevel2+LEVEL_CHECK2;
			 }
	    }
		 
	    if(LEVEL_MAX!=null){//处理液位最大值处理	
	    	// 处理液位最大限制界限
	    	if(LEVEL_MAX<liquidLevel){
	    		liquidLevel=LEVEL_MAX;
	    	}	    		    	
	    }
	    
	    if(LEVEL_MAX2!=null){//处理液位2最大值处理	
		    if(LEVEL_MAX2<liquidLevel2){
	    		liquidLevel2=LEVEL_MAX2;
	    	}
	    }
	    
	    // 特殊情况杨敏提出的
	    if("11116700073".equals(terminalPhone)) {
	    	liquidLevel=liquidLevel*0.875;
	    }
	    
	    //System.out.println("level_max:"+level_max+"  liquidLevel:"+liquidLevel+"  LEVEL_UP:"+LEVEL_UP);
	    
		//处理容积
	    Double Density=0.0;
    	Double V1=0.00;
    	Double V2=0.00;
    	// 处理容积1
    	if(taTank_map.get("DIAMETER")!=null && taTank_map.get("LENGTH")!=null && taTank_map.get("STOREUNITNUM")!=null && taTank_map.get("SHAPE")!=null&&taTank_map.get("VOLUME")!=null){
    		Double Diameter =Double.valueOf(taTank_map.get("DIAMETER").toString());
        	Double Length =Double.valueOf(taTank_map.get("LENGTH").toString());
            Density =Double.valueOf( taTank_map.get("STOREUNITNUM").toString());
        	String tankShape=taTank_map.get("SHAPE").toString();
        	Double maxDvolume =Double.valueOf(taTank_map.get("VOLUME").toString());
        	//System.out.println("liquidLevel:"+liquidLevel+"  liquidLevel2:"+liquidLevel2+"  Diameter:"+Diameter+"  Length:"+Length+" Density:"+Density+"  tankShape:"+tankShape+"  maxDvolume:"+maxDvolume);
            if(Diameter!=0 && Length!=0){
            	V1=tankVolumeCalculate.CalVolume(liquidLevel, Diameter, Length, Density, tankShape, maxDvolume); 
            }
    	}
    	
    	// 处理容积2
    	if(taTank_map.get("diameter2")!=null && taTank_map.get("length2")!=null && taTank_map.get("STOREUNITNUM")!=null && taTank_map.get("SHAPE")!=null){
    		Double Diameter =Double.valueOf(taTank_map.get("diameter2").toString());
        	Double Length =Double.valueOf(taTank_map.get("length2").toString());
            Density =Double.valueOf( taTank_map.get("STOREUNITNUM").toString());
        	String tankShape=taTank_map.get("SHAPE").toString();
        	//Double maxDvolume =Double.valueOf(taTank_map.get("VOLUME").toString());
        	//System.out.println("liquidLevel:"+liquidLevel+"  liquidLevel2:"+liquidLevel2+"  Diameter:"+Diameter+"  Length:"+Length+" Density:"+Density+"  tankShape:"+tankShape+"  maxDvolume:"+maxDvolume);           
        	if(Diameter!=0 && Length!=0){
        		V2=tankVolumeCalculate.CalVolume(liquidLevel2, Diameter, Length, Density, tankShape, null);  
        	}
    	}
	    //System.out.println("V1:"+V1+" V2:"+V2); 
    	   	
	    
    	TaDataPo po=new TaDataPo();
		 po.setEQUIPMENT_ID(eQUIPMENT_ID);
		 po.setDA_UP(DateUtils.BCDdatetime2datetime(dateTime));
		 
		 po.setPRESSURE(pressure1>0?pressure1:0);
		 po.setLEVELS(liquidLevel>0?liquidLevel:0);
		 po.setLEVELS1(liquidLevel2>0?liquidLevel2:0);
		 po.setPRESSURE1(pressure2>0?pressure2:0);
		 po.setPRESSURE2(pressure3>0?pressure3:0);
		 po.setGAS(gasReveal>0?gasReveal:0);
		 po.setGAS1(gasReveal2>0?gasReveal2:0);
		 po.setTEMPERATURE(temperature);
		 po.setTEMPERATURE1(temperature2);
		 
		 po.setWORK_ADD_UP(workFlowAddUp);
		 po.setSTAND_ADD_UP(standardFlowAddUp);
		 po.setWORK_FLOW(workFlow);
		 po.setSTAND_FLOW(standardFlow);
		 po.setTEMPERATURE2(workTemperature);
		 po.setPRESSURE3(workPressure>0?workPressure:0);
		 
		 po.setWORK_ADD_UP1(workFlowAddUp2);
		 po.setSTAND_ADD_UP1(standardFlowAddUp2);
		 po.setWORK_FLOW1(workFlow2);
		 po.setSTAND_FLOW1(standardFlow2);
		 po.setTEMPERATURE3(workTemperature2);
		 po.setPRESSURE4(workPressure2>0?workPressure2:0);
		 
		 po.setA1(A1);
		 po.setA2(A2);
		 po.setA3(A3);
		 po.setA4(A4);
		 po.setA5(A5);
		 po.setA6(A6);	
		 
		 po.setB1(B1);
		 po.setB2(B2);
		 po.setB3(B3);
		 po.setB4(B4);
		 po.setB5(B5);
		 po.setB6(B6);
		 po.setB7(B7);
		 
		 po.setC1(C1);
		 po.setC2(C2);
		 po.setC3(C3);
		 po.setC4(C4);
		 po.setC5(C5);
		 
		 po.setCURRENT_VOLUME(V1);
		 po.setCURRENT_VOLUME1(V2);
		 po.setSTOREUNITNUM(Density);
		 po.setLEVEL_MAX(LEVEL_MAX);
		 
		 po.setTANKPRESSURE3(tankPressure3);
		 po.setLEVELS3(liquidLevel3);
		 po.setTANKPRESSURE4(tankPressure4);
		 po.setLEVELS4(liquidLevel4);
		 
		 int result= taDataBll.addInfoTaDataNew(po);
		 
		 // 处理液位无效值是否处理报警，但是数据没有插入，所以其他报警数据都为无效
		 boolean is_Handle=true;
			if(result==2){
				is_Handle=false;
			}
			
		// 触发事件处理
		/*if(is_Handle){
			Old_EventValuePo old_EventValuePo=new Old_EventValuePo();
		    old_EventValuePo.setEQUIPMENT_ID(eQUIPMENT_ID);
		    old_EventValuePo.setLEVEL_MAX(LEVEL_MAX);
		    old_EventValuePo.setLEVEL_UP(LEVEL_UP);
		    old_EventValuePo.setLEVEL_DOWN(LEVEL_DOWN);
		    old_EventValuePo.setPRESSURE_MAX(PRESSURE_MAX);
		    old_EventValuePo.setPRESSURE_UP(PRESSURE_UP);
		    old_EventValuePo.setPRESSURE_DOWN(PRESSURE_DOWN);
		    old_EventValuePo.setLiquidLevel_KPA(liquidLevel);
		    old_EventValuePo.setPressure1_KPA(pressure1);
		    old_EventValuePo.setGasReveal(gasReveal);
		    old_EventValuePo.setGasReveal2(gasReveal2);
			HandleEvent(old_EventValuePo);
		}*/
		
		//--------黄工 丰乐 报警---------
		
		FL_EventLimitPo eventLimitPo=new FL_EventLimitPo();
		eventLimitPo.setOutgoing_temperature_up(outgoing_temperature_up);
		eventLimitPo.setOutgoing_temperature_down(outgoing_temperature_down);	    
		eventLimitPo.setOutgoing_pressure_up(outgoing_pressure_up);
		eventLimitPo.setOutgoing_pressure_down(outgoing_pressure_down);	    
		eventLimitPo.setOutbound_instantaneous_flow_up(outbound_instantaneous_flow_up);
		eventLimitPo.setOutbound_instantaneous_flow_down(outbound_instantaneous_flow_down);	    
		eventLimitPo.setUnloading_area_pressure(unloading_area_pressure);
		
		FL_EventValuesPo eventValuesPo =new FL_EventValuesPo();
		eventValuesPo.setEQUIPMENT_ID(eQUIPMENT_ID);
		eventValuesPo.setDA_UP(DateUtils.BCDdatetime2datetime(dateTime));		
		eventValuesPo.setStandardFlowAddUp1(standardFlowAddUp);
		eventValuesPo.setStandardFlow1(standardFlow);
		eventValuesPo.setWorkTemperature1(workTemperature);
		eventValuesPo.setWorkPressure1(workPressure);		
		eventValuesPo.setStandardFlowAddUp2(standardFlowAddUp2);
		eventValuesPo.setStandardFlow2(standardFlow2);
		eventValuesPo.setWorkTemperature2(workTemperature2);
		eventValuesPo.setWorkPressure2(workPressure2);		
		eventValuesPo.setStandardFlowAddUp3(standardFlowAddUp3);
		eventValuesPo.setStandardFlow3(standardFlow3);
		eventValuesPo.setWorkTemperature3(workTemperature3);
		eventValuesPo.setWorkPressure3(workPressure3);		
		eventValuesPo.setStandardFlowAddUp4(standardFlowAddUp4);
		eventValuesPo.setStandardFlow4(standardFlow4);
		eventValuesPo.setWorkTemperature4(workTemperature4);
		eventValuesPo.setWorkPressure4(workPressure4);
		
		new Alarm_2_Controller().handleAlarm(eventLimitPo, eventValuesPo);
		
	}
	
	private void HandleEvent(Old_EventValuePo old_EventValuePo){
		boolean is_mg_set_event=false;
		MgSetEventBean mgSetEventBean=new MgSetEventBean();
		mgSetEventBean.setEQUIPMENT_ID(old_EventValuePo.getEQUIPMENT_ID());
		Double LEVEL_MAX=old_EventValuePo.getLEVEL_MAX();
		Double liquidLevel=old_EventValuePo.getLiquidLevel_KPA();
		Double LEVEL_UP=old_EventValuePo.getLEVEL_UP();
		Double LEVEL_DOWN=old_EventValuePo.getLEVEL_DOWN();
		
		Double PRESSURE_MAX=old_EventValuePo.getPRESSURE_MAX();
		Double pressure1=old_EventValuePo.getPressure1_KPA();
		Double PRESSURE_UP=old_EventValuePo.getPRESSURE_UP();
		Double PRESSURE_DOWN=old_EventValuePo.getPRESSURE_DOWN();
		Double gasReveal=old_EventValuePo.getGasReveal();
		Double gasReveal2=old_EventValuePo.getGasReveal2();
		
		if(LEVEL_MAX!=null){//处理液位最大值处理
	    	double level_double=(liquidLevel/LEVEL_MAX)*100;
	    	//System.out.println("level_double:"+level_double);
	    	if(LEVEL_UP!=null && level_double>LEVEL_UP){
	    		// 液位超过最大上限事件
	    		mgSetEventBean.setLEVELS_TYPE(0);
	    		mgSetEventBean.setLEVELS_VALUE(level_double-LEVEL_UP);
	    		is_mg_set_event=true;
	    	}
	    	if(LEVEL_DOWN!=null && level_double<LEVEL_DOWN){
	    		// 液位超过低于下限事件
	    		mgSetEventBean.setLEVELS_TYPE(1);
	    		mgSetEventBean.setLEVELS_VALUE(LEVEL_DOWN-level_double);
	    		is_mg_set_event=true;
	    	}
	    }
	    // System.out.println("level_max:"+level_max+"  liquidLevel:"+liquidLevel+"  LEVEL_UP:"+LEVEL_UP);
	    
	    if(PRESSURE_MAX!=null){
	    	double pressure_double=(pressure1/PRESSURE_MAX)*100;
	    	// System.out.println("pressure_double:"+pressure_double);
	    	if(PRESSURE_UP!=null && pressure_double>PRESSURE_UP){
	    		// 液位超过最大上限事件
	    		mgSetEventBean.setPRESSURE_TYPE(0);
	    		mgSetEventBean.setPRESSURE_VALUE(pressure_double-PRESSURE_UP);
	    		is_mg_set_event=true;
	    	}
	    	if(PRESSURE_DOWN!=null && pressure_double<PRESSURE_DOWN){
	    		// 液位超过低于下限事件
	    		mgSetEventBean.setPRESSURE_TYPE(1);
	    		mgSetEventBean.setPRESSURE_VALUE(PRESSURE_DOWN-pressure_double);
	    		is_mg_set_event=true;
	    	}
	    }
	    
	    // 气体泄漏报警
	    if(gasReveal>20){
	    	mgSetEventBean.setVACUUM_TYPE(0);
	    	mgSetEventBean.setVACUUM_VALUE(gasReveal);
	    	is_mg_set_event=true;
	    }
	    // 气体泄漏2报警
	    if(gasReveal2>20){
	    	mgSetEventBean.setVACUUM_TYPE(0);
	    	mgSetEventBean.setVACUUM_VALUE(gasReveal2);
	    	is_mg_set_event=true;
	    }
	    
	    // 处理事件
    	if(is_mg_set_event){
    		mgSetController.handleMgSet(mgSetEventBean);
    	}
	}
}
