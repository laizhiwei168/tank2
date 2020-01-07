package com.cyws.tank.utils.common;

import java.util.HashMap;
import java.util.Map;

import com.cyws.tank.utils.po.baidulocation;
import com.cyws.tank.utils.serializer.FastJsonSerializer;


public class BaiduLocation {

	private static FastJsonSerializer  fastJsonSerializer=new FastJsonSerializer();
	
	public static String getBaiduLocation(Double longitude,Double latitude){
         String location="";
		//处理百度链接
		Map<String, String> params=new HashMap<String, String>();
		String latlng=latitude+","+longitude;
		params.put("location", latlng);
		params.put("callback", "renderReverse");
		params.put("coordtype", "wgs84ll");
		
		params.put("output", "json");
		params.put("pois", "1");
		params.put("ak", "iFUqE2rlTdcyoTDmpEb1P74divL3eEdW");
		String url="http://api.map.baidu.com/geocoder/v2/";
		try {
			String rs=HttpUtils.OGIPost(url, params);
			//System.out.println("rs="+rs);
			if(rs.length()>40){
			rs=rs.substring(29,rs.length()-1);
			baidulocation gle=fastJsonSerializer.toObject(rs, baidulocation.class);
			
			StringBuilder address=new StringBuilder();
			String province=gle.getResult().getAddressComponent().getProvince();
			String city=gle.getResult().getAddressComponent().getCity();
			String district=gle.getResult().getAddressComponent().getDistrict();
			if(gle.getResult().getPois().size()>0){
				String addr=gle.getResult().getPois().get(0).getAddr();
				int index=0;				
				if(addr.length()<10){
					if(gle.getResult().getPois().size()>1){
						index=1;
					}
				}
				String newAddr=gle.getResult().getPois().get(index).getAddr();
				String name=gle.getResult().getPois().get(index).getName();
				String direction=gle.getResult().getPois().get(index).getDirection();
				String distance=gle.getResult().getPois().get(index).getDistance();
				if(newAddr.contains(province)){
					address.append(newAddr+name+direction+distance+"米");
				}else{
					if(newAddr.contains(city)){
						address.append(province+newAddr+name+direction+distance+"米");
					}else{
						if(newAddr.contains(district)){
							address.append(province+city+newAddr+name+direction+distance+"米");
						}else{
							address.append(province+city+district+newAddr+name+direction+distance+"米");
						}
					}							
				}					
			}else{
				address.append(gle.getResult().getFormatted_address());
			}
			location=address.toString();
			}
			//System.out.println(gle.getResult().getFormatted_address());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return location;	
	}
}
