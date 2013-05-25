package com.miats.forge.modkit;

import cpw.mods.fml.common.Mod;
import java.util.*;

public class ModKit
{

	
	private List<Mod> _mods = new ArrayList<Mod>(); 
	
	// Singleton ModKit instance.
	private static ModKit _kit = null;
	
	
	private static ModKit getInstance()
	{
		if (_kit == null)
			return _kit = new ModKit();
		return _kit;
	}
	
	private void registerInternal(Object mod)
	{
		Mod modAnno = mod.getClass().getAnnotation(Mod.class);
		if (modAnno == null)
			throw new IllegalArgumentException("ModKit was asked to register a non-mod.");
		
		if (_mods.contains(modAnno))
			throw new IllegalArgumentException("This mod has already been registered with ModKit.");
		_mods.add(modAnno);
		
	}

	/**
	 * Register a Forge mod with ModKit.  This MUST be called from the mod's constructor for correct behavior.
	 * @param mod A Forge mod, if it is not annotated with @Mod and exception will be thrown.
	 */
	public static void register(Object mod)
	{
		getInstance().registerInternal(mod);
	}

}
