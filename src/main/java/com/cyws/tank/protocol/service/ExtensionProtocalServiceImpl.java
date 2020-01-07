package com.cyws.tank.protocol.service;

import com.cyws.tank.codec.common.MsExtensionConsts;
import com.cyws.tank.codec.decode.MsgExtensionBaseDecoder;
import com.cyws.tank.codec.decode.MsgExtensionInitiativeDecoder;
import com.cyws.tank.codec.vo.ExtensionBaseData;
import com.cyws.tank.codec.vo.ExtensionInitiativeData;


public class ExtensionProtocalServiceImpl {
	
	public Object dealWithExtensionData(byte[] data){
		
		/*MsgExtensionBaseDecoder msgExtensionBaseDecoder=new MsgExtensionBaseDecoder();
		ExtensionBaseData extensionBaseData =msgExtensionBaseDecoder.bytes2ExtensionBaseData(data);
		
		if(extensionBaseData.getVersions()==MsExtensionConsts.msg_id_extension_can_change_versions){
			if(extensionBaseData.getOrderType()==MsExtensionConsts.msg_id_extension_cimc_dedicated_orderType){
				MsgExtensionInitiativeDecoder msgExtensionInitiativeDecoder=new MsgExtensionInitiativeDecoder();
				ExtensionInitiativeData extensionInitiativeData=msgExtensionInitiativeDecoder.bytes2ExtensionInitiativeData(extensionBaseData.getMsgExtensionBodyBytes());				
			}
		}		*/		
		return null;
	}
}
