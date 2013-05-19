package com.miats.forge.limbo;

public class PositionHelper {
	
	public static double fuzz(double min, double max) {
		double fuzz = Math.random() * (max - min) + min;
		return (Math.random() < 0.5 ? fuzz : -fuzz);
	}

}
