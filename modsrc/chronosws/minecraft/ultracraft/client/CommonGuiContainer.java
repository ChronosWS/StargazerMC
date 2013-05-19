package chronosws.minecraft.ultracraft.client;

import org.lwjgl.opengl.GL11;
import chronosws.minecraft.ultracraft.common.CommonContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class CommonGuiContainer extends GuiContainer
{
  private CommonContainer outerContainer;
  public CommonGuiContainer(CommonContainer container)
  {
    super(container);
    this.outerContainer = container; 
    this.xSize = container.getGuiInfo().getGuiWidth();
    this.ySize = container.getGuiInfo().getGuiHeight();
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int x, int y)
  { 
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
}
