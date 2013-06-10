package stargazer.minecraft.samples.simplecontainer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import stargazer.minecraft.samples.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

//
//This is a minimal (but complete) mod entry point.
//
@Mod(modid = SampleMod.ID, name = SampleMod.NAME, version = SampleMod.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SampleMod
{
  public final static String ID = "SampleSimpleContainer";
  public final static String NAME = "Sample Simple Container";
  public final static String VERSION = "1.0";
    
  // The instance of your mod that Forge uses.
  @Instance(ID)
  public static SampleMod  instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(clientSide = "stargazer.minecraft.samples.common.ClientProxy", serverSide = "stargazer.minecraft.samples.common.CommonProxy")
  public static CommonProxy proxy;
  
  public static Block containerBlock;
  
  public static final int BLOCK_ID = 1002;
  public static final int GUI_ID = 1;
  public static final int CONTAINER_SIZE = 9;
  public static final int INVENTORY_SLOT_SIZE = 18;
  public static final int PLAYER_INVENTORY_SLOTS = 36;
  
  //
  // Mod entry points
  //  

  @Init
  public void load(FMLInitializationEvent event)
  {
    proxy.registerRenderers();
    
    this.containerBlock = new SimpleContainerBlock(BLOCK_ID, Material.wood)
    .setHardness(2.5F)
    .setStepSound(Block.soundWoodFootstep)
    .setUnlocalizedName("sampleSimpleContainer")
    .setCreativeTab(CreativeTabs.tabInventory);
  
    GameRegistry.registerBlock(containerBlock, containerBlock.getUnlocalizedName());
    LanguageRegistry.addName(containerBlock, "Simple Container");
    MinecraftForge.setBlockHarvestLevel(containerBlock, "axe", 0);
  }

  @PostInit
  public void postInit(FMLPostInitializationEvent event)
  {
    //
    // Register the tile entity
    //
    GameRegistry.registerTileEntity(SimpleContainerTileEntity.class, SimpleContainerTileEntity.class.getName());
    
    //
    // Register the gui handler
    //
    NetworkRegistry.instance().registerGuiHandler(this, new SimpleGuiHandler());
  }
}