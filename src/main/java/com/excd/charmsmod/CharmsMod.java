package com.excd.charmsmod;

import com.excd.charmsmod.init.ModEffects;
import com.excd.charmsmod.init.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * @author Greggory Seamon
 */
@Mod(CharmsMod.MODID)
public final class CharmsMod {

    public static final String MODID = "charmsmod";				// Public mod id reference.
    public static final Logger LOGGER = LogUtils.getLogger();	// slf4j logger reference.

    public CharmsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);			// Register setup method for modloading.
        
        ModItems.ITEMS.register(modEventBus);			// Register items to mod event bus.
        ModEffects.MOB_EFFECTS.register(modEventBus);	// Register mob effects to mod event bus.

        MinecraftForge.EVENT_BUS.register(this);		// Register class to core Forge event bus.
    }

    private void setup(final FMLCommonSetupEvent event) {
    	// Setup code.
        LOGGER.info("Charms Mod Setup!");
    }
}