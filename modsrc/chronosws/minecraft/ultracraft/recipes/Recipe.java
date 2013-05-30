package chronosws.minecraft.ultracraft.recipes;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a recipe for a single item. 
 * @author Cliff
 *
 */
public class Recipe
{
  private RecipeCategory category;
  private HashMap<Integer, Integer> recipeItems;
  private int productItemId;
  private int productQuantity;
  
  public Recipe(RecipeCategory category, int productItemId, int productQuantity)
  {
    this.productItemId = productItemId;
    this.productQuantity = productQuantity;
    this.recipeItems = new HashMap<Integer, Integer>(5);
    this.category = category;
  }    
  
  public void addRequirement(int itemId)
  {    
    addRequirement(itemId, 1);
  }
  
  public void addRequirement(int itemId, int quantity)
  {
    Integer currentCount = recipeItems.get(itemId);
    if(currentCount == null)
    {
      currentCount = quantity;
    }
    else
    {
      currentCount += quantity;
    }
          
    this.recipeItems.put(itemId, currentCount);
  }
  
  public Map<Integer, Integer> getRecipeItems() { return this.recipeItems; }
  public RecipeCategory getCategory() { return this.category; }
  public int getProductItemId() { return this.productItemId; }
  public int getProductQuantity() { return this.productQuantity; }
}