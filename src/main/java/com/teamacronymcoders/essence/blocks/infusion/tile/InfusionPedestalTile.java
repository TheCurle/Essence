package com.teamacronymcoders.essence.blocks.infusion.tile;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.block.tile.ActiveTile;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.teamacronymcoders.essence.utils.EssenceObjectHolders;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class InfusionPedestalTile extends ActiveTile<InfusionPedestalTile> {

    @Save
    private Integer ticksExisted = 0;

    @Save
    private InventoryComponent<InfusionPedestalTile> inventory;

    public InfusionPedestalTile() {
        super(EssenceObjectHolders.INFUSION_PEDESTAL);
        addInventory(inventory = new InventoryComponent<InfusionPedestalTile>("inventory", 0, 0, 1)
            .setComponentHarness(this)
            .setOnSlotChanged((stack, integer) -> markComponentForUpdate())
            .setSlotLimit(1)
        );
    }

    @Override
    public void tick() {
        super.tick();
        ticksExisted++;
    }

    @Nonnull
    @Override
    public InfusionPedestalTile getSelf() {
        return this;
    }

    public void addItem(ItemStack stack) {
        this.inventory.insertItem(0, stack, false);
    }

    public ItemStack getStack() {
        return this.inventory.getStackInSlot(0);
    }

    public Integer getTicksExisted() {
        return ticksExisted;
    }
}
