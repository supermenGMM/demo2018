package com.moretrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.*;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class CronDemo {
	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail job1 = newJob(Job1.class).withIdentity("job1", "group1")
					.build();
			// 定时执行\,之后重复
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 10);
			Date startTime = calendar.getTime();
			System.out.println(startTime.toLocaleString() + "-=----");
			
			JobDetail job = newJob(Job1.class).withIdentity("job1", "group1")
					.build();
			SimpleTrigger trigger = (SimpleTrigger) newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(startTime)
//					.withSchedule(
//							SimpleScheduleBuilder.simpleSchedule()
//									.repeatForever().withIntervalInSeconds(10))
					.build();

			Date ft = scheduler.scheduleJob(job, trigger);
			System.out.println(ft);

			// 在未来的5分钟
			JobDetail job2 = newJob(Job2.class)
			.withIdentity("job2","group1")
			.build();
			Trigger trigger2 = newTrigger()
			.withIdentity("triggger2","group1")
			.startAt(DateBuilder.futureDate(20, IntervalUnit.SECOND))
//			.forJob("job2")
			.build();

			scheduler.scheduleJob(job1,trigger2);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
