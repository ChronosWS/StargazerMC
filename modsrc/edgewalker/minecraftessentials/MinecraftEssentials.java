package edgewalker.minecraftessentials;


import edgewalker.minecraftessentials.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

//
// This is a minimal (but complete) mod entry point.
//
@Mod(modid = MinecraftEssentials.ID, name = MinecraftEssentials.NAME, version = MinecraftEssentials.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MinecraftEssentials
{
  public final static String ID = "MinecraftEssentials";
  public final static String NAME = "Minecraft Essentials";
  public final static String VERSION = "0.0.1";
    
  // The instance of your mod that Forge uses.
  @Instance(ID)
  public static MinecraftEssentials  instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(clientSide = "edgewalker.minecraftessentials.client.ClientProxy", serverSide = "edgewalker.minecraftessentials.common.CommonProxy")
  public static CommonProxy proxy;
  
  //
  // Mod entry points
  //  
  @Init
  public void load(FMLInitializationEvent event)
  {
    proxy.registerRenderers();
  }
  
  @ServerStarting
  public void serverLoad(FMLServerStartingEvent event)
  {
    
  }

}