package com.cyws.tank.web.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.http.HttpResponse;

import com.cyws.tank.web.handle.CommandHttpServerHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerRun  implements Runnable{

	@Override
	public void run() {
		try {
			InetAddress inetAddress = InetAddress.getByName("0.0.0.0");
			HttpServer server = HttpServer.create(new InetSocketAddress(inetAddress,8001), 0);
			server.createContext("/tank/command", new CommandHttpServerHandler());			
	        server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}        
	}
}
