package com.miats.forge.limbo;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;

public class LimboDebugCommand extends CommandBase {

	@Override
	public String getCommandName()
	{
		return "limbodebug";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] astring)
	{
		if (astring.length == 0) return;
		
		if (astring[0].equals("home")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP)sender;
				MinecraftServer mServer = MinecraftServer.getServer();
				mServer.getConfigurationManager().transferPlayerToDimension(player, 0, new LimboTeleporter(DimensionManager.getWorld(0)));
			}
		}
	}

}