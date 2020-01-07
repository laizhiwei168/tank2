package com.cyws.tank.codec.decode;

import com.cyws.tank.codec.vo.LocationStatus;
import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.codec.vo.req.LocationInfoUploadMsg;
import com.cyws.tank.utils.common.DateUtils;
import com.cyws.tank.utils.common.ParseBytesUtils;

public class MsgLocationDecoder {
	
	//位置信息
		public LocationInfoUploadMsg toLocationInfoUploadMsg(PackageData packageData) {
			LocationInfoUploadMsg ret = new LocationInfoUploadMsg(packageData);
			final byte[] data = ret.getMsgBodyBytes();

			// 1. byte[0-3] 报警标志(DWORD(32))
			ret.setWarningFlagField(ParseBytesUtils.parseIntFromBytes(data, 0, 4));
			// 2. byte[4-7] 状态(DWORD(32))
			ret.setStatusField(ParseBytesUtils.parseIntFromBytes(data, 4, 4));
			LocationStatus locationStatus=byte2LocationStatus(ret.getStatusField());
			ret.setLocationStatus(locationStatus);
			// 3. byte[8-11] 纬度(DWORD(32)) 以度为单位的纬度值乘以10^6，精确到百万分之一度		
			ret.setLatitude(ParseBytesUtils.parseDoubleFromBytes(data, 8, 4)/1000000);
			// 4. byte[12-15] 经度(DWORD(32)) 以度为单位的经度值乘以10^6，精确到百万分之一度
			ret.setLongitude(ParseBytesUtils.parseDoubleFromBytes(data, 12, 4)/1000000);
			// 5. byte[16-17] 高程(WORD(16)) 海拔高度，单位为米（ m）
			ret.setElevation(ParseBytesUtils.parseIntFromBytes(data, 16, 2));
			// byte[18-19] 速度(WORD) 1/10km/h
			ret.setSpeed(ParseBytesUtils.parseDoubleFromBytes(data, 18, 2));
			// byte[20-21] 方向(WORD) 0-359，正北为 0，顺时针
			ret.setDirection(ParseBytesUtils.parseIntFromBytes(data, 20, 2));
			// byte[22-x] 时间(BCD[6]) YY-MM-DD-hh-mm-ss
			// GMT+8 时间，本标准中之后涉及的时间均采用此时区
			// ret.setTime(this.par);

			byte[] tmp = new byte[6];
			System.arraycopy(data, 22, tmp, 0, 6);
			String time = ParseBytesUtils.parseBcdStringFromBytes(data, 22, 6);		
			ret.setTime(DateUtils.BCDdatetime2date(time));
			//System.out.println("time:"+time);
			
			return ret;
		}
		
		private LocationStatus byte2LocationStatus(int Status){
			LocationStatus locationStatus=new LocationStatus();
			String statusInformation=Integer.toBinaryString(Status);
	    	 StringBuffer strbf=new StringBuffer();
	    	 if(statusInformation.length()<32) {//解析状态信息
	    		for(int i=0;i<32-statusInformation.length();i++) {
	    			strbf.append("0");
	    		}
	    		strbf.append(statusInformation);
	    	 }else {
	    		strbf.append(statusInformation);
	    	 }	
			int bf_length= strbf.length();
			locationStatus.setIs_ACC(("1".equals(strbf.subSequence(bf_length-1, bf_length))));
			locationStatus.setIslocation(("1".equals(strbf.subSequence(bf_length-2, bf_length-1))));
			locationStatus.setIs_lat(("1".equals(strbf.subSequence(bf_length-3, bf_length-2))));
			locationStatus.setIs_log(("1".equals(strbf.subSequence(bf_length-4, bf_length-3))));
			locationStatus.setIs_operationState(("1".equals(strbf.subSequence(bf_length-5, bf_length-4))));
			locationStatus.setIs_loglatSecrecy(("1".equals(strbf.subSequence(bf_length-6, bf_length-5))));		
			locationStatus.setIs_oilWay(("1".equals(strbf.subSequence(bf_length-11, bf_length-10))));
			locationStatus.setIs_electricityWay(("1".equals(strbf.subSequence(bf_length-12, bf_length-11))));
			locationStatus.setIs_doorLock(("1".equals(strbf.subSequence(bf_length-13, bf_length-12))));
			return locationStatus;
		}
		
		private void byte2InfoAddition(byte[] additionBs){			
			Double innterTemp = null;
			Double humidity = null;
			int contentionLength=additionBs.length;
			boolean is_has=true;
	    	int index=0;
	    	int messageid=(additionBs[index] & 0xFF);
	    	int  number=(additionBs[index+1] & 0xFF);
	        //System.out.println("消息序号ID："+messageid); 
	        //System.out.println("位数："+number+"");
	        int contentionLength_index=number+2;
	        int situation=index+1;
            
            while (is_has) {
				switch (messageid) {
				case 1:
					if(number!=2){return;}
		            //serviceId=innterTemp 数据解析
	            	Integer inntertemp=(additionBs[situation+1] << 8 & 0xFFFF) + (additionBs[situation+2] & 0xFF);
		            if(0xffff!=inntertemp) {
		            	innterTemp = ((double)( inntertemp* 0.1f)-40);
		            }else {
		            	innterTemp=null;
		            }
					break;
				case 2:
					if(number!=2){ return;}
		            //serviceId=Humidity字段
		            Integer hdy=(additionBs[situation+1] << 8 & 0xFFFF) + (additionBs[situation+2] & 0xFF);
		            if(0xffff!=hdy) {
		            	humidity=(double)( hdy* 0.1f);
		            }else {
		            	humidity=null;
		            }
					break;
				}
				//处理开始定位 与 读取的字节数
				situation=situation+number+2;
	            if(contentionLength_index>=contentionLength){
	            	is_has=false;
	            	//return;
	            }else{
	            	messageid=(additionBs[situation-1] & 0xFF);
	            	number=(additionBs[situation] & 0xFF);	                
	                contentionLength_index=contentionLength_index+number+2;
	            }
            }
		}
		
}
