package me.externo6.tombs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.potion.PotionEffectType;

public class Checkpoints implements Listener, CommandExecutor{
	static File respawnFile = new File("./plugins/Tombs", "Respawns.yml");
    public static FileConfiguration respawns = YamlConfiguration.loadConfiguration(respawnFile);
	static File checkpointFile = new File("./plugins/Tombs", "Checkpoint.yml");
	public static FileConfiguration checkpoint = YamlConfiguration.loadConfiguration(checkpointFile);
	PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("TombsReloaded").getDescription();
	
	
	
	  @EventHandler
	  public void onPlayerRespawn(PlayerRespawnEvent event)
	  {
		final Player player = event.getPlayer();
	    //Player player = event.getPlayer();
	    if(player.getWorld().getName().equalsIgnoreCase("dun1"))
	    {
	  //  if (respawns.getString(player.getName()) != null)
	    if(respawns.getString(player.getName()) != null)
	    {
	      Location loc = getStringLocation(respawns.getString(player.getName()));
	      event.setRespawnLocation(loc);
	    }
	  }
	}
	  public String LocToString(Location location)
	  {
	    int x = (int)location.getX();
	    int y = (int)location.getY();
	    int z = (int)location.getZ();
	    String w = location.getWorld().getName();
	    return w + ":" + x + ":" + y + ":" + z;
	  }
	  
	  @EventHandler
	  public void onPlayerInteract1(PlayerInteractEvent event)
	  {
	    Player player = event.getPlayer();
	    if ((player.hasPermission("tombs.usecheckpoint")) 
	    		&& 
	      (event.getClickedBlock() != null))
	    {
	      Block b = event.getClickedBlock();
	      if ((b.getType() == Material.STONE_PLATE) || (b.getType() == Material.WOOD_PLATE) || (b.getType() == Material.WOOD_BUTTON) || (b.getType() == Material.IRON_PLATE) || (b.getType() == Material.GOLD_PLATE) || (b.getType() == Material.LEVER) || (b.getType() == Material.STONE_BUTTON))
	      {
	        String loc = LocToString(b.getLocation());
	        List<String> checkpoints = checkpoint.getStringList("Checkpoints");
			Location loc1 = player.getLocation();
	        if (checkpoints.contains(loc)) {
	          if (respawns.get(player.getName()) != null)
	          {
	            if (!respawns.getString(player.getName()).equalsIgnoreCase(loc))
	            {
	              respawns.set(player.getName(), loc);
	              player.playSound(loc1, Sound.LEVEL_UP, 1, 1);
	              player.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "+" + ChatColor.GREEN + "] " + ChatColor.DARK_GREEN + "Checkpoint!");
	            }
	          }
	          else
	          {
	            respawns.set(player.getName(), loc);
	              player.playSound(loc1, Sound.LEVEL_UP, 1, 1);
	            player.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "+" + ChatColor.GREEN + "] " + ChatColor.DARK_GREEN + "Checkpoint!");
	          }
	        }
	        try
	        {
	          respawns.save(respawnFile);
	        }
	        catch (IOException e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	  }
	  
	  public Location getStringLocation(String string)
	  {
	    String[] loc = string.split(":");
	    String world = loc[0];
	    int x = Integer.parseInt(loc[1]);
	    int y = Integer.parseInt(loc[2]);
	    int z = Integer.parseInt(loc[3]);
	    World w = Bukkit.getServer().getWorld(world);
	    return new Location(w, x, y, z);
	  }
	  
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    Player player = (Player)sender;
	    if ((cmd.getName().equalsIgnoreCase("tombs")) 
	    		|| (cmd.getName().equalsIgnoreCase("tomb")))
		    if (player.hasPermission("tombs.command")){
	      if (args.length == 0)
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + " ---------- " + ChatColor.GOLD + "Tombs " + ChatColor.AQUA + pdf.getVersion() + ChatColor.GOLD + " by " + ChatColor.AQUA + "externo6 " + ChatColor.DARK_AQUA + "----------");
	        player.sendMessage(ChatColor.GOLD + "Add: " + ChatColor.GRAY + "Add a checkpoint to your target block.");
	        player.sendMessage(ChatColor.GOLD + "Remove: " + ChatColor.GRAY + "Remove checkpoint from your target block.");
	        player.sendMessage(ChatColor.GOLD + "Cancel: " + ChatColor.GRAY + "Removes your Checkpoint");
	        player.sendMessage(ChatColor.GOLD + "Checkpoint: " + ChatColor.GRAY + "Goto last Checkpoint.");
	        player.sendMessage(ChatColor.GOLD + "Remeffects: " + ChatColor.GRAY + "Removes Tombs Effects");	        
	        player.sendMessage(ChatColor.DARK_AQUA + " -----------------------------------------");
	      }
	      else if (args.length == 1)
	      {
	        List<String> checkpoints = checkpoint.getStringList("Checkpoints");
	        if (args[0].equalsIgnoreCase("Add"))
	        {
	          if (player.hasPermission("tombs.add"))
	          {
	            @SuppressWarnings("deprecation")
				Block b = player.getTargetBlock(null, 10);
	            String loc = LocToString(b.getLocation());
	            if ((b.getType() == Material.STONE_PLATE) 
	            		|| (b.getType() == Material.WOOD_PLATE) 
	            			|| (b.getType() == Material.WOOD_BUTTON) || (b.getType() == Material.IRON_PLATE) 
	            				|| (b.getType() == Material.GOLD_PLATE) || (b.getType() == Material.LEVER) 
	            					|| (b.getType() == Material.STONE_BUTTON))
	            {
	              if (checkpoints.contains(loc))
	              {
	                player.sendMessage(ChatColor.YELLOW + "Block is already a checkpoint");
	              }
	              else
	              {
	                checkpoints.add(loc);
	                player.sendMessage(ChatColor.GREEN + "Checkpoint added!");
	              }
	            }
	            else {
	              player.sendMessage(ChatColor.RED + "Must be looking at a Pressure plate, Button, or Lever");
	            }
	          }
	          else
	          {
	            player.sendMessage(ChatColor.YELLOW + "You don't have permission to use that command.");
	          }
	        }
	        else if ((args[0].equalsIgnoreCase("Remove")) || (args[0].equalsIgnoreCase("rem")))
	        {
	          if (player.hasPermission("tombs.remove"))
	          {
	            @SuppressWarnings("deprecation")
				Block b = player.getTargetBlock(null, 10);
	            String loc = LocToString(b.getLocation());
	            if ((b.getType() == Material.STONE_PLATE) 
	            		|| (b.getType() == Material.WOOD_PLATE) 
	            			|| (b.getType() == Material.WOOD_BUTTON) 
	            				|| (b.getType() == Material.IRON_PLATE) 
	            					|| (b.getType() == Material.GOLD_PLATE) 
	            						|| (b.getType() == Material.LEVER) 
	            							|| (b.getType() == Material.STONE_BUTTON))
	            {
	              if (!checkpoints.contains(loc))
	              {
	                player.sendMessage(ChatColor.YELLOW + "Block isn't a checkpoint.");
	              }
	              else
	              {
	                checkpoints.remove(loc);
	                player.sendMessage(ChatColor.GREEN + "Checkpoint Removed!");
	              }
	            }
	            else {
	              player.sendMessage(ChatColor.RED + "Must be looking at a Pressure plate, Button, or Lever");
	            }
	          }
	          else
	          {
	            player.sendMessage(ChatColor.YELLOW + "You don't have permission to use that command.");
	          }
	        }
	        else if (args[0].equalsIgnoreCase("checkpoint"))
	        {
	          if (player.hasPermission("tombs.checkpoint"))
	          {
	            if (respawns.getString(player.getName()) != null)
	            {
	              if (respawns.getString(player.getName()).equalsIgnoreCase("spawn"))
	              {
	                player.sendMessage(ChatColor.YELLOW + "No Checkpoint Activated!");
	              }
	              else
	              {
	                Location location = getStringLocation(respawns.getString(player.getName()));
	                player.teleport(location);
	              }
	            }
	            else {
	              player.sendMessage(ChatColor.YELLOW + "No Checkpoint Activated!");
	            }
	          }
	          else {
	            player.sendMessage(ChatColor.YELLOW + "You don't have permission to use that command.");
	          }
	        }
	        else if (args[0].equalsIgnoreCase("remeffects"))
	        {
	        	if (player.hasPermission("tombs.remeffects"))
	        	{
	        		 if(player.getWorld().getName().equalsIgnoreCase("dun1"))
	        		 {
	        		  player.removePotionEffect(PotionEffectType.JUMP);
	        		  player.removePotionEffect(PotionEffectType.BLINDNESS);
	        		  player.removePotionEffect(PotionEffectType.CONFUSION);
	        		  player.removePotionEffect(PotionEffectType.WITHER);
	        		  player.removePotionEffect(PotionEffectType.SLOW);
	        		  player.sendMessage(ChatColor.GREEN + "Effects Removed!");
	        	}
	        		 else
	       		 {
	        		player.sendMessage(ChatColor.RED + "You can only do this in the Tomb World!");	 
	       }
	        	 }
	        	else {
		            player.sendMessage(ChatColor.YELLOW + "You don't have permission to use that command.");
	        	}
	        }
	        else if (args[0].equalsIgnoreCase("Cancel"))
	        {
	          if (player.hasPermission("tombs.cancel"))
	          {
	            if (respawns.getString(player.getName()) != null) 
	            {
	              if (respawns.getString(player.getName()).equalsIgnoreCase("spawn")) //|| (player.getWorld().getName().equalsIgnoreCase("dun1")))
	              {
	                player.sendMessage(ChatColor.YELLOW + "No Checkpoint Activated!");
	              }
	              else
	              {
	                respawns.set(player.getName(), null);
	                player.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "X" + ChatColor.GREEN + "] Checkpoint deactivated!");
	              }
	            }
	            else {
	              player.sendMessage(ChatColor.YELLOW + "No Checkpoint Activated!");
	            }
	          }
	          else 
	          {
	            player.sendMessage(ChatColor.YELLOW + "You don't have permission to use that command.");
	          }
	        }
	        else 
	        {
	          player.sendMessage(ChatColor.RED + "Invalid Command");
	        }
	        checkpoint.set("Checkpoints", checkpoints);
	        try
	        {
	          checkpoint.save(checkpointFile);
	          respawns.save(respawnFile);
	        }
	        catch (IOException e)
	        {
	          e.printStackTrace();
	        }
	      }
	      else
	      {
	        player.sendMessage(ChatColor.RED + "Invalid Syntax");
	      }
	    }
	    return false;

}
}
