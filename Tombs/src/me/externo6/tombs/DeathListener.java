package me.externo6.tombs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener{
	
	@EventHandler
	public void onPlayerDeathEvent (PlayerDeathEvent event){
		Player player = event.getEntity();
		if(player.getWorld().getName().equalsIgnoreCase("dun1")){
		event.getDrops().clear();		
		}
	}
}
