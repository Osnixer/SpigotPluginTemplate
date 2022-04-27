package net.osnixer.template;

import com.google.common.base.Stopwatch;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.valid.messages.UseSchemeFormatting;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.osnixer.template.command.argument.PlayerArgument;
import net.osnixer.template.command.handler.PermissionHandler;
import net.osnixer.template.command.handler.UsageHandler;
import net.osnixer.template.command.implementation.MessageCommand;
import net.osnixer.template.command.parametr.PlayerParametr;
import net.osnixer.template.config.ConfigManager;
import net.osnixer.template.config.implemetnation.MessagesConfig;
import net.osnixer.template.config.implemetnation.PluginConfig;
import net.osnixer.template.listener.PlayerJoinListener;
import net.osnixer.template.utils.legacy.LegacyColorProcessor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class Plugin extends JavaPlugin {

    private ConfigManager configManager;
    private LiteCommands liteCommands;
    private MiniMessage miniMessage;

    @Override
    public void onEnable() {
        Server server = this.getServer();

        this.miniMessage = MiniMessage
                .builder()
                .postProcessor(new LegacyColorProcessor())
                .build();

        this.getServer().getScheduler();

        this.configManager = new ConfigManager(server, this.getDataFolder());
        this.configManager.loadConfigs();

        PluginConfig config = this.configManager.getPluginConfig();
        MessagesConfig messages = this.configManager.getMessagesConfig();

        Stream.of(new PlayerJoinListener(messages))
                .forEach(listener -> server.getPluginManager().registerEvents(listener, this));

        this.liteCommands = LiteBukkitFactory.builder(server, "SpigotTemplate")
                .argument(Player.class,         new PlayerArgument(messages, server))
                .parameterBind(Player.class,    new PlayerParametr(messages))

                .permissionMessage(new PermissionHandler(messages))
                .invalidUseMessage(new UsageHandler(messages))
                .formattingUseScheme(UseSchemeFormatting.NORMAL)

                .command(MessageCommand.class)
                .register();
    }

    @Override
    public void onDisable() {
        this.liteCommands.getPlatformManager().unregisterCommands();
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public LiteCommands getLiteCommands() {
        return this.liteCommands;
    }

    public MiniMessage getMiniMessage() {
        return this.miniMessage;
    }
}
