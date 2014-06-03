package me.externo6.tombs;

import me.externo6.tombs.Cooldowns;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Tomb1Artifact implements Listener{
	  @EventHandler
		public void onPlayerInteractTombArtifactInfo(PlayerInteractEvent event){ //If they are not in the correct world, they will not be teleported.
			Player player = event.getPlayer();
			if((event.getAction()==Action.LEFT_CLICK_AIR) || event.getAction()==Action.LEFT_CLICK_BLOCK)
			{
			if((player.getItemInHand().hasItemMeta())
				&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
					&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact"))
					&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")))
			{
			if (Cooldowns.tryCooldown(player, "2", 2000))
			{
			player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "Tomb Artifact" + ChatColor.GOLD + "]" + ChatColor.YELLOW + " This is a key to Tomb 2. Once Tomb 2 is released, right clicking will teleport you to Tomb 2." );
				}
			}
		}
	}
	  @EventHandler
		public void onPlayerInteractTombArtifact(PlayerInteractEvent event)
	  {
			Player player = event.getPlayer();
			Location loc = player.getLocation();
			if((event.getAction()==Action.RIGHT_CLICK_AIR) || event.getAction()==Action.RIGHT_CLICK_BLOCK)
			{
			if(player.getWorld().getName().equalsIgnoreCase("1point7"))
			{
			if((player.getItemInHand().hasItemMeta()) //Check if ItemMeta is present, this stops nullpointerexeptions on normal Quartz
				&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)) 
					&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
						&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact")))
			{
			if (Cooldowns.tryCooldown(player, "1", 15000)) 
			{
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
			player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy!");
			player.sendMessage(ChatColor.AQUA + "You feel an energy build up, then fade away...");
			player.sendMessage(ChatColor.RED + "You must be in the Tomb world to feel the full effect!");
			player.setVelocity(new Vector(0,0.5,0));
			player.playSound(loc, Sound.VILLAGER_NO, 1, 1);
			}
		}
		}
			else
			{ //if they are in the correct world, they will be teleported to the start of Tomb2
			if(player.hasPermission("tombs.tomb2"))
			{
					if(player.getWorld().getName().equalsIgnoreCase("dun1"))
					{
						if((player.getItemInHand().hasItemMeta())
							&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)) 
								&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
									&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact")))
						{
				if (Cooldowns.tryCooldown(player, "1", 15000)) 
				{
				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 120, 100));
				player.sendMessage(ChatColor.GOLD + "The Artifact is surging with energy");
				player.sendMessage(ChatColor.GREEN + "You feel an energy build up and flung into the air!");
				player.playSound(loc, Sound.VILLAGER_YES, 1, 1);
				Tombs.jump(player);
				Tombs.blindness(player);
				Tombs.delay(player);
				Tombs.ouch(player);
				}
			}
		}
				}
			else
			{
					if((player.getItemInHand().hasItemMeta())
							&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ)) 
								&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event")) 
									&& (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Artifact")))
					{
							if (Cooldowns.tryCooldown(player, "1", 15000)) 
							{
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
	
}
