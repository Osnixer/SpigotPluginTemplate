package net.osnixer.template.command.argument;

import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.SingleArgumentHandler;
import dev.rollczi.litecommands.valid.ValidationCommandException;
import net.osnixer.template.config.implemetnation.MessagesConfig;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@ArgumentName("player")
public class PlayerArgument implements SingleArgumentHandler<Player> {

    private final MessagesConfig messages;
    private final Server server;

    public PlayerArgument(MessagesConfig messages, Server server) {
        this.messages = messages;
        this.server = server;
    }

    @Override
    public Player parse(LiteInvocation invocation, String argument) throws ValidationCommandException {
        Player player = this.server.getPlayer(argument);

        if (player == null) {
            throw new ValidationCommandException(this.messages.playerOffline);
        }

        return player;
    }

    @Override
    public List<String> tabulation(LiteInvocation invocation, String command, String[] args) {
        return this.server
                .getOnlinePlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
