package com.teamacronymcoders.essence.data.model;

import com.teamacronymcoders.essence.Essence;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelProviders extends ItemModelProvider {

    public ItemModelProviders(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, Essence.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheld("raw_essence");

        handheld("essence_crystal");
        handheld("empowered_essence_crystal");
        handheld("supreme_essence_crystal");
        handheld("divine_essence_crystal");

        handheld("essence_stick");
        handheld("enderite_scrap");

        handheld("essence_ingot");
        handheld("empowered_essence_ingot");
        handheld("supreme_essence_ingot");
        handheld("divine_essence_ingot");

        handheld("essence_nugget");
        handheld("empowered_essence_nugget");
        handheld("supreme_essence_nugget");
        handheld("divine_essence_nugget");

        handheld("tome_of_knowledge");
        handheld("tome_of_experience");

        handheld("felinium_jaminite_ingot");
        handheld("crafting_cookie");
        handheld("essence_wrench");

        withExistingParent("serialized_entity", "builtin/entity");

        handheld("glue_ball");

        getBuilder("decoder_slingshot").parent(new ModelFile.ExistingModelFile(new ResourceLocation("item/generated"), existingFileHelper))
                .texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot"))
                .transforms()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT).rotation(-80, 260, -40).translation(-1, -2, 2.5f).scale(0.9f).end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT).rotation(-80, -280, 40).translation(-1, -2, 2.5f).scale(0.9f).end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT).rotation(0, -90, 25).translation(1.13f, 3.2f, 1.13f).scale(0.68f).end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT).rotation(0, 90, -25).translation(1.13f, 3.2f, 1.13f).scale(0.68f).end()
                .end()
                .override()
                .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot_pulling_0")))
                .end()
                .override()
                .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                .predicate(new ResourceLocation(Essence.MOD_ID, "pull"), 0.65f)
                .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot_pulling_1")))
                .end()
                .override()
                .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                .predicate(new ResourceLocation(Essence.MOD_ID, "pull"), 0.9f)
                .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot_pulling_2")))
                .end();

        withExistingParent("decoder_slingshot_pulling_0", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot")).texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot_pulling_0"));
        withExistingParent("decoder_slingshot_pulling_1", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot")).texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot_pulling_1"));
        withExistingParent("decoder_slingshot_pulling_2", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot")).texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/decoder_slingshot_pulling_2"));

    }

    /**
     * Create a new Item Model with the item/generated parent.
     * Simply pass the name of the item.
     * The modid is set automatically.
     *
     * Texture must go in items/<name>.
     * @param name the registry name of the item
     * @return an ItemModelBuilder.
     */
    private ItemModelBuilder generated(String name) {
        return singleTexture("item/" + name,       // destination
                mcLoc("item/generated"),           // "parent": ###
                "layer0",                       // ###: TEXTURE
                modLoc("items/" + name));          // LAYER: ###
    }

    /**
     * Create a new Item Model with the item/handheld parent.
     * Simply pass the name of the item.
     * The modid is set automatically.
     *
     * Texture must go in items/<name>.
     * @param name the registry name of the item
     * @return an ItemModelBuilder.
     */
    private ItemModelBuilder handheld(String name) {
        return singleTexture("item/" + name,       // destination
                mcLoc("item/handheld"),           // "parent": ###
                "layer0",                       // ###: TEXTURE
                modLoc("items/" + name));          // LAYER: ###
    }

}