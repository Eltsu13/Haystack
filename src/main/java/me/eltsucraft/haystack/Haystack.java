package me.eltsucraft.haystack;

import me.eltsucraft.haystack.managers.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Haystack extends JavaPlugin {

    private WorldManager worldManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Initialize Managers
        worldManager = new WorldManager(this);

        // Initialize Lobby
        worldManager.initializeLobby();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
