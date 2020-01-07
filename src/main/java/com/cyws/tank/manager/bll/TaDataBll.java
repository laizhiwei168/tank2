package com.cyws.tank.manager.bll;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.dal.TaDataDal;
import com.cyws.tank.manager.po.TaDataPo;
import com.cyws.tank.utils.common.DateUtils;

public class TaDataBll {

	private TaDataDal taDataDal=new TaDataDal();
	
	public List<Map<String, Object>> selectTaTankById(String EQUIPMENT_ID){
		List<Map<String, Object>> ls_taTank=taDataDal.selectTaTankById(EQUIPMENT_ID);
		return ls_taTank;
	}
	
	public List<Map<String, Object>> selectdbAdress(String pk_address){
		List<Map<String, Object>> ls_taTank=taDataDal.selectdbAdress(pk_address);
		return ls_taTank;
	}
	
	// 查询最新数据信息表	
	public List<Map<String, Object>> selectTaLocationByID(String EQUIPMENT_ID){
		List<Map<String, Object>> ls_taTank=taDataDal.selectTaLocationByID(EQUIPMENT_ID);
		return ls_taTank;
	}
	
	//处理数据信息插入
	public int addInfoTaDataNew(TaDataPo po){
		List<Map<String, Object>> ls_taData=taDataDal.selectTaDataByID(po.getEQUIPMENT_ID());
		if(ls_taData.isEmpty()){
			//插入最新表数据
			taDataDal.AddTaDataNew(po);
		}else{
			 Map<String, Object> taData= ls_taData.get(0);
			
			// 需要时间对比，得出数据是否为历史数据
			/*boolean is_history=false;
			if(taData.get("DA_UP")!=null){
				if(DateUtils.compare_dateTime(po.getDA_UP(), taData.get("DA_UP").toString())<0){
					is_history=true;
				}
			}*/
			 
			// 处理液位是否为有效值  如果是当前值超过前一条值的总液位的20%为无效值,无效值不对数据录入
			//if(!is_history){
				Double old_LEVELS=0.0;
				Double LEVELS=po.getLEVELS();
				Double LEVEL_MAX = po.getLEVEL_MAX();
				long minute=1;
				try {
					long value=DateUtils.getDatePoor(taData.get("DA_UP").toString(), po.getDA_UP());
					if(value!=0){
						minute=Math.abs(value);
					}				
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(taData.get("LEVELS")!=null && LEVELS!=null && LEVEL_MAX!=null){
					old_LEVELS=Double.valueOf(taData.get("LEVELS").toString()); 						
					double invalid_LEVEL=LEVEL_MAX*0.2*minute;			
					double differenceValue= old_LEVELS-LEVELS;
					 if(differenceValue>0 && differenceValue>invalid_LEVEL){
						 System.out.println("====液位为无效值====");
						 return 2;
					 }
				 }
			//}
			 
			 
			 // 处理数的统计 填写总值差
			 Double volume=null;
			 Double old_use_volume=0.0;
			 Double old_add_volume=0.0;		
					
			 if(taData.get("use_volume")!=null){
				 old_use_volume=Double.valueOf(taData.get("use_volume").toString()); 
			 }
			 if(taData.get("add_volume")!=null){
				 old_add_volume=Double.valueOf(taData.get("add_volume").toString()); 
			 }			  
			if(taData.get("CURRENT_VOLUME")!=null){
				Double old_volume=Double.valueOf(taData.get("CURRENT_VOLUME").toString());
				volume=po.getCURRENT_VOLUME()-old_volume;
				if(volume>0){
					po.setAdd_volume(volume+old_add_volume);
				}else if(volume<0){
					po.setUse_volume(volume+old_use_volume);
				}
			}
			
			
			// 更改最新表信息
			taDataDal.updateTaDataNew(po);
			
			 // 补充位置的历史数据 当前值差	
			 if(volume!=null){
				if(volume>0){
					po.setAdd_volume(volume);
				}else if(volume<0){
					po.setUse_volume(volume);
				} 
			 }
			 			
		}
		// 插入历史数据
		taDataDal.AddTaData(po);
		return 1;
	}
}
