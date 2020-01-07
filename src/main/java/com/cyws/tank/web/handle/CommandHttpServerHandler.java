package com.cyws.tank.web.handle;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.cyws.tank.utils.common.StringUtils;
import com.cyws.tank.utils.log.LogManager;
import com.cyws.tank.utils.po.HttpCommandPo;
import com.cyws.tank.utils.po.HttpResponsePo;
import com.cyws.tank.utils.serializer.FastJsonSerializer;
import com.cyws.tank.web.controller.HttpServerCommandController;
import com.cyws.tank.websocket.client.CommmandClient;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * 主要处理HttpServer接收数据和发送数据
 * */
public class CommandHttpServerHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String response = handleRequestData(exchange);
                    sendResponseData(exchange, response);
                }catch (IOException ie) {
                    ie.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
	}
	
	/* 处理接收httpserver数据内容 */
	public String handleRequestData(HttpExchange exchange) throws IOException{
		
		//获得查询字符串(get)
        /*String queryString = exchange.getRequestURI().getQuery();
        Map<String,String> queryStringInfo = StringUtils.formData2Dic(queryString);
        System.out.println("========get 表单提交=========");
        for(String key:queryStringInfo.keySet()){
        	System.out.println(key+"   ==  "+queryStringInfo.get(key));
        }*/
		
        //获得表单提交数据(post)
		FastJsonSerializer  fastJsonSerializer=new FastJsonSerializer();
		HttpResponsePo httpResponsePo=new HttpResponsePo();
		
        String postString = IOUtils.toString(exchange.getRequestBody());
        LogManager.getInstance().PrintGetHttpCommandLog(postString);// 打印数据信息
        if(postString!=null && !"".equals(postString) && StringUtils.isJSONValid2(postString)){
	        HttpCommandPo httpCommandPo= fastJsonSerializer.toObject(postString, HttpCommandPo.class);
	        if(httpCommandPo!=null){
	        	System.out.println("========post 表单提交=========");
	            String equipmet_id=httpCommandPo.getEquipmet_id();
	            String command_type=httpCommandPo.getCommand_type();
	            String command_content=httpCommandPo.getCommand_content();
	            System.out.println(equipmet_id.trim()+"   "+ command_type.trim()+"    "+ command_content.trim());
	            if(!StringUtils.strIsNullOrEmpty(equipmet_id)&& !StringUtils.strIsNullOrEmpty(command_type)&& !StringUtils.strIsNullOrEmpty(command_content)){        	
	            	httpResponsePo.setResult(0);
	            	httpResponsePo.setMessage("succeed");
	            	 
	            	//HttpServerCommandController httpServerCommandController=new HttpServerCommandController();
	            	//httpServerCommandController.HandleSendCommandToDevice(equipmet_id.trim(), command_type.trim(), command_content.trim());
	            }else{
	            	httpResponsePo.setResult(2);
	            	httpResponsePo.setMessage("Incorrect parameter contents");	            	
	            }
	        } else {
	        	httpResponsePo.setResult(1);
            	httpResponsePo.setMessage("Wrong way to get an interface");
	        }
        }else{
        	httpResponsePo.setResult(1);
        	httpResponsePo.setMessage("Wrong way to get an interface");
        }
        String str_eventdatajson=fastJsonSerializer.toJSON(httpResponsePo);
		return str_eventdatajson;
	}
	
	/* 处理发送httpserver数据响应内容 */
	public void sendResponseData(HttpExchange exchange,String response) throws IOException{
		Headers responseHeaders = exchange.getResponseHeaders();
		exchange.sendResponseHeaders(200,0);
		//responseHeaders.set("Content-Type", "application/json");
        //responseHeaders.set("Access-Control-Allow-Origin", "*");
		
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}	
	
}
