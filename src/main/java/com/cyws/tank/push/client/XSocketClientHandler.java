package com.cyws.tank.push.client;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.charset.Charset;

import org.xsocket.DataConverter;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;

public class XSocketClientHandler implements IDataHandler ,IConnectHandler ,IDisconnectHandler {  
  
	
	public XSocketClientHandler(){
		
	}
    /** 
     * 连接的成功时的操作 
     */  
    @Override  
    public boolean onConnect(INonBlockingConnection nbc) throws IOException,  
            BufferUnderflowException, MaxReadSizeExceededException {  
        String  remoteName=nbc.getRemoteAddress().getHostName();  
        System.out.println("remoteName "+remoteName +" has connected ！");  
       return true;  
    }  
    /** 
     * 连接断开时的操作 
     */  
    @Override  
    public boolean onDisconnect(INonBlockingConnection nbc) throws IOException {  
        // TODO Auto-generated method stub  
    	//x.doConnect();
       return false;  
    }  
    /** 
     *  
     * 接收到数据库时候的处理 
     */  
    @Override  
    public boolean onData(INonBlockingConnection nbc) throws IOException,  
            BufferUnderflowException, ClosedChannelException,  
            MaxReadSizeExceededException {  
    	System.out.println("!!!!!!!!!!");
         /*String data=nbc.readStringByDelimiter("|");  
         nbc.write("--|Client:receive data from server sucessful| -----");  
         nbc.flush();  */
    	//String data=nbc.readStringByDelimiter("");
    	ByteBuffer copyBuffer = ByteBuffer.allocate(20000);
    	nbc.read(copyBuffer); 
    	copyBuffer.flip();
    	byte[] bt = DataConverter.toBytes(copyBuffer);
    	String data=new String(bt,Charset.forName("gbk"));
    	//处理没有数据的报文
         System.out.println(data);  
         return true;  
    }  

}
