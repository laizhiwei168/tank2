package com.cyws.tank.codec.encode;

import java.util.Arrays;

import com.cyws.tank.codec.common.TPMSConsts;
import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.codec.vo.req.TerminalRegisterMsg;
import com.cyws.tank.codec.vo.resp.InitiativeInfoMsgBody;
import com.cyws.tank.codec.vo.resp.ServerCommonRespMsgBody;
import com.cyws.tank.codec.vo.resp.TerminalRegisterMsgRespBody;
import com.cyws.tank.utils.common.BitOperator;
import com.cyws.tank.utils.common.JT808ProtocolUtils;
import com.cyws.tank.utils.common.Utilty;


public class MsgEncoder {
	private BitOperator bitOperator;
	private JT808ProtocolUtils jt808ProtocolUtils;
	private Utilty utilty;

	public MsgEncoder() {
		this.bitOperator = new BitOperator();
		this.jt808ProtocolUtils = new JT808ProtocolUtils();
		this.utilty=new Utilty();
	}

	public byte[] encode4TerminalRegisterResp(TerminalRegisterMsg req, TerminalRegisterMsgRespBody respMsgBody,
			int flowId) throws Exception {
		// 消息体字节数组
		byte[] msgBody = null;
		// 鉴权码(STRING) 只有在成功后才有该字段
		if (respMsgBody.getReplyCode() == TerminalRegisterMsgRespBody.success) {
			msgBody = this.bitOperator.concatAll(Arrays.asList(//
					bitOperator.integerTo2Bytes(respMsgBody.getReplyFlowId()), // 流水号(2)
					new byte[] { respMsgBody.getReplyCode() }, // 结果
					respMsgBody.getReplyToken().getBytes(TPMSConsts.string_charset)// 鉴权码(STRING)
			));
		} else {
			msgBody = this.bitOperator.concatAll(Arrays.asList(//
					bitOperator.integerTo2Bytes(respMsgBody.getReplyFlowId()), // 流水号(2)
					new byte[] { respMsgBody.getReplyCode() }// 错误代码
			));
		}

		// 消息头
		int msgBodyProps = this.jt808ProtocolUtils.generateMsgBodyProps(msgBody.length, 0b000, false, 0);
		byte[] msgHeader = this.jt808ProtocolUtils.generateMsgHeader(req.getMsgHeader().getTerminalPhone(),
				TPMSConsts.cmd_terminal_register_resp, msgBody, msgBodyProps, flowId);
		byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, msgBody);

		// 校验码
		//int checkSum = this.bitOperator.getCheckSum4JT808(headerAndBody, 0, headerAndBody.length - 1);
		byte checkSum = this.bitOperator.getXor(headerAndBody);
		// 连接并且转义
		return this.doEncode(headerAndBody, checkSum);
	}

	// public byte[] encode4ServerCommonRespMsg(TerminalAuthenticationMsg req,
	// ServerCommonRespMsgBody respMsgBody, int flowId) throws Exception {
	public byte[] encode4ServerCommonRespMsg(PackageData req, ServerCommonRespMsgBody respMsgBody, int flowId)
			throws Exception {
		byte[] msgBody = this.bitOperator.concatAll(Arrays.asList(//
				bitOperator.integerTo2Bytes(respMsgBody.getReplyFlowId()), // 应答流水号
				bitOperator.integerTo2Bytes(respMsgBody.getReplyId()), // 应答ID,对应的终端消息的ID
				new byte[] { respMsgBody.getReplyCode() }// 结果
		));

		// 消息头
		int msgBodyProps = this.jt808ProtocolUtils.generateMsgBodyProps(msgBody.length, 0b000, false, 0);
		byte[] msgHeader = this.jt808ProtocolUtils.generateMsgHeader(req.getMsgHeader().getTerminalPhone(),
				TPMSConsts.cmd_common_resp, msgBody, msgBodyProps, flowId);
		byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, msgBody);
		// 校验码
		//int checkSum = this.bitOperator.getCheckSum4JT808(headerAndBody, 0, headerAndBody.length - 1);
		byte checkSum = this.bitOperator.getXor(headerAndBody);
		// 连接并且转义
		return this.doEncode(headerAndBody, checkSum);
	}
	
	public byte[] encode4InitiativeInfoRespMsg(ExtensionInitiativeData exreq, InitiativeInfoMsgBody respMsgBody, int flowId)
			throws Exception {
		PackageData req=exreq.getPackageData();
		
		byte[] msgTargetBody = this.bitOperator.concatAll(Arrays.asList(
				bitOperator.integerTo1Bytes(respMsgBody.getOrderChar()),
				bitOperator.integerTo1Bytes(respMsgBody.getGoalChar()),
				bitOperator.integerTo1Bytes(respMsgBody.getFlowId()),
				new byte[] {0x07, respMsgBody.getReplyCode() },
				utilty.hexStrToByteArray(respMsgBody.getContent())
				));
		
		byte[] msgExtensionBody =this.bitOperator.concatAll(Arrays.asList(
				bitOperator.integerTo2Bytes(respMsgBody.getVersions()),
				bitOperator.integerTo2Bytes(respMsgBody.getManufacturers()),
				bitOperator.integerTo1Bytes(respMsgBody.getPeripheralType()),
				bitOperator.integerTo1Bytes(respMsgBody.getOrderType()),
				msgTargetBody
				));
		//int ExtensionCheckSum = this.bitOperator.getCheckSum4JT808(msgExtensionBody, 2, msgExtensionBody.length - 1);
		// 累加校验
		int calculatedCheckSum = this.bitOperator.RX_CheckSum(msgExtensionBody, 2, msgExtensionBody.length);		
		byte[] b=this.bitOperator.integerTo2Bytes(calculatedCheckSum);
				
		byte[] msgBody = this.bitOperator.concatAll(Arrays.asList(
				bitOperator.integerTo1Bytes(exreq.getExtensionType()),
				new byte[] {TPMSConsts.pkg_delimiter ,b[1]}, // 0x7e
				//bitOperator.integerTo1Bytes(ExtensionCheckSum),
				msgExtensionBody,
				new byte[] { TPMSConsts.pkg_delimiter } // 0x7e
		));
		
		// 公司内嵌套协议转义
		byte[] zy_msgBody = jt808ProtocolUtils.doEscape4Send(msgBody, 2, msgBody.length - 1);
		// 消息头
		int msgBodyProps = this.jt808ProtocolUtils.generateMsgBodyProps(zy_msgBody.length, 0b000, false, 0);
		byte[] msgHeader = this.jt808ProtocolUtils.generateMsgHeader(req.getMsgHeader().getTerminalPhone(),
				TPMSConsts.cmd_terminal_transmission_info_upload, zy_msgBody, msgBodyProps, flowId);
		byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, zy_msgBody);
		// 校验码
		//int checkSum = this.bitOperator.getCheckSum4JT808(headerAndBody, 0, headerAndBody.length - 1);
		byte checkSum = this.bitOperator.getXor(headerAndBody);
		// 连接并且转义
		return this.doEncode(headerAndBody, checkSum);
	}

	public byte[] encode4ParamSetting(PackageData req,byte[] msgBodyBytes, int flowId) throws Exception {
		// 消息头
		int msgBodyProps = this.jt808ProtocolUtils.generateMsgBodyProps(msgBodyBytes.length, 0b000, false, 0);
		byte[] msgHeader = this.jt808ProtocolUtils.generateMsgHeader(req.getMsgHeader().getTerminalPhone(),
				TPMSConsts.cmd_terminal_param_settings, msgBodyBytes, msgBodyProps, flowId);
		// 连接消息头和消息体
		byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, msgBodyBytes);
		// 校验码
		byte checkSum = this.bitOperator.getXor(headerAndBody);
		// 连接并且转义
		return this.doEncode(headerAndBody, checkSum);
	}

	private  byte[] doEncode(byte[] headerAndBody, byte checkSum) throws Exception {
		byte[] noEscapedBytes = this.bitOperator.concatAll(Arrays.asList(//
				new byte[] { TPMSConsts.pkg_delimiter }, // 0x7e
				headerAndBody, // 消息头+ 消息体
				new byte[]{checkSum}, // 校验码
				new byte[] { TPMSConsts.pkg_delimiter }// 0x7e
		));
		// 转义
		return jt808ProtocolUtils.doEscape4Send(noEscapedBytes, 1, noEscapedBytes.length - 2);
	}
	
	public static void main(String[] args) throws Exception {
		// 消息头
		MsgEncoder msgEncoder=new MsgEncoder();
		BitOperator bitOperator=new BitOperator();
		Utilty utilty=new Utilty();
		
		byte[] msgBodyBytes=new byte[]{(byte) 0x81 ,0x03 ,0x00 ,0x1d ,0x01 ,0x11 ,0x16 ,0x30 ,0x00 ,0x52 ,0x01 ,0x08 ,0x02 ,0x00 ,0x00 ,0x00 ,0x13 ,0x0e ,0x31 ,0x32 ,0x31 ,0x2e ,0x33 ,0x35 ,0x2e ,0x32 ,0x34 ,0x32 ,0x2e ,0x32 ,0x30 ,0x34 ,0x00 ,0x00 ,0x00 ,0x18 ,0x04 ,0x00 ,0x00 ,0x1b ,(byte) 0xbc};
		
		JT808ProtocolUtils jt808ProtocolUtils=new JT808ProtocolUtils();
		int msgBodyProps = jt808ProtocolUtils.generateMsgBodyProps(msgBodyBytes.length, 0b000, false, 0);
		byte[] msgHeader = jt808ProtocolUtils.generateMsgHeader("11116700003",
				TPMSConsts.msg_id_terminal_common_resp, msgBodyBytes, msgBodyProps, 3);
		// 连接消息头和消息体
		byte[] headerAndBody = bitOperator.concatAll(msgHeader, msgBodyBytes);
		// 校验码
		byte checkSum = bitOperator.getXor(headerAndBody);
		// 连接并且转义
		byte[] vt=msgEncoder.doEncode(headerAndBody, checkSum);
		
		System.out.println("basedata："+utilty.parseByte2HexStr(vt));
		/*MsgEncoder msgEncoder=new MsgEncoder();
		BitOperator bitOperator=new BitOperator();
		Utilty utilty=new Utilty();
		byte[] msgBodyBytes=new byte[]{(byte) 0x81 ,0x03 ,0x00 ,0x1d ,0x01 ,0x11 ,0x16 ,0x30 ,0x00 ,0x52 ,0x01 ,0x08 ,0x02 ,0x00 ,0x00 ,0x00 ,0x13 ,0x0e ,0x31 ,0x32 ,0x31 ,0x2e ,0x33 ,0x35 ,0x2e ,0x32 ,0x34 ,0x32 ,0x2e ,0x32 ,0x30 ,0x34 ,0x00 ,0x00 ,0x00 ,0x18 ,0x04 ,0x00 ,0x00 ,0x1b ,(byte) 0xbc};
		int checkSum = bitOperator.getCheckSum4JT808(msgBodyBytes, 0, msgBodyBytes.length - 1);
		System.out.println(checkSum+"   checkSum:"+utilty.toStringByte(bitOperator.integerTo1Bytes(checkSum), 0, 0));
		
		System.out.println(bitOperator.getXor(msgBodyBytes));*/
	}
}
