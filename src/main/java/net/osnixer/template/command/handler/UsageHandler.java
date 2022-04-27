package net.osnixer.template.command.handler;

import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.valid.messages.InvalidUseMessage;
import net.osnixer.template.config.implemetnation.MessagesConfig;

public class UsageHandler implements InvalidUseMessage {

    private final MessagesConfig messages;

    public UsageHandler(MessagesConfig messages) {
        this.messages = messages;
    }

    @Override
    public String message(LiteInvocation invocation, String useScheme) {
        return this.messages.usageMessage.replace("{USAGE}", useScheme);
    }
}
