package com.teamacronymcoders.essence.modifier.item.enchantment;

import com.teamacronymcoders.essence.api.holder.ModifierInstance;
import com.teamacronymcoders.essence.api.modifier.item.extendable.ItemEnchantmentCoreModifier;
import com.teamacronymcoders.essence.item.tool.EssenceBow;
import com.teamacronymcoders.essence.util.helper.EssenceEnchantmentHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class InfinityModifier extends ItemEnchantmentCoreModifier {

  public InfinityModifier() {
    super();
  }

  @Override
  public void onInventoryTick(ItemStack stack, Level level, Entity entity, int inventorySlot, boolean isCurrentItem, ModifierInstance instance) {
    EssenceEnchantmentHelper.createOrUpdateEnchantment(stack, getLinkedEnchantment(stack), instance);
  }

  @Override
  public Enchantment getLinkedEnchantment(ItemStack stack) {
    return Enchantments.INFINITY_ARROWS;
  }

  @Override
  public boolean canApplyOnObject(ItemStack object) {
    return object.getItem() instanceof EssenceBow;
  }

}
