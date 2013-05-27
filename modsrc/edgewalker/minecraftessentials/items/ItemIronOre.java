package edgewalker.minecraftessentials.items;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemIronOre extends Item 
{
	public ItemIronOre(int itemId) 
	{
		super(itemId);		
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("Iron Ore");
	}
		
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("MinecraftEssentials:oreIron");
	}
}
