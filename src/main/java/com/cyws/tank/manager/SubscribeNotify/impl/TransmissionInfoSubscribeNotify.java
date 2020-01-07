package com.cyws.tank.manager.SubscribeNotify.impl;

import com.cyws.tank.codec.vo.ExtensionInitiativeData;
import com.cyws.tank.manager.SubscribeNotify.ISubscribeNotify;
import com.cyws.tank.manager.controller.NaturalGasController;
import com.cyws.tank.manager.controller.StorageTankController;

public class TransmissionInfoSubscribeNotify implements ISubscribeNotify {

	@Override
	public void HandleSubscribeNotify(Object deviceData) {
		ExtensionInitiativeData extensionInitiativeData=(ExtensionInitiativeData) deviceData;
	    if(extensionInitiativeData.getGoalChar()==0x01){
	    	//System.out.println("====111=====");
	    	NaturalGasController naturalGasController=new NaturalGasController();
	    	naturalGasController.assemble01(extensionInitiativeData);
	    }else if(extensionInitiativeData.getGoalChar()==0x03){
	    	//System.out.println("====222=====");
	    	StorageTankController storageTankController=new StorageTankController();	    	
	    	storageTankController.assemble03(extensionInitiativeData);
	    }
	}
}
