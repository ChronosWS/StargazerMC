package com.miats.forge.limbo;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class SpawnPosHelper {
	
	/**
	 * Find the highest safe spawn point for in World w at (x, z).  Returns null if there is no safe location. 
	 * @return Safe spawn height or -1 if none exist.
	 */
	public static int FindSafeSpawnHeight(WorldServer w, int x, int z, int maxy) {
		for (int i = maxy - 2; i >= 0; i--) {
			if (w.getBlockId(x, i, z) != 0 && w.getBlockId(x, i + 1, z) == 0 && w.getBlockId(x, i + 2, z) == 0)
				return i + 1;
		}
		return -1;
	}
	
	public static int FindSafeSpawnHeight(WorldServer w, int x, int z) {
		return FindSafeSpawnHeight(w, x, z, w.getHeight());
	}

}
