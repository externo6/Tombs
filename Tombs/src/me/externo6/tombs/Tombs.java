package me.externo6.tombs;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
 
 
public class Tombs extends JavaPlugin implements Listener{ //Extending JavaPlugin so that Bukkit knows its the main class...
	public final Logger logger = Logger.getLogger("minecraft");
private static Plugin plugin;
 
public void onEnable() {
	PluginDescriptionFile pdfFile = this.getDescription();
	this.logger.info("[Tombs]" + " Version " + pdfFile.getVersion() + " |" + " Developed for Banxsi.com by externo6");
    plugin = this;
    registerEvents(this, new Tomb2Essence(), new MinecartListener(), new Checkpoints(), new Tomb1Artifact());
    getCommand("tomb").setExecutor(new Checkpoints());
    getCommand("tombs").setExecutor(new Checkpoints());
}

public void onDisable() {
	PluginDescriptionFile pdfFile = this.getDescription();
	this.logger.info(pdfFile.getName() + " Has Been Disabled!");  
}


public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
    for (Listener listener : listeners) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}

//ScheduledTasks

public static void delay (final Player player)
{	
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() 
	{
		  public void run() 
		  {
	player.teleport(new Location(player.getWorld(), -395.5, 106, 1424.5, 180 , 0));
		  }
	}, 80L);
	}

public static void jump (final Player player)
{
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() 
	{
		  public void run() 
		  {
				Location loc = player.getLocation();
		  player.setVelocity(new Vector(0,3,0));
			player.playSound(loc, Sound.ENDERDRAGON_WINGS, 1, 1);
		  }
	}, 40L);
	}

public static void blindness (final Player player)
{
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()  
	{
		  public void run() 
		  {
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 55, 1));	
		  }
	}, 50L);
	}
public static void ouch (final Player player)
{
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()  
	{
		  public void run() 
		  {
			  Location loc = player.getLocation();
	player.sendMessage(ChatColor.AQUA + "Ouch!");
	loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2004);
	player.playSound(loc, Sound.FALL_BIG, 1, 1);
		  }
	}, 110L);
	}
public static void tomb2powerupjump (final Player player)
{
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() 
    {
          public void run() {
              //Location loc = player.getLocation();
              player.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + "Me" + ChatColor.GOLD + "]" + ChatColor.WHITE + ":" + ChatColor.GREEN + " Woah!");
                player.setVelocity(new Vector(0,1.50,0));   
                player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_LARGE, 0);
                player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
          }
    }, 90L);
    }
}
