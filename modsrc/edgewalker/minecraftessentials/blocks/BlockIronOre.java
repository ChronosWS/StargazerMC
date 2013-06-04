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

public class BlockIronOre extends Block
{
   
  @SideOnly(Side.CLIENT)
  private Icon icon;

  public BlockIronOre(int blockId, Material material)
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
   
    this.icon = iconRegister.registerIcon(MinecraftEssentials.ID + ":oreIron");
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
    ArrayList<ItemStack> itemsDropped = new ArrayList<ItemStack>();
          
    // Drop 1-3 ore items upon breaking, with 1 additional drop for each level of fortune the player has on tool
    itemsDropped.add(new ItemStack(MinecraftEssentials.itemIronOre.itemID, world.rand.nextInt(3) + fortuneLvl + 1, 0));
    
    // In addition, there's a 20% chance that the block will drop chromium, which is used to make steel
    // fortune adds an additional 10% chance per level that chromium will drop.
    if(world.rand.nextInt(10)+fortuneLvl>=8)
    {
      itemsDropped.add(new ItemStack(MinecraftEssentials.itemChromium.itemID,1,0));    
    }
    
    return itemsDropped;
  }  
}
