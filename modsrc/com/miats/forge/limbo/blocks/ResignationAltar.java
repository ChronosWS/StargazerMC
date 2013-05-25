package com.miats.forge.limbo.blocks;

import com.miats.forge.common.BlockGeneric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import com.miats.forge.common.BlockGeneric.*;

@BlockName("ResignationAltar")
@BlockFriendlyName("Resignation Altar")
@BlockPreferredID(1223)
public class ResignationAltar extends BlockGeneric {

    @SideOnly(Side.CLIENT)
    private Icon workbenchIconTop;
    @SideOnly(Side.CLIENT)
    private Icon workbenchIconFront;

	public ResignationAltar() {
		super(3);
	}
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return side == 1 ? this.workbenchIconTop : (side == 0 ? Block.planks.getBlockTextureFromSide(side) : (side != 2 && side != 4 ? this.blockIcon : this.workbenchIconFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	this.blockIcon = iconRegister.registerIcon("Ultracraft:workbench_side");
        this.workbenchIconTop = iconRegister.registerIcon("Ultracraft:workbench_top");
        this.workbenchIconFront = iconRegister.registerIcon("Ultracraft:workbench_front");
    }
}
