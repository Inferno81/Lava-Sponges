package dev.sophie.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import dev.sophie.LavaSponges;

import java.util.ArrayList;
import java.util.List;

public class SoakedLavaSpongeBlock extends SimplePolymerBlock {
    public SoakedLavaSpongeBlock() {
        super(
                Block.Settings.copy(Blocks.WET_SPONGE),
                PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Identifier.of(LavaSponges.MOD_ID, "block/soaked_lava_sponge")))
        );
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        List<BlockPos> positions = new ArrayList<>();
        positions.add(pos.north());
        positions.add(pos.south());
        positions.add(pos.east());
        positions.add(pos.west());
        positions.add(pos.up());
        for (BlockPos position : positions)
            if (world.getBlockState(position).getBlock() == Blocks.WATER)
                drySponge(world, pos);
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (pos.getY() <= sourcePos.getY() && world.getBlockState(sourcePos).getBlock() == Blocks.WATER)
            drySponge(world, pos);
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    private void drySponge(World world, BlockPos pos) {
        world.setBlockState(pos, LavaSpongesBlocks.LAVA_SPONGE.getDefaultState(), 3);
        world.syncWorldEvent(2009, pos, 0);
        world.playSound(null, pos, SoundEvents.BLOCK_WET_SPONGE_DRIES, SoundCategory.BLOCKS, 1.0F, (1.0F + world.getRandom().nextFloat() * 0.2F) * 0.7F);
    }
}