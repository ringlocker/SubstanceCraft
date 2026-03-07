package com.github.ringlocker.substancecraft.data.component;

import com.github.ringlocker.substancecraft.item.Drug;
import com.mojang.serialization.Codec;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SubstanceData {

    public static final Codec<SubstanceData> CODEC = ExtraCodecs.compactListCodec(SubstanceInstance.CODEC).xmap(SubstanceData::new, SubstanceData::substances);

    private final ArrayList<SubstanceInstance> substances;

    public SubstanceData(List<SubstanceInstance> substances) {
        this.substances = new ArrayList<>(substances);
    }

    public SubstanceData() {
        this(new ArrayList<>());
    }

    public List<SubstanceInstance> substances() {
        return substances;
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
