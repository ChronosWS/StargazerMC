package chronosws.minecraft.ultracraft.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import chronosws.minecraft.ultracraft.client.CommonGuiContainer;

public class CommonContainerInfo
{  
  public enum SlotType
  {
    CONTAINER,
    PLAYER,
  }
  
  public class SlotInfo
  {
    private int xOffset;
    private int yOffset;
    
    
    public SlotInfo(int x, int y)
    {
      this.xOffset = x;
      this.yOffset = y;
    }
    
    public int getXOffset()
    {
      return this.xOffset;
    }
    
    public int getYOffset()
    {
      return this.yOffset;
    }
  }
    
  private String backgroundTexture; 
  private List<SlotInfo> mapping;
  private int guiWidth;
  private int guiHeight;

  private int invXOffset;
  private int invYOffset;
  
  public CommonContainerInfo(String backgroundTexture, int width, int height, int invXOffset, int invYOffset)
  {
    this.mapping = new ArrayList<SlotInfo>();
    this.backgroundTexture = backgroundTexture;
    this.guiWidth = width;
    this.guiHeight = height;
    this.invXOffset = invXOffset;
    this.invYOffset = invYOffset;
  }
  
  public int getGuiWidth()
  {
    return this.guiWidth;
  }
  
  public int getGuiHeight()
  {
    return this.guiHeight;
  }
  
  public int getInvXOffset()
  {
    return this.invXOffset;
  }
  
  public int getInvYOffset()
  {
    return this.invYOffset;
  }
  
  // Override to get a different container
  public CommonContainer getContainerInstance(InventoryPlayer invPlayer, TileEntity tileEntity)
  {
    return new CommonContainer(invPlayer, tileEntity, this);
  }
  
  // Override to get a different gui container
  public CommonGuiContainer getGuiContainerInstance(CommonContainer container)
  {
    return new CommonGuiContainer(container);
  }
  
  public String getBackgroundTexture()
  {
    return this.backgroundTexture;
  }
  
  public void addSlot(int xOffset, int yOffset)
  {
    mapping.add(new SlotInfo(xOffset, yOffset));
  }
  
  public SlotInfo get(int slot)
  {
    return mapping.get(slot);
  }
  
  public int size()
  {
    return mapping.size();
  }
}
