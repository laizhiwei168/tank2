package com.cyws.tank.codec.vo;

/**
 * 这是第二层协议结构
 * 
 * @author lzw
 *
 */
public class ExtensionBaseData{
	
	protected int extensionType;
	/**
	 * 校验码 1byte
	 */
	protected int checkSum;
	// 版本号  
	protected int versions;
	// 厂商编号 
	protected int manufacturers;
	// 外设类型 
	protected int peripheralType;
	// 命令类型 
	protected int orderType;
	// 用户数据
	protected byte[] msgExtensionBodyBytes;
    
	protected PackageData packageData;
	    
	public int getExtensionType() {
		return extensionType;
	}
	public void setExtensionType(int extensionType) {
		this.extensionType = extensionType;
	}
	public int getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}
	public int getVersions() {
		return versions;
	}
	public void setVersions(int versions) {
		this.versions = versions;
	}
	public int getManufacturers() {
		return manufacturers;
	}
	public void setManufacturers(int manufacturers) {
		this.manufacturers = manufacturers;
	}
	public int getPeripheralType() {
		return peripheralType;
	}
	public void setPeripheralType(int peripheralType) {
		this.peripheralType = peripheralType;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public byte[] getMsgExtensionBodyBytes() {
		return msgExtensionBodyBytes;
	}
	public void setMsgExtensionBodyBytes(byte[] msgExtensionBodyBytes) {
		this.msgExtensionBodyBytes = msgExtensionBodyBytes;
	}
	public PackageData getPackageData() {
		return packageData;
	}
	public void setPackageData(PackageData packageData) {
		this.packageData = packageData;
	}
    
}
