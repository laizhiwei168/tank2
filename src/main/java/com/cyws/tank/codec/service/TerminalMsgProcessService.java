package com.cyws.tank.codec.service;


import com.cyws.tank.codec.encode.MsgEncoder;
import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.codec.vo.PackageData.MsgHeader;
import com.cyws.tank.codec.vo.req.LocationInfoUploadMsg;
import com.cyws.tank.codec.vo.req.TerminalAuthenticationMsg;
import com.cyws.tank.codec.vo.req.TerminalRegisterMsg;
import com.cyws.tank.codec.vo.resp.InitiativeInfoMsgBody;
import com.cyws.tank.codec.vo.resp.ServerCommonRespMsgBody;
import com.cyws.tank.codec.vo.resp.TerminalRegisterMsgRespBody;

public class TerminalMsgProcessService {

    private MsgEncoder msgEncoder;

    public TerminalMsgProcessService() {
        this.msgEncoder = new MsgEncoder();
    }

    public byte[] processRegisterMsg(TerminalRegisterMsg msg) throws Exception {
        TerminalRegisterMsgRespBody respMsgBody = new TerminalRegisterMsgRespBody();
        respMsgBody.setReplyCode(TerminalRegisterMsgRespBody.success);
        respMsgBody.setReplyFlowId(msg.getMsgHeader().getFlowId());
        // TODO 鉴权码暂时写死
        respMsgBody.setReplyToken("123");
        int flowId = getFlowId(msg.getMsgHeader().getFlowId());;//super.getFlowId(msg.getChannel());
        byte[] bs = this.msgEncoder.encode4TerminalRegisterResp(msg, respMsgBody, flowId);
        return bs;
    }

    public byte[] processAuthMsg(TerminalAuthenticationMsg msg) throws Exception {
        // TODO 暂时每次鉴权都成功
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody();
        respMsgBody.setReplyCode(ServerCommonRespMsgBody.success);
        respMsgBody.setReplyFlowId(msg.getMsgHeader().getFlowId());
        respMsgBody.setReplyId(msg.getMsgHeader().getMsgId());
        int flowId =getFlowId(msg.getMsgHeader().getFlowId());//super.getFlowId(msg.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(msg, respMsgBody, flowId); 
        return bs;
    }

    public byte[] processTerminalHeartBeatMsg(PackageData req) throws Exception {
        //log.debug("心跳信息:{}", JSON.toJSONString(req, true));
        final MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId =getFlowId(reqHeader.getFlowId());// super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        return bs;
    }

    public byte[] processTerminalLogoutMsg(PackageData req) throws Exception {
        final MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = getFlowId(reqHeader.getFlowId());;//super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        return bs;
    }

    public byte[] processLocationInfoUploadMsg(LocationInfoUploadMsg req) throws Exception {
        //log.debug("位置 信息:{}", JSON.toJSONString(req, true));
        final MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId =getFlowId(reqHeader.getFlowId());// super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId); 
        return bs;
    }
    
    public byte[] processParamSettingMsg(PackageData req,byte[] msgBodyBytes) throws Exception {
        //log.debug("位置 信息:{}", JSON.toJSONString(req, true));
        final MsgHeader reqHeader = req.getMsgHeader();       
        int flowId =getFlowId(reqHeader.getFlowId());// super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ParamSetting(req, msgBodyBytes, flowId); 
        return bs;
    }
        
    public byte[] processInitiativeInfoUploadMsg(ExtensionInitiativeData req) throws Exception {
        //log.debug("位置 信息:{}", JSON.toJSONString(req, true));
        /*final MsgHeader reqHeader = req.getPackageData().getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);*/
    	InitiativeInfoMsgBody respMsgBody=new InitiativeInfoMsgBody();
    	respMsgBody.setExtensionInfo(req.getVersions(), req.getManufacturers(), req.getPeripheralType(), req.getOrderType());
    	respMsgBody.setTargetInfo(0x72, 0x01, 0x77, respMsgBody.success, req.getDateTime());
    			
        int flowId =getFlowId(req.getPackageData().getMsgHeader().getFlowId());
        byte[] bs = this.msgEncoder.encode4InitiativeInfoRespMsg(req, respMsgBody, flowId); 
        return bs;
    }
    
    public byte[] processCommonMsg(PackageData req) throws Exception {
        //log.debug("位置 信息:{}", JSON.toJSONString(req, true));
        final MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId =getFlowId(reqHeader.getFlowId());// super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId); 
        return bs;
    }
    
    //获取流水号
    private int getFlowId(int currentFlowId){
    	if (currentFlowId >= 0xffff)
			currentFlowId = 0;
    	return ++currentFlowId;
    }  
}
