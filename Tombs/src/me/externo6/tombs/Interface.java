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
		
		inv.setItem(1, Jump);
		inv.setItem(2, ChargedJump);
		inv.setItem(3, Speed);
		inv.setItem(4, Invisibility);
		inv.setItem(5, Arrow);
		
		player.openInventory(inv);

	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(!ChatColor.stripColor(event.getInventory().getName()).equals("Tomb Essence Selector"))
				return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if (event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR || !event.getCurrentItem().hasItemMeta()){
			player.closeInventory();
			return;
		}
		
		switch(event.getCurrentItem().getType()) {
		case IRON_BOOTS:
			player.sendMessage(ChatColor.GREEN + "Set to Jump");
			player.closeInventory();
		break;
		case DIAMOND_BOOTS:
			player.sendMessage(ChatColor.GREEN + "Set to Charged Jump");
			player.closeInventory();
		break;
		case POTION:
			player.sendMessage(ChatColor.GREEN + "Set to Speed");
			player.closeInventory();
		break;
		}
		
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK))
		  {
		    Block block = event.getClickedBlock();
		    if (block.getType() == Material.WALL_SIGN)
		    {
		      Sign sign = (Sign)block.getState();
		      Player player = event.getPlayer();
		      if ((sign.getLine(0).equals("Tomb Essence")) 
		    		  && (sign.getLine(1).equals("Infuser"))) 
		      {
		    	  openInterface(event.getPlayer());
		      }
		      }    
		    }
		  }
	
	
	
	
}
