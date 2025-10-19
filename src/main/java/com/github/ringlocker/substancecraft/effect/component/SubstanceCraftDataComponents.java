package com.github.ringlocker.substancecraft.effect.component;

import com.github.ringlocker.substancecraft.effect.component.components.SubstanceData;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class SubstanceCraftDataComponents {

   // public static final DataComponentType<SubstanceData> SUBSTANCE_DATA = register(
  //          "substance_data", dataBuilder -> dataBuilder.persistent(SubstanceData.CODEC).networkSynchronized(SubstanceData.STREAM_CODEC)
  //  );

    //private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator) {
 //       return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id, builderUnaryOperator.apply(DataComponentType.builder()).build());
 //   }

    public static void registerComponents() {
    }

}
