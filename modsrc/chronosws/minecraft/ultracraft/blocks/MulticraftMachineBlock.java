package chronosws.minecraft.ultracraft.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
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
  protected Map<Integer, Map<Integer, Icon>> icons;
  
  protected MulticraftMachineBlock(int blockId, Material material)
  {
    super(blockId, material);
    this.icons = new HashMap();
  }
  
  @SideOnly(Side.CLIENT)
  public Icon getIcon(int side, int metadata)
  {
    return this.icons.get(metadata).get(side);
  }

  @SideOnly(Side.CLIENT)
  public void registerIcons(IconRegister iconRegister)
  {
    for(RecipeCategory category : RecipeCategory.values())
    {
      HashMap<Integer, Icon> iconsBySide = new HashMap();
      iconsBySide.put(BlockSide.TOP.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name() + "_top"));
      iconsBySide.put(BlockSide.NORTH.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name() + "_side"));
      iconsBySide.put(BlockSide.SOUTH.sideIndex, iconsBySide.get(BlockSide.NORTH.sideIndex));
      iconsBySide.put(BlockSide.EAST.sideIndex, iconsBySide.get(BlockSide.NORTH.sideIndex));
      iconsBySide.put(BlockSide.WEST.sideIndex, iconsBySide.get(BlockSide.NORTH.sideIndex));
      iconsBySide.put(BlockSide.BOTTOM.sideIndex, iconRegister.registerIcon("Ultracraft:mcmachine_" + category.name() + "_bottom"));
      this.icons.put(category.getBlockMetadata(), iconsBySide);
    }
  }
  
  @Override
  public TileEntity createNewTileEntity(World world)
  {
    return new MulticraftMachineTileEntity("MulticraftMachine", "MulticraftMachineGui");
  }  
}
