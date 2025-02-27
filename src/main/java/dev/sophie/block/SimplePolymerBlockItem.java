package dev.sophie.block;

import dev.sophie.LavaSponges;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class SimplePolymerBlockItem extends BlockItem implements PolymerItem {
    private final PolymerModelData polymerModel;

    public SimplePolymerBlockItem(Block block, Settings settings, String modelId) {
        super(block, settings);
        polymerModel = PolymerResourcePackUtils.requestModel(Items.SPONGE, Identifier.of(LavaSponges.MOD_ID, modelId));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModel.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModel.value();
    }
}
