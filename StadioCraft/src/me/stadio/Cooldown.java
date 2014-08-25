package me.stadio;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cooldown implements CommandExecutor
{
	//Hallo
	private Map<String, Long> lastUsage = new HashMap<String, Long>();
	private final int cdtime = 18;
	
	@Override
	public boolean onCommand(CommandSender arg0, Command command, String label, String[] args)
	{
		if ((arg0 instanceof Player) == false)
		{
			return false;
		}
		
		Player player = (Player) arg0;
		
		long lastUsed = 0;
		if (lastUsage.containsKey(player.getName()))
		{
			lastUsed = lastUsage.get(player.getName());
		}
		
		int cdmillis = cdtime * 1000;
		
		if (System.currentTimeMillis()-lastUsed>=cdmillis)
		{
			player.sendMessage("Okay! Command run!");
			lastUsage.put(player.getName(), System.currentTimeMillis());
		}
		else
		{
			int timeLeft = (int) (cdtime-((System.currentTimeMillis()-lastUsed)/1000));
			player.sendMessage("This command is on cooldown for you for another " + timeLeft + "seconds.");
		}
		return false;
	}
	
}