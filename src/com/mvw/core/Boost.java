package com.mvw.core;

public class Boost {
	private Trait trait;
	private int bump;
	
	public Boost(Trait trait, int bump) {
		super();
		this.trait = trait;
		this.bump = bump;
	}
	
	public Trait getTrait() {
		return trait;
	}
	public int getBump() {
		return bump;
	}
		
}
