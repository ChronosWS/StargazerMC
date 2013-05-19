package chronosws.minecraft.ultracraft;

import chronosws.minecraft.ultracraft.client.UltraCraftingTableGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonGuiHandler implements IGuiHandler
{
  @Override
  public Object getServerGuiElement(int id, EntityPlayer player, World world,
                  int x, int y, int z) 
  {        
    return commonGetGuiElement(id, player, world, x, y, z, false /* client */);  
  }

  @Override
  public Object getClientGuiElement(int id, EntityPlayer player, World world,
                  int x, int y, int z) 
  {
    return commonGetGuiElement(id, player, world, x, y, z, true /* client */);
  }
  
  private Object commonGetGuiElement(int id, EntityPlayer player, World world,
  int x, int y, int z, boolean client)
  {
    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if(tileEntity != null)
    {
      if(tileEntity instanceof TileEntityWithInventory)
      {
        TileEntityWithInventory invEntity = (TileEntityWithInventory)tileEntity;
        CommonGuiContainer container = new CommonGuiContainer(player.inventory, invEntity, invEntity.getSlotMap());
        
        if(client)
        {
          return new UltraCraftingTableGui(container);
        }
        
        return container;
      }
    }
    
    return null;    
  }
}
