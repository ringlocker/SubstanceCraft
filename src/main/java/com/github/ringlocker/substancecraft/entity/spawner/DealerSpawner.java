package com.github.ringlocker.substancecraft.entity.spawner;

import com.github.ringlocker.substancecraft.data.SubstanceWorldData;
import com.github.ringlocker.substancecraft.entity.SubstanceCraftEntities;
import com.github.ringlocker.substancecraft.entity.entities.Dealer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DealerSpawner implements Spawner {

    private final RandomSource random = RandomSource.create();
    private static final int maxDistance = 48;

    private final SubstanceWorldData data;
    private int tickDelay;
    private int spawnDelay;
    private int spawnChance;

    public DealerSpawner(ServerLevel overworld) {
        SubstanceWorldData data = SubstanceWorldData.get(overworld);
        this.data = data;

        this.tickDelay = 1000;
        this.spawnDelay = data.getDealerSpawnDelay();
        this.spawnChance = data.getDealerSpawnChance();

        if (this.spawnDelay == 0 && this.spawnChance == 0) {
            this.spawnDelay = 10000;
            this.spawnChance = 25;
            data.setDealerSpawnDelay(this.spawnDelay);
            data.setDealerSpawnChance(this.spawnChance);
        }
    }

    @Override
    public void tick(ServerLevel level) {
        if (!level.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING)) return;
        if (!(--this.tickDelay <= 0)) return;

        this.tickDelay = 1000;
        this.spawnDelay -= 1000;
        data.setDealerSpawnDelay(this.spawnDelay);
        if (this.spawnDelay > 0) return;
        this.spawnDelay = 10000;

        int chance = this.spawnChance;
        this.spawnChance = Mth.clamp(this.spawnChance + chance, chance, 75);
        data.setDealerSpawnChance(this.spawnChance);
        if (this.random.nextInt(100) <= chance) {
            if (this.spawn(level)) {
                this.spawnChance = chance;
            }
        }
    }

    private boolean spawn(ServerLevel level) {
        Player player = level.getRandomPlayer();
        if (player == null) {
            return true;
        } else if (this.random.nextInt(10) != 0) {
            return false;
        } else {
            BlockPos playerPos = player.blockPosition();
            PoiManager poimanager = level.getPoiManager();
            Optional<BlockPos> optional = poimanager.find(poiTypeHolder -> poiTypeHolder.is(PoiTypes.MEETING), pos -> true, playerPos, maxDistance, PoiManager.Occupancy.ANY);
            BlockPos foundPos = optional.orElse(playerPos);
            BlockPos spawnPos = this.findSpawnPositionNear(level, foundPos);
            if (spawnPos != null && this.hasEnoughSpace(level, spawnPos)) {
                Dealer dealer = SubstanceCraftEntities.DEALER.spawn(level, spawnPos, EntitySpawnReason.EVENT);
                if (dealer != null) {
                    dealer.setDespawnDelay(48000);
                    dealer.setWanderTarget(foundPos);
                    dealer.setHomeTo(foundPos, 16);
                    return true;
                }
            }
            return false;
        }
    }

    @Nullable
    private BlockPos findSpawnPositionNear(LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = null;
        SpawnPlacementType spawnplacementtype = SpawnPlacements.getPlacementType(EntityType.WANDERING_TRADER);

        for (int i = 0; i < 10; i++) {
            int randX = pos.getX() + this.random.nextInt(DealerSpawner.maxDistance * 2) - DealerSpawner.maxDistance;
            int randZ = pos.getZ() + this.random.nextInt(DealerSpawner.maxDistance * 2) - DealerSpawner.maxDistance;
            int randY = levelReader.getHeight(Heightmap.Types.WORLD_SURFACE, randX, randZ);
            BlockPos randomPos = new BlockPos(randX, randY, randZ);
            if (spawnplacementtype.isSpawnPositionOk(levelReader, randomPos, EntityType.WANDERING_TRADER)) {
                blockpos = randomPos;
                break;
            }
        }
        return blockpos;
    }

    private boolean hasEnoughSpace(BlockGetter blockGetter, BlockPos blockPos) {
        for (BlockPos blockpos : BlockPos.betweenClosed(blockPos, blockPos.offset(1, 2, 1))) {
            if (!blockGetter.getBlockState(blockpos).getCollisionShape(blockGetter, blockpos).isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
