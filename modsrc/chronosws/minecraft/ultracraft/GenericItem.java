package chronosws.minecraft.ultracraft;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GenericItem extends Item {

	public GenericItem(int initItemId) {
		super(initItemId);
		
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName("genericItem");
	}
		
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("Ultracraft:GenericItem");
	}
}
