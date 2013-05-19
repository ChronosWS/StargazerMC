package com.miats.forge.limbo;

import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;

public class LimboTeleporter extends Teleporter {

	private WorldServer worldServer;
	
	public LimboTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
		worldServer = par1WorldServer;
	}

	/**
	 * Place an entity in a nearby portal, creating one if necessary.
	 */
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		if (par1Entity.dimension == StaticConfig.LIMBO_DIM_ID) {
			double x = StaticConfig.LIMBO_SPAWN_X,
				   y = StaticConfig.LIMBO_SPAWN_Y,
				   z = StaticConfig.LIMBO_SPAWN_Z;
	
			x += PositionHelper.fuzz(StaticConfig.LIMBO_SPAWN_MINFUZZ, StaticConfig.LIMBO_SPAWN_MAXFUZZ);
			z += PositionHelper.fuzz(StaticConfig.LIMBO_SPAWN_MINFUZZ, StaticConfig.LIMBO_SPAWN_MAXFUZZ);
			
			double pitch = VectorMath.lookAtPitch(x, y + par1Entity.getEyeHeight(), z, 0.5, y, 0.5),
				   yaw   = VectorMath.lookAtYaw(x, y + par1Entity.getEyeHeight(), z, 0.5, y, 0.5);
			
			y = SpawnPosHelper.FindSafeSpawnHeight(worldServer, (int)x, (int)z);
			
			par1Entity.setLocationAndAngles(x, y, z, (float)yaw, (float)pitch);
		} else {
			ChunkCoordinates p = par1Entity.worldObj.getSpawnPoint();
			par1Entity.setLocationAndAngles(p.posX, p.posY, p.posZ, par1Entity.rotationYaw, 0.0f);
			
		}
		par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
	}

	/**
	 * Place an entity in a nearby portal which already exists.
	 */
	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		throw new RuntimeException("I wish you hadn't called me...");
	}

	public boolean makePortal(Entity par1Entity)
	{
		return true;
	}

	/**
	 * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
	 * WorldServer.getTotalWorldTime() value.
	 */
	public void removeStalePortalLocations(long par1)
	{
	}
}

