package net.osnixer.template.config.implemetnation;

import net.osnixer.template.config.AbstractConfigWithResource;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MessagesConfig extends AbstractConfigWithResource {

    public MessagesConfig(File folder, String child) {
        super(folder, child);
    }

    public String playerOffline = "&cPlayer is offline!";
    public String permissionMessage = "&cYou don't have permission! &7({PERMISSIONS})";
    public String usageMessage = "&eCorrect usage: &7{USAGE}";
    public String onlyPlayerMessage = "&cYou can't use this command, you are not a player!";

    public List<String> welcomeMessage = Arrays.asList(
            "&eWelcome on our server, &7{PLAYER}&e!",
            "",
            "&7Use &e/help &7to see your commands!"
    );
}
