package com.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class JobBase implements Job{
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		lockDate();
		exe();
	}
	public abstract void lockDate();
	public abstract void exe();
}
