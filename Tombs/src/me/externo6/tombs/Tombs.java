package me.externo6.tombs;

import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
	static File essenceFile = new File("./plugins/Tombs", "Essence.yml");
    public static FileConfiguration essence = YamlConfiguration.loadConfiguration(essenceFile);
    
	private static Vehicle vehicle = null;
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event){
	  if ((event.getVehicle() instanceof Minecart))
	  {
	    vehicle = event.getVehicle();
	    Location loc = vehicle.getLocation();
	    vehicle.remove();
	    loc.getWorld().dropItem(loc, new ItemStack(Material.MINECART, 1));
	  }
}
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event){
		Player player = event.getPlayer();
	 if(player.getWorld().getName().equalsIgnoreCase("1point7")){
	  player.removePotionEffect(PotionEffectType.JUMP);
	  player.removePotionEffect(PotionEffectType.BLINDNESS);
	  player.removePotionEffect(PotionEffectType.CONFUSION);
	  player.removePotionEffect(PotionEffectType.WITHER);
	  player.removePotionEffect(PotionEffectType.SLOW);
	}
}
	@EventHandler
	public void onWorldChange1(PlayerChangedWorldEvent event){
		Player player = event.getPlayer();
	 if(player.getWorld().getName().equalsIgnoreCase("dun1")){
		 //if (respawns.getString(player.getName()).equalsIgnoreCase("spawn"))
           respawns.set(player.getName(), null);
         //  player.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "X" + ChatColor.GREEN + "] Checkpoint deactivated!");
         }
       }  

	
	//Sign Listener
	Player player = null;
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK)){
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN){
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("[]")) 
	    		  && (sign.getLine(1).equals("<>")) 
	    		  	&& (sign.getLine(2).equals(":")) 
	    		  		&& (sign.getLine(3).equals("-")))
	    	  if ((player.hasPermission("tombs.signjump")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) {
	    	  player.setVelocity(new Vector(0,3,0));
	          }
	    }
	  }
  }
	
	@EventHandler
	public void onPlayerInteract4(PlayerInteractEvent event)
	{
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Block block = event.getClickedBlock();
			if (block.getType() == Material.WALL_SIGN){
				Sign sign = (Sign)block.getState();
				Player player = event.getPlayer();
				String[] lores = {"Not for Sale"};
				ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
				ItemMeta meta = sword.getItemMeta();
				meta.setLore(Arrays.asList(lores));
				sword.setItemMeta(meta);
				if ((sign.getLine(0).equals(ChatColor.DARK_BLUE + "Tomb")) && (sign.getLine(1).equals("Armour")))
					if ((player.hasPermission("tombs.armour")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) {
						player.getInventory().clear();
						player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
						player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
						player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
						player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));
						player.getInventory().setItemInHand(sword);
					}
			}
		}
	}
	 
	//Checkpoints
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
	    if ((player.hasPermission("tombs.usecheckpoint")) && 
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
	    if ((cmd.getName().equalsIgnoreCase("tombs")) || (cmd.getName().equalsIgnoreCase("tomb")))
		    if (player.hasPermission("tombs.command")){
	      if (args.length == 0)
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + " ---------- " + ChatColor.GOLD + "Tombs " + ChatColor.AQUA + getDescription().getVersion() + ChatColor.GOLD + " by " + ChatColor.AQUA + "externo6 " + ChatColor.DARK_AQUA + "----------");
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
	          if (player.hasPermission("tombs.remove"))
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
	        else if (args[0].equalsIgnoreCase("remeffects"))
	        {
	        	if (player.hasPermission("tombs.remeffects"))
	        	{
	        		 if(player.getWorld().getName().equalsIgnoreCase("dun1")){
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
	  }
	  //** Tomb2 Sign Detection
		@EventHandler
		public void onPlayerInteract9(PlayerInteractEvent event)
		{	
		  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK)){
		    Block block = event.getClickedBlock();
		    if (block.getType() == Material.WALL_SIGN){
		      Sign sign = (Sign)block.getState();
		      Player player = event.getPlayer();
		      if ((sign.getLine(0).equals("Tomb Essence")) 
		    		  && (sign.getLine(1).equals("Infuse With:")) 
		    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Charged Jump"))){
		    	  if ((player.hasPermission("tombs.essencejump")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) {
		    		  if((player.getItemInHand().hasItemMeta())
		    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
		    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
		    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
		    	                        || ((player.getItemInHand().hasItemMeta())
		    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
		    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
		    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
		    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))){
			  	  	if (essence.getString(player.getName()).equalsIgnoreCase("jump")){
			    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Jump");
		    	}else{
		    		essence.set(player.getName(), "Jump");
		    			  ItemStack hand = player.getItemInHand();
		    			  ItemMeta meta = hand.getItemMeta();
		    			  ArrayList<String> lore = new ArrayList<String>();
		    			  meta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
		    			  lore.add(ChatColor.BLUE + "Charged Jump");
		    			  lore.add(ChatColor.AQUA + "Cost: 3 Levels");
		    			  lore.add(ChatColor.GREEN+ "Cooldown: 15 Seconds");
		    			  lore.add(ChatColor.GRAY + "Bound to: " + player.getName());
		    			  lore.add(ChatColor.DARK_GRAY + "Banxsi.com Official Event");
		    			  meta.setLore(lore);
		    			  hand.setItemMeta(meta);
		    			  player.sendMessage(ChatColor.GREEN + "Essence changed to: " + ChatColor.AQUA + "Jump");
				  	      try
					        {
					          essence.save(essenceFile);
					        }
					        catch (IOException e)
					        {
					          e.printStackTrace();
					        }
		    			}
		    		  }else{
			    		  if((player.getItemInHand().hasItemMeta())
	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	                                  	&& (!player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName() ==null))
	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))){
			    			  player.sendMessage(ChatColor.DARK_RED + "That Essence is not bound to you!");		  
		    	  }
		      }
		    }
		  }
		    }
		  }
		}
	
		@EventHandler
		public void onPlayerInteract10(PlayerInteractEvent event)
		{	
		  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK)){
		    Block block = event.getClickedBlock();
		    if (block.getType() == Material.WALL_SIGN){
		      Sign sign = (Sign)block.getState();
		      Player player = event.getPlayer();
		      if ((sign.getLine(0).equals("Tomb Essence")) 
		    		  && (sign.getLine(1).equals("Infuse With:")) 
		    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Speed"))){
		    	  if ((player.hasPermission("tombs.essencespeed")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) {
		    		  if((player.getItemInHand().hasItemMeta())
		    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
		    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
		    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
		    	                        || ((player.getItemInHand().hasItemMeta())
		    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
		    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
		    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
		    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))){
		    			  if (essence.getString(player.getName()).equalsIgnoreCase("jump")){
				    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Speed");
				    	}else{
				    		essence.set(player.getName(), "Speed");
		    			  ItemStack hand = player.getItemInHand();
		    			  ItemMeta meta = hand.getItemMeta();
		    			  ArrayList<String> lore = new ArrayList<String>();
		    			  meta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
		    			  lore.add(ChatColor.BLUE + "Speed");
		    			  lore.add(ChatColor.AQUA + "Cost: 5 Levels");
		    			  lore.add(ChatColor.GREEN+ "Cooldown: 30 Seconds");
		    			  lore.add(ChatColor.GRAY + "Bound to: " + player.getName());
		    			  lore.add(ChatColor.DARK_GRAY + "Banxsi.com Official Event");
		    			  meta.setLore(lore);
		    			  hand.setItemMeta(meta);
		    			  player.sendMessage(ChatColor.GREEN + "Essence changed to: " + ChatColor.AQUA + "Speed");
				  	      try
					        {
					          essence.save(essenceFile);
					        }
					        catch (IOException e)
					        {
					          e.printStackTrace();
				        }
	    			}	
	    			}else{
			    		  if((player.getItemInHand().hasItemMeta())
			    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
			    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
			    	                                  	&& (!player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName() ==null))
			    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))){
			    			  player.sendMessage(ChatColor.DARK_RED + "That Essence is not bound to you!");
	    				}
	    		  }
	    	  }
	      }
	    }
	  }
	}
		@EventHandler
		public void onPlayerInteract11(PlayerInteractEvent event)
		{	
		  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK)){
		    Block block = event.getClickedBlock();
		    if (block.getType() == Material.WALL_SIGN){
		      Sign sign = (Sign)block.getState();
		      Player player = event.getPlayer();
		      if ((sign.getLine(0).equals("Tomb Essence")) 
		    		  && (sign.getLine(1).equals("Infuse With:")) 
		    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Invisibility"))){
		    	  if ((player.hasPermission("tombs.essenceinvisibility")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) {
		    		  if((player.getItemInHand().hasItemMeta())
		    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
		    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
		    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
		    	                        || ((player.getItemInHand().hasItemMeta())
		    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
		    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
		    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
		    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))){
		    			  if (!essence.getString(player.getName()).equalsIgnoreCase("jump")){
					    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Invisibility");
					    	}else{
					    		essence.set(player.getName(), "Invisibility");
		    			  ItemStack hand = player.getItemInHand();
		    			  ItemMeta meta = hand.getItemMeta();
		    			  ArrayList<String> lore = new ArrayList<String>();
		    			  meta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
		    			  lore.add(ChatColor.BLUE + "Invisibility");
		    			  lore.add(ChatColor.AQUA + "Cost: 20 Levels");
		    			  lore.add(ChatColor.GREEN+ "Cooldown: 1 Minute");
		    			  lore.add(ChatColor.GRAY + "Bound to: " + player.getName());
		    			  lore.add(ChatColor.DARK_GRAY + "Banxsi.com Official Event");
		    			  meta.setLore(lore);
		    			  hand.setItemMeta(meta);
		    			  player.sendMessage(ChatColor.GREEN + "Essence changed to: " + ChatColor.AQUA + "Invisibility");
				  	      try
					        {
					          essence.save(essenceFile);
					        }
					        catch (IOException e)
					        {
					          e.printStackTrace();
					        }
			    			}
			    		  }else{
				    		  if((player.getItemInHand().hasItemMeta())
  	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
  	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
  	                                  	&& (!player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName() ==null))
  	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))){
  			  player.sendMessage(ChatColor.DARK_RED + "That Essence is not bound to you!");
			}
	  }
}
}
}
}
}
      //**Tomb2 Essence DETECTION
	    @EventHandler
    	public void onPlayerInteract5(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if((event.getAction()==Action.RIGHT_CLICK_AIR) || (event.getAction()==Action.RIGHT_CLICK_BLOCK) || (event.getAction()==Action.LEFT_CLICK_AIR) || (event.getAction()==Action.LEFT_CLICK_BLOCK)){
            if((player.getWorld().getName().equalsIgnoreCase("1point7")) ||
                    (player.getWorld().getName().equalsIgnoreCase("1point7_Nether"))){
            if((player.getItemInHand().hasItemMeta())
                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))){
            	player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " This Essence has not been infused yet!");
            }
          }
        }
    }
    @EventHandler
      public void onPlayerInteract6(PlayerInteractEvent event){
          Player player = event.getPlayer();
          //Location loc = player.getLocation();
          if((event.getAction()==Action.RIGHT_CLICK_AIR) || event.getAction()==Action.RIGHT_CLICK_BLOCK){
              if((player.getWorld().getName().equalsIgnoreCase("1point7")) ||
                      (player.getWorld().getName().equalsIgnoreCase("1point7_Nether"))){
              if((player.getItemInHand().hasItemMeta())
                  && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Charged Jump"))
                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                      && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                          && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))){
                      if(player.getLevel()>=3){
                          if (Cooldowns.tryCooldown(player, "3", 20000)){
                              player.setLevel(player.getLevel() - 3);
                              player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 3 Experience Levels used. Charging...");
                                  player.getWorld().playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
                                  //player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
                                      tomb2powerupjump(player);
                        }else{
                      	  if (Cooldowns.tryCooldown(player, "4", 4000)){
                      	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                        }
                      }
                     }
                      else{
                          player.sendMessage(ChatColor.RED + "Not enough Experience!");
                      }
              		}
              	}
              }
          }
    @EventHandler
    public void onPlayerInteract7(PlayerInteractEvent event){
        Player player = event.getPlayer();
        //Location loc = player.getLocation();
        if((event.getAction()==Action.RIGHT_CLICK_AIR) || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            if((player.getWorld().getName().equalsIgnoreCase("1point7")) ||
                    (player.getWorld().getName().equalsIgnoreCase("1point7_Nether"))){
            if((player.getItemInHand().hasItemMeta())
                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Speed"))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))){
                    if(player.getLevel()>=5){
                        if (Cooldowns.tryCooldown(player, "3", 40200)){
                            player.setLevel(player.getLevel() - 5);
                            player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 5 Experience Levels used. Speed Given.");
                                player.getWorld().playSound(player.getLocation(), Sound.HORSE_GALLOP, 1, 1);
                                //player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 520, 1));
                                
                      }else{
                    	  if (Cooldowns.tryCooldown(player, "4", 4000)){
                    	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                      }
                    }
                   }
                    else{
                        player.sendMessage(ChatColor.RED + "Not enough Experience!");
                    	}
                	}
            	}
            }
    	}
    @EventHandler
    public void onPlayerInteract8(PlayerInteractEvent event){
        Player player = event.getPlayer();
        //Location loc = player.getLocation();
        if((event.getAction()==Action.RIGHT_CLICK_AIR) || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            if((player.getWorld().getName().equalsIgnoreCase("1point7")) ||
                    (player.getWorld().getName().equalsIgnoreCase("1point7_Nether"))){
            if((player.getItemInHand().hasItemMeta())
                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Invisibility"))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))){
                    if(player.getLevel()>=20){
                        if (Cooldowns.tryCooldown(player, "3", 80000)){
                            player.setLevel(player.getLevel() - 20);
                            player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 20 Experience Levels used. Invisibility Given.");
                                player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                //player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
                                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 1));
                                
                      }else{
                    	  if (Cooldowns.tryCooldown(player, "4", 4000)){
                    	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                      }
                    }
                   }
                    else{
                        player.sendMessage(ChatColor.RED + "Not enough Experience!");
                }
                }
    }
            }
        }
	    //**Tomb 1 Artifact
	  @EventHandler
		public void onPlayerInteract2(PlayerInteractEvent event){ //If they are not in the correct world, they will not be teleported.
			Player player = event.getPlayer();
			if((event.getAction()==Action.LEFT_CLICK_AIR) || event.getAction()==Action.LEFT_CLICK_BLOCK){
			if((player.getItemInHand().hasItemMeta())
				&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
					&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact"))
					&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
){
			if (Cooldowns.tryCooldown(player, "2", 2000)){
			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Tomb Artifact" + ChatColor.GOLD + "]" + ChatColor.YELLOW + " This is a key to Tomb 2. Once Tomb 2 is released, right clicking will teleport you to Tomb 2." );
				}
			}
		}
	}
	  @EventHandler
		public void onPlayerInteract3(PlayerInteractEvent event){
			Player player = event.getPlayer();
			Location loc = player.getLocation();
			if((event.getAction()==Action.RIGHT_CLICK_AIR) || event.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(player.getWorld().getName().equalsIgnoreCase("1point7")){
			if((player.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
				&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)) 
					&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
						&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact"))){
			if (Cooldowns.tryCooldown(player, "1", 15000)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
			player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy!");
			player.sendMessage(ChatColor.AQUA + "You feel an energy build up, then fade away...");
			player.sendMessage(ChatColor.RED + "You must be in the Tomb world to feel the full effect!");
			player.setVelocity(new Vector(0,0.5,0));
			player.playSound(loc, Sound.VILLAGER_NO, 1, 1);
			}
		}
		}else{ //if they are in the correct world, they will be teleported to the start of Tomb2
			if(player.hasPermission("tombs.tomb2")){
					if(player.getWorld().getName().equalsIgnoreCase("dun1")){
						if((player.getItemInHand().hasItemMeta())
							&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)) 
								&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
									&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact"))){
				if (Cooldowns.tryCooldown(player, "1", 15000)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 120, 100));
				player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy");
				player.sendMessage(ChatColor.GREEN + "You feel an energy build up and flung into the air!");
				player.playSound(loc, Sound.VILLAGER_YES, 1, 1);
				jump(player);
				blindness(player);
				delay(player);
				ouch(player);
				}
			}
		}
				}else{
					if((player.getItemInHand().hasItemMeta())
							&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)) 
								&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
									&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact"))){
							if (Cooldowns.tryCooldown(player, "1", 15000)) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
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
        public void tomb2powerupjump (final Player player){
            getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                  public void run() {
                      //Location loc = player.getLocation();
                      player.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + "Me" + ChatColor.GOLD + "]" + ChatColor.WHITE + ":" + ChatColor.GREEN + " Woah!");
                        player.setVelocity(new Vector(0,1.40,0));   
                        player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_LARGE, 0);
                        player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
                  }
            }, 80L);
            }
	}

