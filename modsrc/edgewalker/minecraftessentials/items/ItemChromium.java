package edgewalker.minecraftessentials.items;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemChromium extends Item 
{
	public ItemChromium(int itemId) 
	{
		super(itemId);		
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("Chromium");
	}
		
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("MinecraftEssentials:chromium");
	}
}
