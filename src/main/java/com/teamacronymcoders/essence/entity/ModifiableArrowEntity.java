package com.teamacronymcoders.essence.entity;

import com.google.common.collect.Sets;
import com.teamacronymcoders.essence.api.modifier.item.extendable.ItemArrowCoreModifier;
import com.teamacronymcoders.essence.capability.EssenceCoreCapability;
import com.teamacronymcoders.essence.registrate.EssenceEntityRegistrate;
import com.teamacronymcoders.essence.util.helper.EssenceItemstackModifierHelpers;
import java.util.Collection;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpectralArrowItem;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class ModifiableArrowEntity extends AbstractArrowEntity {

  private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(ModifiableArrowEntity.class, DataSerializers.VARINT);
  private Potion potion = Potions.EMPTY;
  private final Set<EffectInstance> customPotionEffects = Sets.newHashSet();
  private boolean fixedColor;
  private ItemStack bowStack = ItemStack.EMPTY;

  public ModifiableArrowEntity(EntityType<ModifiableArrowEntity> type, World world) {
    super(type, world);
  }

  public ModifiableArrowEntity(World worldIn, double x, double y, double z, ItemStack bowStack, ItemStack arrowStack) {
    super(EssenceEntityRegistrate.ARROW_ENTITY.get(), x, y, z, worldIn);
    this.bowStack = bowStack.copy();
    this.setArrowEffects(arrowStack);
    if (arrowStack.getItem() instanceof TippedArrowItem || arrowStack.getItem() instanceof SpectralArrowItem) {
      this.pickupStatus = PickupStatus.DISALLOWED;
    }
  }

  public ModifiableArrowEntity(World worldIn, LivingEntity shooter, ItemStack bowStack, ItemStack arrowStack) {
    super(EssenceEntityRegistrate.ARROW_ENTITY.get(), shooter, worldIn);
    this.bowStack = bowStack;
    this.setArrowEffects(arrowStack);
    if (arrowStack.getItem() instanceof TippedArrowItem || arrowStack.getItem() instanceof SpectralArrowItem) {
      this.pickupStatus = PickupStatus.DISALLOWED;
    }
  }

  public void setArrowEffects(ItemStack stack) {
    if (stack.getItem() instanceof TippedArrowItem) {
      this.potion = PotionUtils.getPotionFromItem(stack);
      Collection<EffectInstance> collection = PotionUtils.getFullEffectsFromItem(stack);
      if (!collection.isEmpty()) {
        this.customPotionEffects.addAll(collection);
      }
      int i = getCustomColor(stack);
      if (i == -1) {
        this.refreshColor();
      } else {
        this.setFixedColor(i);
      }
    } else if (stack.getItem() == Items.ARROW) {
      this.potion = Potions.EMPTY;
      this.customPotionEffects.clear();
      this.dataManager.set(COLOR, -1);
    }
  }

  public static int getCustomColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getTag();
    return compoundnbt != null && compoundnbt.contains("CustomPotionColor", 99) ? compoundnbt.getInt("CustomPotionColor") : -1;
  }

  private void refreshColor() {
    this.fixedColor = false;
    if (this.potion == Potions.EMPTY && this.customPotionEffects.isEmpty()) {
      this.dataManager.set(COLOR, -1);
    } else {
      this.dataManager.set(COLOR, PotionUtils.getPotionColorFromEffectList(PotionUtils.mergeEffects(this.potion, this.customPotionEffects)));
    }
  }

  public void addEffect(EffectInstance effect) {
    this.customPotionEffects.add(effect);
    this.getDataManager().set(COLOR, PotionUtils.getPotionColorFromEffectList(PotionUtils.mergeEffects(this.potion, this.customPotionEffects)));
  }

  protected void registerData() {
    super.registerData();
    this.dataManager.register(COLOR, -1);
  }

  /**
   * Called to update the entity's position/logic.
   */
  public void tick() {
    super.tick();
    if (this.world.isRemote) {
      if (this.inGround) {
        if (this.timeInGround % 5 == 0) {
          this.spawnPotionParticles(1);
        }
      } else {
        this.spawnPotionParticles(2);
      }
    } else if (this.inGround && this.timeInGround != 0 && !this.customPotionEffects.isEmpty() && this.timeInGround >= 600) {
      this.world.setEntityState(this, (byte) 0);
      this.potion = Potions.EMPTY;
      this.customPotionEffects.clear();
      this.dataManager.set(COLOR, -1);
    }
  }

  private void spawnPotionParticles(int particleCount) {
    int i = this.getColor();
    if (i != -1 && particleCount > 0) {
      double d0 = (double) (i >> 16 & 255) / 255.0D;
      double d1 = (double) (i >> 8 & 255) / 255.0D;
      double d2 = (double) (i >> 0 & 255) / 255.0D;
      for (int j = 0; j < particleCount; ++j) {
        this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), d0, d1, d2);
      }

    }
  }

  public int getColor() {
    return this.dataManager.get(COLOR);
  }

  private void setFixedColor(int color) {
    this.fixedColor = true;
    this.dataManager.set(COLOR, color);
  }

  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    if (this.potion != Potions.EMPTY && this.potion != null) {
      compound.putString("Potion", ForgeRegistries.POTION_TYPES.getKey(this.potion).toString());
    }
    if (this.fixedColor) {
      compound.putInt("Color", this.getColor());
    }
    if (!this.customPotionEffects.isEmpty()) {
      ListNBT listnbt = new ListNBT();
      for (EffectInstance effectinstance : this.customPotionEffects) {
        listnbt.add(effectinstance.write(new CompoundNBT()));
      }
      compound.put("CustomPotionEffects", listnbt);
    }
  }

  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    if (compound.contains("Potion", 8)) {
      this.potion = PotionUtils.getPotionTypeFromNBT(compound);
    }
    for (EffectInstance effectinstance : PotionUtils.getFullEffectsFromTag(compound)) {
      this.addEffect(effectinstance);
    }
    if (compound.contains("Color", 99)) {
      this.setFixedColor(compound.getInt("Color"));
    } else {
      this.refreshColor();
    }
  }

  protected void arrowHit(LivingEntity living) {
    super.arrowHit(living);
    for (EffectInstance effectinstance : this.potion.getEffects()) {
      living.addPotionEffect(new EffectInstance(effectinstance.getPotion(), Math.max(effectinstance.getDuration() / 8, 1), effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.doesShowParticles()));
    }
    if (!this.customPotionEffects.isEmpty()) {
      for (EffectInstance instance : this.customPotionEffects) {
        living.addPotionEffect(instance);
      }
    }
  }

  protected ItemStack getArrowStack() {
    return new ItemStack(Items.ARROW);
  }

  /**
   * Handler for {@link World#setEntityState}
   */
  @OnlyIn(Dist.CLIENT)
  public void handleStatusUpdate(byte id) {
    if (id == 0) {
      int i = this.getColor();
      if (i != -1) {
        double d0 = (double) (i >> 16 & 255) / 255.0D;
        double d1 = (double) (i >> 8 & 255) / 255.0D;
        double d2 = (double) (i >> 0 & 255) / 255.0D;
        for (int j = 0; j < 20; ++j) {
          this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), d0, d1, d2);
        }
      }
    } else {
      super.handleStatusUpdate(id);
    }
  }

  // func_230299_a_ = onCollide
  // func_234616_v_ = getShooter
  @Override
  protected void func_230299_a_(BlockRayTraceResult result) {
    super.func_230299_a_(result);
    Entity shooter = this.func_234616_v_();
    if (EssenceItemstackModifierHelpers.hasSoakedModifier(bowStack)) {
      bowStack.getCapability(EssenceCoreCapability.ITEMSTACK_MODIFIER_HOLDER).map(holder -> holder.getModifierInstances().stream().filter(instance -> instance.getModifier() instanceof ItemArrowCoreModifier)).ifPresent(instances -> instances.forEach(instance -> {
        //LOGGER.info("This is a test logger message");
        ItemArrowCoreModifier modifier = (ItemArrowCoreModifier) instance.getModifier();
        modifier.onCollide(bowStack, this, (PlayerEntity) shooter, result, instance);
      }));
    }
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}
