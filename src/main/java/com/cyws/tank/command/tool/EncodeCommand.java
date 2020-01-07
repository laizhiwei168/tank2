package com.cyws.tank.command.tool;

import com.cyws.tank.command.common.ParameterConfigurationBean;
import com.cyws.tank.utils.common.Utilty;


public class EncodeCommand {
	private  Utilty util=new  Utilty();
	public String HandleEncodeCommand(ParameterConfigurationBean contentBean){
		
		String IP1=contentBean.getIP1(); // 0x0013 主IP
		String portNumber1=contentBean.getPortNumber1(); // 0x0018 主端口 
		String remoteUpgradeIP=contentBean.getRemoteUpgradeIP(); //0x001A 远程升级IP 
		String remoteUpgradePortNumber=contentBean.getRemoteUpgradePortNumber(); // 0x001B 远程升级端口 	
		String MaxPressure=contentBean.getMaxPressure(); // 0x0023 最大压力值KPa 	DWORD
		String MaxLiquidLeve=contentBean.getMaxLiquidLeve(); // 0x0024 最大液位值mmH2O DWORD MaxLiquidLeve fullLoad  TankType  sleepTime
		String  fullLoad=contentBean.getFullLoad(); // 0x0025 满载填充率（90-99）  DWORD
		String TankType=contentBean.getTankType(); // 0x0026 储罐类型 0立罐/1卧罐      BYTE
		String sleepTime=contentBean.getSleepTime(); // 0x0027 休眠时间min      DWORD
		String PressureRange=contentBean.getPressureRange(); // 0x002A 压力量程KPa       DWORD
		String LiquidLeveRange=contentBean.getLiquidLeveRange(); // 0x002B 液位量程KPa       DWORD
		String referenceVoltage=contentBean.getReferenceVoltage(); // 0x0031  温度参考电压mv*10     DWORD
		String referenceG=contentBean.getReferenceG(); // 0x0032  温度放大器G值*100       DWORD
		String standardVoltage=contentBean.getStandardVoltage();//0x0033  温度电源基准电压mv*10  DWORD
		String temperatureA=contentBean.getTemperatureA();// 0x0034  温度公式A值*10000       DWORD
		String temperatureB=contentBean.getTemperatureB();// 0x0035  温度公式B值       DWORD
		String tankDiameter=contentBean.getTankDiameter();// 0x0036  储罐直径（mm）  DWORD
		String tankLength=contentBean.getTankLength(); // 0x0037 储罐长度（mm）  DWORD
		String tankMedium=contentBean.getTankMedium(); // 0x0038 储存介质  DWORD
		String remoteUpgradeSleepTime=contentBean.getRemoteUpgradeSleepTime(); // 0x0039远程升级休眠时间
	    String restart=contentBean.getRestart();// 0x0000003A  uint8  0-256  分钟 X分钟后重启设备
	    String workingTime=contentBean.getWorkingTime(); //0x0000003B uint8 （0-24） ,（0-24） ;  开始工作时间 , 结束工作时间 
	    
	    
	    //========黄工======丰乐====
	     String  blockSet=contentBean.getBlockSet();
		 String  timeStart=contentBean.getTimeStart();
		 String  timeEnd=contentBean.getTimeEnd();
		 String  minutes=contentBean.getMinutes();
	    
		int commandNumber=0;
		StringBuffer strbf=new StringBuffer();
		
		if("".equals(IP1)){
			 strbf.append("00000013");
			 ++commandNumber;
		 }else{
			 if(IP1!=null){
				 String str_ip1= util.convertStringToHex(IP1.trim());
				 String length=getCommandLength(str_ip1.length());
				 strbf.append("00000013"+length+str_ip1);
				 ++commandNumber;
			 }
		 }
		
		if("".equals(portNumber1)){
			 strbf.append("00000018");
			 ++commandNumber;
		 }else{
			 if(portNumber1!=null){
				 try {
					 Double i=Double.valueOf(portNumber1.trim());
					 byte[] byt_portNumber1= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000001804"+util.toStringByte(byt_portNumber1,0,byt_portNumber1.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
			 }
		 }
		
		if("".equals(remoteUpgradeIP)){
			 strbf.append("0000001A");
			 ++commandNumber;
		 }else{
			 if(remoteUpgradeIP!=null){
				 String str_remoteUpgradeIP= util.convertStringToHex(remoteUpgradeIP.trim());
				 String length=getCommandLength(str_remoteUpgradeIP.length());
				 strbf.append("0000001A"+length+str_remoteUpgradeIP);
				 ++commandNumber;
			 }
		 }
		
		if("".equals(remoteUpgradePortNumber)){
			 strbf.append("0000001B");
			 ++commandNumber;
		 }else{
			 if(remoteUpgradePortNumber!=null){
				 try{
					/* int i=Integer.valueOf(remoteUpgradePortNumber.trim());*/
					 Double i=Double.valueOf(remoteUpgradePortNumber.trim());
					 byte[] byt_remoteUpgradePortNumber= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000001B04"+util.toStringByte(byt_remoteUpgradePortNumber,0,byt_remoteUpgradePortNumber.length-1));
					 ++commandNumber;
				 } catch (Exception e) {
						System.out.println(e);
					}
				 }
		 }
		
		if("".equals(MaxPressure)){
			 strbf.append("00000023");
			 ++commandNumber;
		 }else{
			 if(MaxPressure!=null){
				 try {
					/* int i=Integer.valueOf(MaxPressure.trim());*/					 
					 Double i=Double.valueOf(MaxPressure.trim());
					byte[] byt_MaxPressure= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000002304"+util.toStringByte(byt_MaxPressure,0,byt_MaxPressure.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		// 
		if("".equals(MaxLiquidLeve)){
			 strbf.append("00000024");
			 ++commandNumber;
		 }else{
			 if(MaxLiquidLeve!=null){
				 try {
					 /*int i=Integer.valueOf(MaxLiquidLeve.trim());*/
					 Double i=Double.valueOf(MaxLiquidLeve.trim());
					byte[] byt_MaxLiquidLeve= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000002404"+util.toStringByte(byt_MaxLiquidLeve,0,byt_MaxLiquidLeve.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		
		if("".equals(fullLoad)){
			 strbf.append("000025");
			 ++commandNumber;
		 }else{
			 if(fullLoad!=null){
				 try {
					 /*int i=Integer.valueOf(fullLoad.trim());*/
					 Double i=Double.valueOf(fullLoad.trim());
					byte[] byt_fullLoad= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000002504"+util.toStringByte(byt_fullLoad,0,byt_fullLoad.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		
		if("".equals(TankType)){
			 strbf.append("00000026");
			 ++commandNumber;
		 }else{
			 if(TankType!=null){
				 try {
					 /*int i=Integer.valueOf(TankType.trim());*/
					 Double i=Double.valueOf(TankType.trim());
					 byte[] byt_TankType= util.int2Bytes(i.intValue(),1);
					 strbf.append("0000002601"+util.toStringByte(byt_TankType,0,byt_TankType.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				
			 }
		 }
		
		if("".equals(sleepTime)){
			 strbf.append("00000027");
			 ++commandNumber;
		 }else{
			 if(sleepTime!=null){
				 try {
					 /*int i=Integer.valueOf(sleepTime.trim());*/
					 Double i=Double.valueOf(sleepTime.trim());
					 byte[] byt_sleepTime= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000002704"+util.toStringByte(byt_sleepTime,0,byt_sleepTime.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		
		// PressureRange 0x002A
		if("".equals(PressureRange)){
			 strbf.append("0000002A");
			 ++commandNumber;
		 }else{
			 if(PressureRange!=null){
				 try {
					 /*int i=Integer.valueOf(PressureRange.trim());*/
					 Double i=Double.valueOf(PressureRange.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000002A04"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		// LiquidLeveRange  0x002B
		if("".equals(LiquidLeveRange)){
			 strbf.append("0000002B");
			 ++commandNumber;
		 }else{
			 if(LiquidLeveRange!=null){
				 try {
					 /*int i=Integer.valueOf(LiquidLeveRange.trim());*/
					 Double i=Double.valueOf(LiquidLeveRange.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000002B04"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		// referenceVoltage  0x0031
		if("".equals(referenceVoltage)){
			 strbf.append("00000031");
			 ++commandNumber;
		 }else{
			 if(referenceVoltage!=null){
				 try {
					/* int i=Integer.valueOf(referenceVoltage.trim());*/
					 Double i=Double.valueOf(referenceVoltage.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000003104"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		// referenceG  0x0032
		if("".equals(referenceG)){
			 strbf.append("00000032");
			 ++commandNumber;
		 }else{
			 if(referenceG!=null){
				 try {
					/* int i=Integer.valueOf(referenceG.trim());*/
					 Double i=Double.valueOf(referenceG.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000003204"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		// standardVoltage   0x0033
		if("".equals(standardVoltage)){
			 strbf.append("00000033");
			 ++commandNumber;
		 }else{
			 if(standardVoltage!=null){
				 try {
					/* int i=Integer.valueOf(standardVoltage.trim());*/
					 Double i=Double.valueOf(standardVoltage.trim());
						byte[] byt= util.int2Bytes(i.intValue(),4);
						 strbf.append("0000003304"+util.toStringByte(byt,0,byt.length-1));
						 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		// temperatureA   0x0034
		if("".equals(temperatureA)){
			 strbf.append("00000034");
			 ++commandNumber;
		 }else{
			 if(temperatureA!=null){
				 try {
					 /*int i=Integer.valueOf(temperatureA.trim());*/
					 Double i=Double.valueOf(temperatureA.trim());
						byte[] byt= util.int2Bytes(i.intValue(),4);
						 strbf.append("0000003404"+util.toStringByte(byt,0,byt.length-1));
						 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		// temperatureB   0x0035
		if("".equals(temperatureB)){
			 strbf.append("00000035");
			 ++commandNumber;
		 }else{
			 if(temperatureB!=null){
				 try {
					 /*int i=Integer.valueOf(temperatureB.trim());*/
					 Double i=Double.valueOf(temperatureB.trim());
						byte[] byt= util.int2Bytes(i.intValue(),4);
						 strbf.append("0000003504"+util.toStringByte(byt,0,byt.length-1));
						 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		// tankDiameter   0x0036
		if("".equals(tankDiameter)){
			 strbf.append("00000036");
			 ++commandNumber;
		 }else{
			 if(tankDiameter!=null){
				 try {
					/* int i=Integer.valueOf(tankDiameter.trim());*/
					 Double i=Double.valueOf(tankDiameter.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000003604"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		// tankLength   0x0037
		if("".equals(tankLength)){
			 strbf.append("00000037");
			 ++commandNumber;
		 }else{
			 if(tankLength!=null){
				 try {
					/* int i=Integer.valueOf(tankLength.trim());*/
					 Double i=Double.valueOf(tankLength.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000003704"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		// tankMedium   0x0038
		if("".equals(tankMedium)){
			 strbf.append("00000038");
			 ++commandNumber;
		 }else{
			 if(tankMedium!=null){
				 try {
					 /*int i=Integer.valueOf(tankMedium.trim());*/
					 Double i=Double.valueOf(tankMedium.trim());
					byte[] byt= util.int2Bytes(i.intValue(),4);
					 strbf.append("0000003804"+util.toStringByte(byt,0,byt.length-1));
					 ++commandNumber;
				} catch (Exception e) {
					System.out.println(e);
				}
				 
			 }
		 }
		
		if("".equals(remoteUpgradeSleepTime)){
			 strbf.append("00000039");
			 ++commandNumber;
		 }else{
			 if(remoteUpgradeSleepTime!=null){
				 try {
					 /*int i=Integer.valueOf(remoteUpgradeSleepTime.trim());*/
					 Double i=Double.valueOf(remoteUpgradeSleepTime.trim());
					 if(0xff>i){
						 byte[] byt_remoteUpgradeSleepTime= util.int2Bytes(i.intValue(),1);
						 strbf.append("0000003901"+util.toStringByte(byt_remoteUpgradeSleepTime,0,byt_remoteUpgradeSleepTime.length-1));
						 ++commandNumber;
					 }
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		
		if("".equals(restart)){
			 strbf.append("0000003a");
			 ++commandNumber;
		 }else{
			 if(restart!=null){
				 try {
					 /*int i=Integer.valueOf(restart.trim());*/
					 Double i=Double.valueOf(restart.trim());
					 if(0xff>i){
						 byte[] byt_restart= util.int2Bytes(i.intValue(),1);
						 strbf.append("0000003a01"+util.toStringByte(byt_restart,0,byt_restart.length-1));
						 ++commandNumber;
					 }
				} catch (Exception e) {
					System.out.println(e);
				}				 
			 }
		 }
		 
		 if("".equals(workingTime)){
			 strbf.append("0000003b");
			 ++commandNumber;
		 }else{
			 if(workingTime!=null && workingTime.contains(";")){
				 String[] str=workingTime.split(";");
				 /*int i1=Integer.valueOf(str[0]);
				 int i2=Integer.valueOf(str[1]);*/
				 Double i1=Double.valueOf(str[0].trim());
				 Double i2=Double.valueOf(str[1].trim());
				 if(0xff>i1 && 0xff>i2 ){
					 byte[] byt1= util.int2Bytes(i1.intValue(),1);
					 byte[] byt2= util.int2Bytes(i2.intValue(),1);
					 strbf.append("0000003b02"+util.toStringByte(byt1,0,byt1.length-1)+util.toStringByte(byt2,0,byt2.length-1));
					 ++commandNumber;
				 }
			 }
		 }
		 
		 //=======黄工=======		 
		 
		 if(blockSet!=null){
			 try {
				 float f=Float.parseFloat(blockSet);
				 if(f!=0) {
					 strbf.append("000000c104"+Integer.toHexString(Float.floatToIntBits(f)));
					 ++commandNumber;
				 }/*else {
					 strbf.append("000000c10400000000");
				 }*/
			 } catch (Exception e) {
					System.out.println(e);
				}
		 }/*else{
			 strbf.append("000000c104ffffffff");
		 }*/
		 
		 if(timeStart!=null){
			 try {
				 float f=Float.parseFloat(timeStart);
				 if(f!=0) {
					 strbf.append("000000c204"+Integer.toHexString(Float.floatToIntBits(f)));
					 ++commandNumber;
				 }
			 } catch (Exception e) {
					System.out.println(e);
				}
		 }
		 
		 if(timeEnd!=null){
			 try {
				 float f=Float.parseFloat(timeEnd);
				 if(f!=0) {
					 strbf.append("000000c304"+Integer.toHexString(Float.floatToIntBits(f)));
					 ++commandNumber;
				 }
			 } catch (Exception e) {
					System.out.println(e);
				}
		 }
		 
		 if(minutes!=null){
			 try {
				 float f=Float.parseFloat(minutes);
				 if(f!=0) {
					 strbf.append("000000c404"+Integer.toHexString(Float.floatToIntBits(f)));
					 ++commandNumber;
				 }
			 } catch (Exception e) {
					System.out.println(e);
				}
		 }
		 
		 
		
		 byte[] byt_commandNumber= util.int2Bytes(commandNumber,1);
		 String  str_commandNumber=util.toStringByte(byt_commandNumber,0,byt_commandNumber.length-1); 		 
		 return str_commandNumber+strbf.toString();
	}
	
	// 获取数据内容byte长度转换
	private  String getCommandLength(int commandLength){
		byte[] byt= util.int2Bytes(commandLength/2,1);
		 String  str_length=util.toStringByte(byt,0,byt.length-1); 
		return str_length;
	}
	
	/*public static void main(String[] args) {
		EncodeCommand EncodeCommand=new EncodeCommand();
		ParameterConfigurationBean contentBean=new ParameterConfigurationBean();
		contentBean.setIP1("127.0.0.1");
		contentBean.setPortNumber1("31101");
		String str=EncodeCommand.HandleEncodeCommand(contentBean);
		System.out.println(str);
	}*/
}
