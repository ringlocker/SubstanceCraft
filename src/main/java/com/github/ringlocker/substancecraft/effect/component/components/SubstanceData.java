package com.github.ringlocker.substancecraft.effect.component.components;

import com.github.ringlocker.substancecraft.item.Drug;
import com.mojang.serialization.Codec;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record SubstanceData(List<SubstanceInstance> substances) {

    public static final Codec<SubstanceData> CODEC = ExtraCodecs.compactListCodec(SubstanceInstance.CODEC).xmap(SubstanceData::new, SubstanceData::substances);

    public SubstanceData() {
        this(new ArrayList<>());
    }

    public SubstanceInstance getInstanceOrCreateIfNotPresent(Drug drug) {
        for (SubstanceInstance substance : substances) {
            if (substance.drug() == drug) {
                return substance;
            }
        }
        SubstanceInstance instance = new SubstanceInstance(drug, 0, new ArrayList<>());
        substances.add(instance);
        return instance;
    }

    @Override
    public @NotNull String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SubstanceDataComponent{substances=[");
        for (SubstanceInstance substance : substances) {
            builder.append(substance).append(",");
        }
        builder.append("]}");
        return builder.toString();
    }


}
