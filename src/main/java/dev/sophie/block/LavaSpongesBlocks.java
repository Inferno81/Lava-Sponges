package dev.sophie.block;

import dev.sophie.LavaSponges;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class LavaSpongesBlocks {
    public static final Block LAVA_SPONGE = registerBlock("lava_sponge", new LavaSpongeBlock());
    public static final Block SOAKED_LAVA_SPONGE = registerBlock("soaked_lava_sponge", new SoakedLavaSpongeBlock());

    static {
        registerBlockItem("lava_sponge", new SimplePolymerBlockItem(LAVA_SPONGE, new Item.Settings().fireproof(), "block/lava_sponge"));
        registerBlockItem("soaked_lava_sponge", new SimplePolymerBlockItem(SOAKED_LAVA_SPONGE, new Item.Settings().fireproof(), "block/soaked_lava_sponge"));
    }

    public static Block registerBlock(String name, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(LavaSponges.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, BlockItem item){
        Registry.register(Registries.ITEM, Identifier.of(LavaSponges.MOD_ID, name), item);
    }

    public static void initialize(){}
}
