package edgewalker.minecraftessentials;


import chronosws.minecraft.ultracraft.blocks.UltraCraftingTable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import edgewalker.minecraftessentials.blocks.*;
import edgewalker.minecraftessentials.common.*;
import edgewalker.minecraftessentials.items.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

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
  
  public static Item itemCopperOre;
  public static Item itemChromium;
  public static Item itemIronOre;
  public static Item itemGoldOre;
  public static Item itemSilverOre;
  public static Item itemSulphur;
  public static Item itemCopperIngot;
  public static Item itemSteelIngot;
  public static Item itemSilverIngot;
  
  // Ores
  public static Block blockCopperOre;
  public static Block blockIronOre;
  public static Block blockGoldOre;
  public static Block blockSilverOre;
  
  // Crafted Metal Block
  public static Block blockCopper;
  public static Block blockSilver;
  public static Block blockSteel;
  
    
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
	initBlocks();
	initItems();
    initRecipes();
	proxy.registerRenderers();
    
  }
  
  private void initRecipes()
  {
	  GameRegistry.addSmelting(itemIronOre.itemID, new ItemStack(Item.ingotIron), 1.0f);
	  GameRegistry.addSmelting(itemGoldOre.itemID, new ItemStack(Item.ingotGold), 1.0f);
	  GameRegistry.addSmelting(itemCopperOre.itemID, new ItemStack(this.itemCopperIngot), 1.0f);
	  GameRegistry.addSmelting(itemSilverOre.itemID, new ItemStack(this.itemSilverIngot), 1.0f);
	  
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
	// Item Names
    LanguageRegistry.addName(itemCopperOre, "Copper Ore");
    LanguageRegistry.addName(itemIronOre, "Iron Ore");
    LanguageRegistry.addName(itemChromium, "Chromium");
    LanguageRegistry.addName(itemSilverOre, "Silver Ore");
    LanguageRegistry.addName(itemSulphur, "Sulphur");
    LanguageRegistry.addName(itemGoldOre, "Gold Ore");
    LanguageRegistry.addName(itemSteelIngot, "Stainless Steel Ingot");
    LanguageRegistry.addName(itemCopperIngot, "Copper Ingot");
    LanguageRegistry.addName(itemSilverIngot, "Silver Ingot");
    
    
    // Block Names
    LanguageRegistry.addName(blockCopperOre, "Copper Ore Block");
    LanguageRegistry.addName(blockIronOre, "Iron Ore Block");
    LanguageRegistry.addName(blockSilverOre, "Silver Ore Block");
    LanguageRegistry.addName(blockGoldOre, "Gold Ore Block");
    LanguageRegistry.addName(blockSteel, "Steel Block");
    LanguageRegistry.addName(blockCopper, "Copper Block");    
  }
  
  // Initialize blocks
  private void initBlocks()
  {
	   blockCopperOre = new BlockCopperOre(700, Material.rock)
	      .setHardness(3F)
	      .setStepSound(Block.soundGravelFootstep)
	      .setUnlocalizedName("blockCopperOre")
	      .setCreativeTab(CreativeTabs.tabBlock);	    
	    GameRegistry.registerBlock(blockCopperOre, blockCopperOre.getUnlocalizedName());
	    MinecraftForge.setBlockHarvestLevel(blockCopperOre, "pickaxe", 1);
	    
	   blockIronOre = new BlockIronOre(701, Material.rock)
	      .setHardness(3F)
	      .setStepSound(Block.soundGravelFootstep)
	      .setUnlocalizedName("blockIronOre")
	      .setCreativeTab(CreativeTabs.tabBlock);	    
	    GameRegistry.registerBlock(blockIronOre, blockIronOre.getUnlocalizedName());
	    MinecraftForge.setBlockHarvestLevel(blockIronOre, "pickaxe", 1);
	    
	   blockGoldOre = new BlockGoldOre(702, Material.rock)
	      .setHardness(3F)
	      .setStepSound(Block.soundGravelFootstep)
	      .setUnlocalizedName("blockGoldOre")
	      .setCreativeTab(CreativeTabs.tabBlock);	    
	    GameRegistry.registerBlock(blockGoldOre, blockGoldOre.getUnlocalizedName());
	    MinecraftForge.setBlockHarvestLevel(blockGoldOre, "pickaxe", 2);
	    
	   blockSilverOre = new BlockSilverOre(703, Material.rock)
	      .setHardness(3F)
	      .setStepSound(Block.soundGravelFootstep)
	      .setUnlocalizedName("blockSilverOre")
	      .setCreativeTab(CreativeTabs.tabBlock);	    
	    GameRegistry.registerBlock(blockSilverOre, blockSilverOre.getUnlocalizedName());
	    MinecraftForge.setBlockHarvestLevel(blockSilverOre, "pickaxe", 2);
	    
	   blockCopper = new BlockCopper(704,Material.iron)
	   	.setHardness(5f)
	   	.setStepSound(Block.soundMetalFootstep)
	   	.setUnlocalizedName("blockCopper")
	   	.setCreativeTab(CreativeTabs.tabBlock);	   
	   GameRegistry.registerBlock(blockCopper, blockCopper.getUnlocalizedName());
	   MinecraftForge.setBlockHarvestLevel(blockCopper, "pickaxe", 2);
	    
	   blockSteel = new BlockSteel(705,Material.iron)
	   	.setHardness(5f)
	   	.setStepSound(Block.soundMetalFootstep)
	   	.setUnlocalizedName("blockSteel")
	   	.setCreativeTab(CreativeTabs.tabBlock);	   
	   GameRegistry.registerBlock(blockSteel, blockSteel.getUnlocalizedName());
	   MinecraftForge.setBlockHarvestLevel(blockSteel, "pickaxe", 2);
	   
	   blockSilver = new BlockSilver(706,Material.iron)
	   	.setHardness(5f)
	   	.setStepSound(Block.soundMetalFootstep)
	   	.setUnlocalizedName("blockSilver")
	   	.setCreativeTab(CreativeTabs.tabBlock);	   
	   GameRegistry.registerBlock(blockSilver, blockSilver.getUnlocalizedName());
	   MinecraftForge.setBlockHarvestLevel(blockSilver, "pickaxe", 2);
	    
  }
  
  // Initialize items
  private void initItems()
  {
	  itemCopperOre = new ItemCopperOre(4000);  
	  itemChromium = new ItemChromium(4001);  
	  itemIronOre = new ItemIronOre(4002);  
	  itemGoldOre = new ItemGoldOre(4003);  
	  itemSilverOre = new ItemSilverOre(4004);  
	  itemSulphur = new ItemSulphur(4005);  
	  itemCopperIngot = new ItemCopperIngot(4006);
	  itemSteelIngot = new ItemSteelIngot(4007);
	  itemSilverIngot = new ItemSilverIngot(4008);
  }
  

}