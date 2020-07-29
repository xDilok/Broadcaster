package pl.xdilok.broadcaster;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Scheduler {

	public static File messFile;
	public static FileConfiguration mess;
	public static String broadcastMsg;

	public static void loadMessage() {

		messFile = new File(Broadcaster.getInstance().getDataFolder(), "message.yml");
		if(!messFile.exists()){
			messFile.getParentFile().mkdirs();
			FilesManager.copyFromJar(Broadcaster.getInstance().getResource("message.yml"), messFile);
		}
		try {
			mess.load(messFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		broadcastMsg = ChatColor.translateAlternateColorCodes('&', mess.getString("Message"));
	}

	public static int loopID = 1;

	public static void startBroadcasting() {
		loopID = Broadcaster.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Broadcaster.getInstance(), new Runnable() {
			public void run() {
				Bukkit.getServer().broadcastMessage(broadcastMsg);
			}
		}, 0, 20*60*15);
	}

}
