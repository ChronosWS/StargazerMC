package edgewalker.minecraftessentials.items;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSilverOre extends Item 
{
	public ItemSilverOre(int itemId) 
	{
		super(itemId);		
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("Silver Ore");
	}
		
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("MinecraftEssentials:oreSilver");
	}
}
