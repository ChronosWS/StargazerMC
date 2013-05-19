package com.miats.forge.limbo;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	
	public void registerEventHooks() {
		MinecraftForge.EVENT_BUS.register(new LimboEventHandler());
	}
}
