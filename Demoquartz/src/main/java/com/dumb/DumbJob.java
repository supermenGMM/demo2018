package com.dumb;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DumbJob implements Job{
	private String name;
	private int age;
	

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String string = jobDataMap.getString("s1");
		float float1 = jobDataMap.getFloat("f1");
		System.out.println(string+",,"+float1);
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		String name = mergedJobDataMap.getString("name");
		
		int age = mergedJobDataMap.getInt("age");
		System.out.println(name+"---"+age);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
}
