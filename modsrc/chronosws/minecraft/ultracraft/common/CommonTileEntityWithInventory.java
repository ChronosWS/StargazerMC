package chronosws.minecraft.ultracraft.common;

import java.util.ArrayList;
import java.util.List;
import chronosws.minecraft.ultracraft.Ultracraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

//
// A TileEntity which implements IInventory
//
public class CommonTileEntityWithInventory extends TileEntity implements IInventory
{
  public final static String ENTITY_ID = "CommonEntity";
  
  private ItemStack[] inv;
  private String name;
  private String guiId;
  
  public CommonTileEntityWithInventory()
  {  
  }
  
  public CommonTileEntityWithInventory(String invName, String guiId)
  {        
    this.name = invName;
    this.guiId = guiId;
    this.inv = new ItemStack[getGuiInfo().size()];
  }
  
  public CommonContainerInfo getGuiInfo()
  {
    return Ultracraft.guis.get(guiId);
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
    return name;
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
    return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
        && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 25;
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

  private static final String TAG_INVENTORY = "_TEI_Inventory";
  private static final String TAG_INVENTORY_SLOT = "Slot";
  private static final String TAG_GUIID = "_TEI_GuiId";
  
  @Override
  public void readFromNBT(NBTTagCompound tagCompound)
  {
    super.readFromNBT(tagCompound);

    this.guiId = tagCompound.getString(TAG_GUIID);
    this.inv = new ItemStack[getGuiInfo().size()];
    
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

    tagCompound.setString(TAG_GUIID, this.guiId);
    
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
