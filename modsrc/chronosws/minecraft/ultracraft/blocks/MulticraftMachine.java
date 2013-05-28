package chronosws.minecraft.ultracraft.blocks;

import java.util.List;
import chronosws.minecraft.ultracraft.blocks.UltracraftRecipes.RecipeCategory;

public interface MulticraftMachine
{
  /**
   * Gets the categories of recipes supported by this machine
   * @return The categories of recipes supported by this machine
   */
  public List<RecipeCategory> supportedCategories();
}
