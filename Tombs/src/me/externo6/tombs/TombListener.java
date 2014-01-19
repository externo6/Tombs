package me.externo6.tombs;

import org.bukkit.ChatColor;
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
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(player.getItemInHand() != null && player.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
		if (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)){
		if(player.getItemInHand().getItemMeta().hasLore());
		if(player.getItemInHand().getItemMeta().getDisplayName().contentEquals("Tomb Artifact"));			
		player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1));
		player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy!");

			}
		
		
}
}



