package com.effective.java.builder;

public class NutritionFacts {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
	protected static class Builder{
		//parameter required
		private final int servingSize;
		private final int servings;
		//parameter not required
		private int calories;
		private int fat;
		private int sodium;
		private int carbohydrate;
		public Builder(int servingSize,int servings){
			this.servings = servings;
			this.servingSize=servingSize;
		}
		public Builder fat(int fat){
			this.fat = fat;
			return this;
		}
		public Builder calories(int calories){
			this.calories = calories;
			return this;
		}
		public Builder sodium(int sodium){
			this.sodium=sodium;
			return this;
		}
		public Builder carbohydrate(int carbohyrete){
			this.carbohydrate = carbohyrete;
			return this;
		}
		public NutritionFacts builde(){
			return new NutritionFacts(this);
		}
	}
	private NutritionFacts(Builder builder){
		this.servingSize = builder.servingSize;
		this.servings = builder.servings;
		this.calories = builder.calories;
		this.fat = builder.fat;
		this.sodium = builder.sodium;
		this.carbohydrate = builder.carbohydrate;
	}
	public static void main(String[] args) {
		NutritionFacts builde = new NutritionFacts.Builder(1, 1).fat(10).builde();
		
	}
}
