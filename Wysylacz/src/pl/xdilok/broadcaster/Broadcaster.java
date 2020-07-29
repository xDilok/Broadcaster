package pl.xdilok.broadcaster;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Broadcaster extends JavaPlugin{

	private static Broadcaster instance;
	
	@Override
	public void onEnable() {
		instance = this;
		Scheduler.mess = new YamlConfiguration();
		
		Scheduler.loadMessage();
		
		Scheduler.startBroadcasting();
	}
	@Override
	public void onDisable() {
		Bukkit.getScheduler().cancelTask(Scheduler.loopID);
	}
	
	public static Broadcaster getInstance() {
		return instance;
	}
}
