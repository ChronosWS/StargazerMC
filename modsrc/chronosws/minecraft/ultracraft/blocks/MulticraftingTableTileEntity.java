package chronosws.minecraft.ultracraft.blocks;

import java.util.ArrayList;
import java.util.List;
import chronosws.minecraft.ultracraft.recipes.RecipeCategory;

public class MulticraftingTableTileEntity extends MulticraftMachineTileEntity
    implements
    MulticraftMachine
{
  private static List<RecipeCategory> supportedCategory = new ArrayList() {{ add(RecipeCategory.MISCELLANEOUS); }};
  
  public MulticraftingTableTileEntity(String invName, String guiId)
  {
    super(invName, guiId);
  }
  
  @Override
  public List<RecipeCategory> getSupportedCategories()
  {
    return supportedCategory;
  }
}
