package com.cyws.tank.utils.quartz;

import java.text.ParseException;  

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;  
import org.quartz.Job;  
import org.quartz.JobBuilder;
import org.quartz.JobDetail;  
import org.quartz.Scheduler;  
import org.quartz.SchedulerException;  
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzManager {
	private static SchedulerFactory sf = new StdSchedulerFactory();  
	   private static String JOB_GROUP_NAME = "group1";  
	   private static String TRIGGER_GROUP_NAME = "trigger1";  
	    
	     
	   /** *//** 
	    *  添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
	    * @param jobName 任务名 
	    * @param job     任务 
	    * @param time    时间设置，参考quartz说明文档 
	    * @throws SchedulerException 
	    * @throws ParseException 
	    */  
	   public static void addJob(String jobName,Job job,String time)   
	                               throws SchedulerException, ParseException{  
	       Scheduler sched = sf.getScheduler();  
	       JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobName, JOB_GROUP_NAME).requestRecovery().build();//任务名，任务组，任务执行类  
	       //触发器  
	       CronTrigger  trigger =  TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)  
	               .withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
	       sched.scheduleJob(jobDetail, trigger);  
	       //启动  
	       if(!sched.isShutdown()) {
	          sched.start();
	       }
	   }

}
