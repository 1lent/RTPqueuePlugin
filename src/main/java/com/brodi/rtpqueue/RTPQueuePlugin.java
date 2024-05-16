package com.brodi.rtpqueue;

import org.bukkit.plugin.java.JavaPlugin;

public final class RTPQueuePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("rtpqueue").setExecutor(new RTPQueueCommand());
    }

    @Override
    public void onDisable() {

    }
}
