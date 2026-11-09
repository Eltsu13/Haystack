package me.eltsucraft.haystack.managers;

import me.eltsucraft.haystack.Haystack;
import me.eltsucraft.haystack.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;

public class WorldManager {

    private Haystack plugin;

    public WorldManager(Haystack plugin) {
        this.plugin = plugin;
    }

    private static final String LOBBY = "lobby";

    public boolean initializeLobby() {
        File folder = new File(Bukkit.getWorldContainer(), LOBBY);
        boolean folderExists = folder.exists();

        File lobby = new File(plugin.getDataFolder(), "maps/" + LOBBY);
        boolean templateExists = lobby.exists();

        if (lobby.exists() && !folder.exists()) {
            if (!FileUtil.copyDirectory(lobby, folder)) {
                FileUtil.deleteDirectory(folder);
                return false;
            }
        }

        WorldCreator creator = new WorldCreator(LOBBY);

        if (!templateExists && !folderExists) {
            creator.type(WorldType.FLAT);
        }

        World world = creator.createWorld();
        return world != null;
    }
}
