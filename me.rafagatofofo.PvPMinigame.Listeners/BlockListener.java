package me.rafagatofofo.PvPMinigame.Listeners;

import me.rafagatofofo.PvPMinigame.Core;
import me.rafagatofofo.PvPMinigame.Commands.CommandArena;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if (isWithinRegion(block.getLocation())){
			player.sendMessage(Core.TAG+"You can't break blocks here.");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if (isWithinRegion(block.getLocation())){
			player.sendMessage(Core.TAG+"You can't place blocks here");
			event.setCancelled(true);
		}
	}
	
	
	
	private static boolean isWithinRegion(Location location){
		
		Location p1 = CommandArena.Locations.get(1);
		Location p2 = CommandArena.Locations.get(2);

		int minX = p1.getBlockX() < p2.getBlockX() ? p1.getBlockX() : p2.getBlockX();
		int minY = p1.getBlockY() < p2.getBlockY() ? p1.getBlockY() : p2.getBlockY();
		int minZ = p1.getBlockZ() < p2.getBlockZ() ? p1.getBlockZ() : p2.getBlockZ();
		
		int maxX = p1.getBlockX() > p2.getBlockX() ? p1.getBlockX() : p2.getBlockX();
		int maxY = p1.getBlockY() > p2.getBlockY() ? p1.getBlockY() : p2.getBlockY();
		int maxZ = p1.getBlockZ() > p2.getBlockZ() ? p1.getBlockZ() : p2.getBlockZ();
		
		if (location.getBlockX() >= minX && location.getBlockX() <= maxX){
			if (location.getBlockY() >= minY && location.getBlockY() <= maxY){
				if (location.getBlockZ() >= minZ && location.getBlockZ() <= maxZ){
					
					return true;
				}
			}
		}
		
		return false;
	}
}
