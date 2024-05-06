package com.brodi.rtpqueue1;

import org.bukkit.plugin.java.JavaPlugin;

public final class Rtpqueue1 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    getCommand("rtpqueue").setExecutor(new rtpqueueCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
