package com.cyws.tank.codec.decode;

import com.cyws.tank.codec.vo.ExtensionBaseData;
import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.utils.common.BitOperator;
import com.cyws.tank.utils.common.JT808ProtocolUtils;
import com.cyws.tank.utils.common.ParseBytesUtils;
import com.cyws.tank.utils.common.Utilty;
public class MsgExtensionBaseDecoder {
	private BitOperator bitOperator;

	public MsgExtensionBaseDecoder() {
		this.bitOperator = new BitOperator();
	}
	public ExtensionBaseData bytes2ExtensionBaseData(PackageData packageData){
		ExtensionBaseData ret=new ExtensionBaseData();
		ret.setPackageData(packageData);
		
		// 我们公司内嵌套需要转义		
		JT808ProtocolUtils jt808ProtocolUtils=new JT808ProtocolUtils();
		byte[] bs=null;
		try {
			byte[] data=packageData.getMsgBodyBytes();
			bs=jt808ProtocolUtils.doEscape4Receive(data,0,data.length);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//System.out.println("==="+Utilty.getInstance().parseByte2HexStr(data));
		//校验码 
		int checkSumInPkg = bs[2]&0xff;
		int calculatedCheckSum = this.bitOperator.RX_CheckSum(bs, 5, bs.length - 1);
		byte[] b=this.bitOperator.integerTo2Bytes(calculatedCheckSum);
		
		if (checkSumInPkg != (b[1]&0xff)) {
			System.out.println("检验码不一致,pkg:{"+ checkSumInPkg+"},calculated:{"+ (b[1]&0xff)+"}");
		}
		
		ret.setExtensionType(bs[0]);
		ret.setCheckSum(checkSumInPkg);
		ret.setVersions(ParseBytesUtils.parseIntFromBytes(bs, 3, 2));
		ret.setManufacturers(ParseBytesUtils.parseIntFromBytes(bs, 5, 2));
		ret.setPeripheralType(bs[7]&0xff);
		ret.setOrderType(bs[8]&0xff);
		byte[] tmp = new byte[bs.length-10];
		System.arraycopy(bs, 9, tmp, 0, tmp.length);
		ret.setMsgExtensionBodyBytes(tmp);
		//System.out.println("第三层："+Utilty.getInstance().parseByte2HexStr(tmp));
		return ret;
	}
}
