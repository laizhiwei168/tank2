package com.cyws.tank.utils.common;



public class Utilty {
    /*private static Utilty instance = new Utilty();

    public static Utilty getInstance() {
        return instance;
    }*/

    public static final int MIN_MID_VALUE = 1;

    public static final int MAX_MID_VALUE = 65535;


    //�ֽ���תΪ�޷�������
    public int bytes2Int(byte[] b, int start, int length) {
        int sum = 0;
        int end = start + length;
        for (int k = start; k < end; k++) {
            long n = ((int) b[k]) & 0xff;
            n <<= (--length) * 8;
            sum += n;
        }
        return sum;
    }
    
    //�ֽ���תΪ�޷�������
    public long bytes2Long(byte[] b, int start, int length) {
        long sum = 0;
        int end = start + length;
        for (int k = start; k < end; k++) {
            long n = ((int) b[k]) & 0xff;
            n <<= (--length) * 8;
            sum += n;
        }
        return sum;
    }

    //����תΪ�ֽ���
    public byte[] int2Bytes(int value, int length) {
        byte[] b = new byte[length];
        for (int k = 0; k < length; k++) {
            b[length - k - 1] = (byte) ((value >> 8 * k) & 0xff);
        }
        return b;
    }

    //�ж�mid�Ƿ���Ч
    public boolean isValidofMid(int mId) {
        if (mId < MIN_MID_VALUE || mId > MAX_MID_VALUE) {
            return false;
        }
        return true;
    }

    /***
     * ���ֽ���ת��Ϊ16�����ַ���
     */
    public  String parseByte2HexStr(byte[] buf) {
        if (null == buf) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
       
    /*CRC16校验码*/
   	public  int CRC16(byte[] Buf, int len) {  
           int CRC;  
           int i, j;  
           CRC = 0xffff;  
           for (i = 0; i < len; i++) {  
               CRC = CRC ^ (Buf[i] & 0xff);  
               for (j = 0; j < 8; j++) {  
                   if ((CRC & 0x01) == 1)  
                       CRC = (CRC >> 1) ^ 0xA001;  
                   else  
                       CRC = CRC >> 1;  
               }  
           }  
           return CRC;  
       }
   	
   	
   	/*转换时间格式*/
   	public  String toDateTime(String dateTime) {    	
       	/*170804095101*/       	
   		return "20"+dateTime.substring(0,6)+"T"+dateTime.substring(6)+"Z";
   	}

   	/*十六进制数组数据根据起始索引与结束索引定位转换为字符串*/
   	@SuppressWarnings("unused")
   	public  String toStringByte(byte[] bytes, int start,int end) {
   		StringBuffer strbf=new StringBuffer();		
   		for(int i=start ; i<=end;i++) {
   			
   			if(Integer.toHexString(bytes[i]).length()==1) {
   				strbf.append("0"+Integer.toHexString(bytes[i]& 0xFF));
   			}else {
   				strbf.append(Integer.toHexString(bytes[i]& 0xFF).toUpperCase());
   			}
   			
   		}
   		
   		return strbf.toString();
   	}
   	
   	/*String转换为byte数组*/
   	public  byte[] hexStrToByteArray(String str)
	{
	    if (str == null) {
	        return null;
	    }
	    if (str.length() == 0) {
	        return new byte[0];
	    }
	    byte[] byteArray = new byte[str.length() / 2];
	    for (int i = 0; i < byteArray.length; i++){
	        String subStr = str.substring(2 * i, 2 * i + 2);
	        byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
	    }
	    return byteArray;
	}
   
    /*
     *处理16进制的String变成ACSLL码 
     */
    public String convertHexToString(String hex){  
    	  
        StringBuilder sb = new StringBuilder();  
        StringBuilder temp = new StringBuilder();  
    
        //49204c6f7665204a617661 split into two characters 49, 20, 4c...  
        for( int i=0; i<hex.length()-1; i+=2 ){  
    
            //grab the hex in pairs  
            String output = hex.substring(i, (i + 2));  
            //convert hex to decimal  
            int decimal = Integer.parseInt(output, 16);  
            //convert the decimal to character  
            sb.append((char)decimal);  
    
            temp.append(decimal);  
        }  
    
        return sb.toString();  
        }
    
    /*
     *处理ACSLL码变成16进制的String
     */
    public String convertStringToHex(String str){  
    	  
        char[] chars = str.toCharArray();  
    
        StringBuffer hex = new StringBuffer();  
        for(int i = 0; i < chars.length; i++){  
          hex.append(Integer.toHexString((int)chars[i]));  
        }  
    
        return hex.toString();  
      }

}
