package edgewalker.minecraftessentials.items;


import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSteelMix extends Item 
{
	public ItemSteelMix(int itemId) 
	{
		super(itemId);		
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("Steel Mix");
	}
		
  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
  {
      par3List.add("Smelts into steel ingots");
  }
    
	
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("MinecraftEssentials:steelMix");
	}
}
