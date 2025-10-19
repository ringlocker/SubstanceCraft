package com.github.ringlocker.substancecraft.server.command;

import com.github.ringlocker.substancecraft.effect.SubstanceWorldData;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.commands.Commands.literal;

public class SubstanceCraftCommands {

    public static void registerCommands() {
            CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("sampleData")
                .executes(context -> {
                    String msg = SubstanceWorldData.get(context.getSource().getLevel()).toString();
                    List<String> msgs = splitString(msg, 255);
                    msgs.forEach(string -> context.getSource().sendChatMessage(
                            OutgoingChatMessage.create(PlayerChatMessage.system(string)),
                            false,
                            ChatType.bind(ChatType.MSG_COMMAND_INCOMING, context.getSource()).withTargetName(context.getSource().getDisplayName())));
                    return 1;
                })));
    }


    private static List<String> splitString(String input, int maxLength) {
        List<String> result = new ArrayList<>();
        int start = 0;
        int length = input.length();

        while (start < length) {
            int end = Math.min(start + maxLength, length);
            result.add(input.substring(start, end));
            start = end;
        }

        return result;
    }

}
