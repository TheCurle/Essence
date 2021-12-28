package com.teamacronymcoders.essence.util.tier;

import com.teamacronymcoders.essence.block.EssenceBlock;
import com.teamacronymcoders.essence.item.essence.EssenceIngotItem;
import com.teamacronymcoders.essence.item.essence.EssenceNuggetItem;
import com.teamacronymcoders.essence.registrate.EssenceBlockRegistrate;
import com.teamacronymcoders.essence.registrate.EssenceItemRegistrate;
import com.teamacronymcoders.essence.util.EssenceTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Supplier;

public enum EssenceItemTiers implements IEssenceBaseTier {
  BASIC("tier.essence.basic", Rarity.COMMON, () -> Pair.of(EssenceItemRegistrate.ESSENCE_NUGGET, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET), () -> Pair.of(EssenceItemRegistrate.ESSENCE_INGOT, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL), () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK)),
  EMPOWERED("tier.essence.empowered", Rarity.UNCOMMON, () -> Pair.of(EssenceItemRegistrate.ESSENCE_NUGGET_EMPOWERED, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_EMPOWERED), () -> Pair.of(EssenceItemRegistrate.ESSENCE_INGOT_EMPOWERED, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_EMPOWERED), () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_EMPOWERED_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK_EMPOWERED)),
  SUPREME("tier.essence.supreme", Rarity.RARE, () -> Pair.of(EssenceItemRegistrate.ESSENCE_NUGGET_SUPREME, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_SUPREME), () -> Pair.of(EssenceItemRegistrate.ESSENCE_INGOT_SUPREME, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_SUPREME), () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_SUPREME_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK_SUPREME)),
  DIVINE("tier.essence.divine", Rarity.EPIC, () -> Pair.of(EssenceItemRegistrate.ESSENCE_NUGGET_DIVINE, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_NUGGET_DIVINE), () -> Pair.of(EssenceItemRegistrate.ESSENCE_INGOT_DIVINE, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_DIVINE), () -> Pair.of(EssenceBlockRegistrate.ESSENCE_INFUSED_METAL_DIVINE_BLOCK, EssenceTags.EssenceItemTags.ESSENCE_INFUSED_METAL_BLOCK_DIVINE));

  private final String localString;
  private final Rarity rarity;
  private final Supplier<Pair<ItemEntry<EssenceNuggetItem>, Tag.Named<Item>>> nugget;
  private final Supplier<Pair<ItemEntry<EssenceIngotItem>, Tag.Named<Item>>> ingot;
  private final Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> block;

  EssenceItemTiers(String localString, Rarity rarity, Supplier<Pair<ItemEntry<EssenceNuggetItem>, Tag.Named<Item>>> nugget, Supplier<Pair<ItemEntry<EssenceIngotItem>, Tag.Named<Item>>> ingot, Supplier<Pair<BlockEntry<EssenceBlock>, Tag.Named<Item>>> block) {
    this.localString = localString;
    this.rarity = rarity;
    this.nugget = nugget;
    this.ingot = ingot;
    this.block = block;
  }

  @Override
  public String getLocaleString() {
    return localString;
  }

  public Rarity getRarity() {
    return rarity;
  }

  @Override
  public int getFreeModifiers() {
    return 0;
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
}
