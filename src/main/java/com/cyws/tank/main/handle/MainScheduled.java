package com.cyws.tank.main.handle;

import com.cyws.tank.manager.controller.CommandCacheController;


public class MainScheduled implements Runnable{
	private CommandCacheController commandCacheController=new CommandCacheController();
	@Override
	public void run() {
		System.out.println("========= System task timer ==========");
		// 刷新命令到缓存里
		commandCacheController.handlecomman2Cache();
	}
}
