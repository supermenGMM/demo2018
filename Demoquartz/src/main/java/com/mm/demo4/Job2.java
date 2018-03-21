package com.mm.demo4;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(getClass().getName()+"-----");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		int int1 = jobDataMap.getInt("i");
		System.out.println(int1++);
		jobDataMap.put("i", int1);

	}

}
