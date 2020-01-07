package com.cyws.tank.command.socket;

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

import com.cyws.tank.command.handle.HandleCloudCommand;



/**
 * 
 * @author lzw
 *
 */

public class ProjectCommadHandle implements IDataHandler, IConnectHandler,IDisconnectHandler {

	public boolean onDisconnect(INonBlockingConnection connection)
			throws IOException {
		
        return true; 
	}
	
	public boolean onConnect(INonBlockingConnection connection)
			throws IOException, BufferUnderflowException,
			MaxReadSizeExceededException {
		return true;
	}
	
	public boolean onData(INonBlockingConnection connection)
			throws IOException, BufferUnderflowException,
			ClosedChannelException, MaxReadSizeExceededException {
		
		ByteBuffer copyBuffer = ByteBuffer.allocate(20000);
		connection.read(copyBuffer); 
    	copyBuffer.flip();
    	
    	//处理没有数据的报文
    	byte[] bt = DataConverter.toBytes(copyBuffer);
    	String ss=new String(bt,Charset.forName("gbk"));//把byte数组转换为中文
    	System.out.println(ss);
    	
        //处理数据报文业务
    	new HandleCloudCommand().DecodeCloudCommand(ss);
        return true;
	}   
}  