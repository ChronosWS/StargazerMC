package com.miats.forge.limbo;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;

public class LimboEventHandler {
	
	@ForgeSubscribe
	public void EntityEvent(LivingEvent e) {
		if (e.entityLiving instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)e.entityLiving;
			if (player.worldObj.isRemote) return;
			
			if (!(e instanceof LivingUpdateEvent)){
				FMLLog.info(e.getClass().getCanonicalName().toString());
			}
			
			if (e instanceof LivingDeathEvent) {
				//player.mov
				player.isDead = false;
				player.setEntityHealth(20);
				player.extinguish();
				//player.
				MinecraftServer mServer = MinecraftServer.getServer();
				//mServer.getConfigurationManager().transferPlayerToDimension(player, 1);

				int dimID = LimboWorldProvider.LIMBO_DIMENSION_ID;
				if(player.dimension == -dimID) {
					player.travelToDimension(0);
				} else {
					player.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 15, 1, true));
					//mServer.getConfigurationManager().
					mServer.getConfigurationManager().transferPlayerToDimension(player, dimID, new LimboTeleporter(DimensionManager.getWorld(dimID)));
					//player.travelToDimension(LimboWorldProvider.LIMBO_DIMENSION_ID);
				}
				
				/*player.travelToDimension(-1);
				player.posY=80;
				player.posX=-23;
				player.posZ=33;*/
				//player.
				e.setCanceled(true);
			}
		}
	}
	
	@ForgeSubscribe
	public void TerrainGenHandler(ChunkProviderEvent.ReplaceBiomeBlocks e) {
		
		//if (e instan)
		
	}

}
