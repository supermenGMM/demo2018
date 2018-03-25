package com.demo;

import java.util.concurrent.ExecutionException;

import com.thread.demo.Cat;
import com.thread.demo.CatThreadService;

public class JobDemo extends JobBase{

	@Override
	public void lockDate() {
		System.out.println("lockDate");
	}

	@Override
	public void exe() {
		try {
			new CatThreadService<Cat>().execute();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
