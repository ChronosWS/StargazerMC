package chronosws.minecraft.ultracraft.blocks;

import java.util.List;
import java.util.Set;
import chronosws.minecraft.ultracraft.recipes.Recipe;
import chronosws.minecraft.ultracraft.recipes.RecipeCategory;

public interface MulticraftMachine
{
  /**
   * Gets the categories of recipes supported by this machine
   * @return The categories of recipes supported by this machine
   */
  public List<RecipeCategory> getSupportedCategories();

  /**
   * Gets the set of recipes which are supported by this machine for the specified category
   * @param category The recipe category
   * @return The supported set of recipes
   */
  public Set<Recipe> getSupportedRecipesForCategory(RecipeCategory category);
 }
