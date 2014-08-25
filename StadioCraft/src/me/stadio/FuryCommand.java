package me.stadio;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FuryCommand implements CommandExecutor
{
	
	private Map<String, Long> lastUsage = new HashMap<String, Long>();
	private final int cdtime = Main.plugin.getConfig().getInt("Fury CooldownTime");

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
	{

		if ((arg0 instanceof Player) == false)
		{
			return false;
		}

		Player player = (Player) arg0;
		if (player.hasPermission("stadio.fury"))
		{
			long lastUsed = 0;
			if (lastUsage.containsKey(player.getName()))
			{
				lastUsed = lastUsage.get(player.getName());
			}
			
			int cdmillis = cdtime * 1000;
			
			if (System.currentTimeMillis()-lastUsed>=cdmillis)
			{
				player.sendMessage(ChatColor.GREEN + Main.plugin.getConfig().getString("Fury Message"));
				lastUsage.put(player.getName(), System.currentTimeMillis());
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 3));
			}
			else
			{
				int timeLeft = (int) (cdtime-((System.currentTimeMillis()-lastUsed)/1000));
				player.sendMessage(ChatColor.RED + "You need to wait " + ChatColor.RED + timeLeft + ChatColor.RED + " seconds before you can use that command again.");
			}
			
		} else
		{
			player.sendMessage(ChatColor.RED + "You don't have access to that command!");
		}
		return false;
	}

}
