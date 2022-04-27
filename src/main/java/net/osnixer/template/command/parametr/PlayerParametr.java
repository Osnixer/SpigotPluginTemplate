package net.osnixer.template.command.parametr;

import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.bind.Parameter;
import dev.rollczi.litecommands.valid.ValidationCommandException;
import net.osnixer.template.config.implemetnation.MessagesConfig;
import org.bukkit.entity.Player;

public class PlayerParametr implements Parameter {

    private final MessagesConfig messages;

    public PlayerParametr(MessagesConfig messages) {
        this.messages = messages;
    }

    @Override
    public Object apply(LiteInvocation invocation) {
        if (invocation.sender().getSender() instanceof Player) {
            return invocation.sender().getSender();
        }

        throw new ValidationCommandException(this.messages.onlyPlayerMessage);
    }
}
