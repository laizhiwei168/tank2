package com.cyws.tank.main.start;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.cyws.tank.bean.common.ConstantBean;
import com.cyws.tank.command.handle.HandleCloudCommand;
import com.cyws.tank.command.socket.XSocketCommadServer;
import com.cyws.tank.main.handle.MainScheduled;
import com.cyws.tank.push.client.XSocketClientManager;
import com.cyws.tank.push.tcp.XSocketPushServer;
import com.cyws.tank.socket.tcp.XSocketServer;
import com.cyws.tank.websocket.client.CommmandClient;
import com.cyws.tank.websocket.heartbeat.CommmandHeartbeat;

/**
 * 港能与储运卫士结合的
 * ---------港能-------------
 * 1.港能的位置报文不需要解析，
 * 2.压力过低的数据过滤掉不需要入库
 * 
 * ----------储运卫士----------
 * 1.结合港能压力过低的数据过滤掉
 * 
 * 
 * 集团处理
 * 1.排除转发ip地址登记设备管理，起到过滤没有下发命令的设备139.196.92.92
 * 2.需要转发10015900287和11115900400数据给客户
 * 
 * @author lzw
 *
 */

public class TankMain2 {
	private static  Integer PORT;
	public static void main(String[] args) {
		  /**开启解析程序的xsocketServer*/
		  PORT=Integer.valueOf(ConstantBean.getConstantMap().get("XSOCKET_PORT").toString());
		  XSocketServer projectServer = new XSocketServer(PORT);
		  projectServer.start();
		  
		  /**开启命令接收socket服务端*/
		  // 抛弃
			/*ExecutorService Quartz_exc= Executors.newSingleThreadExecutor();
			try {
			  Quartz_exc.execute(new Runnable() {
				@Override
				public void run() {
					XSocketCommadServer projectServer = new XSocketCommadServer(7000);
				    projectServer.start();
				}
			});
			} catch (Exception e) {
			}finally{
			    Quartz_exc.shutdown();
			}*/
		  
		  
		  /**开启客户推送程序的xsocketPush  */
		  // 抛弃
		  /*ExecutorService PushUserext= Executors.newSingleThreadExecutor();
		  try {
			  PushUserext.execute(new Runnable() {
				@Override
				public void run() {
					XSocketPushServer projectPushServer = new XSocketPushServer(31101);
					projectPushServer.start();
				}
			  });
		  } catch (Exception e) {
		  }finally{
			  PushUserext.shutdown();//结束线程
		  }*/
		  
		  /**main 定时轮查*/
		  ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		  executor.scheduleAtFixedRate(new MainScheduled(),0, 30, TimeUnit.MINUTES);
		  
		  
		  /**开启websocket心跳连接*/
		  ExecutorService Commmandext= Executors.newSingleThreadExecutor();
		  try {
			  //"ws://127.0.0.1:8080/LayIM_JavaClient/websocket/456";//
			  String wrUrl=ConstantBean.getConstantMap().get("WEBSOCKETURL").toString();
			  if(wrUrl!=null && !"".equals(wrUrl)){
				  CommmandClient commmandClient=new CommmandClient();
				  commmandClient.runCommmandConnect(wrUrl);
				  Commmandext.execute(new CommmandHeartbeat(commmandClient));//创建commandwebsocket心跳包
			  }
		  }catch (Exception e) {
		  }finally{
	    	Commmandext.shutdown();//结束线程
		  }
		  
		  
		  /**谭总 137.138  这个推送使用在集团管理所有设备时使用到的*/
		  // 抛弃
		  /*String pushIP=ConstantBean.getConstantMap().get("push_jituan_ip").toString();
		 int pushPORT=Integer.valueOf(ConstantBean.getConstantMap().get("push_jituan_port").toString());
		  ExecutorService pushJiTuanToClient_ext= Executors.newSingleThreadExecutor();
	        try {
	        	pushJiTuanToClient_ext.execute(new Runnable() {
				@Override
				public void run() {
					XSocketClientManager pushJiTuanToClient=XSocketClientManager.getClientManager();
					pushJiTuanToClient.connection(pushIP, pushPORT);
				}
	        	});
	        } catch (Exception e) {
		    }finally{
		    	pushJiTuanToClient_ext.shutdown();//结束线程   
		    }*/
		  
	}
}
 