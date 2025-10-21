package com.github.ringlocker.substancecraft.entity.spawner;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.WanderingTrader;
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

    private int tickDelay;
    private int spawnDelay;
    private int spawnChance;

    public DealerSpawner() {
        this.tickDelay = 1200;
        this.spawnDelay = 24000;
        this.spawnChance = 25;
    }

    @Override
    public void tick(ServerLevel level) {
        if (!level.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING)) return;
        if (!(--this.tickDelay <= 0)) return;

        this.tickDelay = 1200;
        this.spawnDelay -= 1200;

        if (this.spawnDelay > 0) return;

        this.spawnDelay = 24000;
        int chance = this.spawnChance;
        this.spawnChance = Mth.clamp(this.spawnChance + 25, 25, 75);
        if (this.random.nextInt(100) <= chance) {
            if (this.spawn(level)) {
                this.spawnChance = 25;
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
            BlockPos spawnPos = this.findSpawnPositionNear(level, foundPos, maxDistance);
            if (spawnPos != null && this.hasEnoughSpace(level, spawnPos)) {
                if (level.getBiome(spawnPos).is(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS)) {
                    return false;
                }

                // TODO made custom dealer entity, like wandering trader, custom trades
                WanderingTrader wanderingtrader = EntityType.WANDERING_TRADER.spawn(level, spawnPos, EntitySpawnReason.EVENT);
                if (wanderingtrader != null) {

                    // update delays
                    wanderingtrader.setDespawnDelay(48000);
                    wanderingtrader.setWanderTarget(foundPos);
                    wanderingtrader.setHomeTo(foundPos, 16);
                    //

                    return true;
                }
            }

            return false;
        }
    }


    @Nullable
    private BlockPos findSpawnPositionNear(LevelReader levelReader, BlockPos pos, int maxDistance) {
        BlockPos blockpos = null;
        SpawnPlacementType spawnplacementtype = SpawnPlacements.getPlacementType(EntityType.WANDERING_TRADER);

        for (int i = 0; i < 10; i++) {
            int randX = pos.getX() + this.random.nextInt(maxDistance * 2) - maxDistance;
            int randZ = pos.getZ() + this.random.nextInt(maxDistance * 2) - maxDistance;
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
