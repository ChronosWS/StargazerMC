package chronosws.minecraft.ultracraft;

import chronosws.minecraft.ultracraft.utilities.Config.CfgBlock;
import chronosws.minecraft.ultracraft.utilities.Config.CfgDesc;
import chronosws.minecraft.ultracraft.utilities.Config.CfgItem;
import chronosws.minecraft.ultracraft.utilities.Config.CfgSource;
import chronosws.minecraft.ultracraft.utilities.Config.CfgVal;

@CfgSource(file="Ultracraft")
public class GeneralConfig
{
  @CfgBlock public int ucTableId = 500;
  @CfgItem  public int genericItemId = 5000;
  @CfgItem  public int itemPickaxeId = 5001;
  
  
  @CfgDesc(desc="The radius in which to search for machines to support the multicrafter")
  @CfgVal 
  public int multicraftSearchRadius = 3;
}
