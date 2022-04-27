package net.osnixer.template.command.implementation;

import dev.rollczi.litecommands.annotations.Arg;
import dev.rollczi.litecommands.annotations.Execute;
import dev.rollczi.litecommands.annotations.Handler;
import dev.rollczi.litecommands.annotations.Joiner;
import dev.rollczi.litecommands.annotations.MinArgs;
import dev.rollczi.litecommands.annotations.Section;
import net.osnixer.template.command.argument.PlayerArgument;
import net.osnixer.template.utils.ChatUtils;
import org.bukkit.entity.Player;

@Section(route = "message", aliases = "msg")
public class MessageCommand {

    @Execute
    @MinArgs(2)
    public void execute(Player player, @Arg(0) @Handler(PlayerArgument.class) Player target, @Arg(value = 1, name = "message") @Joiner String message) {
        player.sendMessage(ChatUtils.color("&9[&bMe -> " + target.getName() + "&9]: &b" + message));
        target.sendMessage(ChatUtils.color("&9[&b" + player.getName() + "&9 -> Me&9]: &b" + message));
    }
}
