package chronosws.minecraft.ultracraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chronosws.minecraft.ultracraft.client.CommonGuiContainer;
import cpw.mods.fml.common.network.IGuiHandler;


//
// CommonGuiHandler - This class is responsible for handling network events related
//                    to Guis, such as for containers.
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
      if(tileEntity instanceof CommonTileEntityWithInventory)
      {
        CommonTileEntityWithInventory invEntity = (CommonTileEntityWithInventory)tileEntity;       
        CommonContainer container = invEntity.getGuiInfo().getContainerInstance(player.inventory, invEntity);
        
        if(client)
        {
          return invEntity.getGuiInfo().getGuiContainerInstance(container);
        }
        
        return container;
      }
    }
    
    return null;    
  }
}
