package chronosws.minecraft.ultracraft.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import chronosws.minecraft.ultracraft.Ultracraft;
import chronosws.minecraft.ultracraft.common.CommonTileEntityWithInventory;
import chronosws.minecraft.ultracraft.recipes.Recipe;
import chronosws.minecraft.ultracraft.recipes.RecipeCategory;
import chronosws.minecraft.ultracraft.recipes.UltracraftRecipes;

public class MulticraftMachineTileEntity extends CommonTileEntityWithInventory
    implements
    MulticraftMachine
{
  protected ArrayList<RecipeCategory> categories = new ArrayList();
  
  public MulticraftMachineTileEntity(String invName, String guiId)
  {
    super(invName, guiId);
  }

  @Override
  public List<RecipeCategory> getSupportedCategories()
  {
    if(categories.isEmpty())
    {
      categories.add(RecipeCategory.getCategoryForBlockMetadata(getBlockMetadata()));
    }
    
    return this.categories;
  }

  @Override
  public Set<Recipe> getSupportedRecipesForCategory(RecipeCategory category)
  {
    // By default, we support all recipes in the specified category
    return Ultracraft.recipes.getAllRecipesForCategory(category);
  }  
}
