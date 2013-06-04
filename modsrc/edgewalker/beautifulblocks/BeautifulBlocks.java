package edgewalker.beautifulblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import edgewalker.beautifulblocks.common.CommonProxy;
import edgewalker.minecraftessentials.blocks.BlockCopperOre;

//
// This is a minimal (but complete) mod entry point.
//
@Mod(modid = BeautifulBlocks.ID, name = BeautifulBlocks.NAME, version = BeautifulBlocks.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class BeautifulBlocks {


	  public final static String ID = "BeautifulBlocks";
	  public final static String NAME = "Beautiful Blocks";
	  public final static String VERSION = "0.0.1";
	  
	  public static Block blockOnyx;
	
	  // The instance of your mod that Forge uses.
	  @Instance(ID)
	  public static BeautifulBlocks  instance;

	  // Says where the client and server 'proxy' code is loaded.
	  @SidedProxy(clientSide = "edgewalker.beautifulblocks.client.ClientProxy", serverSide = "edgewalker.beautifulblocks.common.CommonProxy")
	  public static CommonProxy proxy;
	  
	  //
	  // Mod entry points
	  //  
	  @Init
	  public void load(FMLInitializationEvent event)
	  {	
		initBlocks();
		initItems();
	    initRecipes();
		proxy.registerRenderers();
	    
	  }
	  
	  private void initRecipes()
	  {
		  
	  }

	@PostInit
	  public void postInit(FMLPostInitializationEvent event)
	  {
		  initNames();
	  }
	  
	  @ServerStarting
	  public void serverLoad(FMLServerStartingEvent event)
	  {
	    
	  }
	  
	  // Initialize names
	  private void initNames()
	  {
		  LanguageRegistry.addName(blockOnyx, "Honey Onyx Block");  
	  }
	  
	  // Initialize blocks
	  private void initBlocks()
	  {
		   blockOnyx = new BlockOnyx(900, Material.rock)
		      .setHardness(3F)
		      .setStepSound(BlockOnyx.stepSound)
		      .setUnlocalizedName("blockOnyx")
		      .setCreativeTab(BlockOnyx.creativeTab);	    
		    GameRegistry.registerBlock(blockOnyx, blockOnyx.getUnlocalizedName());
		    MinecraftForge.setBlockHarvestLevel(blockOnyx, BlockOnyx.harvestTool, BlockOnyx.harvestLevel);
	  }
	  
	  // Initialize items
	  private void initItems()
	  {
	  }
		  
	
}
