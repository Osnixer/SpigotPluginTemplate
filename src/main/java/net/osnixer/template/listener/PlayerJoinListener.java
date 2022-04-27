package net.osnixer.template.listener;

import net.osnixer.template.config.implemetnation.MessagesConfig;
import net.osnixer.template.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final MessagesConfig messages;

    public PlayerJoinListener(MessagesConfig messages) {
        this.messages = messages;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.messages.welcomeMessage.forEach(message -> player.sendMessage(ChatUtils.color(message.replace("{PLAYER}", player.getName()))));
    }
}
