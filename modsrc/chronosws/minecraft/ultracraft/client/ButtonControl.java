package chronosws.minecraft.ultracraft.client;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public class ButtonControl extends GuiButton
{
  protected static RenderItem itemRenderer = new RenderItem();
  protected ItemStack item;
  
  public ButtonControl(int id, int xPos, int yPos, String label)
  {
    super(id, xPos, yPos, label);
  }

  public ButtonControl(int id, int xPos, int yPos, int width, int height,
      String label)
  {
    super(id, xPos, yPos, width, height, label);
    // TODO Auto-generated constructor stub
  }

  public ButtonControl(int id, int xPos, int yPos, int width, int height,
      ItemStack item)
  {
    super(id, xPos, yPos, width, height, "");
    this.item = item;
  }

  private static final int ITEMSIZE = 18;
  private static final int DISABLED_COLOR = -2147483648;
  
  @Override
  public void drawButton(Minecraft minecraft, int xPos, int yPos)
  {
    super.drawButton(minecraft, xPos, yPos);
    
    if(this.item != null)
    {
      int xOffset = (this.width - ITEMSIZE) / 2 + 1;
      int yOffset = (this.height - ITEMSIZE) / 2;
      int itemX = this.xPosition + xOffset;// + this.width/2 - ITEMSIZE/2;
      int itemY = this.yPosition + yOffset;// - this.height/2 - ITEMSIZE/2;
      RenderHelper.enableGUIStandardItemLighting();      
      itemRenderer.renderItemAndEffectIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, this.item, itemX, itemY);
      itemRenderer.renderItemOverlayIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, this.item, itemX, itemY);
      RenderHelper.disableStandardItemLighting();
      
      if(!this.enabled)
      {       
        GL11.glEnable(GL11.GL_BLEND);
        this.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, DISABLED_COLOR);
        GL11.glDisable(GL11.GL_BLEND);       
      }
    }
  }
}
