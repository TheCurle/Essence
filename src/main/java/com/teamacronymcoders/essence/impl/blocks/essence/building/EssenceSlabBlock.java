package com.teamacronymcoders.essence.impl.blocks.essence.building;

import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.teamacronymcoders.essence.Essence;
import com.teamacronymcoders.essence.base.CustomSlabBlock;
import com.teamacronymcoders.essence.utils.EssenceTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Consumer;

public class EssenceSlabBlock extends CustomSlabBlock {
    public EssenceSlabBlock() {
        super(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        setRegistryName(new ResourceLocation(Essence.MODID, "essence_wood_slab"));
        setItemGroup(Essence.CORE_TAB);
    }

    @Override
    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 75;
    }

    @Override
    public void registerRecipe(Consumer<IFinishedRecipe> consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(this, 4).setName(new ResourceLocation(Essence.MODID, "essence_wood_slab_mid"))
            .patternLine("   ").patternLine("ppp").patternLine("   ")
            .key('p', EssenceTags.Items.ESSENCE_WOOD_PLANKS)
            .build(consumer);
    }
}
