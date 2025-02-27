package com.teamacronymcoders.essence.common.util.config;

import com.teamacronymcoders.essence.common.util.config.extendables.EssenceCascadingConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class EssenceModifierConfig {

    private static EssenceModifierConfig instance;
    private final ForgeConfigSpec spec;

    private final EssenceCascadingConfig excavation;
    private final EssenceCascadingConfig lumber;
    private final EssenceCascadingConfig vein;

    public EssenceModifierConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Modifiers");
        this.excavation = new EssenceCascadingConfig(builder, "Excavation", 125, 25);
        this.lumber = new EssenceCascadingConfig(builder, "Lumber", 125, 75);
        this.vein = new EssenceCascadingConfig(builder, "Vein", 125, 25);
        builder.pop();
        this.spec = builder.build();
    }

    public static ForgeConfigSpec initialize() {
        EssenceModifierConfig config = new EssenceModifierConfig(new ForgeConfigSpec.Builder());
        instance = config;
        return config.getSpec();
    }

    public static EssenceModifierConfig getInstance() {
        return instance;
    }

    public ForgeConfigSpec getSpec() {
        return spec;
    }

    public EssenceCascadingConfig getExcavation() {
        return excavation;
    }

    public EssenceCascadingConfig getLumber() {
        return lumber;
    }

    public EssenceCascadingConfig getVein() {
        return vein;
    }
}
