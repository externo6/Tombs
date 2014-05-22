package me.externo6.tombs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import me.externo6.tombs.Cooldowns;
import me.externo6.tombs.ParticleEffect;
import me.externo6.tombs.Tombs;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
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
	@EventHandler
	public void onPlayerInteractChargedJumpSign(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("Tomb Essence")) 
	    		  && (sign.getLine(1).equals("Infuse With:")) 
	    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Charged Jump")))
	      {
	    	  if ((player.hasPermission("tombs.essencechargedjump")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    		  if((player.getItemInHand().hasItemMeta())
	    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
	    	                        || ((player.getItemInHand().hasItemMeta())
	    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
	    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
	    		  {
		  	  	if (essence.getString(player.getName()).equalsIgnoreCase("chargedjump"))
		  	  	{
		    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Charged Jump");
	    	}
		  	  	else
		  	  	{
		  	  	if (Cooldowns.tryCooldown(player, "5", 2000))
		  	  	{
	    		essence.set(player.getName(), "chargedjump");
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
	    			  ParticleEffect.PORTAL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  ParticleEffect.ENCHANTMENT_TABLE.display(player.getLocation().add(0.0, 2.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  player.sendMessage(ChatColor.GREEN + "Essence changed to: " + ChatColor.AQUA + "Charged Jump");
			  	      try
				        {
				          essence.save(essenceFile);
				        }
				        catch (IOException e)
				        {
				          e.printStackTrace();
				        }
	    			}
		  	  	else		    				
	    			    {
			    	player.sendMessage(ChatColor.GREEN + "Please Wait... Changing too fast!");
	    			    }
			    	}
    		  }
    	  }
      }
    }
  }
}
	@EventHandler
	public void onPlayerInteractSpeedSign(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("Tomb Essence")) 
	    		  && (sign.getLine(1).equals("Infuse With:")) 
	    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Speed")))
	      {
	    	  if ((player.hasPermission("tombs.essencespeed")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    		  if((player.getItemInHand().hasItemMeta())
	    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
	    	                        || ((player.getItemInHand().hasItemMeta())
	    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
	    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
	    		  {
	  		    	if (essence.getString(player.getName()).equalsIgnoreCase("speed")){
			    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Speed");
			    	}
	  		    	else
			    	{
	  		    		if (Cooldowns.tryCooldown(player, "5", 2000))
	  		    		{
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
	    			  ParticleEffect.PORTAL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  ParticleEffect.ENCHANTMENT_TABLE.display(player.getLocation().add(0.0, 2.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
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
				  	  	else		    				
	    			    {
	    			    	player.sendMessage(ChatColor.GREEN + "Please Wait... Changing too fast!");
	    			    }
			    	}
    		  }
    	  }
      }
    }
  }
}
	@EventHandler
	public void onPlayerInteractInvisibilitySign(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("Tomb Essence")) 
	    		  && (sign.getLine(1).equals("Infuse With:")) 
	    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Invisibility")))
	      {
	    	  if ((player.hasPermission("tombs.essenceinvisibility")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    		  if((player.getItemInHand().hasItemMeta())
	    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
	    	                        || ((player.getItemInHand().hasItemMeta())
	    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
	    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
	    		  {
				  	  	if (essence.getString(player.getName()).equalsIgnoreCase("invisibility")){
				    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Invisibility");
				    	}
				  	  	else
				    	{
				  	  	if (Cooldowns.tryCooldown(player, "5", 2000))
				  	  	{
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
	    			  ParticleEffect.PORTAL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  ParticleEffect.ENCHANTMENT_TABLE.display(player.getLocation().add(0.0, 2.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
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
				  	  	else		    				
	    			    {
	    			    	player.sendMessage(ChatColor.GREEN + "Please Wait... Changing too fast!");
	    			    }
			    	}
    		  }
    	  }
      }
    }
  }
}
	@EventHandler
	public void onPlayerInteractArrowSign(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("Tomb Essence")) 
	    		  && (sign.getLine(1).equals("Infuse With:")) 
	    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Arrow")))
	      {
	    	  if ((player.hasPermission("tombs.essencearrow")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    		  if((player.getItemInHand().hasItemMeta())
	    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
	    	                        || ((player.getItemInHand().hasItemMeta())
	    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
	    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
	    		  {
				  	  	if (essence.getString(player.getName()).equalsIgnoreCase("arrow")){
				    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Arrow");
				    	}
				  	  	else
				    	{
					  	  	if (Cooldowns.tryCooldown(player, "5", 2000))
					  	  	{
				    		essence.set(player.getName(), "Arrow");
	    			  ItemStack hand = player.getItemInHand();
	    			  ItemMeta meta = hand.getItemMeta();
	    			  ArrayList<String> lore = new ArrayList<String>();
	    			  meta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
	    			  lore.add(ChatColor.BLUE + "Arrow");
	    			  lore.add(ChatColor.AQUA + "Cost: 6 Levels");
	    			  lore.add(ChatColor.GREEN+ "Cooldown: 15 Seconds");
	    			  lore.add(ChatColor.GRAY + "Bound to: " + player.getName());
	    			  lore.add(ChatColor.DARK_GRAY + "Banxsi.com Official Event");
	    			  meta.setLore(lore);
	    			  hand.setItemMeta(meta);
	    			  ParticleEffect.PORTAL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  ParticleEffect.ENCHANTMENT_TABLE.display(player.getLocation().add(0.0, 2.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  player.sendMessage(ChatColor.GREEN + "Essence changed to: " + ChatColor.AQUA + "Arrow");
			  	      try
				        {
				          essence.save(essenceFile);
				        }
				        catch (IOException e)
				        {
				          e.printStackTrace();
				        }
		    			}
					  	  	else		    				
		    			    {
		    			    	player.sendMessage(ChatColor.GREEN + "Please Wait... Changing too fast!");
		    			    }
				    	}
	    		  }
	    	  }
	      }
	    }
	  }
	}
	@EventHandler
	public void onPlayerInteractJumpBoostSign(PlayerInteractEvent event)
	{	
	  if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
	  {
	    Block block = event.getClickedBlock();
	    if (block.getType() == Material.WALL_SIGN)
	    {
	      Sign sign = (Sign)block.getState();
	      Player player = event.getPlayer();
	      if ((sign.getLine(0).equals("Tomb Essence")) 
	    		  && (sign.getLine(1).equals("Infuse With:")) 
	    		  	&& (sign.getLine(2).equals(ChatColor.WHITE + "Jump Boost")))
	      {
	    	  if ((player.hasPermission("tombs.essencearrow")) && (player.getWorld().getName().equalsIgnoreCase("dun1"))) 
	    	  {
	    		  if((player.getItemInHand().hasItemMeta())
	    	                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                    && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                        && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
	    	                        || ((player.getItemInHand().hasItemMeta())
	    	                                && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
	    	                                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
	    	                                  	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
	    	                                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
	    		  {
				  	  	if (essence.getString(player.getName()).equalsIgnoreCase("jumpboost")){
				    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Jump Boost");
				    	}
				  	  	else
				    	{
					  	  	if (Cooldowns.tryCooldown(player, "5", 2000))
					  	  	{
				    		essence.set(player.getName(), "jumpboost");
	    			  ItemStack hand = player.getItemInHand();
	    			  ItemMeta meta = hand.getItemMeta();
	    			  ArrayList<String> lore = new ArrayList<String>();
	    			  meta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
	    			  lore.add(ChatColor.BLUE + "Jump Boost");
	    			  lore.add(ChatColor.AQUA + "Cost: 5 Levels");
	    			  lore.add(ChatColor.GREEN+ "Cooldown: 30 Seconds");
	    			  lore.add(ChatColor.GRAY + "Bound to: " + player.getName());
	    			  lore.add(ChatColor.DARK_GRAY + "Banxsi.com Official Event");
	    			  meta.setLore(lore);
	    			  hand.setItemMeta(meta);
	    			  ParticleEffect.PORTAL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  ParticleEffect.ENCHANTMENT_TABLE.display(player.getLocation().add(0.0, 2.0, 0.0), 0.0F, 0.0F, 0.0F, 1.0F, 25);
	    			  player.sendMessage(ChatColor.GREEN + "Essence changed to: " + ChatColor.AQUA + "Jump Boost");
			  	      try
				        {
				          essence.save(essenceFile);
				        }
				        catch (IOException e)
				        {
				          e.printStackTrace();
				        }
		    			}
					  	  	else		    				
		    			    {
		    			    	player.sendMessage(ChatColor.GREEN + "Please Wait... Changing too fast!");
		    			    }
				    	}
	    		  }
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
        	player.sendMessage(ChatColor.DARK_RED +"This Essence is not bound to you!");
        	player.getWorld().playSound(player.getLocation(), Sound.FIRE, 1, 1);
            player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1));
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
          if((player.getWorld().getName().equalsIgnoreCase("1point7"))
            		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
          		  || (player.getWorld().getName().equalsIgnoreCase("1point7_Nether")))
            {
          if((player.getItemInHand().hasItemMeta())
              && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
              	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.AQUA + "Charged Jump"))
              	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName())
                  && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                      && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence")))
                     	//&& (essence.getString(player.getName()).equalsIgnoreCase("chargedjump")))
        	  && player.hasPermission("tombs.chargedjump"))
          {         	  
                  if(player.getLevel()>=3)
                  {
                      if (Cooldowns.tryCooldown(player, "3", 23000))
                      {
                          player.setLevel(player.getLevel() - 3);
                          player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 3 Experience Levels used. Charging...");
                              player.getWorld().playSound(player.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
                              ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                              //player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
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
                player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
                player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1));
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
        if((player.getWorld().getName().equalsIgnoreCase("1point7"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point7_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Speed"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    && (essence.getString(player.getName()).equalsIgnoreCase("speed"))
                    && player.hasPermission("tombs.speed"))
        {
                if(player.getLevel()>=5)
                {
                    if (Cooldowns.tryCooldown(player, "3", 45000))
                    {
                        player.setLevel(player.getLevel() - 5);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 5 Experience Levels used. Speed Given.");
                            player.getWorld().playSound(player.getLocation(), Sound.HORSE_GALLOP, 1, 1);
                            ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                            //player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
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
            player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1));
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
        if((player.getWorld().getName().equalsIgnoreCase("1point7"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point7_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Invisibility"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    	&& (essence.getString(player.getName()).equalsIgnoreCase("Invisibility"))
                    	&& player.hasPermission("tombs.invisibility"))
        {
                if(player.getLevel()>=20)
                {
                    if (Cooldowns.tryCooldown(player, "3", 80000))
                    {
                        player.setLevel(player.getLevel() - 20);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 20 Experience Levels used. Invisibility Given.");
                            player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                            ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                            //player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
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
            player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1));
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
}
}
@EventHandler
public void onPlayerInteractEssenceArrow(PlayerInteractEvent event)
{
	
    Player player = event.getPlayer();
    //Location loc = player.getLocation();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if((player.getWorld().getName().equalsIgnoreCase("1point7"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point7_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Arrow"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    	&& (essence.getString(player.getName()).equalsIgnoreCase("Arrow"))
                    	&& player.hasPermission("tombs.arrow"))
        {
                if(player.getLevel()>=6)
                {
                    if (Cooldowns.tryCooldown(player, "3", 15000))
                    {
                        player.setLevel(player.getLevel() - 6);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 6 Experience Levels used. Arrows Shot!");
                        ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
                        player.launchProjectile(Arrow.class);
                        player.launchProjectile(Arrow.class);
                        player.launchProjectile(Arrow.class);
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
            player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1));
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
    }
}
@EventHandler
public void onPlayerInteractJumpBoost(PlayerInteractEvent event)
{
	
    Player player = event.getPlayer();
    //Location loc = player.getLocation();
    if((event.getAction()==Action.RIGHT_CLICK_AIR) 
    		|| event.getAction()==Action.RIGHT_CLICK_BLOCK){
        if((player.getWorld().getName().equalsIgnoreCase("1point7"))
          		//|| (player.getWorld().getName().equalsIgnoreCase("dun1")) 
        		  || (player.getWorld().getName().equalsIgnoreCase("1point7_Nether")))
          {
        if((player.getItemInHand().hasItemMeta())
            && (event.getPlayer().getItemInHand().getType().equals(Material.QUARTZ))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.BLUE + "Jump Boost"))
            	&& (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
                && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
                    && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))
                    	&& (essence.getString(player.getName()).equalsIgnoreCase("jumpboost"))
                    	&& player.hasPermission("tombs.jumpboost"))
        {
                if(player.getLevel()>=5)
                {
                    if (Cooldowns.tryCooldown(player, "3", 45000))
                    {
                        player.setLevel(player.getLevel() - 5);
                        player.sendMessage(ChatColor.GOLD + "Tomb Essence" + ChatColor.WHITE + ":" + ChatColor.GREEN + " 5 Experience Levels used. Jump Boost Given.");
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 2));
                        ParticleEffect.SPELL.display(player.getLocation().add(0.0, 1.0, 0.0), 0.5F, 0.5F, 0.5F, 1.0F, 25);
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
            player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 1));
            player.sendMessage(ChatColor.RED + "The Essence burnt you upon exploding...");
            }
        }
        }
}
}
}

