package com.dumb;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class DumpDemo {
	public static void main(String[] args) {
		
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail job = newJob(DumbJob.class)
					.withIdentity("dumb","group1")
					.usingJobData("s1", "job")
					.usingJobData("f1",2323.2f)
					.build();
			
			SimpleTrigger trigger = newTrigger()
			.withIdentity("trigger", "group")
			.startNow()
			.usingJobData("name", "aaa")
			.usingJobData("age", 1)
			.withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever())
			.build();
			
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
