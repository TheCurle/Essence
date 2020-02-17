package com.teamacronymcoders.essence;

import com.hrznstudio.titanium.module.ModuleController;
import com.teamacronymcoders.essence.impl.serializable.EssenceRecipeProvider;
import com.teamacronymcoders.essence.utils.EssenceReferences;
import com.teamacronymcoders.essence.utils.module.ModuleObjects;
import com.teamacronymcoders.essence.utils.module.Modules;
import com.teamacronymcoders.essence.api.modifier.core.Modifier;
import com.teamacronymcoders.essence.utils.EssenceRegistration;
import com.teamacronymcoders.essence.impl.serializable.EssenceTagProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("essence")
public class Essence extends ModuleController {

    public static final Logger LOGGER = LogManager.getLogger("Essence");

    public Essence() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        EssenceRegistration.register(eventBus);
    }

    @Override
    protected void initModules() {
        addModule(Modules.CORE);
        addModule(Modules.TOOLS);
    }

    @Override
    public void addDataProvider(GatherDataEvent event) {
        super.addDataProvider(event);
        event.getGenerator().addProvider(new EssenceTagProvider.Items(event.getGenerator()));
        event.getGenerator().addProvider(new EssenceTagProvider.Blocks(event.getGenerator()));
        event.getGenerator().addProvider(new EssenceRecipeProvider(event.getGenerator()));
    }

    private void setup(final FMLCommonSetupEvent event) {
        EssenceReferences.CORE_TAB.addIconStacks(new ItemStack(ModuleObjects.ESSENCE_FLUID.getBucketFluid()), new ItemStack(ModuleObjects.ESSENCE_WOOD_SAPLING), new ItemStack(ModuleObjects.ESSENCE_WOOD_LEAVES), new ItemStack(ModuleObjects.ESSENCE_WOOD_LOG), new ItemStack(ModuleObjects.ESSENCE_WOOD_PLANKS));
        EssenceReferences.TOOL_TAB.addIconStacks(new ItemStack(ModuleObjects.ESSENCE_AXE), new ItemStack(ModuleObjects.ESSENCE_PICKAXE), new ItemStack(ModuleObjects.ESSENCE_SHOVEL), new ItemStack(ModuleObjects.ESSENCE_SWORD), new ItemStack(ModuleObjects.ESSENCE_HOE), new ItemStack(ModuleObjects.ESSENCE_OMNITOOL));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModuleObjects.ESSENCE_WOOD_LEAVES, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModuleObjects.ESSENCE_WOOD_SAPLING, RenderType.getCutout());
    }

    public void serverStarting(FMLServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onNewRegistry(RegistryEvent.NewRegistry newRegistry) {
        new RegistryBuilder<Modifier>()
                .setName(new ResourceLocation(EssenceReferences.MODID, "modifiers"))
                .setIDRange(1, Integer.MAX_VALUE - 1)
                .setType(Modifier.class)
                .disableSaving()
                .create();
    }
}
