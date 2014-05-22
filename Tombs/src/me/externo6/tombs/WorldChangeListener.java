package me.externo6.tombs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffectType;

public class WorldChangeListener implements Listener{
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event)
	{
		Player player = event.getPlayer();
	 if(player.getWorld().getName().equalsIgnoreCase("1point7"))
	 {
	  player.removePotionEffect(PotionEffectType.JUMP);
	  player.removePotionEffect(PotionEffectType.BLINDNESS);
	  player.removePotionEffect(PotionEffectType.CONFUSION);
	  player.removePotionEffect(PotionEffectType.WITHER);
	  player.removePotionEffect(PotionEffectType.SLOW);
	}
}
	@EventHandler
	public void onWorldChange1(PlayerChangedWorldEvent event)
	{
		Player player = event.getPlayer();
	 if(player.getWorld().getName().equalsIgnoreCase("dun1"))
	 {
           Checkpoints.respawns.set(player.getName(), null);
           if (!Tomb2Essence.essence.contains(player.getName())){
        	   Tomb2Essence.essence.set(player.getName(), "None");
           }
         }
       }
}
