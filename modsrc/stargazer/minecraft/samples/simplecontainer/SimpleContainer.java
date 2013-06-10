package stargazer.minecraft.samples.simplecontainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SimpleContainer extends Container
{
  private SimpleContainerTileEntity containerEntity;  
  
  public SimpleContainer(InventoryPlayer playerInv, SimpleContainerTileEntity te)
  {
    this.containerEntity = te;
    addContainerSlots();
    addPlayerSlots(playerInv);
  }
 
  public TileEntity getTileEntity()
  {
    return this.containerEntity;
  }
  
  /**
   * Texture's coordinates for the top-left corner of the container's inventory first slot.
   */
  static final int INVENTORY_LEFT = 8;
  static final int INVENTORY_TOP = 18;
  
  /**
   * Add the slots representing the container's inventory.
   */
  private void addContainerSlots()
  {
    //the Slot constructor takes the IInventory and the slot number in that it binds to
    //and the x-y coordinates it resides on-screen
    for (int slotIndex = 0; slotIndex < SampleMod.CONTAINER_SIZE; slotIndex++) 
    {      
      int xCoord = INVENTORY_LEFT + slotIndex % 3 * SampleMod.INVENTORY_SLOT_SIZE;
      int yCoord = INVENTORY_TOP + slotIndex / 3 * SampleMod.INVENTORY_SLOT_SIZE;
      addSlotToContainer(new Slot(this.containerEntity, slotIndex, xCoord, yCoord));
    }
  }
  
  /**
   * Texture's coordinates for the tops of the player's first inventory and hotbar slots.
   */
  static final int PLAYER_HOTBAR_TOP = 198;
  static final int PLAYER_INVENTORY_TOP = 140;
  
  /**
   * Add the slots representing the player's inventory.
   * @param playerInv
   */
  private void addPlayerSlots(InventoryPlayer playerInv)
  {
    int slotIndex = 0;

    // Add the slots representing the player's hotbar
    for (int i = 0; i < 9; i++, slotIndex++) 
    {
      int xCoord = INVENTORY_LEFT + i * SampleMod.INVENTORY_SLOT_SIZE;
      int yCoord = PLAYER_HOTBAR_TOP;
      addSlotToContainer(new Slot(playerInv, slotIndex, xCoord, yCoord));
    }

    // Add the slots representing the player's inventory
    for (int row = 0; row < 3; row++) 
    {
      for (int column = 0; column < 9; column++, slotIndex++) 
      {
        int xCoord = INVENTORY_LEFT + column * SampleMod.INVENTORY_SLOT_SIZE;
        int yCoord = PLAYER_INVENTORY_TOP + row * SampleMod.INVENTORY_SLOT_SIZE;
        addSlotToContainer(new Slot(playerInv, slotIndex, xCoord, yCoord));
      }
    }
  }
 
  @Override
  public boolean canInteractWith(EntityPlayer player)
  {
    return containerEntity.isUseableByPlayer(player);
  }

  /**
   * This is boilerplate inventory management code for a standard chest.  Machines and whatnot may have
   * some different rules which you would put in here.
   */
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

      // If this is in the set of inventory slots mapping to our container contents
      if (slot < SampleMod.CONTAINER_SIZE)
      {
        // Source is in the container.  Move to the player inventory
        if (!this.mergeItemStack(stackInSlot, 
                                 SampleMod.CONTAINER_SIZE, // First slot to use 
                                 SampleMod.CONTAINER_SIZE + SampleMod.PLAYER_INVENTORY_SLOTS, // Last slot to use + 1
                                 false /* fill from the beginning of the range */))
        {
          return null;
        }
      }
      else
      {
        // Source is the player's inventory.  Move to the container
        if(!this.mergeItemStack(stackInSlot, 0, SampleMod.CONTAINER_SIZE, false))
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
