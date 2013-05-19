package com.miats.forge.limbo;

public class VectorMath {
	
	public static final double PITCH_UP		= -90.0;
	public static final double PITCH_DOWN	= 90.0;
	public static final double YAW_NORTH	= 180.0;  // - Z
	public static final double YAW_EAST		= -90.0;  // + X
	public static final double YAW_SOUTH	= 0.0;    // + Z
	public static final double YAW_WEST		= 90.0;   // - X

	public static final double TO_DEGREES   = 180.0 / Math.PI;
	public static final double TO_RAD       = Math.PI / 180.0;
	
	public static double lookAtYaw(double headX, double headY, double headZ, double targetX, double targetY, double targetZ) {
		double dX = headX - targetX,
		       //dY = headY - targetY,
		       dZ = headZ - targetZ;
		return Math.atan2(dX, -dZ) * TO_DEGREES;
	}
	
	public static double lookAtPitch(double headX, double headY, double headZ, double targetX, double targetY, double targetZ) {
		double dX = headX - targetX,
		       dY = headY - targetY,
		       dZ = headZ - targetZ,
		       q  = Math.sqrt(dX * dX + dZ * dZ);
		return Math.atan(dY / q) * TO_DEGREES;
	}

}
