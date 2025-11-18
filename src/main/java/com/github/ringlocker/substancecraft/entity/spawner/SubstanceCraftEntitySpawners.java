package com.github.ringlocker.substancecraft.entity.spawner;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.List;

public class SubstanceCraftEntitySpawners {

    private static ServerLevel overworld;
    private static final List<Spawner> spawners = new ArrayList<>();

    public static void init() {
        spawners.add(new DealerSpawner());

        ServerTickEvents.START_SERVER_TICK.register(SubstanceCraftEntitySpawners::tick);
        ServerLifecycleEvents.SERVER_STARTED.register(SubstanceCraftEntitySpawners::serverStart);
    }

    public static void serverStart(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        if (level == null) {
            System.out.println("Overworld loading failed!");
            return;
        }
        overworld = level;
    }

    public static void tick(MinecraftServer server) {
        if (server.tickRateManager().isFrozen()) {
            if (!server.tickRateManager().isSteppingForward()) {
                return;
            }
        }
        spawners.forEach(spawner -> spawner.tick(overworld));
    }

}
