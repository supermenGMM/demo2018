package com.mm.demo4;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class StatefulJobDemo {
	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		
		JobDetail job1 = JobBuilder.newJob(Job1Stateful.class)
		.withIdentity("job1", "group1")
		.usingJobData("i", 1)
		.build();
		
		JobDetail job2 = JobBuilder.newJob(Job2.class)
				.usingJobData("i", 1)
				.withIdentity("job2", "group2")
				.build();
		
		
		SimpleTrigger trigger1 = TriggerBuilder.newTrigger()
		.withIdentity("trigger1", "group1")
		.usingJobData("j", 2)
		.startNow()
		.withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(10)
				.repeatForever())
		.build();
		SimpleTrigger trigger2 = TriggerBuilder.newTrigger()
				.usingJobData("j", 2)
				.withIdentity("trigger2", "group2")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(15)
						.repeatForever())
				.build();
		
		
		scheduler.scheduleJob(job1,trigger1);
		scheduler.scheduleJob(job2,trigger2);
		
//		scheduler.shutdown();
	}
	
}
