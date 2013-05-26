package chronosws.minecraft.ultracraft.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import chronosws.minecraft.ultracraft.blocks.UltracraftRecipes.RecipeCategory;
import chronosws.minecraft.ultracraft.utilities.Config.CfgSource;

@CfgSource(file="Recipes")
public class RecipeConfig
{

  /**
   * Look up the recipe category for the output, as specified in the config file.
   * 
   * @param recipeOutput
   * @return The category, or null if no category is specified in the config file.
   */
  public RecipeCategory getCategoryForOutput(ItemStack recipeOutput)
  {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Look up the recipe category for a specified creative tab.
   * @param tab The CreativeTabs entry
   * @return The category, or nill if no category is specified in the config file.
   */
  public RecipeCategory getCategoryForCreativeTab(CreativeTabs tab)
  {
    // TODO Auto-generated method stub
    return null;
  }

}
