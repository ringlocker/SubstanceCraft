package com.github.ringlocker.substancecraft.effect.component.components;

import com.github.ringlocker.substancecraft.item.Drug;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SubstanceInstance {

    public static final Codec<SubstanceInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.xmap(Drug::valueOf, Drug::name).fieldOf("drug").forGetter(SubstanceInstance::drug),
            Codec.FLOAT.fieldOf("amountInSystem").orElse(0.0F).forGetter(SubstanceInstance::amount),
            Dose.CODEC.listOf().fieldOf("doses").forGetter(SubstanceInstance::doses)
    ).apply(instance, SubstanceInstance::new));

    private static final List<Dose> toRemove = new ArrayList<>();

    private final Drug drug;
    private float amount;
    private final List<Dose> doses;

    public SubstanceInstance(Drug drug, float amount, List<Dose> doses) {
        this.drug = drug;
        this.amount = amount;
        this.doses = doses;
    }

    public void tick() {
        for (Dose dose : doses) {
            if (dose.offset() > 0) {
                dose.decrementOffset();
            } else {
                float consumeAmount = drug.getDose() / (float) drug().getComeUp();
                float leftover = dose.consume(consumeAmount);
                amount += consumeAmount;
                if (leftover < 0) {
                    toRemove.add(dose);
                    amount -= leftover;
                }
            }
        }
        toRemove.forEach(doses::remove);
        toRemove.clear();
        this.amount *= drug.getDecayFactor();
    }

    public Drug drug() {
        return drug;
    }

    public float amount() {
        return amount;
    }

    public List<Dose> doses() {
        return doses;
    }

    public void addDose() {
        doses.add(new Dose(drug));
    }

    @Override
    public @NotNull String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("SubstanceInstance{name=%s, amount=%f, doses=[", drug.name(), amount));
        for (Dose dose : doses) {
            builder.append(dose.toString()).append(",");
        }
        if (!doses.isEmpty()) builder.deleteCharAt(builder.length() - 1);
        builder.append("]}");
        return builder.toString();
    }

}