package me.externo6.tombs;

import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
 
public class Tombs extends JavaPlugin implements Listener {	
	public final Logger logger = Logger.getLogger("minecraft");
	public static Tombs plugin;
	static File respawnFile = new File("./plugins/Tombs", "Respawns.yml");
    public static FileConfiguration respawns = YamlConfiguration.loadConfiguration(respawnFile);
	static File checkpointFile = new File("./plugins/Tombs", "Checkpoint.yml");
	public static FileConfiguration checkpoint = YamlConfiguration.loadConfiguration(checkpointFile);

	//**Should Really be in another thread?
	  @EventHandler
	  public void onPlayerRespawn(PlayerRespawnEvent event)
	  {
		final Player player = event.getPlayer();
	    //Player player = event.getPlayer();
	    if(player.getWorld().getName().equalsIgnoreCase("dun1")){
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
	    if ((player.hasPermission("tombs.use")) && 
	      (event.getClickedBlock() != null))
	    {
	      Block b = event.getClickedBlock();
	      if ((b.getType() == Material.STONE_PLATE) || (b.getType() == Material.WOOD_PLATE) || (b.getType() == Material.WOOD_BUTTON) || (b.getType() == Material.IRON_PLATE) || (b.getType() == Material.GOLD_PLATE) || (b.getType() == Material.LEVER) || (b.getType() == Material.STONE_BUTTON))
	      {
	        String loc = LocToString(b.getLocation());
	        List<String> checkpoints = checkpoint.getStringList("Checkpoints");
	        if (checkpoints.contains(loc)) {
	          if (respawns.get(player.getName()) != null)
	          {
	            if (!respawns.getString(player.getName()).equalsIgnoreCase(loc))
	            {
	              respawns.set(player.getName(), loc);
	              player.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "+" + ChatColor.GREEN + "] " + ChatColor.DARK_GREEN + "Checkpoint!");
	            }
	          }
	          else
	          {
	            respawns.set(player.getName(), loc);
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

	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled!"); 
	}

	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[Tombs]" + " Version " + pdfFile.getVersion() + " |" + " Developed for Banxsi.com by externo6");
		@SuppressWarnings("unused")
		PluginManager pm = getServer().getPluginManager();
		getServer().getPluginManager().registerEvents(this, this);	
	}
	

	  
	  public Location getStringLocation(String string)
	  {
	    String[] loc = string.split(":");
	    String world = loc[0];
	    int x = Integer.parseInt(loc[1]);
	    int y = Integer.parseInt(loc[2]);
	    int z = Integer.parseInt(loc[3]);
	    World w = getServer().getWorld(world);
	    return new Location(w, x, y, z);
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    Player player = (Player)sender;
	    if (cmd.getName().equalsIgnoreCase("tombs")) {
	      if (args.length == 0)
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + " ---------- " + ChatColor.GOLD + "Tombs " + ChatColor.AQUA + getDescription().getVersion() + ChatColor.GOLD + " by " + ChatColor.AQUA + "externo6 " + ChatColor.DARK_AQUA + "----------");
	        player.sendMessage(ChatColor.GOLD + "Add: " + ChatColor.GRAY + "Add a checkpoint to your target block.");
	        player.sendMessage(ChatColor.GOLD + "Remove: " + ChatColor.GRAY + "Remove checkpoint from your target block.");
	        player.sendMessage(ChatColor.GOLD + "Cancel: " + ChatColor.GRAY + "Removes your Checkpoint");
	        player.sendMessage(ChatColor.GOLD + "Checkpoint: " + ChatColor.GRAY + "Goto last Checkpoint.");
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
	            if ((b.getType() == Material.STONE_PLATE) || (b.getType() == Material.WOOD_PLATE) || (b.getType() == Material.WOOD_BUTTON) || (b.getType() == Material.IRON_PLATE) || (b.getType() == Material.GOLD_PLATE) || (b.getType() == Material.LEVER) || (b.getType() == Material.STONE_BUTTON))
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
	          if (player.hasPermission("Checkpoint.remove"))
	          {
	            @SuppressWarnings("deprecation")
				Block b = player.getTargetBlock(null, 10);
	            String loc = LocToString(b.getLocation());
	            if ((b.getType() == Material.STONE_PLATE) || (b.getType() == Material.WOOD_PLATE) || (b.getType() == Material.WOOD_BUTTON) || (b.getType() == Material.IRON_PLATE) || (b.getType() == Material.GOLD_PLATE) || (b.getType() == Material.LEVER) || (b.getType() == Material.STONE_BUTTON))
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
	        else if (args[0].equalsIgnoreCase("Cancel"))
	        {
	          if (player.hasPermission("tombs.cancel"))
	          {
	            if (respawns.getString(player.getName()) != null)
	            {
	              if (respawns.getString(player.getName()).equalsIgnoreCase("spawn"))
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
	          else {
	            player.sendMessage(ChatColor.YELLOW + "You don't have permission to use that command.");
	          }
	        }
	        else {
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
	    //**Artifact Detection
	  }
	  @EventHandler
		public void onPlayerInteract(PlayerInteractEvent event){ //If they are not in the correct world, they will not be teleported.
			Player player = event.getPlayer();
			if(event.getAction()==Action.RIGHT_CLICK_AIR){
			if (Cooldowns.tryCooldown(player, "1", 5000)) {
			if(player.getWorld().getName().equalsIgnoreCase("1point7")){
			if(player.getItemInHand() != null && player.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
			if (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)){
			if(player.getItemInHand().getItemMeta().hasLore());
			if(player.getItemInHand().getItemMeta().getDisplayName().contentEquals("Tomb Artifact"));			
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1));
			player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy!");
			player.sendMessage(ChatColor.AQUA + "You feel an energy build up, then fade away...");
			player.setVelocity(new Vector(0,1,0));
			}
			}else{ //if they are in the correct world, they will be teleported to the start of Tomb2
				if(player.hasPermission("tombs.canwarpto")){
				if(player.getWorld().getName().equalsIgnoreCase("dun1")){
				if(player.getItemInHand() != null && player.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
				if (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)){
				if(player.getItemInHand().getItemMeta().hasLore());
				if(player.getItemInHand().getItemMeta().getDisplayName().contentEquals("Tomb Artifact"));			
				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1));
				player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy");
				player.sendMessage(ChatColor.GREEN + "You feel an energy build up and flung into the air!");
				jump(player);
				blindness(player);
				delay(player);
				ouch(player);
				}
				}
				}else{
					if(player.getItemInHand() != null && player.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
						if (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)){
						if(player.getItemInHand().getItemMeta().hasLore());
						if(player.getItemInHand().getItemMeta().getDisplayName().contentEquals("Tomb Artifact"));			
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1));
						player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy!");
						player.sendMessage(ChatColor.RED + "You Dont have Permission to go there yet...");
						
	}
	}
	}
	}
	}
	}
		public void delay (final Player player){	
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				  public void run() {
			player.teleport(new Location(player.getWorld(), -395.5, 106, 1424.5, 180 , 0));
				  }
			}, 80L);
			}
		
		public void jump (final Player player){
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				  public void run() {
						Location loc = player.getLocation();
				  player.setVelocity(new Vector(0,3,0));
					player.playSound(loc, Sound.ENDERDRAGON_WINGS, 1, 1);
				  }
			}, 40L);
			}
		
		public void blindness (final Player player){
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				  public void run() {
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 55, 1));	
				  }
			}, 50L);
			}
		public void ouch (final Player player){
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				  public void run() {
					  Location loc = player.getLocation();
			player.sendMessage(ChatColor.AQUA + "Ouch!");
			loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2004);
			player.playSound(loc, Sound.FALL_BIG, 1, 1);
				  }
			}, 110L);
			}
	}


