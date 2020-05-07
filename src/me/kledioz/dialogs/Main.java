package me.kledioz.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {

	Main pl;
	HashMap<String, List<String>> dialogos;
	ArrayList<BukkitTask> tasks;

	public void onEnable() {
		pl = this;
		dialogos = new HashMap<String, List<String>>();
		tasks = new ArrayList<BukkitTask>();
		saveDefaultConfig();
		reloadConfig();
		getCommand("tdialogs").setExecutor(new DialogCommand(this));

		for (String name : getConfig().getConfigurationSection("dialogs").getKeys(false)) {
			dialogos.put(name.toLowerCase(), getConfig().getStringList("dialogs." + name));
		}
		
	}

	public void onDisable() {
		for (BukkitTask task : tasks) {
			task.cancel();
		}
		tasks = null;
	}

}