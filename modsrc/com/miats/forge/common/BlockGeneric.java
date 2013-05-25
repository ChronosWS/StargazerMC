package com.miats.forge.common;

import java.lang.annotation.*;
import java.lang.reflect.Field;

import net.minecraft.block.Block;



public class BlockGeneric extends Block {
	
	// Sets the internal name of the block.
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE})
	public static @interface BlockName
	{
	    public String value();
	}
	
	// Sets the friendly name of the block.
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE})
	public static @interface BlockFriendlyName
	{
	    public String value();
	}
	
	// Sets the preferred ID of the block.
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE})
	public static @interface BlockPreferredID
	{
	    public int value();
	}
	
	public BlockGeneric(int blockID) {
		super(blockID, null);
		
	}
	
	public void registerBlock() {

		BlockName name = getClass().getAnnotation(BlockName.class);
		BlockFriendlyName fname = getClass().getAnnotation(BlockFriendlyName.class);
		
		if (name != null) {
			//LanguageRegistry.
		}
		
	}
	
	public static int getBlockID(Class blockClass) {
		
		BlockPreferredID id = (BlockPreferredID)blockClass.getAnnotation(BlockPreferredID.class); 
		if (id != null)
			return id.value();
		else
			return -1;
	}
	
	

}
