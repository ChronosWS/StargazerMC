package chronosws.minecraft.ultracraft.recipes;

import java.util.HashMap;
import java.util.Map;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum RecipeCategory
{
  /**
   * Recipes for tools 
   */
  TOOLS ("Tools", 1),

  /**
   *  Recipes for construction materials
   */
  MASONRY ("Masonry", 2),

  /**
   *  Recipes which convert ore from raw materials into ingots and such.
   */
  ORES ("Ores", 3),

  /**
   * Recipes for machines
   */
  MACHINES ("Machines", 4),

  /**
   *  Recipes for metal items with significant tooling, such as armor and weapons
   */
  ARMOURY ("Armoury", 5),

  /**
   *  Recipes for more complex wood-based items
   */
  WOODWORKING ("Woodworking", 6),
  
  /**
   *  Recipes for working with wool and string to make cloth
   */
  CLOTH ("Cloth", 7), 

  /**
   * Recipes for food
   */
  FOOD ("Food", 8),

  /**
   *  Recipes for jewelry
   */
  JEWELRY ("Jewelry", 9),

  /**
   *  Recipes for exotic/magic items such as blaze powder
   */
  EXOTIC ("Exotic", 10), 

  /**
   * Recipes for potions
   */
  BREWING ("Brewing", 11),

  /**
   *  Recipes which do not fall into any other category
   */
  MISCELLANEOUS ("Miscellaneous", 12);
  
  private static Map<Integer, RecipeCategory> categoriesByMetadata = new HashMap();
  public final int blockMetadata;    
  
  RecipeCategory(String uiName, int blockMetadata)
  {
    this.blockMetadata = blockMetadata;      
    LanguageRegistry.instance().addStringLocalization(getLocalizationId(), uiName);
  }
  
  public String getLocalizationId()
  {
    return "UCRecipe." + this.name();
  }
  
  public String getUIName()
  {
    return LanguageRegistry.instance().getStringLocalization(getLocalizationId());
  }
  
  public int getBlockMetadata()
  {
    return this.blockMetadata;
  }
  
  /**
   * Retrieves the recipe category for a given block metadata value
   * @param metadata
   * @return
   */
  public static RecipeCategory getCategoryForBlockMetadata(int metadata)
  {
    if(categoriesByMetadata.isEmpty())
    {
      for(RecipeCategory category : RecipeCategory.values())
      {
        categoriesByMetadata.put(category.blockMetadata, category);
      }
    }
    
    return categoriesByMetadata.get(metadata);
  }
}