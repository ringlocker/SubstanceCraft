package com.github.ringlocker.substancecraft.effect.component.components;

import com.github.ringlocker.substancecraft.item.Drug;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

public class Dose {

    private float amount;
    private int offset;

    public static final Codec<Dose> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("amount").orElse(0.0F).forGetter(Dose::amount),
            Codec.INT.fieldOf("offSet").orElse(0).forGetter(Dose::offset)
    ).apply(instance, Dose::new));

    public Dose(float amount, int offSet) {
        this.amount = amount;
        this.offset = offSet;
    }

    public Dose(Drug drug) {
        this(drug.getDose(), drug.getOffset());
    }

    public float amount() {
        return amount;
    }

    public int offset() {
        return offset;
    }

    public void decrementOffset() {
        offset--;
    }

    public float consume(float amount) {
        this.amount -= amount;
        return this.amount;
    }

    @Override
    public @NotNull String toString() {
        return String.format("Dose{amount=%f, offSet=%d}", amount, offset);
    }

}
