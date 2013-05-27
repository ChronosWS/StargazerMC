package chronosws.minecraft.ultracraft.client;

import chronosws.minecraft.ultracraft.common.CommonContainer;
import chronosws.minecraft.ultracraft.common.CommonContainerInfo;

public class UltracraftingContainerInfo extends CommonContainerInfo
{
  public static final int SELECTED_CATEGORY_U = 176;
  public static final int SELECTED_CATEGORY_V = 0;
  public static final int SELECTED_CATEGORY_W = 22;
  public static final int SELECTED_CATEGORY_H = 22;

  public static final int CATEGORY_BACKGROUND_U = 176;
  public static final int CATEGORY_BACKGROUND_V = 22;
  public static final int CATEGORY_BACKGROUND_W = 20;
  public static final int CATEGORY_BACKGROUND_H = 20;

  public static final int CATEGORY_UNAVAILABLE_U = 176;
  public static final int CATEGORY_UNAVAILABLE_V = 42;
  public static final int CATEGORY_UNAVAILABLE_W = 18;
  public static final int CATEGORY_UNAVAILABLE_H = 18;

  public UltracraftingContainerInfo()
  {
    super("/mods/Ultracraft/textures/gui/ultracraftingtable.png", 
          176, 222, 
          8, 140);
    for (int i = 0; i < 6; i++)
    {
      for (int j = 0; j < 9; j++)
      {
        addSlot(8 + j * 18, 18 + i * 18);
      }
    }
  }

  @Override
  public CommonGuiContainer getGuiContainerInstance(CommonContainer container)
  {
    return new UltracraftingGuiContainer(container);
  } 
}
