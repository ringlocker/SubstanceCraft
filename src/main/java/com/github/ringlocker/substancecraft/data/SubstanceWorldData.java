package com.github.ringlocker.substancecraft.data;

import com.github.ringlocker.substancecraft.data.component.SubstanceData;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SubstanceWorldData extends SavedData {

    private static final String FILE_NAME = "substance_data";
    private static final Codec<Map<UUID, SubstanceData>> MAP_CODEC = Codec.unboundedMap(Codec.STRING.xmap(UUID::fromString, UUID::toString), SubstanceData.CODEC);
    private static final Codec<SubstanceWorldData> CODEC = RecordCodecBuilder.create(instance -> instance.group(MAP_CODEC.fieldOf("players").forGetter(SubstanceWorldData::getPlayerData)).apply(instance, SubstanceWorldData::new));
    private static final SavedDataType<SubstanceWorldData> TYPE = new SavedDataType<>(FILE_NAME, ctx -> new SubstanceWorldData(), ctx -> SubstanceWorldData.CODEC, null);

    private final Map<UUID, SubstanceData> playerData = new HashMap<>();

    public SubstanceWorldData() {
        this.setDirty(true);
    }

    public SubstanceWorldData(Map<UUID, SubstanceData> map) {
        this.playerData.putAll(map);
    }

    public static SavedDataType<SubstanceWorldData> type() {
        return TYPE;
    }

    public static SubstanceWorldData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public static void save(ServerLevel level, SubstanceWorldData value) {
        level.getDataStorage().set(type(), value);
    }

    public Map<UUID, SubstanceData> getPlayerData() {
        return this.playerData;
    }

    public boolean clearPlayerData(UUID uuid) {
        return this.playerData.remove(uuid) != null;
    }

    public SubstanceData getData(UUID playerId) {
        return playerData.computeIfAbsent(playerId, id -> new SubstanceData());
    }

    @Override
    public String toString() {
        return String.format("SubstanceWorldData{playerData=[%s]}", playerData);
    }

}
