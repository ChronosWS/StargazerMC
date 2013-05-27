package chronosws.minecraft.ultracraft.client;

import java.nio.FloatBuffer;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
import chronosws.minecraft.ultracraft.blocks.UltracraftRecipes.RecipeCategory;
import chronosws.minecraft.ultracraft.common.CommonContainer;

public class UltracraftingGuiContainer extends CommonGuiContainer
{  
  private class CategoryInfo
  {
    public Point categoryLocation;
    public ItemStack itemStack;
    public boolean isAvailable;
    
    public CategoryInfo(Point location, ItemStack stack)
    {
      this.categoryLocation = location;
      this.itemStack = stack;
      this.isAvailable = true;
    }
  };
  
  private HashMap<RecipeCategory, CategoryInfo> categoryInfos;    
  private RecipeCategory selectedCategory;  
  private UltracraftingContainerInfo containerInfo;
  public UltracraftingGuiContainer(CommonContainer container)
  {
    super(container);
    this.containerInfo = (UltracraftingContainerInfo) container.getGuiInfo();
    HashMap<RecipeCategory, ItemStack> categoryItems = new HashMap()
    {
      {
        put(RecipeCategory.ARMOURY, new ItemStack(Item.plateIron));
        put(RecipeCategory.BREWING, new ItemStack(Item.potion));
        put(RecipeCategory.CLOTH, new ItemStack(Item.silk));
        put(RecipeCategory.EXOTIC, new ItemStack(Item.blazeRod));
        put(RecipeCategory.FOOD, new ItemStack(Item.chickenCooked));
        put(RecipeCategory.JEWELRY, new ItemStack(Item.emerald));
        put(RecipeCategory.MACHINES, new ItemStack(Block.furnaceIdle));
        put(RecipeCategory.MASONRY, new ItemStack(Block.brick));
        put(RecipeCategory.MISCELLANEOUS, new ItemStack(Item.compass));
        put(RecipeCategory.ORES, new ItemStack(Item.ingotIron));
        put(RecipeCategory.TOOLS, new ItemStack(Item.pickaxeIron));
        put(RecipeCategory.WOODWORKING, new ItemStack(Item.boat));
      }
    };
    
    this.categoryInfos = new HashMap();
    int categoryIndex = 0;
    for(RecipeCategory category : RecipeCategory.values())
    {
      this.categoryInfos.put(category, new CategoryInfo(getCategoryLocation(categoryIndex), categoryItems.get(category)));
      categoryIndex++;
    }
          
    this.selectedCategory = RecipeCategory.TOOLS;
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int x, int y)
  {
    super.drawGuiContainerForegroundLayer(x, y);
    
    drawRecipesForSelectedCategory();    
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
  {
    super.drawGuiContainerBackgroundLayer(f, i, j);
    drawCategoryTabs();
  }

  private RecipeCategory getHoverCategory(int mouseX, int mouseY)
  {
    for(RecipeCategory category : RecipeCategory.values())
    {
      CategoryInfo info = this.categoryInfos.get(category);
      if(mouseX < info.categoryLocation.getX() || 
         mouseX > info.categoryLocation.getX() + CATEGORY_COLUMN_WIDTH ||
         mouseY < info.categoryLocation.getY() ||
         mouseY > info.categoryLocation.getY() + CATEGORY_COLUMN_WIDTH)
      {
        continue;
      }
      
      return category;
    }
    
    return null;
  }
  
  @Override
  protected void mouseMovedOrUp(int mouseX, int mouseY, int mouseEventType)
  {
    RecipeCategory hoverCategory = getHoverCategory(mouseX, mouseY);
    if(hoverCategory != null)
    {
      if(mouseEventType == MOUSE_EVENT_MOVE)
      {
        
      }
      else
      {
        this.selectedCategory = hoverCategory;
      }
    }
  }
  
  private void drawRecipesForSelectedCategory()
  {
  }

  private static final int CATEGORY_COLUMN_WIDTH = 20;
  private static final int CATEGORY_COLUMN_TOP = 30;
  private static final int CATEGORY_COLUMN_LEFT = 30;
  private static final int CATEGORY_COLUMNS = 2;
  private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);
  private static final float CATEGORY_SELECTED_BRIGHTNESS = 0.6F;
  private static final float CATEGORY_UNSELECTED_BRIGHTNESS = 0.3F;
  private static final int SELECTION_BOX_OFFSET = 3;
  private static final int UNAVAILABLE_BOX_OFFSET = 1;
  private static final int BACKGROUND_BOX_OFFSET = 2;
  
  private void drawCategoryTabs()
  {
    RenderHelper.enableGUIStandardItemLighting();
    int categoryIndex = 0;
    for(RecipeCategory category : RecipeCategory.values())
    {        
      CategoryInfo info = this.categoryInfos.get(category);
      int x = info.categoryLocation.getX();
      int y = info.categoryLocation.getY();
      
      this.mc.renderEngine.bindTexture(outerContainer.getGuiInfo().getBackgroundTexture());
      RenderHelper.disableStandardItemLighting();

      // Draw the background for the category
      this.drawTexturedModalRect(x - BACKGROUND_BOX_OFFSET, y - BACKGROUND_BOX_OFFSET, 
          containerInfo.CATEGORY_BACKGROUND_U, containerInfo.CATEGORY_BACKGROUND_V, 
          containerInfo.CATEGORY_BACKGROUND_W, containerInfo.CATEGORY_BACKGROUND_H);
      
      // Draw the category item/block
      RenderHelper.enableGUIStandardItemLighting();
      ItemStack itemStack = info.itemStack;
      itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, itemStack, x, y);
      itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, itemStack, x, y);
      
      // Dim out any categories which are not available.
      if(!info.isAvailable)
      {
        this.mc.renderEngine.bindTexture(outerContainer.getGuiInfo().getBackgroundTexture());
        RenderHelper.disableStandardItemLighting();

        GL11.glEnable(GL11.GL_BLEND);
        this.drawTexturedModalRect(x - UNAVAILABLE_BOX_OFFSET, y - UNAVAILABLE_BOX_OFFSET, 
            containerInfo.CATEGORY_UNAVAILABLE_U, containerInfo.CATEGORY_UNAVAILABLE_V, 
            containerInfo.CATEGORY_UNAVAILABLE_W, containerInfo.CATEGORY_UNAVAILABLE_H);
        GL11.glDisable(GL11.GL_BLEND);       
      }

      // Draw the selection rectangle if necessary
      if(category == this.selectedCategory)
      {        
        this.mc.renderEngine.bindTexture(outerContainer.getGuiInfo().getBackgroundTexture());
        RenderHelper.disableStandardItemLighting();

        GL11.glEnable(GL11.GL_BLEND);
        this.drawTexturedModalRect(x - SELECTION_BOX_OFFSET, y - SELECTION_BOX_OFFSET, 
            containerInfo.SELECTED_CATEGORY_U, containerInfo.SELECTED_CATEGORY_V, 
            containerInfo.SELECTED_CATEGORY_W, containerInfo.SELECTED_CATEGORY_H);
        GL11.glDisable(GL11.GL_BLEND);       
      }

      categoryIndex++;
    }    
  }
  
  private Point getCategoryLocation(int index)
  {
    int x = CATEGORY_COLUMN_LEFT + CATEGORY_COLUMN_WIDTH * (index % CATEGORY_COLUMNS);
    int y = CATEGORY_COLUMN_TOP + CATEGORY_COLUMN_WIDTH * (index / 2);
    return new Point(x, y);
  }
  
  private void setItemLighting(float brightness)
  {
    FloatBuffer colorBuffer = setColorBuffer(brightness, brightness, brightness, 1.0F);
    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, colorBuffer);
    
    brightness = brightness * 0.66F;
    colorBuffer = setColorBuffer(brightness, brightness, brightness, 1.0F);
    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, colorBuffer);
    GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, colorBuffer);
    GL11.glColor4f(brightness, brightness, brightness, 1.0F);
  }
  
  private static FloatBuffer setColorBuffer(float par0, float par1, float par2, float par3)
  {
      colorBuffer.clear();
      colorBuffer.put(par0).put(par1).put(par2).put(par3);
      colorBuffer.flip();
      return colorBuffer;
  }
}
