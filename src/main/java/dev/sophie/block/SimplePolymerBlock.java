package dev.sophie.block;

import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class SimplePolymerBlock extends Block implements PolymerTexturedBlock {
    private final BlockState model;

    public SimplePolymerBlock(Settings settings, BlockState model) {
        super(settings);
        this.model = model;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState blockState) {
        return model;
    }
}
