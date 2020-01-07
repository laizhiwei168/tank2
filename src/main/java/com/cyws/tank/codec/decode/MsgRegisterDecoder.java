package com.cyws.tank.codec.decode;

import com.cyws.tank.codec.vo.PackageData;
import com.cyws.tank.codec.vo.req.TerminalRegisterMsg;
import com.cyws.tank.codec.vo.req.TerminalRegisterMsg.TerminalRegInfo;
import com.cyws.tank.utils.common.ParseBytesUtils;

public class MsgRegisterDecoder {

	//终端注册
		public TerminalRegisterMsg toTerminalRegisterMsg(PackageData packageData) {
			TerminalRegisterMsg ret = new TerminalRegisterMsg(packageData);
			byte[] data = ret.getMsgBodyBytes();

			TerminalRegInfo body = new TerminalRegInfo();

			// 1. byte[0-1] 省域ID(WORD)
			// 设备安装车辆所在的省域，省域ID采用GB/T2260中规定的行政区划代码6位中前两位
			// 0保留，由平台取默认值
			body.setProvinceId(ParseBytesUtils.parseIntFromBytes(data, 0, 2));

			// 2. byte[2-3] 设备安装车辆所在的市域或县域,市县域ID采用GB/T2260中规定的行 政区划代码6位中后四位
			// 0保留，由平台取默认值
			body.setCityId(ParseBytesUtils.parseIntFromBytes(data, 2, 2));

			// 3. byte[4-8] 制造商ID(BYTE[5]) 5 个字节，终端制造商编码
			// byte[] tmp = new byte[5];
			body.setManufacturerId(ParseBytesUtils.parseStringFromBytes(data, 4, 5));

			// 4. byte[9-16] 终端型号(BYTE[8]) 八个字节， 此终端型号 由制造商自行定义 位数不足八位的，补空格。
			body.setTerminalType(ParseBytesUtils.parseStringFromBytes(data, 9, 8));

			// 5. byte[17-23] 终端ID(BYTE[7]) 七个字节， 由大写字母 和数字组成， 此终端 ID由制造 商自行定义
			body.setTerminalId(ParseBytesUtils.parseStringFromBytes(data, 17, 7));

			// 6. byte[24] 车牌颜色(BYTE) 车牌颜 色按照JT/T415-2006 中5.4.12 的规定
			body.setLicensePlateColor(ParseBytesUtils.parseIntFromBytes(data, 24, 1));

			// 7. byte[25-x] 车牌(STRING) 公安交 通管理部门颁 发的机动车号牌
			body.setLicensePlate(ParseBytesUtils.parseStringFromBytes(data, 25, data.length - 25));

			ret.setTerminalRegInfo(body);
			return ret;
		}
}
