package me.externo6.tombs;

import java.io.File;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
//import me.externo6.tombs.ParticleEffect;

public class Tomb2Essence implements Listener{
	static File essenceFile = new File("./plugins/Tombs", "Essence.yml");
    public static FileConfiguration essence = YamlConfiguration.loadConfiguration(essenceFile);

  
    @EventHandler
	public void onPlayerInteractWrongWorld(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (player.getWorld().getName().equalsIgnoreCase("dun1"))
		{
		if((event.getAction()==Action.RIGHT_CLICK_AIR)  
        			|| (event.getAction()==Action.LEFT_CLICK_AIR) 
        				|| (event.getAction()==Action.LEFT_CLICK_BLOCK)){
				 if((player.getItemInHand().hasItemMeta())
						 		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
			                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
			                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))
				 {
					 if (Cooldowns.tryCooldown(player, "6", 2000)){
					 player.sendMessage(ChatColor.BLUE + ("You can only use the Essence in the survival world."));
				 }
			}
		}
	}
}
  //**Tomb2 Essence DETECTION
    @EventHandler
	public void onPlayerInteractUninfused(PlayerInteractEvent event)
    {
    Player player = event.getPlayer();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    			|| (event.getAction()==Action.LEFT_CLICK_AIR) 
    				|| (event.getAction()==Action.LEFT_CLICK_BLOCK)){
        if((player.getItemInHand().hasItemMeta())
        		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence")))
        {
        	 if (Cooldowns.tryCooldown(player, "7", 2000)){

        	player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " This Essence has not been infused yet!");
        	 }
        }
      }
    }
//   }
    @EventHandler
	public void onPlayerInteractUnbound(PlayerInteractEvent event)
    {
    Player player = event.getPlayer();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| (event.getAction()==Action.RIGHT_CLICK_BLOCK) 
    			|| (event.getAction()==Action.LEFT_CLICK_AIR) 
    				|| (event.getAction()==Action.LEFT_CLICK_BLOCK)){
        if((player.getItemInHand().hasItemMeta())
        		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (!player.getItemInHand().getItemMeta().getLore().contains((ChatColor.GRAY + "Bound to: " + player.getName())))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))
        {
        	player.getInventory().removeItem(player.getInventory().getItemInHand());
        	player.sendMessage(ChatColor.DARK_RED +"This Essence is not bound to you!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setFireTicks(120);
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
        }
      }
    }
@EventHandler
  public void onPlayerInteractEssenceChargedJump(PlayerInteractEvent event)
{
      Player player = event.getPlayer();
      if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		  || event.getAction()==Action.RIGHT_CLICK_BLOCK){
          if((player.getWorld().getName().equalsIgnoreCase("1point8"))
            		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
          		  || (player.getWorld().getName().equalsIgnoreCase("1point8_Nether")))
            {
          if((player.getItemInHand().hasItemMeta())
        		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
              	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Charged Jump"))
              	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName())
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))
                && (essence.getString(player.getName()).equalsIgnoreCase("chargedjump")))
          {         	  
                  if(player.getLevel()>=1)
                  {
                      if (Cooldowns.tryCooldown(player, "3", 23000))
                      {
                          player.setLevel(player.getLevel() - 1);
                          player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 1 Experience Level used. Charging...");
                              player.getWorld().playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
                            //  ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                            //  player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
                                  Tombs.tomb2powerupjump(player);
                    }
                      else
                      {
                  	  if (Cooldowns.tryCooldown(player, "4", 4000))
                  	  {
                  	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                    }
                 }
                  }
                  else
                  {
                      player.sendMessage(ChatColor.RED + "Not enough Experience!");
                  }
          		}
              else if((player.getItemInHand().hasItemMeta())
                      	&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                      	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Charged Jump"))
                      	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                        && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                        && (!essence.getString(player.getName()).equalsIgnoreCase("chargedjump")))
                      {
            	  if (Cooldowns.tryCooldown(player, "5", 4000))
            	  {
              	player.sendMessage(ChatColor.DARK_RED +"You may only have one essence bound at a time!");
            	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
                player.getInventory().removeItem(player.getInventory().getItemInHand());
                player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
                player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
                player.setFireTicks(120);
                player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
                      }
          }
         }
      }
      }
@EventHandler
public void onPlayerInteractEssenceSpeed(PlayerInteractEvent event)
{
    Player player = event.getPlayer();
    //Location loc = player.getLocation();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK)
    {
        if((player.getWorld().getName().equalsIgnoreCase("1point8"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point8_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
        		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Speed"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                && (essence.getString(player.getName()).equalsIgnoreCase("speed")))
        {
                if(player.getLevel()>=2)
                {
                    if (Cooldowns.tryCooldown(player, "3", 45000))
                    {
                        player.setLevel(player.getLevel() - 2);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 5 Experience Levels used. Speed Given.");
                            player.getWorld().playSound(player.getLocation(), Sound.HORSE_GALLOP, 1, 1);
                         //   ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
                            
                  }
                    else
                    {
                	  if (Cooldowns.tryCooldown(player, "4", 4000))
                	  {
                	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                  }
                }
               }
                else
                {
                    player.sendMessage(ChatColor.RED + "Not enough Experience!");
                	}
            	}
        else if((player.getItemInHand().hasItemMeta())
                	&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Speed"))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    && (!essence.getString(player.getName()).equalsIgnoreCase("Speed")))
                {
        	if (Cooldowns.tryCooldown(player, "5", 4000))
        	{
          	player.sendMessage(ChatColor.DARK_RED +"You may only have one essence bound at a time!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getInventory().removeItem(player.getInventory().getItemInHand());
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setFireTicks(120);
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
                }
        	}
        }
    }
    }
@EventHandler
public void onPlayerInteractEssenceInvisibility(PlayerInteractEvent event)
{
    Player player = event.getPlayer();
    //Location loc = player.getLocation();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if((player.getWorld().getName().equalsIgnoreCase("1point8"))
        		  || (player.getWorld().getName().equalsIgnoreCase("1point8_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
        		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Invisibility"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
              	&& (essence.getString(player.getName()).equalsIgnoreCase("Invisibility")))
        {
                if(player.getLevel()>=15)
                {
                    if (Cooldowns.tryCooldown(player, "3", 80000))
                    {
                        player.setLevel(player.getLevel() - 15);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 20 Experience Levels used. Invisibility Given.");
                            player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                          //  ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 1));
                            
                  }
                    else
                    {
                	  if (Cooldowns.tryCooldown(player, "4", 4000))
                	  {
                	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                  }
                }
               }
                else
                {
                    player.sendMessage(ChatColor.RED + "Not enough Experience!");
            }
            }
        else if((player.getItemInHand().hasItemMeta())
        		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Invisibility"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                && (!essence.getString(player.getName()).equalsIgnoreCase("Invisibility")))
            {
        	if (Cooldowns.tryCooldown(player, "5", 4000))
        	{
          	player.sendMessage(ChatColor.DARK_RED +"You may only have one essence bound at a time!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getInventory().removeItem(player.getInventory().getItemInHand());
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setFireTicks(120);
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
}
}
@SuppressWarnings("deprecation")
@EventHandler
public void onPlayerInteractEssenceArrow(PlayerInteractEvent event)
{
    Player player = event.getPlayer();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if((player.getWorld().getName().equalsIgnoreCase("1point8"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point8_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Arrow"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                && (essence.getString(player.getName()).equalsIgnoreCase("Arrow")))
        {
                if(player.getInventory().contains(Material.ARROW, 2))
                {
                    if (Cooldowns.tryCooldown(player, "3", 2000))
                    {
                        //player.setLevel(player.getLevel() - 4);
                        player.getInventory().removeItem(new ItemStack(Material.ARROW, 2));
                        player.updateInventory();
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + "1 Critical and 1 Fire Arrow shot");
                   //     ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                        player.launchProjectile(Arrow.class).setCritical(true);
                        Tombs.arrowfire2(player);
                  }
                    else
                    {
                	  if (Cooldowns.tryCooldown(player, "4", 2000))
                	  {
                	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                  }
                }
               }
                else
                {
                    player.sendMessage(ChatColor.RED + "Not enough Experience or Arrows!");
            }
            }
        else if((player.getItemInHand().hasItemMeta())
                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Arrow"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                && (!essence.getString(player.getName()).equalsIgnoreCase("arrow")))
            {
        	if (Cooldowns.tryCooldown(player, "5", 4000))
        	{
          	player.sendMessage(ChatColor.DARK_RED +"You may only have one essence bound at a time!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getInventory().removeItem(player.getInventory().getItemInHand());
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setFireTicks(120);
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
    }
}
/*@EventHandler
public void onPlayerInteractJumpBoost(PlayerInteractEvent event)
{
	
    Player player = event.getPlayer();
    //Location loc = player.getLocation();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if((player.getWorld().getName().equalsIgnoreCase("1point8"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point8_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Jump Boost"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    	&& (essence.getString(player.getName()).equalsIgnoreCase("jumpboost")))
        {
                if(player.getLevel()>=2)
                {
                    if (Cooldowns.tryCooldown(player, "3", 45000))
                    {
                        player.setLevel(player.getLevel() - 2);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 5 Experience Levels used. Jump Boost Given.");
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 2));
                   //     ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                  }
                    else
                    {
                	  if (Cooldowns.tryCooldown(player, "4", 4000))
                	  {
                	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                  }
                }
               }
                else
                {
                    player.sendMessage(ChatColor.RED + "Not enough Experience!");
            }
            }
        else if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Jump Boost"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    	&& (!essence.getString(player.getName()).equalsIgnoreCase("jumpboost")))
            {
        	if (Cooldowns.tryCooldown(player, "5", 4000))
        	{
          	player.sendMessage(ChatColor.DARK_RED +"You may only have one essence bound at a time!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getInventory().removeItem(player.getInventory().getItemInHand());
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setFireTicks(120);
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
}
}*/
@EventHandler
public void onPlayerInteractJumpBoost(PlayerInteractEvent event)
{
    Player player = event.getPlayer();
    //ItemStack
	ItemStack jumpboost = new ItemStack(Material.QUARTZ, 1);
	ItemMeta jumpboostmeta = jumpboost.getItemMeta();
	jumpboostmeta.setLore(Arrays.asList(ChatColor.BLUE + "Jump Boost",  
			ChatColor.AQUA + "Cost: 2 Levels",
			ChatColor.GREEN + "Cooldown: 30 Seconds",
			ChatColor.GRAY + "Bound to: " + player.getName(),
			ChatColor.DARK_GRAY + "Banxsi.com Official Event"));
	jumpboostmeta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
	jumpboost.setItemMeta(jumpboostmeta);
	//ItemStack
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if((player.getWorld().getName().equalsIgnoreCase("1point8"))
        		  || (player.getWorld().getName().equalsIgnoreCase("1point8_Nether")))
          {
        	if(player.getItemInHand().equals(jumpboost)){
                if(player.getLevel()>=2)
                {
                    if (Cooldowns.tryCooldown(player, "3", 45000))
                    {
                        player.setLevel(player.getLevel() - 2);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 5 Experience Levels used. Jump Boost Given.");
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 2));
                   //     ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                  }
                    else
                    {
                	  if (Cooldowns.tryCooldown(player, "4", 4000))
                	  {
                	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                  }
                }
               }
                else
                {
                    player.sendMessage(ChatColor.RED + "Not enough Experience!");
            }
            }
        else if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Jump Boost"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    	&& (!essence.getString(player.getName()).equalsIgnoreCase("jumpboost")))
            {
        	if (Cooldowns.tryCooldown(player, "5", 4000))
        	{
          	player.sendMessage(ChatColor.DARK_RED +"You may only have one essence bound at a time!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getInventory().removeItem(player.getInventory().getItemInHand());
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setFireTicks(120);
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
}
}
@EventHandler
public void onTomb3Key(PlayerInteractEvent event)
{
    Player player = event.getPlayer();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
  		  || event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if (player.getWorld().getName().equalsIgnoreCase("dun1"))
        	//	|| (player.getWorld().getName().equalsIgnoreCase("1point8")))
          {
        if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Tomb 3"))
            //	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName())
            //    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Key")))//)
            {
        if (Cooldowns.tryCooldown(player, "8", 10000)){
        	player.setHealth(20.0);
        	Tombs.tomb3keystrike2(player);
        	Tombs.tomb3keystrike2(player);
        	Tombs.tomb3keystrike3(player);
        	Tombs.tomb3keystrike4(player);
        	Tombs.tomb3keystrike5(player);
        	Tombs.tomb3keystrike6(player);
               }
        else
        {
    	  if (Cooldowns.tryCooldown(player, "4", 4000))
    	  {
    	  player.sendMessage(ChatColor.GOLD + "Tomb Key" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
            		}
          		}
            }
        }
    }
}
    @EventHandler
    public void onLightning(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if((event.getAction()==Action.RIGHT_CLICK_AIR) 
      		  || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            if (player.getWorld().getName().equalsIgnoreCase("dun1")
            		|| (player.getWorld().getName().equalsIgnoreCase("1point8")))
              {
            if((player.getItemInHand().hasItemMeta())
            		&& (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
                	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Tomb Test"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Lightning")))
                {
            if (Cooldowns.tryCooldown(player, "8", 10000)){
            	player.sendMessage(ChatColor.GOLD + "Tombs:" + ChatColor.GREEN + " Lightning Spawned at Crosshair Location");
            	Tombs.tombessencelightning1(player);
            	Tombs.tombessencelightning2(player);
            	Tombs.tombessencelightning3(player);
            	Tombs.tombessencelightning4(player);
            	Tombs.tombessencelightning5(player);
                   }
            else
            {
        	  if (Cooldowns.tryCooldown(player, "4", 4000))
        	  {
        	  player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.BLUE + " On Cooldown!");
                		}
              		}
                }
            }
            }        
  }
}



