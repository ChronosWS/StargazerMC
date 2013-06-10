package stargazer.minecraft.samples.simplecontainer;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

/**
 * This class represents the client-side mechanism by which GUIs for containers are drawn.
 * @author Cliff
 *
 */
@SideOnly(Side.CLIENT)
public class SimpleGuiContainer extends GuiContainer
{
  public SimpleGuiContainer(Container par1Container)
  {
    super(par1Container);
    // TODO Auto-generated constructor stub
  }

  /**
   * Things which need to be drawn on top of the background and container items, 
   * like descriptive text, counters, etc. should be drawn here.
   */
  @Override
  protected void drawGuiContainerForegroundLayer(int x, int y)
  { 
    super.drawGuiContainerForegroundLayer(x,  y);
    
    //draw text and stuff here
    //the parameters for drawString are: string, x, y, color
    fontRenderer.drawString("Sample", 8, 6, 4210752);
    //draws "Inventory" or your regional equivalent
    fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
  }
  
  /**
   * Things which need to be drawn behind the container items should be drawn here.
   */
  @Override
  protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
  {    
    //draw your Gui here, only thing you need to change is the path
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.renderEngine.bindTexture("/mods/SampleSimpleContainer/textures/gui/container.png");
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
  }
}
