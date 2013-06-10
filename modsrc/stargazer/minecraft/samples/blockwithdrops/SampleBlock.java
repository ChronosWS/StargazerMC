package stargazer.minecraft.samples.blockwithdrops;

import java.util.ArrayList;
import java.util.Random;
import stargazer.minecraft.samples.common.BlockSide;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class SampleBlock extends Block
{  
  /**
   * An array of icons representing each side of the block.
   */
  @SideOnly(Side.CLIENT)
  private Icon[] icons;

  public SampleBlock(int blockId, Material material)
  {  
    super(blockId, material);
    this.icons = new Icon[6];
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
    return this.icons[side];
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
    // registerIcon will load the texture from the resources path based on
    // the colon-separated strings passed as an argument.  In the example 
    // below, the first part of the string is the mod id, meaning the base
    // path will be /mods/SampleBreakableBlock/textures/blocks.  The /mods
    // and /textures/blocks are added automatically because registerIcons is
    // being called by Minecraft in the context of retrieving block textures.
    // The second part of the string - 'top' - is the name of the texture
    // file without the .png extension.  So this method call specifically
    // means: retrieve the texture from /mods/SampleBreakableBlock/textures/blocks/top.png
    // and make an icon out of it.  We then store this in the array indexed by 
    // side so that we can return it to the renderer, when getIcon() is called.
    this.icons[BlockSide.TOP.sideIndex] = iconRegister.registerIcon(SampleMod.ID + ":top");
    this.icons[BlockSide.BOTTOM.sideIndex] = iconRegister.registerIcon(SampleMod.ID + ":bottom");
    this.icons[BlockSide.NORTH.sideIndex] = iconRegister.registerIcon(SampleMod.ID + ":north");
    this.icons[BlockSide.SOUTH.sideIndex] = iconRegister.registerIcon(SampleMod.ID + ":south");
    this.icons[BlockSide.EAST.sideIndex] = iconRegister.registerIcon(SampleMod.ID + ":east");
    this.icons[BlockSide.WEST.sideIndex] = iconRegister.registerIcon(SampleMod.ID + ":west");
  }

  /**
   * If we wanted to do something special when the block is about to break, we would
   * do it here.
   */
  @Override
  public void breakBlock(World world, int x, int y, int z, int par5, int par6)
  {    
    super.breakBlock(world, x, y, z, par5, par6);
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
    ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);
    
    ItemStack defaultDrop = ret.get(0);
    
    // We are only going to drop additional items if this is the first time we have broken
    // this block - otherwise you could break it, place it and break it again and get the drops
    // again.
    // TODO: See below, this mechanic doesn't work (metadata is always 0.)
    if(defaultDrop.getItemDamage() == 0)
    {
      // Set the metadata value for the block drop to 1.
      // TODO: This is not having any actual effect right now.  Needs fixing.
      ret.get(0).setItemDamage(1);
  
      // Drop an additional stack of 0 to 4 items with no metadata.
      ret.add(new ItemStack(SampleMod.sampleItem.itemID, world.rand.nextInt(5) + 1, 0));
    }
    
    return ret;
  }  
}
