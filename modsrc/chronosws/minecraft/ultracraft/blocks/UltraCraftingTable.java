package chronosws.minecraft.ultracraft.blocks;

import java.util.Random;
import chronosws.minecraft.ultracraft.CommonGuiSlotMap;
import chronosws.minecraft.ultracraft.TileEntityWithInventory;
import chronosws.minecraft.ultracraft.Ultracraft;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class UltraCraftingTable extends BlockContainer
{

  @SideOnly(Side.CLIENT)
  private Icon workbenchIconTop;
  @SideOnly(Side.CLIENT)
  private Icon workbenchIconFront;

  public UltraCraftingTable(int blockId, Material material)
  {
    super(blockId, material);
    
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
  public void breakBlock(World world, int x, int y, int z, int par5, int par6)
  {
    dropItems(world, x, y, z);
    super.breakBlock(world, x, y, z, par5, par6);
  }

  private void dropItems(World world, int x, int y, int z)
  {
    Random rand = new Random();

    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if (!(tileEntity instanceof IInventory))
    {
      return;
    }
    IInventory inventory = (IInventory) tileEntity;

    for (int i = 0; i < inventory.getSizeInventory(); i++)
    {
      ItemStack item = inventory.getStackInSlot(i);

      if (item != null && item.stackSize > 0)
      {
        float rx = rand.nextFloat() * 0.8F + 0.1F;
        float ry = rand.nextFloat() * 0.8F + 0.1F;
        float rz = rand.nextFloat() * 0.8F + 0.1F;

        EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz,
            new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

        if (item.hasTagCompound())
        {
          entityItem.getEntityItem().setTagCompound(
              (NBTTagCompound) item.getTagCompound().copy());
        }

        float factor = 0.05F;
        entityItem.motionX = rand.nextGaussian() * factor;
        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
        entityItem.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(entityItem);
        item.stackSize = 0;
      }
    }
  }

  public boolean onBlockActivated(World world, int x, int y, int z,
      EntityPlayer player, int idk, float what, float these, float are)
  {
    if (player.isSneaking())
    {
      return false;
    }

    player.openGui(Ultracraft.instance, 0, world, x, y, z);
    return true;
  }

  @Override
  public TileEntity createNewTileEntity(World world)
  {
    return new TileEntityWithInventory("UltraCraftingTable", "UltraCraftingTableGui");
  }
}
