package com.cyws.tank.TANK2;

import java.text.ParseException;
import org.quartz.SchedulerException;
import com.cyws.tank.utils.quartz.OGIJob;
import com.cyws.tank.utils.quartz.QuartzManager;

public class QuartzTest {

	public static void main(String[] args) {
		try {
			OGIJob OGIJob=  new OGIJob();
			QuartzManager qtz=new QuartzManager();
			qtz.addJob("OGI",OGIJob , "0 0/5 * * * ?");	 // 0 2 1 L * ?  每月最后一天的1点2分触发 		
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
