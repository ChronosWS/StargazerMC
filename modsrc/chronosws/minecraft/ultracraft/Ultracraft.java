package chronosws.minecraft.ultracraft;

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
import chronosws.minecraft.ultracraft.client.UltracraftingContainerInfo;
import chronosws.minecraft.ultracraft.common.CommonContainerInfo;
import chronosws.minecraft.ultracraft.common.CommonGuiHandler;
import chronosws.minecraft.ultracraft.common.CommonProxy;
import chronosws.minecraft.ultracraft.common.CommonTileEntityWithInventory;
import chronosws.minecraft.ultracraft.recipes.RecipeConfig;
import chronosws.minecraft.ultracraft.recipes.UltracraftRecipes;
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

@Mod(modid = Ultracraft.ID, name = Ultracraft.NAME, version = Ultracraft.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Ultracraft
{
  public final static String ID = "Ultracraft";
  public final static String NAME = "Ultracraft";
  public final static String VERSION = "1.0";
  
  public static GeneralConfig generalConfig = new GeneralConfig();
  public static RecipeConfig recipeConfig = new RecipeConfig();
  public static UltracraftRecipes recipes;

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

  // Gui 
  public static Map<String, CommonContainerInfo> guis = new HashMap<String, CommonContainerInfo>();
  
  //
  // Mod entry points
  //
  
  @PreInit
  public void preInit(FMLPreInitializationEvent event)
  {
    logger = Logger.getLogger(ID);
    logger.setParent(FMLLog.getLogger());
    Config.sync(event.getModConfigurationDirectory(), ID, generalConfig);
    Config.sync(event.getModConfigurationDirectory(), ID, recipeConfig);
    this.recipes = new UltracraftRecipes(recipeConfig);
    this.recipes.updateRecipeMappings();
  }

  @Init
  public void load(FMLInitializationEvent event)
  {
    initBlocks();
    proxy.registerRenderers();
  }
  
  @PostInit
  public void postInit(FMLPostInitializationEvent event)
  {
    initCrafting();
    initTileEntities();
    initGuis();
    initItems();
    initNames();
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
    Item.itemsList[Item.pickaxeStone.itemID].setMaxDamage(0);
    Item.itemsList[Item.pickaxeIron.itemID].setMaxDamage(0);
    Item.itemsList[Item.pickaxeGold.itemID].setMaxDamage(0);
    Item.itemsList[Item.pickaxeWood.itemID].setMaxDamage(0);
    Item.itemsList[Item.pickaxeDiamond.itemID].setMaxDamage(0);

    Item.itemsList[Item.shovelStone.itemID].setMaxDamage(0);
    Item.itemsList[Item.shovelIron.itemID].setMaxDamage(0);
    Item.itemsList[Item.shovelGold.itemID].setMaxDamage(0);
    Item.itemsList[Item.shovelWood.itemID].setMaxDamage(0);
    Item.itemsList[Item.shovelDiamond.itemID].setMaxDamage(0);

    Item.itemsList[Item.hoeStone.itemID].setMaxDamage(0);
    Item.itemsList[Item.hoeIron.itemID].setMaxDamage(0);
    Item.itemsList[Item.hoeGold.itemID].setMaxDamage(0);
    Item.itemsList[Item.hoeWood.itemID].setMaxDamage(0);
    Item.itemsList[Item.hoeDiamond.itemID].setMaxDamage(0);

    Item.itemsList[Item.axeStone.itemID].setMaxDamage(0);
    Item.itemsList[Item.axeIron.itemID].setMaxDamage(0);
    Item.itemsList[Item.axeGold.itemID].setMaxDamage(0);
    Item.itemsList[Item.axeWood.itemID].setMaxDamage(0);
    Item.itemsList[Item.axeDiamond.itemID].setMaxDamage(0);

    Item.itemsList[Item.shears.itemID].setMaxDamage(0);
    
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
  
  private void initTileEntities()
  {    
    GameRegistry.registerTileEntity(CommonTileEntityWithInventory.class, CommonTileEntityWithInventory.ENTITY_ID);
  }
  
  private void initGuis()
  {  
    // 
    // Register our guis
    // TODO: Tie this in more closely with the block declarations so we can 
    //       get the container impls.
    this.guis.put("UltraCraftingTableGui", new UltracraftingContainerInfo());
    
    //
    // Register any special handlers
    //
    NetworkRegistry.instance().registerGuiHandler(this, new CommonGuiHandler());
  }
}