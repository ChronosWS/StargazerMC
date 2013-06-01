package chronosws.minecraft.ultracraft.blocks;

import chronosws.minecraft.ultracraft.recipes.RecipeCategory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MulticraftMachineItemBlock extends ItemBlock
{
  public MulticraftMachineItemBlock(int par1)
  {
    super(par1);
    setHasSubtypes(true);
      
  }

  @Override
  public int getMetadata(int damageValue)
  {
    return damageValue;
  }
  
  @Override 
  public String getUnlocalizedName(ItemStack itemStack)
  {
    return MulticraftMachineBlock.getUnlocalizedNameForMetadata(itemStack.getItemDamage());
  }
}
