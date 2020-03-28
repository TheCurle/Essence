package com.teamacronymcoders.essence.blocks.infusion;

import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.teamacronymcoders.essence.Essence;
import com.teamacronymcoders.essence.blocks.infusion.tile.InfusionTableTile;
import com.teamacronymcoders.essence.utils.helpers.EssenceVoxelHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InfusionTableBlock extends BasicTileBlock<InfusionTableTile> {

    private static final VoxelShape[] bounds = new VoxelShape[]{
        EssenceVoxelHelper.combine(
            makeCuboidShape(1, 0, 1, 15, 9, 15),
            makeCuboidShape(0, 4, 0, 2, 10, 2),
            makeCuboidShape(0, 4, 14, 2, 10, 16),
            makeCuboidShape(14, 4, 14, 16, 10, 16),
            makeCuboidShape(14, 4, 0, 16, 10, 2),
            makeCuboidShape(5, 0.1, 0.5, 11, 9.5, 3.5),
            makeCuboidShape(5, 0.1, 12.5, 11, 9.5, 15.5),
            makeCuboidShape(12.5, 0.1, 5, 15.5, 9.5, 11),
            makeCuboidShape(0.5, 0.1, 5, 3.5, 9.5, 11),
            makeCuboidShape(3, 9, 3, 13, 10, 13)
        )
    };

    public InfusionTableBlock() {
        super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).harvestTool(ToolType.PICKAXE).harvestLevel(2).notSolid().variableOpacity(), InfusionTableTile.class);
        setItemGroup(Essence.CORE_TAB);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        }
        InfusionTableTile te = worldIn.getTileEntity(pos) instanceof InfusionTableTile ? (InfusionTableTile) worldIn.getTileEntity(pos) : null;
        if (te == null) {
            return ActionResultType.PASS;
        }
        te.openGui(player);
        return ActionResultType.SUCCESS;
    }

    @Override
    public IFactory<InfusionTableTile> getTileEntityFactory() {
        return InfusionTableTile::new;
    }

    // TODO: Figure out this Black Magic, might CC Squid or Nooby for help :S
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        super.animateTick(state, world, pos, random);
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }
                if (random.nextInt(16) == 0) {
                    for(int k = 0; k <= 1; ++k) {
                        BlockPos blockpos = pos.add(i, k, j);
                        if (world.getBlockState(blockpos).getEnchantPowerBonus(world, pos) > 0) {
                            if (!world.isAirBlock(pos.add(i / 2, 0, j / 2))) {
                                break;
                            }
                            world.addParticle(ParticleTypes.ENCHANT, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + random.nextFloat()) - 0.5D, (double)((float)k - random.nextFloat() - 1.0F), (double)((float)j + random.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return bounds[0];
    }

    @Nonnull
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext selectionContext) {
        return bounds[0];
    }

    @Override
    public List<VoxelShape> getBoundingBoxes(BlockState state, IBlockReader source, BlockPos pos) {
        return Arrays.asList(bounds);
    }
}
