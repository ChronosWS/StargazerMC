package stargazer.minecraft.samples.simplecontainer;

import java.util.ArrayList;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SimpleContainerBlock extends BlockContainer
{  
  /**
   * An array of icons representing each side of the block.
   */
  @SideOnly(Side.CLIENT)
  private Icon icon;

  public SimpleContainerBlock(int blockId, Material material)
  {  
    super(blockId, material);
  }

  /**
   * This method is what is called by the renderer to determine which texture to use
   * to draw a given side.  
   * @param side The side of the block for which the icon/texture should be retrieved.
   * @param metadata The metadata value associated with this block.
   * @return The Icon holding the texture to be rendered for this side.
   */
  @SideOnly(Side.CLIENT)
  public Icon getIcon(int side, int metadata)
  {
    return this.icon;
  }

  /**
   * This method is called to load textures for the block.  It is only called once
   * so load all of the textures you might need.
   * 
   * @param iconRegister The IconRegister associated with block textures.
   */
  @SideOnly(Side.CLIENT)
  public void registerIcons(IconRegister iconRegister)
  {
    this.icon = iconRegister.registerIcon(SampleMod.ID + ":container");
  }

  /**
   * Called when the player activates (right clicks) on the block.  Here
   * we can determine whether or not to open the gui and show the container contents,
   * for instance.
   * @return True if we have "activated" the block, false otherwise.
   */
  @Override
  public boolean onBlockActivated(
      World world, int x, int y, int z,
      EntityPlayer player, 
      int idk, float what, float these, float are) 
  {
    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if (tileEntity == null || player.isSneaking()) {
            return false;
    }

    player.openGui(SampleMod.instance, SampleMod.GUI_ID, world, x, y, z);
    return true;
  }
  
  /**
   * Gets the TileEntity implementation representing this container's content storage in the world.
   */
  @Override
  public TileEntity createNewTileEntity(World world) {
          return new SimpleContainerTileEntity();
  }

  /**
   * This returns a complete list of items dropped from this block.
   *
   * @param world The current world
   * @param x X Position
   * @param y Y Position
   * @param z Z Position
   * @param metadata Current metadata
   * @param fortune Breakers fortune level
   * @return A ArrayList containing all items this block drops
   */
  @Override
  public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
  {
    // Get the default drops (which is just the block itself)
    ArrayList<ItemStack> itemsToDrop = super.getBlockDropped(world, x, y, z, metadata, fortune);
    
    // Now add in the whole contents of this inventory.
    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if (tileEntity instanceof IInventory)
    {
      IInventory inventory = (IInventory) tileEntity;
  
      for (int i = 0; i < inventory.getSizeInventory(); i++)
      {
        ItemStack item = inventory.getStackInSlot(i);
  
        if (item != null && item.stackSize > 0)
        {
          itemsToDrop.add(item);
        }
      }    
    }
    
    return itemsToDrop;    
  }  
}
