package com.cyws.tank.TANK2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.cyws.tank.push.client.XSocketClientManager;
import com.cyws.tank.utils.common.TankVolumeCalculate;
import com.cyws.tank.utils.po.PushJiTeToUserPo;
import com.cyws.tank.utils.serializer.FastJsonSerializer;


public class Test {

	public static void main(String[] args) {
		/*String str="123456789";
		System.out.println(str.substring(3,str.length()));
		System.out.println(createID());*/
		/*double d= 80/20;
		System.out.println(d);*/
		
		/*double liquidLevel_KPA=40*((11266-590)/(2400.0))*101.972;
		System.out.println(liquidLevel_KPA);*/
		//V:37.88798396508921     maxDvolume:60.54
		/*BigDecimal a = new BigDecimal(200);
		BigDecimal b = new BigDecimal(3);
		System.out.println(a.divide(b,2,BigDecimal.ROUND_HALF_UP));*/
		
		 /*BigDecimal d = new BigDecimal("37.8865225"); 
		 BigDecimal r = new BigDecimal("60.54");
		 System.out.println(d.divide(r,2,BigDecimal.ROUND_HALF_UP).setScale(2,RoundingMode.HALF_EVEN));*/
		
		/*PushJiTeToUserPo pushJiTeToUserPo=new PushJiTeToUserPo();
		pushJiTeToUserPo.setEQUIPMENT_ID("123456");
		pushJiTeToUserPo.setLEVELS(11.0);
		pushJiTeToUserPo.setPRESSURE(12.01);
		pushJiTeToUserPo.setDA_UP("1231564");
		String  json=new FastJsonSerializer().toJSON(pushJiTeToUserPo);
		System.out.println(json);*/
		
		/*Map<String, Object> m=new HashMap<String, Object>();
		m.put("EQUIPMENT_ID", "123456");
		m.put("LEVELS", 11.0);
		m.put("PRESSURE", 12.01);
		m.put("DA_UP", "wewqweqwe");
		String  json1=new FastJsonSerializer().toJSON(m);
		
		System.out.println(json1);*/
		
		//System.out.println(new TankVolumeCalculate().CalVolume(852, 2.6, 11.41, 425, "立式", null));
		
		//System.out.println(Float.intBitsToFloat(1148913657));
	}
	
	private static byte[] lock = new byte[0];
	
	// 位数，默认是8位
	private final static long w = 100000000;
    
	public static String createID() {
		long r = 0;
		synchronized (lock) {
			r = (long) ((Math.random() + 1) * w);
		}
        System.out.println(System.currentTimeMillis()+"   "+String.valueOf(r).substring(1)+"   "+String.valueOf(r));
		return System.currentTimeMillis() + String.valueOf(r);
	}
}
