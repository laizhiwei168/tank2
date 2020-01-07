package com.cyws.tank.push.tcp;

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

import com.cyws.tank.protocol.service.ProtocolServiceImpl;
import com.cyws.tank.push.service.PushProtocolServiceImpl;
import com.cyws.tank.push.user.PushUserManager;
import com.cyws.tank.socket.user.DeviceUserManager;

/**
 * 
 * @author lzw
 *
 */

public class PushProjectHandle implements IDataHandler, IConnectHandler,IDisconnectHandler {

	public boolean onDisconnect(INonBlockingConnection connection)
			throws IOException {
		PushUserManager pushUserManager=PushUserManager.getInstance();        
		pushUserManager.removePushUser(connection.getId());
        System.out.println("Total number of PushUser online is "+pushUserManager.getOnlineCount());
        return true; 
	}
	
	public boolean onConnect(INonBlockingConnection connection)
			throws IOException, BufferUnderflowException,
			MaxReadSizeExceededException {
		    //System.out.println(connection.getId() + "is connect!");
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
		String json=new String(bt,Charset.forName("gbk"));//把byte数组转换为中文
    	//System.out.println(json);
    	
    	PushProtocolServiceImpl pushProtocolServiceImpl=new PushProtocolServiceImpl();
    	pushProtocolServiceImpl.dealWithData(json, connection);
        return true;
	}
}  