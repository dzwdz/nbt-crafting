package de.siphalor.nbtcrafting.recipe;

import com.google.gson.JsonObject;
import de.siphalor.nbtcrafting.NbtCrafting;
import de.siphalor.nbtcrafting.api.ServerRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;

public class AnvilRecipe extends IngredientRecipe<Inventory> implements ServerRecipe {
	protected int levels = 0;

	public static final IngredientRecipe.Serializer<AnvilRecipe> SERIALIZER = new IngredientRecipe.Serializer<>(AnvilRecipe::new);

	public AnvilRecipe(Identifier identifier, Ingredient base, Ingredient ingredient, ItemStack result) {
		super(identifier, base, ingredient, result);
	}

	public int getLevels() {
		return levels;
	}

	@Override
	public RecipeType<?> getType() {
		return NbtCrafting.ANVIL_RECIPE_TYPE;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public void readCustomData(JsonObject json) {
		super.readCustomData(json);
		levels = JsonHelper.getInt(json, "levels", 0);
	}

	@Override
	public void readCustomData(PacketByteBuf buf) {
		super.readCustomData(buf);
		levels = buf.readVarInt();
	}

	@Override
	public void writeCustomData(PacketByteBuf buf) {
		super.writeCustomData(buf);
		buf.writeVarInt(levels);
	}
}
