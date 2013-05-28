package chronosws.minecraft.ultracraft.common;

import chronosws.minecraft.ultracraft.Constants;
import chronosws.minecraft.ultracraft.common.CommonContainerInfo.SlotInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class CommonContainer extends Container
{
  private TileEntity containerEntity;  
  private IInventory containerInventory;
  private String backgroundTexture;
  private CommonContainerInfo guiInfo;
  private int playerHotBarFirst;
  private int playerInventoryFirst;
  
  public CommonContainer(
      InventoryPlayer playerInv, 
      TileEntity te, 
      CommonContainerInfo guiInfo)
  {
    this.containerEntity = te;
    this.containerInventory = (IInventory) this.containerEntity;
    this.guiInfo = guiInfo;
    
    bindInventories(playerInv);
  }

  public CommonContainerInfo getGuiInfo()
  {
    return this.guiInfo;
  }
  
  public TileEntity getTileEntity()
  {
    return this.containerEntity;
  }
 
  public IInventory getContainerInventory()
  {
    return this.containerInventory;
  }
  
  private void bindInventories(InventoryPlayer playerInv)
  {
    //
    // Bind container inventory
    //
    for (int i = 0; i < guiInfo.size(); i++)
    {
      SlotInfo slotInfo = guiInfo.get(i);
      addSlotToContainer(new Slot(this.containerInventory, i,
          slotInfo.getXOffset(), slotInfo.getYOffset()));
    }

    this.playerHotBarFirst = guiInfo.size();
    
    //
    // Player hotbar
    //
    for (int i = 0; i < Constants.PLAYER_HOTBAR_COUNT; i++)
    {
      int xOffset = guiInfo.getInvXOffset() + i * Constants.STD_INVENTORY_CELL_SIZE;
      final int yOffset = guiInfo.getInvYOffset() + Constants.STD_INVENTORY_CELL_SIZE * Constants.STD_INVENTORY_ROWS + Constants.STD_INVENTORY_HOTBAR_MARGIN;
      addSlotToContainer(new Slot(playerInv, i, xOffset, yOffset));
    }

    this.playerInventoryFirst = this.playerHotBarFirst + Constants.PLAYER_HOTBAR_COUNT;
    
    //
    // Player inventory
    //
    for (int row = 0; row < 3; row++)
    {
      for (int col = 0; col < 9; col++)
      {
        int srcIndex = col + row * 9 + 9;
        int xOffset = guiInfo.getInvXOffset() + col * Constants.STD_INVENTORY_CELL_SIZE;
        int yOffset = guiInfo.getInvYOffset() + row * Constants.STD_INVENTORY_CELL_SIZE;
        addSlotToContainer(new Slot(playerInv, 
            srcIndex, 
            xOffset,
            yOffset));
      }
    }
  }
 
  @Override
  public boolean canInteractWith(EntityPlayer player)
  {
    return containerInventory.isUseableByPlayer(player);
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer player, int slot)
  {
    ItemStack stack = null;
    Slot slotObject = (Slot) inventorySlots.get(slot);

    // null checks and checks if the item can be stacked (maxStackSize > 1)
    if (slotObject != null && slotObject.getHasStack())
    {
      ItemStack stackInSlot = slotObject.getStack();
      stack = stackInSlot.copy();


      if (slot < this.playerHotBarFirst)
      {
        // Source is in the container.  Move to the player inventory or hotbar
        if (!this.mergeItemStack(stackInSlot, this.playerInventoryFirst, this.playerInventoryFirst + Constants.PLAYER_INVENTORY_COUNT, true))
        {
          return null;
        }
      }
      else if(slot >= this.playerHotBarFirst && slot < this.playerInventoryFirst)
      {
        // Source is the hot bar.  Move to the container.
        if(!this.mergeItemStack(stackInSlot, 0, this.playerHotBarFirst, true))
        {
          return null;
        }
      }
      else
      {
        // Source is the player inventory.  Move to the container.
        if(!this.mergeItemStack(stackInSlot, 0, this.playerHotBarFirst, true))
        {
          return null;
        }
      }

      // Notify the slot of any changes
      if (stackInSlot.stackSize == 0)
      {
        slotObject.putStack(null);
      }
      else
      {
        slotObject.onSlotChanged();
      }

      if (stackInSlot.stackSize == stack.stackSize)
      {
        return null;
      }
      
      slotObject.onPickupFromSlot(player, stackInSlot);
    }

    return stack;
  }
}
