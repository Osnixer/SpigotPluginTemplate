package net.osnixer.template.config;

import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.osnixer.template.config.composer.LocationComposer;
import net.osnixer.template.config.implemetnation.MessagesConfig;
import net.osnixer.template.config.implemetnation.PluginConfig;
import org.bukkit.Location;
import org.bukkit.Server;

import java.io.File;

public class ConfigManager {

    private final MessagesConfig messagesConfig;
    private final PluginConfig pluginConfig;
    private final Cdn cdn;

    public ConfigManager(Server server, File dataFolder) {

        this.pluginConfig = new PluginConfig(dataFolder, "config.yml");
        this.messagesConfig = new MessagesConfig(dataFolder, "messages.yml");

        this.cdn = CdnFactory
                .createYamlLike()
                .getSettings()
                .withComposer(Location.class, new LocationComposer(server))
                .build();
    }

    public void loadConfigs() {
        this.loadAndRender(this.pluginConfig);
        this.loadAndRender(this.messagesConfig);
    }

    public <T extends ConfigWithResource> void loadAndRender(T config) {
        this.cdn.load(config.getResource(), config)
                .orElseThrow(RuntimeException::new);

        this.cdn.render(config, config.getResource())
                .orElseThrow(RuntimeException::new);
    }

    public <T extends ConfigWithResource> void render(T config) {
        this.cdn.render(config, config.getResource())
                .orElseThrow(RuntimeException::new);
    }

    public MessagesConfig getMessagesConfig() {
        return this.messagesConfig;
    }

    public PluginConfig getPluginConfig() {
        return this.pluginConfig;
    }
}
