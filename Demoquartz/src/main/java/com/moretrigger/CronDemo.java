package com.moretrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.mm.demo4.Job2;

public class CronDemo {
	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail job1 = JobBuilder.newJob(Job2.class).withIdentity("job1", "group1")
					.build();
			// 定时执行\,之后重复
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 10);
			Date startTime = calendar.getTime();
			System.out.println(startTime.toLocaleString() + "-=----");
			
			SimpleTrigger trigger = (SimpleTrigger) newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(startTime)
//					.withSchedule(
//							SimpleScheduleBuilder.simpleSchedule()
//									.repeatForever().withIntervalInSeconds(10))
					.build();

			Date ft = scheduler.scheduleJob(job1, trigger);
			System.out.println(ft);

			// 在未来20秒执行一次
			JobDetail job2 = newJob(Job2.class)
			.withIdentity("job2","group2")
			.build();
			Trigger trigger2 = newTrigger()
			.withIdentity("triggger2","group2")
			.startAt(DateBuilder.futureDate(20, IntervalUnit.SECOND))
			.forJob("job2", "group2")
			.build();

			scheduler.scheduleJob(job2,trigger2);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
