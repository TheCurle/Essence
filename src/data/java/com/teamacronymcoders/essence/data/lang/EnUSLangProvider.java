package com.teamacronymcoders.essence.data.lang;

import com.teamacronymcoders.essence.Essence;
import com.teamacronymcoders.essence.compat.registrate.EssenceItemsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Objects;

public class EnUSLangProvider extends LanguageProvider {

    public EnUSLangProvider(DataGenerator generatorIn) {
        super(generatorIn, Essence.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(EssenceItemsRegistry.RAW_ESSENCE.get(), "Raw Essence");
        add(EssenceItemsRegistry.ESSENCE_CRYSTAL.get(), "Essence-Infused Crystal");
        add(EssenceItemsRegistry.ESSENCE_CRYSTAL_EMPOWERED.get(), "Essence-Infused Crystal");
        add(EssenceItemsRegistry.ESSENCE_CRYSTAL_DIVINE.get(), "Essence-Infused Crystal");
        add(EssenceItemsRegistry.ESSENCE_AXE_SUPREME.get(), "Essence-Infused Crystal");

        add(EssenceItemsRegistry.ESSENCE_STICK.get(), "Essence-Wood Sticks");
        add(EssenceItemsRegistry.ENDERITE_SCRAP.get(), "Enderite Scrap");

        add(EssenceItemsRegistry.ESSENCE_INGOT.get(), "Essence Ingot");
        add(EssenceItemsRegistry.ESSENCE_INGOT_EMPOWERED.get(), "Essence Ingot");
        add(EssenceItemsRegistry.ESSENCE_INGOT_SUPREME.get(), "Essence Ingot");
        add(EssenceItemsRegistry.ESSENCE_INGOT_DIVINE.get(), "Essence Ingot");

        add(EssenceItemsRegistry.ESSENCE_NUGGET.get(), "Essence Nugget");
        add(EssenceItemsRegistry.ESSENCE_NUGGET_EMPOWERED.get(), "Essence Nugget");
        add(EssenceItemsRegistry.ESSENCE_NUGGET_SUPREME.get(), "Essence Nugget");
        add(EssenceItemsRegistry.ESSENCE_NUGGET_DIVINE.get(), "Essence Nugget");

        add(EssenceItemsRegistry.TOME_OF_KNOWLEDGE.get(), "Tome of Bound-Knowledge");
        add(EssenceItemsRegistry.TOME_OF_EXPERIENCE.get(), "Tome of Experience");

        add(EssenceItemsRegistry.FELINIUM_JAMINITE_INGOT.get(), "Felinium Jaminite Ingot");
        add(EssenceItemsRegistry.CRAFTING_COOKIE.get(), "Crafting Cookie");
        add(EssenceItemsRegistry.ESSENCE_WRENCH.get(), "Essence-Infused Wrench");

        add(EssenceItemsRegistry.SERIALIZED_ENTITY.get(), "Serialized Entity");
        add(EssenceItemsRegistry.GLUE_BALL_ITEM.get(), "Ball of Glue");
        add(EssenceItemsRegistry.DECODER_SLINGSHOT.get(), "Decoder Slingshot");
    }

    private void add(CreativeModeTab group, String name) {
        add("itemGroup." + Objects.requireNonNull(group.getRecipeFolderName()), name);
    }

}
