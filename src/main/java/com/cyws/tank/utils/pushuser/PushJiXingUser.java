package com.cyws.tank.utils.pushuser;

import java.util.HashMap;
import java.util.Map;

import com.cyws.tank.bean.common.JiXingBean;
import com.cyws.tank.utils.common.HttpClientUtil;
import com.cyws.tank.utils.log.LogManager;
import com.cyws.tank.utils.po.GainTokenPo;
import com.cyws.tank.utils.po.PushSensorToUserPo;
import com.cyws.tank.utils.po.RePushResultPo;
import com.cyws.tank.utils.serializer.FastJsonSerializer;

public class PushJiXingUser {

private FastJsonSerializer fastJsonSerializer=new FastJsonSerializer();

   public void handlePushUser(PushSensorToUserPo po){
	   
	   if(po==null){
		   return;
	   }
	   if(JiXingBean.token==null || "".equals(JiXingBean.token)){
		   gainToken();
	   }
	   
	   if(JiXingBean.token!=null || !"".equals(JiXingBean.token)){
		   String str_json = "";
		   try {
			    str_json=fastJsonSerializer.toJSON(po);
				String token="bearer "+JiXingBean.token;
				String strResult =HttpClientUtil.doPostFormTojson(JiXingBean.push_url, token, str_json);
				if(!"".equals(strResult)){
					RePushResultPo Po=fastJsonSerializer.toObject(strResult, RePushResultPo.class);
					if(Po.getError()!=null || "invalid_token".equals(Po.getError())){
						// 刷新token
						String re=refreshToken();
						if(re!=null){
							HttpClientUtil.doPostFormTojson(JiXingBean.push_url, JiXingBean.token, str_json);
						}
					}
				}
		   } catch (Exception e) {
			 System.out.println(e);	
		   }		   
		   LogManager.getInstance().PrintPushJsonLog(str_json);//打印log
	   }
	   
   }
   
   
   private  String gainToken(){
	   String access_token = null;
	   try {
		   Map<String, String> params=new HashMap<String, String>();
			params.put("grant_type", JiXingBean.grant_type_gain);
			params.put("scope", JiXingBean.scope);
			params.put("username", JiXingBean.username);
			params.put("password", JiXingBean.password);
			String strResult =HttpClientUtil.doPostFormToAttestation(JiXingBean.url1, JiXingBean.userName, JiXingBean.userPass,params);
			if(!"".equals(strResult)){
				GainTokenPo gainTokenPo=fastJsonSerializer.toObject(strResult, GainTokenPo.class);
				access_token=gainTokenPo.getAccess_token();
				if(access_token!=null && !"".equals(access_token)){
					JiXingBean.token=access_token;
					JiXingBean.refresh_token=gainTokenPo.getRefresh_token();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	   
		return access_token;
   }
   
   private  String refreshToken(){
	   String access_token = null;
	   try {
		   Map<String, String> params=new HashMap<String, String>();
		   params.put("grant_type", JiXingBean.grant_type_refresh);
		   params.put("refresh_token","22a9fe51-2358-420e-a4db-3373b8a27a38");//JiXingBean.refresh_token  "22a9fe51-2358-420e-a4db-3373b8a27a38"
		   String strResult =HttpClientUtil.doPostFormToAttestation(JiXingBean.url1, JiXingBean.userName, JiXingBean.userPass,params);
		   // System.out.println(strResult);
		   if(!"".equals(strResult)){
				GainTokenPo gainTokenPo=fastJsonSerializer.toObject(strResult, GainTokenPo.class);
				access_token=gainTokenPo.getAccess_token();
				if(access_token!=null && !"".equals(access_token)){
					//System.out.println(access_token);
					JiXingBean.token=access_token;
					JiXingBean.refresh_token=gainTokenPo.getRefresh_token();
				}
		   }
	    } catch (Exception e) {
	    	System.out.println(e);
		}
	   return access_token;
   }
   
	/*public static void main(String[] args) {
		 mian();
		PushJiXingUser pushJiXingUser=new PushJiXingUser();
		pushJiXingUser.refreshToken();
	}
	
	private static void mian() {
		PushSensorToUserPo po=new PushSensorToUserPo();
		po.setGuan_number("JXNU1000105");// 罐箱编号
		po.setSj("2018-11-25 23:00:35");// 时间
		po.setF_sbsjsjjg("0");// 上报时间间隔
		po.setF_yl("0.14");// 压力
		po.setF_wd("-153");// 温度
		po.setF_zlbfb("88.0");// 质量百分比
		po.setLng("117.17713281973");// 经度
		po.setLat("36.192441001813");// 纬度
		po.setAlarm("1");// 是否报警
		po.setAlarm_status("液位报警");// 报警信息
		po.setF_dian("100");// 电量
		po.setF_jzzl("16899.2");// 介质质量
		po.setNumber(number);// 设备号
		po.setImei(imei);// 设备IMEI
		po.setF_cggcrj(f_cggcrj); // 公称容积
		po.setF_rqlx(f_rqlx); // 容器类型
		po.setF_jz(f_jz);// 介质
		 	
		
		FastJsonSerializer fastJsonSerializer=new FastJsonSerializer();
		String str=fastJsonSerializer.toJSON(po);
		// System.out.println(str);  invalid_token // 刷新token的
		String url="http://47.105.47.32:9526/api/sensor/dingli/tank";
		String token="bearer 7a99d74f-3ab8-4d11-8753-93ef6f6c09c8";
		HttpClientUtil.doPostFormTojson(url, token, str);
		
		String url1="http://47.105.47.32:9526/oauth/token";
		String userName="gzzj";
		String userPass="0ca175b9-c0f7-26a8-31d8-95e269332461";
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("grant_type", "password");
		params.put("scope", "all");
		params.put("username", "zjny2018");
		params.put("password", "6619e14d0ccec");
		HttpClientUtil.doPostFormToAttestation(url1, userName, userPass,params);
	}*/
}
