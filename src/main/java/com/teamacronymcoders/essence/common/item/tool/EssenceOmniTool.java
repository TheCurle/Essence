package com.teamacronymcoders.essence.common.item.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.teamacronymcoders.essence.api.modified.rewrite.IModifiedItem;
import com.teamacronymcoders.essence.api.modified.rewrite.itemstack.ItemStackModifierProvider;
import com.teamacronymcoders.essence.api.recipe.tool.AxeStrippingRecipe;
import com.teamacronymcoders.essence.api.recipe.tool.ShovelPathingRecipe;
import com.teamacronymcoders.essence.common.util.EssenceTags;
import com.teamacronymcoders.essence.common.util.helper.EssenceItemstackModifierHelpers;
import com.teamacronymcoders.essence.common.util.tier.EssenceToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EssenceOmniTool extends DiggerItem implements IModifiedItem {

    private final EssenceToolTiers tier;

    //TODO: Figure out what to do about EFFECTIVE_ON
    public EssenceOmniTool(Properties properties, EssenceToolTiers tier) {
        super(tier.getAttackDamageBonus(), tier.getSpeed(), tier, EssenceTags.EssenceBlockTags.OMNITOOL_BLOCKS, properties.rarity(tier.getRarity()));
        this.tier = tier;
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull Rarity getRarity(ItemStack stack) {
        return tier.getRarity();
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isFoil(ItemStack stack) {
        return EssenceItemstackModifierHelpers.hasEnchantedModifier(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack) + getMaxDurabilityFromModifiers(stack, tier);
    }

    @Override
    @ParametersAreNonnullByDefault
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state) + getDestroySpeedFromModifiers(stack, state, super.getDestroySpeed(stack, state));
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        hurtEnemyFromModifiers(stack, target, attacker);
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        mineBlockFromModifiers(stack, level, state, pos, entityLiving);
        return super.mineBlock(stack, level, state, pos, entityLiving);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
        inventoryTickFromModifiers(stack, level, entityIn, itemSlot, isSelected);
        super.inventoryTick(stack, level, entityIn, itemSlot, isSelected);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            return getAttributeModifiersFromModifiers(getDefaultAttributeModifiers(slot), slot, stack);
        }
        return HashMultimap.create();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, level, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("tooltip.essence.tool.tier").withStyle(ChatFormatting.GRAY).append(new TranslatableComponent(tier.getLocaleString()).withStyle(tier.getRarity().color)));
        addInformationFromModifiers(stack, level, tooltip, flagIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        if (!stack.isEmpty() && nbt != null) {
            return new ItemStackModifierProvider(stack, nbt);
        }
        return new ItemStackModifierProvider(stack);
    }

    @Override
    public @NotNull EssenceToolTiers getTier() {
        return tier;
    }

    @Override
    public InteractionResult useOnModified(UseOnContext context, boolean isRecursive) {
        return useOn(context);
    }
}
