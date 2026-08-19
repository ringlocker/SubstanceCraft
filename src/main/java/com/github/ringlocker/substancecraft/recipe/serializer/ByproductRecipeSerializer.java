package com.github.ringlocker.substancecraft.recipe.serializer;

import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ByproductRecipeSerializer<R extends ByproductRecipe> {

    private final ByproductRecipe.Factory<R> factory;
    private final MapCodec<R> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, R> packetCodec;

    public ByproductRecipeSerializer(ByproductRecipe.Factory<R> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.mapCodec(
                (instance) -> instance.group(
                        Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(ByproductRecipe::getInputs),
                        ItemStackTemplate.CODEC.fieldOf("result").forGetter(ByproductRecipe::getResult),
                        ItemStackTemplate.CODEC.listOf().fieldOf("byproducts").forGetter(ByproductRecipe::getByproducts),
                        ItemStackTemplate.CODEC.optionalFieldOf("catalyst").forGetter(ByproductRecipe::getCatalyst),
                        Codec.INT.fieldOf("time").orElse(200).forGetter(ByproductRecipe::getTime)
                ).apply(instance, factory::create));
        packetCodec = StreamCodec.of(this::write, this::read);
    }

    public @NotNull MapCodec<R> codec() {
        return codec;
    }

    public @NotNull StreamCodec<RegistryFriendlyByteBuf, R> streamCodec() {
        return packetCodec;
    }

    private R read(RegistryFriendlyByteBuf buf) {
        List<Ingredient> input = new ArrayList<>();
        int size = buf.readVarInt();
        for (int i = 0; i < size; i++) {
            input.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
        }
        ItemStackTemplate output = ItemStackTemplate.STREAM_CODEC.decode(buf);
        ArrayList<ItemStackTemplate> byproducts = new ArrayList<>();
        size = buf.readVarInt();
        for (int i = 0; i < size; i++) {
            byproducts.add(ItemStackTemplate.STREAM_CODEC.decode(buf));
        }
        boolean hasCatalyst = buf.readBoolean();
        ItemStackTemplate catalyst = null;
        if (hasCatalyst) catalyst = ItemStackTemplate.STREAM_CODEC.decode(buf);
        return this.factory.create(input, output, byproducts, Optional.ofNullable(catalyst), buf.readInt());
    }

    private void write(RegistryFriendlyByteBuf buf, R recipe) {
        buf.writeVarInt(recipe.getInputs().size());
        for (Ingredient ingredient : recipe.getInputs()) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
        }
        ItemStackTemplate.STREAM_CODEC.encode(buf, recipe.getResult());
        buf.writeVarInt(recipe.getByproducts().size());
        for (ItemStackTemplate byproduct : recipe.getByproducts()) {
            ItemStackTemplate.STREAM_CODEC.encode(buf, byproduct);
        }
        boolean hasCatalyst = recipe.getCatalyst().isPresent();
        buf.writeBoolean(hasCatalyst);
        if (hasCatalyst) ItemStackTemplate.STREAM_CODEC.encode(buf, recipe.getCatalyst().get());
        buf.writeInt(recipe.getTime());
    }

}
