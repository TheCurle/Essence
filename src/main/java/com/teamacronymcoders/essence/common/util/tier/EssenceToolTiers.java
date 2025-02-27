package com.teamacronymcoders.essence.common.util.tier;

import com.teamacronymcoders.essence.Essence;
import com.teamacronymcoders.essence.compat.registrate.EssenceItemsRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.util.Lazy;

import java.util.List;
import java.util.function.Supplier;

public enum EssenceToolTiers implements Tier, IEssenceBaseTier {
    ESSENCE("tier.essence.basic", "basic", 3, 2, 256, 384, 6.0F,
            2.0F, 6.0F, 1, 1.5F, 3, -3.1F, -1.0F, -2.8F, -3.0F, -2.4F,
            0, Rarity.COMMON, () -> {
                return Ingredient.of(EssenceItemsRegistry.ESSENCE_INGOT.get());
            }, List.of(), List.of(Tiers.STONE)
    ),
    EMPOWERED("tier.essence.empowered", "empowered", 4, 3, 1561, 2332, 8.0F,
            3.0F, 6.0F, 1, 1.5F, 3, -3.1F, -1.0F, -2.8F, -3.0F, -2.4F,
            0, Rarity.UNCOMMON, () -> {
                return Ingredient.of(EssenceItemsRegistry.ESSENCE_INGOT_EMPOWERED.get());
            }, List.of(), List.of(Tiers.IRON)
    ),
    SUPREME("tier.essence.supreme", "supreme", 5, 4, 2031, 3047, 9.0F,
            4.0F, 6.0F, 1, 1.5F, 3, -3.1F, -1.0F, -2.8F, -3.0F, -2.4F,
            0, Rarity.RARE, () -> {
                return Ingredient.of(EssenceItemsRegistry.ESSENCE_INGOT_SUPREME.get());
            }, List.of(), List.of(Tiers.DIAMOND)
    ),
    DIVINE("tier.essence.divine", "divine", 6, 6, 4062, 6093, 10.0F,
            6.0F, 6.0F, 1, 1.5F, 3, -3.1F, -1.0F, -2.8F, -3.0F, -2.4F,
            0, Rarity.EPIC, () -> {
                return Ingredient.of(EssenceItemsRegistry.ESSENCE_INGOT_DIVINE.get());
            }, List.of(), List.of(Tiers.NETHERITE)
    );

    private final String localName;
    private final int freeModifiers;
    private final int level;
    private final int maxUses;
    private final int maxUsesBow;
    private final float speed;
    private final float attackDamageGeneral;
    private final float attackDamageAxeMod;
    private final int attackDamagePickaxeMod;
    private final float attackDamageShovelMod;
    private final int attackDamageSwordMod;
    private final float attackSpeedAxeMod;
    private final float attackSpeedHoeMod;
    private final float attackSpeedPickaxeMod;
    private final float attackSpeedShovelMod;
    private final float attackSpeedSwordMod;
    private final int enchantability;
    private final Rarity rarity;
    private final Lazy<Ingredient> repairMaterial;

    EssenceToolTiers(String localName, String resourceName, int freeModifiers, int harvestLevel, int maxUses, int maxUsesBow, float efficiency, float attackDamageGeneral, float attackDamageAxeMod, int attackDamagePickaxeMod, float attackDamageShovelMod, int attackDamageSwordMod, float attackSpeedAxeMod, float attackSpeedHoeMod, float attackSpeedPickaxeMod, float attackSpeedShovelMod, float attackSpeedSwordMod, int enchantability, Rarity rarity, Supplier<Ingredient> repairMaterial, List<Object> before, List<Object> after) {
        // Register the tier to the sorting registry
        // TODO: this entire enum needs to be split into static fields - C
        TierSortingRegistry.registerTier(this, new ResourceLocation(Essence.MOD_ID, resourceName), before, after);
        this.localName = localName;
        this.freeModifiers = freeModifiers;
        this.level = harvestLevel;
        this.maxUses = maxUses;
        this.maxUsesBow = maxUsesBow;
        this.speed = efficiency;
        this.attackDamageGeneral = attackDamageGeneral;
        this.attackDamageAxeMod = attackDamageAxeMod;
        this.attackDamagePickaxeMod = attackDamagePickaxeMod;
        this.attackDamageShovelMod = attackDamageShovelMod;
        this.attackDamageSwordMod = attackDamageSwordMod;
        this.attackSpeedAxeMod = attackSpeedAxeMod;
        this.attackSpeedHoeMod = attackSpeedHoeMod;
        this.attackSpeedPickaxeMod = attackSpeedPickaxeMod;
        this.attackSpeedShovelMod = attackSpeedShovelMod;
        this.attackSpeedSwordMod = attackSpeedSwordMod;
        this.enchantability = enchantability;
        this.rarity = rarity;
        this.repairMaterial = Lazy.of(repairMaterial);
    }

    @Override
    public int getBasePoints() {
        return freeModifiers;
    }

    public int getMaxUsesBow() {
        return maxUsesBow;
    }

    public float getAttackDamageAxeMod() {
        return attackDamageAxeMod;
    }

    public int getAttackDamagePickaxeMod() {
        return attackDamagePickaxeMod;
    }

    public float getAttackDamageShovelMod() {
        return attackDamageShovelMod;
    }

    public int getAttackDamageSwordMod() {
        return attackDamageSwordMod;
    }

    public float getAttackSpeedAxeMod() {
        return attackSpeedAxeMod;
    }

    public float getAttackSpeedHoeMod() {
        return attackSpeedHoeMod;
    }

    public float getAttackSpeedPickaxeMod() {
        return attackSpeedPickaxeMod;
    }

    public float getAttackSpeedShovelMod() {
        return attackSpeedShovelMod;
    }

    public float getAttackSpeedSwordMod() {
        return attackSpeedSwordMod;
    }

    @Override
    public String getLocaleString() {
        return localName;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageGeneral;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}

