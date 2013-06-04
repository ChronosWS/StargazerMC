package edgewalker.beautifulblocks;

	import java.util.ArrayList;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

	public class BlockOnyx extends Block
	{
	
		public static final float hardness = 3F;
		public static final Material material = Material.rock;
		public static final String harvestTool = "pickaxe";
		public static final int harvestLevel = 1;
		public static final CreativeTabs creativeTab = CreativeTabs.tabBlock;
		public static final StepSound stepSound = Block.soundGravelFootstep;
		
	  @SideOnly(Side.CLIENT)
	  private Icon icon;

	  public BlockOnyx(int blockId, Material material)
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
	    this.icon = iconRegister.registerIcon(BeautifulBlocks.ID + ":onyx_1");
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
	    
	    
	    
	    return ret;
	  }  
	

}
