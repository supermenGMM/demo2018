package com.moretrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
public class TriggerDemo {
	public static void main(String[] args) throws SchedulerException {
//		排除的时间
		HolidayCalendar hCalendar = new HolidayCalendar();
		Calendar calendar =Calendar.getInstance();
				calendar.set(2018, 3, 16);
		Date excludedDate = calendar.getTime();
		hCalendar.addExcludedDate(excludedDate );
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		scheduler.addCalendar("calname", hCalendar, false,false);
		JobDetail job1 = newJob(Job1.class)
				.withIdentity("myjob", "group1")
				.build();
		Trigger trigger = newTrigger()
				.withIdentity("trigger1","group1")
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(17,47))
				.modifiedByCalendar("calname")
				.startNow()
				.build();
		
		scheduler.scheduleJob(job1, trigger);
	}
	
	
}
