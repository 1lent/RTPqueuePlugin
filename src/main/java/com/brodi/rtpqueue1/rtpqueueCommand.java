package com.brodi.rtpqueue1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class rtpqueueCommand implements CommandExecutor {
    ArrayList<UUID> playerList=new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (playerList.contains(player.getUniqueId())) {
                playerList.remove(player.getUniqueId());
                player.sendMessage(ChatColor.RED + "You were removed from the queue.");
                return false;
            }
            playerList.add(player.getUniqueId());
                player.sendMessage(ChatColor.GREEN + "You were added to the queue.");
                if (playerList.size()>1) {
                    Location loc = getLocation();
                    Player player1 = Bukkit.getPlayer(playerList.get(0));
                    Player player2 = Bukkit.getPlayer(playerList.get(1));
                    player1.sendMessage(ChatColor.YELLOW + "You are being teleported");
                    player2.sendMessage(ChatColor.YELLOW + "You are being teleported");
                    player1.teleport(loc);
                    player2.teleport(loc);
                    playerList.remove(player1.getUniqueId());
                    playerList.remove(player2.getUniqueId());
                }


        }

        return false;


    }
    public Location getLocation() {
        int i = 0;
        int xMin = 50;
        int xMax = 1000;
        int zMin = 50;
        int zMax = 1000;
        Random r = new Random();
        World w = Bukkit.getWorld("world");

        do {
            int redx = r.nextInt(xMax - xMin + 1) + xMin;
            int redz = r.nextInt(zMax - zMin + 1) + zMin;
            int redy = w.getHighestBlockYAt(redx, redz) + 3;
            Location redLoc = new Location(w, redx, redy, redz).add(0, 2, 0);


                i = 1;
                return redLoc;


        } while (i == 0);

    }}
