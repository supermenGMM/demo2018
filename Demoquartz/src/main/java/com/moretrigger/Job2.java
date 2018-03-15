package com.moretrigger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("job2--execute");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("job2");			
	}
	
}