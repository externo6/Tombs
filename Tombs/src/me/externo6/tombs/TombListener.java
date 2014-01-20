package me.externo6.tombs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TombListener implements Listener{
	public static Tombs plugin;
	
	@EventHandler
	//Maybe better ways to do this, but it works.
	public void onPlayerInteract(PlayerInteractEvent event){ //If they are not in the correct world, they will not be teleported.
		Player player = event.getPlayer();
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
		}
		}else{ //if they are in the correct world, they will be teleported to the start of Tomb2
			Player player1 = event.getPlayer();
			if(player1.hasPermission(new Permissions().canWarpToTomb2)){
			if(player1.getWorld().getName().equalsIgnoreCase("dun1")){
			if(player1.getItemInHand() != null && player1.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
			if (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)){
			if(player1.getItemInHand().getItemMeta().hasLore());
			if(player1.getItemInHand().getItemMeta().getDisplayName().contentEquals("Tomb Artifact"));			
			player1.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 250, 1));
			player1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
			player1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1));
			player1.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy");
			player1.sendMessage(ChatColor.GREEN + "You feel an energy build up and transported...");
			player.teleport(new Location(player1.getWorld(), -395.5, 89, 1424.5, 180 , 0));
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



