package stargazer.minecraft.samples.simplecontainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

//
// A TileEntity which implements IInventory
//
public class SimpleContainerTileEntity extends TileEntity implements IInventory
{  
  private ItemStack[] inv;
  private static final float USE_RADIUS_SQUARED = 25; // 5 block use radius
  private static final String NAME = "SimpleContainer";
  
  public SimpleContainerTileEntity()
  {  
    this.inv = new ItemStack[SampleMod.CONTAINER_SIZE];
  }
    
  @Override
  public int getSizeInventory()
  {
    return inv.length;
  }

  @Override
  public ItemStack getStackInSlot(int slot)
  {
    return inv[slot];
  }

  @Override
  public ItemStack decrStackSize(int slot, int amt)
  {
    ItemStack stack = getStackInSlot(slot);
    if (stack != null)
    {
      if (stack.stackSize <= amt)
      {
        setInventorySlotContents(slot, null);
      }
      else
      {
        stack = stack.splitStack(amt);
        if (stack.stackSize == 0)
        {
          setInventorySlotContents(slot, null);
        }
      }
    }
    return stack;  
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int slot)
  {
    ItemStack stack = getStackInSlot(slot);
    if (stack != null)
    {
      setInventorySlotContents(slot, null);
    }

    return stack;
  }

  @Override
  public void setInventorySlotContents(int slot, ItemStack stack)
  {
    inv[slot] = stack;
    if (stack != null && stack.stackSize > getInventoryStackLimit())
    {
      stack.stackSize = getInventoryStackLimit();
    }
  }

  @Override
  public String getInvName()
  {
    return NAME;
  }

  @Override
  public boolean isInvNameLocalized()
  {
    return false;
  }

  @Override
  public int getInventoryStackLimit()
  {
    return 64;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player)
  {
    return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
           player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < USE_RADIUS_SQUARED;
  }

  @Override
  public void openChest()
  {
  }

  @Override
  public void closeChest()
  {
  }

  @Override
  public boolean isStackValidForSlot(int i, ItemStack itemstack)
  {
    return true;
  }

  // NBT Tag names we will use for this entity.
  private static final String TAG_INVENTORY = "Inventory";
  private static final String TAG_INVENTORY_SLOT = "Slot";
  
  @Override
  public void readFromNBT(NBTTagCompound tagCompound)
  {
    super.readFromNBT(tagCompound);

    this.inv = new ItemStack[SampleMod.CONTAINER_SIZE];
    
    NBTTagList tagList = tagCompound.getTagList(TAG_INVENTORY);
    for (int i = 0; i < tagList.tagCount(); i++)
    {
      NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
      byte slot = tag.getByte(TAG_INVENTORY_SLOT);
      if (slot >= 0 && slot < inv.length)
      {
        inv[slot] = ItemStack.loadItemStackFromNBT(tag);
      }
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound tagCompound)
  {
    super.writeToNBT(tagCompound);
    
    NBTTagList itemList = new NBTTagList();    
    for (int i = 0; i < inv.length; i++)
    {
      ItemStack stack = inv[i];
      if (stack != null)
      {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setByte(TAG_INVENTORY_SLOT, (byte) i);
        stack.writeToNBT(tag);
        itemList.appendTag(tag);
      }
    }
    
    tagCompound.setTag(TAG_INVENTORY, itemList);    
  }
}
