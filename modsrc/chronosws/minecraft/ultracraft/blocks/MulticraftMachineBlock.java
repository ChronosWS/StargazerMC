package chronosws.minecraft.ultracraft.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import chronosws.minecraft.ultracraft.common.BlockSide;
import chronosws.minecraft.ultracraft.common.CommonBlockContainer;
import chronosws.minecraft.ultracraft.common.CommonTileEntityWithInventory;
import chronosws.minecraft.ultracraft.recipes.RecipeCategory;

/**
 * Represents any of the standard Multicrafting machines.  Metadata value corresponds to
 * the recipe category (See RecipeCategory.getBlockMetadata())
 * 
 * @author Cliff
 *
 */
public class MulticraftMachineBlock extends CommonBlockContainer
{
  public static final String unlocalizedName = "mcMachine";
  
  protected Map<Integer, Map<Integer, Icon>> icons;
  protected Icon blankMachineIcon;
  
  public MulticraftMachineBlock(int blockId, Material material)
  {
    super(blockId, material);
    this.icons = new HashMap();
  }
  
  public static final String getUnlocalizedNameForMetadata(int metadata)
  {
    RecipeCategory category = RecipeCategory.getCategoryForBlockMetadata(metadata);
    if(category != null)
    {
      return unlocalizedName + "." + RecipeCategory.getCategoryForBlockMetadata(metadata).name();
    }
    else
    {
      return unlocalizedName;
    }
  }
  
  @SideOnly(Side.CLIENT)
  public Icon getIcon(int side, int metadata)
  {
    
    Map<Integer, Icon> iconsForMachine = this.icons.get(metadata);
    if(iconsForMachine != null)
    {
      Icon icon = iconsForMachine.get(side);
      if(icon != null)
      {
        return icon;
      }
    }
    
    return blankMachineIcon;
  }

  @SideOnly(Side.CLIENT)
  public void registerIcons(IconRegister iconRegister)
  {
    blankMachineIcon = iconRegister.registerIcon("Ultracraft:mcmachine_blank");
    for(RecipeCategory category : RecipeCategory.values())
    {
      HashMap<Integer, Icon> iconsBySide = new HashMap();
      for(BlockSide side : BlockSide.values())
      {
        iconsBySide.put(BlockSide.TOP.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name()));
      }
      
      /*
      iconsBySide.put(BlockSide.TOP.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name() + "_top"));
      iconsBySide.put(BlockSide.NORTH.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name() + "_side"));
      iconsBySide.put(BlockSide.SOUTH.sideIndex, iconsBySide.get(BlockSide.NORTH.sideIndex));
      iconsBySide.put(BlockSide.EAST.sideIndex, iconsBySide.get(BlockSide.NORTH.sideIndex));
      iconsBySide.put(BlockSide.WEST.sideIndex, iconsBySide.get(BlockSide.NORTH.sideIndex));
      iconsBySide.put(BlockSide.BOTTOM.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name() + "_bottom"));
      this.icons.put(category.getBlockMetadata(), iconsBySide);
      */
    }
  }
  
  @Override
  public TileEntity createNewTileEntity(World world)
  {
    return new MulticraftMachineTileEntity("MulticraftMachine", "MulticraftMachineGui");
  }  

  /**
   * Ensure that when this block is dropped, the metadata it had is transferred into the item dropped.
   */
  @Override
  public int damageDropped(int metadata)
  {
    return metadata;
  }
  
  /**
   * When items go into the creative tab for this block, we will instead add
   * versions that contain machine-specific metadata.
   */
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) 
  {
    for(RecipeCategory category : RecipeCategory.values())
    {
      subItems.add(new ItemStack(this, 1, category.blockMetadata));
    }
  }
}
