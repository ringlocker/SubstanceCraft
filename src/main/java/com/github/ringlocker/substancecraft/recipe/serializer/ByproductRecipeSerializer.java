package com.github.ringlocker.substancecraft.recipe.serializer;

import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ByproductRecipeSerializer<R extends ByproductRecipe> implements RecipeSerializer<R> {

    private final ByproductRecipe.Factory<R> factory;
    private final MapCodec<R> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, R> packetCodec;

    public ByproductRecipeSerializer(ByproductRecipe.Factory<R> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.mapCodec(
                (instance) -> instance.group(
                        Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(ByproductRecipe::getInputs),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(ByproductRecipe::getResult),
                        ItemStack.CODEC.listOf().fieldOf("byproducts").forGetter(ByproductRecipe::getByproducts),
                        Codec.INT.fieldOf("time").orElse(200).forGetter(ByproductRecipe::getTime)
                ).apply(instance, factory::create));
        packetCodec = StreamCodec.of(this::write, this::read);
    }

    @Override
    public @NotNull MapCodec<R> codec() {
        return codec;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, R> streamCodec() {
        return packetCodec;
    }

    private R read(RegistryFriendlyByteBuf buf) {
        List<Ingredient> input = new ArrayList<>();
        int size = buf.readVarInt();
        for (int i = 0; i < size; i++) {
            input.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
        }
        ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
        NonNullList<ItemStack> byproducts =  NonNullList.withSize(buf.readVarInt(), ItemStack.EMPTY);
        byproducts.replaceAll(ingredient -> ItemStack.STREAM_CODEC.decode(buf));
        return this.factory.create(input, output, byproducts, buf.readInt());
    }

    private void write(RegistryFriendlyByteBuf buf, R recipe) {
        buf.writeVarInt(recipe.getInputs().size());
        for (Ingredient ingredient : recipe.getInputs()) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
        }
        ItemStack.STREAM_CODEC.encode(buf, recipe.getResult());
        buf.writeVarInt(recipe.getByproducts().size());
        for (ItemStack byproduct : recipe.getByproducts()) {
            ItemStack.STREAM_CODEC.encode(buf, byproduct);
        }
        buf.writeInt(recipe.getTime());
    }

}
