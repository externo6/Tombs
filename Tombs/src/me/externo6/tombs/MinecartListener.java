package me.externo6.tombs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;

public class MinecartListener implements Listener{	
	private static Vehicle vehicle = null;	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event){
		if(event.getVehicle() instanceof Minecart)
	  {
	    vehicle = event.getVehicle();
	    Location loc = vehicle.getLocation();
	    vehicle.remove();
	    loc.getWorld().dropItem(loc, new ItemStack(Material.MINECART, 1));
	  }
}

}
