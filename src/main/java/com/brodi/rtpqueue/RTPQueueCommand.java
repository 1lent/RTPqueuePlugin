package com.brodi.rtpqueue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RTPQueueCommand implements CommandExecutor {
    List<UUID> playersInQueue = new ArrayList<>(); // this queue will at max contain 2 entries, you don't need a List
    private final static int xMin = 50; // hard coded magic values - also a bad idea, consider making them configurable
    private final static int xMax = 1000;
    private final static int zMin = 50;
    private final static int zMax = 1000;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player player)) return false; // use jdk 17 for cool instant instanceOf casting
        if (playersInQueue.contains(player.getUniqueId())) {
            playersInQueue.remove(player.getUniqueId());
            player.sendMessage(ChatColor.RED + "You were removed from the queue.");
            return true;
        }

        playersInQueue.add(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "You were added to the queue.");
        if (playersInQueue.size() == 2) {
            Location loc = getRandomLocation();
            Player player1 = Bukkit.getPlayer(playersInQueue.get(0));
            Player player2 = Bukkit.getPlayer(playersInQueue.get(1));
            player1.sendMessage(ChatColor.YELLOW + "You are being teleported"); // possibly switch to async teleports - this will pause the main thread
            player2.sendMessage(ChatColor.YELLOW + "You are being teleported");
            player1.teleport(loc);
            player2.teleport(loc);
            playersInQueue.remove(player1.getUniqueId());
            playersInQueue.remove(player2.getUniqueId());
        }
        return true; // don't return false, it will give the player the feeling that they misspelled the command
    }
    public Location getRandomLocation() {
        Random randomSource = new Random();
        World hopeFullyExistingDefaultWorld = Bukkit.getWorld("world"); // bad idea - rather make world names configurable!

        int randomX = randomSource.nextInt(xMax - xMin + 1) + xMin;
        int randomZ = randomSource.nextInt(zMax - zMin + 1) + zMin;
        int highestY = hopeFullyExistingDefaultWorld.getHighestBlockYAt(randomX, randomZ) + 3;
        return new Location(hopeFullyExistingDefaultWorld, randomX, highestY, randomZ).add(0, 2, 0);
    }}
