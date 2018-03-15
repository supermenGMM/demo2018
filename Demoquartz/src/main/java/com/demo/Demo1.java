package com.demo;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.moretrigger.Job1;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
public class Demo1 {
	public static void main(String[] args)  {
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail job1 = newJob(JobDemo.class)
					.withIdentity("job1", "group1")
					.build();
			
			Trigger trigger = newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(10)
							.repeatForever())
					.build();
			scheduler.scheduleJob(job1, trigger);
			JobDetail job2 = newJob(Job1.class).withIdentity("job2","group2").build();
			SimpleTrigger trigger2 = newTrigger()
			.withIdentity("trigger2","group1")
			.startNow()
			.withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
			.build();
			
			scheduler.scheduleJob(job2, trigger2);
			
//			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
