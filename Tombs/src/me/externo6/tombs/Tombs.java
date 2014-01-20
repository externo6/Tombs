package me.externo6.tombs;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tombs extends JavaPlugin {	
	public final Logger logger = Logger.getLogger("minecraft");
	public static Tombs plugin;
	public final TombListener pl = new TombListener();
	

	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled!"); 
		getServer().getPluginManager().removePermission(new Permissions().canWarpToTomb2);
	}

	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[Tombs]" + " Version " + pdfFile.getVersion() + " |" + " Developed for Banxsi.com by externo6");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
		pm.addPermission(new Permissions().canWarpToTomb2);
		
	}

}

