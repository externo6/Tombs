package me.externo6.tombs;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Tomb2Signs implements Listener{
	
	@EventHandler
	public void onSignCreate(SignChangeEvent sign){
		Player player = sign.getPlayer();
		if(sign.getLine(0).equalsIgnoreCase("Tomb Essence")){
			if(!player.hasPermission("tombs.placeessencesign")){
		sign.setLine(0, "NO PERMISSION");
		sign.setLine(1, "NO PERMISSION");
		sign.setLine(2, "NO PERMISSION");
		sign.setLine(3, "NO PERMISSION");
			}
		}
}
	
	@EventHandler
	public void onPlayerInteractTomb2Kit(PlayerInteractEvent event)
	{
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Block block = event.getClickedBlock();
			if (block.getType() == Material.WALL_SIGN)
			{
				Sign sign = (Sign)block.getState();
				Player player = event.getPlayer();
				String[] lores = {"Not for Sale"};
				ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
				ItemMeta meta = sword.getItemMeta();
				meta.setLore(Arrays.asList(lores));
				sword.setItemMeta(meta);
				if ((sign.getLine(0).equals(ChatColor.DARK_BLUE + "Tomb")) && (sign.getLine(1).equals("Armour")))
					if ((player.hasPermission("tombs.armour")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
					{
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

	@EventHandler
	public void onPlayerInteractPermission(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals(ChatColor.DARK_BLUE + "[Kit]")) 
	    		  	&& (sign.getLine(1).equals("Tomb2Essence"))) 
	    	  if ((player.hasPermission("tombs.tomb2essence")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    		  }
	    		  else{
	    			  player.sendMessage(ChatColor.GREEN + "You can now use the warp " + ChatColor.GOLD + "/warp essence");
	    			  player.sendMessage(ChatColor.AQUA + "You can infuse your essence with the signs behind you.");
		    		  Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " add essentials.warps.essence");
		    		  Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " add tombs.(tomb2essence|essencechargedjump|essencespeed|essenceinvisibility|essencearrow|essencejumpboost) dun1");
	    		  }
	          }
	    }
	  }
	Player player = null;
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("[]")) 
	    		  && (sign.getLine(1).equals("<>")) 
	    		  	&& (sign.getLine(2).equals(":")) 
	    		  		&& (sign.getLine(3).equals("-")))
	    	  if ((player.hasPermission("tombs.signjump")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    	  player.setVelocity(new Vector(0,3,0));
	          }
	    }
	  }
  }
}
//getSever().dispatchCommand(getServer().getConsoleSender, "command goes here");