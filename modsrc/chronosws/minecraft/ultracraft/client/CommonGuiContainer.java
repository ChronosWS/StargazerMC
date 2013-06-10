package chronosws.minecraft.ultracraft.client;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import chronosws.minecraft.ultracraft.common.CommonContainer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class CommonGuiContainer extends GuiContainer
{
  public static final int MOUSE_EVENT_MOVE = -1;
  
  protected GuiButton selectedButton;
  
  protected CommonContainer outerContainer;
  public CommonGuiContainer(CommonContainer container)
  {
    super(container);
    this.outerContainer = container; 
    this.xSize = container.getGuiInfo().getGuiWidth();
    this.ySize = container.getGuiInfo().getGuiHeight();
  }

  public int getContainerWidth()
  {
    return outerContainer.getGuiInfo().getGuiWidth();    
  }

  public int getContainerHeight()
  {
    return outerContainer.getGuiInfo().getGuiHeight();    
  }

  public int getContainerLeft()
  {
    return (width - outerContainer.getGuiInfo().getGuiWidth()) / 2;    
  }

  public int getContainerTop()
  {
    return (height - outerContainer.getGuiInfo().getGuiHeight()) / 2;
  }
  
  @Override
  protected void drawGuiContainerForegroundLayer(int x, int y)
  { 
    super.drawGuiContainerForegroundLayer(x,  y);
    
    //draw text and stuff here
    //the parameters for drawString are: string, x, y, color
    fontRenderer.drawString("Ultracraft", 8, 6, 4210752);
    //draws "Inventory" or your regional equivalent
    fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
  }
  
  @Override
  protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
  {    
    //draw your Gui here, only thing you need to change is the path
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.renderEngine.bindTexture(outerContainer.getGuiInfo().getBackgroundTexture());
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
  }

  /**
   * Because GuiContainer overrides drawScreen and does not invoke the superclass version, we have
   * to do it ourselves to get the proper handling
   */
  @Override
  public void drawScreen(int par1, int par2, float par3)
  {
    super.drawScreen(par1, par2, par3);

    for (int k = 0; k < this.buttonList.size(); ++k)
    {
        GuiButton guibutton = (GuiButton)this.buttonList.get(k);
        guibutton.drawButton(this.mc, par1, par2);
    }    
  }

  /**
   * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
   * mouseMove, which==0 or which==1 is mouseUp
   */
  @Override
  protected void mouseMovedOrUp(int par1, int par2, int par3)
  {
      if (this.selectedButton != null && par3 == 0)
      {
          this.selectedButton.mouseReleased(par1, par2);
          this.selectedButton = null;
      }
      
      super.mouseMovedOrUp(par1, par2, par3);
  }
}
