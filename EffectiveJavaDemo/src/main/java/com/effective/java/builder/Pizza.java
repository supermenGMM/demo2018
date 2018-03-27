package com.effective.java.builder;

import java.util.EnumSet;
import java.util.Set;

public abstract class Pizza {
	private final Set<Topping> toppings;
	public enum Topping{
		BEED,MIKE,CHICKEN
	}
	public static abstract class Builder{
		EnumSet<Topping> enumSet = EnumSet.noneOf(Topping.class);
		public Builder addTopping(Topping topping){
			enumSet.add(topping);
			return self();
		}
		public abstract Pizza Builde();
		public abstract Builder self();
	}
	protected Pizza(Builder builder){
		toppings = builder.enumSet;
	}
}