package chronosws.minecraft.ultracraft.blocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class UltracraftRecipes
{
  public enum RecipeCategory
  {
    /**
     * Recipes for tools 
     */
    TOOLS,

    /**
     * Recipes for machines
     */
    MACHINES,
    
    /**
     *  Recipes which convert ore from raw materials into ingots and such.
     */
    ORES,
        
    /**
     *  Recipes for metal items with significant tooling, such as armor and weapons
     */
    ARMOURY,
    
    /**
     *  Recipes for construction materials
     */
    MASONRY,
    
    /**
     *  Recipes for more complex wood-based items
     */
    WOODWORKING,
    
    /**
     *  Recipes for working with wool and string to make cloth
     */
    CLOTH, 
    
    /**
     *  Recipes for jewelry
     */
    JEWELRY,

    /**
     *  Recipes for exotic/magic items such as blaze powder
     */
    EXOTIC, 

    /**
     * Recipes for food
     */
    FOOD,

    /**
     * Recipes for potions
     */
    BREWING,

    /**
     *  Recipes which do not fall into any other category
     */
    MISCELLANEOUS,    
  }
  
  
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
  
  private static final RecipeCategory defaultCategory = RecipeCategory.MISCELLANEOUS;
  private HashMap<String, RecipeCategory> defaultCreativeTabCategoryMappings;
  private HashMap<RecipeCategory, ArrayList<Recipe>> allRecipes;
  private RecipeConfig recipeConfig;
    
  public UltracraftRecipes(RecipeConfig config)
  {
    this.allRecipes = new HashMap<RecipeCategory, ArrayList<Recipe>>();
    this.defaultCreativeTabCategoryMappings = new HashMap<String, RecipeCategory>()
    {
      { 
        put("buildingBlocks", RecipeCategory.MASONRY);
        put("decorations", RecipeCategory.MISCELLANEOUS);
        put("redstone", RecipeCategory.MACHINES);
        put("transportation", RecipeCategory.MACHINES);
        put("misc", RecipeCategory.MISCELLANEOUS);
        put("food", RecipeCategory.FOOD);
        put("tools", RecipeCategory.TOOLS);
        put("combat", RecipeCategory.ARMOURY);
        put("brewing", RecipeCategory.BREWING);
        put("materials", RecipeCategory.MISCELLANEOUS);
      }
    };

    this.recipeConfig = config;
  }
  
  public void updateRecipeMappings()
  {    
    List recipes = CraftingManager.getInstance().getRecipeList();
    for(Object untypedRecipe : recipes)
    {      
      if(untypedRecipe instanceof ShapedRecipes)
      {
        ShapedRecipes shaped = (ShapedRecipes) untypedRecipe;
        ItemStack recipeOutput = shaped.getRecipeOutput();
        Recipe recipe = new Recipe(getMappedRecipeCategory(recipeOutput), recipeOutput.itemID, recipeOutput.stackSize);
        for(ItemStack item : shaped.recipeItems)
        { 
          if(item == null)
          {
            continue;
          }
          
          recipe.addRequirement(item.itemID, item.stackSize);
        }
        
        addRecipe(recipe);        
      }
      else if(untypedRecipe instanceof ShapelessRecipes)
      {
        ShapelessRecipes shapeless = (ShapelessRecipes) untypedRecipe;
        ItemStack recipeOutput = shapeless.getRecipeOutput();
        Recipe recipe = new Recipe(getMappedRecipeCategory(recipeOutput), recipeOutput.itemID, recipeOutput.stackSize);
        for(Object item : shapeless.recipeItems)
        {
          if(item == null)
          {
            continue;
          }
          
          // For some reason shapeless recipes returns an untyped list of items
          // rather than a typed one, but looking at ShapelessRecipes it's clear 
          // they are all of the same type.
          ItemStack stack = (ItemStack) item;
          recipe.addRequirement(stack.itemID, stack.stackSize);
        }
      }
      else       
      {
        // One of the special types only implementing IRecipe
      }
    }
  }
  
  /**
   * From a specified recipe output, determines the appropriate category based on some
   * default mappings or override mappings provided by the configuration.
   * @param recipeOutput The primary item produced by the recipe
   * @return The category in which this recipe should be placed
   */
  private RecipeCategory getMappedRecipeCategory(ItemStack recipeOutput)
  {    
    // Determine if the config has a specific mapping for this item.  That has the highest
    // precedence.
    RecipeCategory configCategory = this.recipeConfig.getCategoryForOutput(recipeOutput);
    
    // Try to determine the category based on the creative tab configuration
    if(configCategory == null)
    {
      CreativeTabs tab = Item.itemsList[recipeOutput.itemID].getCreativeTab();  
      
      // Does the config have a mapping for all items in this creative tab?
      configCategory = this.recipeConfig.getCategoryForCreativeTab(tab);
      
      // If there is no mapping in the config, use the default mapping.
      if(configCategory == null)
      {
        configCategory = this.defaultCreativeTabCategoryMappings.get(tab.getTabLabel());
      }      
    }
    
    // If there is no other categorization, return the global default
    if(configCategory == null)
    {
      configCategory = defaultCategory;
    }
    
    return configCategory;
  }
  
  private void addRecipe(Recipe recipe)
  {
    ArrayList<Recipe> recipesForTab = this.allRecipes.get(recipe.getCategory());
    if(recipesForTab == null)
    {
      recipesForTab = new ArrayList<Recipe>();
      this.allRecipes.put(recipe.getCategory(), recipesForTab);
    }
    
    recipesForTab.add(recipe);    
  }

  public ArrayList<Recipe> recipesCraftableWithInputs(CreativeTabs category, Collection<ItemStack> inputs)
  {
    return null;
  }
}
