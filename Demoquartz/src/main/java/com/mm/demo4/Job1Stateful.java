package com.mm.demo4;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class Job1Stateful implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(getClass().getName()+"-----");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		int int1 = jobDataMap.getInt("i");
		System.out.println(int1++);
		jobDataMap.put("i", int1);
	}
	
}
