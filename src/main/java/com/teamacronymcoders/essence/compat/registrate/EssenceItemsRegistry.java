package com.teamacronymcoders.essence.compat.registrate;

import com.hrznstudio.titanium.item.BasicItem;
import com.teamacronymcoders.essence.Essence;
import com.teamacronymcoders.essence.common.item.CraftingCookieItem;
import com.teamacronymcoders.essence.common.item.essence.EssenceCrystalItem;
import com.teamacronymcoders.essence.common.item.essence.EssenceIngotItem;
import com.teamacronymcoders.essence.common.item.essence.EssenceNuggetItem;
import com.teamacronymcoders.essence.common.item.misc.DecoderSlingshot;
import com.teamacronymcoders.essence.common.item.misc.GlueBallItem;
import com.teamacronymcoders.essence.common.item.tome.TomeOfKnowledgeItem;
import com.teamacronymcoders.essence.common.item.tome.experience.TomeOfExperienceItem;
import com.teamacronymcoders.essence.common.item.tool.*;
import com.teamacronymcoders.essence.common.item.wrench.EssenceWrench;
import com.teamacronymcoders.essence.common.item.wrench.SerializedEntityItem;
import com.teamacronymcoders.essence.common.util.EssenceTags;
import com.teamacronymcoders.essence.common.util.tier.EssenceItemTiers;
import com.teamacronymcoders.essence.common.util.tier.EssenceToolTiers;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Central registration location for Essence's Items.
 * When adding an item here, make sure to add Localization {@see EnUSLangProvider.class},
 * a Model {@see ItemModelProviders.class} and a Tag {@see ItemTagsProviders.class} if appropriate.
 *
 * @author Lanse505, Curle
 */
@SuppressWarnings("unchecked")
public class EssenceItemsRegistry {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Essence.MOD_ID);

    // CORE ITEMS
    public static final RegistryObject<Item> RAW_ESSENCE = ITEMS.register("raw_essence", () ->
            new Item(new Item.Properties().fireResistant().tab(Essence.CORE_TAB))
    );

    public static final RegistryObject<Item> ESSENCE_CRYSTAL = ITEMS.register("essence_crystal", () ->
            new EssenceCrystalItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.BASIC)
    );

    public static final RegistryObject<Item> ESSENCE_CRYSTAL_EMPOWERED = ITEMS.register("empowered_essence_crystal", () ->
            new EssenceCrystalItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.EMPOWERED)
    );

    public static final RegistryObject<Item> ESSENCE_CRYSTAL_SUPREME = ITEMS.register("supreme_essence_crystal", () ->
            new EssenceCrystalItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.SUPREME)
    );

    public static final RegistryObject<Item> ESSENCE_CRYSTAL_DIVINE = ITEMS.register("divine_essence_crystal", () ->
            new EssenceCrystalItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.DIVINE)
    );

    public static final RegistryObject<Item> ESSENCE_STICK = ITEMS.register("essence_stick", () ->
            new BasicItem(new Item.Properties().tab(Essence.CORE_TAB))
    );

    public static final RegistryObject<Item> ENDERITE_SCRAP = ITEMS.register("enderite_scrap", () ->
            new BasicItem(new Item.Properties().tab(Essence.CORE_TAB))
    );

    // Tiered Items
    public static final RegistryObject<Item> ESSENCE_INGOT = ITEMS.register("essence_ingot", () ->
            new EssenceIngotItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.BASIC)
    );

    public static final RegistryObject<Item> ESSENCE_INGOT_EMPOWERED = ITEMS.register("empowered_essence_ingot", () ->
            new EssenceIngotItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.EMPOWERED)
    );

    public static final RegistryObject<Item> ESSENCE_INGOT_SUPREME = ITEMS.register("supreme_essence_ingot", () ->
            new EssenceIngotItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.SUPREME)
    );

    public static final RegistryObject<Item> ESSENCE_INGOT_DIVINE = ITEMS.register("divine_essence_ingot", () ->
            new EssenceIngotItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.DIVINE)
    );

    public static final RegistryObject<Item> ESSENCE_NUGGET = ITEMS.register("essence_nugget", () ->
            new EssenceNuggetItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.BASIC)
    );

    public static final RegistryObject<Item> ESSENCE_NUGGET_EMPOWERED = ITEMS.register("empowered_essence_nugget", () ->
            new EssenceNuggetItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.EMPOWERED)
    );

    public static final RegistryObject<Item> ESSENCE_NUGGET_SUPREME = ITEMS.register("supreme_essence_nugget", () ->
            new EssenceNuggetItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.SUPREME)
    );

    public static final RegistryObject<Item> ESSENCE_NUGGET_DIVINE = ITEMS.register("divine_essence_nugget", () ->
            new EssenceNuggetItem(new Item.Properties().tab(Essence.CORE_TAB), EssenceItemTiers.DIVINE)
    );

    // TOMES

    public static final RegistryObject<Item> TOME_OF_KNOWLEDGE = ITEMS.register("tome_of_knowledge", () ->
            new TomeOfKnowledgeItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).tab(Essence.CORE_TAB))
    );

    public static final RegistryObject<Item> TOME_OF_EXPERIENCE = ITEMS.register("tome_of_experience", () ->
            new TomeOfExperienceItem(new Item.Properties().stacksTo(1).tab(Essence.CORE_TAB))
    );

    // Misc
    public static final RegistryObject<Item> FELINIUM_JAMINITE_INGOT = ITEMS.register("felinium_jaminite_ingot", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(Essence.CORE_TAB))
    );

    public static FoodProperties CRAFTING_COOKIE_FOOD = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().build();

    public static final RegistryObject<Item> CRAFTING_COOKIE = ITEMS.register("crafting_cookie", () ->
            new CraftingCookieItem(new Item.Properties().rarity(Rarity.RARE).food(CRAFTING_COOKIE_FOOD).tab(Essence.CORE_TAB))
    );

    public static final RegistryObject<Item> ESSENCE_WRENCH = ITEMS.register("essence_wrench", () ->
            new EssenceWrench(new Item.Properties().stacksTo(1).defaultDurability(2048).rarity(Rarity.RARE).tab(Essence.TOOL_TAB))
    );

    public static final RegistryObject<Item> SERIALIZED_ENTITY = ITEMS.register("serialized_entity", () ->
            new SerializedEntityItem(new Item.Properties().defaultDurability(1))
    );

    public static final RegistryObject<Item> GLUE_BALL_ITEM = ITEMS.register("glue_ball", () ->
            new GlueBallItem(new Item.Properties().tab(Essence.CORE_TAB))
    );

    public static final RegistryObject<Item> DECODER_SLINGSHOT = ITEMS.register("decoder_slingshot", () ->
            new DecoderSlingshot(new Item.Properties().stacksTo(1).defaultDurability(640).tab(Essence.TOOL_TAB))
    );

    /*
    public static final RegistryObject<Item> name = ITEMS.register("", () ->
            new Item(new Item.Properties())
    );
     */



    public static final Map<Class<?>, BiFunction<Item.Properties, EssenceToolTiers, Item>> constructorMap = new HashMap<>();
    public static final Map<Class<?>, ResourceLocation> textureMap = new HashMap<>();

    static {
        // Constructors
        constructorMap.put(EssenceAxe.class, EssenceAxe::new);
        constructorMap.put(EssencePickaxe.class, EssencePickaxe::new);
        constructorMap.put(EssenceShovel.class, EssenceShovel::new);
        constructorMap.put(EssenceSword.class, EssenceSword::new);
        constructorMap.put(EssenceHoe.class, EssenceHoe::new);
        constructorMap.put(EssenceOmniTool.class, EssenceOmniTool::new);
        constructorMap.put(EssenceShear.class, EssenceShear::new);
        constructorMap.put(EssenceBow.class, EssenceBow::new);

        // Textures
        textureMap.put(EssenceAxe.class, new ResourceLocation(Essence.MOD_ID, "item/essence_axe"));
        textureMap.put(EssencePickaxe.class, new ResourceLocation(Essence.MOD_ID, "item/essence_pickaxe"));
        textureMap.put(EssenceShovel.class, new ResourceLocation(Essence.MOD_ID, "item/essence_shovel"));
        textureMap.put(EssenceSword.class, new ResourceLocation(Essence.MOD_ID, "item/essence_sword"));
        textureMap.put(EssenceHoe.class, new ResourceLocation(Essence.MOD_ID, "item/essence_hoe"));
        textureMap.put(EssenceOmniTool.class, new ResourceLocation(Essence.MOD_ID, "item/essence_omnitool"));
        textureMap.put(EssenceShear.class, new ResourceLocation(Essence.MOD_ID, "item/essence_shear"));
        textureMap.put(EssenceBow.class, new ResourceLocation(Essence.MOD_ID, "item/essence_bow"));
    }

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // CORE

    // Tiered Materials

    // Tomes


    // Tools
    // MISC



    // Tier 1
    public static ItemEntry<EssenceAxe> ESSENCE_AXE = getTool("_essence_axe", "Essence Axe", EssenceAxe.class, EssenceToolTiers.ESSENCE, axeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), new ResourceLocation(Essence.MOD_ID, "essence_axe")), EssenceTags.EssenceItemTags.ESSENCE_AXE);
    public static ItemEntry<EssencePickaxe> ESSENCE_PICKAXE = getTool("_essence_pickaxe", "Essence Pickaxe", EssencePickaxe.class, EssenceToolTiers.ESSENCE, pickaxeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), new ResourceLocation(Essence.MOD_ID, "essence_pickaxe")), EssenceTags.EssenceItemTags.ESSENCE_PICKAXE);
    public static ItemEntry<EssenceShovel> ESSENCE_SHOVEL = getTool("_essence_shovel", "Essence Shovel", EssenceShovel.class, EssenceToolTiers.ESSENCE, shovelRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), new ResourceLocation(Essence.MOD_ID, "essence_shovel")), EssenceTags.EssenceItemTags.ESSENCE_SHOVEL);
    public static ItemEntry<EssenceSword> ESSENCE_SWORD = getTool("_essence_sword", "Essence Sword", EssenceSword.class, EssenceToolTiers.ESSENCE, swordRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), new ResourceLocation(Essence.MOD_ID, "essence_sword")), EssenceTags.EssenceItemTags.ESSENCE_SWORD);
    public static ItemEntry<EssenceHoe> ESSENCE_HOE = getTool("_essence_hoe", "Essence Hoe", EssenceHoe.class, EssenceToolTiers.ESSENCE, hoeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), new ResourceLocation(Essence.MOD_ID, "essence_hoe")), EssenceTags.EssenceItemTags.ESSENCE_HOE);
    public static ItemEntry<EssenceOmniTool> ESSENCE_OMNITOOL = getTool("_essence_omnitool", "Essence Omni-Tool", EssenceOmniTool.class, EssenceToolTiers.ESSENCE, omnitoolRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_AXE), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_SHOVEL), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_PICKAXE), new ResourceLocation(Essence.MOD_ID, "essence_omnitool")), EssenceTags.EssenceItemTags.ESSENCE_OMNITOOL);
    public static ItemEntry<EssenceShear> ESSENCE_SHEAR = getTool("_essence_shear", "Essence Shear", EssenceShear.class, EssenceToolTiers.ESSENCE, shearRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), new ResourceLocation(Essence.MOD_ID, "essence_shear")), EssenceTags.EssenceItemTags.ESSENCE_SHEAR);
    public static ItemEntry<EssenceBow> ESSENCE_BOW = getTool("_essence_bow", "Essence Bow", EssenceBow.class, EssenceToolTiers.ESSENCE, bowRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET), new ResourceLocation(Essence.MOD_ID, "essence_bow")), EssenceTags.EssenceItemTags.ESSENCE_BOW);

    // Tier 2
    public static ItemEntry<EssenceAxe> ESSENCE_AXE_EMPOWERED = getTool("_essence_axe", "Essence Axe", EssenceAxe.class, EssenceToolTiers.EMPOWERED, axeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_axe_empowered")), EssenceTags.EssenceItemTags.ESSENCE_AXE_EMPOWERED);
    public static ItemEntry<EssencePickaxe> ESSENCE_PICKAXE_EMPOWERED = getTool("_essence_pickaxe", "Essence Pickaxe", EssencePickaxe.class, EssenceToolTiers.EMPOWERED, pickaxeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_pickaxe_empowered")), EssenceTags.EssenceItemTags.ESSENCE_PICKAXE_EMPOWERED);
    public static ItemEntry<EssenceShovel> ESSENCE_SHOVEL_EMPOWERED = getTool("_essence_shovel", "Essence Shovel", EssenceShovel.class, EssenceToolTiers.EMPOWERED, shovelRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_shovel_empowered")), EssenceTags.EssenceItemTags.ESSENCE_SHOVEL_EMPOWERED);
    public static ItemEntry<EssenceSword> ESSENCE_SWORD_EMPOWERED = getTool("_essence_sword", "Essence Sword", EssenceSword.class, EssenceToolTiers.EMPOWERED, swordRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_sword_empowered")), EssenceTags.EssenceItemTags.ESSENCE_SWORD_EMPOWERED);
    public static ItemEntry<EssenceHoe> ESSENCE_HOE_EMPOWERED = getTool("_essence_hoe", "Essence Hoe", EssenceHoe.class, EssenceToolTiers.EMPOWERED, hoeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_hoe_empowered")), EssenceTags.EssenceItemTags.ESSENCE_HOE_EMPOWERED);
    public static ItemEntry<EssenceOmniTool> ESSENCE_OMNITOOL_EMPOWERED = getTool("_essence_omnitool", "Essence Omni-Tool", EssenceOmniTool.class, EssenceToolTiers.EMPOWERED, omnitoolRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_AXE_EMPOWERED), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_SHOVEL_EMPOWERED), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_PICKAXE_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_omnitool_empowered")), EssenceTags.EssenceItemTags.ESSENCE_OMNITOOL_EMPOWERED);
    public static ItemEntry<EssenceShear> ESSENCE_SHEAR_EMPOWERED = getTool("_essence_shear", "Essence Shear", EssenceShear.class, EssenceToolTiers.EMPOWERED, shearRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_shear_empowered")), EssenceTags.EssenceItemTags.ESSENCE_SHEAR_EMPOWERED);
    public static ItemEntry<EssenceBow> ESSENCE_BOW_EMPOWERED = getTool("_essence_bow", "Essence Bow", EssenceBow.class, EssenceToolTiers.EMPOWERED, bowRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_EMPOWERED), new ResourceLocation(Essence.MOD_ID, "essence_bow_empowered")), EssenceTags.EssenceItemTags.ESSENCE_BOW_EMPOWERED);
    // Tier 3
    public static ItemEntry<EssenceAxe> ESSENCE_AXE_SUPREME = getTool("_essence_axe", "Essence Axe", EssenceAxe.class, EssenceToolTiers.SUPREME, axeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_axe_supreme")), EssenceTags.EssenceItemTags.ESSENCE_AXE_SUPREME);
    public static ItemEntry<EssencePickaxe> ESSENCE_PICKAXE_SUPREME = getTool("_essence_pickaxe", "Essence Pickaxe", EssencePickaxe.class, EssenceToolTiers.SUPREME, pickaxeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_pickaxe_supreme")), EssenceTags.EssenceItemTags.ESSENCE_PICKAXE_SUPREME);
    public static ItemEntry<EssenceShovel> ESSENCE_SHOVEL_SUPREME = getTool("_essence_shovel", "Essence Shovel", EssenceShovel.class, EssenceToolTiers.SUPREME, shovelRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_shovel_supreme")), EssenceTags.EssenceItemTags.ESSENCE_SHOVEL_SUPREME);
    public static ItemEntry<EssenceSword> ESSENCE_SWORD_SUPREME = getTool("_essence_sword", "Essence Sword", EssenceSword.class, EssenceToolTiers.SUPREME, swordRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_sword_supreme")), EssenceTags.EssenceItemTags.ESSENCE_SWORD_SUPREME);
    public static ItemEntry<EssenceHoe> ESSENCE_HOE_SUPREME = getTool("_essence_hoe", "Essence Hoe", EssenceHoe.class, EssenceToolTiers.SUPREME, hoeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_hoe_supreme")), EssenceTags.EssenceItemTags.ESSENCE_HOE_SUPREME);
    public static ItemEntry<EssenceOmniTool> ESSENCE_OMNITOOL_SUPREME = getTool("_essence_omnitool", "Essence Omni-Tool", EssenceOmniTool.class, EssenceToolTiers.SUPREME, omnitoolRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_AXE_SUPREME), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_SHOVEL_SUPREME), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_PICKAXE_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_omnitool_supreme")), EssenceTags.EssenceItemTags.ESSENCE_OMNITOOL_SUPREME);
    public static ItemEntry<EssenceShear> ESSENCE_SHEAR_SUPREME = getTool("_essence_shear", "Essence Shear", EssenceShear.class, EssenceToolTiers.SUPREME, shearRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_shear_supreme")), EssenceTags.EssenceItemTags.ESSENCE_SHEAR_SUPREME);
    public static ItemEntry<EssenceBow> ESSENCE_BOW_SUPREME = getTool("_essence_bow", "Essence Bow", EssenceBow.class, EssenceToolTiers.SUPREME, bowRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_SUPREME), new ResourceLocation(Essence.MOD_ID, "essence_bow_supreme")), EssenceTags.EssenceItemTags.ESSENCE_BOW_SUPREME);
    // Tier 4
    public static ItemEntry<EssenceAxe> ESSENCE_AXE_DIVINE = getTool("_essence_axe", "Essence Axe", EssenceAxe.class, EssenceToolTiers.DIVINE, axeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_axe_divine")), EssenceTags.EssenceItemTags.ESSENCE_AXE_DIVINE);
    public static ItemEntry<EssencePickaxe> ESSENCE_PICKAXE_DIVINE = getTool("_essence_pickaxe", "Essence Pickaxe", EssencePickaxe.class, EssenceToolTiers.DIVINE, pickaxeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_pickaxe_divine")), EssenceTags.EssenceItemTags.ESSENCE_PICKAXE_DIVINE);
    public static ItemEntry<EssenceShovel> ESSENCE_SHOVEL_DIVINE = getTool("_essence_shovel", "Essence Shovel", EssenceShovel.class, EssenceToolTiers.DIVINE, shovelRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_shovel_divine")), EssenceTags.EssenceItemTags.ESSENCE_SHOVEL_DIVINE);
    public static ItemEntry<EssenceSword> ESSENCE_SWORD_DIVINE = getTool("_essence_sword", "Essence Sword", EssenceSword.class, EssenceToolTiers.DIVINE, swordRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_sword_divine")), EssenceTags.EssenceItemTags.ESSENCE_SWORD_DIVINE);
    public static ItemEntry<EssenceHoe> ESSENCE_HOE_DIVINE = getTool("_essence_hoe", "Essence Hoe", EssenceHoe.class, EssenceToolTiers.DIVINE, hoeRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_hoe_divine")), EssenceTags.EssenceItemTags.ESSENCE_HOE_DIVINE);
    public static ItemEntry<EssenceOmniTool> ESSENCE_OMNITOOL_DIVINE = getTool("_essence_omnitool", "Essence Omni-Tool", EssenceOmniTool.class, EssenceToolTiers.DIVINE, omnitoolRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_AXE_DIVINE), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_SHOVEL_DIVINE), () -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_PICKAXE_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_omnitool_divine")), EssenceTags.EssenceItemTags.ESSENCE_OMNITOOL_DIVINE);
    public static ItemEntry<EssenceShear> ESSENCE_SHEAR_DIVINE = getTool("_essence_shear", "Essence Shear", EssenceShear.class, EssenceToolTiers.DIVINE, shearRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_shear_divine")), EssenceTags.EssenceItemTags.ESSENCE_SHEAR_DIVINE);
    public static ItemEntry<EssenceBow> ESSENCE_BOW_DIVINE = getTool("_essence_bow", "Essence Bow", EssenceBow.class, EssenceToolTiers.DIVINE, bowRecipe(() -> DataIngredient.tag(EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_DIVINE), new ResourceLocation(Essence.MOD_ID, "essence_bow_divine")), EssenceTags.EssenceItemTags.ESSENCE_BOW_DIVINE);

    @SuppressWarnings("unchecked")
    public static <T extends Item> ItemEntry<T> getTool(String name, String lang, Class<T> tool, EssenceToolTiers tier, NonNullBiConsumer<DataGenContext<Item, Item>, RegistrateRecipeProvider> recipeConsumer, Tag.Named<Item>... tags) {
        String tierType = tier == EssenceToolTiers.ESSENCE ? "" : tier.toString().toLowerCase();
        String entryName = tierType.equals("") ? name.substring(1) : tierType + name;
        return (ItemEntry<T>) Essence.ESSENCE_REGISTRATE.object(entryName)
                .item(properties -> constructorMap.get(tool).apply(properties, tier))
                .model((context, provider) -> {
                    String id = name.substring(1);
                    if (!tool.equals(EssenceBow.class)) {
                        if (tier == EssenceToolTiers.ESSENCE) {
                            provider.handheld(context, new ResourceLocation(Essence.MOD_ID, "item/" + id));
                        } else {
                            provider.handheld(context, new ResourceLocation(Essence.MOD_ID, "item/" + tierType + "_" + id));
                        }
                    } else {
                        if (tier == EssenceToolTiers.ESSENCE) {
                            provider.generated(context)
                                    .texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/essence_bow"))
                                    .transforms()
                                    .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT).rotation(-80, 260, -40).translation(-1, -2, 2.5f).scale(0.9f).end()
                                    .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT).rotation(-80, -280, 40).translation(-1, -2, 2.5f).scale(0.9f).end()
                                    .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT).rotation(0, -90, 25).translation(1.13f, 3.2f, 1.13f).scale(0.68f).end()
                                    .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT).rotation(0, 90, -25).translation(1.13f, 3.2f, 1.13f).scale(0.68f).end()
                                    .end()
                                    .override()
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                                    .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Essence.MOD_ID, "item/essence_bow_pulling_0")))
                                    .end()
                                    .override()
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pull"), 0.65f)
                                    .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Essence.MOD_ID, "item/essence_bow_pulling_1")))
                                    .end()
                                    .override()
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pull"), 0.9f)
                                    .model(new ModelFile.UncheckedModelFile(new ResourceLocation(Essence.MOD_ID, "item/essence_bow_pulling_2")))
                                    .end();
                            provider.withExistingParent("essence_bow_pulling_0", new ResourceLocation(Essence.MOD_ID, "item/essence_bow")).texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/essence_bow_pulling_0"));
                            provider.withExistingParent("essence_bow_pulling_1", new ResourceLocation(Essence.MOD_ID, "item/essence_bow")).texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/essence_bow_pulling_1"));
                            provider.withExistingParent("essence_bow_pulling_2", new ResourceLocation(Essence.MOD_ID, "item/essence_bow")).texture("layer0", new ResourceLocation(Essence.MOD_ID, "item/essence_bow_pulling_2"));
                        } else {
                            provider.generated(context, new ResourceLocation(Essence.MOD_ID, "item/" + tierType + "_" + "essence_bow"))
                                    .override()
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                                    .model(provider.getExistingFile(new ResourceLocation(Essence.MOD_ID, "item/" + tierType + "_" + "essence_bow_pulling_0")))
                                    .end()
                                    .override()
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pull"), 0.65f)
                                    .model(provider.getExistingFile(new ResourceLocation(Essence.MOD_ID, "item/" + tierType + "_" + "essence_bow_pulling_1")))
                                    .end()
                                    .override()
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pulling"), 1)
                                    .predicate(new ResourceLocation(Essence.MOD_ID, "pull"), 0.9f)
                                    .model(provider.getExistingFile(new ResourceLocation(Essence.MOD_ID, "item/" + tierType + "_" + "essence_bow_pulling_2")))
                                    .end();
                        }
                    }
                })
                .lang(lang)
                .tag(tags)
                .recipe(recipeConsumer)
                .tab(() -> Essence.TOOL_TAB)
                .register();
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> axeRecipe(Supplier<DataIngredient> ingot, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern("ii ").pattern("is ").pattern(" s ").define('i', ingot.get()).define('s', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).unlockedBy("has_" + provider.safeName(ingot.get()), ingot.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> pickaxeRecipe(Supplier<DataIngredient> ingot, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern("iii").pattern(" s ").pattern(" s ").define('i', ingot.get()).define('s', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).unlockedBy("has_" + provider.safeName(ingot.get()), ingot.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> shovelRecipe(Supplier<DataIngredient> ingot, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern(" i ").pattern(" s ").pattern(" s ").define('i', ingot.get()).define('s', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).unlockedBy("has_" + provider.safeName(ingot.get()), ingot.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> swordRecipe(Supplier<DataIngredient> ingot, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern(" i ").pattern(" i ").pattern(" s ").define('i', ingot.get()).define('s', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).unlockedBy("has_" + provider.safeName(ingot.get()), ingot.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> shearRecipe(Supplier<DataIngredient> ingot, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern(" i").pattern("i ").define('i', ingot.get()).unlockedBy("has_" + provider.safeName(ingot.get()), ingot.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> bowRecipe(Supplier<DataIngredient> nugget, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern("stn").pattern("s t").pattern("stn").define('t', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).define('s', DataIngredient.tag(Tags.Items.STRING)).define('n', nugget.get()).unlockedBy("has_" + provider.safeName(nugget.get()), nugget.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> omnitoolRecipe(Supplier<DataIngredient> axe, Supplier<DataIngredient> shovel, Supplier<DataIngredient> pickaxe, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern("asp").pattern(" t ").pattern(" t ").define('a', axe.get()).define('s', shovel.get()).define('p', pickaxe.get()).define('t', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).unlockedBy("has_" + provider.safeName(axe.get()), axe.get().getCritereon(provider)).unlockedBy("has_" + provider.safeName(shovel.get()), shovel.get().getCritereon(provider)).unlockedBy("has_" + provider.safeName(pickaxe.get()), pickaxe.get().getCritereon(provider)).save(provider, rl);
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> hoeRecipe(Supplier<DataIngredient> ingot, ResourceLocation rl) {
        return (context, provider) -> ShapedRecipeBuilder.shaped(context.get(), 1).pattern("ii ").pattern(" s ").pattern(" s ").define('i', ingot.get()).define('s', EssenceTags.EssenceItemTags.ESSENCE_INFUSED_STICK).unlockedBy("has_" + provider.safeName(ingot.get()), ingot.get().getCritereon(provider)).save(provider, rl);
    }
}
