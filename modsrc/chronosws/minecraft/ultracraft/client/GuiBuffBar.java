package chronosws.minecraft.ultracraft.client;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiBuffBar extends Gui
{
  private Minecraft mc;

  public GuiBuffBar(Minecraft mc)
  {
    super();
    this.mc = mc;
  }

  //
  // This event is called by GuiIngameForge during each frame by
  // GuiIngameForge.pre() and GuiIngameForce.post().
  //
  @ForgeSubscribe(priority = EventPriority.HIGHEST)
  public void onRenderHotbar(RenderGameOverlayEvent event)
  {
    // 
    // We draw after the hotbar has drawn.  
    //
    if(event.isCancelable() || event.type != ElementType.HOTBAR)
    {
      return;
    }

    int i = 2;
    int j = 2;
    Collection collection = this.mc.thePlayer.getActivePotionEffects();
    if (!collection.isEmpty())
    {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(GL11.GL_LIGHTING);
      int k = 20;

      for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects()
          .iterator(); iterator.hasNext(); i += k)
      {
        PotionEffect potioneffect = (PotionEffect) iterator.next();
        Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/inventory.png");
        // this.drawTexturedModalRect(i, j, 0, 166, 140, 32);

        if (potion.hasStatusIcon())
        {
          int l = potion.getStatusIconIndex();
          this.drawTexturedModalRect(i, j, 0 + l % 8 * 18, 198 + l / 8 * 18,
              18, 18);
        }

        /*
         * String s = StatCollector.translateToLocal(potion.getName());
         * if (potioneffect.getAmplifier() == 1)
         * {
         * s = s + " II";
         * }
         * else if (potioneffect.getAmplifier() == 2)
         * {
         * s = s + " III";
         * }
         * else if (potioneffect.getAmplifier() == 3)
         * {
         * s = s + " IV";
         * }
         * this.fontRenderer.drawStringWithShadow(s, i + 10 + 18, j + 6,
         * 16777215);
         * String s1 = Potion.getDurationString(potioneffect);
         * this.fontRenderer.drawStringWithShadow(s1, i + 10 + 18, j + 6 + 10,
         * 8355711);
         */
      }
    }
  }
}
