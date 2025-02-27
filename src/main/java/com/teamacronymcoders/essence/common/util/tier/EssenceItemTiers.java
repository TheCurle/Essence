package com.teamacronymcoders.essence.common.util.tier;

import com.teamacronymcoders.essence.common.block.EssenceBlock;
import com.teamacronymcoders.essence.common.item.essence.EssenceCrystalItem;
import com.teamacronymcoders.essence.common.item.essence.EssenceIngotItem;
import com.teamacronymcoders.essence.common.item.essence.EssenceNuggetItem;
import com.teamacronymcoders.essence.common.util.EssenceTags;
import com.teamacronymcoders.essence.compat.registrate.EssenceBlockRegistrate;
import com.teamacronymcoders.essence.compat.registrate.EssenceItemsRegistry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Supplier;

public enum EssenceItemTiers implements IEssenceBaseTier {
    BASIC("tier.essence.basic", Rarity.COMMON,
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_NUGGET, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_INGOT, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_CRYSTAL, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_CRYSTAL_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_BLOCK),
            3),
    EMPOWERED("tier.essence.empowered", Rarity.UNCOMMON,
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_NUGGET_EMPOWERED, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_EMPOWERED),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_INGOT_EMPOWERED, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_EMPOWERED_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK_EMPOWERED),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_CRYSTAL_EMPOWERED, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_EMPOWERED),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_CRYSTAL_EMPOWERED_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_EMPOWERED_BLOCK),
            4),
    SUPREME("tier.essence.supreme", Rarity.RARE,
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_NUGGET_SUPREME, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_SUPREME),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_INGOT_SUPREME, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_SUPREME_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK_SUPREME),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_CRYSTAL_SUPREME, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_SUPREME),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_CRYSTAL_SUPREME_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_SUPREME_BLOCK),
            5),
    DIVINE("tier.essence.divine", Rarity.EPIC,
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_NUGGET_DIVINE, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_DIVINE),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_INGOT_DIVINE, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_DIVINE_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK_DIVINE),
            () -> Pair.of(EssenceItemsRegistry.ESSENCE_CRYSTAL_DIVINE, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_DIVINE),
            () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_CRYSTAL_DIVINE_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_CRYSTAL_DIVINE_BLOCK),
            6);

    private final String localString;
    private final Rarity rarity;
    private final Supplier<Pair<ItemEntry<EssenceNuggetItem>, Tag.Named<Item>>> nugget;
    private final Supplier<Pair<ItemEntry<EssenceIngotItem>, Tag.Named<Item>>> ingot;
    private final Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> block;
    private final Supplier<Pair<ItemEntry<EssenceCrystalItem>, Tag.Named<Item>>> crystal;
    private final Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> crystalBlock;
    private final int basePoints;

    EssenceItemTiers(String localString, Rarity rarity, Supplier<Pair<ItemEntry<EssenceNuggetItem>, Tag.Named<Item>>> nugget, Supplier<Pair<ItemEntry<EssenceIngotItem>, Tag.Named<Item>>> ingot, Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> block, Supplier<Pair<ItemEntry<EssenceCrystalItem>, Tag.Named<Item>>> crystal, Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> crystalBlock, int basePoints) {
        this.localString = localString;
        this.rarity = rarity;
        this.nugget = nugget;
        this.ingot = ingot;
        this.block = block;
        this.crystal = crystal;
        this.crystalBlock = crystalBlock;
        this.basePoints = basePoints;
    }

    @Override
    public String getLocaleString() {
        return localString;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int getBasePoints() {
        return this.basePoints;
    }

    public Supplier<Pair<ItemEntry<EssenceNuggetItem>, Tag.Named<Item>>> getNugget() {
        return nugget;
    }

    public Supplier<Pair<ItemEntry<EssenceIngotItem>, Tag.Named<Item>>> getIngot() {
        return ingot;
    }

    public Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> getBlock() {
        return block;
    }

    public Supplier<Pair<ItemEntry<EssenceCrystalItem>, Tag.Named<Item>>> getCrystal() {
        return crystal;
    }

    public Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> getCrystalBlock() {
        return crystalBlock;
    }
}
