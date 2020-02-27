package com.teamacronymcoders.essence.impl.blocks.essence;

import com.hrznstudio.titanium.block.BasicBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.teamacronymcoders.essence.utils.EssenceObjectHolders;
import com.teamacronymcoders.essence.utils.EssenceReferences;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class EssenceBlock extends BasicBlock {

    public EssenceBlock() {
        super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).speedFactor(1.25f));
        setRegistryName(new ResourceLocation(EssenceReferences.MODID, "essence_infused_block"));
        setItemGroup(EssenceReferences.CORE_TAB);
    }

    @Override
    public void registerRecipe(Consumer<IFinishedRecipe> consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(EssenceObjectHolders.ESSENCE_INFUSED_METAL)
            .patternLine("nnn").patternLine("nnn").patternLine("nnn")
            .key('n', EssenceObjectHolders.ESSENCE_INFUSED_METAL_NUGGET)
            .build(consumer);
        TitaniumShapedRecipeBuilder.shapedRecipe(EssenceObjectHolders.ESSENCE_INFUSED_METAL_BLOCK)
            .patternLine("iii").patternLine("iii").patternLine("iii")
            .key('i', EssenceObjectHolders.ESSENCE_INFUSED_METAL)
            .build(consumer);
    }
}
