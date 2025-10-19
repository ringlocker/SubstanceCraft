package com.github.ringlocker.substancecraft.command;

import com.github.ringlocker.substancecraft.data.SubstanceWorldData;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.commands.Commands.literal;

public class SubstanceCraftCommands {

    public static void registerCommands() {
            CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("substancedata")
                .executes(context -> {
                    String msg = SubstanceWorldData.get(context.getSource().getLevel()).toString();
                    List<String> msgs = splitString(msg, 250);
                    List<Component> components = new ArrayList<>();
                    msgs.forEach(string -> components.add(Component.literal(string)));
                    components.forEach(c -> context.getSource().getPlayer().sendSystemMessage(c));


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
