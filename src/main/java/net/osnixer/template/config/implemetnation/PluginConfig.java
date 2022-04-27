package net.osnixer.template.config.implemetnation;

import net.osnixer.template.config.AbstractConfigWithResource;
import org.bukkit.Location;

import java.io.File;

public class PluginConfig extends AbstractConfigWithResource {

    public PluginConfig(File folder, String child) {
        super(folder, child);
    }

    public Location spawnLocation = new Location(null, 0, 0, 0);


}
