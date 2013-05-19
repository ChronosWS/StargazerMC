package com.miats.forge.limbo;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class LimboWorldProvider extends WorldProvider {
	
	public static int LIMBO_DIMENSION_ID = -4545;

	/**
     * creates a new world chunk manager for WorldProvider
     */
	@Override
    public void registerWorldChunkManager()
    {
		super.registerWorldChunkManager();
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.plains, 1.0F, 0.0F);
        //this.hasNoSky = true;
        this.dimensionId = LIMBO_DIMENSION_ID;
    }


	@Override
	public String getDimensionName() {
		return "Limbo";
	}
	
	/**
     * Returns a new chunk provider which generates chunks for this world
     */
	@Override
    public IChunkProvider createChunkGenerator()
    {
		return new LimboChunkProviderGenerator(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }


}
