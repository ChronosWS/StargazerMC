package stargazer.minecraft.samples.simplecontainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chronosws.minecraft.ultracraft.client.CommonGuiContainer;
import cpw.mods.fml.common.network.IGuiHandler;


/**
 * CommonGuiHandler - This class is responsible for handling network events related
 *                    to Guis, such as for containers.
 */
public class SimpleGuiHandler implements IGuiHandler
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
    if(id != SampleMod.GUI_ID)
    {
      // Not one of ours
      return null;
    }
      
    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    if(tileEntity != null)
    {
      if(tileEntity instanceof SimpleContainerTileEntity)
      {
        SimpleContainer container = new SimpleContainer(player.inventory, (SimpleContainerTileEntity)tileEntity);
        if(client)
        {
          return new SimpleGuiContainer(container);
        }

        return container;
      }
    }
    
    return null;    
  }
}
