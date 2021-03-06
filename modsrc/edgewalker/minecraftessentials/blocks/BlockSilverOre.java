package edgewalker.minecraftessentials.blocks;


import java.util.ArrayList;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import edgewalker.minecraftessentials.MinecraftEssentials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSilverOre extends Block
{
   
  @SideOnly(Side.CLIENT)
  private Icon icon;

  public BlockSilverOre(int blockId, Material material)
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
   
    this.icon = iconRegister.registerIcon(MinecraftEssentials.ID + ":oreSilver");
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
   * @param fortuneLvl Breakers fortune level
   * @return A ArrayList containing all items this block drops
   */
  @Override
  public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortuneLvl)
  {
    // Get the default drops (which is just the block itself)
    //ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortuneLvl);
    
    ArrayList<ItemStack> itemsDropped = new ArrayList<ItemStack>();
          
    // Drop 1-3 + 1 per fortune lvl
    itemsDropped.add(new ItemStack(MinecraftEssentials.itemSilverOre.itemID, world.rand.nextInt(3) + 1 + fortuneLvl, 0));
    
    return itemsDropped;
  }  
}
