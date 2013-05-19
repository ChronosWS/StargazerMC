package com.miats.forge.limbo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;

@Mod(modid="com_miats_limbo", name="Limbo", version="0.1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class Limbo {

	// The instance of your mod that Forge uses.
	@Instance("Limbo")
	public static Limbo instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.miats.forge.limbo.client.ClientProxy", serverSide="com.miats.forge.limbo.CommonProxy")
	public static CommonProxy proxy;
	
	@ServerStarting
	public void serverStart(FMLServerStartingEvent event)
	{
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager serverCommand = ((ServerCommandManager) command);
		serverCommand.registerCommand(new LimboDebugCommand());
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerEventHooks();
		
		// Register LimboWorldProvider
		int dimID = LimboWorldProvider.LIMBO_DIMENSION_ID;
		DimensionManager.registerProviderType(dimID, LimboWorldProvider.class, true);
		DimensionManager.registerDimension(dimID, dimID);
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}