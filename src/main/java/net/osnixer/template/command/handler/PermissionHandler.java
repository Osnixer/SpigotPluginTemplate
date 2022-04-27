package net.osnixer.template.command.handler;

import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.valid.messages.PermissionMessage;
import net.osnixer.template.config.implemetnation.MessagesConfig;
import panda.utilities.text.Joiner;

import java.util.List;

public class PermissionHandler implements PermissionMessage {

    private final MessagesConfig messages;

    public PermissionHandler(MessagesConfig messages) {
        this.messages = messages;
    }

    @Override
    public String message(LiteInvocation invocation, List<String> permissions) {
        String permission = Joiner.on(", ").join(permissions).toString();

        return this.messages.permissionMessage.replace("{PERMISSIONS}", permission);
    }
}
