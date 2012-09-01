package net.CreatorJames.PlayerTime;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	String Players = "";
	
	
	@Override 
	public void onEnable() {
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
		
			@Override
            public void run() {
				
				if (!Players.equals("")) {
			String[] Players2 = Players.split(",");
			
			for(int i=0; i < Players2.length; i++){
				
				String[] SetPerson = Players2[i].split(":");
			
				Player person = Bukkit.getPlayer(SetPerson[0]);
		       try { 
				if(person.isOnline())
				{
				person.setPlayerTime(Long.valueOf(SetPerson[1]), false);
				}
		        } catch (Exception ex) {}
				}
				}
			
			}
			
			
		} , 100, 100);
	}
	
	@Override 
	public void onDisable() {
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	if (cmd.getName().equalsIgnoreCase("fptime"))
	{
		if (args.length == 2)
		{
			if (Players.contains(args[0]))
			{
				String[] Players2 = Players.split(",");
				
				
				for(int i=0; i < Players2.length; i++){
					
					String[] SetPerson = Players2[i].split(":");
					
					if(args[0].equals(SetPerson[0])) {
						Players = Players + args[0] + ":" + args[1] + ",";
					} else 
					{
						Players = Players + Players2[i] + ",";
					}					
				}
			}
			
	Players = Players + args[0] + ":" + args[1] + ",";
		
		} else return false;
		return true;
		}
	if (cmd.getName().equalsIgnoreCase("clearfptime"))
	{
	Players = "";
	return true;
	}
		return false;
	}
}
