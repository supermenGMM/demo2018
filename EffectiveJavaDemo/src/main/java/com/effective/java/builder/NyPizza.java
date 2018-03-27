package com.effective.java.builder;

public class NyPizza extends Pizza{
	private final int size;
	public static class NyBuilder extends Builder{
		private  int size;
		
		public NyBuilder size(int size){
			this.size = size;
			return self();
		}
		
		@Override
		public Pizza Builde() {
			return new NyPizza(self());
		}

		@Override
		public NyBuilder self() {
			return this;
		}
		
	}
	private NyPizza(NyBuilder builder){
		super(builder);
		size = builder.size;
	}
}
