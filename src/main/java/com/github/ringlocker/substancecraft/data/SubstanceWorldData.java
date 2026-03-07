package com.github.ringlocker.substancecraft.data;

import com.github.ringlocker.substancecraft.data.component.SubstanceData;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SubstanceWorldData extends SavedData {

    private static final String FILE_NAME = "substance_data";
    private static final Codec<Map<UUID, SubstanceData>> MAP_CODEC = Codec.unboundedMap(Codec.STRING.xmap(UUID::fromString, UUID::toString), SubstanceData.CODEC);
    private static final Codec<SubstanceWorldData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    MAP_CODEC.fieldOf("playerData").forGetter(SubstanceWorldData::getPlayerData),
                    Codec.INT.fieldOf("dealerSpawnDelay").forGetter(SubstanceWorldData::getDealerSpawnDelay),
                    Codec.INT.fieldOf("dealerSpawnChance").forGetter(SubstanceWorldData::getDealerSpawnChance)
            ).apply(instance, SubstanceWorldData::new)
    );
    private static final SavedDataType<@NotNull SubstanceWorldData> TYPE = new SavedDataType<>(FILE_NAME, SubstanceWorldData::new, SubstanceWorldData.CODEC, DataFixTypes.LEVEL);

    private final Map<UUID, SubstanceData> playerData = new HashMap<>();
    private int dealerSpawnDelay;
    private int dealerSpawnChance;

    public SubstanceWorldData() {

    }

    public SubstanceWorldData(Map<UUID, SubstanceData> map, int dealerSpawnDelay,  int dealerSpawnChance) {
        this.playerData.putAll(map);
        this.dealerSpawnDelay = dealerSpawnDelay;
        this.dealerSpawnChance = dealerSpawnChance;
    }

    public static SavedDataType<@NotNull SubstanceWorldData> type() {
        return TYPE;
    }

    public static SubstanceWorldData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public Map<UUID, SubstanceData> getPlayerData() {
        return this.playerData;
    }

    public int getDealerSpawnDelay() {
        return this.dealerSpawnDelay;
    }

    public int getDealerSpawnChance() {
        return this.dealerSpawnChance;
    }

    public boolean clearPlayerData(UUID uuid) {
        return this.playerData.remove(uuid) != null;
    }

    public SubstanceData getData(UUID playerId) {
        return playerData.computeIfAbsent(playerId, id -> new SubstanceData());
    }

    public void setDealerSpawnDelay(int dealerSpawnDelay) {
        if (dealerSpawnDelay != this.dealerSpawnDelay) this.setDirty();
        this.dealerSpawnDelay = dealerSpawnDelay;
    }

    public void setDealerSpawnChance(int dealerSpawnChance) {
        if (dealerSpawnChance != this.dealerSpawnChance) this.setDirty();
        this.dealerSpawnChance = dealerSpawnChance;
    }

    @Override
    public String toString() {
        return String.format("SubstanceWorldData{dealerSpawnDelay=%d,dealerSpawnChance=%d,p;ayerData=[%s]}",
                dealerSpawnDelay, dealerSpawnChance, playerData);
    }

}
