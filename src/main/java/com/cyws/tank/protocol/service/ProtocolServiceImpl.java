package com.cyws.tank.protocol.service;

import org.xsocket.connection.INonBlockingConnection;

import com.cyws.tank.bean.common.BlacklistMapBean;
import com.cyws.tank.codec.common.TPMSConsts;
import com.cyws.tank.codec.decode.MsgCommonDecoder;
import com.cyws.tank.codec.decode.MsgDecoder;
import com.cyws.tank.codec.decode.MsgExtensionBaseDecoder;
import com.cyws.tank.codec.decode.MsgExtensionInitiativeDecoder;
import com.cyws.tank.codec.decode.MsgLocationDecoder;
import com.cyws.tank.codec.service.TerminalMsgProcessService;
import com.cyws.tank.codec.vo.ExtensionBaseData;
import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.codec.vo.PackageData.MsgHeader;
import com.cyws.tank.codec.vo.req.CommonMsg;
import com.cyws.tank.codec.vo.req.LocationInfoUploadMsg;
import com.cyws.tank.manager.SubscribeFactory.SubscribeFactory;
import com.cyws.tank.socket.user.DeviceUserController;
import com.cyws.tank.thread.pool.SubscribePool;
import com.cyws.tank.utils.common.JT808ProtocolUtils;
import com.cyws.tank.utils.common.Utilty;
import com.cyws.tank.utils.log.LogManager;

public class ProtocolServiceImpl {
	private TerminalMsgProcessService msgProcessService=new TerminalMsgProcessService();
	//private final MsgDecoder decoder=new MsgDecoder();
	public Object dealWithData(final byte[] bt,INonBlockingConnection nbc) {
		byte[] re_bs=null;
		if(0x7e!=bt[0] || 0x7e!=bt[bt.length-1]){
			/**处理设备上报的码流*/
			LogManager.getInstance().PrintGainDeviceLog("deivce",bt);//打印log
			return null;
		}
		// 转换数据
		JT808ProtocolUtils jt808ProtocolUtils=new JT808ProtocolUtils();
		byte[] bs=null;
		try {
			bs=jt808ProtocolUtils.doEscape4Receive(bt,0,bt.length);
			//System.out.println("basedata："+new Utilty().toStringByte(bs, 0, bs.length-1));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// 解析消息头
		MsgDecoder msgDecoder=new MsgDecoder();
		PackageData packageData=msgDecoder.bytes2PackageData(bs);
		
		final MsgHeader header = packageData.getMsgHeader();
		/**处理设备上报的码流*/
		String TerminalPhone=packageData.getMsgHeader().getTerminalPhone();
		LogManager.getInstance().PrintGainDeviceLog(TerminalPhone,bt);//打印log
		String ip =nbc.getRemoteAddress().getHostAddress();
		System.out.println(ip+"====================="+TerminalPhone);		
		// 1. 终端通用应答
		if (TPMSConsts.msg_id_terminal_common_resp == header.getMsgId()) {
			MsgCommonDecoder msgCommonDecoder=new MsgCommonDecoder();
			CommonMsg commonMsg= msgCommonDecoder.toLocationInfoUploadMsg(packageData);
			HandldSubscribeData(TPMSConsts.msg_id_terminal_common_resp,commonMsg);

		}
		// 2. 终端心跳-消息体为空 ==> 平台通用应答
		else if (TPMSConsts.msg_id_terminal_heart_beat == header.getMsgId()) {
			try {
				re_bs=this.msgProcessService.processTerminalHeartBeatMsg(packageData);						
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 3. 位置信息汇报 ==> 平台通用应答
		else if (TPMSConsts.msg_id_terminal_location_info_upload == header.getMsgId()) {
			try {
				MsgLocationDecoder msgLocationDecoder=new MsgLocationDecoder();
				LocationInfoUploadMsg locationInfoUploadMsg = msgLocationDecoder.toLocationInfoUploadMsg(packageData);
				//System.out.println(locationInfoUploadMsg);
				re_bs=this.msgProcessService.processLocationInfoUploadMsg(locationInfoUploadMsg);
				HandldSubscribeData(TPMSConsts.msg_id_terminal_location_info_upload,locationInfoUploadMsg);
				LogManager.getInstance().PrintSentDeviceLog(TerminalPhone,re_bs);//打印log
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 数据上行透传 ==> 平台通用应答
		else if(TPMSConsts.msg_id_terminal_transmission_info_upload == header.getMsgId()){
			try {
				MsgExtensionBaseDecoder msgExtensionBaseDecoder=new MsgExtensionBaseDecoder();
				ExtensionBaseData extensionBaseData =msgExtensionBaseDecoder.bytes2ExtensionBaseData(packageData);
				byte[] re_common_bs=this.msgProcessService.processCommonMsg(packageData);
				nbc.write(re_common_bs);//先回复通用应答帧
				LogManager.getInstance().PrintSentDeviceLog(TerminalPhone,re_common_bs);//打印log
				if(extensionBaseData.getMsgExtensionBodyBytes()[0]==0x75){
					MsgExtensionInitiativeDecoder msgExtensionInitiativeDecoder=new MsgExtensionInitiativeDecoder();				
					ExtensionInitiativeData extensionInitiativeData=msgExtensionInitiativeDecoder.bytes2ExtensionInitiativeData(extensionBaseData);	
					//System.out.println(extensionInitiativeData);
					if(extensionInitiativeData.getGoalChar()!=0x02){
						HandldSubscribeData(TPMSConsts.msg_id_terminal_transmission_info_upload,extensionInitiativeData);
						re_bs=this.msgProcessService.processInitiativeInfoUploadMsg(extensionInitiativeData);
						LogManager.getInstance().PrintSentDeviceLog(TerminalPhone,re_bs);//打印log
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		/**记录设备基本信息*/		
		if(!BlacklistMapBean.getExist(ip))//这个使用于集团服务器的
		DeviceUserController.getInstance().handleDeviceController(packageData, nbc);  
		
		return re_bs;
	}
	
	
	
	/**处理业务数据并操作数据库*/
	public void HandldSubscribeData(int MsgId,Object objectNode){
        SubscribePool.init().execute(new  Runnable() {
 			public void run() {
 				SubscribeFactory subscribeFactory=new SubscribeFactory();
 				subscribeFactory.pushDataToPlatform(MsgId,objectNode);
 			}
 		});
	}
}
