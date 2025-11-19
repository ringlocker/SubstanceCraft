package com.github.ringlocker.substancecraft.util.particle;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class Smoke {

    private static final float BASE_SMOKE_VELOCITY = 0.01F;
    private static final float SMOKE_VELOCITY_XZ_RANDOMNESS = 0.07F;
    private static final float SMOKE_VELOCITY_Y_RANDOMNESS = 0.05F;
    private static final float SMOKE_POSITION_Y_RANDOMNESS = 0.4F;
    private static final Vector3f UP_VECTOR = new Vector3f(0.0f, 1.6f, 0.0f);


    public static void generateSmokeParticles(Player player, Level level, float smokeVelocityMultiplier, int widthInEighthBlocks, int minRollsPerSection, int maxRollsPerSection) {

        Vector3f positionVector = new Vector3f((float) player.getX(), (float) player.getY(), (float) player.getZ())
                .add(player.getLookAngle().normalize().toVector3f().mul(0.8f))
                .add(UP_VECTOR);
        Vector3f smokeVelocity = player.getLookAngle().normalize().toVector3f()
                .add(UP_VECTOR.mul(0.5F))
                .mul(BASE_SMOKE_VELOCITY * smokeVelocityMultiplier);

        boolean even = widthInEighthBlocks % 2 == 0;
        int min = - (widthInEighthBlocks / 2);
        int max = (widthInEighthBlocks / 2) + (even ? 0 : 1);
        float increment = (float) (maxRollsPerSection - minRollsPerSection) / 3.0F;
        int rollCount1 = Math.round(minRollsPerSection + increment);
        int rollCount2 = Math.round(maxRollsPerSection - increment);

        RandomSource randomSource = level.random;
        for (int x = min; x < max; x++) {
            for (int z = min; z < max; z++) {
                float chance = randomSource.nextFloat();
                int rolls = (chance < 0.15F) ? minRollsPerSection : ((chance < 0.5) ? rollCount1 : ((chance < 0.8F) ? rollCount2 : maxRollsPerSection));
                smoke(x, z, positionVector, smokeVelocity, level, randomSource, rolls);
            }
        }
    }

    private static void smoke(int x, int z, Vector3f position, Vector3f velocity, Level level, RandomSource random, int rolls) {
        for (int i = 0; i < rolls; i++) {
            if (!level.isClientSide()) {
                ServerLevel server = (ServerLevel) level;
                server.sendParticles(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        position.x + ((double) x) / 8,
                        position.y + random.nextFloat() * SMOKE_POSITION_Y_RANDOMNESS,
                        position.z + ((double) z) / 8,
                        0,
                        velocity.x + (((double) x) / 8) * (random.nextFloat() * SMOKE_VELOCITY_XZ_RANDOMNESS),
                        velocity.y + random.nextFloat() * SMOKE_VELOCITY_Y_RANDOMNESS,
                        velocity.z + (((double) z) / 8) * (random.nextFloat() * SMOKE_VELOCITY_XZ_RANDOMNESS),
                        1
                );
            }
        }
    }

}
