package dev.sophie.block;

import dev.sophie.LavaSponges;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LavaSpongeBlock extends SimplePolymerBlock {
    public LavaSpongeBlock() {
        super(
                Block.Settings.copy(Blocks.SPONGE),
                PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Identifier.of(LavaSponges.MOD_ID, "block/lava_sponge")))
        );
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (this.absorbLava(world, pos)) {
            world.setBlockState(pos, LavaSpongesBlocks.SOAKED_LAVA_SPONGE.getDefaultState(), NOTIFY_LISTENERS);
            world.playSound(null, pos, SoundEvents.BLOCK_SPONGE_ABSORB, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    private boolean absorbLava(World world, BlockPos pos) {
        return BlockPos.iterateRecursively(pos, 6, 65, (currentPos, queuer) -> {
            for (Direction direction : DIRECTIONS) {
                queuer.accept(currentPos.offset(direction));
            }
        }, currentPos -> {
            if (currentPos.equals(pos)) {
                return true;
            } else {
                BlockState blockState = world.getBlockState(currentPos);
                FluidState fluidState = world.getFluidState(currentPos);
                if (fluidState.isIn(FluidTags.LAVA)) {
                    if (blockState.getBlock() instanceof FluidDrainable fluidDrainable && !fluidDrainable.tryDrainFluid(null, world, currentPos, blockState).isEmpty()) {
                        return true;
                    }

                    if (blockState.getBlock() instanceof FluidBlock) {
                        world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), NOTIFY_ALL);
                        return true;
                    }

                }
                return false;
            }
        }) > 1;
    }
}