package com.cyws.tank.codec.decode;

import com.cyws.tank.codec.vo.ExtensionBaseData;
import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.utils.common.Utilty;

public class MsgExtensionInitiativeDecoder {
private Utilty Utilty=new Utilty();
	public ExtensionInitiativeData bytes2ExtensionInitiativeData(ExtensionBaseData extensionBaseData){
		ExtensionInitiativeData extensionInitiativeData=new ExtensionInitiativeData(extensionBaseData);
		byte[] data=extensionBaseData.getMsgExtensionBodyBytes();
		//解析协议解析头
		extensionInitiativeData.setOrderChar(data[0]&0xff);
		extensionInitiativeData.setGoalChar(data[1]&0xff);
		extensionInitiativeData.setFlowId(data[2]&0xff);
		extensionInitiativeData.setMsgBodyLength(data[3]&0xff);
		
		//解析协议体内容
		String dateTime=Utilty.toStringByte(data,4,9);
		extensionInitiativeData.setDateTime(dateTime);
		//System.out.println("主动透传  dateTime:"+dateTime+" SensorNumber:"+(data[10]&0xff));
		
		extensionInitiativeData.setSensorNumber(data[10]&0xff);//传感器个数		
			
		// 数据项解析
		byte[] tmp = new byte[data.length-11];
		System.arraycopy(data, 11, tmp, 0, tmp.length-1);
		//decoderInfo03(extensionInitiativeData,tmp);
		if(data[1]==0x01){// 储罐
			decoderInfo01(extensionInitiativeData,tmp);
		}else if(data[1]==0x03){// 气化站
			decoderInfo03(extensionInitiativeData,tmp);
		}		
        //System.out.println(extensionInitiativeData.toString());
		return extensionInitiativeData;
	}
	
	//通用传感器状态。
	public void decoderInfo01(ExtensionInitiativeData extensionInitiativeData,byte[] tmp){
		int contentionLength=tmp.length;
		boolean is_has=true;
    	int index=0;
    	int messageid=(tmp[index] & 0xFF);// 数据项
    	int  number=(tmp[index+1] & 0xFF);// 长度
        int contentionLength_index=number+2;
        int situation=index+1;
        
        while (is_has) {
			switch (messageid) {
			case 1:
				if(number!=2){break;}
	            // serviceId=innterTemp 数据解析
            	Integer pressure1=(tmp[situation+2] << 8 & 0xFFFF) + (tmp[situation+1] & 0xFF);
            	extensionInitiativeData.setPressure1(pressure1);
				break;
			case 2:
				if(number!=2){ break;}
				Integer liquidLevel=(tmp[situation+2] << 8 & 0xFFFF) + (tmp[situation+1] & 0xFF);
				extensionInitiativeData.setLiquidLevel(liquidLevel);
				break;
			case 3:
				if(number!=2){ break;}
				Integer pressure2=(tmp[situation+2] << 8 & 0xFFFF) + (tmp[situation+1] & 0xFF);
				extensionInitiativeData.setPressure2(pressure2);
				break;
			case 4:
				if(number!=2){ break;}
				Integer gasReveal=(tmp[situation+2] << 8 & 0xFFFF) + (tmp[situation+1] & 0xFF);
				extensionInitiativeData.setGasReveal(gasReveal);
				break;
			case 5:
				if(number!=2){ break;}
				Integer temperature=(tmp[situation+2] << 8) + (tmp[situation+1] & 0xFF);
				extensionInitiativeData.setTemperature(temperature);
				break;
			case 0xf0:
				if(number!=1){ break;}
				Integer powerDownDetection=(tmp[situation+1] & 0xFF);
				extensionInitiativeData.setPowerDownDetection(powerDownDetection);
				break;
			case 0xf1:
				if(number!=1){ break;}
				Integer doorOnOff=(tmp[situation+1] & 0xFF);
				extensionInitiativeData.setDoorOnOff(doorOnOff);
				break;
			case 0xf2:
				if(number!=2){ break;}
				Integer battery=(tmp[situation+2] << 8 & 0xFFFF) + (tmp[situation+1] & 0xFF);
				extensionInitiativeData.setBattery(battery);
				break;
			}
			// 处理开始定位 与 读取的字节数
			situation=situation+number+2;
            if(contentionLength_index>=contentionLength){
            	is_has=false;
            	// return;
            }else{
            	messageid=(tmp[situation-1] & 0xFF);
            	number=(tmp[situation] & 0xFF);	                
                contentionLength_index=contentionLength_index+number+2;
                //System.out.println(messageid+" = "+number+"  = "+contentionLength_index+" = "+contentionLength);
            }
        }
	}
	
	// plc传感器。
	public void decoderInfo03(ExtensionInitiativeData extensionInitiativeData,byte[] tmp){
		int contentionLength=tmp.length;
		boolean is_has=true;
    	int index=0;
    	int messageid=(tmp[index] & 0xFF);// 数据项
    	int  number=(tmp[index+1] & 0xFF);// 长度
        int contentionLength_index=number+2;
        int situation=index+1;
        
        while (is_has) {
			switch (messageid) {
			case 1:
	            // serviceId=innterTemp 数据解析
            	int pressure1=Utilty.bytes2Int(tmp, situation+1,number);
            	extensionInitiativeData.setPressure1(pressure1);
				break;
			case 2:
				int liquidLevel=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setLiquidLevel(liquidLevel);
				//System.out.println("  ---   "+liquidLevel);
				break;
			case 3:
				int pressure2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setPressure2(pressure2);
				break;
			case 4:
				int gasReveal=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setGasReveal(gasReveal);
				break;
			case 5:
				int temperature=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setTemperature(temperature);
				break;
			case 6:
				int gasReveal2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setGasReveal2(gasReveal2);
				break;
			case 7:
				int pressure3=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setPressure3(pressure3);
				break;
			case 8:
				int liquidLevel2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setLiquidLevel2(liquidLevel2);
				break;
			case 9:
				int temperature2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setTemperature2(temperature2);
				break;
			case 0x11:
				int addSmellyNumber=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setAddSmellyNumber(addSmellyNumber);
				break;
			case 0xA1:
				int A1=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setA1(A1);
				break;
			case 0xA2:
				int A2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setA2(A2);
				break;
			case 0xA3:
				int A3=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setA3(A3);
				break;
			case 0xA4:
				int A4=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setA4(A4);
				break;
			case 0xA5:
				int A5=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setA5(A5);
				break;
			case 0xA6:
				int A6=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setA6(A6);
				break;
			case 0xB1:
				int B1=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB1(B1);
				break;
			case 0xB2:
				int B2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB2(B2);
				break;
			case 0xB3:
				int B3=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB3(B3);
				break;
			case 0xB4:
				int B4=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB4(B4);
				break;
			case 0xB5:
				int B5=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB5(B5);
				break;
			case 0xB6:
				int B6=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB6(B6);
				break;
			case 0xB7:
				int B7=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setB7(B7);
				break;
			case 0xC1:
				int C1=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setC1(C1);
				break;
			case 0xC2:
				int C2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setC2(C2);
				break;
			case 0xC3:
				int C3=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setC3(C3);
				break;
			case 0xC4:
				int C4=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setC4(C4);
				break;
			case 0xC5:
				int C5=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setC5(C5);
				break;				
			case 0xD1:
				if(number==8){	
					// 丰乐特殊情况
					/*String str_later=Utilty.toStringByte(tmp, situation+5,4);
					if("ffffffff".equals(str_later.toLowerCase())){*/
					int int_later=Utilty.bytes2Int(tmp, situation+5,4);
					if(int_later==0){
						int int_workFlowAddUp=Utilty.bytes2Int(tmp, situation+1,4);
						extensionInitiativeData.setWorkFlowAddUp(int_workFlowAddUp);
						extensionInitiativeData.setIs_workFlowAddUp_8(false);
					}else{
						long workFlowAddUp=Utilty.bytes2Long(tmp, situation+1,number);
						extensionInitiativeData.setWorkFlowAddUp(workFlowAddUp);
						extensionInitiativeData.setIs_workFlowAddUp_8(true);
					}					
				}				
				break;
			case 0xD2:
				if(number==8){
					// 丰乐特殊情况
					/*String str_later=Utilty.toStringByte(tmp, situation+5,4);
					if("ffffffff".equals(str_later.toLowerCase())){*/
					int int_later=Utilty.bytes2Int(tmp, situation+5,4);
					String terminalPhone=extensionInitiativeData.getPackageData().getMsgHeader().getTerminalPhone();
					if(int_later==0 && !"11116700204".equals(terminalPhone)&& !"11116700194".equals(terminalPhone)&& !"11116700187".equals(terminalPhone)){//11116700204这台是特殊处理
						int int_standardFlowAddUp=Utilty.bytes2Int(tmp, situation+1,4);
						extensionInitiativeData.setStandardFlowAddUp(int_standardFlowAddUp);
						extensionInitiativeData.setIs_standardFlowAddUp_8(false);
					}else{
						long standardFlowAddUp=Utilty.bytes2Long(tmp, situation+1,number);
						extensionInitiativeData.setStandardFlowAddUp(standardFlowAddUp);
						extensionInitiativeData.setIs_standardFlowAddUp_8(true);
					}
				}
				break;
			case 0xD3:
				int workFlow=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setWorkFlow(workFlow);
				break;
			case 0xD4:
				int standardFlow=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setStandardFlow(standardFlow);
				break;
			case 0xD5:
				int workTemperature=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setWorkTemperature(workTemperature);
				break;
			case 0xD6:
				int workPressure=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setWorkPressure(workPressure);
				break;
			case 0xD7:
				int residueFlow=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setResidueFlow(residueFlow);
				break;
				
			case 0xD8:
				int liquidLevel3=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setLiquidLevel3(liquidLevel3);
				break;
			case 0xD9:
				int tankPressure3=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setTankPressure3(tankPressure3);
				break;
				
			case 0xE1:
				if(number==8){		
					// 丰乐特殊情况
					/*String str_later=Utilty.toStringByte(tmp, situation+5,4);
					if("ffffffff".equals(str_later.toLowerCase())){*/
					int int_later=Utilty.bytes2Int(tmp, situation+5,4);
					if(int_later==0){
						int int_workFlowAddUp2=Utilty.bytes2Int(tmp, situation+1,4);
						extensionInitiativeData.setWorkFlowAddUp2(int_workFlowAddUp2);
						extensionInitiativeData.setIs_workFlowAddUp2_8(false);
					}else{
						long workFlowAddUp2=Utilty.bytes2Long(tmp, situation+1,number);
						extensionInitiativeData.setWorkFlowAddUp2(workFlowAddUp2);
						extensionInitiativeData.setIs_workFlowAddUp2_8(true);
					}
				}
				break;
			case 0xE2:
				if(number==8){
					// 丰乐特殊情况
					/*String str_later=Utilty.toStringByte(tmp, situation+5,4);
					if("ffffffff".equals(str_later.toLowerCase())){*/
					int int_later=Utilty.bytes2Int(tmp, situation+5,4);
					if(int_later==0){
						int int_standardFlowAddUp2=Utilty.bytes2Int(tmp, situation+1,4);
						extensionInitiativeData.setStandardFlowAddUp2(int_standardFlowAddUp2);
						extensionInitiativeData.setIs_standardFlowAddUp2_8(false);
					}else{
						long standardFlowAddUp2=Utilty.bytes2Long(tmp, situation+1,number);
						extensionInitiativeData.setStandardFlowAddUp2(standardFlowAddUp2);
						extensionInitiativeData.setIs_standardFlowAddUp2_8(true);
					}
				}
				break;
			case 0xE3:
				int workFlow2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setWorkFlow2(workFlow2);
				break;
			case 0xE4:
				int standardFlow2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setStandardFlow2(standardFlow2);
				break;
			case 0xE5:
				int workTemperature2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setWorkTemperature2(workTemperature2);
				break;
			case 0xE6:
				int workPressure2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setWorkPressure2(workPressure2);
				break;
			case 0xE7:
				int residueFlow2=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setResidueFlow2(residueFlow2);
				break;
			case 0xE8:
				int liquidLevel4=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setLiquidLevel4(liquidLevel4);
				break;
			case 0xE9:
				int tankPressure4=Utilty.bytes2Int(tmp, situation+1,number);
				extensionInitiativeData.setTankPressure4(tankPressure4);
				break;
			case 0xf0:
				int powerDownDetection=(tmp[situation+1] & 0xFF);
				extensionInitiativeData.setPowerDownDetection(powerDownDetection);
				break;
			case 0xf1:
				int doorOnOff=(tmp[situation+1] & 0xFF);
				extensionInitiativeData.setDoorOnOff(doorOnOff);
				break;
			case 0xf2:
				int battery=(tmp[situation+1] << 8 & 0xFFFF) + (tmp[situation+2] & 0xFF);
				extensionInitiativeData.setBattery(battery);
				break;
				
				//===黄工新添加的 3号和4号流量计===
				case 0x51:
					if(number==8){		
						// 丰乐特殊情况
						int int_later=Utilty.bytes2Int(tmp, situation+5,4);
						if(int_later==0){
							int int_workFlowAddUp3=Utilty.bytes2Int(tmp, situation+1,4);
							extensionInitiativeData.setWorkFlowAddUp3(int_workFlowAddUp3);
							extensionInitiativeData.setIs_workFlowAddUp3_8(false);
						}else{
							long workFlowAddUp3=Utilty.bytes2Long(tmp, situation+1,number);
							extensionInitiativeData.setWorkFlowAddUp3(workFlowAddUp3);
							extensionInitiativeData.setIs_workFlowAddUp3_8(true);
						}
					}
					break;
				case 0x52:
					if(number==8){
						// 丰乐特殊情况
						int int_later=Utilty.bytes2Int(tmp, situation+5,4);
						if(int_later==0){
							int int_standardFlowAddUp3=Utilty.bytes2Int(tmp, situation+1,4);
							extensionInitiativeData.setStandardFlowAddUp3(int_standardFlowAddUp3);
							extensionInitiativeData.setIs_standardFlowAddUp3_8(false);
						}else{
							long standardFlowAddUp3=Utilty.bytes2Long(tmp, situation+1,number);//
							extensionInitiativeData.setStandardFlowAddUp3(standardFlowAddUp3);
							extensionInitiativeData.setIs_standardFlowAddUp3_8(true);
						}
					}
					break;
				case 0x53:
					int workFlow3=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setWorkFlow3(workFlow3);
					break;
				case 0x54:
					int standardFlow3=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setStandardFlow3(standardFlow3);
					break;
				case 0x55:
					int workTemperature3=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setWorkTemperature3(workTemperature3);
					break;
				case 0x56:
					int workPressure3=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setWorkPressure3(workPressure3);
					break;
					
			/****/
				case 0x5A:
					if(number==8){		
						// 丰乐特殊情况
						int int_later=Utilty.bytes2Int(tmp, situation+5,4);
						if(int_later==0){
							int int_workFlowAddUp4=Utilty.bytes2Int(tmp, situation+1,4);
							extensionInitiativeData.setWorkFlowAddUp4(int_workFlowAddUp4);
							extensionInitiativeData.setIs_workFlowAddUp4_8(false);
						}else{
							long workFlowAddUp4=Utilty.bytes2Long(tmp, situation+1,number);
							extensionInitiativeData.setWorkFlowAddUp4(workFlowAddUp4);
							extensionInitiativeData.setIs_workFlowAddUp4_8(true);
						}
					}
					break;
				case 0x5B:
					if(number==8){
						// 丰乐特殊情况
						int int_later=Utilty.bytes2Int(tmp, situation+5,4);
						if(int_later==0){
							int int_standardFlowAddUp4=Utilty.bytes2Int(tmp, situation+1,4);
							extensionInitiativeData.setStandardFlowAddUp4(int_standardFlowAddUp4);
							extensionInitiativeData.setIs_standardFlowAddUp4_8(false);
						}else{
							long standardFlowAddUp4=Utilty.bytes2Long(tmp, situation+1,number);//
							extensionInitiativeData.setStandardFlowAddUp4(standardFlowAddUp4);
							extensionInitiativeData.setIs_standardFlowAddUp4_8(true);
						}
					}
					break;
				case 0x5C:
					int workFlow4=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setWorkFlow4(workFlow4);
					break;
				case 0x5D:
					int standardFlow4=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setStandardFlow4(standardFlow4);
					break;
				case 0x5E:
					int workTemperature4=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setWorkTemperature4(workTemperature4);
					break;
				case 0x5F:
					int workPressure4=Utilty.bytes2Int(tmp, situation+1,number);//
					extensionInitiativeData.setWorkPressure4(workPressure4);
					break;
					
				// 11  04 臭次数
				
			}
			// 处理开始定位 与 读取的字节数
			situation=situation+number+2;
            if(contentionLength_index>=contentionLength){
            	is_has=false;
            	// return;
            }else{
            	messageid=(tmp[situation-1] & 0xFF);
            	//System.out.println(messageid);
            	number=(tmp[situation] & 0xFF);
                contentionLength_index=contentionLength_index+number+2;
            }
        }
	}	
}
