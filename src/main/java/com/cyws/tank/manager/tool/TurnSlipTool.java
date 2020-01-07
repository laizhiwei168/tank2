package com.cyws.tank.manager.tool;

import java.util.HashMap;
import java.util.Map;

import com.cyws.tank.manager.data.Re_LongitudeAndLatitude;
import com.cyws.tank.utils.common.HttpClientUtil;
import com.cyws.tank.utils.data.GaodeTurnSlipJo;
import com.cyws.tank.utils.serializer.FastJsonSerializer;

public class TurnSlipTool {

	private FastJsonSerializer fastJsonSerializer=new FastJsonSerializer();	
	
	public Re_LongitudeAndLatitude ToGaoDeTurnSlip(double longitude,double latitude){
		Re_LongitudeAndLatitude re_LongitudeAndLatitude=new Re_LongitudeAndLatitude();
		String locations=longitude+","+latitude;
		Map<String, String> params=new HashMap<String, String>();
		params.put("locations", locations);
		params.put("coordsys", "gps");		
		params.put("output", "json");
		params.put("key", "9cbd9f51be3e7d937f41d629193f368d");
		String url="https://restapi.amap.com/v3/assistant/coordinate/convert";
		try {
			String rs=HttpClientUtil.doGetForm(url, params);							
			GaodeTurnSlipJo jo=fastJsonSerializer.toObject(rs, GaodeTurnSlipJo.class);
			String loct=jo.getLocations();
			//System.out.println(loct);
			if(loct!=null && !"".equals(loct)){
				String[] strs=loct.split(",");
				if(strs.length==2){
					re_LongitudeAndLatitude.setLongitude(Double.valueOf(strs[0]));
					re_LongitudeAndLatitude.setLatitude(Double.valueOf(strs[1]));
				}
			}
		}catch(Exception e){
			
		}
		
		return re_LongitudeAndLatitude;
	}
}
