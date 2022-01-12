package com.teamacronymcoders.essence.data.tags;

import com.teamacronymcoders.essence.Essence;
import com.teamacronymcoders.essence.common.util.EssenceTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagsProviders extends ItemTagsProvider {

    public ItemTagsProviders(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, Essence.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_EMPOWERED);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_SUPREME);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_DIVINE);

        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK);
        tag(EssenceTags.EssenceItemTags.ENDERITE_SCRAP);

        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE);

        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_EMPOWERED);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_SUPREME);
        tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_DIVINE);

    }
}
