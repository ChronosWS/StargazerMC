package chronosws.minecraft.samples.guioverlay;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import chronosws.minecraft.ultracraft.blocks.UltraCraftingTable;
import chronosws.minecraft.ultracraft.client.GuiBuffBar;
import chronosws.minecraft.ultracraft.common.CommonContainerInfo;
import chronosws.minecraft.ultracraft.common.CommonGuiHandler;
import chronosws.minecraft.ultracraft.common.CommonProxy;
import chronosws.minecraft.ultracraft.common.CommonTileEntityWithInventory;
import chronosws.minecraft.ultracraft.utilities.Config;
import cpw.mods.fml.common.FMLLog;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

//
// This is a basic mod with minimal (but complete) mod which demonstrates how to draw
// overlays on the main game screen
//
@Mod(modid = GuiOverlayMod.ID, name = GuiOverlayMod.NAME, version = GuiOverlayMod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class GuiOverlayMod
{
  public final static String ID = "GuiOverlay";
  public final static String NAME = "GuiOverlay";
  public final static String VERSION = "1.0";
    
  // The instance of your mod that Forge uses.
  @Instance(ID)
  public static GuiOverlayMod  instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(clientSide = "chronosws.minecraft.ultracraft.client.ClientProxy", serverSide = "chronosws.minecraft.ultracraft.CommonProxy")
  public static CommonProxy proxy;
  
  //
  // Mod entry points
  //  
  @PreInit
  public void preInit(FMLPreInitializationEvent event)
  {
  }

  @Init
  public void load(FMLInitializationEvent event)
  {
    proxy.registerRenderers();
  }

  @PostInit
  public void postInit(FMLPostInitializationEvent event)
  {
    MinecraftForge.EVENT_BUS.register(new GuiBuffBar(Minecraft.getMinecraft()));
  }
}