package com.cyws.tank.codec.decode;

import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.codec.vo.req.CommonMsg;

public class MsgCommonDecoder {
	//位置信息
	public CommonMsg toLocationInfoUploadMsg(PackageData packageData) {
		CommonMsg ret=new CommonMsg(packageData);
		final byte[] body_bt = ret.getMsgBodyBytes();
		Integer up_flowId=(body_bt[0]<< 8 & 0XFFFF)+(body_bt[1] & 0xFF);
		Integer up_msgId=(body_bt[2]<< 8 & 0XFFFF)+(body_bt[3] & 0xFF);
		Integer result=body_bt[4] & 0xFF;
		ret.setUp_flowId(up_flowId);
		ret.setUp_msgId(up_msgId);
		ret.setResult(result);
		return ret;
	}
}
