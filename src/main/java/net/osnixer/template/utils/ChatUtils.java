package net.osnixer.template.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import panda.std.stream.PandaStream;

import java.util.List;

public final class ChatUtils {

    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    private ChatUtils() {
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static Component component(String text) {
        return SERIALIZER.deserialize(text);
    }

    public static List<Component> component(Iterable<String> texts) {
        return PandaStream.of(texts).map(ChatUtils::component).toList();
    }
}

