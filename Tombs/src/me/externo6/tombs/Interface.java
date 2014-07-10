package me.externo6.tombs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Interface implements Listener{
	

	public void openInterface(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Tomb Essence Selector");
		
		ItemStack Jump = new ItemStack(Material.IRON_BOOTS);
		ItemMeta JumpMeta = Jump.getItemMeta();
		ItemStack ChargedJump = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta ChargedJumpMeta = ChargedJump.getItemMeta();
		ItemStack Speed = new ItemStack(Material.POTION);
		ItemMeta SpeedMeta = Speed.getItemMeta();
		ItemStack Invisibility = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta InvisibilityMeta = Invisibility.getItemMeta();
		ItemStack Arrow = new ItemStack(Material.ARROW);
		ItemMeta ArrowMeta = Arrow.getItemMeta();
	//	ItemStack Spacer = new ItemStack(Material.PAPER);
		
		JumpMeta.setDisplayName(ChatColor.AQUA + "Jump");
		JumpMeta.setLore(Arrays.asList (ChatColor.BLUE + "Click to change to Jump"));
		Jump.setItemMeta(JumpMeta);
		ChargedJumpMeta.setDisplayName(ChatColor.AQUA + "Jump");
		ChargedJumpMeta.setLore(Arrays.asList (ChatColor.BLUE + "Click to change to Jump"));
		ChargedJump.setItemMeta(ChargedJumpMeta);
		SpeedMeta.setDisplayName(ChatColor.AQUA + "Jump");
		SpeedMeta.setLore(Arrays.asList (ChatColor.BLUE + "Click to change to Jump"));
		Speed.setItemMeta(SpeedMeta);
		InvisibilityMeta.setDisplayName(ChatColor.AQUA + "Jump");
		InvisibilityMeta.setLore(Arrays.asList (ChatColor.BLUE + "Click to change to Jump"));
		Invisibility.setItemMeta(InvisibilityMeta);
		ArrowMeta.setDisplayName(ChatColor.AQUA + "Jump");
		ArrowMeta.setLore(Arrays.asList (ChatColor.BLUE + "Click to change to Jump"));
		Arrow.setItemMeta(ArrowMeta);
		
		inv.setItem(0, Jump);
	//	inv.setItem(1, Spacer);
		inv.setItem(2, ChargedJump);
	//	inv.setItem(3, Spacer);
		inv.setItem(4, Speed);
	//	inv.setItem(5, Spacer);
		inv.setItem(6, Invisibility);
	//	inv.setItem(7, Spacer);
		inv.setItem(8, Arrow);
		
		player.openInventory(inv);
		

	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		if(!ChatColor.stripColor(event.getInventory().getName()).equals("Tomb Essence Selector"))
				return;
		event.setCancelled(true);
		if (Cooldowns.tryCooldown(player, "5", 2000))
  		{
		if (event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR || !event.getCurrentItem().hasItemMeta()){
			//player.closeInventory();
			player.sendMessage("Click on one of the Essence Powers");
			return;
		}		
		switch(event.getCurrentItem().getType()) {
		case IRON_BOOTS:
  		  if((player.getItemInHand().hasItemMeta())
    	          && (player.getItemInHand().getType().equals(Material.QUARTZ))
    	          && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
    	          && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
    	              || ((player.getItemInHand().hasItemMeta())
    	          && (player.getItemInHand().getType().equals(Material.QUARTZ))
    	          && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
    	          && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
    	          && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
		  {
  	  	if (Tomb2Essence.essence.getString(player.getName()).equalsIgnoreCase("chargedjump"))
  	  	{
    		player.sendMessage(ChatColor.RED + "Your Essence is already set to Charged Jump");
	}
  	  	else
  	  	{
  	  	if (Cooldowns.tryCooldown(player, "5", 2000))
  	  	{
		Tomb2Essence.essence.set(player.getName(), "chargedjump");
			  ItemStack hand = player.getItemInHand();
			  ItemMeta meta = hand.getItemMeta();
			  ArrayList<String> lore = new ArrayList<String>();
			  meta.setDisplayName(ChatColor.GOLD + "Tomb Essence");
			  meta.addEnchant(Enchantment.DURABILITY, 100, true);
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
	  	    	Tomb2Essence.essence.save(Tomb2Essence.essenceFile);
		        }
		        catch (IOException e)
		        {
		          e.printStackTrace();
		        }
			}
  	  	}
  	  }
  		  
		break;

		case DIAMOND_BOOTS:
			player.getInventory().contains(Material.QUARTZ);
			player.sendMessage(ChatColor.GREEN + "Set to Charged Jump");
			player.closeInventory();
		break;
		case POTION:
			player.sendMessage(ChatColor.GREEN + "Set to Speed");
			player.closeInventory();
		break;
		default:
			player.closeInventory();
			break;
		}
  		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
	      Player player = event.getPlayer();
		  if((player.getItemInHand().hasItemMeta())
    	          && (player.getItemInHand().getType().equals(Material.QUARTZ))
    	          && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
    	          && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Uninfused Tomb Essence"))
    	              || ((player.getItemInHand().hasItemMeta())
    	          && (player.getItemInHand().getType().equals(Material.QUARTZ))
    	          && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Banxsi.com Official Event"))
    	          && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Bound to: " + player.getName()))
    	          && (player.getItemInHand().getItemMeta().getDisplayName().contentEquals(ChatColor.GOLD + "Tomb Essence"))))
		  {
		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
		  {
		    Block block = event.getClickedBlock();
		    if (block.getType() == Material.WALL_SIGN)
		    {
		      Sign sign = (Sign)block.getState();
		      if ((sign.getLine(0).equals("Tomb Essence")) 
		    		  && (sign.getLine(1).equals("Infuser"))) 
		      {
		    	  openInterface(event.getPlayer());
		    }
		    }
		  }
		  }
		  }  
}

