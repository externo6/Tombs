package me.externo6.tombs;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class MinecartListener implements Listener{	
	private static Vehicle vehicle = null;	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event){
		if((event.getVehicle() instanceof Minecart) &&
			 (event.getVehicle().getWorld().getName().equalsIgnoreCase("dun1")))
	  {
	    vehicle = event.getVehicle();
	    vehicle.remove();
	  }
}

}
