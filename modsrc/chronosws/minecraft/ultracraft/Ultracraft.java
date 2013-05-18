package chronosws.minecraft.ultracraft;

import java.util.logging.Logger;
import chronosws.minecraft.ultracraft.utilities.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Ultracraft.ID, name = "Ultracrafting Table", version = Ultracraft.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Ultracraft
{
  public final static String ID = "Ultracraft";
  public final static String VERSION = "1.0";
  
  public static GeneralConfig generalConfig = new GeneralConfig();
  
  //
  // Items and blocks
  //
  public static Item  genericItem;
  public static Block ucTable;

  // The instance of your mod that Forge uses.
  @Instance(ID)
  public static Ultracraft  instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(clientSide = "chronosws.minecraft.ultracraft.client.ClientProxy", serverSide = "chronosws.minecraft.ultracraft.CommonProxy")
  public static CommonProxy proxy;

  // Logger
  public static Logger logger;

  //
  // Mod entry points
  //
  
  @PreInit
  public void preInit(FMLPreInitializationEvent event)
  {
    logger = Logger.getLogger(ID);
    logger.setParent(FMLLog.getLogger());
        
    Config.load(event.getSuggestedConfigurationFile(), generalConfig);
  }

  @Init
  public void load(FMLInitializationEvent event)
  {
    initBlocks();
    initItems();
    initCrafting();
    initNames();

    proxy.registerRenderers();
  }

  @PostInit
  public void postInit(FMLPostInitializationEvent event)
  {
    // Stub Method
  }

  //
  // Helper methods
  //

  // Initialize blocks
  private void initBlocks()
  {
    ucTable = new UltraCraftingTable(generalConfig.ucTableId, Material.wood)
      .setHardness(2.5F)
      .setStepSound(Block.soundWoodFootstep)
      .setUnlocalizedName("ucTable")
      .setCreativeTab(CreativeTabs.tabDecorations);
    
    GameRegistry.registerBlock(ucTable, ucTable.getUnlocalizedName());
    MinecraftForge.setBlockHarvestLevel(ucTable, "axe", 0);
  }

  // Initialize items
  private void initItems()
  {
    genericItem = new GenericItem(generalConfig.genericItemId)
      .setMaxStackSize(64)
      .setUnlocalizedName("genericItem")
      .setCreativeTab(CreativeTabs.tabMisc);
  }
  
  // Initialize names
  private void initNames()
  {
    LanguageRegistry.addName(genericItem, "Generic Item");
    LanguageRegistry.addName(ucTable, "Ultracrafting Table");
  }

  // Initialize all crafting recipes for this mod.
  private void initCrafting()
  {
    ItemStack cobbleStack = new ItemStack(Block.cobblestone);
    ItemStack chestStack = new ItemStack(Block.chest);
    ItemStack plankStack = new ItemStack(Block.planks);
    ItemStack ucStack = new ItemStack(ucTable);

    // Ultracrafting Table
    GameRegistry.addRecipe(ucStack, "aaa", "bcb", "bbb", 'a', cobbleStack, 'b',
        plankStack, 'c', chestStack);
  }
}