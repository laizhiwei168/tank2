package com.cyws.tank.TANK2;

import java.util.List;
import java.util.Map;

import com.cyws.tank.manager.common.DBBean;
import com.cyws.tank.manager.db.DBHandleHelper;

public class CreateMysqlTest {

	public static void main(String[] args) {
		 DBHandleHelper DbHelper=new DBHandleHelper();
		
		
		 //我么要执行创建表的DDl语句
	     /* String creatsql = "CREATE TABLE pepole2("
	              + "username varchar(10) not null,"
	              + "userpwd varchar(10) not null,"
	              + "age int(4) not null,"
	              + "birth datetime," 
				  +"primary key (username, userpwd)"
	              + ")charset=utf8;";*/
	      
	      
	      String creat_ta_equipment_data_sql = "CREATE TABLE  ta_equipment_data_19_01("
	              + "equipment_id varchar(32) not null,"
	              + "da_up datetime not null,"
	              
	             // + "longitude double(9,6) ,"
	             // + "latitude double(9,6) ,"
	             // + "r_latitude decimal(9,6) ,"
	             // + "r_longitude decimal(9,6) ,"
	             // + "place varchar(150) ,"
	             // + "direction int(6) ,"
	             // + "speed double(6,2),"
	             // + "heigth int(8) ,"
	             // + "mileage decimal(10,3) ,"
	              
	              + "levels double(9,2) ,"
	              + "temperature double(5,1),"
	              + "pressure double(8,2) ,"
	              + "vacuum int(5) ,"
	              + "door tinyint(1) ,"
	              + "gas decimal(8,1) ,"
	              + "dip_x int(3) ,"
	              + "fuel double(8,2) ,"
	              + "is_location decimal(1) ,"
	              + "accelerator_x decimal(8,3) ,"
	              + "dip_y int(3) ,"
	              + "accelerator_y decimal(8,3) ,"
	              + "current_volume double(12,2) ,"
	              + "electric int(3) ,"
	              + "temperature1 double(5,1) ,"
	              + "temperature2 double(5,1) ,"
	              + "temperature3 double(5,1) ,"
	              + "temperature4 double(5,1) ,"
	              + "door1 tinyint(1) ,"
	              + "door2 tinyint(1) ,"
	              + "door3 tinyint(1) ,"
	              + "door4 tinyint(1) ,"
	              + "voltage1 decimal(5,2) ,"
	              + "voltage2 decimal(5,2) ,"
	              + "voltage3 decimal(5,2) ,"
	              + "voltage4 decimal(5,2) ,"
	              + "rssi1 int(4) ,"
	              + "rssi2 int(4) ,"
	              + "rssi3 int(4) ,"
	              + "rssi4 int(4) ,"
	              + "state varchar(20) ,"
	              + "pressure1 double(9,2) ,"
	              + "pressure2 double(9,2) ,"
	              + "pressure3 double(9,2) ,"
	              + "pressure4 double(9,2) ,"
	              + "stand_add_up decimal(12,4) ,"
	              + "stand_add_up1 decimal(12,4) ,"
	              + "levels1 double(9,2) ,"
	              + "current_volume1 double(12,2) ,"
	              + "work_add_up decimal(12,4) ,"
	              + "work_add_up1 decimal(12,4) ,"
	              + "stand_flow double(6,2) ,"
	              + "stand_flow1 double(6,2) ,"
	              + "work_flow double(6,2) ,"
	              + "work_flow1 double(6,2) ,"
	              + "gas1 double(7,2) ,"
	              + "a1 double(6,1) ,"
	              + "a2 double(6,1) ,"
	              + "a3 double(6,1) ,"
	              + "a4 double(6,1) ,"
	              + "a5 double(6,1) ,"
	              + "a6 double(6,1) ,"
	              + "b1 double(5,1) ,"
	              + "b2 double(5,1) ,"
	              + "b3 double(5,1) ,"
	              + "b4 double(5,1) ,"
	              + "b5 double(5,1) ,"
	              + "b6 double(5,1) ,"
	              + "b7 double(5,1) ,"
	              + "c1 double(5,1) ,"
	              + "c2 double(5,1) ,"
	              + "c3 double(5,1) ,"
	              + "c4 double(5,1) ,"
	              + "c5 double(5,1) ,"
	              + "f91 double(5,1) ,"
	              + "f92 double(5,1) ,"
	              + "f93 double(5,1) ,"
	              + "use_volume double(12,2) ,"
	              + "add_volume double(12,2) ,"
	              + "storeunitnum decimal(10,3) ,"
	              
				  +"primary key (equipment_id, da_up)"
	              + ")charset=utf8;";
	      
	      String creat_ta_equipment_location_sql = "CREATE TABLE  ta_equipment_location_19_01("
	              + "EQUIPMENT_ID varchar(32) not null,"
	              + "DA_UP datetime not null," 
	              + "LOCATION_TYPE int(2) ,"
	              + "IS_LOCATION tinyint(1) ,"
	              + "LONGITUDE decimal(9,6) ,"
	              + "LATITUDE decimal(9,6) ,"
	              + "PLACE varchar(140)," 
	              + "SPEED double(6,2),"
	              + "HEIGTH int(8) ,"
	              + "DIRECTION int(6) ,"              
	              + "R_LONGITUDE decimal(9,6) ,"
	              + "R_LATITUDE decimal(9,6) ,"
				  +"primary key (EQUIPMENT_ID, DA_UP)"
	              + ")charset=utf8;";
	      
	      
	      int Result=DbHelper.ExecuteSql(DBBean.gndata,creat_ta_equipment_location_sql);
		  
	      
		/*String SQLString ="SELECT * FROM user"; 
		List<Map<String, Object>> ls_taData= DbHelper.Query(DBBean.gndata,SQLString);
		for(Map<String, Object> map:ls_taData){
			for(String key:map.keySet()){
				System.out.println(key+" "+map.get(key));				
			}
		}*/
	}
}
