package stargazer.minecraft.samples.noachievements;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import stargazer.minecraft.samples.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

//
//This is a minimal (but complete) mod entry point.
//
@Mod(modid = SampleMod.ID, name = SampleMod.NAME, version = SampleMod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SampleMod
{
  public final static String ID = "SampleNoAchievements";
  public final static String NAME = "Sample No Achievements";
  public final static String VERSION = "1.0";
    
  // The instance of your mod that Forge uses.
  @Instance(ID)
  public static SampleMod  instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(clientSide = "stargazer.minecraft.samples.common.ClientProxy", serverSide = "stargazer.minecraft.samples.common.CommonProxy")
  public static CommonProxy proxy;
  
  //
  // Mod entry points
  //  

  @Init
  public void load(FMLInitializationEvent event)
  {
    proxy.registerRenderers();
  }

  @PostInit
  public void postInit(FMLPostInitializationEvent event)
  {
    Minecraft.getMinecraft().guiAchievement = new NoAchievement(Minecraft.getMinecraft());
  }
}