package stargazer.minecraft.samples.noachievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.stats.Achievement;

/**
 * Simple replacement for the GuiAchievement class which eliminates the showing of the
 * achievement notification window.
 * 
 * @author Cliff
 *
 */
@SideOnly(Side.CLIENT)
public class NoAchievement extends GuiAchievement
{
  public NoAchievement(Minecraft par1Minecraft)
  {
    super(par1Minecraft);
  }
  
  @Override
  public void queueTakenAchievement(Achievement par1Achievement)
  {  
  }

  @Override
  public void queueAchievementInformation(Achievement par1Achievement)
  {    
  }
}
