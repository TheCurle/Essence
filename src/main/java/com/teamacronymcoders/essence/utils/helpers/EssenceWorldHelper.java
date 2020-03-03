package com.teamacronymcoders.essence.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EssenceWorldHelper {

    /**
     * @param world
     * @param pos
     * @param hasTileEntity
     * @param entity
     * @param stack
     * @return
     */
    public static boolean breakBlock(World world, BlockPos pos, boolean hasTileEntity, @Nullable Entity entity, ItemStack stack) {
        BlockState blockstate = world.getBlockState(pos);
        if (blockstate.isAir(world, pos)) {
            return false;
        } else {
            IFluidState ifluidstate = world.getFluidState(pos);
            world.playEvent(2001, pos, Block.getStateId(blockstate));
            if (hasTileEntity) {
                TileEntity tileentity = blockstate.hasTileEntity() ? world.getTileEntity(pos) : null;
                Block.spawnDrops(blockstate, world, pos, tileentity, entity, stack);
            }

            return world.setBlockState(pos, ifluidstate.getBlockState(), 3);
        }
    }

    /**
     * Gets a tile entity if the location is loaded
     * @param world - world
     * @param pos   - position
     * @return tile entity if found, null if either not found or not loaded
     */
    @Nullable
    public static TileEntity getTileEntity(@Nullable World world, @Nonnull BlockPos pos) {
        if (world == null || !world.isBlockPresent(pos)) {
            return null;
        }
        return world.getTileEntity(pos);
    }

}
