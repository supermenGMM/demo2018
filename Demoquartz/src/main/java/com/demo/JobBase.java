package com.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class JobBase implements Job{
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	/*	try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		lockDate();
		exe();
	}
	public abstract void lockDate();
	public abstract void exe();
}
