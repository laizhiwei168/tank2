package com.cyws.tank.codec.common;

public class MsExtensionConsts {

	/** 扩展协议版本*/
	// 冷链的
	public static final int msg_id_extension_cold_chain_versions=0x0101;
	
	// 工业烟草
	public static final int msg_id_extension_tobacco_industry_versions=0x0201;
	
	// 商业烟草
	public static final int msg_id_extension_tobacco_commercial_versions=0x0202;
	
	// 能化
	public static final int msg_id_extension_can_change_versions=0x0301;
	
	
	
	/**厂商编号*/
	// 中集
	public static final int msg_id_extension_cimc_manufacturer=0x7363;
	
	
	/**外设类型编号*/
	// 行业信息终端机
	public static final int msg_id_extension_profession_info_peripheralType=0x01;
	
	// 调度显示屏	
	public static final int msg_id_extension_dispatch_screen_peripheralType=0x02;
	
	// 车载导航显示屏
	public static final int msg_id_extension_navigation_screen_peripheralType=0x03;
	
	// 油量检测器
	public static final int msg_id_extension_oil_mass_peripheralType=0x04;
	
	// 加速度检测器
	public static final int msg_id_extension_accelerated_peripheralType=0x05;
	
	// 防盗报警器
	public static final int msg_id_extension_burglar_alarm_peripheralType=0x06;
	
	// 接口扩展器 
	public static final int msg_id_extension_interface_expander_peripheralType=0x07;
	
	// 载重检测器
	public static final int msg_id_extension_load_peripheralType=0x08;
	
	// 客流检测器
	public static final int msg_id_extension_passenger_flow_peripheralType=0x09;
	
	// 通用传感器
	public static final int msg_id_extension_common_sensor_peripheralType=0x0a;
	
	// 智能终端机
	public static final int msg_id_extension_cimc_terminal_peripheralType=0x65;
	
	
	/**命令类型*/
	// 专用命令类型-开利
    public static final int msg_id_extension_kaili_dedicated_orderType=0x41;
    
    // 专用命令类型-冷王
    public static final int msg_id_extension_lenwang_dedicated_orderType=0x42;
    
	// 专用命令类型-中集
	public static final int msg_id_extension_cimc_dedicated_orderType=0x61;
	
}
