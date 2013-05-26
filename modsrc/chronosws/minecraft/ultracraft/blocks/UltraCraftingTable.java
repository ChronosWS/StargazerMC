package chronosws.minecraft.ultracraft.blocks;

import java.util.List;
import java.util.Random;
import chronosws.minecraft.ultracraft.Ultracraft;
import chronosws.minecraft.ultracraft.common.CommonBlockContainer;
import chronosws.minecraft.ultracraft.common.CommonContainerInfo;
import chronosws.minecraft.ultracraft.common.CommonTileEntityWithInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class UltraCraftingTable extends CommonBlockContainer
{
  @SideOnly(Side.CLIENT)
  private Icon workbenchIconTop;
  @SideOnly(Side.CLIENT)
  private Icon workbenchIconFront;

  private UltracraftRecipes recipes;
  
  public UltraCraftingTable(RecipeConfig config, int blockId, Material material)
  {
    super(blockId, material);
    this.recipes = new UltracraftRecipes(config);
    this.recipes.updateRecipeMappings();
  }

  /**
   * From the specified side and block metadata retrieves the blocks texture.
   * Args: side, metadata
   */
  @SideOnly(Side.CLIENT)
  public Icon getIcon(int side, int metadata)
  {
    return side == 1 ? this.workbenchIconTop : (side == 0 ? Block.planks
        .getBlockTextureFromSide(side)
        : (side != 2 && side != 4 ? this.blockIcon : this.workbenchIconFront));
  }

  @SideOnly(Side.CLIENT)
  public void registerIcons(IconRegister iconRegister)
  {
    this.blockIcon = iconRegister.registerIcon("Ultracraft:workbench_side");
    this.workbenchIconTop = iconRegister
        .registerIcon("Ultracraft:workbench_top");
    this.workbenchIconFront = iconRegister
        .registerIcon("Ultracraft:workbench_front");
  }
  
  @Override
  public TileEntity createNewTileEntity(World world)
  {
    return new CommonTileEntityWithInventory("UltraCraftingTable", "UltraCraftingTableGui");
  }  
}
