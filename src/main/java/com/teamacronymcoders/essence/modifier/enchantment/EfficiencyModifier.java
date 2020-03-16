package com.teamacronymcoders.essence.modifier.enchantment;

import com.teamacronymcoders.essence.api.modifier.EnchantmentCoreModifier;
import com.teamacronymcoders.essence.api.tool.ModifierInstance;
import com.teamacronymcoders.essence.items.tools.EssenceAxe;
import com.teamacronymcoders.essence.items.tools.EssenceOmniTool;
import com.teamacronymcoders.essence.items.tools.EssencePickaxe;
import com.teamacronymcoders.essence.items.tools.EssenceShovel;
import com.teamacronymcoders.essence.utils.helpers.EssenceEnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EfficiencyModifier extends EnchantmentCoreModifier {

    public EfficiencyModifier() {
        super(5);
    }

    @Override
    public void onInventoryTick(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isCurrentItem, ModifierInstance instance) {
        EssenceEnchantmentHelper.createOrUpdateEnchantment(stack, getLinkedEnchantment(stack), instance, 0);
    }

    @Override
    public Enchantment getLinkedEnchantment(ItemStack stack) {
        return Enchantments.EFFICIENCY;
    }

    @Override
    public boolean canApplyOnItemStack(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof EssenceAxe || item instanceof EssencePickaxe || item instanceof EssenceShovel || item instanceof EssenceOmniTool;
    }
}
