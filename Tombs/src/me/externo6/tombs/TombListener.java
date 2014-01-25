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
		if (Cooldowns.tryCooldown(player, "1", 10000)) {
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
			player.sendMessage(ChatColor.GREEN + "You feel an energy build up and transported...");
			player.teleport(new Location(player.getWorld(), -395.5, 89, 1424.5, 180 , 0));
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




