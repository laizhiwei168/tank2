package com.cyws.tank.utils.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesUtil {
	//static //根据Key读取Value
	//ClassLoader cl = Thread.currentThread().getContextClassLoader();
	//private PropertiesUtil(){}
	
	      public static String GetValueByKey(String fileName, String key) {
	    	  String path=System.getProperty("user.dir");
	          Properties ppts = new Properties();
	          try {
	        	  //InputStream in = cl.getResourceAsStream(fileName);
	              InputStream in = new BufferedInputStream (new FileInputStream(path+fileName));  
	              ppts.load(in);
	              String value = ppts.getProperty(key);
	             System.out.println(key + " = " + value);
	             return value;
	             
	         }catch (IOException e) {
	             e.printStackTrace();
	            return null;
	         }
	          
	     }
	      
	      public static Properties GetProperties(String fileName){
	    	  String path=System.getProperty("user.dir");
	    	  Properties ppts = new Properties();
	        	  //InputStream in = cl.getResourceAsStream(fileName);
	    	  try {    
	    	      InputStream in = new BufferedInputStream (new FileInputStream(path+fileName));                
					ppts.load(in);
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	  
	              return ppts;
	      }
	     
	     //读取Properties的全部信息
	     public static void GetAllProperties(String fileName) throws IOException {
	         Properties pps = new Properties();
	         InputStream in = new BufferedInputStream(new FileInputStream(fileName));
	         pps.load(in);
	        Enumeration en = pps.propertyNames(); //得到配置文件的名字
	         
	         while(en.hasMoreElements()) {
	             String strKey = (String) en.nextElement();
	             String strValue = pps.getProperty(strKey);
	             System.out.println(strKey + "=" + strValue);
	        }
	         in.close();
	         
	    }
	     
	     //写入Properties信息
	     public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
	    	 String path=System.getProperty("user.dir");
	         Properties pps = new Properties();
	         
	         InputStream in = new FileInputStream(path+filePath);
	         //从输入流中读取属性列表（键和元素对） 
	         pps.load(in);
	         //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
	         //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	         OutputStream out = new FileOutputStream(path+filePath);
	         pps.setProperty(pKey, pValue);
	         //以适合使用 load 方法加载到 Properties 表中的格式，  
	        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
	         pps.store(out, "Update " + pKey + " name");
	         
	         in.close();
	         out.close();
	         
	     }
     
	     public static void main(String [] args) throws IOException{
	    	 //String userdir = System.getProperty("user.dir");
	         //String value = GetValueByKey("Test.properties", "name");	        
	        // System.out.println(value);	    	 
	    	 WriteProperties("/resources/Test.properties", "00751171215020","3");
	     }
}
